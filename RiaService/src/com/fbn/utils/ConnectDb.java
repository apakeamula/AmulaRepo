package com.fbn.utils;

import org.apache.log4j.Logger;
import java.sql.*;

public class ConnectDb {
private  static final Logger logger = LogGEN.getLoggerInstance(ConnectDb.class);
    public Connection connection (){
        LoadProp loadProp = new LoadProp();
        try {
            Class.forName(loadProp.databaseDriver);
            return  DriverManager.getConnection(loadProp.databaseUrl,loadProp.databaseUser,loadProp.databasePwd);
        }
        catch(Exception e){
            logger.info("Exception: "+e.getMessage());
        }
        return null;
    }
}
