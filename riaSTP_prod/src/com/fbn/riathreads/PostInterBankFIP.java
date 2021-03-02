package com.fbn.riathreads;

import com.fbn.bancsconnect.GetTranId;
import com.fbn.bancsconnect.PostTransaction;
import com.fbn.db.jpa.RiaDtlTbl;
import com.fbn.db.jpa.RiaDtlTblJpaController;
import com.fbn.riastp.loadProp;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;
import org.apache.log4j.Logger;
import org.tempuri.ArrayOfPaymentResponse;
import org.tempuri.PaymentRequest;
import org.tempuri.PaymentResponse;
import org.tempuri.XHeader;

public class PostInterBankFIP {

    private static final Logger logFile = Logger.getLogger(PostInsertNeftGiro.class);

    public void PostFIPTransactions() {
        String orderNo = "";
        String bnfPIN = "";
        String debitSuspense = "";
        String creditSuspense = "";
        String tranAmount = "";
        String fipPicked = "";
        String postResponse = "";
        String trancurr = "";
        String suspenseAccount = "";
        String otherBankCustomerAccount = "";
        Date suspenseDate = null;
        String paymentref = "";
        String bankCode = "";
        String postbridgeserv = loadProp.POSTBRIDGEKEY;
        String postbridgeKey = "";
                try {
                    com.fbn.ria.util.DecryptionCode decCode = new com.fbn.ria.util.DecryptionCode();
                    postbridgeKey = decCode.decryptPassword(postbridgeserv);
                } catch (Exception pe) {
                    logFile.info("Error occurred in get Postbridge Key ==> Try again later ==> " + orderNo + " ==> " + pe.toString());
                }        

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("riaSTPPU");
        EntityManager em = emf.createEntityManager();
        PaymentRequest pr = new PaymentRequest();
        XHeader xHead = new XHeader();
        xHead.setPasskey(postbridgeKey);
        xHead.setServicename(loadProp.POSTBRIDGEAPPNAME);
        Holder holval = new Holder(xHead);
        org.tempuri.PostBridge pstBrd = new org.tempuri.PostBridge();
        org.tempuri.PostBridgeSoap psPo = pstBrd.getPostBridgeSoap();
        BigDecimal feeamount;
        try {
            logFile.info("Get all pending Neft transactions");
            Query fipTrans = em.createNamedQuery("RiaDtlTbl.findByFIPReceived").setParameter("fbnFlg", Character.valueOf('N')).setParameter("failFlg", Character.valueOf('N')).setParameter("processed", Character.valueOf('Y'));
            List<RiaDtlTbl> FIPTransactions = fipTrans.getResultList();
            logFile.info("How many records in the list FIPTransactions class -- " + FIPTransactions.size());
            int count = 0;
            for (RiaDtlTbl fip : FIPTransactions) {
                count += 1;
                logFile.info("Process the transaction first -- " + fip.getOrderno());
                creditSuspense = loadProp.NEFTSUSPENSELIST[java.util.Arrays.asList(loadProp.NEFTCURRENCYLIST).indexOf(fip.getBeneficiarycurrency())];
                debitSuspense = loadProp.NEFTDEBITACCOUNT;
                suspenseAccount = loadProp.NEFTSUSPENSEACCOUNT;
                logFile.info("Neft credit and debit suspense accounts -- " + creditSuspense + " -- debit -- " + debitSuspense);
                tranAmount = fip.getBeneficiaryamount();
                otherBankCustomerAccount = fip.getBankaccountno();
                fipPicked = fip.getFippicked();
                orderNo = fip.getOrderno();
                bnfPIN = fip.getPin();
                char postedflag = fip.getPstdFlg().charValue();
                logFile.info("Tran Pin -- " + bnfPIN);
                trancurr = fip.getBeneficiarycurrency();
                String senderName = "";
                bankCode = fip.getBankroutingcode();
                bankCode = bankCode.substring(0, 3);
                if (fip.getCustfirstname() != null) {
                    senderName = senderName + fip.getCustfirstname();
                }
                if (fip.getCustlastname() != null) {
                    senderName = senderName + " " + fip.getCustlastname();
                }
                logFile.info("Senders Name = " + senderName);
                String tranReason = "RIA/" + bnfPIN + "/" + senderName;
                paymentref = fip.getPayRef();

                PostTransaction pstRec = new PostTransaction();
                String totalTranAmountFee = "";
                String suspensepostResponse = "";
                String fee = "";
                double tranAmountDou = Double.parseDouble(tranAmount);
                BigDecimal tranAmtDec = BigDecimal.valueOf(tranAmountDou);
                try {
                    feeamount = psPo.getInterBankCharge(tranAmtDec);
                    logFile.info("FIP Fee ==> " + feeamount);
                } catch (Exception we) {
                    logFile.error("Error occurred in get fee in FIP == " + we.toString() + " == " + orderNo);
                    continue;
                }

                fee = feeamount.toString();
                logFile.info("Final fee Amount for this transaction -- " + orderNo + " -- " + tranAmount + " -- " + fee);

                if (fip.getSuspensepostDate() == null) {
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
                    suspenseDate = fip.getSuspensepostDate();
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
                    tranId = gtranId.getTranIdWithAmount(fip.getPin(), suspenseAccount, Double.valueOf(tranAmount));
                    logFile.info("tran Id of Transaction -- " + tranId);
                    if (tranId.trim().equals("")) {
                        logFile.info("Actual transaction does not exist!!! Reset suspense account time and try again later!!!!");
                        RiaDtlTblJpaController em1 = new RiaDtlTblJpaController(emf);
                        em1.getEntityManager();
                        RiaDtlTbl ordd = em1.findRiaDtlTbl(fip.getOrderno());
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
                    tranId = gtranId.getTranIdWithAmount(fip.getPin(), suspenseAccount, Double.valueOf(tranAmount));
                    logFile.info("tran Id of Transaction -- " + tranId);
                    if (tranId.trim().equals("")) {
                        logFile.info("Actual transaction does not exist!!! Reset suspense account time and try again later!!!!");
                        RiaDtlTblJpaController em1 = new RiaDtlTblJpaController(emf);
                        em1.getEntityManager();
                        RiaDtlTbl ordd = em1.findRiaDtlTbl(fip.getOrderno());
                        Calendar cal = Calendar.getInstance();
                        Timestamp sysdate = new Timestamp(loadProp.SDF.parse(cal.getTime().toString()).getTime());
                        ordd.setSuspensepostDate(sysdate);
                        em1.edit(ordd);
                        logFile.info("suspense account date reset done successfully -- " + sysdate);
                        continue;
                    }
                } else {
                    logFile.info("Suspense account transaction posting failed. Try again later." + suspensepostResponse + " -- " + suspenseAccount + " -- " + Calendar.getInstance().getTime() + " == " + orderNo);
                    continue;
                }
                XMLGregorianCalendar xmlDate = null;
                GregorianCalendar gc = new GregorianCalendar();
                gc.setTime(new Date());

                try {
                    xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println("XMLGregorianCalendar :- " + xmlDate);
//                try {
//                    com.fbn.ria.util.DecryptionCode decCode = new com.fbn.ria.util.DecryptionCode();
//                    postbridgeKey = decCode.decryptPassword(postbridgeserv);
//                } catch (Exception pe) {
//                    logFile.info("Error occurred in get Postbridge Key ==> Try again later ==> " + orderNo + " ==> " + pe.toString());
//                }
               System.out.println("Input to postbridge before" + tranAmount + " " + debitSuspense + " " + tranReason + " " + otherBankCustomerAccount + " " + bankCode + " " + "RIA" + orderNo + " " + xmlDate + " " + paymentref + " " + postbridgeKey + " " + loadProp.POSTBRIDGEAPPNAME);
                pr.setAmount(tranAmount);
                pr.setFromAcc(debitSuspense);
                pr.setNarration(tranReason);
                pr.setToAcc(otherBankCustomerAccount);
                pr.setToBankCode(bankCode);
                pr.setTraceID("RIA" + orderNo);
                pr.setRequestDate(xmlDate);
                pr.setTranStatus(1);
                pr.setServiceID(1);
                pr.setIsReversed(false);
                pr.setToAccName(senderName);
                pr.setTranRefNo(paymentref);

                System.out.println("Input to postbridge after " + tranAmount + " " + debitSuspense + " " + tranReason + " " + otherBankCustomerAccount + " " + bankCode + " " + "RIA" + orderNo + " " + xmlDate + " " + paymentref + " " + postbridgeKey + " " + loadProp.POSTBRIDGEAPPNAME);
                if (fipPicked.equalsIgnoreCase("N")) {
                    PaymentResponse response = new PaymentResponse();

                    try {
                        response = psPo.postTransRequest(pr, holval);
                        postResponse = response.getResponseCode();
                        logFile.info("Response from postbridge == > " + postResponse + " == " + response.getResponseMessage() + " == " + response.getTraceID() + " == " + response.getTranRefNo());
                    } catch (Exception pe) {
                        logFile.error("Error occurred in post transaction == " + pe.toString());
                        try {
                            RiaDtlTblJpaController em1 = new RiaDtlTblJpaController(emf);
                            em1.getEntityManager();
                            RiaDtlTbl ordd = em1.findRiaDtlTbl(fip.getOrderno());
                            int k = Integer.parseInt(ordd.getRetryCount()) + 1;
                            ordd.setRetryCount(String.valueOf(k));
                            ordd.setFippicked("P");
                            em1.edit(ordd);
                            logFile.info("RIA TBL Updated successfully with response code when message failed !!!");
                            continue;
                        } catch (Exception ex) {
                            System.out.println(" JpaController Validate:" + ex.toString());
                            logFile.error("Neft flag not updated successfully !!!");
                            continue;
                        }
                    }

                    if (postResponse.equals("000")) {
                        try {
                            RiaDtlTblJpaController em1 = new RiaDtlTblJpaController(emf);
                            em1.getEntityManager();
                            RiaDtlTbl ordd = em1.findRiaDtlTbl(fip.getOrderno());
                            Calendar cal = Calendar.getInstance();
                            Timestamp sysdate = new Timestamp(loadProp.SDF.parse(cal.getTime().toString()).getTime());
                            ordd.setPstdDate(sysdate);
                            ordd.setPstdFlg('Y');
                            int k = Integer.parseInt(ordd.getRetryCount()) + 1;
                            ordd.setRetryCount(String.valueOf(k));
                            ordd.setResponseCode("000");
                            ordd.setResponseMessage("Transaction Successful");
                            ordd.setTransDate(sysdate);
                            ordd.setFipSessionID(response.getTranRefNo());
                            ordd.setFippicked("Y");
                            ordd.setReturnedFlg('N');
                            em1.edit(ordd);
                            logFile.info("RIA TBL Updated successfully with Posted date and flag when successful !!!");

                        } catch (Exception ex) {
                            System.out.println(" Posted Date, flag and tranId not successfully inserted :" + ex.toString());
                            logFile.error(" Posted Date, flag and tranId not successfully inserted :" + ex.toString());

                        }
                    } else {
                        String resMess = response.getResponseMessage();
                        try {
                            RiaDtlTblJpaController em1 = new RiaDtlTblJpaController(emf);
                            em1.getEntityManager();
                            RiaDtlTbl ordd = em1.findRiaDtlTbl(fip.getOrderno());
                            int k = Integer.parseInt(ordd.getRetryCount()) + 1;
                            ordd.setRetryCount(String.valueOf(k));
                            ordd.setResponseCode(postResponse);
                            ordd.setResponseMessage(resMess);
                            ordd.setFailFlg('Y');
                            ordd.setFailReason(resMess);
                            ordd.setFippicked("Y");
                            ordd.setReturnedFlg('Y');
                            em1.edit(ordd);
                            logFile.info("RIA TBL Updated successfully with response code and message when failed !!!");
                        } catch (Exception ex) {
                            System.out.println(" JpaController Validate:" + ex.toString());
                            logFile.error("Neft flag not updated successfully !!!");

                        }
                    }

                } else {
                    //Transaction has been picked before for processing!!! Check for Transaction Status
                    //Check if exist
                    ArrayOfPaymentResponse arrRes = new ArrayOfPaymentResponse();

                    try {

                        arrRes = psPo.queryTransaction("RIA" + orderNo, holval);
                    } catch (Exception ew) {
                        System.out.println("Error occurred in query transaction == " + ew.toString());
                        logFile.error("Error occurred in query transaction == " + ew.toString() + " == " + orderNo);
                        continue;
                    }

                    List<PaymentResponse> payRes = arrRes.getPaymentResponse();
                    if (payRes.isEmpty()) {
                        try {
                            RiaDtlTblJpaController em1 = new RiaDtlTblJpaController(emf);
                            em1.getEntityManager();
                            RiaDtlTbl ordd = em1.findRiaDtlTbl(fip.getOrderno());
                            int k = Integer.parseInt(ordd.getRetryCount()) + 1;
                            ordd.setRetryCount(String.valueOf(k));
                            ordd.setResponseMessage("Transaction not Processed Successfully");
                            ordd.setFailFlg('Y');
                            ordd.setFailReason("Transaction not Processed Successfully");
                            ordd.setReturnedFlg('Y');
                            em1.edit(ordd);
                            logFile.info("RIA TBL Updated successfully with response code when message failed !!!");
                        } catch (Exception ex) {
                            System.out.println("Fail flag not updated successfully for FIP Query transaction is empty:" + ex.toString());
                            logFile.error("Fail flag not updated successfully for FIP Query transaction is empty !!!");
                        }
                    } else {
                        PaymentResponse paymetRes = payRes.get(0);

                        String resp = paymetRes.getResponseCode();

                        if (resp.equalsIgnoreCase("000")) {
                            if (paymetRes.getPaymentRequest().getTranStatus() == 1) {
                                try {
                                    RiaDtlTblJpaController em1 = new RiaDtlTblJpaController(emf);
                                    em1.getEntityManager();
                                    RiaDtlTbl ordd = em1.findRiaDtlTbl(fip.getOrderno());
                                    Calendar cal = Calendar.getInstance();
                                    Timestamp sysdate = new Timestamp(loadProp.SDF.parse(cal.getTime().toString()).getTime());
                                    ordd.setPstdDate(sysdate);
                                    ordd.setPstdFlg('Y');
                                    int k = Integer.parseInt(ordd.getRetryCount()) + 1;
                                    ordd.setRetryCount(String.valueOf(k));
                                    ordd.setResponseCode("000");
                                    ordd.setResponseMessage("Transaction Successful");
                                    ordd.setTransDate(sysdate);
                                    ordd.setFipSessionID(paymetRes.getTranRefNo());
                                    ordd.setFippicked("Y");
                                    ordd.setReturnedFlg('N');
                                    em1.edit(ordd);
                                    logFile.info("RIA TBL Updated successfully with Posted date and flag when successful !!!");

                                } catch (Exception ex) {
                                    System.out.println(" Posted Date, flag and tranId not successfully inserted :" + ex.toString());
                                    logFile.error(" Posted Date, flag and tranId not successfully inserted :" + ex.toString());

                                }
                            } else {
                                try {
                                    RiaDtlTblJpaController em1 = new RiaDtlTblJpaController(emf);
                                    em1.getEntityManager();
                                    RiaDtlTbl ordd = em1.findRiaDtlTbl(fip.getOrderno());
                                    int k = Integer.parseInt(ordd.getRetryCount()) + 1;
                                    ordd.setRetryCount(String.valueOf(k));
                                    ordd.setResponseMessage("Transaction not Processed Successfully");
                                    ordd.setFailFlg('Y');
                                    ordd.setFailReason("Transaction not Processed Successfully");
                                    ordd.setFippicked("Y");
                                    ordd.setReturnedFlg('Y');
                                    em1.edit(ordd);
                                    logFile.info("RIA TBL Updated successfully with response code when message  failed !!!");

                                } catch (Exception ex) {
                                    System.out.println("Fail flag not updated successfully for FIP Query transaction is empty:" + ex.toString());
                                    logFile.error("Fail flag not updated successfully for FIP Query transaction is empty !!!");

                                }
                            }
                        } else {
                            try {
                                RiaDtlTblJpaController em1 = new RiaDtlTblJpaController(emf);
                                em1.getEntityManager();
                                RiaDtlTbl ordd = em1.findRiaDtlTbl(fip.getOrderno());
                                int k = Integer.parseInt(ordd.getRetryCount()) + 1;
                                ordd.setRetryCount(String.valueOf(k));
                                ordd.setResponseMessage("Transaction not Processed Successfully");
                                ordd.setFailFlg('Y');
                                ordd.setFailReason("Transaction not Processed Successfully");
                                ordd.setFippicked("Y");
                                em1.edit(ordd);
                                ordd.setReturnedFlg('Y');
                                logFile.info("RIA TBL Updated successfully with response code when message failed !!!");
                            } catch (Exception ex) {
                                System.out.println("Fail flag not updated successfully for FIP Query transaction is empty:" + ex.toString());
                                logFile.error("Fail flag not updated successfully for FIP Query transaction is empty !!!");
                            }
                        }

                    }

                }

            }
        } catch (Exception e) {
            logFile.error("Error occurred in Post Neft transactions before insert into Giro Table !!!" + e.toString());
        }
    }

}
