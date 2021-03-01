package com.fbn.xml.parse;

import com.fbn.db.jpa.RiaDtlTbl;
import com.fbn.db.jpa.RiaDtlTblJpaController;
import com.fbn.db.jpa.RiaRequestsController;
import com.fbn.db.jpa.RiaResponseController;
import com.fbn.db.jpa.exceptions.NonexistentEntityException;
import com.fbn.riastp.loadProp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import org.apache.log4j.Logger;

public class GetStatusForUpdate
{
  private static final Logger logFile = Logger.getLogger(GetStatusForUpdate.class);
  @PersistenceUnit(unitName="riaSTPPU")
  EntityManagerFactory emf;
  
  public void GetStatus()
    throws NonexistentEntityException, Exception
  {
    logFile.info("Enter Get Status of order after processing --");
    String endpointurl = loadProp.ENDPOINTURL;
    String endpointtimeout = loadProp.ENDPOINTTIMEOUT;
    String replyStatus = "";
    
    this.emf = Persistence.createEntityManagerFactory("riaSTPPU");
    try
    {
      Query qstatus = this.emf.createEntityManager().createNamedQuery("RiaDtlTbl.findByStatus").setParameter("status", "N");
      
      List<RiaDtlTbl> getRec = qstatus.getResultList();
      if (getRec.isEmpty())
      {
        System.out.println("No Record Found for Update");
        return;
      }
      List<RiaDtlTbl> updateRec = null;
      
      String xmlVal = "";
      xmlVal = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ces=\"CES.Services.FXGlobal\">\n   <soapenv:Header/>\n   <soapenv:Body>\n      <ces:InputOrderStatusNotices>\n           <ces:xmlDoc>\n<Root xmlns:ns2=\"CES.Services.FXGlobal\">\n  <PayingCorrespID>6737914</PayingCorrespID>\n  <InputLayoutVersion>1.1</InputLayoutVersion>\n  <OrderStatusNotices>\n";
      updateRec = new ArrayList();
      for (RiaDtlTbl orderRec : getRec)
      {
        String posted = orderRec.getPstdFlg().toString();
        String fail = orderRec.getFailFlg().toString();
        String orderNo = orderRec.getOrderno();
        System.out.println("getOrderno " + orderRec.getOrderno());
        String returned = orderRec.getReturnedFlg().toString();
        String process = orderRec.getProcessed().toString();
        String statuss = orderRec.getStatus();
        if ((posted.equals("N")) && (fail.equals("N")))
        {
          System.out.println("No Record Found for Update");
        }
        else if ((orderRec.getFbnFlg() != null) && 
          (orderRec.getFbnFlg().charValue() == 'N') && (orderRec.getPstdFlg().charValue() == 'Y') && (orderRec.getReturnedFlg().charValue() == 'X'))
        {
          logFile.info("Other bank transfer has not been handled -- " + orderNo);
        }
        else
        {
          if (posted.equals("Y")) {
            replyStatus = "PAID";
          }
          if (fail.equals("Y")) {
            replyStatus = "REJECTED";
          }
          if (returned.equals("Y")) {
            replyStatus = "REJECTED";
          }
          if ((process.equals("N")) && (statuss.equals("N"))) {
            replyStatus = "RECEIVED";
          }
          if ((posted.equals("N")) && (fail.equals("Y"))) {
            replyStatus = "REJECTED";
          }
          xmlVal = xmlVal + "    <OrderStatusNotice>\n";
          xmlVal = xmlVal + "<PCOrderNo>" + orderRec.getBatchId() + "</PCOrderNo>\n";
          xmlVal = xmlVal + "<SCOrderNo>" + orderRec.getOrderno() + "</SCOrderNo>\n";
          
          xmlVal = xmlVal + "<OrderStatus>" + replyStatus + "</OrderStatus>\n";
          xmlVal = xmlVal + "<StatusDate>" + loadProp.SDF2.format(Calendar.getInstance().getTime()) + "</StatusDate>\n";
          xmlVal = xmlVal + "<StatusTime>" + loadProp.SDF3.format(Calendar.getInstance().getTime()) + "</StatusTime>\n";
          if ((replyStatus.equals("REJECTED")) || (replyStatus.equals("CANCELLED"))) {
            xmlVal = xmlVal + "<Reason>" + orderRec.getFailReason() + "</Reason>\n";
          }
          if (replyStatus.equals("PAID"))
          {
            if (orderRec.getBeneidtype() != null) {
              xmlVal = xmlVal + "<BenIDType>" + orderRec.getBeneidtype() + "</BenIDType>\n";
            }
            if (orderRec.getBeneidno() != null) {
              xmlVal = xmlVal + "<BenIDNo>" + orderRec.getBeneidno() + "</BenIDNo>\n";
            }
          }
          xmlVal = xmlVal + " </OrderStatusNotice>\n";
          updateRec.add(orderRec);
        }
      }
      xmlVal = xmlVal + "  </OrderStatusNotices>\n" + "</Root>\n" + "         </ces:xmlDoc>\n" + "      </ces:InputOrderStatusNotices>\n" + "   </soapenv:Body>\n" + "</soapenv:Envelope>";
      try
      {
        RiaRequestsController riaReq = new RiaRequestsController();
        riaReq.persistRequest(xmlVal);
      }
      catch (Exception e)
      {
        logFile.error("Error occurred in insert request into table -- " + e.toString());
      }
      logFile.info("Final XML passed for order status received  -- " + xmlVal + " EndpointUrl -- " + endpointurl + " EndpointTimeout --" + endpointtimeout);
      
      HttpGenericResponse response = null;
      try
      {
        logFile.info("Size of records sent -- " + updateRec.size());
        HttpSenderMessenger sendMes = new HttpSenderMessenger();
        response = sendMes.sendHttpRequest(endpointurl, xmlVal, Integer.parseInt(endpointtimeout), "CES.Services.FXGlobal/IRiaAsSender/InputOrderStatusNotices");
        if (response.getResponseCode() == 200)
        {
          logFile.info("XML output for sending current status to RIA for transactions   -- " + response.getResponseMessage());
          try
          {
            RiaResponseController riaResCtrl = new RiaResponseController();
            riaResCtrl.persistRiaResponse(response.getResponseCode() + "-" + response.getResponseMessage().trim());
          }
          catch (Exception e)
          {
            logFile.error("Error occurred in insert response into file -- " + e.toString());
          }
          if (!updateRec.isEmpty()) {
            for (RiaDtlTbl orderRec : updateRec)
            {
              String orderNo = orderRec.getOrderno();
              System.out.println("getOrderno for status update -- " + orderRec.getOrderno());
              RiaDtlTblJpaController emm = new RiaDtlTblJpaController(this.emf);
              emm.getEntityManager();
              RiaDtlTbl ord = emm.findRiaDtlTbl(orderNo);
              ord.setStatus("Y");
              emm.edit(ord);
            }
          }
        }
      }
      catch (Exception ep)
      {
        ep.printStackTrace();
        logFile.error("Error occured in sending status update to RIA system -- " + ep.toString());
        logFile.info("Status not updated for records !!!!!");
      }
    }
    catch (Exception ex)
    {
      logFile.error("Error occurred in sending status of transactions to RIA -- " + ex.toString() + " -- " + ex.getLocalizedMessage());
    }
  }
}
