 package com.fbn.db.jpa;

 import java.io.Serializable;
 import javax.persistence.Embeddable;

 @Embeddable
 public class giroEmbed
         implements Serializable  {
     private String payref;
        private String customnarr;
    
     public giroEmbed() {
    }
    
     public giroEmbed(String payref, String customnarr)  {
         this.payref = payref;
         this.customnarr = customnarr;
            }
    
     public int hashCode()  {
         return this.payref.hashCode() + this.payref.hashCode();
            }
    
     public boolean equals(Object obj)  {
         if ((obj instanceof giroEmbed))  {
             giroEmbed giroEmbed = (giroEmbed) obj;
             if (!giroEmbed.getPayref().equals(this.payref)) {
                 return false;
                            }
             if (!giroEmbed.getCustomnarr().equals(this.customnarr)) {
                 return false;
                            }
             return true;
                    }
         return false;
            }
    
     public String getPayref()  {
         return this.payref;
            }
    
     public void setPayref(String payref) {
         this.payref = payref;
            }
    
     public String getCustomnarr() {
         return this.customnarr;
            }
    
     public void setCustomnarr(String customnarr)  {
         this.customnarr = customnarr;
            }
     }

