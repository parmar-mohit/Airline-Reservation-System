package AirlineReservationSystem;

import javax.swing.*;
import javax.swing.table.TableModel;
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

    public ResultSet executeQuery(String query) throws Exception {
        Statement statement = db.createStatement();
        return statement.executeQuery(query);
    }

    public Integer executeUpdate(String query) throws Exception{
        Statement statement = db.createStatement();
        return statement.executeUpdate(query);
    }

    public Boolean checkExist(String column,String table,String entry) throws Exception{
        String query = "SELECT EXISTS (SELECT "+column+" FROM "+table+" WHERE "+column+"="+entry+");";
        Statement statement = db.createStatement();
        ResultSet result = statement.executeQuery(query);
        result.next();

        return result.getBoolean(1);
    }

    public void schedule_flight(int flightId,String from,String to,Date date,int firstSeats,int businessSeats,int economySeats,int firstPrice,int businessPrice,int econonmyPrice) throws Exception {
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
    }

    public void change_password(String username,String password) throws Exception {
        PreparedStatement preparedStatement =  db.prepareStatement("UPDATE user_info SET password=? WHERE username=?");
        preparedStatement.setString(1,password);
        preparedStatement.setString(2,username);
        preparedStatement.executeUpdate();
    }

    public void cancel_flight(int flightid) throws Exception {
        PreparedStatement preparedStatement = db.prepareStatement("DELETE FROM flight_schedule WHERE flight_id=?");
        preparedStatement.setInt(1,flightid);
        preparedStatement.executeUpdate();
        preparedStatement = db.prepareStatement("DELETE FROM seat_price WHERE flight_id=?");
        preparedStatement.setInt(1,flightid);
        preparedStatement.executeUpdate();
    }

    public void user_info(String firstname,String lastname,String email,String username,String password) throws Exception {
        PreparedStatement preparedStatement = db.prepareStatement("INSERT INTO user_info VALUE(?,?,?,?,?);");
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,firstname);
        preparedStatement.setString(3,lastname);
        preparedStatement.setString(4,password);
        preparedStatement.setString(5,email);
        preparedStatement.executeUpdate();
    }

    public ResultSet getFlights(String from,String to,String seatClass) throws Exception {
        Date currentDate = new Date(new java.util.Date().getTime());
        PreparedStatement preparedStatement = db.prepareStatement("SELECT * FROM flight_schedule JOIN seat_price on flight_schedule.flight_id=seat_price.flight_id WHERE boarding_date > ? AND "+seatClass+" > 1 AND source=? AND destination=? ORDER BY boarding_date");
        preparedStatement.setDate(1,currentDate);
        preparedStatement.setString(2,from);
        preparedStatement.setString(3,to);
        return preparedStatement.executeQuery();
    }

    public void book_ticket(int flight_id,String username,String firstname,String lastname,String gender,int age,String seat,String email,String contact) throws Exception {
        PreparedStatement preparedStatement = db.prepareStatement("INSERT INTO tickets_booked VALUE(?,?,?,?,?,?,?,?,?);");
        preparedStatement.setInt(1, flight_id);
        preparedStatement.setString(2, username);
        preparedStatement.setString(3, firstname);
        preparedStatement.setString(4, lastname);
        preparedStatement.setString(5, gender);
        preparedStatement.setInt(6, age);
        preparedStatement.setString(7, seat);
        preparedStatement.setString(8, email);
        preparedStatement.setString(9, contact);
        preparedStatement.executeUpdate();
        if (seat.equals("First")) {
            db.createStatement().executeUpdate("UPDATE seat_price SET firstclass_seats = firstclass_seats - 1 WHERE flight_id=" + flight_id + ";");
        } else if (seat.equals("Business")) {
            db.createStatement().executeUpdate("UPDATE seat_price SET businessclass_seats = businessclass_seats - 1 WHERE flight_id=" + flight_id + ";");
        } else {
            db.createStatement().executeUpdate("UPDATE seat_price SET economyclass_seats = economyclass_seats - 1 WHERE flight_id=" + flight_id + ";");
        }
    }

    public void cancel_tickets(TableModel tableModel,int row) throws Exception {
        PreparedStatement preparedStatement = db.prepareStatement("DELETE FROM tickets_booked WHERE flight_id=? AND passengar_firstname=? AND passengar_lastname=? AND passengar_gender=? AND passengar_age=? AND seat_type=? AND email=? AND contactno=?;");
        preparedStatement.setInt(1,Integer.parseInt(tableModel.getValueAt(row,0)+""));
        preparedStatement.setString(2,tableModel.getValueAt(row,1)+"");
        preparedStatement.setString(3,tableModel.getValueAt(row,2)+"");
        preparedStatement.setString(4,tableModel.getValueAt(row,3)+"");
        preparedStatement.setInt(5,Integer.parseInt(tableModel.getValueAt(row,4)+""));
        preparedStatement.setString(6,tableModel.getValueAt(row,5)+"");
        preparedStatement.setString(7,tableModel.getValueAt(row,6)+"");
        preparedStatement.setString(8,tableModel.getValueAt(row,7)+"");
        preparedStatement.executeUpdate();

        String seat = tableModel.getValueAt(row,5)+"";
        int flight_id = (int)tableModel.getValueAt(row,0);
        if (seat.equals("First")) {
            db.createStatement().executeUpdate("UPDATE seat_price SET firstclass_seats = firstclass_seats + 1 WHERE flight_id=" + flight_id + ";");
        } else if (seat.equals("Business")) {
            db.createStatement().executeUpdate("UPDATE seat_price SET businessclass_seats = businessclass_seats + 1 WHERE flight_id=" + flight_id + ";");
        } else {
            db.createStatement().executeUpdate("UPDATE seat_price SET economyclass_seats = economyclass_seats + 1 WHERE flight_id=" + flight_id + ";");
        }

    }

     public Date getFlightDate(int flightid) throws Exception {
        PreparedStatement preparedStatement= db.prepareStatement("SELECT boarding_date FROM flight_schedule WHERE flight_id = ?;");
        preparedStatement.setInt(1,flightid);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getDate("boarding_date");
    }

    public static void showOptionPane(JComponent parent,Exception e){
        System.out.println("Connection to Database Failed");
        System.out.println(e);
        JOptionPane messageBox = new JOptionPane();
        messageBox.showMessageDialog(parent,"We are unable to Connect to database right now.Please try again later","Connection Failed",JOptionPane.ERROR_MESSAGE);
    }

    public static void showOptionPane(JFrame parent,Exception e){
        System.out.println("Connection to Database Failed");
        System.out.println(e);
        JOptionPane messageBox = new JOptionPane();
        messageBox.showMessageDialog(parent,"We are unable to Connect to database right now.Please try again later","Connection Failed",JOptionPane.ERROR_MESSAGE);
    }
}
