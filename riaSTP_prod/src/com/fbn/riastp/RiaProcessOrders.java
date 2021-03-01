package com.fbn.riastp;

import com.fbn.riathreads.FBNRecordtoPost;
import com.fbn.riathreads.PostInsertNeftGiro;
import com.fbn.riathreads.PostInterBankFIP;
import com.fbn.riathreads.ProcessCancelledRequest;
import com.fbn.validate.GetRecordToValidate;
import com.fbn.xml.parse.GetStatusForUpdate;
import java.io.PrintStream;
import org.apache.log4j.Logger;

public class RiaProcessOrders {

    static Logger logFile = Logger.getLogger(RiaProcessOrders.class);

    public static void main(String[] args) {
        logFile.info("Start Process of Transactions !!!");
        setCurrency();

        logFile.info("Call up cancelled requests !!!");
        try {
            ProcessCancelledRequest proCanReq = new ProcessCancelledRequest();
            proCanReq.processCancelledRequest();
            logFile.info("Cancelled Request received and processed");
        } catch (Exception e) {
            logFile.error("Error occurred in process cancelled request -- " + e.toString() + " -- " + e.getLocalizedMessage());
        }
        logFile.info("Validate records first -- ");
        try {
            GetRecordToValidate valRecord = new GetRecordToValidate();
            valRecord.validateRec();
        } catch (Exception ex) {
            logFile.error("Error occurred in validate record and marking as processed -- " + ex.getLocalizedMessage() + " -- " + ex.toString());
        }
        try {
            FBNRecordtoPost fbnRecord = new FBNRecordtoPost();
            fbnRecord.getRecord();
        } catch (Exception ex) {
            logFile.error("Error occurred in process RIA FBN transactions -- " + ex.toString() + " -- " + ex.getLocalizedMessage());
        }

        if (loadProp.INTERBANKSWITCH.equalsIgnoreCase("NEFT")) {
            try {
                PostInsertNeftGiro pstNeft = new PostInsertNeftGiro();
                pstNeft.PostNeftTransactions();
            } catch (Exception ex) {
                logFile.error("Error occurred in process RIA NEFT transactions -- " + ex.toString() + " -- " + ex.getLocalizedMessage());
            }
        } else {
            try {
                PostInterBankFIP pstFIP = new PostInterBankFIP();
                pstFIP.PostFIPTransactions();
            } catch (Exception ex) {
                logFile.error("Error occurred in process RIA FIP transactions -- " + ex.toString() + " -- " + ex.getLocalizedMessage());
            }

        }
        try {
            com.fbn.validate.CheckNeftReturn neftret = new com.fbn.validate.CheckNeftReturn();
            neftret.checkNeftRet();
        } catch (Exception e) {
            System.out.println("CheckNeftReturn Error: " + e.toString());
            logFile.error("CheckNeftReturn Error: " + e.toString() + " -- " + e.getLocalizedMessage());
        }
        try {
            GetStatusForUpdate updt = new GetStatusForUpdate();
            updt.GetStatus();
        } catch (Exception e) {
            System.out.println("Error Occurred while Updating Data " + e.toString());
            logFile.error("Error Occurred while Updating Data " + e.toString() + " -- " + e.getLocalizedMessage());
        }
    }

    private static void setCurrency() {
        loadProp.CURRENCYLIST = loadProp.CURRENCYALLOWED.split(" ");
        loadProp.SUSPENSELIST = loadProp.SUSPENSEACCOUNTS.split(" ");
        loadProp.NEFTCURRENCYLIST = loadProp.CURRENCYALLOWEDFORNEFT.split(" ");
        loadProp.NEFTSUSPENSELIST = loadProp.NEFTOUTWARDSUSPENSE.split(" ");
        System.out.println("Neft Currency " + loadProp.NEFTCURRENCYLIST[2]);
        logFile.info("Neft Currency " + loadProp.NEFTCURRENCYLIST[2]);
    }
}
