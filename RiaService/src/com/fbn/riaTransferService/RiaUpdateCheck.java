package com.fbn.riaTransferService;

import com.fbn.utils.ConnectDb;
import com.fbn.utils.LogGEN;
import com.fbn.utils.Query;
import com.fbn.utils.RiaConstants;
import org.apache.log4j.Logger;
import java.sql.*;

public class RiaUpdateCheck implements RiaConstants {
    private static final Logger logger = LogGEN.getLoggerInstance(RiaUpdateCheck.class);
    public void checkRiaStatus (){
        ConnectDb connectDb = new ConnectDb();
        Connection connection = connectDb.connection();
        Query query = new Query();
        try {
            Statement statement = connection.createStatement();
            Statement statement1 = connection.createStatement();
            Statement statement2 = connection.createStatement();
            logger.info(query.getUpdateRiaTxn());
            ResultSet resultSet = statement.executeQuery(query.getUpdateRiaTxn());
            
            //statement.setMaxRows(10);
            if (!resultSet.isBeforeFirst()) {
                logger.info("No records found.");
                statement.close();
                statement1.close();
                statement2.close();
                connection.close();
                return;
            }

            while (resultSet.next()){
               String orderNo = resultSet.getString(ria_OrderNo);
               logger.info("orderno: "+orderNo);


               Query query1 = new Query(orderNo);
              logger.info("update Query: "+query1.getUpdateStatus());
              try {
                  ResultSet resultSet1 = statement1.executeQuery(query1.getUpdateStatus());
                  resultSet1.next();
                  String status = resultSet1.getString(mto_status);
                  logger.info("status: " + status);
                  if (status.equalsIgnoreCase(paid)) {

                      Query query2 = new Query(orderNo);
                      logger.info(query2.updateRiaPostStatus());
                      int updateRiaTable = statement2.executeUpdate(query2.updateRiaPostStatus());
                      if (updateRiaTable >= 0)
                          logger.info("Query updated successfully");
                  }
              } catch (Exception e){
                  logger.info("No new records to process");
                 continue;
                  
              }
            }

            statement.close();
            statement1.close();
            statement2.close();
            connection.close();
        }
        catch (Exception e){
            logger.error("Exception occurred "+ e.getMessage());
        }
    }
}
