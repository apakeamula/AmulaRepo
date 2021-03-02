package com.fbn.riathreads;

import com.fbn.db.jpa.RiaDtlTbl;
import com.fbn.db.jpa.RiaDtlTblJpaController;
import com.fbn.riastp.loadProp;
import com.fbn.xml.parse.GetCancellationRequest;
import com.fbn.xml.parse.HttpGenericResponse;
import com.fbn.xml.parse.HttpSenderMessenger;
import java.io.StringReader;
import java.util.Calendar;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
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

public class ProcessCancelledRequest
{
  Logger logFile = Logger.getLogger(ProcessCancelledRequest.class);
  @PersistenceUnit(unitName="riaSTPPU")
  EntityManagerFactory emf;
  
  public void processCancelledRequest()
    throws Exception
  {
    GetCancellationRequest getcancelfile = new GetCancellationRequest();
    String endpointurl = loadProp.ENDPOINTURL;
    String endpointtimeout = loadProp.ENDPOINTTIMEOUT;
    
    String xmlVal = "";
    xmlVal = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ces=\"CES.Services.FXGlobal\">\n   <soapenv:Header/>\n   <soapenv:Body>\n      <ces:InputOrderStatusNotices>\n           <ces:xmlDoc>\n<Root xmlns:ns2=\"CES.Services.FXGlobal\">\n  <PayingCorrespID>7744314</PayingCorrespID>\n  <InputLayoutVersion>1.1</InputLayoutVersion>\n  <OrderStatusNotices>\n";
    







    System.out.println("I got to GetStatus in GetStatusForUpdate");
    try
    {
      String xmlFile = getcancelfile.getCancelRequest();
      this.logFile.info("XML String for cancellation request -- " + xmlFile);
      if (!xmlFile.equalsIgnoreCase(""))
      {
        xmlFile = StringEscapeUtils.unescapeHtml(xmlFile);
        this.logFile.info("Read the soap message for cancel after unescape --- " + xmlFile);
        try
        {
          DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
          DocumentBuilder db = dbf.newDocumentBuilder();
          InputSource is = new InputSource();
          is.setCharacterStream(new StringReader(xmlFile));
          
          Document doc = db.parse(is);
          NodeList nodes = doc.getElementsByTagName("RequestResponses");
          this.logFile.info("Get Nodes from xml -- " + nodes.getLength());
          if ((nodes != null) && 
            (nodes.getLength() != 0))
          {
            Element element = (Element)nodes.item(0);
            this.logFile.info("Start processing when node has RequestResponses !!  ");
            NodeList ResponseCode = element.getElementsByTagName("CancelationRequest");
            if ((ResponseCode != null) && (ResponseCode.getLength() != 0))
            {
              this.logFile.info("Enter loop only if the node CancelationRequest exists -- " + ResponseCode.getLength());
              for (int k = 0; k < ResponseCode.getLength(); k++)
              {
                Element node = (Element)ResponseCode.item(k);
                
                String orderNo = node.getAttribute("SCOrderNo");
                this.logFile.info("Orderno to be cancelled : " + node.getAttribute("SCOrderNo"));
                
                this.emf = Persistence.createEntityManagerFactory("riaSTPPU");
                Query getRecQuery = this.emf.createEntityManager().createNamedQuery("RiaDtlTbl.findByOrderno").setParameter("orderno", orderNo.trim());
                
                Object getRecfrmTbl = getRecQuery.getSingleResult();
                RiaDtlTbl riaVal = (RiaDtlTbl)getRecfrmTbl;
                if ((riaVal.getFailFlg().equals(Character.valueOf('N'))) && (riaVal.getPstdFlg().equals(Character.valueOf('N'))))
                {
                  xmlVal = xmlVal + "    <OrderStatusNotice>\n";
                  xmlVal = xmlVal + "<PCOrderNo>" + riaVal.getBatchId() + "</PCOrderNo>\n";
                  xmlVal = xmlVal + "<SCOrderNo>" + riaVal.getOrderno() + "</SCOrderNo>\n";
                  xmlVal = xmlVal + "<OrderStatus>CANCELED</OrderStatus>\n";
                  xmlVal = xmlVal + "<StatusDate>" + loadProp.SDF2.format(Calendar.getInstance().getTime()) + "</StatusDate>\n";
                  xmlVal = xmlVal + "<StatusTime>" + loadProp.SDF3.format(Calendar.getInstance().getTime()) + "</StatusTime>\n";
                  xmlVal = xmlVal + "<Reason>CANCEL OK</Reason>\n";
                  xmlVal = xmlVal + " </OrderStatusNotice>\n";
                  

                  RiaDtlTblJpaController em1 = new RiaDtlTblJpaController(this.emf);
                  em1.getEntityManager();
                  RiaDtlTbl ord = em1.findRiaDtlTbl(orderNo);
                  ord.setStatus("Y");
                  ord.setFailFlg(Character.valueOf('Y'));
                  ord.setFailReason("ORDER CANCELED BY RIA");
                  em1.edit(ord);
                }
                if (riaVal.getFailFlg().equals(Character.valueOf('Y')))
                {
                  xmlVal = xmlVal + "    <OrderStatusNotice>\n";
                  xmlVal = xmlVal + "<PCOrderNo>" + riaVal.getBatchId() + "</PCOrderNo>\n";
                  xmlVal = xmlVal + "<SCOrderNo>" + riaVal.getOrderno() + "</SCOrderNo>\n";
                  xmlVal = xmlVal + "<OrderStatus>FAILED</OrderStatus>\n";
                  xmlVal = xmlVal + "<StatusDate>" + loadProp.SDF2.format(Calendar.getInstance().getTime()) + "</StatusDate>\n";
                  xmlVal = xmlVal + "<StatusTime>" + loadProp.SDF3.format(Calendar.getInstance().getTime()) + "</StatusTime>\n";
                  xmlVal = xmlVal + "<Reason>" + riaVal.getFailReason() + "</Reason>\n";
                  xmlVal = xmlVal + " </OrderStatusNotice>\n";
                }
                if ((riaVal.getFailFlg().equals(Character.valueOf('N'))) && (riaVal.getPstdFlg().equals(Character.valueOf('Y'))) && (riaVal.getFbnFlg().equals(Character.valueOf('Y'))))
                {
                  xmlVal = xmlVal + "    <OrderStatusNotice>\n";
                  xmlVal = xmlVal + "<PCOrderNo>" + riaVal.getBatchId() + "</PCOrderNo>\n";
                  xmlVal = xmlVal + "<SCOrderNo>" + riaVal.getOrderno() + "</SCOrderNo>\n";
                  xmlVal = xmlVal + "<OrderStatus>PAID</OrderStatus>\n";
                  xmlVal = xmlVal + "<StatusDate>" + loadProp.SDF2.format(Calendar.getInstance().getTime()) + "</StatusDate>\n";
                  xmlVal = xmlVal + "<StatusTime>" + loadProp.SDF3.format(Calendar.getInstance().getTime()) + "</StatusTime>\n";
                  if (riaVal.getBeneidtype() != null) {
                    xmlVal = xmlVal + "<BenIDType>" + riaVal.getBeneidtype() + "</BenIDType>\n";
                  }
                  if (riaVal.getBeneidno() != null) {
                    xmlVal = xmlVal + "<BenIDNo>" + riaVal.getBeneidno() + "</BenIDNo>\n";
                  }
                  xmlVal = xmlVal + " </OrderStatusNotice>\n";
                }
                if ((riaVal.getFailFlg().equals(Character.valueOf('N'))) && (riaVal.getPstdFlg().equals(Character.valueOf('Y'))) && (riaVal.getFbnFlg().equals(Character.valueOf('N'))) && (riaVal.getNeftPicked().equalsIgnoreCase("Y")))
                {
                  xmlVal = xmlVal + "    <OrderStatusNotice>\n";
                  xmlVal = xmlVal + "<PCOrderNo>" + riaVal.getBatchId() + "</PCOrderNo>\n";
                  xmlVal = xmlVal + "<SCOrderNo>" + riaVal.getOrderno() + "</SCOrderNo>\n";
                  xmlVal = xmlVal + "<OrderStatus>PROCESSING</OrderStatus>\n";
                  xmlVal = xmlVal + "<StatusDate>" + loadProp.SDF2.format(Calendar.getInstance().getTime()) + "</StatusDate>\n";
                  xmlVal = xmlVal + "<StatusTime>" + loadProp.SDF3.format(Calendar.getInstance().getTime()) + "</StatusTime>\n";
                  xmlVal = xmlVal + "<Reason>SENT TO OTHER BANK FOR PROCESSING</Reason>\n";
                  xmlVal = xmlVal + " </OrderStatusNotice>\n";
                }
              }
            }
          }
        }
        catch (Exception ey)
        {
          this.logFile.error("Error occurred in reading XML response for cancelled transactions -- " + ey.toString());
        }
      }
      xmlVal = xmlVal + "  </OrderStatusNotices>\n" + "</Root>\n" + "         </ces:xmlDoc>\n" + "      </ces:InputOrderStatusNotices>\n" + "   </soapenv:Body>\n" + "</soapenv:Envelope>";
      




      this.logFile.info("Final XML passed for order status received  -- " + xmlVal + " EndpointUrl -- " + endpointurl + " EndpointTimeout --" + endpointtimeout);
      HttpSenderMessenger sendMes = new HttpSenderMessenger();
      HttpGenericResponse response = sendMes.sendHttpRequest(endpointurl, xmlVal, Integer.parseInt(endpointtimeout), "CES.Services.FXGlobal/IRiaAsSender/InputOrderStatusNotices");
      
      this.logFile.info("XML output for cancelled  -- " + response.getResponseMessage());
      String receiveResponse = response.getResponseMessage();
      if (!receiveResponse.equalsIgnoreCase(""))
      {
        receiveResponse = StringEscapeUtils.unescapeHtml(receiveResponse);
        this.logFile.info("Read the soap message after unescape --- " + receiveResponse);
        try
        {
          DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
          DocumentBuilder db = dbf.newDocumentBuilder();
          InputSource is = new InputSource();
          is.setCharacterStream(new StringReader(receiveResponse));
          
          Document doc = db.parse(is);
          NodeList nodes = doc.getElementsByTagName("Acknowledgements");
          this.logFile.info("Get Nodes from xml -- " + nodes.getLength());
          if ((nodes != null) && 
            (nodes.getLength() != 0))
          {
            Element element = (Element)nodes.item(0);
            this.logFile.info("Start processing when node has Acknowledgements !!  ");
            NodeList ResponseCode = element.getElementsByTagName("OrderStatusNoticeAcknowledgement");
            if ((ResponseCode != null) && (ResponseCode.getLength() != 0))
            {
              this.logFile.info("Enter loop only if the node Order exists -- " + ResponseCode.getLength());
              Element line = null;
              for (int k = 0; k < ResponseCode.getLength(); k++)
              {
                Element node = (Element)ResponseCode.item(k);
                this.logFile.info("Value of K in orders -- " + k);
                
                NodeList ordernonode = node.getElementsByTagName("PCOrderNo");
                if (ordernonode != null)
                {
                  line = (Element)ordernonode.item(0);
                  this.logFile.info("PCOrderNo : " + getCharacterDataFromElement(line));
                }
                NodeList SCOrderNonode = node.getElementsByTagName("SCOrderNo");
                if (SCOrderNonode != null)
                {
                  line = (Element)SCOrderNonode.item(0);
                  this.logFile.info("SCOrderNo : " + getCharacterDataFromElement(line));
                }
                NodeList ProcessDatenode = node.getElementsByTagName("ProcessDate");
                if (ProcessDatenode != null)
                {
                  line = (Element)ProcessDatenode.item(0);
                  this.logFile.info("ProcessDate : " + getCharacterDataFromElement(line));
                }
                NodeList ProcessTimenode = node.getElementsByTagName("ProcessTime");
                if (ProcessTimenode != null)
                {
                  line = (Element)ProcessTimenode.item(0);
                  this.logFile.info("ProcessTime : " + getCharacterDataFromElement(line));
                }
                NodeList NotificationDescnode = node.getElementsByTagName("NotificationDesc");
                if (NotificationDescnode != null)
                {
                  line = (Element)NotificationDescnode.item(0);
                  this.logFile.info("NotificationDesc : " + getCharacterDataFromElement(line));
                }
              }
            }
          }
        }
        catch (Exception ey)
        {
          this.logFile.error("Error occurred in read Acknowledgement response for received transaction -- " + ey.toString());
        }
      }
    }
    catch (Exception ex)
    {
      this.logFile.error("Error occurred in process cancelled request  -- " + ex.toString() + " -- " + ex.getLocalizedMessage());
      throw new Exception(ex.toString());
    }
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
