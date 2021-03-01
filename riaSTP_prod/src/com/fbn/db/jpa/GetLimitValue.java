 package com.fbn.db.jpa;

 public class GetLimitValue  {
     private String debitLimit;
        private String creditLimit;
        private String minAcctBalance;
        private String maxAcctBalance;
    
     public String getDebitLimit()  {
         return this.debitLimit;
            }
    
     public void setDebitLimit(String debitLimit)  {
         this.debitLimit = debitLimit;
            }
    
     public String getCreditLimit()  {
         return this.creditLimit;
            }
    
     public void setCreditLimit(String creditLimit) {
         this.creditLimit = creditLimit;
            }
    
     public String getMinAcctBalance()  {
         return this.minAcctBalance;
            }
    
     public void setMinAcctBalance(String minAcctBalance)  {
         this.minAcctBalance = minAcctBalance;
            }
    
     public String getMaxAcctBalance()  {
         return this.maxAcctBalance;
            }
    
     public void setMaxAcctBalance(String maxAcctBalance)  {
         this.maxAcctBalance = maxAcctBalance;
            }
     }


