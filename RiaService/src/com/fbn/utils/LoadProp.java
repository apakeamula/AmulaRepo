package com.fbn.utils;

import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.UnsupportedEncodingException;
import java.util.Properties;
import org.apache.log4j.Logger;

 public class LoadProp implements RiaConstants {
	 private static final Logger logger = LogGEN.getLoggerInstance(LoadProp.class);
     public  String databaseUrl;
     public  String databaseDriver;
     public  String databaseUser;
     public  String databasePwd;
     public  String ngnTempCreAcct;


      {
         try  {
             logger.info("Start loading properties file");
             Properties prop = new Properties();
             InputStream in = new FileInputStream(configPath);
             prop.load(in);
             databaseUser = prop.getProperty(getDatabaseUser);
             databaseUrl = prop.getProperty(getDatabaseUrl);
             databaseDriver = prop.getProperty(getDatabaseDriver);
             databasePwd = prop.getProperty(getDatabasePwd);
             ngnTempCreAcct = prop.getProperty(getNgnTempCreAcct);
             //suspenseAccount = prop.getProperty(getSusAcct);

                    } catch (UnsupportedEncodingException ex) {
             ex.printStackTrace();
             logger.error("Error occurred in load property file - Unsupported Exception --" + ex.getMessage());
                    }  catch (FileNotFoundException ex) {
             ex.printStackTrace();
             logger.error("Error occurred in load property file - FileNotFoundException Exception --" + ex.getMessage());
                    }  catch (IOException ex) {
             ex.printStackTrace();
             logger.error("Error occurred in load property file - IOException Exception --" + ex.getMessage());
                    }
            }

     }


