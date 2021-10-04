package AirlineReservationSystem.frames.panels;

import javax.swing.*;
import java.awt.*;

import static AirlineReservationSystem.Constraint.setPosition;

public class ViewFlightsPanel extends JPanel {

    private JTable table;
    private JScrollPane scrollPane;

    public ViewFlightsPanel() {
        //Initialising Member
        table = new JTable();
        scrollPane = new JScrollPane(table);

        //panel details
        setBackground(new Color(94,235,228));
        setLayout(new GridBagLayout());

        //adding member to panel
        add(scrollPane,setPosition(0,0));
    }
}
