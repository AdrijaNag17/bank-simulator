package simulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Pin extends JFrame implements ActionListener {

    private JLabel img, chng, newP, reEnt;
    private JTextField newPin, reEntPin;
    private JButton save, back;
    private String pinNum;

    public Pin(String pinNum){
        this.pinNum = pinNum;

        setTitle("PIN Change");

        chng = new JLabel("Change your PIN");
        chng.setFont(new Font("Raleway",Font.BOLD,35));

        newP = new JLabel("Enter new PIN");
        newP.setFont(new Font("Raleway",Font.BOLD,20));

        reEnt = new JLabel("Re-enter new PIN: ");
        reEnt.setFont(new Font("Raleway",Font.BOLD,20));

        newPin = new JTextField();
        newPin.setFont(new Font("Raleway",Font.BOLD,20));

        reEntPin = new JTextField();
        reEntPin.setFont(new Font("Raleway",Font.BOLD,20));

        save = new JButton("Save");
        save.setFont(new Font("Raleway",Font.BOLD,15));
        save.setBackground(Color.BLACK);
        save.setForeground(Color.white);
        save.addActionListener(this);

        back = new JButton("Back");
        back.setFont(new Font("Raleway",Font.BOLD,15));
        back.setBackground(Color.BLACK);
        back.setForeground(Color.white);
        back.addActionListener(this);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("simulator/icons/pin.png"));
        Image i2 = i1.getImage().getScaledInstance(100,80,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        img = new JLabel(i3);

        chng.setBounds(170, 25, 400, 50);
        img.setBounds(60,20,100,80);
        newP.setBounds(80, 225, 200, 30);
        newPin.setBounds(300, 225, 220, 30);
        reEnt.setBounds(80,275,200,30);
        reEntPin.setBounds(300,275,220,30);
        save.setBounds(300,350,100,30);
        back.setBounds(420,350,100,30);

        add(chng);
        add(img);
        add(newP);
        add(newPin);
        add(reEnt);
        add(reEntPin);
        add(save);
        add(back);

        getContentPane().setBackground(Color.WHITE);
        setSize(600,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){


        try{
            if(ae.getSource() == save){
                String newPass = newPin.getText();
                String checkPass = reEntPin.getText();
                Connect conn = new Connect();
                if(newPass.equals(checkPass)){
                    String q1 = "update bank set pin = '"+newPass+"' where pin = '"+pinNum+"'";
                    String q2 = "update login set pin = '"+ newPass + "' where pin = '"+pinNum+"'";
                    String q3 = "update signup2 set pin = '"+ newPass + "' where pin = '"+pinNum+"'";
                    conn.getStmnt().executeUpdate(q1);
                    conn.getStmnt().executeUpdate(q2);
                    conn.getStmnt().executeUpdate(q3);

                    JOptionPane.showMessageDialog(null, "PIN updated successfully");

                    new Transaction(pinNum).setVisible(true);
                    setVisible(false);
                } else{
                    JOptionPane.showMessageDialog(null,"Re-enter the correct PIN");
                }
            }
            if(ae.getSource() == back){
                new Transaction(pinNum).setVisible(true);
                setVisible(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
