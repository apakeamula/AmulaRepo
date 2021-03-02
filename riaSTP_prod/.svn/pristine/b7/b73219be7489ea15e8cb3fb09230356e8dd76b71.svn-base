 package com.fbn.riathreads;

 import com.fbn.bancsconnect.PostTransaction;
 import com.fbn.db.jpa.RiaDtlTbl;
 import com.fbn.db.jpa.RiaDtlTblJpaController;
 import com.fbn.riastp.loadProp;
 import java.sql.Timestamp;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.List;
 import javax.persistence.EntityManagerFactory;
 import javax.persistence.Persistence;
 import javax.persistence.PersistenceUnit;
 import javax.persistence.Query;
 import org.apache.log4j.Logger;

 public class ReverseRejectedNeft {
     Logger logFile = Logger.getLogger(ReverseRejectedNeft.class);
        @PersistenceUnit(unitName = "riaSTPPU")
     EntityManagerFactory emf;
    
     public void reversePostTransactions()  {
        String resp;
         try  {
             logFile.info("Get record for posting --");
             resp = null;
             String response = null;
             this.emf = Persistence.createEntityManagerFactory("riaSTPPU");
             Query notPosted = this.emf.createEntityManager().createNamedQuery("RiaDtlTbl.findByNeftReturnReverse").setParameter("pstdFlg", Character.valueOf('Y')).setParameter("returnedFlg", Character.valueOf('Y')).setParameter("reverseResFlg", Character.valueOf('N')).setParameter("fbnFlg", Character.valueOf('N'));
            
            
            
            
            
             List<RiaDtlTbl> getRec = notPosted.getResultList();
             logFile.info("Number of records gotten for reversing  -- " + getRec.size() + " -- " + new Timestamp(System.currentTimeMillis()));
             if (getRec.isEmpty()) {
                 System.out.println("No Record Found for Posting");
                 logFile.info("No record to reverse  -- " + new Timestamp(System.currentTimeMillis()));
                 return;
                            }
             for (RiaDtlTbl orderRec : getRec) {
                 String orderNo = orderRec.getOrderno();
                 logFile.info("Order No -- " + orderNo);
                 orderNo = orderNo + "1";
                 logFile.info("Order number reversal =  " + orderNo);
                 String tranCurr = orderRec.getBeneficiarycurrency();
                 logFile.info("transaction Currency -- " + tranCurr);
                 String tranAmt = orderRec.getReverseAmount();
                 logFile.info("Reverse transaction Amount -- " + tranAmt);
                 String bnfPIN = orderRec.getPin();
                String tranReason = "REV-NEFT/" + orderNo.substring(0, orderNo.length() - 1) + "/" + bnfPIN;
                 logFile.info("transaction Reason -- " + tranReason);
                 String payRef = orderRec.getPayRef();
                 logFile.info("Payment Reference -- " + payRef);
                 String creditSuspense = loadProp.NEFTSUSPENSELIST[java.util.Arrays.asList(loadProp.NEFTCURRENCYLIST).indexOf(orderRec.getBeneficiarycurrency())];
                 String debitSuspense = loadProp.NEFTDEBITACCOUNT;
                 logFile.info("Suspense Account to Credit for reversal -Initial Neft debit -- " + debitSuspense);
                 Date reverseDate = null;
                 if (orderRec.getReverseDate() == null)  {
                     reverseDate = new Date();
                     try  {
                         RiaDtlTblJpaController em = new RiaDtlTblJpaController(this.emf);
                         em.getEntityManager();
                         RiaDtlTbl ord = em.findRiaDtlTbl(orderNo);
                         ord.setReverseDate(reverseDate);
                         em.edit(ord);
                         logFile.info("Reverse date entered in database --" + orderNo);
                                            } catch (Exception ex) {
                         logFile.error("Error occurred in set Reversed time -- " + ex.getMessage());
                                            }
                                    } else  {
                     reverseDate = orderRec.getReverseDate();
                                    }
                 System.out.println("Credit Account:" + debitSuspense);
                 logFile.info("Final Credit account -- " + debitSuspense + " -- " + orderRec.getFbnFlg().toString());
                 System.out.println("Suspense Account to debit for reversal =  " + creditSuspense);
                 logFile.info("Suspense Account to debit for reversal =  " + creditSuspense);
                 try {
                     PostTransaction post = new PostTransaction();
                     resp = post.postTran(orderNo, debitSuspense, tranReason, creditSuspense, tranAmt, reverseDate, bnfPIN, tranCurr);
                     logFile.info("Response from Connect24 -- " + resp);
                     System.out.println(resp);
                                    }  catch (Exception e) {
                     System.out.println("PostTransaction Exception: " + e.toString());
                     logFile.error("Error occurred in Finacle Posting -- " + e.getMessage());
                                    }
                 Calendar cal = Calendar.getInstance();
                 Timestamp sysdate = new Timestamp(loadProp.SDF.parse(cal.getTime().toString()).getTime());
                 try  {
                     RiaDtlTblJpaController em = new RiaDtlTblJpaController(this.emf);
                     em.getEntityManager();
                     RiaDtlTbl ord = em.findRiaDtlTbl(orderNo);
                     if ((resp.equals("000")) || (resp.equals("913")))  {
                         ord.setReverseResCode(resp);
                         ord.setReverseResFlg(Character.valueOf('Y'));
                         ord.setFinalReverseDate(sysdate);
                                            }  else {
                         ord.setReverseResCode(resp);
                         ord.setFinalReverseDate(sysdate);
                                            }
                     em.edit(ord);
                                    }  catch (Exception e) {
                     System.out.println("RiaDtlTblJpaController Exception for reversal : " + e.toString());
                     logFile.error("RiaDtlTblJpaController Exception for reversal : " + e.toString());
                                    }
                            }
                    }  catch (Exception ex)  {
            
             logFile.error("Error occurred in Reverse rejected Neft Transaction -- " + ex.toString() + " -- " + ex.getLocalizedMessage());
                    }
            }
     }

