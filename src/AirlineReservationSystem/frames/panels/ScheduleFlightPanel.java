package AirlineReservationSystem.frames.panels;

import AirlineReservationSystem.Constraint;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static AirlineReservationSystem.Constraint.setPosition;

public class ScheduleFlightPanel extends JPanel implements KeyListener {

    private JLabel scheduleFlightLabel,flightIdLabel,fromImageLabel,fromLabel,toImageLabel,toLabel,dateImageLabel,dateLabel;
    private JLabel seatDetailsLabel,firstClassLabel,businessClassLabel,economyClassLabel,seatsAvailableLabel,priceLabel,messageLabel;
    private JButton scheduleFlightButton;
    private JTextField flightIDTextField,firstSeatsTextField,businessSeatsTextField,economySeatsTextField,firstPriceTextField,businessPriceTextField,economyPriceTextField;
    private JComboBox from,to;
    private JDateChooser date;

    public ScheduleFlightPanel() {
        //Initialising Member Variables
        scheduleFlightLabel = new JLabel("Schedule Flight");
        flightIdLabel = new JLabel("Flight ID : ");
        flightIDTextField = new JTextField(15);
        fromImageLabel = new JLabel(new ImageIcon("Images/LocationAvatar.png"));
        fromLabel = new JLabel("From : ");
        from = new JComboBox();
        toImageLabel = new JLabel(new ImageIcon("Images/LocationAvatar.png"));
        toLabel = new JLabel("To : ");
        to = new JComboBox();
        dateImageLabel = new JLabel(new ImageIcon("Images/CalendarVector.png"));
        dateLabel = new JLabel("Date : ");
        date = new JDateChooser();
        seatDetailsLabel = new JLabel("Seat Details");
        firstClassLabel = new JLabel("First Class");
        businessClassLabel = new JLabel("Business Class");
        economyClassLabel = new JLabel("Economy Class");
        seatsAvailableLabel = new JLabel("Seats Available : ");
        firstSeatsTextField = new JTextField(7);
        businessSeatsTextField = new JTextField(7);
        economySeatsTextField = new JTextField(7);
        priceLabel = new JLabel("Price : ");
        firstPriceTextField = new JTextField(10);
        businessPriceTextField = new JTextField(10);
        economyPriceTextField = new JTextField(10);
        messageLabel = new JLabel("");
        scheduleFlightButton = new JButton("Schedule Flight");

        //forcing to input only numbers in flightid,seats,available and price
        flightIDTextField.addKeyListener(this);
        firstSeatsTextField.addKeyListener(this);
        businessSeatsTextField.addKeyListener(this);
        economySeatsTextField.addKeyListener(this);
        firstPriceTextField.addKeyListener(this);
        businessPriceTextField.addKeyListener(this);
        economyPriceTextField.addKeyListener(this);


        //Editing member variables
        scheduleFlightLabel.setFont(new Font("SansSerif",Font.BOLD,24));
        seatDetailsLabel.setFont(new Font("Serif",Font.PLAIN,20));

        //Panel Details
        setBackground(new Color(94,235,228));
        setLayout(new GridBagLayout());


        //Adding member variables to panel
        add(scheduleFlightLabel,setPosition(0,0,6,1));
        add(flightIdLabel,setPosition(0,1,3,1,Constraint.RIGHT));
        add(flightIDTextField,setPosition(3,1,4,1,Constraint.LEFT));
        add(fromImageLabel,setPosition(0,2, Constraint.LEFT));
        add(fromLabel,setPosition(1,2,Constraint.LEFT));
        add(from,setPosition(2,2,Constraint.LEFT));
        add(toImageLabel,setPosition(3,2,Constraint.RIGHT));
        add(toLabel,setPosition(4,2,Constraint.RIGHT));
        add(to,setPosition(5,2,Constraint.RIGHT));
        add(dateImageLabel,setPosition(1,3,Constraint.RIGHT));
        add(dateLabel,setPosition(2,3,Constraint.RIGHT));
        add(date,setPosition(3,3,Constraint.LEFT));
        add(seatDetailsLabel,setPosition(0,4,6,1));
        add(firstClassLabel,setPosition(2,5));
        add(businessClassLabel,setPosition(3,5));
        add(economyClassLabel,setPosition(4,5,2,1));
        add(seatsAvailableLabel,setPosition(0,6,2,1));
        add(firstSeatsTextField,setPosition(2,6));
        add(businessSeatsTextField,setPosition(3,6));
        add(economySeatsTextField,setPosition(4,6,2,1));
        add(priceLabel,setPosition(0,7,2,1));
        add(firstPriceTextField,setPosition(2,7));
        add(businessPriceTextField,setPosition(3,7));
        add(economyPriceTextField,setPosition(4,7,2,1));
        add(messageLabel,setPosition(0,8,6,1));
        add(scheduleFlightButton,setPosition(0,9,6,1));
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if( !Character.isDigit(e.getKeyChar() ) ) {
            e.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
