 package com.fbn.db.jpa;

 import com.fbn.riastp.loadProp;
 import java.util.List;
 import javax.persistence.EntityManagerFactory;
 import javax.persistence.Persistence;
 import javax.persistence.PersistenceUnit;
 import javax.persistence.Query;
 import org.apache.log4j.Logger;

 public class GetPayRef {
     private static final Logger logFile = Logger.getLogger(GetPayRef.class);
        @PersistenceUnit(unitName = "riaSTPPU")
     EntityManagerFactory emf;
    
     public String getPayRef()  {
         String payRef = "";
        
         String getQuery = "SELECT CUSTOM.NEFT_GetRefNum('" + loadProp.MONEYTRANSFERSOL + "') FROM DUAL";
         System.out.println(getQuery);
         logFile.info("Get Query fpr getPayRef -- " + getQuery);
         this.emf = Persistence.createEntityManagerFactory("riaSTPPU");
         Query query = this.emf.createEntityManager().createNativeQuery(getQuery);
         List<String> refList = query.getResultList();
         if (!refList.isEmpty())  {
             payRef = (String) refList.get(0);
             logFile.info("Payment Reference -- " + payRef);
             System.out.println("Tran ID is " + payRef);
                    }
         return payRef;
            }
     }

