package com.fbn.db.jpa;

import com.fbn.ria.util.DecryptionCode;
import com.fbn.riastp.loadProp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

public class AtcTblDirectFeeCheck {

    Logger logFile = Logger.getLogger(AtcTblDirectFeeCheck.class);

    public String getTotalFees(double tranAmount)
            throws Exception {
        String totFees = "";
        AtcTblDirectFeeCheck AtcCheck = new AtcTblDirectFeeCheck();
        try {
            String commAmount = AtcCheck.getTotalCommissionFees(tranAmount);
            double commAmt = Double.parseDouble(commAmount);
            this.logFile.info("Commission Amount for Tran Amount --- " + commAmt + " --- " + tranAmount);
                   //String vatAmt = AtcCheck.getVATFees(commAmt);
            //double vatAmount = Double.parseDouble(vatAmt);
            //this.logFile.info("Vat Amount for Tran Amount and Commission Amount --- " + commAmt + " --- " + vatAmount + " -- " + tranAmount);
            //double totAmount = commAmt + vatAmount;
            totFees = String.valueOf(commAmt);
        } catch (Exception er) {
            this.logFile.error("Error occurred in getting Total Fees -- " + er.getLocalizedMessage());
            throw er;
        }
        this.logFile.info("Total Fees for transaction amount -- " + totFees + " --- " + tranAmount);
        return totFees;
    }

    public String getTotalCommissionFees(double tranAmount)
            throws Exception {
        String totFees = "";
        Connection conn = null;
        PreparedStatement stmt1 = null;
        ResultSet rs1 = null;
        try {
            String decPwd = "";
            DecryptionCode decPwd1 = new DecryptionCode();
            decPwd = decPwd1.decryptPassword(loadProp.DATABASEPWD);
            try {
                Class.forName(loadProp.DATABASEDRIVER);
                conn = DriverManager.getConnection(loadProp.DATABASEURL, loadProp.DATABASEUSER, decPwd);
                this.logFile.info("Connection made to database -- " + conn.toString());
            } catch (Exception ex) {
                this.logFile.error("Error occurred in get connection to database -- " + ex.getLocalizedMessage() + " -- " + ex.toString());
                throw new Exception(ex.toString());
            }
            String getFees = "SELECT MAX_AMT FROM tbaadm.amount_table_code_table  where AMT_TBL_CODE = 'NFTCH' and '" + tranAmount + "' between START_AMT and END_AMT and DEL_FLG ='N'";
            stmt1 = conn.prepareStatement(getFees);
            rs1 = stmt1.executeQuery();
            double amtValue = 0.0D;
            while (rs1.next()) {
                this.logFile.info("Query returned successfully -- " + rs1.getDouble("MAX_AMT") + " -- " + tranAmount);
                amtValue = rs1.getDouble("MAX_AMT");
                this.logFile.info("Commission fee -- " + amtValue + " -- " + tranAmount);
            }
            totFees = String.valueOf(amtValue);
        } catch (Exception er) {
            this.logFile.error("Error occurred in getting Total Fees -- " + er.getLocalizedMessage());
            throw er;
        }
        {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception pe) {
                    this.logFile.info("Error occurred in close Connection!!!!   " + pe.getLocalizedMessage());
                }
            }
            if (stmt1 != null) {
                try {
                    stmt1.close();
                } catch (Exception pe) {
                    this.logFile.info("Error occurred in close Prepared Statement !!!!   " + pe.getLocalizedMessage());
                }
            }
            if (rs1 != null) {
                try {
                    rs1.close();
                } catch (Exception pe) {
                    this.logFile.info("Error occurred in close resultset!!!!   " + pe.getLocalizedMessage());
                }
            }
            this.logFile.info("All connections and statements and resultset closed successfully for getTotalCommissionFees!!!!");
        }
        return totFees;
    }

    public String getVATFees(double commissionAmount)
            throws Exception {
        String vatFees = "";
        PreparedStatement stmt1 = null;
        ResultSet rs1 = null;
        Connection conn = null;
        try {
            String decPwd = "";
            DecryptionCode decPwd1 = new DecryptionCode();
            decPwd = decPwd1.decryptPassword(loadProp.DATABASEPWD);
            try {
                Class.forName(loadProp.DATABASEDRIVER);
                conn = DriverManager.getConnection(loadProp.DATABASEURL, loadProp.DATABASEUSER, decPwd);
                this.logFile.info("Connection made to database -- " + conn.toString());
            } catch (Exception ex) {
                this.logFile.error("Error occurred in get connection to database -- " + ex.getLocalizedMessage() + " -- " + ex.toString());
                throw new Exception(ex.toString());
            }
            String getFees = "SELECT PCNT_AMT FROM tbaadm.amount_table_code_table  where AMT_TBL_CODE = 'VATCH' and DEL_FLG ='N'";
            stmt1 = conn.prepareStatement(getFees);
            rs1 = stmt1.executeQuery();
            double amtValue = 0.0D;
            while (rs1.next()) {
                this.logFile.info("Query returned successfully -- " + rs1.getDouble("PCNT_AMT") + " -- " + commissionAmount);
                amtValue = rs1.getDouble("PCNT_AMT");
                this.logFile.info("VAT AMount Percentage fee -- " + amtValue + " -- " + commissionAmount);
            }
            this.logFile.info("Process VAT Amount -- ");
            double feeCalc = 0.0D;
            feeCalc = amtValue * commissionAmount / 100.0D;
            this.logFile.info("Exact VAT AMount fee -- " + feeCalc + " -- " + commissionAmount);
            vatFees = String.valueOf(feeCalc);
            this.logFile.info("Exact VAT AMount fee in String -- " + vatFees + " -- " + commissionAmount);
        } catch (Exception er) {
            this.logFile.error("Error occurred in getting Total Fees -- " + er.getLocalizedMessage());
            throw er;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception pe) {
                    this.logFile.info("Error occurred in close Connection!!!!   " + pe.getLocalizedMessage());
                }
            }
            if (stmt1 != null) {
                try {
                    stmt1.close();
                } catch (Exception pe) {
                    this.logFile.info("Error occurred in close Prepared Statement !!!!   " + pe.getLocalizedMessage());
                }
            }
            if (rs1 != null) {
                try {
                    rs1.close();
                } catch (Exception pe) {
                    this.logFile.info("Error occurred in close resultset!!!!   " + pe.getLocalizedMessage());
                }
            }
            this.logFile.info("All connections and statements and resultset closed successfully for getVATAmount!!!!");
        }
        return vatFees;
    }

    public CommissionFeeData getSeperateCommissionFees(double commissionAmount)
            throws Exception {
        CommissionFeeData totFees = new CommissionFeeData();
        PreparedStatement stmt1 = null;
        ResultSet rs1 = null;
        Connection conn = null;
        try {
            String decPwd = "";
            DecryptionCode decPwd1 = new DecryptionCode();
            decPwd = decPwd1.decryptPassword(loadProp.DATABASEPWD);
            try {
                Class.forName(loadProp.DATABASEDRIVER);
                conn = DriverManager.getConnection(loadProp.DATABASEURL, loadProp.DATABASEUSER, decPwd);
                this.logFile.info("Connection made to database -- " + conn.toString());
            } catch (Exception ex) {
                this.logFile.error("Error occurred in get connection to database -- " + ex.getLocalizedMessage() + " -- " + ex.toString());
                throw new Exception(ex.toString());
            }
            String getFees = "select BR_AMT,NIBBS_AMT,VAT from custom.NEFTCHARGES where COMM_AMT = '" + commissionAmount + "' and DEL_FLG ='N'";
            stmt1 = conn.prepareStatement(getFees);
            rs1 = stmt1.executeQuery();
            double brComm = 0.0D;
            double niComm = 0.0D;
            double vatAmt = 0.0D;
            while (rs1.next()) {
                totFees = new CommissionFeeData();
                brComm = rs1.getDouble("BR_AMT");
                niComm = rs1.getDouble("NIBBS_AMT");
                vatAmt = rs1.getDouble("VAT");
            }
            totFees.setBranchCommission(String.valueOf(brComm));
            totFees.setHeadOfficeCommission(String.valueOf(niComm));
            totFees.setVatAmount(String.valueOf(vatAmt));
            this.logFile.info("Fees for commission -- " + totFees.getBranchCommission() + " --- " + totFees.getHeadOfficeCommission() + " -- " + commissionAmount);
        } catch (Exception er) {
            this.logFile.error("Error occurred in getting Total Fees -- " + er.getLocalizedMessage());
            throw er;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception pe) {
                    this.logFile.info("Error occurred in close Connection!!!!   " + pe.getLocalizedMessage());
                }
            }
            if (stmt1 != null) {
                try {
                    stmt1.close();
                } catch (Exception pe) {
                    this.logFile.info("Error occurred in close Prepared Statement !!!!   " + pe.getLocalizedMessage());
                }
            }
            if (rs1 != null) {
                try {
                    rs1.close();
                } catch (Exception pe) {
                    this.logFile.info("Error occurred in close resultset!!!!   " + pe.getLocalizedMessage());
                }
            }
            this.logFile.info("All connections and statements and resultset closed successfully for get Seperate Commission Amount!!!!");
        }
        return totFees;
    }

    public static void main(String[] args) {
        AtcTblDirectFeeCheck atCheck = new AtcTblDirectFeeCheck();
        double tranAmount = 2000.0D;
        CommissionFeeData comfee = new CommissionFeeData();
        try {
            String commAmount = atCheck.getTotalCommissionFees(tranAmount);
            System.out.println("Value of VAT Amount --- " + atCheck.getVATFees(Double.parseDouble(commAmount)));
            comfee = atCheck.getSeperateCommissionFees(Double.parseDouble(commAmount));
            System.out.println("Branch Commission -- " + comfee.getBranchCommission() + " ---- " + "Head office Fee -- " + comfee.getHeadOfficeCommission());
        } catch (Exception ex) {
            System.out.println("Error occured in get values  --- " + ex.toString());
        }
    }
}

/* Location:           C:\Users\sn025896\Downloads\Yetunde\RIASTPAPplication\riaSTP\

 * Qualified Name:     com.fbn.db.jpa.AtcTblDirectFeeCheck

 * JD-Core Version:    0.7.0.1

 */
