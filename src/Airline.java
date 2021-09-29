import javax.swing.*;
import java.awt.*;

public class Airline extends JFrame {

    JButton loginButton, signupButton, adminLoginButton;

    Airline() {
        //Creating Buttons
        loginButton = new JButton("Login");
        signupButton = new JButton("Sign Up");
        adminLoginButton = new JButton("Admin Login");

        //Editing Button Look
        loginButton.setBackground(Color.CYAN);
        signupButton.setBackground(Color.CYAN);
        adminLoginButton.setBackground(Color.CYAN);

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

    private void setImageBackground(){
        setContentPane(new JPanel(){
            final Image img = Toolkit.getDefaultToolkit().getImage("Images/Back.jpg");

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img,0,0,null);
            }
        });
        pack();
    }

    public static void main(String[] args) {
        new Airline();
    }
}
