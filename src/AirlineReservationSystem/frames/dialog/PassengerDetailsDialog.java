package AirlineReservationSystem.frames.dialog;

import AirlineReservationSystem.CreatePdf;
import AirlineReservationSystem.DatabaseCon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static AirlineReservationSystem.Constraint.setPosition;

public class PassengerDetailsDialog extends JDialog implements KeyListener, ActionListener {

    private JLabel firstnameLabel,lastnameLabel,emailLabel,noLabel,ageLabel,priceLabel,messageLabel;
    private JTextField firstnameTextField,lastnameTextField,emailTextField,noTextField,ageTextField,priceTextField;
    private JRadioButton maleRadioButton,femaleRadioButton;
    private JButton goBackButton,bookTicketButton;
    private DatabaseCon db;
    private JFrame parent;
    private int flight_id;
    private String username,seat;

    public PassengerDetailsDialog(JFrame parent,int flight_id, String username,Integer price,String seat){
        super(parent);
        this.parent = parent;
        this.flight_id = flight_id;
        this.seat = seat;
        this.username = username;
        firstnameLabel = new JLabel("Passenger First Name : ");
        firstnameTextField = new JTextField(20);
        lastnameLabel = new JLabel("Passenger Last Name : ");
        lastnameTextField = new JTextField(20);
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
        messageLabel = new JLabel();
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
        goBackButton.addActionListener(this);
        bookTicketButton.addActionListener(this);

        //Frame Details
        setTitle("Airline Reservation System");
        getContentPane().setBackground(new Color(255,77,77));
        setIconImage(Toolkit.getDefaultToolkit().getImage("Images/Icon.png"));
        setLayout(new GridBagLayout());
        setSize(800,450);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);

        //Adding Members to Frame
        add(firstnameLabel,setPosition(0,0));
        add(firstnameTextField,setPosition(1,0));
        add(lastnameLabel,setPosition(2,0));
        add(lastnameTextField,setPosition(3,0));
        add(emailLabel,setPosition(0,1));
        add(emailTextField,setPosition(1,1));
        add(noLabel,setPosition(2,1));
        add(noTextField,setPosition(3,1));
        add(priceLabel,setPosition(0,2));
        add(priceTextField,setPosition(1,2));
        add(ageLabel,setPosition(2,2));
        add(ageTextField,setPosition(3,2));
        add(maleRadioButton,setPosition(0,3,2,1));
        add(femaleRadioButton,setPosition(2,3,2,1));
        add(messageLabel,setPosition(0,4,4,1));
        add(goBackButton,setPosition(0,5,2,1));
        add(bookTicketButton,setPosition(2,5,2,1));
        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bookTicketButton){
            String firstname = firstnameTextField.getText();
            String lastname = lastnameTextField.getText();

            if(firstname.isEmpty() || lastname.isEmpty()){
                messageLabel.setText("Enter Name");
                return;
            }

            String email = emailTextField.getText();
            if(email.isEmpty()){
                messageLabel.setText("Enter Email");
                return;
            }

            String contactNo = noTextField.getText();
            if( contactNo.isEmpty()){
                messageLabel.setText("Enter Contact No");
                return;
            }

            if(ageTextField.getText().isEmpty()){
                messageLabel.setText("Enter Age");
                return;
            }
            int age = Integer.parseInt(ageTextField.getText());

            String gender;
            if( maleRadioButton.isSelected() ){
                gender = "M";
            }else if(femaleRadioButton.isSelected()){
                gender = "F";
            }else{
                messageLabel.setText("Select Gender");
                return;
            }

            try{
                db = new DatabaseCon();
                db.book_ticket(flight_id,username,firstname,lastname,gender,age,seat,email,contactNo);
                new CreatePdf(flight_id,username,firstname,lastname,gender,age,seat,email,contactNo);
                new TicketBookedDialog(parent,flight_id);
                dispose();
            }catch(Exception excp){
                DatabaseCon.showOptionPane(parent,excp);
            }
        }else if(e.getSource() == goBackButton ){
            dispose();
        }
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
