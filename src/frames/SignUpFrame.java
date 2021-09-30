package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import static frames.Constraint.setPosition;

public class SignUpFrame extends JFrame implements ActionListener, ItemListener {

    JLabel avatarImage,signUpLabel,messageLabel,firstnameLabel,lastnameLabel,usernameLabel,emailLabel,passwordLabel,confirmPasswordLabel;
    JTextField firstnameTextField,lastnameTextField,usernameTextField,emailTextField;
    JPasswordField passwordField,confirmPasswordField;
    JCheckBox showPasswordCheckbox;
    JButton signupButton,goBackButton;

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
        add(showPasswordCheckbox,setPosition(1,7,2,1,Constraint.LEFT));
        add(messageLabel,setPosition(1,8,2,1));
        add(signupButton,setPosition(1,9,2,1));
        add(goBackButton,setPosition(1,10,2,1));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource() == goBackButton ) {
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
