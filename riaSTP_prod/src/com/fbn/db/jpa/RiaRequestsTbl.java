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
 @Table(name = "RIA_REQUESTS_TBL", catalog = "", schema = "CUSTOM")
 @XmlRootElement
 @NamedQueries({
    @javax.persistence.NamedQuery(name = "RiaRequestsTbl.findAll", query = "SELECT r FROM RiaRequestsTbl r"),
    @javax.persistence.NamedQuery(name = "RiaRequestsTbl.findByRiaRequestsTime", query = "SELECT r FROM RiaRequestsTbl r WHERE r.riaRequestsTime = :riaRequestsTime"),
    @javax.persistence.NamedQuery(name = "RiaRequestsTbl.findByRiaRequestsXml", query = "SELECT r FROM RiaRequestsTbl r WHERE r.riaRequestsXml = :riaRequestsXml")})
 public class RiaRequestsTbl
         implements Serializable {
     private static final long serialVersionUID = 1L;
        @Id
        @Basic(optional = false)
        @Column(name = "RIA_REQUESTS_TIME")
     private String riaRequestsTime;
        @Column(name = "RIA_REQUESTS_XML")
     private String riaRequestsXml;
    
     public RiaRequestsTbl() {
    }
    
     public RiaRequestsTbl(String riaRequestsTime)  {
         this.riaRequestsTime = riaRequestsTime;
            }
    
     public String getRiaRequestsTime() {
         return this.riaRequestsTime;
            }
    
     public void setRiaRequestsTime(String riaRequestsTime)  {
         this.riaRequestsTime = riaRequestsTime;
            }
    
     public String getRiaRequestsXml()  {
         return this.riaRequestsXml;
            }
    
     public void setRiaRequestsXml(String riaRequestsXml)  {
         this.riaRequestsXml = riaRequestsXml;
            }
    
     public int hashCode() {
         int hash = 0;
         hash += (this.riaRequestsTime != null ? this.riaRequestsTime.hashCode() : 0);
         return hash;
            }
    
     public boolean equals(Object object)  {
         if (!(object instanceof RiaRequestsTbl)) {
             return false;
                    }
         RiaRequestsTbl other = (RiaRequestsTbl) object;
         if (((this.riaRequestsTime == null) && (other.riaRequestsTime != null)) || ((this.riaRequestsTime != null) && (!this.riaRequestsTime.equals(other.riaRequestsTime)))) {
             return false;
                    }
         return true;
            }
    
     public String toString()  {
         return "com.fbn.db.jpa.RiaRequestsTbl[ riaRequestsTime=" + this.riaRequestsTime + " ]";
            }
     }


