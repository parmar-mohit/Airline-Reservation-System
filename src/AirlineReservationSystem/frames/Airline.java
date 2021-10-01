package AirlineReservationSystem.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Airline extends JFrame implements ActionListener {

    private JButton loginButton, signupButton, adminLoginButton;

    public Airline() {
        //Creating Buttons
        loginButton = new JButton("Login");
        signupButton = new JButton("Sign Up");
        adminLoginButton = new JButton("Admin Login");

        //Editing Button Look
        loginButton.setBackground(Color.CYAN);
        signupButton.setBackground(Color.CYAN);
        adminLoginButton.setBackground(Color.CYAN);

        //Adding ActionListener to Buttons
        loginButton.addActionListener(this);
        signupButton.addActionListener(this);
        adminLoginButton.addActionListener(this);

        //Frame Details
        setTitle("Airline Reservation System");
        setIconImage(Toolkit.getDefaultToolkit().getImage("Images/Icon.png"));
        setImageBackground();
        setSize(728,452);
        setLayout(new FlowLayout(FlowLayout.CENTER,75,175));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        //Adding Buttons to Frame
        add(loginButton);
        add(signupButton);
        add(adminLoginButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource() == loginButton ) {
            new LoginFrame();
            dispose();
        }else if( e.getSource() == signupButton ) {
            new SignUpFrame();
            dispose();
        }else if( e.getSource() == adminLoginButton ) {
            new AdminLogin();
            dispose();
        }
    }

    private void setImageBackground(){
        getContentPane().setBackground(new Color(255,77,77));
        setContentPane(new JPanel(){
            final Image img = Toolkit.getDefaultToolkit().getImage("Images/Background1.jpg");

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img,0,0,null);
            }
        });
        pack();
    }
}
