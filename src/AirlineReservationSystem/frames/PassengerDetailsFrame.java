package AirlineReservationSystem.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static AirlineReservationSystem.Constraint.setPosition;

public class PassengerDetailsFrame extends JFrame implements KeyListener {

    private JLabel nameLabel,emailLabel,noLabel,ageLabel,priceLabel;
    private JTextField nameTextField,emailTextField,noTextField,ageTextField,priceTextField;
    private JRadioButton maleRadioButton,femaleRadioButton;
    private JButton goBackButton,bookTicketButton;

    public PassengerDetailsFrame(int flight_id,Integer price){
        nameLabel = new JLabel("Passenger Name : ");
        nameTextField = new JTextField(20);
        emailLabel = new JLabel("Passenger Email : ");
        emailTextField = new JTextField(20);
        noLabel = new JLabel("Passenger Contact No : ");
        noTextField = new JTextField(20);
        priceLabel = new JLabel("Ticket Price : ");
        priceTextField = new JTextField(price.toString(),20);
        ageLabel = new JLabel("Age : ");
        ageTextField = new JTextField(20);
        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");
        goBackButton = new JButton("Go Back");
        bookTicketButton = new JButton("Book Ticket");

        //Buttongrp for RadioButton
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(maleRadioButton);
        buttonGroup.add(femaleRadioButton);

        //Editing Member Variables
        priceTextField.setEditable(false);
        maleRadioButton.setBackground(new Color(255,77,77));
        femaleRadioButton.setBackground(new Color(255,77,77));

        //Adding Listener to Members
        noTextField.addKeyListener(this);
        ageTextField.addKeyListener(this);

        //Frame Details
        setTitle("Airline Reservation System");
        getContentPane().setBackground(new Color(255,77,77));
        setIconImage(Toolkit.getDefaultToolkit().getImage("Images/Icon.png"));
        setLayout(new GridBagLayout());
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        //Adding Members to Frame
        add(nameLabel,setPosition(0,0));
        add(nameTextField,setPosition(1,0));
        add(emailLabel,setPosition(2,0));
        add(emailTextField,setPosition(3,0));
        add(noLabel,setPosition(0,1));
        add(noTextField,setPosition(1,1));
        add(priceLabel,setPosition(2,1));
        add(priceTextField,setPosition(3,1));
        add(ageLabel,setPosition(0,2));
        add(ageTextField,setPosition(1,2));
        add(maleRadioButton,setPosition(2,2));
        add(femaleRadioButton,setPosition(3,2));
        add(goBackButton,setPosition(0,3,2,1));
        add(bookTicketButton,setPosition(2,3,2,1));
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
