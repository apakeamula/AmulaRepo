package com.fbn.riaTransferService;

import com.fbn.utils.*;
import org.apache.log4j.Logger;
import java.sql.*;

public  class RiaTransfer extends Thread implements RiaConstants {
    private static final Logger logger = LogGEN.getLoggerInstance(RiaTransfer.class);

    public void run(){
        moveRiaTxn();
    }
    private void moveRiaTxn (){
        LoadProp loadProp = new LoadProp();
        try {
            ConnectDb connectDb = new ConnectDb();
            Connection con = connectDb.connection();

            Statement statement = con.createStatement();
            Statement statement2 = con.createStatement();
            Statement statement3 = con.createStatement();


            Query query = new Query();
            logger.info(query.getRiaTxn());
            ResultSet resultSet = statement.executeQuery(query.getRiaTxn());
            statement.setMaxRows(10);
            if (!resultSet.isBeforeFirst()) {
                logger.info("No records to process");
                statement.close();
                statement2.close();
                statement3.close();
                con.close();
                return;
            }
            while (resultSet.next()){

               String orderNo = resultSet.getString(ria_OrderNo);
               logger.info("orderno: "+orderNo);
               String  beneAccount = resultSet.getString(ria_beneAccount);
               logger.info("beneAcct: "+beneAccount);
               String beneAmount = resultSet.getString(ria_beneAmount);
               logger.info("beneAmount: "+beneAmount);

                logger.info("About to insert");
                Query query1 = new Query(orderNo,loadProp.ngnTempCreAcct,beneAccount,beneAmount);
                logger.info(query1.insertRiaTxn());
                int insertCount = statement2.executeUpdate(query1.insertRiaTxn());
                logger.info("Done inserting. count = "+ insertCount);
                if (insertCount >= 0){
                    Query query2 = new Query(orderNo);
                    logger.info("update query: "+ query2.updateRiaTxn());
                    int updateCount = statement3.executeUpdate(query2.updateRiaTxn());
                    logger.info("UpdateCount "+ updateCount + ". if Update count is >= 0 query saved successfully");
                }
            }
            statement.close();
            statement2.close();
            statement3.close();
            con.close();

        }
        catch (Exception e){
            logger.error("Exception occurred "+ e.getMessage());
        }

    }

}
