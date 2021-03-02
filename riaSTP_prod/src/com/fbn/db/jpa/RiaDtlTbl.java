 package com.fbn.db.jpa;

 import java.io.Serializable;
 import java.util.Date;
 import javax.persistence.Basic;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.Id;
 import javax.persistence.NamedQueries;
 import javax.persistence.Table;
 import javax.persistence.Temporal;
 import javax.persistence.TemporalType;
 import javax.xml.bind.annotation.XmlRootElement;

 @Entity
 @Table(name = "RIA_DTL_TBL", catalog = "", schema = "CUSTOM")
 @XmlRootElement
 @NamedQueries({
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findAll", query = "SELECT r FROM RiaDtlTbl r"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByOrderno", query = "SELECT r FROM RiaDtlTbl r WHERE r.orderno = :orderno"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByPin", query = "SELECT r FROM RiaDtlTbl r WHERE r.pin = :pin"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByPayingcorrespseqid", query = "SELECT r FROM RiaDtlTbl r WHERE r.payingcorrespseqid = :payingcorrespseqid"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findBySalesdate", query = "SELECT r FROM RiaDtlTbl r WHERE r.salesdate = :salesdate"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findBySalestime", query = "SELECT r FROM RiaDtlTbl r WHERE r.salestime = :salestime"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByCountryfrom", query = "SELECT r FROM RiaDtlTbl r WHERE r.countryfrom = :countryfrom"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByCountryto", query = "SELECT r FROM RiaDtlTbl r WHERE r.countryto = :countryto"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findBySendingcorrespseqid", query = "SELECT r FROM RiaDtlTbl r WHERE r.sendingcorrespseqid = :sendingcorrespseqid"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByPayingcorresplocid", query = "SELECT r FROM RiaDtlTbl r WHERE r.payingcorresplocid = :payingcorresplocid"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findBySendingcorrespbranchno", query = "SELECT r FROM RiaDtlTbl r WHERE r.sendingcorrespbranchno = :sendingcorrespbranchno"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByBenequestion", query = "SELECT r FROM RiaDtlTbl r WHERE r.benequestion = :benequestion"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByBeneanswer", query = "SELECT r FROM RiaDtlTbl r WHERE r.beneanswer = :beneanswer"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByPmtinstructions", query = "SELECT r FROM RiaDtlTbl r WHERE r.pmtinstructions = :pmtinstructions"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByBeneficiarycurrency", query = "SELECT r FROM RiaDtlTbl r WHERE r.beneficiarycurrency = :beneficiarycurrency"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByBeneficiaryamount", query = "SELECT r FROM RiaDtlTbl r WHERE r.beneficiaryamount = :beneficiaryamount"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByDeliverymethod", query = "SELECT r FROM RiaDtlTbl r WHERE r.deliverymethod = :deliverymethod"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByPaymentcurrency", query = "SELECT r FROM RiaDtlTbl r WHERE r.paymentcurrency = :paymentcurrency"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByPaymentamount", query = "SELECT r FROM RiaDtlTbl r WHERE r.paymentamount = :paymentamount"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByCommissioncurrency", query = "SELECT r FROM RiaDtlTbl r WHERE r.commissioncurrency = :commissioncurrency"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByCommissionamount", query = "SELECT r FROM RiaDtlTbl r WHERE r.commissionamount = :commissionamount"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByCustomerchargecurrency", query = "SELECT r FROM RiaDtlTbl r WHERE r.customerchargecurrency = :customerchargecurrency"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByCustomerchargeamount", query = "SELECT r FROM RiaDtlTbl r WHERE r.customerchargeamount = :customerchargeamount"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByBeneid", query = "SELECT r FROM RiaDtlTbl r WHERE r.beneid = :beneid"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByBenefirstname", query = "SELECT r FROM RiaDtlTbl r WHERE r.benefirstname = :benefirstname"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByBenelastname", query = "SELECT r FROM RiaDtlTbl r WHERE r.benelastname = :benelastname"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByBenelastname2", query = "SELECT r FROM RiaDtlTbl r WHERE r.benelastname2 = :benelastname2"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByBeneaddress", query = "SELECT r FROM RiaDtlTbl r WHERE r.beneaddress = :beneaddress"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByBenecity", query = "SELECT r FROM RiaDtlTbl r WHERE r.benecity = :benecity"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByBenestate", query = "SELECT r FROM RiaDtlTbl r WHERE r.benestate = :benestate"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByBenezipcode", query = "SELECT r FROM RiaDtlTbl r WHERE r.benezipcode = :benezipcode"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByBenecountry", query = "SELECT r FROM RiaDtlTbl r WHERE r.benecountry = :benecountry"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByBenephoneno", query = "SELECT r FROM RiaDtlTbl r WHERE r.benephoneno = :benephoneno"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByBenemessage", query = "SELECT r FROM RiaDtlTbl r WHERE r.benemessage = :benemessage"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByCustid", query = "SELECT r FROM RiaDtlTbl r WHERE r.custid = :custid"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByCustfirstname", query = "SELECT r FROM RiaDtlTbl r WHERE r.custfirstname = :custfirstname"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByCustlastname", query = "SELECT r FROM RiaDtlTbl r WHERE r.custlastname = :custlastname"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByCustlastname2", query = "SELECT r FROM RiaDtlTbl r WHERE r.custlastname2 = :custlastname2"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByCustcountry", query = "SELECT r FROM RiaDtlTbl r WHERE r.custcountry = :custcountry"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByCustid1type", query = "SELECT r FROM RiaDtlTbl r WHERE r.custid1type = :custid1type"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByCustid1no", query = "SELECT r FROM RiaDtlTbl r WHERE r.custid1no = :custid1no"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByCustid1issuedby", query = "SELECT r FROM RiaDtlTbl r WHERE r.custid1issuedby = :custid1issuedby"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByCustid1issuedbystate", query = "SELECT r FROM RiaDtlTbl r WHERE r.custid1issuedbystate = :custid1issuedbystate"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByCustid1issuedbycountry", query = "SELECT r FROM RiaDtlTbl r WHERE r.custid1issuedbycountry = :custid1issuedbycountry"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByCustid1issueddate", query = "SELECT r FROM RiaDtlTbl r WHERE r.custid1issueddate = :custid1issueddate"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByCustid1expirationdate", query = "SELECT r FROM RiaDtlTbl r WHERE r.custid1expirationdate = :custid1expirationdate"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByCustid2type", query = "SELECT r FROM RiaDtlTbl r WHERE r.custid2type = :custid2type"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByCustid2no", query = "SELECT r FROM RiaDtlTbl r WHERE r.custid2no = :custid2no"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByCustid2issuedby", query = "SELECT r FROM RiaDtlTbl r WHERE r.custid2issuedby = :custid2issuedby"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByCustid2issuedbystate", query = "SELECT r FROM RiaDtlTbl r WHERE r.custid2issuedbystate = :custid2issuedbystate"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByCustid2issuedbycountry", query = "SELECT r FROM RiaDtlTbl r WHERE r.custid2issuedbycountry = :custid2issuedbycountry"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByCustid2issueddate", query = "SELECT r FROM RiaDtlTbl r WHERE r.custid2issueddate = :custid2issueddate"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByCustid2expirationdate", query = "SELECT r FROM RiaDtlTbl r WHERE r.custid2expirationdate = :custid2expirationdate"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByCusttaxid", query = "SELECT r FROM RiaDtlTbl r WHERE r.custtaxid = :custtaxid"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByCusttaxcountry", query = "SELECT r FROM RiaDtlTbl r WHERE r.custtaxcountry = :custtaxcountry"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByCustcountryofbirth", query = "SELECT r FROM RiaDtlTbl r WHERE r.custcountryofbirth = :custcountryofbirth"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByCustnationality", query = "SELECT r FROM RiaDtlTbl r WHERE r.custnationality = :custnationality"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByCustdateofbirth", query = "SELECT r FROM RiaDtlTbl r WHERE r.custdateofbirth = :custdateofbirth"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByCustoccupation", query = "SELECT r FROM RiaDtlTbl r WHERE r.custoccupation = :custoccupation"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByCustsourceoffunds", query = "SELECT r FROM RiaDtlTbl r WHERE r.custsourceoffunds = :custsourceoffunds"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByCustpaymentmethod", query = "SELECT r FROM RiaDtlTbl r WHERE r.custpaymentmethod = :custpaymentmethod"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByTransferreason", query = "SELECT r FROM RiaDtlTbl r WHERE r.transferreason = :transferreason"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByBankname", query = "SELECT r FROM RiaDtlTbl r WHERE r.bankname = :bankname"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByBankaccounttype", query = "SELECT r FROM RiaDtlTbl r WHERE r.bankaccounttype = :bankaccounttype"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByBankaccountno", query = "SELECT r FROM RiaDtlTbl r WHERE r.bankaccountno = :bankaccountno"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByBeneidtype", query = "SELECT r FROM RiaDtlTbl r WHERE r.beneidtype = :beneidtype"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByBeneidno", query = "SELECT r FROM RiaDtlTbl r WHERE r.beneidno = :beneidno"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByBenetaxid", query = "SELECT r FROM RiaDtlTbl r WHERE r.benetaxid = :benetaxid"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByBankcity", query = "SELECT r FROM RiaDtlTbl r WHERE r.bankcity = :bankcity"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByBankbranchno", query = "SELECT r FROM RiaDtlTbl r WHERE r.bankbranchno = :bankbranchno"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByBankbranchname", query = "SELECT r FROM RiaDtlTbl r WHERE r.bankbranchname = :bankbranchname"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByBankbranchaddress", query = "SELECT r FROM RiaDtlTbl r WHERE r.bankbranchaddress = :bankbranchaddress"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByBankcode", query = "SELECT r FROM RiaDtlTbl r WHERE r.bankcode = :bankcode"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByBankroutingcode", query = "SELECT r FROM RiaDtlTbl r WHERE r.bankroutingcode = :bankroutingcode"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByBankroutingtype", query = "SELECT r FROM RiaDtlTbl r WHERE r.bankroutingtype = :bankroutingtype"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByBicSwift", query = "SELECT r FROM RiaDtlTbl r WHERE r.bicSwift = :bicSwift"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByUnitarybankaccountno", query = "SELECT r FROM RiaDtlTbl r WHERE r.unitarybankaccountno = :unitarybankaccountno"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByUnitarytype", query = "SELECT r FROM RiaDtlTbl r WHERE r.unitarytype = :unitarytype"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByValuetype", query = "SELECT r FROM RiaDtlTbl r WHERE r.valuetype = :valuetype"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByRequesterrors", query = "SELECT r FROM RiaDtlTbl r WHERE r.requesterrors = :requesterrors"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByReceivedDate", query = "SELECT r FROM RiaDtlTbl r WHERE r.receivedDate = :receivedDate"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByFbnFlg", query = "SELECT r FROM RiaDtlTbl r WHERE r.fbnFlg = :fbnFlg"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByProcessed", query = "SELECT r FROM RiaDtlTbl r WHERE r.processed = :processed and r.failFlg = :failFlg"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByProcessedValidate", query = "SELECT r FROM RiaDtlTbl r WHERE r.processed in ('N','E') and r.failFlg = :failFlg"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByProcessedDate", query = "SELECT r FROM RiaDtlTbl r WHERE r.processedDate = :processedDate"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByPstdFlg", query = "SELECT r FROM RiaDtlTbl r WHERE r.pstdFlg = :pstdFlg"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByPstdDate", query = "SELECT r FROM RiaDtlTbl r WHERE r.pstdDate = :pstdDate"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByTranId", query = "SELECT r FROM RiaDtlTbl r WHERE r.tranId = :tranId"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByFailFlg", query = "SELECT r FROM RiaDtlTbl r WHERE r.failFlg = :failFlg"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByFailReason", query = "SELECT r FROM RiaDtlTbl r WHERE r.failReason = :failReason"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByResponseCode", query = "SELECT r FROM RiaDtlTbl r WHERE r.responseCode = :responseCode"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByResponseMessage", query = "SELECT r FROM RiaDtlTbl r WHERE r.responseMessage = :responseMessage"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByReturnedFlg", query = "SELECT r FROM RiaDtlTbl r WHERE r.returnedFlg = :returnedFlg"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByReturnedStatus", query = "SELECT r FROM RiaDtlTbl r WHERE r.returnedStatus = :returnedStatus"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByPstdFlgFailFlgProcessedFbnFlg", query = "SELECT r FROM RiaDtlTbl r WHERE r.pstdFlg = :pstdFlg and r.failFlg= :failFlg and r.processed = :processed and r.fbnFlg = :fbnFlg"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByBatchId", query = "SELECT r FROM RiaDtlTbl r WHERE r.batchId = :batchId"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByStatus", query = "SELECT r FROM RiaDtlTbl r WHERE r.status = :status"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByRetryCount", query = "SELECT r FROM RiaDtlTbl r WHERE r.retryCount = :retryCount and r.pstdFlg = :pstdFlg and r.responseCode in ('904','906','907','909','911')"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByPayRef", query = "SELECT r FROM RiaDtlTbl r WHERE r.payRef = :payRef"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByNeftReturnPayRef", query = "SELECT r FROM RiaDtlTbl r WHERE r.fbnFlg = :fbnFlg and r.pstdFlg = :pstdFlg and r.returnedFlg = :returnedFlg"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByNeftReceived", query = "SELECT r FROM RiaDtlTbl r WHERE r.fbnFlg = :fbnFlg and r.neftPicked = :neftPicked and r.failFlg = :failFlg and r.processed = :processed"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByFIPReceived", query = "SELECT r FROM RiaDtlTbl r WHERE r.fbnFlg = :fbnFlg and r.failFlg = :failFlg and r.processed = :processed and r.neftPicked = 'N' and r.fippicked in ('P','N')"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByFbnFlgBatchId", query = "SELECT r FROM RiaDtlTbl r WHERE r.fbnFlg = :fbnFlg and r.batchId = :batchId"),
    @javax.persistence.NamedQuery(name = "RiaDtlTbl.findByNeftReturnReverse", query = "SELECT r FROM RiaDtlTbl r WHERE r.fbnFlg = :fbnFlg and r.pstdFlg = :pstdFlg and r.returnedFlg = :returnedFlg and r.reverseResFlg = :reverseResFlg")})
 public class RiaDtlTbl
         implements Serializable  {
     private static final long serialVersionUID = 1L;
        @Id
        @Basic(optional = false)
        @Column(name = "ORDERNO")
     private String orderno;
        @Column(name = "PIN")
     private String pin;
        @Column(name = "PAYINGCORRESPSEQID")
     private String payingcorrespseqid;
        @Column(name = "SALESDATE")
     private String salesdate;
        @Column(name = "SALESTIME")
     private String salestime;
        @Column(name = "COUNTRYFROM")
     private String countryfrom;
        @Column(name = "COUNTRYTO")
     private String countryto;
        @Column(name = "SENDINGCORRESPSEQID")
     private String sendingcorrespseqid;
        @Column(name = "PAYINGCORRESPLOCID")
     private String payingcorresplocid;
        @Column(name = "SENDINGCORRESPBRANCHNO")
     private String sendingcorrespbranchno;
        @Column(name = "BENEQUESTION")
     private String benequestion;
        @Column(name = "BENEANSWER")
     private String beneanswer;
        @Column(name = "PMTINSTRUCTIONS")
     private String pmtinstructions;
        @Column(name = "BENEFICIARYCURRENCY")
     private String beneficiarycurrency;
        @Column(name = "BENEFICIARYAMOUNT")
     private String beneficiaryamount;
        @Column(name = "DELIVERYMETHOD")
     private String deliverymethod;
        @Column(name = "PAYMENTCURRENCY")
     private String paymentcurrency;
        @Column(name = "PAYMENTAMOUNT")
     private String paymentamount;
        @Column(name = "COMMISSIONCURRENCY")
     private String commissioncurrency;
        @Column(name = "COMMISSIONAMOUNT")
     private String commissionamount;
        @Column(name = "CUSTOMERCHARGECURRENCY")
     private String customerchargecurrency;
        @Column(name = "CUSTOMERCHARGEAMOUNT")
     private String customerchargeamount;
        @Column(name = "BENEID")
     private String beneid;
        @Column(name = "BENEFIRSTNAME")
     private String benefirstname;
        @Column(name = "BENELASTNAME")
     private String benelastname;
        @Column(name = "BENELASTNAME2")
     private String benelastname2;
        @Column(name = "BENEADDRESS")
     private String beneaddress;
        @Column(name = "BENECITY")
     private String benecity;
        @Column(name = "BENESTATE")
     private String benestate;
        @Column(name = "BENEZIPCODE")
     private String benezipcode;
        @Column(name = "BENECOUNTRY")
     private String benecountry;
        @Column(name = "BENEPHONENO")
     private String benephoneno;
        @Column(name = "BENEMESSAGE")
     private String benemessage;
        @Column(name = "CUSTID")
     private String custid;
        @Column(name = "CUSTFIRSTNAME")
     private String custfirstname;
        @Column(name = "CUSTLASTNAME")
     private String custlastname;
        @Column(name = "CUSTLASTNAME2")
     private String custlastname2;
        @Column(name = "CUSTCOUNTRY")
     private String custcountry;
        @Column(name = "CUSTID1TYPE")
     private String custid1type;
        @Column(name = "CUSTID1NO")
     private String custid1no;
        @Column(name = "CUSTID1ISSUEDBY")
     private String custid1issuedby;
        @Column(name = "CUSTID1ISSUEDBYSTATE")
     private String custid1issuedbystate;
        @Column(name = "CUSTID1ISSUEDBYCOUNTRY")
     private String custid1issuedbycountry;
        @Column(name = "CUSTID1ISSUEDDATE")
     private String custid1issueddate;
        @Column(name = "CUSTID1EXPIRATIONDATE")
     private String custid1expirationdate;
        @Column(name = "CUSTID2TYPE")
     private String custid2type;
        @Column(name = "CUSTID2NO")
     private String custid2no;
        @Column(name = "CUSTID2ISSUEDBY")
     private String custid2issuedby;
        @Column(name = "CUSTID2ISSUEDBYSTATE")
     private String custid2issuedbystate;
        @Column(name = "CUSTID2ISSUEDBYCOUNTRY")
     private String custid2issuedbycountry;
        @Column(name = "CUSTID2ISSUEDDATE")
     private String custid2issueddate;
        @Column(name = "CUSTID2EXPIRATIONDATE")
     private String custid2expirationdate;
        @Column(name = "CUSTTAXID")
     private String custtaxid;
        @Column(name = "CUSTTAXCOUNTRY")
     private String custtaxcountry;
        @Column(name = "CUSTCOUNTRYOFBIRTH")
     private String custcountryofbirth;
        @Column(name = "CUSTNATIONALITY")
     private String custnationality;
        @Column(name = "CUSTDATEOFBIRTH")
     private String custdateofbirth;
        @Column(name = "CUSTOCCUPATION")
     private String custoccupation;
        @Column(name = "CUSTSOURCEOFFUNDS")
     private String custsourceoffunds;
        @Column(name = "CUSTPAYMENTMETHOD")
     private String custpaymentmethod;
        @Column(name = "TRANSFERREASON")
     private String transferreason;
        @Column(name = "BANKNAME")
     private String bankname;
        @Column(name = "BANKACCOUNTTYPE")
     private String bankaccounttype;
        @Column(name = "BANKACCOUNTNO")
     private String bankaccountno;
        @Column(name = "BENEIDTYPE")
     private String beneidtype;
        @Column(name = "BENEIDNO")
     private String beneidno;
        @Column(name = "BENETAXID")
     private String benetaxid;
        @Column(name = "BANKCITY")
     private String bankcity;
        @Column(name = "BANKBRANCHNO")
     private String bankbranchno;
        @Column(name = "BANKBRANCHNAME")
     private String bankbranchname;
        @Column(name = "BANKBRANCHADDRESS")
     private String bankbranchaddress;
        @Column(name = "BANKCODE")
     private String bankcode;
        @Column(name = "BANKROUTINGCODE")
     private String bankroutingcode;
        @Column(name = "BANKROUTINGTYPE")
     private String bankroutingtype;
        @Column(name = "BIC_SWIFT")
     private String bicSwift;
        @Column(name = "UNITARYBANKACCOUNTNO")
     private String unitarybankaccountno;
        @Column(name = "UNITARYTYPE")
     private String unitarytype;
        @Column(name = "VALUETYPE")
     private String valuetype;
        @Column(name = "REQUESTERRORS")
     private String requesterrors;
        @Column(name = "RECEIVED_DATE")
        @Temporal(TemporalType.TIMESTAMP)
     private Date receivedDate;
        @Column(name = "FBN_FLG")
     private Character fbnFlg;
        @Column(name = "PROCESSED")
     private Character processed;
        @Column(name = "PROCESSED_DATE")
        @Temporal(TemporalType.TIMESTAMP)
     private Date processedDate;
        @Column(name = "PSTD_FLG")
     private Character pstdFlg;
        @Column(name = "PSTD_DATE")
        @Temporal(TemporalType.TIMESTAMP)
     private Date pstdDate;
        @Column(name = "TRAN_ID")
     private String tranId;
        @Column(name = "FAIL_FLG")
     private Character failFlg;
        @Column(name = "FAIL_REASON")
     private String failReason;
        @Column(name = "RESPONSE_CODE")
     private String responseCode;
        @Column(name = "RESPONSE_MESSAGE")
     private String responseMessage;
        @Column(name = "RETURNED_FLG")
     private Character returnedFlg;
        @Column(name = "RETURNED_STATUS")
     private String returnedStatus;
        @Column(name = "BATCH_ID")
     private String batchId;
        @Column(name = "STATUS")
     private String status;
        @Column(name = "RETRYCOUNT")
     private String retryCount;
        @Column(name = "PAY_REF")
     private String payRef;
        @Column(name = "CRE_TIME")
        @Temporal(TemporalType.TIMESTAMP)
     private Date creTime;
        @Column(name = "NEFT_PICKED")
     private String neftPicked;
        @Column(name = "UPDATE_TIME")
        @Temporal(TemporalType.TIMESTAMP)
     private Date updateTime;
        @Column(name = "BATCH_SERIAL_NUM")
     private long batchSerialNum;
        @Column(name = "REVERSE_DATE")
        @Temporal(TemporalType.TIMESTAMP)
     private Date reverseDate;
        @Column(name = "FINAL_REVERSE_DATE")
        @Temporal(TemporalType.TIMESTAMP)
     private Date finalReverseDate;
        @Column(name = "REVERSE_RES_CODE")
     private String reverseResCode;
         @Column(name = "NEFT_NAPS_PICKED")
     private String neftornapsPicked;
          @Column(name = "NAME_ENQUIRY_TIMES")
     private int nameEnquiryTimes;
           @Column(name = "FIP_SESSION_ID")
    private String fipSessionID;
          @Column(name = "FIP_PICKED")
    private String fippicked;

    public void setBankroutingtype(String bankroutingtype) {
        this.bankroutingtype = bankroutingtype;
    }

    public void setReverseResCode(String reverseResCode) {
        this.reverseResCode = reverseResCode;
    }
        @Column(name = "REVERSE_RES_FLAG")
     private Character reverseResFlg;
        @Column(name = "REVERSE_AMOUNT")
     private String reverseAmount;
        @Column(name = "SUSPENSE_POST_DATE")
        @Temporal(TemporalType.TIMESTAMP)
     private Date suspensepostDate;
        @Column(name = "TRANS_DATE")
        @Temporal(TemporalType.TIMESTAMP)
     private Date transDate;
    
     public RiaDtlTbl() {
    }
    
     public Date getUpdateTime()  {
         return this.updateTime;
            }
    
     public void setUpdateTime(Date updateTime)  {
         this.updateTime = updateTime;
            }
    
     public RiaDtlTbl(String orderno) {
         this.orderno = orderno;
            }
    
     public String getOrderno()  {
         return this.orderno;
            }
    
     public void setOrderno(String orderno)  {
         this.orderno = orderno;
            }
    
     public String getPin()  {
         return this.pin;
            }
    
     public void setPin(String pin){
         this.pin = pin;
            }
    
     public String getPayingcorrespseqid() {
         return this.payingcorrespseqid;
            }
    
     public void setPayingcorrespseqid(String payingcorrespseqid) {
         this.payingcorrespseqid = payingcorrespseqid;
            }
    
     public String getSalesdate()  {
         return this.salesdate;
            }
    
     public void setSalesdate(String salesdate) {
         this.salesdate = salesdate;
            }
    
     public String getSalestime()  {
         return this.salestime;
            }
    
     public void setSalestime(String salestime) {
         this.salestime = salestime;
            }
    
     public String getCountryfrom()  {
         return this.countryfrom;
            }
    
     public void setCountryfrom(String countryfrom)  {
         this.countryfrom = countryfrom;
            }
    
     public String getCountryto() {
         return this.countryto;
            }
    
     public void setCountryto(String countryto)  {
         this.countryto = countryto;
            }
    
     public String getSendingcorrespseqid() {
         return this.sendingcorrespseqid;
            }
    
     public void setSendingcorrespseqid(String sendingcorrespseqid)  {
         this.sendingcorrespseqid = sendingcorrespseqid;
            }
    
     public String getPayingcorresplocid() {
         return this.payingcorresplocid;
            }
    
     public void setPayingcorresplocid(String payingcorresplocid)  {
         this.payingcorresplocid = payingcorresplocid;
            }
    
     public String getSendingcorrespbranchno() {
         return this.sendingcorrespbranchno;
            }
    
     public void setSendingcorrespbranchno(String sendingcorrespbranchno)  {
         this.sendingcorrespbranchno = sendingcorrespbranchno;
            }
    
     public String getBenequestion() {
         return this.benequestion;
            }
    
     public void setBenequestion(String benequestion)  {
         this.benequestion = benequestion;
            }
    
     public String getBeneanswer()  {
         return this.beneanswer;
            }
    
     public void setBeneanswer(String beneanswer)  {
         this.beneanswer = beneanswer;
            }
    
     public String getPmtinstructions() {
         return this.pmtinstructions;
            }
    
     public void setPmtinstructions(String pmtinstructions)  {
         this.pmtinstructions = pmtinstructions;
            }
    
     public String getBeneficiarycurrency()  {
         return this.beneficiarycurrency;
            }
    
     public void setBeneficiarycurrency(String beneficiarycurrency)  {
         this.beneficiarycurrency = beneficiarycurrency;
            }
    
     public String getBeneficiaryamount()  {
         return this.beneficiaryamount;
            }
    
     public void setBeneficiaryamount(String beneficiaryamount) {
         this.beneficiaryamount = beneficiaryamount;
            }
    
     public String getDeliverymethod()  {
         return this.deliverymethod;
            }
    
     public void setDeliverymethod(String deliverymethod)  {
         this.deliverymethod = deliverymethod;
            }
    
     public String getPaymentcurrency() {
         return this.paymentcurrency;
            }
    
     public void setPaymentcurrency(String paymentcurrency)  {
         this.paymentcurrency = paymentcurrency;
            }
    
     public String getPaymentamount(){
         return this.paymentamount;
            }
    
     public void setPaymentamount(String paymentamount)  {
         this.paymentamount = paymentamount;
            }
    
     public String getCommissioncurrency()  {
         return this.commissioncurrency;
            }
    
     public void setCommissioncurrency(String commissioncurrency) {
         this.commissioncurrency = commissioncurrency;
            }
    
     public String getCommissionamount()  {
         return this.commissionamount;
            }
    
     public void setCommissionamount(String commissionamount)  {
         this.commissionamount = commissionamount;
            }
    
     public String getCustomerchargecurrency(){
         return this.customerchargecurrency;
            }
    
     public void setCustomerchargecurrency(String customerchargecurrency)  {
         this.customerchargecurrency = customerchargecurrency;
            }
    
     public String getCustomerchargeamount(){
         return this.customerchargeamount;
            }
    
     public void setCustomerchargeamount(String customerchargeamount)  {
         this.customerchargeamount = customerchargeamount;
            }
    
     public String getBeneid()  {
         return this.beneid;
            }
    
     public void setBeneid(String beneid)  {
         this.beneid = beneid;
            }
    
     public String getBenefirstname() {
         return this.benefirstname;
            }
    
     public void setBenefirstname(String benefirstname) {
         this.benefirstname = benefirstname;
            }
    
     public String getBenelastname()  {
         return this.benelastname;
            }
    
     public void setBenelastname(String benelastname)  {
         this.benelastname = benelastname;
            }
    
     public String getBenelastname2()  {
         return this.benelastname2;
            }
    
     public void setBenelastname2(String benelastname2)  {
         this.benelastname2 = benelastname2;
            }
    
     public String getBeneaddress()  {
         return this.beneaddress;
            }
    
     public void setBeneaddress(String beneaddress) {
         this.beneaddress = beneaddress;
            }
    
     public String getBenecity() {
         return this.benecity;
            }
    
     public void setBenecity(String benecity)  {
         this.benecity = benecity;
            }
    
     public String getBenestate()  {
         return this.benestate;
            }
    
     public void setBenestate(String benestate){
         this.benestate = benestate;
            }
    
     public String getBenezipcode()  {
         return this.benezipcode;
            }
    
     public void setBenezipcode(String benezipcode) {
         this.benezipcode = benezipcode;
            }
    
     public String getBenecountry() {
         return this.benecountry;
            }
    
     public void setBenecountry(String benecountry)  {
         this.benecountry = benecountry;
            }
    
     public String getBenephoneno()  {
         return this.benephoneno;
            }
    
     public void setBenephoneno(String benephoneno)  {
         this.benephoneno = benephoneno;
            }
    
     public String getBenemessage() {
         return this.benemessage;
            }
    
     public void setBenemessage(String benemessage)  {
         this.benemessage = benemessage;
            }
    
     public String getCustid()  {
         return this.custid;
            }
    
     public void setCustid(String custid)  {
         this.custid = custid;
            }
    
     public String getCustfirstname(){
         return this.custfirstname;
            }
    
     public void setCustfirstname(String custfirstname) {
         this.custfirstname = custfirstname;
            }
    
     public String getCustlastname()  {
         return this.custlastname;
            }
    
     public void setCustlastname(String custlastname) {
         this.custlastname = custlastname;
            }
    
     public String getCustlastname2()  {
         return this.custlastname2;
            }
    
     public void setCustlastname2(String custlastname2) {
         this.custlastname2 = custlastname2;
            }
    
     public String getCustcountry()  {
         return this.custcountry;
            }
    
     public void setCustcountry(String custcountry)  {
         this.custcountry = custcountry;
            }
    
     public String getCustid1type()  {
         return this.custid1type;
            }
    
     public void setCustid1type(String custid1type) {
         this.custid1type = custid1type;
            }
    
     public String getCustid1no()  {
         return this.custid1no;
            }
    
     public void setCustid1no(String custid1no)  {
         this.custid1no = custid1no;
            }
    
     public String getCustid1issuedby()  {
         return this.custid1issuedby;
            }
    
     public void setCustid1issuedby(String custid1issuedby)  {
         this.custid1issuedby = custid1issuedby;
            }
    
     public String getCustid1issuedbystate(){
         return this.custid1issuedbystate;
            }
    
     public void setCustid1issuedbystate(String custid1issuedbystate)  {
         this.custid1issuedbystate = custid1issuedbystate;
            }
    
     public String getCustid1issuedbycountry()  {
         return this.custid1issuedbycountry;
            }
    
     public void setCustid1issuedbycountry(String custid1issuedbycountry)  {
         this.custid1issuedbycountry = custid1issuedbycountry;
            }
    
     public String getCustid1issueddate()  {
         return this.custid1issueddate;
            }
    
     public void setCustid1issueddate(String custid1issueddate) {
         this.custid1issueddate = custid1issueddate;
            }
    
     public String getCustid1expirationdate()  {
         return this.custid1expirationdate;
            }
    
     public void setCustid1expirationdate(String custid1expirationdate)  {
         this.custid1expirationdate = custid1expirationdate;
            }
    
     public String getCustid2type()  {
         return this.custid2type;
            }
    
     public void setCustid2type(String custid2type)  {
         this.custid2type = custid2type;
            }
    
     public String getCustid2no()  {
         return this.custid2no;
            }
    
     public void setCustid2no(String custid2no)  {
         this.custid2no = custid2no;
            }
    
     public String getCustid2issuedby()  {
         return this.custid2issuedby;
            }
    
     public void setCustid2issuedby(String custid2issuedby)  {
         this.custid2issuedby = custid2issuedby;
            }
    
     public String getCustid2issuedbystate()  {
         return this.custid2issuedbystate;
            }
    
     public void setCustid2issuedbystate(String custid2issuedbystate)  {
         this.custid2issuedbystate = custid2issuedbystate;
            }
    
     public String getCustid2issuedbycountry()  {
         return this.custid2issuedbycountry;
            }
    
     public void setCustid2issuedbycountry(String custid2issuedbycountry)  {
         this.custid2issuedbycountry = custid2issuedbycountry;
            }
    
     public String getCustid2issueddate() {
         return this.custid2issueddate;
            }
    
     public void setCustid2issueddate(String custid2issueddate) {
         this.custid2issueddate = custid2issueddate;
            }
    
     public String getCustid2expirationdate() {
         return this.custid2expirationdate;
            }
    
     public void setCustid2expirationdate(String custid2expirationdate)  {
         this.custid2expirationdate = custid2expirationdate;
            }
    
     public String getCusttaxid()  {
         return this.custtaxid;
            }
    
     public void setCusttaxid(String custtaxid)  {
         this.custtaxid = custtaxid;
            }
    
     public String getCusttaxcountry()  {
         return this.custtaxcountry;
            }
    
     public void setCusttaxcountry(String custtaxcountry)  {
         this.custtaxcountry = custtaxcountry;
            }
    
     public String getCustcountryofbirth()  {
         return this.custcountryofbirth;
            }
    
     public void setCustcountryofbirth(String custcountryofbirth)  {
         this.custcountryofbirth = custcountryofbirth;
            }
    
     public String getCustnationality()  {
         return this.custnationality;
            }
    
     public void setCustnationality(String custnationality){
         this.custnationality = custnationality;
            }
    
     public String getCustdateofbirth() {
         return this.custdateofbirth;
            }
    
     public void setCustdateofbirth(String custdateofbirth) {
         this.custdateofbirth = custdateofbirth;
            }
    
     public String getCustoccupation()  {
         return this.custoccupation;
            }
    
     public void setCustoccupation(String custoccupation) {
         this.custoccupation = custoccupation;
            }
    
     public String getCustsourceoffunds()  {
         return this.custsourceoffunds;
            }
    
     public void setCustsourceoffunds(String custsourceoffunds)  {
         this.custsourceoffunds = custsourceoffunds;
            }
    
     public String getCustpaymentmethod()  {
         return this.custpaymentmethod;
            }
    
     public void setCustpaymentmethod(String custpaymentmethod)  {
         this.custpaymentmethod = custpaymentmethod;
            }
    
     public String getTransferreason()  {
         return this.transferreason;
            }
    
     public void setTransferreason(String transferreason)  {
         this.transferreason = transferreason;
            }
    
     public String getBankname() {
         return this.bankname;
            }
    
     public void setBankname(String bankname)  {
         this.bankname = bankname;
            }
    
     public String getBankaccounttype()  {
         return this.bankaccounttype;
            }
    
     public void setBankaccounttype(String bankaccounttype)  {
         this.bankaccounttype = bankaccounttype;
            }
    
     public String getBankaccountno()  {
         return this.bankaccountno;
            }
    
     public void setBankaccountno(String bankaccountno)  {
         this.bankaccountno = bankaccountno;
            }
    
     public String getBeneidtype()  {
         return this.beneidtype;
            }
    
     public void setBeneidtype(String beneidtype)  {
         this.beneidtype = beneidtype;
            }
    
     public String getBeneidno()  {
         return this.beneidno;
            }
    
     public void setBeneidno(String beneidno)  {
         this.beneidno = beneidno;
            }
    
     public String getBenetaxid()  {
         return this.benetaxid;
            }
    
     public void setBenetaxid(String benetaxid)  {
         this.benetaxid = benetaxid;
            }
    
     public String getBankcity()  {
         return this.bankcity;
            }
    
     public void setBankcity(String bankcity) {
         this.bankcity = bankcity;
            }
    
     public String getBankbranchno()  {
         return this.bankbranchno;
            }
    
     public void setBankbranchno(String bankbranchno)  {
         this.bankbranchno = bankbranchno;
            }
    
     public String getBankbranchname()  {
         return this.bankbranchname;
            }
    
     public void setBankbranchname(String bankbranchname) {
         this.bankbranchname = bankbranchname;
            }
    
     public String getBankbranchaddress()  {
         return this.bankbranchaddress;
            }
    
     public void setBankbranchaddress(String bankbranchaddress)  {
         this.bankbranchaddress = bankbranchaddress;
            }
    
     public String getBankcode() {
         return this.bankcode;
            }
    
     public void setBankcode(String bankcode)  {
         this.bankcode = bankcode;
            }
    
     public String getBankroutingcode() {
         return this.bankroutingcode;
            }
    
     public void setBankroutingcode(String bankroutingcode) {
         this.bankroutingcode = bankroutingcode;
            }
    
     public String getBankroutingtype()  {
         return this.bankroutingtype;
            }
    
    
     public String getBicSwift()  {
         return this.bicSwift;
            }
    
     public void setBicSwift(String bicSwift) {
         this.bicSwift = bicSwift;
            }
    
     public String getUnitarybankaccountno()  {
         return this.unitarybankaccountno;
            }
    
     public void setUnitarybankaccountno(String unitarybankaccountno)  {
         this.unitarybankaccountno = unitarybankaccountno;
            }
    
     public String getUnitarytype() {
         return this.unitarytype;
            }
    
     public void setUnitarytype(String unitarytype){
         this.unitarytype = unitarytype;
            }
    
     public String getValuetype() {
         return this.valuetype;
            }
    
     public void setValuetype(String valuetype)  {
         this.valuetype = valuetype;
            }
    
     public String getRequesterrors() {
         return this.requesterrors;
            }
    
     public void setRequesterrors(String requesterrors)  {
         this.requesterrors = requesterrors;
            }
    
     public Date getReceivedDate()  {
         return this.receivedDate;
            }
    
     public void setReceivedDate(Date receivedDate)  {
         this.receivedDate = receivedDate;
            }
    
     public Character getFbnFlg()  {
         return this.fbnFlg;
            }
    
     public void setFbnFlg(Character fbnFlg)  {
         this.fbnFlg = fbnFlg;
            }
    
     public Character getProcessed()  {
         return this.processed;
            }
    
     public void setProcessed(Character processed)  {
         this.processed = processed;
            }
    
     public Date getProcessedDate()  {
         return this.processedDate;
            }
    
     public void setProcessedDate(Date processedDate) {
         this.processedDate = processedDate;
            }
    
     public Character getPstdFlg()  {
         return this.pstdFlg;
            }
    
     public void setPstdFlg(Character pstdFlg)  {
         this.pstdFlg = pstdFlg;
            }
    
     public Date getPstdDate()  {
         return this.pstdDate;
            }
    
     public void setPstdDate(Date pstdDate) {
         this.pstdDate = pstdDate;
            }
    
     public String getTranId()  {
         return this.tranId;
            }
    
     public void setTranId(String tranId)  {
         this.tranId = tranId;
            }
    
     public Character getFailFlg() {
         return this.failFlg;
            }
    
     public void setFailFlg(Character failFlg){
         this.failFlg = failFlg;
            }
    
     public String getFailReason()  {
         return this.failReason;
            }
    
     public void setFailReason(String failReason)  {
         this.failReason = failReason;
            }
    
     public String getResponseCode()  {
         return this.responseCode;
            }
    
     public void setResponseCode(String responseCode) {
         this.responseCode = responseCode;
            }
    
     public String getResponseMessage() {
         return this.responseMessage;
            }
    
     public void setResponseMessage(String responseMessage)  {
         this.responseMessage = responseMessage;
            }
    
     public Character getReturnedFlg() {
         return this.returnedFlg;
            }
    
     public void setReturnedFlg(Character returnedFlg)  {
         this.returnedFlg = returnedFlg;
            }
    
     public String getReturnedStatus()  {
         return this.returnedStatus;
            }
    
     public void setReturnedStatus(String returnedStatus)  {
         this.returnedStatus = returnedStatus;
            }
    
     public String getBatchId()  {
         return this.batchId;
            }
    
     public void setBatchId(String batchId)  {
         this.batchId = batchId;
            }
    
     public String getStatus()  {
         return this.status;
            }
    
     public void setStatus(String status) {
         this.status = status;
            }
    
     public String getRetryCount()  {
         return this.retryCount;
            }
    
     public void setRetryCount(String retryCount)  {
         this.retryCount = retryCount;
            }
    
     public String getPayRef()  {
         return this.payRef;
            }
    
     public void setPayRef(String payRef)  {
         this.payRef = payRef;
            }
    
     public Date getCreTime()  {
         return this.creTime;
            }
    
     public void setCreTime(Date creTime)  {
         this.creTime = creTime;
            }
    
     public String getNeftPicked()  {
         return this.neftPicked;
            }
    
     public void setNeftPicked(String neftPicked)  {
         this.neftPicked = neftPicked;
            }
    
     public long getBatchSerialNum()  {
         return this.batchSerialNum;
            }
    
     public void setBatchSerialNum(long batchSerialNum)  {
         this.batchSerialNum = batchSerialNum;
            }
    
     public Date getReverseDate()  {
         return this.reverseDate;
            }
    
     public void setReverseDate(Date reverseDate)  {
         this.reverseDate = reverseDate;
            }
    
     public Date getFinalReverseDate()  {
         return this.finalReverseDate;
            }
    
     public void setFinalReverseDate(Date finalReverseDate) {
         this.finalReverseDate = finalReverseDate;
            }
    
     public String getReverseResCode()  {
         return this.reverseResCode;
            }
    
     {
         this.reverseResCode = reverseResCode;
            }
    
     public Character getReverseResFlg()  {
         return this.reverseResFlg;
            }
    
     public void setReverseResFlg(Character reverseResFlg)  {
         this.reverseResFlg = reverseResFlg;
            }
    
     public String getReverseAmount()  {
         return this.reverseAmount;
            }
    
     public void setReverseAmount(String reverseAmount)  {
         this.reverseAmount = reverseAmount;
            }
    
     public Date getSuspensepostDate()  {
         return this.suspensepostDate;
            }
    
     public void setSuspensepostDate(Date suspensepostDate){
         this.suspensepostDate = suspensepostDate;
            }
    
     public Date getTransDate()  {
         return this.transDate;
            }
    
     public void setTransDate(Date transDate)  {
         this.transDate = transDate;
            }

    public String getNeftornapsPicked() {
        return neftornapsPicked;
    }

    public void setNeftornapsPicked(String neftornapsPicked) {
        this.neftornapsPicked = neftornapsPicked;
    }

    public int getNameEnquiryTimes() {
        return nameEnquiryTimes;
    }

    public void setNameEnquiryTimes(int nameEnquiryTimes) {
        this.nameEnquiryTimes = nameEnquiryTimes;
    }

    public String getFipSessionID() {
        return fipSessionID;
    }

    public void setFipSessionID(String fipSessionID) {
        this.fipSessionID = fipSessionID;
    }

    public String getFippicked() {
        return fippicked;
    }

    public void setFippicked(String fippicked) {
        this.fippicked = fippicked;
    }
       
     public int hashCode()  {
         int hash = 0;
         hash += (this.orderno != null ? this.orderno.hashCode() : 0);
         return hash;
            }
    
     public boolean equals(Object object)  {
         if (!(object instanceof RiaDtlTbl)) {
             return false;
                    }
         RiaDtlTbl other = (RiaDtlTbl) object;
         if (((this.orderno == null) && (other.orderno != null)) || ((this.orderno != null) && (!this.orderno.equals(other.orderno)))) {
             return false;
                    }
         return true;
            }
    
     public String toString()  {
         return "com.db.jpa.RiaDtlTbl[ orderno=" + this.orderno + " ]";
            }
     }
