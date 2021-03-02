 package com.fbn.db.jpa;

 import com.fbn.riastp.loadProp;
 import java.util.Calendar;
 import java.util.Random;
 import javax.persistence.EntityManager;
 import javax.persistence.EntityManagerFactory;
 import javax.persistence.Persistence;
 import org.apache.log4j.Logger;

 public class RiaResponseController  {
     Logger logFile = Logger.getLogger(RiaResponseController.class);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("riaSTPPU");
        EntityManager em = this.emf.createEntityManager();
    
     public void persistRiaResponse(String responseXML) {
         this.logFile.info("Response sent to RIA -- " + responseXML);
         try {
             Random randone = new Random();
             long valueadd = randone.nextLong();
             Calendar cal = Calendar.getInstance();
             String sysDate = loadProp.SDF.format(cal.getTime()) + "--" + String.valueOf(valueadd);
             this.logFile.info("System date for insert RiaResponse -- " + sysDate);
            
             RiaResponseTbl riaRes = new RiaResponseTbl();
             riaRes.setRiaResponseTime(sysDate);
             if (responseXML.length() > 3990) {
                 riaRes.setRiaResponseXml(responseXML.substring(0, 3990));
                            } else {
                 riaRes.setRiaResponseXml(responseXML);
                            }
             try  {
                 this.em.getTransaction().begin();
                 this.em.persist(riaRes);
                 this.em.getTransaction().commit();
                            }catch (Exception e)  {
                 this.logFile.error("Error occurred in insert ria respons einto database -- " + e.toString() + " -- " + e.getLocalizedMessage());
                 throw e;
                            }
                    }  catch (Exception ex)  {
             this.logFile.error("Error occurred in processing RIA Response into database -- " + ex.toString() + " -- " + ex.getLocalizedMessage());
                    }
            }
     }
