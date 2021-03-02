 package com.fbn.riathreads;

 import com.fbn.validate.GetReturnNeft;

 public class TestNeftValidate  {
     public static void main(String[] args)  {
         String payref = "244858433";
         long seqn = 1L;
         String serialNumStr = String.valueOf(seqn);
         System.out.println("Value of sequence now -- " + serialNumStr);
         while (serialNumStr.length() < 4) {
             serialNumStr = "0" + serialNumStr;
                    }
         System.out.println("Final Value of sequence now -- " + serialNumStr);
         String finalbatchNumPassed = payref + serialNumStr;
         System.out.println("Final Original Seq Number -- " + finalbatchNumPassed);
        
         GetReturnNeft gRN = new GetReturnNeft();
         String payRefStatus = gRN.getRec(finalbatchNumPassed);
         System.out.println("payRefStatus=" + payRefStatus);
            }
     }

