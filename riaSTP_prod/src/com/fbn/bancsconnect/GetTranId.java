 package com.fbn.bancsconnect;
  import com.fbn.db.jpa.TrandetailsData;
 import com.fbn.ria.util.DecryptionCode;
 import com.fbn.riastp.loadProp;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.ResultSet;
 import java.util.Date;
 import java.util.List;
 import javax.persistence.EntityManagerFactory;
 import javax.persistence.Persistence;
 import javax.persistence.PersistenceUnit;
 import javax.persistence.Query;
 import org.apache.log4j.Logger;
  public class GetTranId 
 {
     private static final Logger logFile = Logger.getLogger(GetTranId.class);
        @PersistenceUnit(unitName = "riaSTPPU")
     EntityManagerFactory emf;
          public String getTranId(String orderNo, String foracid) 
     {
         String tranId = "";
         logFile.info("Get Tran ID Class -- " + orderNo + " -- " + foracid);
         String getQuery = "SELECT TRAN_ID FROM TBAADM.DTD r WHERE r.ACID = (SELECT ACID FROM TBAADM.GAM t WHERE t.FORACID ='" + foracid + "' ) AND r.REF_NUM='" + orderNo + "' AND r.PART_TRAN_TYPE='D'" + " UNION SELECT TRAN_ID FROM TBAADM.HTD r WHERE r.ACID = (SELECT ACID FROM TBAADM.GAM t WHERE t.FORACID ='" + foracid + "' ) AND r.REF_NUM='" + orderNo + "' AND r.PART_TRAN_TYPE='D'";
                  System.out.println(getQuery);
         logFile.info(getQuery);
         this.emf = Persistence.createEntityManagerFactory("riaSTPPU");
         Query query = this.emf.createEntityManager().createNativeQuery(getQuery);
         List<String> tranList = query.getResultList();
         if (!tranList.isEmpty())
         {
             tranId = (String) tranList.get(0);
             System.out.println("Tran ID is " + tranId);
             logFile.info("TranId of transaction --" + tranId);
                    }
         return tranId;
            }
          public String getTranIdWithAmount(String pin, String foracid, Double tranAmount)
     {
         String tranId = "";
         logFile.info("Get Tran ID Class -- " + pin + " -- " + foracid + " -- TranAmount -- " + tranAmount);
         String getQuery = "SELECT TRAN_ID FROM TBAADM.DTD r WHERE r.ACID = (SELECT ACID FROM TBAADM.GAM t WHERE t.FORACID ='" + foracid + "' ) AND r.TRAN_RMKS='" + pin + "' AND r.PART_TRAN_TYPE='D' AND r.TRAN_AMT='" + tranAmount + "'" + " UNION SELECT TRAN_ID FROM TBAADM.HTD r WHERE r.ACID = (SELECT ACID FROM TBAADM.GAM t WHERE t.FORACID ='" + foracid + "' ) AND r.TRAN_RMKS='" + pin + "' AND r.PART_TRAN_TYPE='D' AND r.TRAN_AMT='" + tranAmount + "'";
                  System.out.println(getQuery);
         logFile.info(getQuery);
         this.emf = Persistence.createEntityManagerFactory("riaSTPPU");
         Query query = this.emf.createEntityManager().createNativeQuery(getQuery);
         List<String> tranList = query.getResultList();
         if (!tranList.isEmpty())
         {
             tranId = (String) tranList.get(0);
             System.out.println("Tran ID is " + tranId);
             logFile.info("TranId of transaction --" + tranId);
                    }
         return tranId;
            }
          public String getTranIdCredit(String pin, String foracid)
     {
         String tranId = "";
         logFile.info("Get Tran ID Class -- " + pin + " -- " + foracid);
         String getQuery = "SELECT TRAN_ID FROM TBAADM.DTD r WHERE r.ACID = (SELECT ACID FROM TBAADM.GAM t WHERE t.FORACID ='" + foracid + "' ) AND r.TRAN_RMKS='" + pin + "' AND r.PART_TRAN_TYPE='C'" + " UNION SELECT TRAN_ID FROM TBAADM.HTD r WHERE r.ACID = (SELECT ACID FROM TBAADM.GAM t WHERE t.FORACID ='" + foracid + "' ) AND r.TRAN_RMKS='" + pin + "' AND r.PART_TRAN_TYPE='C'";
                  System.out.println(getQuery);
         logFile.info(getQuery);
         this.emf = Persistence.createEntityManagerFactory("riaSTPPU");
         Query query = this.emf.createEntityManager().createNativeQuery(getQuery);
         List<String> tranList = query.getResultList();
         if (!tranList.isEmpty()) 
         {
             tranId = (String) tranList.get(0);
             System.out.println("Tran ID is " + tranId);
             logFile.info("TranId of transaction --" + tranId);
                    }
         return tranId;
            }
          public TrandetailsData getTranDetails(String pin, String foracid, Double tranAmount)
             throws Exception 
     {
         TrandetailsData tranDet = new TrandetailsData();
         logFile.info("Get Tran ID Class -- " + pin + " -- " + foracid);
         Connection conn = null;
         String decPwd = "";
         DecryptionCode decPwd1 = new DecryptionCode();
         decPwd = decPwd1.decryptPassword(loadProp.DATABASEPWD);
         try 
         {
             Class.forName(loadProp.DATABASEDRIVER);
             conn = DriverManager.getConnection(loadProp.DATABASEURL, loadProp.DATABASEUSER, decPwd);
             logFile.info("Connection made to database -- " + conn.toString());
                    }
         catch (Exception ex) 
         {
             logFile.error("Error occurred in get connection to database -- " + ex.getLocalizedMessage() + " -- " + ex.toString());
             throw new Exception(ex.toString());
                    }
         String getQuery = "SELECT TRAN_ID,tran_date FROM TBAADM.DTD r WHERE r.ACID = (SELECT ACID FROM TBAADM.GAM t WHERE t.FORACID ='" + foracid + "' ) AND r.TRAN_RMKS='" + pin + "' AND r.PART_TRAN_TYPE='D' AND r.TRAN_AMT='" + tranAmount + "'" + " UNION SELECT TRAN_ID, tran_Date FROM TBAADM.HTD r WHERE r.ACID = (SELECT ACID FROM TBAADM.GAM t WHERE t.FORACID ='" + foracid + "' ) AND r.TRAN_RMKS='" + pin + "' AND r.PART_TRAN_TYPE='D' AND r.TRAN_AMT='" + tranAmount + "'";
                  System.out.println(getQuery);
         logFile.info(getQuery);
                  ResultSet rs1 = null;
                  rs1 = conn.createStatement().executeQuery(getQuery);
         if (rs1.next()) 
         {
             String s = rs1.getString("TRAN_ID");
             Date t = rs1.getDate("tran_date");
             logFile.info("Tran Details  of transaction --" + s + " -- " + t);
             tranDet.setTranDate(t);
             tranDet.setTranId(s);
                    }
         if (!conn.isClosed()) {
             conn.close();
                    }
         return tranDet;
            }
          public static void main(String[] args) 
     {
         System.out.println("Check if data exists already -- ");
         GetTranId gTran = new GetTranId();
                           TrandetailsData trDat = new TrandetailsData();
         try 
         {
             trDat = gTran.getTranDetails("11219891995", "2023465493", Double.valueOf(2726.6100000000001D));
                    }
         catch (Exception ep) {
        }
         String tranId = "";
         Date tranDate = new Date();
         tranDate = trDat.getTranDate();
         tranId = trDat.getTranId();
         System.out.println("TranDetails value -- " + tranDate + " -- " + tranId);
            }
     }

/* Location:           C:\Users\sn025896\Downloads\Yetunde\RIASTPAPplication\riaSTP\

 * Qualified Name:     com.fbn.bancsconnect.GetTranId

 * JD-Core Version:    0.7.0.1

 */
