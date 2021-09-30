package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static frames.Constraint.setPosition;

public class AdminLogin extends JFrame implements ActionListener {

    private JLabel avatarImage,adminLoginLabel,passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton,gobackButton;
    public AdminLogin() {
        //Initialising Member Variables
        avatarImage = new JLabel(new ImageIcon("Images/Avatar3.png"));
        adminLoginLabel = new JLabel("Admin Login");
        passwordLabel = new JLabel("Enter Password");
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        gobackButton = new JButton("Go Back");

        //Editing Member Variable look
        adminLoginLabel.setFont(new Font("SansSerif",Font.BOLD,24));
        passwordLabel.setFont(new Font("Serif",Font.PLAIN,20));
        passwordLabel.setFont(new Font("Serif",Font.PLAIN,18));
        passwordField.setEchoChar('*');
        loginButton.setPreferredSize(new Dimension(100,25));
        gobackButton.setPreferredSize(new Dimension(100,25));

        //Adding Action Listener to Buttons
        gobackButton.addActionListener(this);

        //Frame Details
        setTitle("Airline Reservation System");
        getContentPane().setBackground(new Color(255,77,77));
        setIconImage(Toolkit.getDefaultToolkit().getImage("Images/Icon.png"));
        setSize(728,452);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setVisible(true);

        //Adding Member variables to Frame
        add(avatarImage,setPosition(0,0,1,5,Constraint.LEFT));
        add(adminLoginLabel,setPosition(1,0));
        add(passwordLabel,setPosition(1,1,Constraint.LEFT));
        add(passwordField,setPosition(1,2,Constraint.LEFT));
        add(loginButton,setPosition(1,3));
        add(gobackButton,setPosition(1,4));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource() == gobackButton ) {
            new Airline();
            dispose();
        }
    }
}
