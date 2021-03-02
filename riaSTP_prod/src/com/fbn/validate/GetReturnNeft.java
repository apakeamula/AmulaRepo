 package com.fbn.validate;

 import javax.persistence.EntityManagerFactory;
 import javax.persistence.Persistence;
 import javax.persistence.PersistenceUnit;
 import javax.persistence.Query;
 import org.apache.log4j.Logger;

 public class GetReturnNeft  {
     Logger logFile = Logger.getLogger(GetReturnNeft.class);
        @PersistenceUnit(unitName = "riaSTPPU")
     EntityManagerFactory emf;
    
     public String getRec(String payRef)  {
         String valid = "NO";
         try  {
             this.logFile.info("Enter Get Return Neft -- ");
            
             String getQuery = "";
             Object retList = null;
             getQuery = "select return_reason FROM neftret  where ORIG_PRES_ITEM_SEQ like '%" + payRef + "'";
             System.out.println(getQuery);
             this.logFile.info("Query for get return neft -- " + getQuery);
            
             this.emf = Persistence.createEntityManagerFactory("riaSTPPU");
             Query query = this.emf.createEntityManager().createNativeQuery(getQuery);
             try {
                 retList = query.getSingleResult();
                            }  catch (Exception ey)  {
                 System.out.println("Error occurred in check Return Neft Table -- " + ey.toString());
                 this.logFile.error("Error occurred in check Return Neft Table -- " + ey.toString() + " -- " + ey.getLocalizedMessage());
                 return valid;
                            }
             String genDate = "";
             genDate = retList.toString();
            
             System.out.println("GenDate=" + genDate);
             this.logFile.info("Reason for return -- " + genDate + " -- " + payRef);
             if (Integer.valueOf(genDate).intValue() > 0)  {
                 String reasonQuery = "select ret_desc FROM custom.neft_ret_reason  where ret_code = '" + Integer.valueOf(genDate) + "'";
                 this.logFile.info("Query for get reason for neft return -- " + reasonQuery + " -- " + payRef);
                
                 Query query1 = this.emf.createEntityManager().createNativeQuery(reasonQuery);
                 Object reasonList = null;
                 try {
                     reasonList = query1.getSingleResult();
                                    } catch (Exception w)  {
                     System.out.println("Error occurred in check Reason for Return Table -- " + w.toString());
                     this.logFile.error("Error occurred in check Return Neft Table -- " + w.toString() + " -- " + w.getLocalizedMessage());
                     return "RETURNED";
                                    }
                 String retReason = reasonList.toString();
                 valid = retReason;
                            }
             this.logFile.info("valid or not valid -- " + valid);
                    } catch (Exception e)  {
             System.out.println(e.toString());
             this.logFile.error("Error occurred in Get Return Neft -- " + e.toString() + " -- " + e.getLocalizedMessage());
                    }
         return valid;
            }
     }

