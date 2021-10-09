package AirlineReservationSystem.frames;

import AirlineReservationSystem.frames.panels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static AirlineReservationSystem.Constraint.setPosition;

public class AdminFrame extends JFrame implements ActionListener {

    private JButton addLocationButton,scheduleFlightButton,viewFlightsButton,cancelFlightButton,changePasswordButton,logoutButton;
    private JPanel optionPanel;

    public AdminFrame() {
        //Initialising Member Variable
        addLocationButton = new JButton("Add Location");
        scheduleFlightButton = new JButton("Schedule Flight");
        viewFlightsButton = new JButton("View Flights");
        cancelFlightButton = new JButton("Cancel Flight");
        changePasswordButton = new JButton("Change Password");
        logoutButton = new JButton("Logout");

        //Editing member Variables look
        addLocationButton.setPreferredSize(new Dimension(150,25));
        scheduleFlightButton.setPreferredSize(new Dimension(150,25));
        viewFlightsButton.setPreferredSize(new Dimension(150,25));
        cancelFlightButton.setPreferredSize(new Dimension(150,25));
        changePasswordButton.setPreferredSize(new Dimension(150,25));
        logoutButton.setPreferredSize(new Dimension(150,25));

        //adding Listener to Button
        addLocationButton.addActionListener(this);
        scheduleFlightButton.addActionListener(this);
        viewFlightsButton.addActionListener(this);
        cancelFlightButton.addActionListener(this);
        changePasswordButton.addActionListener(this);
        logoutButton.addActionListener(this);

        setTitle("Airline Reservation System");
        getContentPane().setBackground(new Color(255,77,77));
        setIconImage(Toolkit.getDefaultToolkit().getImage("Images/Icon.png"));
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setVisible(true);

        //adding member variables to Frame
        add(addLocationButton,setPosition(0,0));
        add(scheduleFlightButton,setPosition(0,1));
        add(viewFlightsButton,setPosition(0,2));
        add(cancelFlightButton,setPosition(0,3));
        add(changePasswordButton,setPosition(0,4));
        add(logoutButton,setPosition(0,5));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if( optionPanel != null ) {
            remove(optionPanel);
        }

        if( e.getSource() == addLocationButton ) {
            optionPanel = new AddLocationPanel();
        }else if( e.getSource() == scheduleFlightButton ) {
            optionPanel = new ScheduleFlightPanel();
        }else if( e.getSource() == viewFlightsButton ) {
            optionPanel = new ViewFlightsPanel();
        }else if( e.getSource() == cancelFlightButton ){
            optionPanel = new CancelFlightPanel();
        }else if( e.getSource() == changePasswordButton ) {
            optionPanel = new ChangePasswordPanel("admin");
        }else if( e.getSource() == logoutButton ) {
            new Airline();
            dispose();
            return;
        }

        add(optionPanel,setPosition(1,0,1,6));
        optionPanel.setVisible(true);
        revalidate();
        repaint();
    }
}