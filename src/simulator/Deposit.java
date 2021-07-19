package simulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {

    private JLabel img, deposit, amt;
    private JTextField amtNum;
    private JButton dpst, back, exit;
    private String pinNum;

    public Deposit(String pinNum){
        this.pinNum = pinNum;

        setTitle("Deposit");

        deposit = new JLabel("Deposit");
        deposit.setFont(new Font("Raleway",Font.BOLD,35));

        amt = new JLabel("Enter Amount: ");
        amt.setFont(new Font("Raleway",Font.BOLD,25));

        amtNum = new JTextField();
        amtNum.setFont(new Font("Raleway",Font.BOLD,20));

        dpst = new JButton("Deposit");
        dpst.setFont(new Font("Raleway",Font.BOLD,14));
        dpst.setBackground(Color.BLACK);
        dpst.setForeground(Color.white);
        dpst.addActionListener(this);

        back = new JButton("Back");
        back.setFont(new Font("Raleway",Font.BOLD,14));
        back.setBackground(Color.BLACK);
        back.setForeground(Color.white);
        back.addActionListener(this);

        exit = new JButton("Exit");
        exit.setFont(new Font("Raleway",Font.BOLD,14));
        exit.setBackground(Color.BLACK);
        exit.setForeground(Color.white);
        exit.addActionListener(this);

        getContentPane().setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("simulator/icons/deposit.jpg"));
        Image i2 = i1.getImage().getScaledInstance(90,90,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        img = new JLabel(i3);

        deposit.setBounds(250, 25, 400, 50);
        img.setBounds(100,25,90,90);
        amt.setBounds(50, 175, 200, 30);
        amtNum.setBounds(300, 175, 250, 30);
        dpst.setBounds(300,300,100,30);
        back.setBounds(300,350,250,30);
        exit.setBounds(450,300,100,30);

        add(deposit);
        add(img);
        add(amt);
        add(amtNum);
        add(dpst);
        add(back);
        add(exit);

        getContentPane().setBackground(Color.WHITE);
        setSize(600,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){


        try{
            if(ae.getSource() == dpst){
                double amount = Double.parseDouble(amtNum.getText());
                Date date = new Date();
                Connect conn = new Connect();
                String q = "insert into bank values('"+pinNum+"','"+date+"','Deposit','"+amount+"')";
                conn.getStmnt().executeUpdate(q);
                JOptionPane.showMessageDialog(null,"Amount deposited successfully");

                new Transaction(pinNum).setVisible(true);
                setVisible(false);
            } else{
                    JOptionPane.showMessageDialog(null,"Invalid PIN");
            }
            if(ae.getSource() == back){
                new Transaction(pinNum).setVisible(true);
                setVisible(false);
            }
            if(ae.getSource() == exit){
            System.exit(0);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
