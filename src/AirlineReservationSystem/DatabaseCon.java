package AirlineReservationSystem;

import javax.swing.*;
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
            String query = "SELECT EXISTS (SELECT "+column+" FROM "+table+" WHERE "+column+"="+entry+");";
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

    public void schedule_flight(int flightId,String from,String to,Date date,int firstSeats,int businessSeats,int economySeats,int firstPrice,int businessPrice,int econonmyPrice){
        try{
            PreparedStatement preparedStatement1 = db.prepareStatement("INSERT INTO flight_schedule VALUE(?,?,?,?,?,?,?);");
            PreparedStatement preparedStatement2 = db.prepareStatement("INSERT INTO seat_price VALUE(?,?,?,?,?,?,?);");

            preparedStatement1.setInt(1,flightId);
            preparedStatement1.setString(2,from);
            preparedStatement1.setString(3,to);
            preparedStatement1.setDate(4,date);
            preparedStatement1.setInt(5,firstSeats);
            preparedStatement1.setInt(6,businessSeats);
            preparedStatement1.setInt(7,economySeats);

            preparedStatement2.setInt(1,flightId);
            preparedStatement2.setInt(2,firstSeats);
            preparedStatement2.setInt(3,firstPrice);
            preparedStatement2.setInt(4,businessSeats);
            preparedStatement2.setInt(5,businessPrice);
            preparedStatement2.setInt(6,economySeats);
            preparedStatement2.setInt(7,econonmyPrice);

            preparedStatement1.executeUpdate();
            preparedStatement2.executeUpdate();
        }catch(Exception e){
            System.out.println("Query Execution Failed");
            System.out.println(e);
        }
    }

    public void change_password(String username,String password){
        try{
            PreparedStatement preparedStatement =  db.prepareStatement("UPDATE user_info SET password=? WHERE username=?");
            preparedStatement.setString(1,password);
            preparedStatement.setString(2,username);
            preparedStatement.executeUpdate();
        }catch( Exception e){
            System.out.println("Query Execution Failed");
            System.out.println(e);
        }
    }

    public void cancel_flight(int flightid){
        try{
            PreparedStatement preparedStatement = db.prepareStatement("DELETE FROM flight_schedule WHERE flight_id=?");
            preparedStatement.setInt(1,flightid);
            preparedStatement.executeUpdate();
            preparedStatement = db.prepareStatement("DELETE FROM seat_price WHERE flight_id=?");
            preparedStatement.setInt(1,flightid);
            preparedStatement.executeUpdate();
        }catch(Exception e){
            System.out.println("Query Execution Failed");
            System.out.println(e);
        }
    }

    public static void showOptionPane(JComponent parent,Exception e){
        System.out.println("Connection to Database Failed");
        System.out.println();
        JOptionPane messageBox = new JOptionPane();
        messageBox.showMessageDialog(parent,"We are unable to Connect to database right now.Please try again later","Connection Failed",JOptionPane.ERROR_MESSAGE);
    }

    public static void showOptionPane(JFrame parent,Exception e){
        System.out.println("Connection to Database Failed");
        System.out.println();
        JOptionPane messageBox = new JOptionPane();
        messageBox.showMessageDialog(parent,"We are unable to Connect to database right now.Please try again later","Connection Failed",JOptionPane.ERROR_MESSAGE);
    }
}
