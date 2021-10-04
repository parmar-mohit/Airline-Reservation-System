package AirlineReservationSystem.frames.panels;

import AirlineReservationSystem.DatabaseCon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static AirlineReservationSystem.Constraint.setPosition;

public class AddLocationPanel extends JPanel implements ActionListener {

    private JLabel enterLocationLabel,messageLabel;
    private JTextField locationTextField;
    private JButton addLocationButton;
    private DatabaseCon db;

    public AddLocationPanel() {
        //Initialising Member Variables
        enterLocationLabel = new JLabel("Enter the Location you want to add");
        locationTextField = new JTextField(20);
        addLocationButton = new JButton("Add Location");
        messageLabel = new JLabel("");

        //adding listener to buttons
        addLocationButton.addActionListener(this);

        //Panel details
        setBackground(new Color(94,235,228));
        setLayout(new GridBagLayout());

        //Adding Members to Panel
        add(enterLocationLabel,setPosition(0,0));
        add(locationTextField,setPosition(0,1));
        add(addLocationButton,setPosition(0,2));
        add(messageLabel,setPosition(0,3));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String location = locationTextField.getText();
        try {
            db = new DatabaseCon();
            Boolean exist = db.checkExist("location", "locations", location);
            if(exist != null ) {
                if (exist) {
                    messageLabel.setText("Location already exists in Database");
                } else {
                    db.executeUpdate("INSERT INTO locations VALUE(\"" + location + "\");");
                    messageLabel.setText("Location Added to Database");
                }
            }else{
                throw new Exception();
            }
        }catch(Exception excp){
            System.out.println("Connection to Database Failed");
            System.out.println(excp);
            JOptionPane messageBox = new JOptionPane();
            messageBox.showMessageDialog(this,"We are unable to Connect to database right now.Please try again later","Connection Failed",JOptionPane.ERROR_MESSAGE);
        }finally {
            db.closeConnection();
        }
    }
}
