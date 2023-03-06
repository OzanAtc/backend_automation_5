package database;

import java.sql.*;
public class DatabaseConnection {

    public static void main(String[] args) throws SQLException {

        /**
         * In order to connect to the database, we need our URL, username, password and query
         * NOTE : This can be the interview question (How do you connect your database ? )
         */

        String url = "jdbc:oracle:thin:@techglobal.cup7q3kvh5as.us-east-2.rds.amazonaws.com:1521/TGDEVQA";
        String username = "ozan";
        String password = "$ozan123!";
        String query = "select * from employees";

        // Create a connection to the database with the parameters we stored

        Connection connection = DriverManager.getConnection(url,username,password);

        System.out.println("Database connection is successful");
        /**
         * Statement keeps the connection between DB and automation
         * to send queries
         */
        Statement statement = connection.createStatement(); // It's a bound between your connection and we will create once this

        // ResultSet is sending the query to the database and get the result. It returns a single values, like table data. Itself is not enough
        ResultSet resultSet = statement.executeQuery(query);

        /**
         * ResultSetMetaData gives the information about the table
         * You can't simply print the column values. We need to call them with iterations.
         */
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData(); // We will do everything to fetch more data information

        System.out.println("Number of the columns" + resultSetMetaData.getColumnCount());
        System.out.println("Column name: " + resultSetMetaData.getColumnName(1));

        while(resultSet.next()) {
            System.out.println(resultSet.getString("FIRST_NAME"));
            System.out.println(resultSet.getString("LAST_NAME"));
        }
    }
}