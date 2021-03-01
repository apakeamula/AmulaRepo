 package com.fbn.db.jpa;

 import java.io.Serializable;
 import java.math.BigDecimal;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.Id;
 import javax.persistence.IdClass;
 import javax.persistence.NamedQueries;
 import javax.persistence.Table;
 import javax.xml.bind.annotation.XmlRootElement;

 @Entity
 @Table(name = "GIRO_NAPS_TABLE", catalog = "", schema = "CUSTOM")
 @IdClass(giroEmbed.class)
 @XmlRootElement
 @NamedQueries({
    @javax.persistence.NamedQuery(name = "GiroTblNaps.findOne", query = "SELECT r FROM GiroTblNaps r where r.payref = :payref and r.serialnum = :serialnum")})
 public class GiroTblNaps
         implements Serializable  {
     @Id
        @Column(name = "PAY_REF")
     private String payref;
        @Id
        @Column(name = "CUSTOM_NARR")
     private String customnarr;
        @Column(name = "BENF_NAME")
     private String benfname;
        @Column(name = "BENF_BK_NAME")
     private String benfbkname;
        @Column(name = "BENF_BK_SORT")
     private String benfbksort;
        @Column(name = "BENF_BR_NAME")
     private String benfbrname;
        @Column(name = "BK_BR_SORTCODE")
     private String bkbrsortcode;
        @Column(name = "BENF_FORACID")
     private String benfforacid;
        @Column(name = "INIT_SORT")
     private String initsort;
        @Column(name = "OWNER_NAME")
     private String ownername;
        @Column(name = "OWNER_FORACID")
     private String ownerforacid;
        @Column(name = "AMOUNT")
     private BigDecimal amount;
        @Column(name = "CAPTURE_DATE")
     private String capturedate;
        @Column(name = "TRAN_CODE")
     private String trancode;
        @Column(name = "TRAN_TYPE")
     private String trantype;
        @Column(name = "REVERSAL_FLG")
     private String reversalflg;
        @Column(name = "POSTED_FLG")
     private String postedflg;
        @Column(name = "TRANSMIT_FLG")
     private String transmitflg;
        @Column(name = "ENTD_USER")
     private String entduser;
        @Column(name = "TRAN_ID")
     private String tranid;
        @Column(name = "PSTD_USER")
     private String pstduser;
        @Column(name = "TRANS_DATE")
     private String transdate;
        @Column(name = "CLEAR_SESS")
     private String clearsess;
        @Column(name = "TTIME")
     private String ttime;
        @Column(name = "RETURN_FLG")
     private String returnflg;
        @Column(name = "TRAN_DETAIL")
     private String trandetail;
        @Column(name = "SERIAL_NUM")
     private long serialnum;
        @Column(name = "GENERATE_DATE")
     private String generatedate;
        @Column(name = "GENERATE_SESS")
     private String generatesess;
        @Column(name = "CHEQUE_NUM")
     private String chequenum;
        @Column(name = "DEL_FLG")
     private String delflg;
        @Column(name = "FINACCTNAME")
     private String finacctname;
        @Column(name = "BATCHID")
     private String batchid;
        @Column(name = "BANK_ID")
     private String bankid;
        @Column(name = "TRANSACTION_STATUS")
     private String transactionStatus;
        @Column(name = "TRAN_UNIQUE_ID")
     private String transactionUniqueId;
        @Column(name = "BRANCH_SOL_ID")
     private String branchSolId;
    
     public String getBenfname() {
         return this.benfname;
            }
    
     public void setBenfname(String benfname)  {
         this.benfname = benfname;
            }
    
     public String getBenfbkname()  {
         return this.benfbkname;
            }
    
     public void setBenfbkname(String benfbkname) {
         this.benfbkname = benfbkname;
            }
    
     public String getBenfbksort()  {
         return this.benfbksort;
            }
    
     public void setBenfbksort(String benfbksort)  {
         this.benfbksort = benfbksort;
            }
    
     public String getBenfbrname()  {
         return this.benfbrname;
            }
    
     public void setBenfbrname(String benfbrname) {
         this.benfbrname = benfbrname;
            }
    
     public String getBkbrsortcode()  {
         return this.bkbrsortcode;
            }
    
    public void setBkbrsortcode(String bkbrsortcode)  {
         this.bkbrsortcode = bkbrsortcode;
            }
    
    public String getBenfforacid()  {
         return this.benfforacid;
            }
    
    public void setBenfforacid(String benfforacid)  {
         this.benfforacid = benfforacid;
            }
    
    public String getInitsort() {
         return this.initsort;
            }
    
    public void setInitsort(String initsort) {
         this.initsort = initsort;
            }
    
    public String getOwnername() {
         return this.ownername;
            }
    
  public void setOwnername(String ownername)  {
         this.ownername = ownername;
            }
    
    public String getOwnerforacid()  {
         return this.ownerforacid;
            }
    
     public void setOwnerforacid(String ownerforacid) {
         this.ownerforacid = ownerforacid;
            }
    
     public BigDecimal getAmount()  {
         return this.amount;
            }
    
     public void setAmount(BigDecimal amount)  {
         this.amount = amount;
            }
    
     public String getPayref()  {
         return this.payref;
            }
    
     public void setPayref(String payref)  {
         this.payref = payref;
            }
    
     public String getCapturedate()  {
         return this.capturedate;
            }
    
     public void setCapturedate(String capturedate)  {
         this.capturedate = capturedate;
            }
    
     public String getTrancode(){
         return this.trancode;
            }
    
     public void setTrancode(String trancode)  {
         this.trancode = trancode;
            }
    
     public String getTrantype() {
         return this.trantype;
            }
    
     public void setTrantype(String trantype)  {
         this.trantype = trantype;
            }
    
     public String getReversalflg(){
         return this.reversalflg;
            }
    
     public void setReversalflg(String reversalflg)  {
         this.reversalflg = reversalflg;
            }
    
     public String getPostedflg()  {
         return this.postedflg;
            }
    
     public void setPostedflg(String postedflg)  {
         this.postedflg = postedflg;
            }
    
     public String getTransmitflg()  {
         return this.transmitflg;
            }
    
     public void setTransmitflg(String transmitflg) {
         this.transmitflg = transmitflg;
            }
    
     public String getEntduser()  {
         return this.entduser;
            }
    
     public void setEntduser(String entduser)  {
         this.entduser = entduser;
            }
    
     public String getTranid() {
         return this.tranid;
            }
    
     public void setTranid(String tranid)  {
         this.tranid = tranid;
            }
    
     public String getPstduser()  {
         return this.pstduser;
            }
    
     public void setPstduser(String pstduser) {
         this.pstduser = pstduser;
            }
    
     public String getTransdate()  {
         return this.transdate;
            }
    
     public void setTransdate(String transdate) {
         this.transdate = transdate;
            }
    
     public String getClearsess()  {
         return this.clearsess;
            }
    
     public void setClearsess(String clearsess)  {
         this.clearsess = clearsess;
            }
    
     public String getTtime()  {
         return this.ttime;
            }
    
     public void setTtime(String ttime) {
         this.ttime = ttime;
            }
    
     public String getReturnflg() {
         return this.returnflg;
            }
    
     public void setReturnflg(String returnflg) {
         this.returnflg = returnflg;
            }
    
     public String getTrandetail()  {
         return this.trandetail;
            }
    
     public void setTrandetail(String trandetail)  {
         this.trandetail = trandetail;
            }
    
     public long getSerialnum()  {
         return this.serialnum;
            }
    
     public void setSerialnum(long serialnum)  {
         this.serialnum = serialnum;
            }
    
     public String getGeneratedate()  {
         return this.generatedate;
            }
    
     public void setGeneratedate(String generatedate) {
         this.generatedate = generatedate;
            }
    
     public String getGeneratesess()  {
         return this.generatesess;
            }
    
     public void setGeneratesess(String generatesess)  {
         this.generatesess = generatesess;
            }
    
     public String getChequenum()  {
         return this.chequenum;
            }
    
     public void setChequenum(String chequenum) {
         this.chequenum = chequenum;
            }
    
     public String getDelflg() {
         return this.delflg;
            }
    
     public void setDelflg(String delflg)  {
         this.delflg = delflg;
            }
    
     public String getCustomnarr()  {
         return this.customnarr;
            }
    
     public void setCustomnarr(String customnarr)  {
         this.customnarr = customnarr;
            }
    
     public String getFinacctname()  {
         return this.finacctname;
            }
    
     public void setFinacctname(String finacctname) {
         this.finacctname = finacctname;
            }
    
     public String getBatchid()  {
         return this.batchid;
            }
    
     public void setBatchid(String batchid)  {
         this.batchid = batchid;
            }
    
     public String getBankid() {
         return this.bankid;
            }
    
     public void setBankid(String bankid){
         this.bankid = bankid;
            }
    
     public String getTransactionStatus() {
         return this.transactionStatus;
            }
    
     public void setTransactionStatus(String transactionStatus)  {
         this.transactionStatus = transactionStatus;
            }
    
     public String getTransactionUniqueId() {
         return this.transactionUniqueId;
            }
    
     public void setTransactionUniqueId(String transactionUniqueId)  {
         this.transactionUniqueId = transactionUniqueId;
            }
    
     public String getBranchSolId()  {
         return this.branchSolId;
            }
    
     public void setBranchSolId(String branchSolId) {
         this.branchSolId = branchSolId;
            }
     }

