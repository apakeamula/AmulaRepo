 package com.fbn.db.jpa;

 import java.io.Serializable;
 import javax.persistence.Basic;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.Id;
 import javax.persistence.NamedQueries;
 import javax.persistence.Table;
 import javax.xml.bind.annotation.XmlRootElement;

 @Entity
 @Table(name = "NEFTCHARGES", catalog = "", schema = "CUSTOM")
 @XmlRootElement
 @NamedQueries({
    @javax.persistence.NamedQuery(name = "NeftCommisionChargeTbl.findOne", query = "select r  from NeftCommisionChargeTbl r where r.comm_amt = :commamt and r.del_flg = 'N'")})
 public class NeftCommisionChargeTbl
         implements Serializable  {
     @Id
        @Basic(optional = false)
        @Column(name = "COMM_AMT")
     private String comm_amt;
        @Column(name = "DEL_FLG")
     private String del_flg;
        @Column(name = "BR_AMT")
     private String br_amt;
        @Column(name = "NIBBS_AMT")
     private String nibbs_amt;
    
     public String getComm_amt() {
         return this.comm_amt;
            }
    
     public void setComm_amt(String comm_amt)  {
         this.comm_amt = comm_amt;
            }
    
     public String getDel_flg()  {
         return this.del_flg;
            }
    
     public void setDel_flg(String del_flg) {
         this.del_flg = del_flg;
            }
    
     public String getBr_amt()  {
         return this.br_amt;
            }
    
     public void setBr_amt(String br_amt) {
         this.br_amt = br_amt;
            }
    
     public String getNibbs_amt()  {
         return this.nibbs_amt;
            }
    
     public void setNibbs_amt(String nibbs_amt)  {
         this.nibbs_amt = nibbs_amt;
            }
     }


