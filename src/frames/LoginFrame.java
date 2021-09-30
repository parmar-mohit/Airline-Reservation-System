package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static frames.Constraint.setPosition;

public class LoginFrame extends JFrame implements ActionListener,ItemListener,MouseListener {

    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JButton loginButton,goBackButton;
    private JLabel avatarImage,backgroundImage;
    private JCheckBox showPasswordCheckbox;

    public LoginFrame() {
        //Initialising members
        backgroundImage = new JLabel(new ImageIcon("Images/Background2.png"));
        usernameTextField = new JTextField("username",15);
        passwordField  = new JPasswordField("password",15);
        loginButton = new JButton("Login");
        goBackButton = new JButton("Go Back");
        avatarImage = new JLabel(new ImageIcon("Images/Avatar1.png"));
        showPasswordCheckbox = new JCheckBox("Show Password");

        //Editing Member variables look
        usernameTextField.setHorizontalAlignment(SwingConstants.CENTER);
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        passwordField.setEchoChar('*');
        showPasswordCheckbox.setBackground(new Color(255,77,77));
        loginButton.setPreferredSize(new Dimension(100,25));
        goBackButton.setPreferredSize(new Dimension(100,25));

        //Adding Listener to Buttons
        usernameTextField.addMouseListener(this);
        passwordField.addMouseListener(this);
        showPasswordCheckbox.addItemListener(this);
        goBackButton.addActionListener(this);

        //Frame Details
        setTitle("Airline Reservation System");
        getContentPane().setBackground(new Color(255,77,77));
        setIconImage(Toolkit.getDefaultToolkit().getImage("Images/Icon.png"));
        setLayout(new GridBagLayout());
        setSize(728,452);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        //Adding Member to Frame
        add(backgroundImage,setPosition(0,0,1,6));
        add(avatarImage,setPosition(1,0));
        add(usernameTextField,setPosition(1,1));
        add(passwordField,setPosition(1,2));
        add(showPasswordCheckbox,setPosition(1,3));
        add(loginButton,setPosition(1,4));
        add(goBackButton,setPosition(1,5));
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
        if( showPasswordCheckbox.isSelected() ) {
            passwordField.setEchoChar((char)0);
        }else {
            passwordField.setEchoChar('*');
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if( e.getSource() == usernameTextField ) {
            usernameTextField.setText("");
        }else if( e.getSource() == passwordField ) {
            passwordField.setText("");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
