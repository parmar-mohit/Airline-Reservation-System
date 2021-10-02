package AirlineReservationSystem.frames.panels;

import AirlineReservationSystem.Constraint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import static AirlineReservationSystem.Constraint.setPosition;

public class ChangePasswordPanel extends JPanel implements ItemListener {

    private JLabel oldPasswordLabel,newPasswordLabel,confirmPasswordLabel,messageLabel;
    private JPasswordField oldPasswordField,newPasswordField,confirmPasswordField;
    private JCheckBox showPasswordCheckbox;
    private JButton changePasswordButton;

    public ChangePasswordPanel() {
        //Initialising Member Variables
        oldPasswordLabel = new JLabel("Enter Old Password : ");
        oldPasswordField = new JPasswordField(20);
        newPasswordLabel = new JLabel("Enter New Password : ");
        newPasswordField = new JPasswordField(20);
        confirmPasswordLabel = new JLabel("Confirm Password : ");
        confirmPasswordField = new JPasswordField(20);
        showPasswordCheckbox = new JCheckBox("Show Passsword");
        messageLabel = new JLabel("");
        changePasswordButton = new JButton("Change Password");

        //Editing member variables attributes
        oldPasswordField.setEchoChar('*');
        newPasswordField.setEchoChar('*');
        confirmPasswordField.setEchoChar('*');
        showPasswordCheckbox.setBackground(new Color(94,235,228));

        //Editing panel details
        setBackground(new Color(94,235,228));
        setLayout(new GridBagLayout());

        //Adding Listener to member variables
        showPasswordCheckbox.addItemListener(this);

        //adding member to panel
        add(oldPasswordLabel,setPosition(0,0, Constraint.RIGHT));
        add(oldPasswordField,setPosition(1,0,Constraint.LEFT));
        add(newPasswordLabel,setPosition(0,1,Constraint.RIGHT));
        add(newPasswordField,setPosition(1,1,Constraint.LEFT));
        add(confirmPasswordLabel,setPosition(0,2,Constraint.RIGHT));
        add(confirmPasswordField,setPosition(1,2,Constraint.LEFT));
        add(showPasswordCheckbox,setPosition(0,3,Constraint.LEFT));
        add(messageLabel,setPosition(0,4,2,1));
        add(changePasswordButton,setPosition(0,4,2,1));
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if( showPasswordCheckbox.isSelected() ) {
            oldPasswordField.setEchoChar((char)0);
            newPasswordField.setEchoChar((char)0);
            confirmPasswordField.setEchoChar((char)0);
        }else{
            oldPasswordField.setEchoChar('*');
            newPasswordField.setEchoChar('*');
            confirmPasswordField.setEchoChar('*');
        }
    }
}