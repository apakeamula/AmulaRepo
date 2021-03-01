package com.fbn.xml.parse;

import com.fbn.db.jpa.GetPayRef;
import com.fbn.db.jpa.RiaDtlTbl;
import com.fbn.db.jpa.RiaDtlTblJpaController;
import com.fbn.db.jpa.exceptions.PreexistingEntityException;
import com.fbn.riastp.loadProp;
import java.io.PrintStream;
import java.io.StringReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class GetXmlOrderData
{
  Logger logFile = Logger.getLogger(GetXmlOrderData.class);
  @PersistenceUnit(unitName="riaSTPPU")
  EntityManagerFactory emf;
  
  public int GetXmlData()
    throws PreexistingEntityException, Exception
  {
    int ret = 0;
    
    SimpleRequest simReq = new SimpleRequest();
    this.emf = Persistence.createEntityManagerFactory("riaSTPPU");
    
    String batchId = loadProp.SDF6.format(Calendar.getInstance().getTime());
    System.out.println("batchId:" + batchId);
    this.logFile.info("batchId -- " + batchId);
    String soapMessage = "";
    soapMessage = simReq.simpleRequest();
    if (!soapMessage.equalsIgnoreCase(""))
    {
      soapMessage = StringEscapeUtils.unescapeHtml(soapMessage);
      this.logFile.info("Read the soap message after unescape --- " + soapMessage);
      try
      {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(soapMessage));
        
        Document doc = db.parse(is);
        NodeList nodes = doc.getElementsByTagName("RequestResponses");
        this.logFile.info("Get Nodes from xml -- " + nodes.getLength());
        if (nodes != null)
        {
          long lval = 1L;
          GetPayRef pRef = new GetPayRef();
          String payRef = pRef.getPayRef();
          if (nodes.getLength() != 0)
          {
            Element element = (Element)nodes.item(0);
            this.logFile.info("Start processing when node has RequestResponses !!  ");
            NodeList ResponseCode = element.getElementsByTagName("Order");
            if ((ResponseCode != null) && (ResponseCode.getLength() != 0))
            {
              this.logFile.info("Enter loop only if the node Order exists -- " + ResponseCode.getLength());
              Element line = null;
              for (int k = 0; k < ResponseCode.getLength(); k++)
              {
            	  	
                this.logFile.info("Value of K in orders -- " + k);
                RiaDtlTbl ord = new RiaDtlTbl();
                
                Document node = null;
				NodeList ordernonode = node.getElementsByTagName("OrderNo");
                if (ordernonode != null)
                {
                  line = (Element)ordernonode.item(0);
                  ord.setOrderno(getCharacterDataFromElement(line));
                  this.logFile.info("Orderno : " + getCharacterDataFromElement(line));
                }
                NodeList PINnode = node.getElementsByTagName("PIN");
                if (PINnode != null)
                {
                  line = (Element)PINnode.item(0);
                  ord.setPin(getCharacterDataFromElement(line));
                  this.logFile.info("PIN : " + getCharacterDataFromElement(line));
                }
                NodeList SendingCorrespSeqIDnode = node.getElementsByTagName("SendingCorrespSeqID");
                if (SendingCorrespSeqIDnode != null)
                {
                  line = (Element)SendingCorrespSeqIDnode.item(0);
                  ord.setSendingcorrespseqid(getCharacterDataFromElement(line));
                }
                NodeList PayingCorrespSeqIDnode = node.getElementsByTagName("PayingCorrespSeqID");
                if (PayingCorrespSeqIDnode != null)
                {
                  line = (Element)PayingCorrespSeqIDnode.item(0);
                  ord.setPayingcorrespseqid(getCharacterDataFromElement(line));
                }
                NodeList SalesDatenode = node.getElementsByTagName("SalesDate");
                if (SalesDatenode != null)
                {
                  line = (Element)SalesDatenode.item(0);
                  ord.setSalesdate(getCharacterDataFromElement(line));
                }
                NodeList SalesTimenode = node.getElementsByTagName("SalesTime");
                if (SalesTimenode != null)
                {
                  line = (Element)SalesTimenode.item(0);
                  ord.setSalestime(getCharacterDataFromElement(line));
                }
                NodeList CountryFromnode = node.getElementsByTagName("CountryFrom");
                if (CountryFromnode != null)
                {
                  line = (Element)CountryFromnode.item(0);
                  ord.setCountryfrom(getCharacterDataFromElement(line));
                }
                NodeList CountryTonode = node.getElementsByTagName("CountryTo");
                if (CountryTonode != null)
                {
                  line = (Element)CountryTonode.item(0);
                  ord.setCountryto(getCharacterDataFromElement(line));
                }
                NodeList PayingCorrespLocIDnode = node.getElementsByTagName("PayingCorrespLocID");
                if (PayingCorrespLocIDnode != null)
                {
                  line = (Element)PayingCorrespLocIDnode.item(0);
                  ord.setPayingcorresplocid(getCharacterDataFromElement(line));
                }
                NodeList SendingCorrespBranchNonode = node.getElementsByTagName("SendingCorrespBranchNo");
                if (SendingCorrespBranchNonode != null)
                {
                  line = (Element)SendingCorrespBranchNonode.item(0);
                  ord.setSendingcorrespbranchno(getCharacterDataFromElement(line));
                }
                NodeList BeneQuestionnode = node.getElementsByTagName("BeneQuestion");
                if (BeneQuestionnode != null)
                {
                  line = (Element)BeneQuestionnode.item(0);
                  ord.setBenequestion(getCharacterDataFromElement(line));
                }
                NodeList BeneAnswernode = node.getElementsByTagName("BeneAnswer");
                if (BeneAnswernode != null)
                {
                  line = (Element)BeneAnswernode.item(0);
                  ord.setBeneanswer(getCharacterDataFromElement(line));
                }
                NodeList PmtInstructionsnode = node.getElementsByTagName("PmtInstructions");
                if (PmtInstructionsnode != null)
                {
                  line = (Element)PmtInstructionsnode.item(0);
                  ord.setPmtinstructions(getCharacterDataFromElement(line));
                }
                NodeList BeneficiaryCurrencynode = node.getElementsByTagName("BeneficiaryCurrency");
                if (BeneficiaryCurrencynode != null)
                {
                  line = (Element)BeneficiaryCurrencynode.item(0);
                  ord.setBeneficiarycurrency(getCharacterDataFromElement(line));
                }
                NodeList BeneficiaryAmountnode = node.getElementsByTagName("BeneficiaryAmount");
                if (BeneficiaryAmountnode != null)
                {
                  line = (Element)BeneficiaryAmountnode.item(0);
                  ord.setBeneficiaryamount(getCharacterDataFromElement(line));
                }
                NodeList DeliveryMethodnode = node.getElementsByTagName("DeliveryMethod");
                if (DeliveryMethodnode != null)
                {
                  line = (Element)DeliveryMethodnode.item(0);
                  ord.setDeliverymethod(getCharacterDataFromElement(line));
                }
                NodeList PaymentCurrencynode = node.getElementsByTagName("PaymentCurrency");
                if (PaymentCurrencynode != null)
                {
                  line = (Element)PaymentCurrencynode.item(0);
                  ord.setPaymentcurrency(getCharacterDataFromElement(line));
                }
                NodeList PaymentAmountnode = node.getElementsByTagName("PaymentAmount");
                if (PaymentAmountnode != null)
                {
                  line = (Element)PaymentAmountnode.item(0);
                  ord.setPaymentamount(getCharacterDataFromElement(line));
                }
                NodeList CommissionCurrencynode = node.getElementsByTagName("CommissionCurrency");
                if (CommissionCurrencynode != null)
                {
                  line = (Element)CommissionCurrencynode.item(0);
                  ord.setCommissioncurrency(getCharacterDataFromElement(line));
                }
                NodeList CommissionAmountnode = node.getElementsByTagName("CommissionAmount");
                if (CommissionAmountnode != null)
                {
                  line = (Element)CommissionAmountnode.item(0);
                  ord.setCommissionamount(getCharacterDataFromElement(line));
                }
                NodeList CustomerChargeCurrencynode = node.getElementsByTagName("CustomerChargeCurrency");
                if (CustomerChargeCurrencynode != null)
                {
                  line = (Element)CustomerChargeCurrencynode.item(0);
                  ord.setCustomerchargecurrency(getCharacterDataFromElement(line));
                }
                NodeList CustomerChargeAmountnode = node.getElementsByTagName("CustomerChargeAmount");
                if (CustomerChargeAmountnode != null)
                {
                  line = (Element)CustomerChargeAmountnode.item(0);
                  ord.setCustomerchargeamount(getCharacterDataFromElement(line));
                }
                NodeList BeneIDnode = node.getElementsByTagName("BeneID");
                if (BeneIDnode != null)
                {
                  line = (Element)BeneIDnode.item(0);
                  ord.setBeneid(getCharacterDataFromElement(line));
                }
                NodeList BeneFirstNamenode = node.getElementsByTagName("BeneFirstName");
                if (BeneFirstNamenode != null)
                {
                  line = (Element)BeneFirstNamenode.item(0);
                  ord.setBenefirstname(getCharacterDataFromElement(line));
                }
                NodeList BeneLastNamenode = node.getElementsByTagName("BeneLastName");
                if (BeneLastNamenode != null)
                {
                  line = (Element)BeneLastNamenode.item(0);
                  ord.setBenelastname(getCharacterDataFromElement(line));
                }
                NodeList BeneLastName2node = node.getElementsByTagName("BeneLastName2");
                if (BeneLastName2node != null)
                {
                  line = (Element)BeneLastName2node.item(0);
                  ord.setBenelastname2(getCharacterDataFromElement(line));
                }
                NodeList BeneAddressnode = node.getElementsByTagName("BeneAddress");
                if (BeneAddressnode != null)
                {
                  line = (Element)BeneAddressnode.item(0);
                  ord.setBeneaddress(getCharacterDataFromElement(line));
                }
                NodeList BeneCitynode = node.getElementsByTagName("BeneCity");
                if (BeneCitynode != null)
                {
                  line = (Element)BeneCitynode.item(0);
                  ord.setBenecity(getCharacterDataFromElement(line));
                }
                NodeList BeneStatenode = node.getElementsByTagName("BeneState");
                if (BeneStatenode != null)
                {
                  line = (Element)BeneStatenode.item(0);
                  ord.setBenestate(getCharacterDataFromElement(line));
                }
                NodeList BeneZipCodenode = node.getElementsByTagName("BeneZipCode");
                if (BeneZipCodenode != null)
                {
                  line = (Element)BeneZipCodenode.item(0);
                  ord.setBenezipcode(getCharacterDataFromElement(line));
                }
                NodeList BeneCountrynode = node.getElementsByTagName("BeneCountry");
                if (BeneCountrynode != null)
                {
                  line = (Element)BeneCountrynode.item(0);
                  ord.setBenecountry(getCharacterDataFromElement(line));
                }
                NodeList BenePhoneNonode = node.getElementsByTagName("BenePhoneNo");
                if (BenePhoneNonode != null)
                {
                  line = (Element)BenePhoneNonode.item(0);
                  ord.setBenephoneno(getCharacterDataFromElement(line));
                }
                NodeList BeneMessagenode = node.getElementsByTagName("BeneMessage");
                if (BeneMessagenode != null)
                {
                  line = (Element)BeneMessagenode.item(0);
                  ord.setBenemessage(getCharacterDataFromElement(line));
                }
                NodeList CustIDnode = node.getElementsByTagName("CustID");
                if (CustIDnode != null)
                {
                  line = (Element)CustIDnode.item(0);
                  ord.setCustid(getCharacterDataFromElement(line));
                }
                NodeList CustFirstNamenode = node.getElementsByTagName("CustFirstName");
                if (CustFirstNamenode != null)
                {
                  line = (Element)CustFirstNamenode.item(0);
                  ord.setCustfirstname(getCharacterDataFromElement(line));
                }
                NodeList CustLastNamenode = node.getElementsByTagName("CustLastName");
                if (CustLastNamenode != null)
                {
                  line = (Element)CustLastNamenode.item(0);
                  ord.setCustlastname(getCharacterDataFromElement(line));
                }
                NodeList CustLastName2node = node.getElementsByTagName("CustLastName2");
                if (CustLastName2node != null)
                {
                  line = (Element)CustLastName2node.item(0);
                  ord.setCustlastname2(getCharacterDataFromElement(line));
                }
                NodeList CustCountrynode = node.getElementsByTagName("CustCountry");
                if (CustCountrynode != null)
                {
                  line = (Element)CustCountrynode.item(0);
                  ord.setCustcountry(getCharacterDataFromElement(line));
                }
                NodeList CustID1Typenode = node.getElementsByTagName("CustID1Type");
                if (CustID1Typenode != null)
                {
                  line = (Element)CustID1Typenode.item(0);
                  ord.setCustid1type(getCharacterDataFromElement(line));
                }
                NodeList CustID1Nonode = node.getElementsByTagName("CustID1No");
                if (CustID1Nonode != null)
                {
                  line = (Element)CustID1Nonode.item(0);
                  ord.setCustid1no(getCharacterDataFromElement(line));
                }
                NodeList CustID1IssuedBynode = node.getElementsByTagName("CustID1IssuedBy");
                if (CustID1IssuedBynode != null)
                {
                  line = (Element)CustID1IssuedBynode.item(0);
                  ord.setCustid1issuedby(getCharacterDataFromElement(line));
                }
                NodeList CustID1IssuedByStatenode = node.getElementsByTagName("CustID1IssuedByState");
                if (CustID1IssuedByStatenode != null)
                {
                  line = (Element)CustID1IssuedByStatenode.item(0);
                  ord.setCustid1issuedbystate(getCharacterDataFromElement(line));
                }
                NodeList CustID1IssuedByCountrynode = node.getElementsByTagName("CustID1IssuedByCountry");
                if (CustID1IssuedByCountrynode != null)
                {
                  line = (Element)CustID1IssuedByCountrynode.item(0);
                  ord.setCustid1issuedbycountry(getCharacterDataFromElement(line));
                }
                NodeList CustID1IssuedDatenode = node.getElementsByTagName("CustID1IssuedDate");
                if (CustID1IssuedDatenode != null)
                {
                  line = (Element)CustID1IssuedDatenode.item(0);
                  ord.setCustid1issueddate(getCharacterDataFromElement(line));
                }
                NodeList CustID1ExpirationDatenode = node.getElementsByTagName("CustID1ExpirationDate");
                if (CustID1ExpirationDatenode != null)
                {
                  line = (Element)CustID1ExpirationDatenode.item(0);
                  ord.setCustid1expirationdate(getCharacterDataFromElement(line));
                }
                NodeList CustID2Typenode = node.getElementsByTagName("CustID2Type");
                if (CustID2Typenode != null)
                {
                  line = (Element)CustID2Typenode.item(0);
                  ord.setCustid2type(getCharacterDataFromElement(line));
                }
                NodeList CustID2Nonode = node.getElementsByTagName("CustID2No");
                if (CustID2Nonode != null)
                {
                  line = (Element)CustID2Nonode.item(0);
                  ord.setCustid2no(getCharacterDataFromElement(line));
                }
                NodeList CustID2IssuedBynode = node.getElementsByTagName("CustID2IssuedBy");
                if (CustID2IssuedBynode != null)
                {
                  line = (Element)CustID2IssuedBynode.item(0);
                  ord.setCustid2issuedby(getCharacterDataFromElement(line));
                }
                NodeList CustID2IssuedByStatenode = node.getElementsByTagName("CustID2IssuedByState");
                if (CustID2IssuedByStatenode != null)
                {
                  line = (Element)CustID2IssuedByStatenode.item(0);
                  ord.setCustid2issuedbystate(getCharacterDataFromElement(line));
                }
                NodeList CustID2IssuedByCountrynode = node.getElementsByTagName("CustID2IssuedByCountry");
                if (CustID2IssuedByCountrynode != null)
                {
                  line = (Element)CustID2IssuedByCountrynode.item(0);
                  ord.setCustid2issuedbycountry(getCharacterDataFromElement(line));
                }
                NodeList CustID2IssuedDatenode = node.getElementsByTagName("CustID2IssuedDate");
                if (CustID2IssuedDatenode != null)
                {
                  line = (Element)CustID2IssuedDatenode.item(0);
                  ord.setCustid2issueddate(getCharacterDataFromElement(line));
                }
                NodeList CustID2ExpirationDatenode = node.getElementsByTagName("CustID2ExpirationDate");
                if (CustID2ExpirationDatenode != null)
                {
                  line = (Element)CustID2ExpirationDatenode.item(0);
                  ord.setCustid2expirationdate(getCharacterDataFromElement(line));
                }
                NodeList CustTaxIDnode = node.getElementsByTagName("CustTaxID");
                if (CustTaxIDnode != null)
                {
                  line = (Element)CustTaxIDnode.item(0);
                  ord.setCusttaxid(getCharacterDataFromElement(line));
                }
                NodeList CustTaxCountrynode = node.getElementsByTagName("CustTaxCountry");
                if (CustTaxCountrynode != null)
                {
                  line = (Element)CustTaxCountrynode.item(0);
                  ord.setCusttaxcountry(getCharacterDataFromElement(line));
                }
                NodeList CustCountryOfBirthnode = node.getElementsByTagName("CustCountryOfBirth");
                if (CustCountryOfBirthnode != null)
                {
                  line = (Element)CustCountryOfBirthnode.item(0);
                  ord.setCustcountryofbirth(getCharacterDataFromElement(line));
                }
                NodeList CustNationalitynode = node.getElementsByTagName("CustNationality");
                if (CustNationalitynode != null)
                {
                  line = (Element)CustNationalitynode.item(0);
                  ord.setCustnationality(getCharacterDataFromElement(line));
                }
                NodeList CustDateOfBirthnode = node.getElementsByTagName("CustDateOfBirth");
                if (CustDateOfBirthnode != null)
                {
                  line = (Element)CustDateOfBirthnode.item(0);
                  ord.setCustdateofbirth(getCharacterDataFromElement(line));
                }
                NodeList CustOccupationnode = node.getElementsByTagName("CustOccupation");
                if (CustOccupationnode != null)
                {
                  line = (Element)CustOccupationnode.item(0);
                  ord.setCustoccupation(getCharacterDataFromElement(line));
                }
                NodeList CustSourceOfFundsnode = node.getElementsByTagName("CustSourceOfFunds");
                if (CustSourceOfFundsnode != null)
                {
                  line = (Element)CustSourceOfFundsnode.item(0);
                  ord.setCustsourceoffunds(getCharacterDataFromElement(line));
                }
                NodeList CustPaymentMethodnode = node.getElementsByTagName("CustPaymentMethod");
                if (CustPaymentMethodnode != null)
                {
                  line = (Element)CustPaymentMethodnode.item(0);
                  ord.setCustpaymentmethod(getCharacterDataFromElement(line));
                }
                NodeList TransferReasonnode = node.getElementsByTagName("TransferReason");
                if (TransferReasonnode != null)
                {
                  line = (Element)TransferReasonnode.item(0);
                  ord.setTransferreason(getCharacterDataFromElement(line));
                }
                NodeList BankNamenode = node.getElementsByTagName("BankName");
                if (BankNamenode != null)
                {
                  line = (Element)BankNamenode.item(0);
                  ord.setBankname(getCharacterDataFromElement(line));
                }
                NodeList BankAccountTypenode = node.getElementsByTagName("BankAccountType");
                if (BankAccountTypenode != null)
                {
                  line = (Element)BankAccountTypenode.item(0);
                  ord.setBankaccounttype(getCharacterDataFromElement(line));
                }
                NodeList BankAccountNonode = node.getElementsByTagName("BankAccountNo");
                if (BankAccountNonode != null)
                {
                  line = (Element)BankAccountNonode.item(0);
                  ord.setBankaccountno(getCharacterDataFromElement(line));
                }
                NodeList BeneIDTypenode = node.getElementsByTagName("BeneIDType");
                if (BeneIDTypenode != null)
                {
                  line = (Element)BeneIDTypenode.item(0);
                  ord.setBeneidtype(getCharacterDataFromElement(line));
                }
                NodeList BeneIDNonode = node.getElementsByTagName("BeneIDNo");
                if (BeneIDNonode != null)
                {
                  line = (Element)BeneIDNonode.item(0);
                  ord.setBeneidno(getCharacterDataFromElement(line));
                }
                NodeList BeneTaxIDnode = node.getElementsByTagName("BeneTaxID");
                if (BeneTaxIDnode != null)
                {
                  line = (Element)BeneTaxIDnode.item(0);
                  ord.setBenetaxid(getCharacterDataFromElement(line));
                }
                NodeList BankCitynode = node.getElementsByTagName("BankCity");
                if (BankCitynode != null)
                {
                  line = (Element)BankCitynode.item(0);
                  ord.setBankcity(getCharacterDataFromElement(line));
                }
                NodeList BankBranchNonode = node.getElementsByTagName("BankBranchNo");
                if (BankBranchNonode != null)
                {
                  line = (Element)BankBranchNonode.item(0);
                  ord.setBankbranchno(getCharacterDataFromElement(line));
                }
                NodeList BankBranchNamenode = node.getElementsByTagName("BankBranchName");
                if (BankBranchNamenode != null)
                {
                  line = (Element)BankBranchNamenode.item(0);
                  ord.setBankbranchname(getCharacterDataFromElement(line));
                }
                NodeList BankBranchAddressnode = node.getElementsByTagName("BankBranchAddress");
                if (BankBranchAddressnode != null)
                {
                  line = (Element)BankBranchAddressnode.item(0);
                  ord.setBankbranchaddress(getCharacterDataFromElement(line));
                }
                NodeList BankCodenode = node.getElementsByTagName("BankCode");
                if (BankCodenode != null)
                {
                  line = (Element)BankCodenode.item(0);
                  ord.setBankcode(getCharacterDataFromElement(line));
                }
                NodeList BankRoutingTypenode = node.getElementsByTagName("BankRoutingType");
                if (BankRoutingTypenode != null)
                {
                  line = (Element)BankRoutingTypenode.item(0);
                  ord.setBankroutingtype(getCharacterDataFromElement(line));
                }
                NodeList BIC_SWIFTnode = node.getElementsByTagName("BIC_SWIFT");
                if (BIC_SWIFTnode != null)
                {
                  line = (Element)BIC_SWIFTnode.item(0);
                  ord.setBicSwift(getCharacterDataFromElement(line));
                }
                NodeList UnitaryBankAccountNonode = node.getElementsByTagName("UnitaryBankAccountNo");
                if (UnitaryBankAccountNonode != null)
                {
                  line = (Element)UnitaryBankAccountNonode.item(0);
                  ord.setUnitarybankaccountno(getCharacterDataFromElement(line));
                }
                NodeList UnitaryTypenode = node.getElementsByTagName("UnitaryType");
                if (UnitaryTypenode != null)
                {
                  line = (Element)UnitaryTypenode.item(0);
                  ord.setUnitarytype(getCharacterDataFromElement(line));
                }
                NodeList Valuetypenode = node.getElementsByTagName("Valuetype");
                if (Valuetypenode != null)
                {
                  line = (Element)Valuetypenode.item(0);
                  ord.setValuetype(getCharacterDataFromElement(line));
                }
                NodeList BankRoutingCodenode = node.getElementsByTagName("BankRoutingCode");
                if (BankRoutingCodenode != null)
                {
                  line = (Element)BankRoutingCodenode.item(0);
                  ord.setBankroutingcode(getCharacterDataFromElement(line));
                  String routCode = getCharacterDataFromElement(line);
                  this.logFile.info("Routing Code -- " + routCode);
                  if (!routCode.trim().equalsIgnoreCase(""))
                  {
                    if (routCode.substring(0, 3).equals("011"))
                    {
                      ord.setFbnFlg(Character.valueOf('Y'));
                      ord.setReturnedFlg(Character.valueOf('N'));
                      ord.setFailFlg(Character.valueOf('N'));
                      this.logFile.info("Order is FBN transaction -- " + routCode);
                    }
                    else
                    {
                      ord.setPayRef(payRef);
                      ord.setFbnFlg(Character.valueOf('N'));
                      ord.setReturnedFlg(Character.valueOf('X'));
                      ord.setNeftPicked("N");
                      ord.setFippicked("N");
                      ord.setBatchSerialNum(lval);
                      ord.setFailFlg(Character.valueOf('N'));
                      this.logFile.info("Value of batchserial num -- " + lval + " -- " + payRef);
                    }
                  }
                  else
                  {
                    ord.setFailFlg(Character.valueOf('Y'));
                    ord.setFbnFlg(Character.valueOf('N'));
                    ord.setFailReason("Bank Routing code does not exist for this order");
                    ord.setReturnedFlg(Character.valueOf('X'));
                  }
                }
                ord.setProcessed(Character.valueOf('N'));
                ord.setPstdFlg(Character.valueOf('N'));
                
                Timestamp sysdate = new Timestamp(loadProp.SDF.parse(Calendar.getInstance().getTime().toString()).getTime());
                System.out.println(sysdate);
                ord.setReceivedDate(sysdate);
                ord.setBatchId(batchId);
                ord.setRetryCount("0");
                ord.setStatus("N");
                ord.setCreTime(sysdate);
                lval += 1L;
                try
                {
                  RiaDtlTblJpaController em = new RiaDtlTblJpaController(this.emf);
                  em.getEntityManager();
                  em.create(ord);
                  this.logFile.info("Record inserted into database for orderno -- " + ord.getOrderno());
                }
                catch (Exception e)
                {
                  System.out.println("Exception on GetXmlOrder - RIA controller: " + e.toString());
                  this.logFile.error("Exception on GetXmlOrder - RIA controller: " + e.toString());
                }
            	 
              }
            }
            else
            {
              this.logFile.info("No orders available to download -- " + Calendar.getInstance().getTime());
              ret = 0;
              return 0;
            }
          }
          try
          {
            OrderStatusNotices thisBatch = new OrderStatusNotices();
            thisBatch.orderStatus("RECEIVED", batchId);
          }
          catch (Exception e)
          {
            System.out.println("OrderStatusNotices Exception: " + e.toString());
            this.logFile.error("OrderStatusNotices Exception: " + e.toString());
          }
          ret = 1;
        }
        else
        {
          System.out.println("No Request Fetched at " + loadProp.SDF.format(Calendar.getInstance().getTime()));
          this.logFile.info("No Request Fetched at " + loadProp.SDF.format(Calendar.getInstance().getTime()));
          ret = 0;
          return 0;
        }
      }
      catch (Exception ey)
      {
        System.out.println("Get Records in XML Exception: " + ey.toString());
        this.logFile.error("Get Records in XML  Exception: " + ey.toString());
      }
    }
    else
    {
      System.out.println("WebService returned no response !!! " + soapMessage);
      this.logFile.info("WebService returned no response !!!!!! " + soapMessage);
    }
    return ret;
  }
  
  public static String getCharacterDataFromElement(Element e)
  {
    if (e == null) {
      return " ";
    }
    Node child = e.getFirstChild();
    if ((child instanceof CharacterData))
    {
      CharacterData cd = (CharacterData)child;
      return cd.getData();
    }
    return " ";
  }
}
