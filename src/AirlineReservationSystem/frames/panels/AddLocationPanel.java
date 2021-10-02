package AirlineReservationSystem.frames.panels;

import javax.swing.*;
import java.awt.*;

import static AirlineReservationSystem.Constraint.setPosition;

public class AddLocationPanel extends JPanel {

    private JLabel enterLocationLabel,messageLabel;
    private JTextField locationTextField;
    private JButton addLocationButton;

    public AddLocationPanel() {
        //Initialising Member Variables
        enterLocationLabel = new JLabel("Enter the Location you want to add");
        locationTextField = new JTextField(20);
        addLocationButton = new JButton("Add Location");
        messageLabel = new JLabel("");

        //Panel details
        setBackground(new Color(94,235,228));
        setLayout(new GridBagLayout());

        //Adding Members to Panel
        add(enterLocationLabel,setPosition(0,0));
        add(locationTextField,setPosition(0,1));
        add(addLocationButton,setPosition(0,2));
        add(messageLabel,setPosition(0,3));
    }
}
