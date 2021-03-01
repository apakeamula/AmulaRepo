package com.fbn.xml.parse;

import com.fbn.db.jpa.RiaRequestsController;
import com.fbn.db.jpa.RiaResponseController;
import com.fbn.riastp.loadProp;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.log4j.Logger;

public class SimpleRequest
{
  private static final Logger logFile = Logger.getLogger(SimpleRequest.class);
  
  public String simpleRequest()
    throws ParserConfigurationException
  {
    String xmlRes ="";
    String endpointurl = loadProp.ENDPOINTURL;
    String endpointtimeout = loadProp.ENDPOINTTIMEOUT;
    
    String xmlFileFormatedMessage = "";
    try
    {
      xmlFileFormatedMessage = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ces=\"CES.Services.FXGlobal\">\n   <soapenv:Header/>\n   <soapenv:Body>\n      <ces:GetOrdersForDownload>\n         <ces:xmlDoc>\n<Root xmlns:ns2=\"CES.Services.FXGlobal\">\n<PayingCorrespID>6737914</PayingCorrespID>\n<RequestLayoutVersion>1.0</RequestLayoutVersion>\n<RequestType>OrdersForDownload</RequestType>\n</Root>\n         </ces:xmlDoc>\n      </ces:GetOrdersForDownload>\n   </soapenv:Body>\n</soapenv:Envelope>";
      try
      {
        RiaRequestsController riaReq = new RiaRequestsController();
        riaReq.persistRequest(xmlFileFormatedMessage);
      }
      catch (Exception ex)
      {
        logFile.error("Error occurred in inserting request into DB for Getdownload -- " + ex.getLocalizedMessage() + " -- " + ex.toString());
      }
      HttpSenderMessenger sendMes = new HttpSenderMessenger();
      HttpGenericResponse response = sendMes.sendHttpRequest(endpointurl, xmlFileFormatedMessage, Integer.parseInt(endpointtimeout), "CES.Services.FXGlobal/IRiaAsSender/GetOrdersForDownload");
      
      logFile.info("XML output -- " + response.getResponseMessage());
      if (response.getResponseCode() == 200) {
        xmlRes = response.getResponseMessage();
      } else {
        xmlRes = "";
      }
      try
      {
        RiaResponseController riaRes = new RiaResponseController();
        riaRes.persistRiaResponse(xmlRes);
      }
      catch (Exception e)
      {
        logFile.error("Error occurred in insert into response table for RIA request -- " + e.toString() + " -- " + e.getLocalizedMessage());
      }
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      logFile.error("Error occurred in simpleRequest send -- " + ex.toString() + " -- " + ex.getLocalizedMessage());
    }
    logFile.info("Response sent to GetXmlOrder Data --  " + xmlRes);
    return xmlRes;
  }
}