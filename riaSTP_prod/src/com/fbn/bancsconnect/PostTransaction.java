package com.fbn.bancsconnect;

import com.fbn.riastp.loadProp;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.packager.GenericPackager;

public class PostTransaction {

    private static final Logger logFile = Logger.getLogger(PostTransaction.class);

    public String postTran(String orderNo, String crAcct, String tranReason, String suspAccount, String tranAmt, Date creationDate, String tranPIN, String tranCurr) {
        ISOMsg isomsg = new ISOMsg();
        logFile.info("Post Transaction -- " + creationDate + " -- " + crAcct + " -- " + tranReason + " -- " + suspAccount + " -- " + tranAmt + " -- " + orderNo);
        try {
            ISOPackager isopkg = new GenericPackager("iso93ascii.xml");
            ASCIIChannel channel = new ASCIIChannel(loadProp.CONNECTIP, Integer.parseInt(loadProp.CONNECTPORT), isopkg);
            channel.setTimeout(Integer.valueOf(loadProp.CONNECTTIMEOUT).intValue());
            System.out.println("Connecting.......");
            channel.connect();
            System.out.println(new Date());
            logFile.info("Start to post transaction -- " + new Date());
            isomsg.setMTI("1200");
            isomsg.set(2, "0000000000000000");
            isomsg.set(3, "400000");
            isomsg.set(4, getIsoAmount(tranAmt));
            isomsg.set(11, orderNo);
            isomsg.set(12, getIsoTime(creationDate));
            isomsg.set(17, getIsoDate(creationDate));
            isomsg.set(24, "200");
            isomsg.set(32, "504511");
            isomsg.set(42, "RIA");
            isomsg.set(49, tranCurr);
            isomsg.set(102, suspAccount);
            isomsg.set(103, crAcct);
            isomsg.set(123, "RIA");
            isomsg.set(125, tranReason);
            isomsg.set(126, tranPIN);
            System.out.println("Before Send");
            logFile.info("Before Send");
            channel.send(isomsg);
            logFile.info("After Send");
            System.out.println("After Send");
            ISOMsg r = channel.receive();
            System.out.println("Response --" + r.getString(39));
            logFile.info("Response -- " + r.getString(39) + " -- " + orderNo);
            String response = "";
            response = r.getString(39);
            channel.disconnect();
            logFile.info("End of process record in C24 -- " + orderNo);
            return response;
        } catch (Exception e) {
            logFile.error("Error occurred in posting the transaction for order number -- " + orderNo + " -- " + e.toString());
        }
        return "119";
    }

    public String postNeftTran(String orderNo, String crAcct, String tranReason, String suspAccount, String tranAmt, Date creationDate, String tranPIN, String vatAmount, String branchComm, String headofficeComm, String trancurr, String paymentRef) {
        ISOMsg isomsg = new ISOMsg();
        logFile.info("Post Transaction -- " + creationDate + " -- " + crAcct + " -- " + tranReason + " -- " + suspAccount + " -- " + tranAmt + " -- " + orderNo);
        vatAmount = getIsoAmount(vatAmount);
        branchComm = getIsoAmount(branchComm);
        headofficeComm = getIsoAmount(headofficeComm);
        String field127Val = "";
        String feeAmount46Val = "";
        field127Val = getFeeAccountMod(loadProp.NEFTCOMMISIONACCOUNTBRANCH) + getFeeAccountMod(loadProp.NEFTCOMMISIONACCOUNTHEADOFFICE) + getFeeAccountMod(loadProp.NEFTVAT) + "0000000000000000000000000000";
        feeAmount46Val = "70" + trancurr + "D" + branchComm + "00000001D0000000000000000" + trancurr + "70" + trancurr + "D" + headofficeComm + "00000001D0000000000000000" + trancurr + "70" + trancurr + "D" + vatAmount + "00000001D0000000000000000" + trancurr + "70NGND000000000000000000000001D0000000000000000NGN70NGND000000000000000000000001D0000000000000000NGN";
        try {
            ISOPackager isopkg = new GenericPackager("iso93ascii.xml");
            ASCIIChannel channel = new ASCIIChannel(loadProp.CONNECTIP, Integer.parseInt(loadProp.CONNECTPORT), isopkg);
            channel.setTimeout(Integer.valueOf(loadProp.CONNECTTIMEOUT).intValue());
            System.out.println("Connecting.......");
            channel.connect();
            System.out.println(new Date());
            logFile.info("Start to post transaction -- " + new Date());
            isomsg.setMTI("1200");
            isomsg.set(2, "0000000000000000");
            isomsg.set(3, "400000");
            isomsg.set(4, getIsoAmount(tranAmt));
            isomsg.set(11, orderNo);
            isomsg.set(12, getIsoTime(creationDate));
            isomsg.set(17, getIsoDate(creationDate));
            isomsg.set(24, "200");
            isomsg.set(32, paymentRef);
            isomsg.set(42, "RIA");
            isomsg.set(46, feeAmount46Val);
            isomsg.set(49, "NGN");
            isomsg.set(72, field127Val);
            isomsg.set(102, suspAccount);
            isomsg.set(103, crAcct);
            isomsg.set(123, "RIA");
            isomsg.set(125, tranReason);
            isomsg.set(126, tranPIN);
            System.out.println("Before Send");
            logFile.info("Before Send");
            channel.send(isomsg);
            logFile.info("After Send");
            System.out.println("After Send");
            ISOMsg r = channel.receive();
            System.out.println("Response --" + r.getString(39));
            logFile.info("Response -- " + r.getString(39) + " -- " + orderNo);
            String response = "";
            response = r.getString(39);
            channel.disconnect();
            logFile.info("End of process record in C24 -- " + orderNo);
            return response;
        } catch (Exception e) {
            logFile.error("Error occurred in posting the transaction for order number -- " + orderNo + " -- " + e.toString());
        }
        return "119";
    }

    public String postNeftFirstTran(String orderNo, String crAcct, String tranReason, String suspAccount, String tranAmt, Date creationDate, String tranPIN, String chargeAmount, String trancurr, String paymentRef) {
        ISOMsg isomsg = new ISOMsg();
        logFile.info("Post Transaction -- " + creationDate + " -- " + crAcct + " -- " + tranReason + " -- " + suspAccount + " -- " + tranAmt + " -- " + orderNo);
        chargeAmount = getIsoAmount(chargeAmount);
        String field127Val = "";
        String feeAmount46Val = "";
        field127Val = getFeeAccountMod(crAcct) + "00000000000000000000000000000000000000000000000000000000";
        feeAmount46Val = "70" + trancurr + "D" + chargeAmount + "00000001D0000000000000000" + trancurr + "70NGND000000000000000000000001D0000000000000000NGN70NGND000000000000000000000001D0000000000000000NGN70NGND000000000000000000000001D0000000000000000NGN70NGND000000000000000000000001D0000000000000000NGN";
        logFile.info("Field127 -- " + field127Val + " -- " + feeAmount46Val + " -- First Post for Neft -----");
        try {
            ISOPackager isopkg = new GenericPackager("iso93ascii.xml");
            ASCIIChannel channel = new ASCIIChannel(loadProp.CONNECTIP, Integer.parseInt(loadProp.CONNECTPORT), isopkg);
            channel.setTimeout(Integer.valueOf(loadProp.CONNECTTIMEOUT).intValue());
            System.out.println("Connecting.......");
            channel.connect();
            System.out.println(new Date());
            logFile.info("Start to post transaction -- " + new Date());
            isomsg.setMTI("1200");
            isomsg.set(2, "0000000000000000");
            isomsg.set(3, "400000");
            isomsg.set(4, getIsoAmount(tranAmt));
            isomsg.set(11, orderNo);
            isomsg.set(12, getIsoTime(creationDate));
            isomsg.set(17, getIsoDate(creationDate));
            isomsg.set(24, "200");
            isomsg.set(32, paymentRef);
            isomsg.set(42, "RIA");
            isomsg.set(46, feeAmount46Val);
            isomsg.set(49, "NGN");
            isomsg.set(72, field127Val);
            isomsg.set(102, suspAccount);
            isomsg.set(103, crAcct);
            isomsg.set(123, "RIA");
            isomsg.set(125, tranReason);
            isomsg.set(126, tranPIN);
            System.out.println("Before Send");
            logFile.info("Before Send");
            channel.send(isomsg);
            logFile.info("After Send");
            System.out.println("After Send");
            ISOMsg r = channel.receive();
            System.out.println("Response --" + r.getString(39));
            logFile.info("Response -- " + r.getString(39) + " -- " + orderNo);
            String response = "";
            response = r.getString(39);
            channel.disconnect();
            logFile.info("End of process record in C24 -- " + orderNo);
            return response;
        } catch (Exception e) {
            logFile.error("Error occurred in posting the transaction for order number -- " + orderNo + " -- " + e.toString());
        }
        return "119";
    }

    public String GetBalance(String orderNo, String crAcct, String tranAmt, Date creationDate, String tranCurr) {
        ISOMsg isomsg = new ISOMsg();
        logFile.info("Post Transaction -- " + creationDate + " -- " + crAcct + " -- " + tranAmt + " -- " + orderNo);
        try {
            ISOPackager isopkg = new GenericPackager("iso93ascii.xml");
            ASCIIChannel channel = new ASCIIChannel(loadProp.CONNECTIP, Integer.parseInt(loadProp.CONNECTPORT), isopkg);
            channel.setTimeout(Integer.valueOf(loadProp.CONNECTTIMEOUT).intValue());
            System.out.println("Connecting.......");
            channel.connect();
            System.out.println(new Date());
            logFile.info("Start to post transaction -- " + new Date());
            isomsg.setMTI("1200");
            isomsg.set(3, "310000");
            isomsg.set(4, getIsoAmount(tranAmt));
            isomsg.set(11, orderNo);
            isomsg.set(12, getIsoTime(creationDate));
            isomsg.set(17, getIsoDate(creationDate));
            isomsg.set(24, "200");
            isomsg.set(32, "504511");
            isomsg.set(34, "YET");
            isomsg.set(42, "RIA");
            isomsg.set(49, tranCurr);
            isomsg.set(102, crAcct);
            isomsg.set(123, "RIA");
            System.out.println("Before Send");
            logFile.info("Before Send");
            channel.send(isomsg);
            logFile.info("After Send");
            System.out.println("After Send");
            ISOMsg r = channel.receive();
            System.out.println("Response --" + r.getString(39));
            System.out.println("Balance --" + r.getString(48));
            logFile.info("Response -- " + r.getString(39) + " -- " + orderNo);
            String response = "";
            String balRes = r.getString(49);
            response = balRes.substring(1, 17);
            channel.disconnect();
            logFile.info("End of process record in C24 -- " + orderNo);
            return response;
        } catch (Exception e) {
            logFile.error("Error occurred in posting the transaction for order number -- " + orderNo + " -- " + e.toString());
        }
        return "119";
    }

    private String getIsoTime(Date d) {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(d);
    }

    private String getIsoDate(Date d) {
        return new SimpleDateFormat("yyyyMMdd").format(d);
    }

    public String getIsoAmount(String amt) {
        double total = Double.valueOf(amt).doubleValue();
        String val = String.format("%017.2f", new Object[]{Double.valueOf(total)});
        amt = val.replace(".", "");
        return amt;
    }

    private String getFeeAccountMod(String feeaccount) {
        String feeAcc = "";
        while (feeaccount.length() < 14) {
            feeaccount = "0" + feeaccount;
        }
        feeAcc = feeaccount;
        return feeAcc;
    }
}

/* Location:           C:\Users\sn025896\Downloads\Yetunde\RIASTPAPplication\riaSTP\

 * Qualified Name:     com.fbn.bancsconnect.PostTransaction

 * JD-Core Version:    0.7.0.1

 */
