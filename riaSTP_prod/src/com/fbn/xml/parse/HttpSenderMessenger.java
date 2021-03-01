package com.fbn.xml.parse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.log4j.Logger;

public class HttpSenderMessenger
{
  Logger LOG = Logger.getLogger(HttpSenderMessenger.class);
  
  public HttpGenericResponse sendHttpRequest(String endPointUrl, String xmlFileFormatedMessage, int timeout, String actionVal)
    throws Exception
  {
    this.LOG.info("sending http request with EndPoint URL : " + endPointUrl);
    
    this.LOG.info("sending formated message \n : " + xmlFileFormatedMessage);
    
    System.out.println("Timeout for plugin-adapter : " + timeout / 1000 + "s");
    
    System.setProperty("javax.net.debug", "true");
    
    HttpURLConnection httpUrlConn = null;
    OutputStream outputStream = null;
    BufferedReader bufferedReader = null;
    
    this.LOG.info("Local Variables declared");
    try
    {
      URL url = new URL(endPointUrl);
      httpUrlConn = (HttpURLConnection)url.openConnection();
      
      this.LOG.info("opened http connection");
      
      httpUrlConn.setDoOutput(true);
      httpUrlConn.setDoInput(true);
      httpUrlConn.setAllowUserInteraction(true);
      httpUrlConn.setRequestMethod("POST");
      httpUrlConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
      httpUrlConn.setReadTimeout(timeout);
      httpUrlConn.setRequestProperty("SOAPAction", actionVal);
      
      System.out.println("set some request properties and timeout");
      
      outputStream = httpUrlConn.getOutputStream();
      outputStream.write(xmlFileFormatedMessage.getBytes());
      
      this.LOG.info("attached XML payload to the request message");
      

      HttpGenericResponse httpResponse = new HttpGenericResponse();
      int returnCode = httpUrlConn.getResponseCode();
      httpResponse.setResponseCode(returnCode);
      this.LOG.info("executed HTTP request with response code : " + returnCode);
      
      InputStream connectionIn = null;
      if (returnCode == 200) {
        connectionIn = httpUrlConn.getInputStream();
      } else {
        connectionIn = httpUrlConn.getErrorStream();
      }
      System.out.println("print resulting data stream");
      bufferedReader = new BufferedReader(new InputStreamReader(connectionIn));
      
      StringBuffer stringBuffer = new StringBuffer();
      String inputLine;
      while ((inputLine = bufferedReader.readLine()) != null) {
        stringBuffer = stringBuffer.append(inputLine);
      }
      this.LOG.info("Response load -- " + stringBuffer.toString());
      httpResponse.setResponseMessage(stringBuffer.toString());
      return httpResponse;
    }
    finally
    {
      try
      {
        if (httpUrlConn != null)
        {
          httpUrlConn.disconnect();
          this.LOG.info("closed httpUrlConn");
        }
        if (outputStream != null)
        {
          outputStream.flush();
          outputStream.close();
          this.LOG.info("closed  outputstream");
        }
        if (bufferedReader != null)
        {
          bufferedReader.close();
          this.LOG.info("bufferedReader  outputstream");
        }
      }
      catch (IOException localIOException1) {
      
      }
    }
  }
  
  public static void main(String[] args)
  {
    String endPointUrl = "";
    String xmlFileFormatedMessage = "";
    int timeout = 1000000;
    String actionVal = "CES.Services.FXGlobal/IRiaAsSender/GetOrdersForDownload";
    endPointUrl = "http://stagingfxglobalwebsvcnocert.riaenvia.net:9771/FXGlobalSending.svc/Binding_Basic_NoCert";
    
    xmlFileFormatedMessage = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ces=\"CES.Services.FXGlobal\">\n   <soapenv:Header/>\n   <soapenv:Body>\n      <ces:GetOrdersForDownload>\n         <ces:xmlDoc>\n<Root xmlns:ns2=\"CES.Services.FXGlobal\">\n<PayingCorrespID>7744314</PayingCorrespID>\n<RequestLayoutVersion>1.0</RequestLayoutVersion>\n<RequestType>OrdersForDownload</RequestType>\n</Root>\n         </ces:xmlDoc>\n      </ces:GetOrdersForDownload>\n   </soapenv:Body>\n</soapenv:Envelope>";
    try
    {
      HttpSenderMessenger sendMes = new HttpSenderMessenger();
      sendMes.sendHttpRequest(endPointUrl, xmlFileFormatedMessage, timeout, actionVal);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
