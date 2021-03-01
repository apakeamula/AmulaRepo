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
@Table(name = "AMOUNT_TABLE_CODE_TABLE", catalog = "", schema = "TBAADM")
@XmlRootElement
@NamedQueries({
    @javax.persistence.NamedQuery(name = "AtcTbl.findCommissionAmt", query = "SELECT r FROM AtcTbl r where r.amttblcode = :amttblcode and :tramAmount between r.startamount and r.endamount and r.delflg =:delflg"),
    @javax.persistence.NamedQuery(name = "AtcTbl.findVatPercent", query = "SELECT r FROM AtcTbl r where r.amttblcode = :amttblcode and r.delflg =:delflg")})
public class AtcTbl
        implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "AMT_TBL_CODE")
    private String amttblcode;
    @Column(name = "AMT_TBL_CODE_SRL_NUM")
    private String amttblcodesrlnum;
    @Column(name = "CRNCY_CODE")
    private String crncycode;
    @Column(name = "ENTITY_CRE_FLG")
    private char entitycreflg;
    @Column(name = "DEL_FLG")
    private char delflg;
    @Column(name = "START_AMT")
    private String startamount;
    @Column(name = "END_AMT")
    private String endamount;
    @Column(name = "MIN_AMT")
    private String minamount;
    @Column(name = "PCNT_AMT")
    private String pcntamount;
    @Column(name = "MAX_AMT")
    private String maxamount;
    @Column(name = "INPUT_RND_FLG")
    private char inputrndflg;
    @Column(name = "INPUT_RND_AMT")
    private String inputrndamt;
    @Column(name = "OUTPUT_RND_FLG")
    private char outputrndflg;
    @Column(name = "OUTPUT_RND_AMT")
    private String outputrndamt;
    @Column(name = "TOT_MOD_TIMES")
    private String totmodtimes;
    @Column(name = "RCRE_USER_ID")
    private String rcreuserid;
    @Column(name = "RCRE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rcreTime;
    @Column(name = "LCHG_USER_ID")
    private String lghguserid;
    @Column(name = "LCHG_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lchgTime;
    @Column(name = "TS_CNT")
    private String tnscount;
    @Column(name = "BANK_ID")
    private String bankid;
    @Column(name = "CALC_METHOD")
    private char calcmethod;

    public String getAmttblcode() {
        return this.amttblcode;
    }

    public void setAmttblcode(String amttblcode) {
        this.amttblcode = amttblcode;
    }

    public String getAmttblcodesrlnum() {
        return this.amttblcodesrlnum;
    }

    public void setAmttblcodesrlnum(String amttblcodesrlnum) {
        this.amttblcodesrlnum = amttblcodesrlnum;
    }

    public String getCrncycode() {
        return this.crncycode;
    }

    public void setCrncycode(String crncycode) {
        this.crncycode = crncycode;
    }

    public char getEntitycreflg() {
        return this.entitycreflg;
    }

    public void setEntitycreflg(char entitycreflg) {
        this.entitycreflg = entitycreflg;
    }

    public char getDelflg() {
        return this.delflg;
    }

    public void setDelflg(char delflg) {
        this.delflg = delflg;
    }

    public String getStartamount() {
        return this.startamount;
    }

    public void setStartamount(String startamount) {
        this.startamount = startamount;
    }

    public String getEndamount() {
        return this.endamount;
    }

    public void setEndamount(String endamount) {
        this.endamount = endamount;
    }

    public String getMinamount() {
        return this.minamount;
    }

    public void setMinamount(String minamount) {
        this.minamount = minamount;
    }

    public String getPcntamount() {
        return this.pcntamount;
    }

    public void setPcntamount(String pcntamount) {
        this.pcntamount = pcntamount;
    }

    public String getMaxamount() {
        return this.maxamount;
    }

    public void setMaxamount(String maxamount) {
        this.maxamount = maxamount;
    }

    public char getInputrndflg() {
        return this.inputrndflg;
    }

    public void setInputrndflg(char inputrndflg) {
        this.inputrndflg = inputrndflg;
    }

    public String getInputrndamt() {
        return this.inputrndamt;
    }

    public void setInputrndamt(String inputrndamt) {
        this.inputrndamt = inputrndamt;
    }

    public char getOutputrndflg() {
        return this.outputrndflg;
    }

    public void setOutputrndflg(char outputrndflg) {
        this.outputrndflg = outputrndflg;
    }

    public String getOutputrndamt() {
        return this.outputrndamt;
    }

    public void setOutputrndamt(String outputrndamt) {
        this.outputrndamt = outputrndamt;
    }

    public String getTotmodtimes() {
        return this.totmodtimes;
    }

    public void setTotmodtimes(String totmodtimes) {
        this.totmodtimes = totmodtimes;
    }

    public String getRcreuserid() {
        return this.rcreuserid;
    }

    public void setRcreuserid(String rcreuserid) {
        this.rcreuserid = rcreuserid;
    }

    public Date getRcreTime() {
        return this.rcreTime;
    }

    public void setRcreTime(Date rcreTime) {
        this.rcreTime = rcreTime;
    }

    public String getLghguserid() {
        return this.lghguserid;
    }

    public void setLghguserid(String lghguserid) {
        this.lghguserid = lghguserid;
    }

    public Date getLchgTime() {
        return this.lchgTime;
    }

    public void setLchgTime(Date lchgTime) {
        this.lchgTime = lchgTime;
    }

    public String getTnscount() {
        return this.tnscount;
    }

    public void setTnscount(String tnscount) {
        this.tnscount = tnscount;
    }

    public String getBankid() {
        return this.bankid;
    }

    public void setBankid(String bankid) {
        this.bankid = bankid;
    }

    public char getCalcmethod() {
        return this.calcmethod;
    }

    public void setCalcmethod(char calcmethod) {
        this.calcmethod = calcmethod;
    }
}

/* Location:           C:\Users\sn025896\Downloads\Yetunde\RIASTPAPplication\riaSTP\

 * Qualified Name:     com.fbn.db.jpa.AtcTbl

 * JD-Core Version:    0.7.0.1

 */
