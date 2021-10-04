package AirlineReservationSystem;

import java.sql.*;

public class DatabaseCon {

    private static final String URL = "jdbc:mysql://localhost:3306/airline";
    private static final String USERNAME = "airline_user";
    private static final String PASSWORD = "airline_pass";
    private Connection db;

    public DatabaseCon() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver"); //Loading SQL Connector Driver
        db = DriverManager.getConnection(URL,USERNAME,PASSWORD); //Creating Connection with database
    }

    public void closeConnection(){
        try {
            db.close();
        }catch(Exception e ) {
            System.out.println(e);
        }
    }

    public ResultSet executeQuery(String query) {
        try {
            Statement statement = db.createStatement();
             return statement.executeQuery(query);
        }catch(Exception e){
            System.out.println("Query Execution Failed");
            System.out.println(e);
            return null;
        }
    }

    public Integer executeUpdate(String query) {
        try{
            Statement statement = db.createStatement();
            return statement.executeUpdate(query);
        }catch(Exception e){
            System.out.println("Query Execution Failed");
            System.out.println(e);
            return null;
        }
    }

    public Boolean checkExist(String column,String table,String entry) {
        try{
            String query = "SELECT EXISTS (SELECT "+column+" FROM "+table+" WHERE "+column+"=\""+entry+"\");";
            Statement statement = db.createStatement();
            ResultSet result = statement.executeQuery(query);
            result.next();

            return result.getBoolean(1);
        }catch(Exception e){
            System.out.println("Query Execution Failed");
            System.out.println(e);
            return null;
        }
    }
}
