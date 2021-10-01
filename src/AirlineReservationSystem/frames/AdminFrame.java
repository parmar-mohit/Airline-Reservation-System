package AirlineReservationSystem.frames;

import javax.swing.*;
import java.awt.*;

public class AdminFrame extends JFrame {
    public AdminFrame() {
        setTitle("Airline Reservation System");
        getContentPane().setBackground(new Color(255,77,77));
        setIconImage(Toolkit.getDefaultToolkit().getImage("Images/Icon.png"));
        setSize(728,452);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
