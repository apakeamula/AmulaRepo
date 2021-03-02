package com.fbn.validate;

import com.fbn.db.jpa.GetLimitValue;
import com.fbn.db.jpa.RiaDtlTbl;
import com.fbn.db.jpa.RiaDtlTblJpaController;
import com.fbn.db.jpa.SchemeLimitsController;
import com.fbn.db.jpa.exceptions.NonexistentEntityException;
import com.fbn.riastp.loadProp;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import org.apache.log4j.Logger;

public class ValidateAccount {

    Logger logFile = Logger.getLogger(ValidateAccount.class);
    @PersistenceUnit(unitName = "riaSTPPU")
    EntityManagerFactory emf;

    public String validateThis(String foracid, String orderNo, String ordCurrency, double transactionAmount)
            throws NonexistentEntityException, Exception {
        System.out.println("Inside validateAccount code ==== ");
        String acctName = "";
        String frezCode = "";
        String currency = "";
        String acctClosed = "";
        String glSubHeadCode = "";
        String schemeCode = "";
        double clearbalAmount = 0.0D;

        System.out.println("Get query to run");
        String getQuery = "SELECT FORACID, ACCT_NAME, NVL(FREZ_CODE,0), ACCT_CLS_FLG, ACCT_CRNCY_CODE,GL_SUB_HEAD_CODE,SCHM_CODE,CLR_BAL_AMT FROM TBAADM.GAM r WHERE r.FORACID='" + foracid + "'";
        System.out.println(getQuery);
        this.logFile.info("Query to get account record from Finacle -- " + getQuery);
        this.emf = Persistence.createEntityManagerFactory("riaSTPPU");
        Query query = this.emf.createEntityManager().createNativeQuery(getQuery);
        List<Object[]> namey = query.getResultList();
        RiaDtlTblJpaController em = new RiaDtlTblJpaController(this.emf);
        em.getEntityManager();
        RiaDtlTbl ord = em.findRiaDtlTbl(orderNo);
        if (!Arrays.asList(loadProp.CURRENCYLIST).contains(ordCurrency)) {
            ord.setFailReason("Transaction on Beneficiary Currency Not Allowed");
            ord.setFailFlg('Y');
            em.edit(ord);
            this.logFile.info("Transaction on Beneficiary Currency Not Allowed --- " + Arrays.asList(loadProp.CURRENCYLIST) + " -- " + ordCurrency);
            return "invalid";
        }
        String specialSchemeLimit = loadProp.SPECIALSCHEMECODES;
        System.out.println("Special Scheme ==> " + specialSchemeLimit);
        String[] specialistValues = specialSchemeLimit.split(",");

        if (!namey.isEmpty()) {
            acctName = ((Object[]) namey.get(0))[1].toString();
            frezCode = ((Object[]) namey.get(0))[2].toString();
            acctClosed = ((Object[]) namey.get(0))[3].toString();
            currency = ((Object[]) namey.get(0))[4].toString();
            glSubHeadCode = ((Object[]) namey.get(0))[5].toString();
            schemeCode = ((Object[]) namey.get(0))[6].toString();
            clearbalAmount = Double.valueOf(((Object[]) namey.get(0))[7].toString());

            System.out.println(acctName);
            System.out.println(frezCode);
            System.out.println(currency);
            System.out.println(acctClosed);
            this.logFile.info("Account exist in Finacle -- " + acctName + " -- " + frezCode + " -- " + acctClosed + " -- " + currency);
            if (frezCode.equals("C")) {
                ord.setFailReason("Beneficiary Account is Credit Frozen");
                ord.setFailFlg('Y');
                em.edit(ord);
                return "invalid";
            }
            if (frezCode.equals("T")) {
                ord.setFailReason("Beneficiary Account is Totally Frozen");
                ord.setFailFlg('Y');
                em.edit(ord);
                return "invalid";
            }
            if (acctClosed.equals("Y")) {
                ord.setFailReason("Beneficiary Account Closed");
                ord.setFailFlg('Y');
                em.edit(ord);
                return "invalid";
            }
            if (!currency.equals(ordCurrency)) {
                ord.setFailReason("Account Currency Mismatch");
                ord.setFailFlg('Y');
                em.edit(ord);
                return "invalid";
            }
            //if ((schemeCode.equalsIgnoreCase("SA321")) || (schemeCode.equalsIgnoreCase("SA310")) || (schemeCode.equalsIgnoreCase("SA340")) || (schemeCode.equalsIgnoreCase("SA342"))) {
            if (Arrays.asList(specialistValues).contains(schemeCode)) {
                SchemeLimitsController schControl = new SchemeLimitsController();
                GetLimitValue gLimit = new GetLimitValue();
                try {
                    gLimit = schControl.confLimit(schemeCode);
                    this.logFile.info("Value of gLimit -- " + gLimit);
                    if (gLimit == null) {
                        this.logFile.info("Go on with processing of the request!!!! " + orderNo);
                    } else {
                        if (transactionAmount > Double.valueOf(gLimit.getCreditLimit())) {
                            this.logFile.info("Account cannot be credited with this amount at a time!!!! " + orderNo);
                            ord.setFailReason("Account cannot be credited with this amount at a time");
                            ord.setFailFlg('Y');
                            em.edit(ord);
                            return "invalid";
                        }
                        if (transactionAmount + clearbalAmount > Double.valueOf(gLimit.getMaxAcctBalance())) {
                            this.logFile.info("Account cannot be credited above " + gLimit.getMaxAcctBalance() + " == " + orderNo);
                            ord.setFailReason("Account cannot be credited above " + gLimit.getMaxAcctBalance());
                            ord.setFailFlg('Y');
                            em.edit(ord);
                            return "invalid";
                        }
                    }
                } catch (Exception ex) {
                    this.logFile.error("Error occurred in get Limits for account -- " + ex.getLocalizedMessage() + " ==Scheme Code== " + schemeCode);
                    return "Limit";
                }
            }

            logFile.info("Start account name validation ==> " + orderNo);
            String benefirstName = ord.getBenefirstname().toUpperCase();
            String benefirstName2 = "";
            String benelastName = "";
            if (ord.getBenelastname2() != null) {
                benelastName = ord.getBenelastname2().toUpperCase();
            }
            String[] benfirstSplit = benefirstName.trim().split(" ");
           
            if (benfirstSplit.length > 1) {
                logFile.info("Beneficiary first name split > 1 ");
                benefirstName = benfirstSplit[0].toUpperCase();
                benefirstName2 = benfirstSplit[1].toUpperCase();
                if(benfirstSplit.length >= 3){
                    benelastName = benfirstSplit[2].toUpperCase();
                } else {
                    benefirstName2 = ord.getBenelastname().toUpperCase();
                    if (ord.getBenelastname2() != null) {
                        benelastName = ord.getBenelastname2().toUpperCase();
                    }
                }              
            } else {
                 logFile.info("Beneficiary first name split < 1 ");
                benefirstName2 = ord.getBenelastname().toUpperCase();               
                String[] benelastSplit = benefirstName2.split(" ");
                 //logFile.info("Bene firstname 2 == " + benefirstName2 + " == " + benelastSplit.length);
                if (benelastSplit.length == 2) {
                    benefirstName2 = benelastSplit[0];
                    benelastName = benelastSplit[1];
                } else {
                    if (ord.getBenelastname2() != null) {
                        benelastName = ord.getBenelastname2().toUpperCase();
                    }
                }

            }

            if (benefirstName.length() < 3) {
                //  logFile.info("Beneficiary First name is less than 3");
                benefirstName = "";
            }
            if (benefirstName2.length() < 3) {
                //logFile.info("Beneficiary First name 2 is less than 3");
                benefirstName2 = "";
            }
            if (benelastName.length() < 3) {
                // logFile.info("Beneficiary last name is less than 3");
                benelastName = "";
            }

            logFile.info("Beneficiary First name -- " + benefirstName + " -- bene first name 2 -- " + benefirstName2 + " -- last name --" + benelastName);
            logFile.info("Account Name ==> " + acctName);

            if (((!benefirstName.equalsIgnoreCase("")) && (acctName.contains(benefirstName))) || ((!benefirstName2.equalsIgnoreCase("")) && (acctName.contains(benefirstName2))) || ((!benelastName.equalsIgnoreCase("")) && (acctName.contains(benelastName)))) {
                logFile.info("Match exists in account name ==> " + benefirstName + " == " + benefirstName2 + " == " + benelastName + " == " + acctName);
                return acctName;
            } else {
                logFile.info("No Match exists in account name ==> " + benefirstName + " == " + benefirstName2 + " == " + benelastName + " == " + " == " + acctName);
                ord.setFailReason("Beneficiary Name does not match with account Name ");
                ord.setFailFlg('Y');
                em.edit(ord);
                return "invalid";
            }

        }
        ord.setFailReason("Beneficiary Account Does Not Exist");
        ord.setFailFlg('Y');
        em.edit(ord);
        this.logFile.info("Beneficiary Account Does Not Exist  --- " + foracid);
        return "invalid";
    }
}
