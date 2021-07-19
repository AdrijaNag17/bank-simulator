package simulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    private JLabel img, bnk, card, pin;
    private JTextField cardNo;
    private JPasswordField pinNum;
    private JButton login, signup, clear;

    public Login(){

        setTitle("Banking Simulator");
        bnk = new JLabel("Banking Simulator");
        bnk.setFont(new Font("Raleway",Font.BOLD, 35));

        card = new JLabel("Card No.: ");
        card.setFont(new Font("Raleway", Font.BOLD, 25));

        pin = new JLabel("PIN: ");
        pin.setFont(new Font("Raleway", Font.BOLD, 25));

        cardNo = new JTextField(15);
        cardNo.setFont(new Font("Raleway", Font.PLAIN, 20));

        pinNum = new JPasswordField(15);
        pinNum.setFont(new Font("Raleway", Font.PLAIN, 20));

        login = new JButton("Login");
        login.setFont(new Font("Raleway", Font.BOLD, 14));
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);

        signup = new JButton("Sign Up");
        signup.setFont(new Font("Raleway", Font.BOLD, 14));
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.WHITE);

        clear = new JButton("Clear All");
        clear.setFont(new Font("Raleway", Font.BOLD, 14));
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);

        getContentPane().setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("simulator/icons/bank.jpg"));
        Image i2 = i1.getImage().getScaledInstance(90,90,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        img = new JLabel(i3);

        bnk.setBounds(170, 25, 400, 50);
        img.setBounds(60,20,90,90);
        card.setBounds(100, 175, 150, 30);
        pin.setBounds(100, 225, 150, 30);
        cardNo.setBounds(250, 175, 250, 30);
        pinNum.setBounds(250, 225, 250, 30);
        login.setBounds(250,300,100,30);
        signup.setBounds(250,350,250,30);
        clear.setBounds(400,300,100,30);

        add(bnk);
        add(img);
        add(card);
        add(pin);
        add(cardNo);
        add(pinNum);
        add(login);
        add(signup);
        add(clear);

        login.addActionListener(this);
        signup.addActionListener(this);
        clear.addActionListener(this);

        getContentPane().setBackground(Color.WHITE);

        setSize(600,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        try{

            if(ae.getSource() == login){
                String cardNo = this.cardNo.getText();
                String pass = pinNum.getText();
                Connect con = new Connect();
                String query = "select * from login where cardno = '" + cardNo + "' and pin = '" + pass + "'";
                ResultSet rs = con.getStmnt().executeQuery(query);
                if(rs.next()){
                    new Transaction(pass).setVisible(true);
                    setVisible(false);
                } else{
                    JOptionPane.showMessageDialog(null,"Incorrect card number or password");
                }
            }
            if(ae.getSource() == clear){
                this.cardNo.setText("");
                pinNum.setText("");
            }
            if(ae.getSource() == signup){
                new Signup().setVisible(true);
                setVisible(false);
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args){
        Login loginPage = new Login();
        loginPage.setVisible(true);
    }

}
