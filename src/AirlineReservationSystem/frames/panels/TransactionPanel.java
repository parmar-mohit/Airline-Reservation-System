package AirlineReservationSystem.frames.panels;

import AirlineReservationSystem.DatabaseCon;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

import static AirlineReservationSystem.Constraint.setPosition;

public class TransactionPanel extends JPanel implements ActionListener {

    private JTable table;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private JLabel messageLabel;
    private DatabaseCon db;
    private String username;
    private JButton cancelTicketButton;

    public TransactionPanel(String uname){
        //Initialising Member Variables
        username = uname;
        String columns[] ={"Flight ID","Passenger First Name","Passenger Last Name","Gender","Age","Seat Type","Email","Contact No"};
        tableModel = new DefaultTableModel(columns,0);
        table = new JTable(tableModel){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        scrollPane = new JScrollPane(table);
        messageLabel = new JLabel();
        cancelTicketButton = new JButton("Cancel Ticket");

        //editing members
        scrollPane.setPreferredSize(new Dimension(600,400));
        messageLabel.setPreferredSize(new Dimension(600,25));

        //addingListener to Button
        cancelTicketButton.addActionListener(this);

        fillTable();

        //panel details
        setSize(500,300);
        setBackground(new Color(94,235,228));
        setLayout(new GridBagLayout());

        //adding member to panel
        add(scrollPane,setPosition(0,0));
        add(messageLabel,setPosition(0,1));
        add(cancelTicketButton,setPosition(0,2));
    }

    private void fillTable(){
        while( tableModel.getRowCount() > 0 ){
            tableModel.removeRow(0);
        }

        //filling table
        try{
            db = new DatabaseCon();
            ResultSet result = db.executeQuery("SELECT * FROM tickets_booked WHERE username=\""+username+"\";");
            if(!result.next()){
                messageLabel.setText("No Details Found");
                return;
            }

            do{
                int flightid = result.getInt("flight_id");
                String firstname = result.getString("passengar_firstname");
                String lastname = result.getString("passengar_lastname");
                String gender = result.getString("passengar_gender");
                int age = result.getInt("passengar_age");
                String seat = result.getString("seat_type");
                String email = result.getString("email");
                String contact = result.getString("contactno");
                tableModel.addRow(new Object[]{flightid,firstname,lastname,gender,age,seat,email,contact});
                revalidate();
                repaint();
            }while(result.next());
        }catch(Exception e){
            DatabaseCon.showOptionPane(this,e);
        }finally{
            db.closeConnection();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int row = table.getSelectedRow();

        if( row == -1 ) {
            messageLabel.setText("Select A Ticket");
            return;
        }

        try {
            db = new DatabaseCon();
            Date date = db.getFlightDate((int)tableModel.getValueAt(row,0));
            Date today = new Date();
            if( date.before(today) ){
                messageLabel.setText("Flight has Already Departed,Cannot Cancel Tickets now");
            }else {
                db.cancel_tickets(tableModel, row);
                messageLabel.setText("Ticket Cancelled");
                fillTable();
            }
        }catch( Exception excp){
            DatabaseCon.showOptionPane(this,excp);
        }finally {
            db.closeConnection();
        }
    }
}
