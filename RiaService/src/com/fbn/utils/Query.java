package com.fbn.utils;

public class Query implements RiaConstants {
    private String susacct;
    private String beneAmount;
    private String beneAccountNo;
    private String orderNo;
    private String txnid;
 
    public Query (){

    }
    public Query(String orderNo) {
        this.orderNo = orderNo;
    }
  
    public  Query (String orderNo,String susacct, String beneAccountNo, String beneAmount){
        this.orderNo = orderNo;
        this.susacct = susacct;
        this.beneAmount = beneAmount;
        this.beneAccountNo = beneAccountNo;
    }
    
    public String getRiaTxn() {
        return  "select ORDERNO, BENEFICIARYCURRENCY, BENEFICIARYAMOUNT, BANKACCOUNTNO from " + ria_table + " where FBN_FLG ='Y' and PROCESSED = 'Y' and PSTD_FLG = 'T' and FAIL_FLG = 'N' and BENEFICIARYCURRENCY = 'USD'";
    }

    public String getUpdateRiaTxn() {
        return  "select ORDERNO from " + ria_table + " where FBN_FLG ='Y' and PROCESSED = 'Y' and PSTD_FLG = 'M' and FAIL_FLG = 'N' and BENEFICIARYCURRENCY  = 'USD'";
    }
    
    public String insertRiaTxn() {
        return "insert into " + mto_table + " (txnid, mtocode, mtosuspenseacct,tran_amt, ngnaccountno, status) values ('" + orderNo + "', '" + mtocode + "'," + susacct + "," + beneAmount + "," + beneAccountNo + ", '" +status+ "')";
    }
    public String updateRiaTxn() {
        return "update " + ria_table + " set PSTD_FLG = 'M' where ORDERNO = '" + orderNo + "'";   
    }

    public String getUpdateStatus() {
        return "select tran_status from " + mto_table + " where mtocode = '" + mtocode + "' and txnid = '" + orderNo + "'";
    }

    public String updateRiaPostStatus () {
        return "update " + ria_table + " set PSTD_FLG = 'Y', PSTD_DATE = (select to_date(to_char(SYSDATE, 'MM/DD/YYYY HH24:MI:SS'),('MM/DD/YYYY HH24:MI:SS')) from dual), RESPONSE_MESSAGE = 'Transaction Successful From RiaService' where orderno = '" + orderNo + "'";
    }
}
