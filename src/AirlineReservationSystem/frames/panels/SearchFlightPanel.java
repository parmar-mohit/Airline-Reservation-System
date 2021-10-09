package AirlineReservationSystem.frames.panels;

import AirlineReservationSystem.Constraint;
import AirlineReservationSystem.DatabaseCon;
import AirlineReservationSystem.frames.PassengerDetailsFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;

import static AirlineReservationSystem.Constraint.setPosition;

public class SearchFlightPanel extends JPanel implements ActionListener {

    private JLabel fromImageLabel,fromLabel,toImageLabel,toLabel,messageLabel;
    private JComboBox from,to;
    private JRadioButton firstclassRadioButton,businesclassRadioButton,economyclassRadioButton;
    private DefaultTableModel tableModel;
    private JTable table;
    private JScrollPane scrollPane;
    private JButton searchFlightsButton,bookFlightButton;
    private DatabaseCon db;

    public SearchFlightPanel(){
        //Initialising Member Variables
        fromImageLabel = new JLabel(new ImageIcon("Images/LocationAvatar.png"));
        fromLabel = new JLabel("From : ");
        from = new JComboBox();
        toImageLabel = new JLabel(new ImageIcon("Images/LocationAvatar.png"));
        toLabel = new JLabel("To : ");
        to = new JComboBox();
        firstclassRadioButton = new JRadioButton("First Class");
        businesclassRadioButton = new JRadioButton("Business Class");
        economyclassRadioButton = new JRadioButton("Economy Class");
        searchFlightsButton = new JButton("Search Flight");
        messageLabel = new JLabel();
        bookFlightButton = new JButton("Book Flight");

        //Adding RadioButton to ButtonGrp
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(firstclassRadioButton);
        buttonGroup.add(businesclassRadioButton);
        buttonGroup.add(economyclassRadioButton);

        //Filling Combobox
        try{
            db = new DatabaseCon();
            ResultSet result = db.executeQuery("SELECT location FROM locations;");
            while( result.next() ){
                from.addItem(result.getString("location"));
                to.addItem(result.getString("location"));
            }
        }catch(Exception e){
            DatabaseCon.showOptionPane(this,e);
        }finally{
            db.closeConnection();
        }

        //Editing member varibles
        firstclassRadioButton.setBackground(new Color(94,235,228));
        businesclassRadioButton.setBackground(new Color(94,235,228));
        economyclassRadioButton.setBackground(new Color(94,235,228));


        //Adding Listener to Button
        searchFlightsButton.addActionListener(this);
        bookFlightButton.addActionListener(this);

        //Panel Details
        setSize(500,300);
        setBackground(new Color(94,235,228));
        setLayout(new GridBagLayout());

        //Adding member to Panel
        add(fromImageLabel,setPosition(0,0, Constraint.LEFT));
        add(fromLabel,setPosition(1,0,Constraint.LEFT));
        add(from,setPosition(2,0,Constraint.LEFT));
        add(toImageLabel,setPosition(3,0,Constraint.RIGHT));
        add(toLabel,setPosition(4,0,Constraint.RIGHT));
        add(to,setPosition(5,0,Constraint.RIGHT));
        add(firstclassRadioButton,setPosition(0,1,2,1));
        add(businesclassRadioButton,setPosition(3,1,2,1));
        add(economyclassRadioButton,setPosition(5,1,2,1));
        add(searchFlightsButton,setPosition(0,2,6,1));
        add(messageLabel,setPosition(0,4,6,1));
        add(bookFlightButton,setPosition(0,5,6,1));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchFlightsButton) {
            if (scrollPane != null) {
                remove(scrollPane);
            }
            if (e.getSource() == searchFlightsButton) {
                String columns[] = {"Flight ID", "From", "To", "Date"};
                tableModel = new DefaultTableModel(columns, 0);
                table = new JTable(tableModel) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                scrollPane = new JScrollPane(table);
                table.setColumnSelectionAllowed(false);
                scrollPane.setSize(new Dimension(500, 200));
                scrollPane.setPreferredSize(new Dimension(650, 200));
                messageLabel.setText("");
                String source = (String) from.getSelectedItem();
                String destination = (String) to.getSelectedItem();

                if (source.equals(destination)) {
                    messageLabel.setText("From and to cannot be same locations");
                    return;
                }

                String seatClass;
                if (firstclassRadioButton.isSelected()) {
                    seatClass = "firstclass_seats";
                    tableModel.addColumn("First Class Ticket Price");
                } else if (businesclassRadioButton.isSelected()) {
                    seatClass = "businessclass_seats";
                    tableModel.addColumn("Business Class Ticket Price");
                } else if (economyclassRadioButton.isSelected()) {
                    seatClass = "economyclass_seats";
                    tableModel.addColumn("Economy Class Ticket Price");
                } else {
                    messageLabel.setText("Select Class of Seat");
                    return;
                }

                //Fillling Table
                try {
                    db = new DatabaseCon();
                    ResultSet result = db.getFlights(source, destination, seatClass);

                    if (!result.next()) {
                        messageLabel.setText("No Flights Available for Given Search Parameters");
                        return;
                    }

                    do {
                        int flight_id = result.getInt("flight_id");
                        String s = result.getString("source");
                        String d = result.getString("destination");
                        Date boarding_date = result.getDate("boarding_date");
                        int seatPrice;
                        if (seatClass.equals("firstclass_seats")) {
                            seatPrice = result.getInt("firstclass_price");
                        } else if (seatClass.equals("businessclass_seats")) {
                            seatPrice = result.getInt("businessclass_price");
                        } else {
                            seatPrice = result.getInt("economyclass_price");
                        }
                        tableModel.addRow(new Object[]{flight_id, s, d, boarding_date, seatPrice});
                        add(scrollPane, setPosition(0, 3, 6, 1));
                        revalidate();
                        repaint();
                    } while (result.next());

                } catch (Exception excp) {
                    DatabaseCon.showOptionPane(this, excp);
                } finally {
                    db.closeConnection();
                }
            }
        }else if(e.getSource()==bookFlightButton){
            int row = table.getSelectedRow();
            if( row == -1 ){
                messageLabel.setText("Select a Flight");
                return;
            }

            int flightid = (int)tableModel.getValueAt(row,0);
            int price = (int)tableModel.getValueAt(row,4);
            new PassengerDetailsFrame(flightid,price);
        }
    }
}
