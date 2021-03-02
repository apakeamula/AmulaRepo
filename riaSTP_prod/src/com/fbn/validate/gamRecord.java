package com.fbn.validate;

import java.io.Serializable;

public class gamRecord
  implements Serializable
{
  private String foracid;
  private String acctName;
  
  public String getForacid()
  {
    return this.foracid;
  }
  
  public void setForacid(String foracid)
  {
    this.foracid = foracid;
  }
  
  public String getAcctName()
  {
    return this.acctName;
  }
  
  public void setAcctName(String acctName)
  {
    this.acctName = acctName;
  }
}