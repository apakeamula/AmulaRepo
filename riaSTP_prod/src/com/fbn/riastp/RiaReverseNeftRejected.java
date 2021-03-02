 package com.fbn.riastp;

 import com.fbn.riathreads.ReverseRejectedNeft;
 import org.apache.log4j.Logger;

 public class RiaReverseNeftRejected  {
     static Logger logFile = Logger.getLogger(RiaReverseNeftRejected.class);
    
     public static void main(String[] args) {
         logFile.info("Reverse rejected NEFT transaction to suspense account");
         try  {
             ReverseRejectedNeft revNeft = new ReverseRejectedNeft();
             revNeft.reversePostTransactions();
             logFile.info("Reverse transactions processed successfully!!!");
                    }  catch (Exception ex){
             logFile.error("Error occurred in RiaReverseNeftRejected -- " + ex.toString() + " -- " + ex.getLocalizedMessage());
                    }
            }
     }


