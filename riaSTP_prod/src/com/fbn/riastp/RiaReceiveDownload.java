 package com.fbn.riastp;

 import com.fbn.db.jpa.exceptions.NonexistentEntityException;
 import com.fbn.xml.parse.GetXmlOrderData;
 import java.sql.Timestamp;
 import org.apache.log4j.Logger;

 public class RiaReceiveDownload  {
     private static Logger logFile = Logger.getLogger(RiaReceiveDownload.class);
    
     public static void main(String[] args)
             throws NonexistentEntityException, Exception  {
         int succ = 0;
         try  {
             GetXmlOrderData x = new GetXmlOrderData();
             succ = x.GetXmlData();
             if (succ == 1) {
                 logFile.info("Record was downloaded successfully -- " + new Timestamp(System.currentTimeMillis()));
                            } else {
                 logFile.info("No record downloaded at this time -- " + new Timestamp(System.currentTimeMillis()));
                            }
                    }  catch (Exception e)  {
             System.out.println("Error Occurred while Getting XML Data " + e.toString() + " " + e.getMessage());
             logFile.error("Error Occurred while Getting XML Data " + e.toString() + " " + e.getMessage());
                    }
            }
     }


