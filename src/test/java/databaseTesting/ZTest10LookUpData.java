package databaseTesting;

import java.sql.ResultSet;

public class ZTest10LookUpData {

	public static void main(String[] args) {

        DatabaseUtil.connect();

        try {
        	ResultSet rs = DatabaseUtil.executeQuery(

        			"SELECT S.CUSTOMER_ID, C.CITY_NAME EXPECTED_CITY, " +
        			"T.CITY_NAME TARGET_CITY " +
        			"FROM CUSTOMER_SOURCE_LOOKUP S " +
        			"JOIN CITY_MASTER C ON S.CITY_ID=C.CITY_ID " +
        			"JOIN CUSTOMER_TARGET_LOOKUP T " +
        			"ON S.CUSTOMER_ID=T.CUSTOMER_ID " +
        			"WHERE C.CITY_NAME<>T.CITY_NAME");

        			while(rs.next()){

        			    System.out.println("Customer ID : "
        			            + rs.getInt("CUSTOMER_ID"));

        			    System.out.println("Expected City : "
        			            + rs.getString("EXPECTED_CITY"));

        			    System.out.println("Actual City : "
        			            + rs.getString("TARGET_CITY"));

        			}
        }
        			 catch (Exception e) {

        		            e.printStackTrace();

        		        }

        		        finally {

        		            DatabaseUtil.closeConnection();
        		        }
        }
	
}
