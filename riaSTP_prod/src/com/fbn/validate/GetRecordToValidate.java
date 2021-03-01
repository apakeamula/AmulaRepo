package com.fbn.validate;

import com.fbn.db.jpa.RiaDtlTbl;
import com.fbn.db.jpa.RiaDtlTblJpaController;
import com.fbn.db.jpa.exceptions.NonexistentEntityException;
import com.fbn.riastp.loadProp;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import org.apache.log4j.Logger;

public class GetRecordToValidate {

    Logger logFile = Logger.getLogger(GetRecordToValidate.class);
    @PersistenceUnit(unitName = "riaSTPPU")
    EntityManagerFactory emf;

    public void validateRec()
            throws NonexistentEntityException, Exception {
        String foracid = "";
        String orderNo = "";
        String ordCurrency = "";
        List<RiaDtlTbl> rr = null;
        this.logFile.info("Enter Get record to Validate Generally");
        try {
            this.emf = Persistence.createEntityManagerFactory("riaSTPPU");
            Query xx = this.emf.createEntityManager().createNamedQuery("RiaDtlTbl.findByProcessedValidate").setParameter("failFlg", 'N');

            rr = xx.getResultList();
            this.logFile.info("Number of records to be validated -- " + rr.size());
            if (rr.isEmpty()) {
                System.out.println("No Record Found for Update");
                this.logFile.info("Record to validate is empty!!");
                return;
            }
        } catch (Exception e) {
            System.out.println("EntityManagerFactory Exception: " + e.toString());
            this.logFile.error("Error occurred in gettting records for validation -- " + e.toString() + " -- " + e.getLocalizedMessage());
        }
        this.logFile.info("How Many records here " + rr.size());
        for (RiaDtlTbl ord : rr) {
            String updateProcessed = "Y";
            try {
                this.logFile.info("Pick each record to validate -- " + ord.getOrderno() + " -- " + ord.getBankaccountno() + " -- " + ord.getBeneficiarycurrency() + " -- " + ord.getBeneficiaryamount());
                foracid = ord.getBankaccountno();
                System.out.println(foracid);
                orderNo = ord.getOrderno();
                ordCurrency = ord.getBeneficiarycurrency();
                String ordAmount = ord.getBeneficiaryamount();

                System.out.println("Currency=" + ordCurrency);
                System.out.println("ord.getFbnFlg() " + ord.getFbnFlg());
                if (ord.getFbnFlg().equals('Y')) {
                    try {
                        String finacleName = "";
                        System.out.println("Call Validator for FBN Accounts");
                        this.logFile.info("Call Validator for FBN Accounts == " + foracid + " == " + ordCurrency + " == " + orderNo + " == " + Double.valueOf(ordAmount));
                        ValidateAccount valAcct = new ValidateAccount();
                        finacleName = valAcct.validateThis(foracid, orderNo, ordCurrency, Double.valueOf(ordAmount));
                        if (finacleName.equalsIgnoreCase("Limit")) {
                            updateProcessed = "N";
                        }
                    } catch (Exception e) {
                        System.out.println("Validate Account :" + e.toString());
                        this.logFile.error("Error occurred in valdiating FBN account -- " + e.toString() + " -- " + e.getLocalizedMessage());
                        updateProcessed = "N";
                    }
                } else {
                    updateProcessed = "N";
                    //code disabled by kufre in accordance to new CBN policy
//                    try {
//                        System.out.println("Call Validator for non FBN Accounts");
//                        this.logFile.info("Call Validator for non FBN Accounts");
//                        System.out.println(ordCurrency + orderNo);
//                        this.logFile.info("Order currency and order no -- " + ordCurrency + " -- " + orderNo);
//                        String validateres = "";
//                        ValidateNeftRec giroRec = new ValidateNeftRec();
//                        validateres = giroRec.validateNeft(ordCurrency, orderNo);
//                        if(validateres.equalsIgnoreCase("")){
//                            updateProcessed = "N";
//                        }
//                        if (validateres.equalsIgnoreCase("NOT UP")) {
//                            updateProcessed = "N";
//                        }
//                    } catch (Exception e) {
//                        System.out.println("Validate NEFT :" + e.toString());
//                        this.logFile.info("Error occurred in validating NEFT transaction -- " + e.toString() + " -- " + e.getLocalizedMessage());
//                        updateProcessed = "N";
//                    }
                }
                logFile.info("UpdateProcessed flag ==> " + updateProcessed + " ==> " + orderNo);
                if (updateProcessed.equalsIgnoreCase("Y")) {
                    try {
                        RiaDtlTblJpaController em = new RiaDtlTblJpaController(this.emf);
                        em.getEntityManager();
                        RiaDtlTbl ordd = em.findRiaDtlTbl(orderNo);
                        Calendar cal = Calendar.getInstance();
                        Timestamp sysdate = new Timestamp(loadProp.SDF.parse(cal.getTime().toString()).getTime());
                        ordd.setProcessed('Y');
                        ordd.setProcessedDate(sysdate);
                        em.edit(ordd);
                        this.logFile.info("Processed flag updated to successful -- " + ordd.getOrderno());
                    } catch (Exception e) {
                        System.out.println(" JpaController Validate:" + e.toString());
                        this.logFile.error("Error occurred in updating processed flag -- " + e.toString() + " -- " + e.getLocalizedMessage());
                    }
                }
            } catch (Exception e) {
                System.out.println("Exception From GetRecord to Validate: " + e.toString());
                this.logFile.error("Error occurred in GetRecord to Validate after record picked -- " + e.toString() + " -- " + e.getLocalizedMessage());
            }

        }
    }
}
