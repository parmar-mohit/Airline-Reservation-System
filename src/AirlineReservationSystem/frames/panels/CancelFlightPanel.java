package AirlineReservationSystem.frames.panels;

import javax.swing.*;
import java.awt.*;

import static AirlineReservationSystem.Constraint.setPosition;

public class CancelFlightPanel extends JPanel {

    private JTable table;
    private JButton cancelFlightButton;
    private JLabel messageLabel;
    private JScrollPane scrollPane;

    public CancelFlightPanel() {
        //Initialising Member Variables
        table = new JTable();
        scrollPane = new JScrollPane(table);
        cancelFlightButton = new JButton("Cancel Flight");
        messageLabel = new JLabel();

        //Panel details
        setBackground(new Color(94,235,228));
        setLayout(new GridBagLayout());

        //adding member to panel
        add(scrollPane,setPosition(0,0));
        add(messageLabel,setPosition(0,1));
        add(cancelFlightButton,setPosition(0,2));
    }
}
