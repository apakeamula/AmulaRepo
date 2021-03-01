 package com.fbn.riathreads;

 import com.fbn.db.jpa.exceptions.NonexistentEntityException;
 import com.fbn.xml.parse.GetStatusForUpdate;
 import java.util.logging.Level;
 import java.util.logging.Logger;

 public class returnRecordUpdate
         extends Thread  {
     @Override
     public void run()  {
         try {
             GetStatusForUpdate updt = new GetStatusForUpdate();
             updt.GetStatus();
                    }  catch (NonexistentEntityException ex)  {
             Logger.getLogger(VerifyRecords.class.getName()).log(Level.SEVERE, null, ex);
                    }  catch (Exception ex) {
             Logger.getLogger(VerifyRecords.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
     }


