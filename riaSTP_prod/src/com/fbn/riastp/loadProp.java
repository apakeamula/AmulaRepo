 package com.fbn.riastp;

 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.UnsupportedEncodingException;
 import java.text.SimpleDateFormat;
 import java.util.Properties;
 import org.apache.log4j.Logger;

 public class loadProp {
     private static final Logger logFile = Logger.getLogger(loadProp.class);
        public static String FETCHINTERVAL;
        public static String CLEARINGHOURS;
        public static String CLEARINGDAYS;
        public static String USEDAYS;
        public static String MONEYTRANSFERSOL;
        public static String POSTINTERVAL;
        public static String VALIDATEINTERVAL;
        public static String CONNECTIP;
        public static String CONNECTPORT;
        public static String CONNECTDCCID;
        public static String CONNECTPAN;
        public static String CONNECTTYPE;
        public static String CONNECTTIMEOUT;
        public static String ALLOWDEBITFREEZE;
        public static String USENAMEMATCHING;
        public static String NAMEMATCHLEVEL;
        public static String RETRYFAILEDTRAN;
        public static String NOOFRETRY;
        public static String CURRENCYALLOWED;
        public static String SUSPENSEACCOUNTS;
        public static String CURRENCYALLOWEDFORNEFT;
        public static String NEFTOUTWARDSUSPENSE;
        public static String BANKBIC;
        public static String BANKSORT;
        public static String NEFTDEBITACCOUNT;
        public static String NAMEVALIDATIONTIME;
        public static String POSTBRIDGEKEY;
        public static String POSTBRIDGEAPPNAME;
        public static String SPECIALSCHEMECODES;
        public static String INTERBANKSWITCH;
        public static String NGNTEMPCREACCT; //Variable-Field for the NGN Temporary credit account
        public static String NGN;   //NGN Currency-Field 
        public static String USD;  //USD Currency-Field 
    
     static {
         try  {
             logFile.info("Start loading properties file");
             Properties prop = new Properties();
//             InputStream in = new FileInputStream("C:\\Users\\sn027512\\Documents\\NetBeansProjects\\riaSTP\\src\\com\\fbn\\riastp\\riaprop.properties");
             InputStream in = new FileInputStream("E:\\RIASTPAPplication\\riaprop.properties");
             prop.load(in);
             FETCHINTERVAL = prop.getProperty("FETCHINTERVAL");
             POSTINTERVAL = prop.getProperty("POSTINTERVAL");
             VALIDATEINTERVAL = prop.getProperty("VALIDATEINTERVAL");
             CONNECTIP = prop.getProperty("CONNECTIP");
             CONNECTPORT = prop.getProperty("CONNECTPORT");
             CONNECTDCCID = prop.getProperty("CONNECTDCCID");
             CONNECTPAN = prop.getProperty("CONNECTPAN");
             CONNECTTYPE = prop.getProperty("CONNECTTYPE");
             CONNECTTIMEOUT = prop.getProperty("CONNECTTIMEOUT");
             ALLOWDEBITFREEZE = prop.getProperty("ALLOWDEBITFREEZE");
             USENAMEMATCHING = prop.getProperty("USENAMEMATCHING");
             NAMEMATCHLEVEL = prop.getProperty("NAMEMATCHLEVEL");
             RETRYFAILEDTRAN = prop.getProperty("RETRYFAILEDTRAN");
             NOOFRETRY = prop.getProperty("NOOFRETRY");
             CURRENCYALLOWED = prop.getProperty("CURRENCYALLOWED");
             SUSPENSEACCOUNTS = prop.getProperty("SUSPENSEACCOUNTS");
             CURRENCYALLOWEDFORNEFT = prop.getProperty("CURRENCYALLOWEDFORNEFT");
             NEFTOUTWARDSUSPENSE = prop.getProperty("NEFTOUTWARDSUSPENSE");
             BANKBIC = prop.getProperty("BANKBIC");
             BANKSORT = prop.getProperty("BANKSORT");
             MONEYTRANSFERSOL = prop.getProperty("MONEYTRANSFERSOL");
             CLEARINGHOURS = prop.getProperty("CLEARINGHOURS");
             CLEARINGDAYS = prop.getProperty("CLEARINGDAYS");
             USEDAYS = prop.getProperty("USEDAYS");
             NEFTDEBITACCOUNT = prop.getProperty("NEFTDEBITACCOUNT");
             ENDPOINTURL = prop.getProperty("ENDPOINTURL");
             ENDPOINTTIMEOUT = prop.getProperty("ENDPOINTTIMEOUT");
             DATABASEUSER = prop.getProperty("DATABASEUSER");
             DATABASEURL = prop.getProperty("DATABASEURL");
             DATABASEDRIVER = prop.getProperty("DATABASEDRIVER");
             DATABASEPWD = prop.getProperty("DATABASEPWD");
             NEFTCOMMISIONACCOUNTBRANCH = prop.getProperty("NEFTCOMMISIONACCOUNTBRANCH");
             NEFTCOMMISIONACCOUNTHEADOFFICE = prop.getProperty("NEFTCOMMISIONACCOUNTHEADOFFICE");
             NEFTVAT = prop.getProperty("NEFTVAT");
             NEFTSUSPENSEACCOUNT = prop.getProperty("NEFTSUSPENSEACCOUNT");
             NAPSTSSACCOUNT = prop.getProperty("NAPSTSSACCOUNT");
             NAPSENABLED = prop.getProperty("NAPSENABLED");
             FBNPOSTINGHOURS = prop.getProperty("FBNPOSTINGHOURS");
             NAMEVALIDATIONTIME = prop.getProperty("NAMEVALIDATIONTIME");
             POSTBRIDGEKEY = prop.getProperty("POSTBRIDGEKEY");
             POSTBRIDGEAPPNAME = prop.getProperty("POSTBRIDGEAPPNAME");
             SPECIALSCHEMECODES = prop.getProperty("SPECIALSCHEMECODES");
             INTERBANKSWITCH = prop.getProperty("INTERBANKSWITCH");
             NGNTEMPCREACCT = prop.getProperty("NGNTEMPCREACCT"); //assign from ria.proprties
             NGN = prop.getProperty("NGN"); //assign from ria.proprties
             USD = prop.getProperty("USD"); //assign from ria.proprties
                    } catch (UnsupportedEncodingException ex) {
             ex.printStackTrace();
             logFile.error("Error occurred in load property file - Unsupported Exception --" + ex.getMessage());
                    }  catch (FileNotFoundException ex) {
             ex.printStackTrace();
             logFile.error("Error occurred in load property file - FileNotFoundException Exception --" + ex.getMessage());
                    }  catch (IOException ex) {
             ex.printStackTrace();
             logFile.error("Error occurred in load property file - IOException Exception --" + ex.getMessage());
                    }
            }
    
        private static int curr = 5;
        public static String[] CURRENCYLIST = new String[curr];
        public static String[] SUSPENSELIST = new String[curr];
        public static String[] NEFTCURRENCYLIST = new String[curr];
        public static String[] NEFTSUSPENSELIST = new String[curr];
        public static SimpleDateFormat SDF = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy");
        public static SimpleDateFormat SDF2 = new SimpleDateFormat("yyyyMMdd");
        public static SimpleDateFormat SDF3 = new SimpleDateFormat("HHmmss");
        public static SimpleDateFormat SDF4 = new SimpleDateFormat("yyMMddHHmmssZ");
       public static SimpleDateFormat SDF5 = new SimpleDateFormat("dd/MMM/yyyy");
        public static SimpleDateFormat SDF6 = new SimpleDateFormat("yyMMddHHmmss");
        public static String ENDPOINTURL;
        public static String ENDPOINTTIMEOUT;
        public static String DATABASEURL;
        public static String DATABASEDRIVER;
        public static String DATABASEUSER;
        public static String DATABASEPWD;
        public static String NEFTCOMMISIONACCOUNTBRANCH;
        public static String NEFTCOMMISIONACCOUNTHEADOFFICE;
        public static String NEFTVAT;
        public static String NEFTSUSPENSEACCOUNT;
        public static String NAPSENABLED;
        public static String NAPSTSSACCOUNT;
        public static String FBNPOSTINGHOURS;
     }


