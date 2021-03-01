package com.fbn.validate;

import com.fbn.db.jpa.RiaDtlTbl;
import com.fbn.db.jpa.RiaDtlTblJpaController;
import com.fbn.db.jpa.exceptions.NonexistentEntityException;
import com.fbn.riastp.loadProp;
import java.util.Arrays;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.xml.ws.Holder;
import org.apache.log4j.Logger;
import org.tempuri.XHeader;

public class ValidateNeftRec {

    private static Logger logFile = Logger.getLogger(ValidateNeftRec.class);
    @PersistenceUnit(unitName = "riaSTPPU")
    EntityManagerFactory emf;

    public String validateNeft(String ordCurrency, String orderNo)
            throws NonexistentEntityException, Exception {
        logFile.info("Validate NEFT Currency");
        logFile.info("NEFTCURRENCYLIST" + loadProp.NEFTCURRENCYLIST[2]);
        this.emf = Persistence.createEntityManagerFactory("riaSTPPU");
        RiaDtlTblJpaController em = new RiaDtlTblJpaController(this.emf);
        em.getEntityManager();
        RiaDtlTbl ord = new RiaDtlTbl();
        ord = em.findRiaDtlTbl(orderNo);
        logFile.info("Order currency -- " + ordCurrency.trim());
        logFile.info("NEFTCURRENCYLIST AGAIN" + loadProp.NEFTCURRENCYLIST[2] + " ==> " + loadProp.NAMEVALIDATIONTIME);
        String namevalidationtimes = loadProp.NAMEVALIDATIONTIME;
        int namevalidationtimesint = Integer.parseInt(namevalidationtimes);
        String postbridgeserv = loadProp.POSTBRIDGEKEY;
        String postbeidgeAppname = loadProp.POSTBRIDGEAPPNAME;

        if (!Arrays.asList(loadProp.NEFTCURRENCYLIST).contains(ordCurrency.trim())) {
            ord.setFailReason("Currency Not Allowed for Other Bank Transsactions");
            ord.setFailFlg('Y');
            em.edit(ord);
            return "FAILED";
        } else {
            System.out.println("Currency Ok for NEFT");
        }

        String acctNum = ord.getBankaccountno();
        String bankCode = ord.getBankroutingcode();
        logFile.info("Check if account number is Nuban Complaint -- " + acctNum + " -- " + orderNo + " -- " + bankCode);
        if (bankCode != null) {
            bankCode = bankCode.substring(0, 3);
            logFile.info("Bank Code is -- " + acctNum + " -- " + bankCode);
            String getQuery = "select CUSTOM.IsNuban('" + acctNum + "','" + bankCode + "') from dual";
            System.out.println(getQuery);
            logFile.info("Query for Check Neft for account -- " + getQuery);

            this.emf = Persistence.createEntityManagerFactory("riaSTPPU");
            Query query = this.emf.createEntityManager().createNativeQuery(getQuery);
            Object retList = query.getSingleResult();
            String isNuban = retList.toString();
            if (isNuban.substring(0, 1).equalsIgnoreCase("N")) {
                logFile.info("Account is not NUBAN Compliant - " + acctNum + " -- " + isNuban);
                ord.setFailFlg('Y');
                ord.setFailReason("Bank Account Number is not NUBAN Compliant");
                em.edit(ord);
                return "FAILED";
            } else {
                logFile.info("Account is NUBAN Compliant - " + acctNum + " -- " + isNuban);
            }
        } else {
            logFile.info("Bank Routing Code is null -- cannot be validated !!!!");
            ord.setFailFlg('Y');
            ord.setFailReason("Bank Routing Code is compulsory for other bank transfers");
            em.edit(ord);
            return "FAILED";
        }

        logFile.info("Validate Account Name through FIP == " + ord.getBenefirstname() + " == " + ord.getBenelastname());
        logFile.info("Get Postbridge Key ==> " + postbridgeserv);
        String postbridgeKey = "";
        com.fbn.ria.util.DecryptionCode decCode = new com.fbn.ria.util.DecryptionCode();

        try {
            postbridgeKey = decCode.decryptPassword(postbridgeserv);
        } catch (Exception pe) {
            logFile.info("Error occurred in get Postbridge Key ==> Try again later ==> " + orderNo + " ==> " + pe.toString());
            return "NOT UP";
        }
        if (!postbridgeKey.equalsIgnoreCase("")) {
            logFile.info("Postbridge key decrypted ==> " + postbridgeKey);
            try {
                String accountname = "";
                org.tempuri.NameEnquiryRequest nmdReq = new org.tempuri.NameEnquiryRequest();
                nmdReq.setAccountNo(acctNum);
                nmdReq.setBankCode(bankCode);
                logFile.info("Name enquiry set ==> " + nmdReq.getAccountNo() + " == " + nmdReq.getBankCode());
                XHeader xHead = new XHeader();
                xHead.setPasskey(postbridgeKey);
                xHead.setServicename(postbeidgeAppname);
                Holder holval = new Holder(xHead);
                logFile.info("Postbridge Header ==> " + xHead.getPasskey() + " == " + xHead.getServicename());
                org.tempuri.NameEnquiryResponse2 nmdres = new org.tempuri.NameEnquiryResponse2();

                org.tempuri.PostBridge pstBrd = new org.tempuri.PostBridge();

                org.tempuri.PostBridgeSoap psPo = pstBrd.getPostBridgeSoap();
                logFile.info("Connect to postbridge service == > " + psPo);
                nmdres = psPo.nameEnquiry(nmdReq, holval);
                logFile.info("Name enquiry completed ==> " + nmdres.getResponseCode() + " ==" + nmdres.getResponseMessage());
                if (nmdres.getResponseCode().trim().equalsIgnoreCase("000")) {
                    logFile.info("Account Name is ==> " + nmdres.getAccountName() + " == " + orderNo);
                    accountname = nmdres.getAccountName();

                } else {
                    logFile.info("No name fetched ==> " + nmdres.getResponseMessage() + " == " + orderNo);
                    int existnameenq = ord.getNameEnquiryTimes();
                    if (existnameenq == namevalidationtimesint) {
                        ord.setFailFlg('Y');
                        ord.setFailReason("Account Name Enquiry Failed");
                        em.edit(ord);
                        return "FAILED";
                    } else {
                        ord.setNameEnquiryTimes(existnameenq + 1);
                        ord.setProcessed('E');
                        em.edit(ord);
                        return "NOT UP";
                    }
                }

                String acctFirstName = ord.getBenefirstname().trim().toUpperCase();
                String[] benfirstSplit = acctFirstName.split(" ");
                String acctLastname = "";
                String benefirstName2 = "";

                if (benfirstSplit.length > 1) {
                    logFile.info("Beneficiary first name split > 1 ");
                    acctFirstName = benfirstSplit[0].toUpperCase();
                    benefirstName2 = benfirstSplit[1].toUpperCase();
                    if(benfirstSplit.length >= 3){
                    acctLastname = benfirstSplit[2].toUpperCase();      
                    } else {
                        benefirstName2 = ord.getBenelastname().toUpperCase();
                        if (ord.getBenelastname2() != null) {
                            acctLastname = ord.getBenelastname2().toUpperCase();
                        }
                    }
                } else {
                    logFile.info("Beneficiary first name split < 1 ");
                    benefirstName2 = ord.getBenelastname().toUpperCase();
                    String[] benelastSplit = benefirstName2.split(" ");
                   // logFile.info("Bene firstname 2 == " + benefirstName2 + " == " + benelastSplit.length);
                    if (benelastSplit.length == 2) {
                        benefirstName2 = benelastSplit[0];
                        acctLastname = benelastSplit[1];
                    } else {
                        if (ord.getBenelastname2() != null) {
                            acctLastname = ord.getBenelastname2().toUpperCase();
                        }
                    }

                }

                if (acctFirstName.length() < 3) {
                    acctFirstName = "";
                }
                if (benefirstName2.length() < 3) {
                    benefirstName2 = "";
                }
                if (acctLastname.length() < 3) {
                    acctLastname = "";
                }

                if (((!acctFirstName.equalsIgnoreCase("")) && (accountname.contains(acctFirstName))) || ((!benefirstName2.equalsIgnoreCase("")) && (accountname.contains(benefirstName2))) || ((!acctLastname.equalsIgnoreCase("")) && (accountname.contains(acctLastname)))) {
                    logFile.info("Match exists in account name ==> " + acctFirstName + " == " + benefirstName2 + " == " + acctLastname + " == " + accountname);
                    return "SUCCESS";
                } else {
                    logFile.info("No Match exists in account name ==> " + acctFirstName + " == " + benefirstName2 + " == " + acctLastname + " == " + " == " + accountname);
                    ord.setFailFlg('Y');
                    ord.setFailReason("Account Name Mismatch");
                    em.edit(ord);
                    return "FAILED";
                }

            } catch (Exception pe) {
                logFile.error("Error occurred in get account name and validate account ==> " + pe.toString());
                logFile.info("Error in get account name ==> Try again later ==> " + orderNo);
                return "NOT UP";

            }

        } else {
            logFile.info("Postbridge Key is empty ==> Try again later ==> " + orderNo);
            return "NOT UP";
        }

    }
}
