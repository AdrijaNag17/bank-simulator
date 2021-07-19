package simulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Transaction extends JFrame implements ActionListener {

    private JLabel img, select;
    private JButton dpst, wdrl, pinC, enqy, mini, exit;
    private String pinNum;

    public Transaction(String pinNum){
        this.pinNum = pinNum;

        setTitle("Transactions");

        select = new JLabel("Please select transaction type");
        select.setFont(new Font("Raleway",Font.BOLD,25));

        dpst = new JButton("Deposit");
        dpst.setFont(new Font("Raleway",Font.BOLD,18));
        dpst.setBackground(Color.BLACK);
        dpst.setForeground(Color.white);
        dpst.addActionListener(this);

        wdrl = new JButton("Withdrawal");
        wdrl.setFont(new Font("Raleway",Font.BOLD,18));
        wdrl.setBackground(Color.BLACK);
        wdrl.setForeground(Color.white);
        wdrl.addActionListener(this);

        pinC = new JButton("Pin Change");
        pinC.setFont(new Font("Raleway",Font.BOLD,18));
        pinC.setBackground(Color.BLACK);
        pinC.setForeground(Color.white);
        pinC.addActionListener(this);

        enqy = new JButton("Balance Enquiry");
        enqy.setFont(new Font("Raleway",Font.BOLD,18));
        enqy.setBackground(Color.BLACK);
        enqy.setForeground(Color.white);
        enqy.addActionListener(this);

        mini = new JButton("Mini Statement");
        mini.setFont(new Font("Raleway",Font.BOLD,18));
        mini.setBackground(Color.BLACK);
        mini.setForeground(Color.white);
        mini.addActionListener(this);

        exit = new JButton("Exit");
        exit.setFont(new Font("Raleway",Font.BOLD,18));
        exit.setBackground(Color.BLACK);
        exit.setForeground(Color.white);
        exit.addActionListener(this);

        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("simulator/icons/transaction.jpg"));
        Image i2 = i1.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        img = new JLabel(i3);

        select.setBounds(120,30,700,30);
        img.setBounds(30,20,70,70);
        dpst.setBounds(80,120,200,50);
        wdrl.setBounds(300,120,200,50);
        pinC.setBounds(80,200,200,50);
        enqy.setBounds(300,200,200,50);
        mini.setBounds(80,280,200,50);
        exit.setBounds(300,280,200,50);


        add(select);add(img);add(dpst);add(wdrl);
        add(pinC);add(enqy);add(exit);add(mini);

        getContentPane().setBackground(Color.WHITE);
        setSize(600,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == dpst) {
            new Deposit(pinNum).setVisible(true);
            setVisible(false);
        }
        if (ae.getSource() == wdrl) {
            new Withdrawal(pinNum).setVisible(true);
            setVisible(false);
        }
        if (ae.getSource() == pinC) {
            new Pin(pinNum).setVisible(true);
            setVisible(false);
        }
        if (ae.getSource() == exit) {
            System.exit(0);
        }
        if (ae.getSource() == mini) {
            new Mini(pinNum).setVisible(true);
        }
        if (ae.getSource() == enqy) {
            try {
                double balance = 0.0;
                Connect conn = new Connect();
                String q = "select * from bank where pin ='" + pinNum + "'";
                ResultSet rs = conn.getStmnt().executeQuery(q);
                while (rs.next()) {
                    if (rs.getString("mode").equals("Deposit")) {
                        balance += rs.getDouble("amount");
                    } else {
                        balance -= rs.getDouble("amount");
                    }
                }
                JOptionPane.showMessageDialog(null, "Your balance: " + balance);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
