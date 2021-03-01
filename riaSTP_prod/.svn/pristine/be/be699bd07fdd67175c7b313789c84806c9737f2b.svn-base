 package com.fbn.db.jpa;
 import javax.persistence.EntityManager;
 import javax.persistence.EntityManagerFactory;
 import javax.persistence.Persistence;
 import javax.persistence.Query;
 import org.apache.log4j.Logger;
  public class AtcTblController 
 {
     Logger logFile = Logger.getLogger(AtcTblController.class);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("riaSTPPU");
        EntityManager em = this.emf.createEntityManager();
          public String getCommissionFees(String tranAmount)
             throws Exception
     {
         String NibbsFee = "";
         try 
         {
             this.logFile.info("Get Commission fees for NEFT transaction -- " + tranAmount);
             Query neftTrans = this.em.createNamedQuery("AtcTbl.findCommissionAmt").setParameter("amttblcode", "NFTCH").setParameter("tramAmount", tranAmount).setParameter("delflg", Character.valueOf('N'));
                                                    Object distinctRec = neftTrans.getSingleResult();
             AtcTbl distRec = (AtcTbl) distinctRec;
             NibbsFee = distRec.getMaxamount();
             this.logFile.info("Maximum amount fee for tran amount -- " + NibbsFee + " -- " + tranAmount);
                    } 
         catch (Exception ex) 
         {
             this.logFile.error("Error occurred in get commission fee  -- " + ex.toString() + " -- " + ex.getLocalizedMessage());
             throw new Exception(ex.toString());
                    }
         return NibbsFee;
            }
          public String getVATAmount(String commissionAmt)
             throws Exception 
     {
         String vatAmt = "";
         try 
         {
             this.logFile.info("Get VAT fees for NEFT transaction -- ");
             Query neftTrans = this.em.createNamedQuery("AtcTbl.findVatPercent").setParameter("amttblcode", "VATCH").setParameter("delflg", Character.valueOf('N'));
                                                    Object distinctRec = neftTrans.getSingleResult();
             AtcTbl distRec = (AtcTbl) distinctRec;
             String pcntval = distRec.getPcntamount();
             this.logFile.info("PCNT Value -- " + pcntval + " -- Commission amount -- " + commissionAmt);
             Double feecalc = Double.valueOf(Double.parseDouble(pcntval) * Double.parseDouble(commissionAmt) / 100.0D);
             this.logFile.info("Calculated fee is -- " + feecalc);
             vatAmt = String.valueOf(feecalc);
             this.logFile.info("Calculated string fee -- " + vatAmt);
                    } 
         catch (Exception ex) 
             {
             this.logFile.error("Error occurred in calculate vat amount -- " + ex.toString() + " -- " + ex.getLocalizedMessage());
             throw new Exception(ex.toString());
                    }
         return vatAmt;
            }
          public String getFinalFees(String tranAmount)
             throws Exception 
     {
         String FinFee = "";
         AtcTblController atcControl = new AtcTblController();
         double FinFeed = 0.0D;
         String commissionFee = "";
         String vatFee = "";
         try 
         {
             commissionFee = atcControl.getCommissionFees(tranAmount);
             this.logFile.info("Commission fee for transaction -- " + commissionFee);
             vatFee = atcControl.getVATAmount(commissionFee);
             this.logFile.info("Vat fee for transaction -- " + vatFee);
             FinFeed = Double.parseDouble(commissionFee) + Double.parseDouble(vatFee);
             FinFee = String.valueOf(FinFeed);
             this.logFile.info("Final fee returned for transaction -- " + FinFee + " -- " + FinFeed + " -- " + tranAmount);
                    } 
         catch (Exception ex) 
         {
             this.logFile.error("Error occurred in getFinalFees -- " + ex.toString() + " -- " + ex.getLocalizedMessage());
             throw new Exception(ex.toString());
                    }
         return FinFee;
            }
          public CommissionFeeData getCommissionDiff(String commissionAmount)
             throws Exception 
     {
         CommissionFeeData comFee = new CommissionFeeData();
         try 
         {
             this.logFile.info("Get commission seperate fees for NEFT transaction -- ");
             Query neftTrans = this.em.createNamedQuery("NeftCommisionChargeTbl.findOne").setParameter("commamt", commissionAmount);
                                                    Object distinctRec = neftTrans.getSingleResult();
             NeftCommisionChargeTbl neftComm = (NeftCommisionChargeTbl) distinctRec;
                          String branchIncome = neftComm.getBr_amt();
             String headofficeIncome = neftComm.getNibbs_amt();
                          this.logFile.info("Branch Value -- " + branchIncome + " -- Head office Commission amount -- " + headofficeIncome);
             comFee.setBranchCommission(branchIncome);
             comFee.setHeadOfficeCommission(headofficeIncome);
                    } 
         catch (Exception ex) 
         {
             this.logFile.error("Error occurred in calculate vat amount -- " + ex.toString() + " -- " + ex.getLocalizedMessage());
             throw new Exception(ex.toString());
                    }
         return comFee;
            }
          public static void main(String[] args) 
     {
         AtcTblController atcFee = new AtcTblController();
         CommissionFeeData comFee = new CommissionFeeData();
         try 
         {
             comFee = atcFee.getCommissionDiff("125");
                    } 
         catch (Exception ex) 
         {
             ex.printStackTrace();
                    }
         System.out.println("Values gotten -- " + comFee.getBranchCommission() + " -- " + comFee.getHeadOfficeCommission());
            }
     }

/* Location:           C:\Users\sn025896\Downloads\Yetunde\RIASTPAPplication\riaSTP\

 * Qualified Name:     com.fbn.db.jpa.AtcTblController

 * JD-Core Version:    0.7.0.1

 */
