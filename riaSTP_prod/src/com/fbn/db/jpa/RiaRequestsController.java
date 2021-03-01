 package com.fbn.db.jpa;

 import com.fbn.riastp.loadProp;
 import java.util.Calendar;
 import java.util.Random;
 import javax.persistence.EntityManager;
 import javax.persistence.EntityManagerFactory;
 import javax.persistence.Persistence;
 import org.apache.log4j.Logger;

 public class RiaRequestsController  {
     Logger logFile = Logger.getLogger(RiaRequestsController.class);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("riaSTPPU");
        EntityManager em = this.emf.createEntityManager();
    
     public void persistRequest(String requestXML)  {
         this.logFile.info("Request sent to RIA -- " + requestXML);
         try {
             Random randone = new Random();
             long valueadd = randone.nextLong();
             Calendar cal = Calendar.getInstance();
             String sysDate = loadProp.SDF.format(cal.getTime()) + String.valueOf(valueadd);
             this.logFile.info("System date for insert RiaRequest -- " + sysDate);
            
             RiaRequestsTbl riaReq = new RiaRequestsTbl();
             riaReq.setRiaRequestsTime(sysDate);
             if (requestXML.length() > 3990) {
                 riaReq.setRiaRequestsXml(requestXML.substring(0, 3990));
                            } else {
                 riaReq.setRiaRequestsXml(requestXML);
                            }
             try  {
                 this.em.getTransaction().begin();
                 this.em.persist(riaReq);
                 this.em.getTransaction().commit();
                            }  catch (Exception ex) {
                 this.logFile.error("Error occurred in insert record into database for RIA REQUESTS -- " + ex.toString() + " -- " + ex.getLocalizedMessage());
                 throw ex;
                            }
                    } catch (Exception ex) {
             this.logFile.error("Error occurred in handling RIA REQUESTS -- " + ex.toString() + " -- " + ex.getLocalizedMessage());
                    }
            }
     }

