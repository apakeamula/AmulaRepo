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
 @Table(name = "RIA_RESPONSE_TBL", catalog = "", schema = "CUSTOM")
 @XmlRootElement
 @NamedQueries({
    @javax.persistence.NamedQuery(name = "RiaResponseTbl.findAll", query = "SELECT r FROM RiaResponseTbl r"),
    @javax.persistence.NamedQuery(name = "RiaResponseTbl.findByRiaResponseTime", query = "SELECT r FROM RiaResponseTbl r WHERE r.riaResponseTime = :riaResponseTime"),
    @javax.persistence.NamedQuery(name = "RiaResponseTbl.findByRiaResponseXml", query = "SELECT r FROM RiaResponseTbl r WHERE r.riaResponseXml = :riaResponseXml")})
 public class RiaResponseTbl
         implements Serializable {
     private static final long serialVersionUID = 1L;
        @Id
        @Basic(optional = false)
        @Column(name = "RIA_RESPONSE_TIME")
     private String riaResponseTime;
        @Column(name = "RIA_RESPONSE_XML")
     private String riaResponseXml;
    
     public RiaResponseTbl() {
    }
    
     public RiaResponseTbl(String riaResponseTime)  {
         this.riaResponseTime = riaResponseTime;
            }
    
     public String getRiaResponseTime()  {
         return this.riaResponseTime;
            }
    
     public void setRiaResponseTime(String riaResponseTime) {
         this.riaResponseTime = riaResponseTime;
            }
    
     public String getRiaResponseXml()  {
         return this.riaResponseXml;
            }
    
     public void setRiaResponseXml(String riaResponseXml)  {
         this.riaResponseXml = riaResponseXml;
            }
    
     public int hashCode()  {
         int hash = 0;
         hash += (this.riaResponseTime != null ? this.riaResponseTime.hashCode() : 0);
         return hash;
            }
    
     public boolean equals(Object object)  {
         if (!(object instanceof RiaResponseTbl)) {
             return false;
                    }
         RiaResponseTbl other = (RiaResponseTbl) object;
         if (((this.riaResponseTime == null) && (other.riaResponseTime != null)) || ((this.riaResponseTime != null) && (!this.riaResponseTime.equals(other.riaResponseTime)))) {
             return false;
                    }
         return true;
            }
    
     public String toString()  {
         return "com.fbn.db.jpa.RiaResponseTbl[ riaResponseTime=" + this.riaResponseTime + " ]";
            }
     }


