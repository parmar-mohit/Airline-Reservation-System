package AirlineReservationSystem.frames;

import AirlineReservationSystem.Constraint;
import AirlineReservationSystem.DatabaseCon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import static AirlineReservationSystem.Constraint.setPosition;

public class SignUpFrame extends JFrame implements ActionListener, ItemListener {

    private JLabel avatarImage,signUpLabel,messageLabel,firstnameLabel,lastnameLabel,usernameLabel,emailLabel,passwordLabel,confirmPasswordLabel;
    private JTextField firstnameTextField,lastnameTextField,usernameTextField,emailTextField;
    private JPasswordField passwordField,confirmPasswordField;
    private JCheckBox showPasswordCheckbox;
    private JButton signupButton,goBackButton;
    private DatabaseCon db;

    SignUpFrame() {
        //Initialising Member Variables
        avatarImage = new JLabel(new ImageIcon("Images/Avatar2.png"));
        signUpLabel = new JLabel("Sign Up");
        firstnameLabel = new JLabel("Firstname");
        firstnameTextField = new JTextField(15);
        lastnameLabel = new JLabel("Lastname");
        lastnameTextField = new JTextField(15);
        emailLabel = new JLabel("Email ID");
        emailTextField = new JTextField(15);
        usernameLabel = new JLabel("Username");
        usernameTextField = new JTextField(15);
        passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField(15);
        showPasswordCheckbox = new JCheckBox("Show Password");
        confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordField = new JPasswordField(15);
        messageLabel = new JLabel();
        signupButton = new JButton("Sign Up");
        goBackButton = new JButton("Go Back");

        //Editing Member Variables look
        signUpLabel.setFont(new Font("Serif", Font.BOLD,20));
        firstnameTextField.setHorizontalAlignment(JTextField.CENTER);
        lastnameTextField.setHorizontalAlignment(JTextField.CENTER);
        emailTextField.setHorizontalAlignment(JTextField.CENTER);
        usernameTextField.setHorizontalAlignment(JTextField.CENTER);
        passwordField.setHorizontalAlignment(JTextField.CENTER);
        confirmPasswordField.setHorizontalAlignment(JTextField.CENTER);
        passwordField.setEchoChar('*');
        showPasswordCheckbox.setBackground(new Color(255,77,77));
        confirmPasswordField.setEchoChar('*');
        signupButton.setPreferredSize(new Dimension(100,25));
        goBackButton.setPreferredSize(new Dimension(100,25));

        //Adding Listener to Buttons
        showPasswordCheckbox.addItemListener(this);
        signupButton.addActionListener(this);
        goBackButton.addActionListener(this);

        //Frame Details
        setTitle("Airline Reservation System");
        setIconImage(Toolkit.getDefaultToolkit().getImage("Images/Icon.png"));
        getContentPane().setBackground(new Color(255,77,77));
        setLayout(new GridBagLayout());
        setSize(728,452);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        //Adding member variables to Frame
        add(avatarImage,setPosition(0,0,1,11));
        add(signUpLabel,setPosition(1,0,2,1));
        add(firstnameLabel,setPosition(1,1));
        add(lastnameLabel,setPosition(2,1));
        add(firstnameTextField,setPosition(1,2));
        add(lastnameTextField,setPosition(2,2));
        add(emailLabel,setPosition(1,3));
        add(usernameLabel,setPosition(2,3));
        add(emailTextField,setPosition(1,4));
        add(usernameTextField,setPosition(2,4));
        add(passwordLabel,setPosition(1,5));
        add(confirmPasswordLabel,setPosition(2,5));
        add(passwordField,setPosition(1,6));
        add(confirmPasswordField,setPosition(2,6));
        add(showPasswordCheckbox,setPosition(1,7,2,1, Constraint.LEFT));
        add(messageLabel,setPosition(1,8,2,1));
        add(signupButton,setPosition(1,9,2,1));
        add(goBackButton,setPosition(1,10,2,1));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource() == signupButton ){
            String firstname = firstnameTextField.getText();
            String lastname = lastnameTextField.getText();
            if( firstname.isEmpty() || lastname.isEmpty() ){
                messageLabel.setText("Enter Name");
                return;
            }

            String email = emailTextField.getText();
            if( email.isEmpty() ){
                messageLabel.setText("Enter Email");
                return;
            }

            String username = usernameTextField.getText();
            if( username.isEmpty() ){
                messageLabel.setText("Enter Username");
                return;
            }

            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            if(password.isEmpty() || confirmPassword.isEmpty() ){
                messageLabel.setText("Enter Password");
                return;
            }else if( !Constraint.isValidPassword(password) ) {
                messageLabel.setText("Password must contain 1 Uppercase letter,1 Lowercase letter and 1 Number");
                return;
            }else if( !password.equals(confirmPassword) ){
                messageLabel.setText("Passwords Do not match");
                return;
            }

            try{
                db = new DatabaseCon();
                if( db.checkExist("username","user_info","\""+username+"\"")){
                    messageLabel.setText("Username is already in use, Please enter a different username");
                    return;
                }else{
                    db.user_info(firstname,lastname,email,username,password);
                    messageLabel.setText("User Added");
                    new UserFrame(firstname,username);
                    dispose();
                }
            }catch(Exception excp){
                DatabaseCon.showOptionPane(this,excp);
            }finally{
                db.closeConnection();
            }
        }else if( e.getSource() == goBackButton ) {
            new Airline();
            dispose();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == showPasswordCheckbox ) {
            if( showPasswordCheckbox.isSelected() ) {
                passwordField.setEchoChar((char)0);
                confirmPasswordField.setEchoChar((char)0);
            }else {
                passwordField.setEchoChar('*');
                confirmPasswordField.setEchoChar('*');
            }
        }
    }
}
