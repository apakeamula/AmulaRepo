package com.fbn.riathreads;

import com.fbn.bancsconnect.GetTranId;
import com.fbn.bancsconnect.PostTransaction;
import com.fbn.bancsconnect.ResponseCode;
import com.fbn.db.jpa.AtcTblDirectFeeCheck;
import com.fbn.db.jpa.CommissionFeeData;
import com.fbn.db.jpa.GiroTblJpaController;
import com.fbn.db.jpa.GiroTblJpaControllerNaps;
import com.fbn.db.jpa.RiaDtlTbl;
import com.fbn.db.jpa.RiaDtlTblJpaController;
import com.fbn.db.jpa.TrandetailsData;
import com.fbn.ria.util.DecryptionCode;
import com.fbn.riastp.loadProp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.apache.log4j.Logger;

public class PostInsertNeftGiro {

    private static final Logger logFile = Logger.getLogger(PostInsertNeftGiro.class);

    public void PostNeftTransactions() {
        String orderNo = "";
        String bnfPIN = "";
        String debitSuspense = "";
        String creditSuspense = "";
        String tranAmount = "";
        Date createDate = null;
        String postResponse = "";
        String trancurr = "";
        String suspenseAccount = "";
        Date suspenseDate = null;
        String paymentref = "";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("riaSTPPU");
        EntityManager em = emf.createEntityManager();
        String isNaps = "NO";
        try {
            logFile.info("Get all pending Neft transactions");
            Query neftTrans = em.createNamedQuery("RiaDtlTbl.findByNeftReceived").setParameter("fbnFlg", Character.valueOf('N')).setParameter("neftPicked", "N").setParameter("failFlg", Character.valueOf('N')).setParameter("processed", Character.valueOf('Y'));
            List<RiaDtlTbl> neftTransactions = neftTrans.getResultList();
            logFile.info("How many records in the list neftTransactions class -- " + neftTransactions.size());
            int count = 0;
            for (RiaDtlTbl neft : neftTransactions) {
                count += 1;
                logFile.info("Process the transaction first -- " + neft.getOrderno());
                creditSuspense = loadProp.NEFTSUSPENSELIST[java.util.Arrays.asList(loadProp.NEFTCURRENCYLIST).indexOf(neft.getBeneficiarycurrency())];
                debitSuspense = loadProp.NEFTDEBITACCOUNT;
                suspenseAccount = loadProp.NEFTSUSPENSEACCOUNT;
                logFile.info("Neft credit and debit suspense accounts -- " + creditSuspense + " -- debit -- " + debitSuspense);
                tranAmount = neft.getBeneficiaryamount();
                createDate = null;
                orderNo = neft.getOrderno();
                bnfPIN = neft.getPin();
                char postedflag = neft.getPstdFlg().charValue();
                logFile.info("Tran Pin -- " + bnfPIN);
                trancurr = neft.getBeneficiarycurrency();
                String senderName = "";
                if (neft.getCustfirstname() != null) {
                    senderName = senderName + neft.getCustfirstname();
                }
                if (neft.getCustlastname() != null) {
                    senderName = senderName + " " + neft.getCustlastname();
                }
                logFile.info("Senders Name = " + senderName);
                String tranReason = "RIA NEFT/" + bnfPIN + "/" + senderName;
                paymentref = neft.getPayRef();
                if (neft.getCreTime() == null) {
                    createDate = new Date();
                    try {
                        RiaDtlTblJpaController em2 = new RiaDtlTblJpaController(emf);
                        em2.getEntityManager();
                        RiaDtlTbl ord = em2.findRiaDtlTbl(orderNo);
                        ord.setCreTime(createDate);
                        em2.edit(ord);
                        logFile.info("Creation date entered in database --" + orderNo);
                    } catch (Exception ex) {
                        logFile.error("Error occurred in set creation time -- " + ex.getMessage());
                    }
                } else {
                    createDate = neft.getCreTime();
                }
                if (postedflag == 'Y') {
                    logFile.info("Transaction posted already try to insert into Giro !!!  " + neft.getOrderno());
                    postResponse = "000";
                } else {
                    PostTransaction pstRec = new PostTransaction();
                    AtcTblDirectFeeCheck atcCheck = new AtcTblDirectFeeCheck();
                    String totCommission = "";
                    String vatAmount = "";
                    String branchincome = "";
                    String headofficeIncome = "";
                    CommissionFeeData comFee = new CommissionFeeData();
                    String totalTranAmountFee = "";
                    String suspensepostResponse = "";
                    String fee = "";
                    double tranAmountDou = Double.parseDouble(tranAmount);
                    logFile.info("Transaction Amount in Double -- " + tranAmountDou + " -- " + neft.getOrderno());
                    try {
                        fee = atcCheck.getTotalFees(tranAmountDou);
                        totCommission = atcCheck.getTotalCommissionFees(tranAmountDou);
                        comFee = atcCheck.getSeperateCommissionFees(Double.parseDouble(totCommission));
                        branchincome = comFee.getBranchCommission();
                        headofficeIncome = comFee.getHeadOfficeCommission();
                        vatAmount = comFee.getVatAmount();
                        logFile.info("Various Fees for this transaction -- " + orderNo + " -- " + fee + " -- " + totCommission + " -- " + vatAmount + " --- " + branchincome + " -- " + headofficeIncome);
                        Double calFinalTran = Double.valueOf(fee) + Double.parseDouble(tranAmount);
                        totalTranAmountFee = String.valueOf(calFinalTran);
                        logFile.info("Final tran AMount with fees for this transaction -- " + orderNo + " -- " + totCommission + " -- " + vatAmount + " -- " + tranAmount + " -- " + headofficeIncome + " -- " + branchincome);
                    } catch (Exception ex) {
                        logFile.error("Error occurred in get fee for transaction -- " + ex.toString() + " -- " + ex.getLocalizedMessage());
                        continue;
                    }
                    if (neft.getSuspensepostDate() == null) {
                        suspenseDate = new Date();
                        try {
                            RiaDtlTblJpaController em2 = new RiaDtlTblJpaController(emf);
                            em2.getEntityManager();
                            RiaDtlTbl ord = em2.findRiaDtlTbl(orderNo);
                            ord.setSuspensepostDate(suspenseDate);
                            em2.edit(ord);
                            logFile.info("Suspense creation date entered in database --" + orderNo);
                        } catch (Exception ex) {
                            logFile.error("Error occurred in set creation time -- " + ex.getMessage());
                        }
                    } else {
                        suspenseDate = neft.getSuspensepostDate();
                    }
                    try {
                        logFile.info("values passed for posting suspense account -- " + orderNo + " -- " + debitSuspense + " -- " + suspenseAccount + " -- " + bnfPIN + " -- " + totalTranAmountFee);
                        suspensepostResponse = pstRec.postNeftFirstTran(orderNo, debitSuspense, tranReason, suspenseAccount, tranAmount, suspenseDate, bnfPIN, fee, trancurr, "0" + paymentref);
                        logFile.info("Response from posting to suspense account -- " + suspensepostResponse + " -- " + orderNo);
                    } catch (Exception ep) {
                        logFile.error("Error occurred in debit suspense account to credit Nuban account -- " + ep.toString() + " -- " + orderNo);
                        continue;
                    }
                    if (suspensepostResponse.equalsIgnoreCase("913")) {
                        logFile.info("Suspense account posting returned duplicate!! Check DTD for transaction -- " + orderNo);
                        GetTranId gtranId = new GetTranId();
                        String tranId = "";
                        tranId = gtranId.getTranIdWithAmount(neft.getPin(), suspenseAccount, Double.valueOf(tranAmount));
                        logFile.info("tran Id of Transaction -- " + tranId);
                        if (tranId.trim().equals("")) {
                            logFile.info("Actual transaction does not exist!!! Reset suspense account time and try again later!!!!");
                            RiaDtlTblJpaController em1 = new RiaDtlTblJpaController(emf);
                            em1.getEntityManager();
                            RiaDtlTbl ordd = em1.findRiaDtlTbl(neft.getOrderno());
                            Calendar cal = Calendar.getInstance();
                            Timestamp sysdate = new Timestamp(loadProp.SDF.parse(cal.getTime().toString()).getTime());
                            ordd.setSuspensepostDate(sysdate);
                            em1.edit(ordd);
                            logFile.info("suspense account date reset done successfully -- " + sysdate);
                            continue;
                        }
                    } else if (suspensepostResponse.equalsIgnoreCase("000")) {
                        logFile.info("Suspense account posting was successful. Confirm tran_id exist for the transaction before going on!!!" + suspensepostResponse + " -- " + orderNo);
                        GetTranId gtranId = new GetTranId();
                        String tranId = "";
                        tranId = gtranId.getTranIdWithAmount(neft.getPin(), suspenseAccount, Double.valueOf(tranAmount));
                        logFile.info("tran Id of Transaction -- " + tranId);
                        if (tranId.trim().equals("")) {
                            logFile.info("Actual transaction does not exist!!! Reset suspense account time and try again later!!!!");
                            RiaDtlTblJpaController em1 = new RiaDtlTblJpaController(emf);
                            em1.getEntityManager();
                            RiaDtlTbl ordd = em1.findRiaDtlTbl(neft.getOrderno());
                            Calendar cal = Calendar.getInstance();
                            Timestamp sysdate = new Timestamp(loadProp.SDF.parse(cal.getTime().toString()).getTime());
                            ordd.setSuspensepostDate(sysdate);
                            em1.edit(ordd);
                            logFile.info("suspense account date reset done successfully -- " + sysdate);
                            continue;
                        }
                    } else {
                        logFile.info("Suspense account transaction posting failed. Try again later." + suspensepostResponse + " -- " + suspenseAccount + " -- " + Calendar.getInstance().getTime());
                        continue;
                    }
                    
                    
                    if (loadProp.NAPSENABLED.equalsIgnoreCase("Y")) {
                        logFile.info("Check if transaction is NAPS if Naps flag is enabled to ensure TSS account is credited");
                        isNaps = "YES";
                        String benfbksort = neft.getBankroutingcode().trim().substring(0, 3);
                        isNaps = checkifNaps(benfbksort);
                        if(isNaps.equalsIgnoreCase("YES")){
                               creditSuspense = loadProp.NAPSTSSACCOUNT;
                                logFile.info("Credit Naps account instead of regular clearing account -- " + creditSuspense + " -- " + bnfPIN);
                        }
                    }
                    try {
                        logFile.info("Values passed to post for NEFT --" + orderNo + " -- " + creditSuspense + " -- " + bnfPIN + " -- " + tranAmount + " -- " + createDate);
                        postResponse = pstRec.postNeftTran(orderNo, creditSuspense, tranReason, debitSuspense, tranAmount, createDate, bnfPIN, vatAmount, branchincome, headofficeIncome, trancurr, "0" + paymentref);
                        logFile.info("Response from posting -- " + postResponse);
                    } catch (Exception e) {
                        logFile.error("Error in post transactions -- " + e.toString());
                        continue;
                    }
                    if ((postResponse.equals("000")) || (postResponse.equals("913")) || (postResponse.equals("116"))) {
                        try {
                            RiaDtlTblJpaController em1 = new RiaDtlTblJpaController(emf);
                            em1.getEntityManager();
                            RiaDtlTbl ordd = em1.findRiaDtlTbl(neft.getOrderno());
                            Calendar cal = Calendar.getInstance();
                            Timestamp sysdate = new Timestamp(loadProp.SDF.parse(cal.getTime().toString()).getTime());
                            GetTranId gtranId = new GetTranId();
                            TrandetailsData tranData = new TrandetailsData();
                            String tranId = "";
                            Date tranDate = new Date();
                            tranData = gtranId.getTranDetails(neft.getPin(), debitSuspense, Double.valueOf(tranAmount));
                            tranId = tranData.getTranId();
                            tranDate = tranData.getTranDate();
                            logFile.info("tran Details of Transaction -- " + tranId + " -- " + tranDate);
                            if (tranId == null) {
                                logFile.info("Actual transaction does not exist!!! Reset creation time and try again later!!!!");
                                ordd.setCreTime(sysdate);
                                int k = Integer.parseInt(ordd.getRetryCount()) + 1;
                                ordd.setRetryCount(String.valueOf(k));
                                em1.edit(ordd);
                                continue;
                            }
                            ordd.setPstdDate(sysdate);
                            ordd.setPstdFlg('Y');
                            int k = Integer.parseInt(ordd.getRetryCount()) + 1;
                            ordd.setRetryCount(String.valueOf(k));
                            ordd.setResponseCode("000");
                            ordd.setResponseMessage("Transaction Successful");
                            ordd.setTranId(tranId);
                            ordd.setReverseAmount(ordd.getBeneficiaryamount());
                            ordd.setTransDate(tranDate);
                            em1.edit(ordd);
                            postResponse = "000";
                            logFile.info("RIA TBL Updated successfully with Posted date and flag when successful !!!");
                        } catch (Exception ex) {
                            System.out.println(" Posted Date, flag and tranId not successfully inserted :" + ex.toString());
                            logFile.error(" Posted Date, flag and tranId not successfully inserted :" + ex.toString());
                            continue;
                        }
                    }
                    ResponseCode getResMess = new ResponseCode();
                    String resMess = getResMess.CheckResponse(postResponse);
                    try {
                        RiaDtlTblJpaController em1 = new RiaDtlTblJpaController(emf);
                        em1.getEntityManager();
                        RiaDtlTbl ordd = em1.findRiaDtlTbl(neft.getOrderno());
                        int k = Integer.parseInt(ordd.getRetryCount()) + 1;
                        ordd.setRetryCount(String.valueOf(k));
                        ordd.setResponseCode(postResponse);
                        ordd.setResponseMessage(resMess);
                        em1.edit(ordd);
                        logFile.info("RIA TBL Updated successfully with response code and message when failed !!!");
                    } catch (Exception ex) {
                        System.out.println(" JpaController Validate:" + ex.toString());
                        logFile.error("Neft flag not updated successfully !!!");
                        continue;
                    }
                }
                if (postResponse.equals("000")) {
                    try {
                        RiaDtlTblJpaController em2 = new RiaDtlTblJpaController(emf);
                        em2.getEntityManager();
                        RiaDtlTbl ord = em2.findRiaDtlTbl(orderNo);

                        if (isNaps.equalsIgnoreCase("NO")) {
                            GiroTblJpaController girotblctrl = new GiroTblJpaController();
                            girotblctrl.persistGiro(ord);
                        } else {
                            GiroTblJpaControllerNaps girotblctrlNaps = new GiroTblJpaControllerNaps();
                            girotblctrlNaps.persistGiro(ord);
                        }
                    } catch (Exception ex) {
                        logFile.error("Error occurred in inserting record into Giro!! Try again later!!  " + ex.toString() + " -- " + ex.getLocalizedMessage());
                        continue;
                    }
                    logFile.info("Update RiaTBL so that record will not be picked again!!");
                    try {
                        RiaDtlTblJpaController em1 = new RiaDtlTblJpaController(emf);
                        em1.getEntityManager();
                        RiaDtlTbl ordd = em1.findRiaDtlTbl(neft.getOrderno());
                        Calendar cal = Calendar.getInstance();
                        Timestamp sysdate = new Timestamp(loadProp.SDF.parse(cal.getTime().toString()).getTime());
                        ordd.setUpdateTime(sysdate);
                        ordd.setNeftPicked("Y");
                        int k = Integer.parseInt(ordd.getRetryCount()) + 1;
                        ordd.setRetryCount(String.valueOf(k));
                          if (isNaps.equalsIgnoreCase("NO")) {
                              ordd.setNeftornapsPicked("NEFT");
                          }else{
                              ordd.setNeftornapsPicked("NAPS");
                          }
                        em1.edit(ordd);
                        logFile.info("RIA TBL Updated successfully with Neft picked flag now Y !!!");
                    } catch (Exception e) {
                        System.out.println(" JpaController Validate:" + e.toString());
                        logFile.error("Neft flag not updated successfully !!!");
                    }
                }
            }
        } catch (Exception e) {
            logFile.error("Error occurred in Post Neft transactions before insert into Giro Table !!!" + e.toString());
        }
    }
    
    private String checkifNaps(String beneficiarySortCode) {
        String response = "NO";
        Connection conn = null;
        String decPwd = "";
       int reccount=0;
        try {
            DecryptionCode decPwd1 = new DecryptionCode();
            decPwd = decPwd1.decryptPassword(loadProp.DATABASEPWD);
            Class.forName(loadProp.DATABASEDRIVER);
            conn = DriverManager.getConnection(loadProp.DATABASEURL, loadProp.DATABASEUSER, decPwd);
            logFile.info("Connection made to database for check if transaction is NAPS -- " + conn.toString());
        } catch (Exception ex) {
            logFile.error("Error occurred in get connection to database -- " + ex.getLocalizedMessage() + " -- " + ex.toString());
            return response = "NO";
        }
        String que1 = "SELECT count(BANK_SORT_CODE)  RECCOUNT FROM CUSTOM.NEFT_NAPS_BANK WHERE DEL_FLG = 'N' and BANK_SORT_CODE = '" + beneficiarySortCode + "' " ;
        logFile.info("Query to get bank name -- " + que1);
        ResultSet rs1 = null;
        try {
            rs1 = conn.createStatement().executeQuery(que1);
            if (rs1.next()) {
                reccount = rs1.getInt("RECCOUNT");
                logFile.info("Record Count -- " + reccount);
            }
        } catch (Exception re) {
            logFile.error("Error occurred in query to confirm if transaction is a NAPs transaction or not -- " + re.getLocalizedMessage());
        }
        if (reccount > 0) {
            response = "YES";
        }
        try {
            if (!conn.isClosed()) {
                conn.close();
            }
        } catch (Exception pr) {
            logFile.info("Error occurred in closing connection after confirming if transaction is NAPS -- " + pr.getLocalizedMessage());
        }
        return response;
    }
}


