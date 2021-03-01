package com.fbn.utils;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

public class LogGEN implements RiaConstants
{
	public static Logger getLoggerInstance(Class logClass) 
	{
		Logger logger = null;
		try 
		{
			logger = Logger.getLogger(logClass);
			
			PatternLayout layout = new PatternLayout();
			layout.setConversionPattern("[%d{dd MMM yyyy HH:mm:ss:SSS}] (%F:%L) - %m%n");
			String logFile = logPath+ logClass.getSimpleName() + ".log";
			RollingFileAppender appender = new RollingFileAppender(layout,logFile, true);
			appender.setMaxFileSize("1000KB");
			appender.setMaxBackupIndex(10);
			appender.setThreshold(Level.DEBUG);
			
			logger.removeAllAppenders();
			logger.addAppender(appender);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		return logger;
		
	}
}

