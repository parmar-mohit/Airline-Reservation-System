package AirlineReservationSystem.frames.panels;

import AirlineReservationSystem.DatabaseCon;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import static AirlineReservationSystem.Constraint.setPosition;

public class CancelFlightPanel extends JPanel implements ActionListener {

    private JTable table;
    private DefaultTableModel tableModel;
    private JButton cancelFlightButton;
    private JLabel messageLabel;
    private JScrollPane scrollPane;
    private DatabaseCon db;
    private ResultSet result;

    public CancelFlightPanel() {
        //Initialising Member Variables
        String columns[] = {"Flight ID","From","To","Date","First Class Seats","Business Class Seats","Economy Class Seats"};
        tableModel = new DefaultTableModel(columns,0);
        table = new JTable(tableModel){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        scrollPane = new JScrollPane(table);
        cancelFlightButton = new JButton("Cancel Flight");
        messageLabel = new JLabel();

        //flilling table
        try{
            db = new DatabaseCon();
            result = db.executeQuery("SELECT * FROM flight_schedule;");
            while(result.next()){
                tableModel.addRow(new Object[]{result.getInt("flight_id"),result.getString("source"),result.getString("destination"),result.getDate("boarding_date"),result.getInt("first_seats"),result.getInt("business_seats"),result.getInt("economy_seats")});
            }
        }catch(Exception e){
            DatabaseCon.showOptionPane(this,e);
        }finally{
            db.closeConnection();
        }

        //Editing Member details
        table.setColumnSelectionAllowed(false);
        scrollPane.setPreferredSize(new Dimension(800,500));

        //adding action listener to buttons
        cancelFlightButton.addActionListener(this);

        //Panel details
        setBackground(new Color(94,235,228));
        setLayout(new GridBagLayout());

        //adding member to panel
        add(scrollPane,setPosition(0,0));
        add(messageLabel,setPosition(0,1));
        add(cancelFlightButton,setPosition(0,2));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int row = table.getSelectedRow();
        if( row == -1 ){
            messageLabel.setText("Select a Flight");
            return;
        }

        int flightid = (int)tableModel.getValueAt(row,0);
        try{
            db = new DatabaseCon();
            db.cancel_flight(flightid);
            tableModel.removeRow(row);
            messageLabel.setText("Flight Cancelled");
        }catch(Exception excp){
            DatabaseCon.showOptionPane(this,excp);
        }finally {
            db.closeConnection();
        }
    }
}
