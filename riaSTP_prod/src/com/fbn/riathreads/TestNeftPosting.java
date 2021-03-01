 package com.fbn.riathreads;

 import com.fbn.bancsconnect.PostTransaction;
 import java.io.PrintStream;
 import java.util.Date;

 public class TestNeftPosting  {
     public static void main(String[] args) {
         PostTransaction pstTran = new PostTransaction();
        
        
        
        
         String connRes = pstTran.GetBalance("0002345", "2019559306", "0", new Date(System.currentTimeMillis()), "NGN");
         System.out.println("Response -- " + connRes);
            }
     }

