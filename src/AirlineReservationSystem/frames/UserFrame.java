package AirlineReservationSystem.frames;

import AirlineReservationSystem.frames.panels.ChangePasswordPanel;
import AirlineReservationSystem.frames.panels.SearchFlightPanel;
import AirlineReservationSystem.frames.panels.TransactionPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static AirlineReservationSystem.Constraint.setPosition;

public class UserFrame extends JFrame implements ActionListener {

    private JLabel avatarLabel,welcomeLabel;
    private JPanel optionPanel;
    private JButton bookTicketButton,transactionDetails,logoutButton,changePasswordButton;
    private String username;

    public UserFrame(String name, String uname){
        //Initialising Member Variables
        username = uname;
        avatarLabel = new JLabel(new ImageIcon("Images/Avatar4.png"));
        welcomeLabel = new JLabel("Welcome "+name);
        bookTicketButton = new JButton("Book Ticket");
        transactionDetails = new JButton("Transaction Details");
        changePasswordButton = new JButton("Change Password");
        logoutButton = new JButton("Logout");

        //Editing Member Variables
        welcomeLabel.setFont(new Font("SansSerif",Font.BOLD,20));

        //Adding ActionLisetner Button
        bookTicketButton.addActionListener(this);
        transactionDetails.addActionListener(this);
        changePasswordButton.addActionListener(this);
        logoutButton.addActionListener(this);

        //Frame Details
        setTitle("Airline Reservation System");
        getContentPane().setBackground(new Color(255,77,77));
        setIconImage(Toolkit.getDefaultToolkit().getImage("Images/Icon.png"));
        setLayout(new GridBagLayout());
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        //Adding Member to Frame
        add(avatarLabel,setPosition(0,0,2,1));
        add(welcomeLabel,setPosition(1,0,2,1));
        add(bookTicketButton,setPosition(0,1));
        add(transactionDetails,setPosition(1,1));
        add(changePasswordButton,setPosition(2,1));
        add(logoutButton,setPosition(3,1));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if( optionPanel != null ) {
            remove(optionPanel);
        }

        if(e.getSource() == bookTicketButton ){
            optionPanel = new SearchFlightPanel(username);
        }else if(e.getSource() == transactionDetails){
            optionPanel = new TransactionPanel(username);
        }else if(e.getSource() == changePasswordButton) {
            optionPanel = new ChangePasswordPanel(username);
        }else if( e.getSource() == logoutButton ){
                new Airline();
                dispose();
                return;
        }

        add(optionPanel,setPosition(0,2,4,1));
        optionPanel.setVisible(true);
        revalidate();
        repaint();
    }
}
