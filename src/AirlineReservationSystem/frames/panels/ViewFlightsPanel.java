package AirlineReservationSystem.frames.panels;

import AirlineReservationSystem.DatabaseCon;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;

import static AirlineReservationSystem.Constraint.setPosition;

public class ViewFlightsPanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private DatabaseCon db;
    private ResultSet result;

    public ViewFlightsPanel() {
        //Initialising Member
        String columns[] = {"Flight ID","From","To","Date","First Class Seats","Business Class Seats","Economy Class Seats"};
        tableModel = new DefaultTableModel(columns,0);
        table = new JTable(tableModel){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        scrollPane = new JScrollPane(table);

        //filling table
        try{
            db = new DatabaseCon();
            result = db.executeQuery("SELECT flight_schedule.*,seat_price.firstclass_seats,seat_price.businessclass_seats,seat_price.economyclass_seats FROM flight_schedule JOIN seat_price ON flight_schedule.flight_id = seat_price.flight_id;");
            while(result.next()){
                tableModel.addRow(new Object[]{result.getInt("flight_id"),result.getString("source"),result.getString("destination"),result.getDate("boarding_date"),result.getInt("firstclass_seats")+"/"+result.getInt("first_seats"),result.getInt("businessclass_seats")+"/"+result.getInt("business_seats"),result.getInt("economyclass_seats")+"/"+result.getInt("economy_seats")});
            }
        }catch(Exception e){
            DatabaseCon.showOptionPane(this,e);
        }finally {
            db.closeConnection();
        }

        //Editing member details
        table.setColumnSelectionAllowed(false);
        table.setRowSelectionAllowed(false);
        scrollPane.setPreferredSize(new Dimension(800,500));

        //panel details
        setSize(500,300);
        setBackground(new Color(94,235,228));
        setLayout(new GridBagLayout());

        //adding member to panel
        add(scrollPane,setPosition(0,0));
    }
}
