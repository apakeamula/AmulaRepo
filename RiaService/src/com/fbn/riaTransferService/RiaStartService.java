package com.fbn.riaTransferService;
import com.fbn.utils.LogGEN;
import org.apache.log4j.Logger;



public class RiaStartService {
	
   private static final Logger logger = LogGEN.getLoggerInstance(RiaStartService.class);

   public static void main(String[] args) {
   	try {
   	logger.info("Service Started");
   	// first commit from local machine
   	RiaTransfer riaTransfer = new RiaTransfer();
   	RiaUpdateCheck riaUpdateCheck = new RiaUpdateCheck();

   	riaTransfer.moveRiaTxn();
   	riaUpdateCheck.checkRiaStatus();

   	logger.info("Service Completed");
   	} catch (Exception e){
   		logger.error("Exception occurred. "+ e.getMessage());
	}
	}

}
