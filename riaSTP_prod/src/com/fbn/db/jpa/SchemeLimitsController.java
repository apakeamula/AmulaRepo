 package com.fbn.db.jpa;

 import com.fbn.ria.util.DecryptionCode;
 import com.fbn.riastp.loadProp;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import org.apache.log4j.Logger;

 public class SchemeLimitsController {
     Logger logFile = Logger.getLogger(SchemeLimitsController.class);
    
     public GetLimitValue confLimit(String schemeCode)  {
         GetLimitValue limValue = new GetLimitValue();
         try  {
             Connection conn = null;
             String decPwd = "";
             DecryptionCode decPwd1 = new DecryptionCode();
             decPwd = decPwd1.decryptPassword(loadProp.DATABASEPWD);
             try  {
                 Class.forName(loadProp.DATABASEDRIVER);
                 conn = DriverManager.getConnection(loadProp.DATABASEURL, loadProp.DATABASEUSER, decPwd);
                 this.logFile.info("Connection made to database -- " + conn.toString());
                            } catch (Exception ex)  {
                 this.logFile.error("Error occurred in get connection to database -- " + ex.getLocalizedMessage() + " -- " + ex.toString());
                 throw new Exception(ex.toString());
                            }
             PreparedStatement ps = null;
             ResultSet rs = null;
             try  {
                 String queryStr = "select DAILY_DR_LIMIT, TRAN_CR_LIMIT, MIN_ACCT_BAL,MAX_ACCT_BAL from custom.productslimit where schm_code='" + schemeCode + "'" + " and DEL_FLG='N' and BANK_ID='01'";
                
                 this.logFile.info("Limit Validation Query --- " + queryStr);
                 ps = conn.prepareStatement(queryStr);
                
                
                 rs = ps.executeQuery();
                 
                while (rs.next())  {
                     this.logFile.info("Value of rs -- " + rs.toString() + " --- " + rs.getString("TRAN_CR_LIMIT"));
                     limValue = new GetLimitValue();
                     limValue.setDebitLimit(rs.getString(1));
                     limValue.setCreditLimit(rs.getString(2));
                     limValue.setMinAcctBalance(rs.getString(3));
                     limValue.setMaxAcctBalance(rs.getString(4));
                     System.out.println("Values returned -- " + limValue.getDebitLimit() + " -- " + limValue.getMaxAcctBalance() + " -- " + schemeCode);
                                    }
                            } catch (SQLException e) {
                 e.printStackTrace();
                 this.logFile.info("Error occurred in get limits from DB -- " + e.getLocalizedMessage() + " -- " + e.getCause());
                 throw new Exception();
                            } catch (Exception ep) {
                 ep.printStackTrace();
                 this.logFile.info("Error occurred at Fetch -- " + ep.toString());
                 throw new Exception();
                            } finally  {
                 if (rs != null) {
                     try  {
                         rs.close();
                                            }  catch (Exception e) {
                         this.logFile.error("Error occurred in closing the resultset for getting limits -- " + e.toString());
                                            }
                                    }
                 if (ps != null) {
                     try {
                         ps.close();
                                            } catch (Exception e)  {
                         this.logFile.error("Error occurred in closing prepared statement created for get limits -- " + e.toString());
                                            }
                                    }
                            }
             return limValue;
                    }  catch (Exception ep) {
             this.logFile.error("Error occurred in getting the limit for a scheme code -- " + ep.toString());
                    }
        return limValue;
            }
     }

