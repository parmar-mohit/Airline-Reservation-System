package AirlineReservationSystem.frames.dialog;

import javax.swing.*;
import java.awt.*;

import static AirlineReservationSystem.Constraint.setPosition;

public class TicketBookedDialog extends JDialog  {

    public TicketBookedDialog(JFrame parent,int flightid){
        super(parent);
        //dialog details
        setTitle("Airline Reservation System");
        getContentPane().setBackground(new Color(255,77,77));
        setIconImage(Toolkit.getDefaultToolkit().getImage("Images/Icon.png"));
        setLayout(new GridBagLayout());
        setSize(400,250);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);

        add(new JLabel("Your Ticket is booked on Flight Id : "+flightid),setPosition(0,0));
        revalidate();
        repaint();
    }
}
