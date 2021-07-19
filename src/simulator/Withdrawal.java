package simulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Withdrawal extends JFrame implements ActionListener {
    private JLabel img, amt, withdraw;
    private JTextField amtNum;
    private JButton wtdr, back, exit;
    private String pinNum;

    public Withdrawal(String pinNum){
        this.pinNum = pinNum;

        setTitle("Withdraw");

        withdraw = new JLabel("Withdraw");
        withdraw.setFont(new Font("Raleway",Font.BOLD,35));

        amt = new JLabel("Enter Amount: ");
        amt.setFont(new Font("Raleway",Font.BOLD,25));

        amtNum = new JTextField();
        amtNum.setFont(new Font("Raleway",Font.BOLD,20));

        wtdr = new JButton("Withdraw");
        wtdr.setFont(new Font("Raleway",Font.BOLD,14));
        wtdr.setBackground(Color.BLACK);
        wtdr.setForeground(Color.white);
        wtdr.addActionListener(this);

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

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("simulator/icons/withdraw.jpg"));
        Image i2 = i1.getImage().getScaledInstance(90,90,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        img = new JLabel(i3);

        withdraw.setBounds(230, 25, 400, 50);
        img.setBounds(100,25,90,90);
        amt.setBounds(50, 175, 200, 30);
        amtNum.setBounds(300, 175, 250, 30);
        wtdr.setBounds(300,300,100,30);
        back.setBounds(300,350,250,30);
        exit.setBounds(450,300,100,30);

        add(withdraw);
        add(img);
        add(amt);
        add(amtNum);
        add(wtdr);
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
            if(ae.getSource() == wtdr){
                double amount = Double.parseDouble(amtNum.getText());
                Date date = new Date();
                double balance = 0.0;
                Connect conn = new Connect();
                String q = "select * from bank where pin ='"+pinNum+"'";
                ResultSet rs = conn.getStmnt().executeQuery(q);
                while(rs.next()) {
                    if(rs.getString("mode").equals("Deposit")) {
                        balance += rs.getDouble("amount");
                    } else{
                        balance -= rs.getDouble("amount");
                    }
                }
                if(balance > amount) {
                    String q2 = "insert into bank values('"+pinNum+"','"+date+"','Withdrawal','"+amount+"')";
                    conn.getStmnt().executeUpdate(q2);

                    JOptionPane.showMessageDialog(null, "Amount debited successfully");
                    new Transaction(pinNum).setVisible(true);
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Insufficient funds!");
                }
            }
            if(ae.getSource() == back){
                new Transaction(pinNum).setVisible(true);
                setVisible(false);
            }
            if(ae.getSource() == exit){
                System.exit(0);
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}

