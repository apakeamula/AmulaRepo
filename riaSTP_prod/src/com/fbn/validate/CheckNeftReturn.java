package com.fbn.validate;

import com.fbn.db.jpa.GiroTbl;
import com.fbn.db.jpa.GiroTblNaps;
import com.fbn.db.jpa.RiaDtlTbl;
import com.fbn.db.jpa.RiaDtlTblJpaController;
import com.fbn.db.jpa.exceptions.NonexistentEntityException;
import com.fbn.riastp.loadProp;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import org.apache.log4j.Logger;

public class CheckNeftReturn {

    private static final Logger logFile = Logger.getLogger(CheckNeftReturn.class);
    @PersistenceUnit(unitName = "riaSTPPU")
    EntityManagerFactory emf;

    public void checkNeftRet()
            throws NonexistentEntityException, Exception {
        try {
            System.out.println("Called checkNeftRet");
            logFile.info("Called checkNeftRet");

            boolean clearingDays = true;
            this.emf = Persistence.createEntityManagerFactory("riaSTPPU");
            Query returned = this.emf.createEntityManager().createNamedQuery("RiaDtlTbl.findByNeftReturnPayRef").setParameter("fbnFlg", Character.valueOf('N')).setParameter("pstdFlg", Character.valueOf('Y')).setParameter("returnedFlg", Character.valueOf('X'));

            List<RiaDtlTbl> getRec = returned.getResultList();
            if (!getRec.isEmpty()) {
                for (RiaDtlTbl orderRec : getRec) {
                    String payref = orderRec.getPayRef();
                    String orderNo = orderRec.getOrderno();
                    long batchSerialNum = orderRec.getBatchSerialNum();
                    String serialNumStr = String.valueOf(batchSerialNum);
                    int retrycount = Integer.parseInt(orderRec.getRetryCount());
                    String neftornaps=orderRec.getNeftornapsPicked();
                    logFile.info("Batch Serial Number and payment reference -- " + serialNumStr + " -- " + payref);
                    if (neftornaps.equalsIgnoreCase("NEFT")) {
                        logFile.info("Transaction is a NEFT transaction -- " + payref);
                        this.emf = Persistence.createEntityManagerFactory("riaSTPPU");
                        Query giroCheck = this.emf.createEntityManager().createNamedQuery("GiroTbl.findOne").setParameter("payref", payref).setParameter("serialnum", Long.valueOf(batchSerialNum));

                        Object gsingle = giroCheck.getSingleResult();
                        GiroTbl gTable = new GiroTbl();
                        if (gsingle != null) {
                            gTable = (GiroTbl) gsingle;
                            logFile.info("Record in Giro Table -- " + gTable + " --- " + payref + " -- " + batchSerialNum);

                        } else {
                            logFile.info("Record is  not in giro table -- " + payref + " -- " + batchSerialNum);
                            continue;
                        }
                        while (serialNumStr.length() < 4) {
                            serialNumStr = "0" + serialNumStr;
                        }
                        logFile.info("Batch Serial Number after padding and payment reference -- " + serialNumStr + " -- " + payref + " -- " + orderNo);

                        String finalbatchNumPassed = payref + serialNumStr;
                        logFile.info("Original Pres Item Sequence number in DB and payment reference -- " + finalbatchNumPassed + " -- " + payref);
                        Date processDate = orderRec.getProcessedDate();

                        Timestamp sysdate = new Timestamp(loadProp.SDF.parse(Calendar.getInstance().getTime().toString()).getTime());
                        System.out.println("procDate " + processDate);
                        logFile.info("procDate " + processDate);
                        System.out.println("sysdate " + sysdate);
                        logFile.info("sysdate " + sysdate);
                        long diff = sysdate.getTime() - processDate.getTime();
                        logFile.info("Difference itself -- " + diff);
                        long diffHours = TimeUnit.MILLISECONDS.toHours(diff);
                        long diffDays = TimeUnit.MILLISECONDS.toDays(diff);

                        System.out.println(diffHours + " -- " + diffDays);
                        logFile.info("diffHours -- " + diffHours);
                        logFile.info("diffDays -- " + diffDays);
                        logFile.info("USEDAYs Value -- " + loadProp.USEDAYS);
                        logFile.info("Value of clearing days before check -- " + clearingDays);
                        if (loadProp.USEDAYS.equals("Y")) {
                            if ((int) diffDays <= Integer.valueOf(loadProp.CLEARINGDAYS)) {
                                logFile.info("USEDAYS is Y -- " + diffDays + " diffDays is less than clearingdays ");
                                clearingDays = false;
                            }
                        } else if ((int) diffHours <= Integer.valueOf(loadProp.CLEARINGHOURS)) {
                            logFile.info("USEDAYS is N -- " + diffHours + " diffHours is less than clearinghours ");
                            clearingDays = false;
                        }
                        System.out.println("clearingDays " + clearingDays);
                        logFile.info("clearingDays " + clearingDays + " --- " + payref + " --- " + batchSerialNum);
                        if (clearingDays) {
                            GetReturnNeft gRN = new GetReturnNeft();
                            String payRefStatus = gRN.getRec(finalbatchNumPassed);
                            System.out.println("payRefStatus=" + payRefStatus + " -- " + payref + " --- " + batchSerialNum);
                            logFile.info("payRefStatus=" + payRefStatus + " -- " + payref + " --- " + batchSerialNum);
                            RiaDtlTblJpaController emm = new RiaDtlTblJpaController(this.emf);
                            emm.getEntityManager();
                            RiaDtlTbl ord = emm.findRiaDtlTbl(orderNo);
                            if (payRefStatus.equalsIgnoreCase("NO")) {
                                ord.setReturnedFlg('N');
                                ord.setUpdateTime(Calendar.getInstance().getTime());
                                ord.setRetryCount(String.valueOf(retrycount + 1));
                            } else {
                                ord.setReturnedFlg('Y');
                                ord.setUpdateTime(Calendar.getInstance().getTime());
                                ord.setRetryCount(String.valueOf(retrycount + 1));
                                ord.setFailReason(payRefStatus);
                                ord.setFailFlg('F');
                            }
                            emm.edit(ord);
                        }
                    } else {

                        logFile.info("It is a NAPS transactions ==> " + payref + " == " + serialNumStr);
                        this.emf = Persistence.createEntityManagerFactory("riaSTPPU");
                        Query giroCheck = this.emf.createEntityManager().createNamedQuery("GiroTblNaps.findOne").setParameter("payref", payref).setParameter("serialnum", Long.valueOf(batchSerialNum));
                        Object gsingle = giroCheck.getSingleResult();
                        GiroTblNaps gTable = new GiroTblNaps();
                        if (gsingle != null) {
                            gTable = (GiroTblNaps) gsingle;
                            logFile.info("Record in Giro Naps Table -- " + gTable + " --- " + payref + " -- " + batchSerialNum);

                        } else {
                            logFile.info("Record is  not in giro Naps table -- " + payref + " -- " + batchSerialNum);
                            continue;
                        }

                        logFile.info("Transmit flag for record ==> " + gTable.getTransactionUniqueId() + " == " + gTable.getTransmitflg() + " == " + gTable.getTransactionStatus());

                        logFile.info("Transaction is a NAPS transaction!!!! " + payref + " --- " + batchSerialNum + " -- " + gTable.getTransactionUniqueId() + " -- " + gTable.getTransactionStatus());
                        String transactionStatus = gTable.getTransactionStatus().trim();
                        logFile.info("Transaction Status after Trim ==> " + transactionStatus + " ==> " + orderNo);
                        if (transactionStatus.equalsIgnoreCase("PENDING")) {
                            logFile.info("Transaction is pending processing -- " + payref + " --- " + batchSerialNum);
                        } else {
                            logFile.info("Transaction has been processed!! Get Transaction Status -- " + payref + " --- " + batchSerialNum + " -- " + transactionStatus);
                            RiaDtlTblJpaController emm = new RiaDtlTblJpaController(this.emf);
                            emm.getEntityManager();
                            RiaDtlTbl ord = emm.findRiaDtlTbl(orderNo);
                            logFile.info("Get Transaction Status after get record from ria table -- " + payref + " --- " + batchSerialNum + " -- " + transactionStatus);
                            if (transactionStatus.equalsIgnoreCase("PAID")) {
                                ord.setReturnedFlg('N');
                                ord.setUpdateTime(Calendar.getInstance().getTime());
                                ord.setRetryCount(String.valueOf(retrycount + 1));
                                emm.edit(ord);
                            } else if ((transactionStatus.equalsIgnoreCase("UNPAID")) || (transactionStatus.equalsIgnoreCase("REJECTED")) || (transactionStatus.equalsIgnoreCase("NOT_APPROVED")) || (transactionStatus.equalsIgnoreCase("CANCELLED"))) {
                                logFile.info("Transaction failed completely - update ==> " + orderNo + " == " + transactionStatus);
                                ord.setReturnedFlg('Y');
                                ord.setUpdateTime(Calendar.getInstance().getTime());
                                ord.setRetryCount(String.valueOf(retrycount + 1));
                                ord.setFailReason(transactionStatus);
                                ord.setFailFlg('F');
                                emm.edit(ord);
                            } else {
                                logFile.info("Transaction not processed ==> " + orderNo + " == " + transactionStatus);
                            }
                        }

                    }
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            logFile.error("Error occurred in checkNeftRet -- " + ex.getLocalizedMessage() + " -- " + ex.toString());
        }
    }

}
