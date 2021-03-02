package com.fbn.xml.parse;

import com.fbn.db.jpa.RiaDtlTbl;
import com.fbn.db.jpa.RiaRequestsController;
import com.fbn.db.jpa.RiaResponseController;
import com.fbn.riastp.loadProp;
import java.io.IOException;
import java.io.StringReader;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class OrderStatusNotices
{
  private static final Logger logFile = Logger.getLogger(OrderStatusNotices.class);
  @PersistenceUnit(unitName="riaSTPPU")
  EntityManagerFactory emf;
  String endpointurl = loadProp.ENDPOINTURL;
  String endpointtimeout = loadProp.ENDPOINTTIMEOUT;
  
  public void orderStatus(String status, String batchId)
  {
    try
    {
      logFile.info("Send order status to confirm receipt of batch");
      
      String xmlVal = "";
      xmlVal = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ces=\"CES.Services.FXGlobal\">\n   <soapenv:Header/>\n   <soapenv:Body>\n      <ces:InputOrderStatusNotices>\n           <ces:xmlDoc>\n<Root xmlns:ns2=\"CES.Services.FXGlobal\">\n  <PayingCorrespID>6737914</PayingCorrespID>\n  <InputLayoutVersion>1.1</InputLayoutVersion>\n  <OrderStatusNotices>\n";
     
      this.emf = Persistence.createEntityManagerFactory("riaSTPPU");
      Query getBatch = this.emf.createEntityManager().createNamedQuery("RiaDtlTbl.findByBatchId").setParameter("batchId", batchId);
      List<RiaDtlTbl> getRec = getBatch.getResultList();
      logFile.info("Number of records to be sent as received -- " + getRec.size() + " -- Record empty or not--  " + getRec.isEmpty());
      if (!getRec.isEmpty())
      {
        for (RiaDtlTbl orderRec : getRec)
        {
          xmlVal = xmlVal + "    <OrderStatusNotice>\n";
          xmlVal = xmlVal + "<PCOrderNo>" + orderRec.getBatchId() + "</PCOrderNo>\n";
          xmlVal = xmlVal + "<SCOrderNo>" + orderRec.getOrderno() + "</SCOrderNo>\n";
          xmlVal = xmlVal + "<OrderStatus>" + status + "</OrderStatus>\n";
          xmlVal = xmlVal + "<StatusDate>" + loadProp.SDF2.format(Calendar.getInstance().getTime()) + "</StatusDate>\n";
          xmlVal = xmlVal + "<StatusTime>" + loadProp.SDF3.format(Calendar.getInstance().getTime()) + "</StatusTime>\n";
          xmlVal = xmlVal + " </OrderStatusNotice>\n";
        }
        xmlVal = xmlVal + "  </OrderStatusNotices>\n" + "</Root>\n" + "         </ces:xmlDoc>\n" + "      </ces:InputOrderStatusNotices>\n" + "   </soapenv:Body>\n" + "</soapenv:Envelope>";
       
        logFile.info("Final XML passed for order status received  -- " + xmlVal + " EndpointUrl -- " + this.endpointurl + " EndpointTimeout --" + this.endpointtimeout);
        try
        {
          RiaRequestsController riaReq = new RiaRequestsController();
          riaReq.persistRequest(xmlVal);
        }
        catch (Exception e)
        {
          logFile.error("Error occurred in insert request into table -- " + e.toString());
        }
        HttpSenderMessenger sendMes = new HttpSenderMessenger();
        HttpGenericResponse response = sendMes.sendHttpRequest(this.endpointurl, xmlVal, Integer.parseInt(this.endpointtimeout), "CES.Services.FXGlobal/IRiaAsSender/InputOrderStatusNotices");
        
        logFile.info("XML output -- " + response.getResponseMessage());
        String receiveResponse = response.getResponseMessage();
        if (!receiveResponse.equalsIgnoreCase(""))
        {
          receiveResponse = StringEscapeUtils.unescapeHtml(receiveResponse);
          logFile.info("Read the soap message after unescape --- " + receiveResponse);
          try
          {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(receiveResponse));
            
            Document doc = db.parse(is);
            NodeList nodes = doc.getElementsByTagName("Acknowledgements");
            logFile.info("Get Nodes from xml -- " + nodes.getLength());
            if ((nodes != null) && 
              (nodes.getLength() != 0))
            {
              Element element = (Element)nodes.item(0);
              logFile.info("Start processing when node has Acknowledgements !!  ");
              NodeList ResponseCode = element.getElementsByTagName("OrderStatusNoticeAcknowledgement");
              if ((ResponseCode != null) && (ResponseCode.getLength() != 0))
              {
                logFile.info("Enter loop only if the node Order exists -- " + ResponseCode.getLength());
                Element line = null;
                for (int k = 0; k < ResponseCode.getLength(); k++)
                {
                  Element node = (Element)ResponseCode.item(k);
                  logFile.info("Value of K in orders -- " + k);
                  
                  NodeList ordernonode = node.getElementsByTagName("PCOrderNo");
                  if (ordernonode != null)
                  {
                    line = (Element)ordernonode.item(0);
                    logFile.info("PCOrderNo : " + GetXmlOrderData.getCharacterDataFromElement(line));
                  }
                  NodeList SCOrderNonode = node.getElementsByTagName("SCOrderNo");
                  if (SCOrderNonode != null)
                  {
                    line = (Element)SCOrderNonode.item(0);
                    logFile.info("SCOrderNo : " + GetXmlOrderData.getCharacterDataFromElement(line));
                  }
                  NodeList ProcessDatenode = node.getElementsByTagName("ProcessDate");
                  if (ProcessDatenode != null)
                  {
                    line = (Element)ProcessDatenode.item(0);
                    logFile.info("ProcessDate : " + GetXmlOrderData.getCharacterDataFromElement(line));
                  }
                  NodeList ProcessTimenode = node.getElementsByTagName("ProcessTime");
                  if (ProcessTimenode != null)
                  {
                    line = (Element)ProcessTimenode.item(0);
                    logFile.info("ProcessTime : " + GetXmlOrderData.getCharacterDataFromElement(line));
                  }
                  NodeList NotificationDescnode = node.getElementsByTagName("NotificationDesc");
                  if (NotificationDescnode != null)
                  {
                    line = (Element)NotificationDescnode.item(0);
                    logFile.info("NotificationDesc : " + GetXmlOrderData.getCharacterDataFromElement(line));
                  }
                }
              }
            }
          }
          catch (Exception ey)
          {
            logFile.error("Error occurred in read Acknowledgement response for received transaction -- " + ey.toString());
          }
        }
        try
        {
          RiaResponseController riaResCtrl = new RiaResponseController();
          riaResCtrl.persistRiaResponse(response.getResponseCode() + "-" + response.getResponseMessage().trim());
        }
        catch (Exception e)
        {
          logFile.error("Error occurred in insert response into table -- " + e.toString());
        }
      }
    }
    catch (IOException ex)
    {
      logFile.error("Error occurred in return order status Notice -- " + ex.getMessage() + " -- " + ex.toString());
    }
    catch (Exception e)
    {
      logFile.error("Error occurred in return order status Notice -- " + e.getMessage() + " -- " + e.toString());
    }
  }
}
