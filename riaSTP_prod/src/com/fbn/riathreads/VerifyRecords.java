 package com.fbn.riathreads;

 import com.fbn.db.jpa.exceptions.NonexistentEntityException;
 import com.fbn.validate.GetRecordToValidate;
 import java.util.logging.Level;
 import java.util.logging.Logger;

 public class VerifyRecords
         extends Thread  {
     public void run()  {
         try {
             GetRecordToValidate y = new GetRecordToValidate();
             y.validateRec();
                    }  catch (NonexistentEntityException ex)  {
             Logger.getLogger(VerifyRecords.class.getName()).log(Level.SEVERE, null, ex);
                    }  catch (Exception ex) {
             Logger.getLogger(VerifyRecords.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
     }

