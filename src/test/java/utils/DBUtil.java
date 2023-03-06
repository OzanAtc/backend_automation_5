package utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DBUtil {

    private static String url = ConfigReader.getProperty("DBUrl");
    private static String username = ConfigReader.getProperty("DBUsername");
    private static String password = ConfigReader.getProperty("DBPassword");

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static Connection createDBConnection() {

        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection is successful");
        } catch (SQLException e) {
            System.out.println("Database connection failed");
            e.printStackTrace();
        }
            return connection;
    }

    public static void executeQuery(String query) {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> getColumnNames(String query) {
        List<String> columnNames = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                columnNames.add(columnName);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
            return columnNames;
    }

    public static List<List<Object>> getQueryResultList(String query) {

        executeQuery(query);
        List<List<Object>> rowList = new ArrayList<>();

        //We need to find number of the column to stop our loop at the limit
        ResultSetMetaData resultSetMetaData;
        try {
            //this is giving us a table information
            resultSetMetaData = resultSet.getMetaData();
            // create empty list for each row
            while(resultSet.next()) {
                List<Object> row = new ArrayList<>();

                //resultSetMetaData.getColumnCount() is giving us the number of column
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    // we store each column in a list
                    row.add(resultSet.getObject(i));
                }
                rowList.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowList;
    }

    public static Object getCellValue(String query) {
        /**
         * If we have only one value from one query we use this method because
         * we don't need list of list
         */
        return getQueryResultList(query).get(0).get(0);
        // select first_name from employees
        // where first_name like '%O%'
    }
}
