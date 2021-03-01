 package com.fbn.db.jpa;

 import com.fbn.ria.util.DecryptionCode;
 import com.fbn.riastp.loadProp;
 import java.math.BigDecimal;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.ResultSet;
 import java.text.DateFormat;
 import java.text.SimpleDateFormat;
 import java.util.Calendar;
 import java.util.Date;
 import org.apache.log4j.Logger;

 public class GiroTblJpaController  {
     private static final Logger logFile = Logger.getLogger(GiroTblJpaController.class);
    
     public void persistGiro(RiaDtlTbl neft)
             throws Exception  {
         Connection conn = null;
        
        
        
        
         String decPwd = "";
         DecryptionCode decPwd1 = new DecryptionCode();
         decPwd = decPwd1.decryptPassword(loadProp.DATABASEPWD);
         try  {
             Class.forName(loadProp.DATABASEDRIVER);
             conn = DriverManager.getConnection(loadProp.DATABASEURL, loadProp.DATABASEUSER, decPwd);
             logFile.info("Connection made to database -- " + conn.toString());
                    } 
        catch (Exception ex)  {
             logFile.error("Error occurred in get connection to database -- " + ex.getLocalizedMessage() + " -- " + ex.toString());
             throw new Exception(ex.toString());
                    }
         try  {
             Calendar cal = Calendar.getInstance();
             String sysdate = loadProp.SDF5.format(cal.getTime());
             logFile.info("Giro Insert " + sysdate.toString());
             String benfirstName = neft.getBenefirstname();
             benfirstName = nameRet(benfirstName);
             String benfName = benfirstName + " " + neft.getBenelastname();
             String benfbkname = neft.getBankname();
             String benfbksort = neft.getBankroutingcode();
             if (benfbksort != null)  {
                 String que1 = "select BANK_NAME from custom.giro_bank where BANKCODE = '" + benfbksort.trim().substring(0, 3) + "'";
                 logFile.info("Query to get bank name -- " + que1);
                 ResultSet rs1 = null;
                
                 rs1 = conn.createStatement().executeQuery(que1);
                 if (rs1.next())  {
                     String s = rs1.getString("BANK_NAME");
                     benfbkname = s.trim();
                     logFile.info("Beneficiary bank name -- " + benfbkname);
                                    }
                 System.out.print("Tran Id -- " + neft.getOrderno() + " -- " + neft.getTranId());
                            }
             Date tranDate = neft.getTransDate();
             logFile.info("Tran date received from database -- " + tranDate);
             DateFormat dFal = new SimpleDateFormat("dd-MMM-yyyy");
             String curDateStr = dFal.format(tranDate);
             logFile.info("Change Tran date to expected date -- " + curDateStr);
             String senderName = neft.getCustfirstname();
             senderName = nameRet(senderName);
             String ownername = senderName + " " + neft.getCustlastname();
            
            
             String ownerforacid = loadProp.NEFTDEBITACCOUNT;
             BigDecimal w = new BigDecimal(neft.getBeneficiaryamount().trim());
             logFile.info("Value of amount to print -- " + w);
             String tranUniqueId = neft.getPayRef().trim() + neft.getBatchSerialNum();
              String transferReason = "";
             if(neft.getTransferreason() == null){
                 transferReason = "RIA/" + neft.getOrderno() + "/" + neft.getPin();
             }else{
                 transferReason = neft.getTransferreason() ;
             }
             String que = "insert into custom.GIRO_TABLE(BENF_NAME,BENF_BK_NAME,BENF_BK_SORT,BENF_FORACID,INIT_SORT,OWNER_NAME,OWNER_FORACID,AMOUNT,PAY_REF,CAPTURE_DATE,TRANS_DATE,TRAN_CODE,TRAN_TYPE,REVERSAL_FLG,POSTED_FLG,TRANSMIT_FLG,ENTD_USER,RETURN_FLG,TRAN_ID,PSTD_USER, TRAN_DETAIL,CUSTOM_NARR,SERIAL_NUM,DEL_FLG,BANK_ID,BRANCH_SOL_ID, TRAN_UNIQUE_ID,FAIL_FLG) values('" + benfName + "','" + benfbkname + "','" + benfbksort + "','" + neft.getBankaccountno() + "','" + loadProp.BANKSORT + "','" + ownername + "','" + ownerforacid + "','" + neft.getBeneficiaryamount().trim() + "','" + neft.getPayRef() + "',sysdate,'" + curDateStr + "','01' ,'C','N','Y','N','RIAONLINE','N','" + neft.getTranId() + "','CDCI','" + transferReason + "','" + "RIA/" + neft.getOrderno() + "/" + neft.getPin() + "','" + neft.getBatchSerialNum() + "','N','01','932','" + tranUniqueId + "','N')";
            
            
            
            
            
            
             logFile.info("Print Query -- " + que);
             int i = conn.createStatement().executeUpdate(que);
             if (i == 1) {
                 try  {
                     logFile.info("Record inserted into DB!!!");
                     conn.commit();
                     logFile.info("Insert committed successfully!!!!");
                                    } catch (Exception ex)  {
                     logFile.error("Error occurred in insert into DB for Giro -- " + neft.getOrderno() + " -- " + ex.toString());
                     throw new Exception(ex.toString());
                                    }
                            }
                    }catch (Exception e) {
             System.out.println("Giro Error " + e.toString());
             logFile.error("GiroTblJpaController Error " + e.toString());
             throw new Exception(e.toString());
                    } finally {
             if (!conn.isClosed()) {
                 conn.close();
                            }
                    }
            }
    
     private String nameRet(String fullName) {
         String valueto = "";
        
         String finalval = "";
         String finalval2 = "";
         try {
             String[] aa = fullName.split(" ");
             System.out.println(" aa length" + aa.length);
             if (aa.length == 1)  {
                 valueto = fullName;
                            } else  {
                 finalval = fullName.substring(0, fullName.indexOf(" "));
                 System.out.println("Space exist in string -- " + fullName.trim() + " -- " + finalval);
                 finalval2 = fullName.substring(fullName.indexOf(" ") + 1, fullName.indexOf(" ") + 2);
                 System.out.println("Space exist in string -- " + fullName.trim() + " -- " + finalval2);
                 valueto = finalval + " " + finalval2;
                            }
                    } catch (StringIndexOutOfBoundsException ey) {
             System.err.println("Exception String index out of bound occurred -- " + ey.toString());
             valueto = fullName;
                    }catch (Exception ex)  {
             System.err.println("Exception occurred -- " + ex.toString());
                    }
         return valueto;
            }
     }

