package simulator;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Mini extends JFrame {

    private String pinNum;
    private JLabel stmt, card, trns, blnc;

    public Mini(String pinNum){
        this.pinNum = pinNum;

        stmt = new JLabel("Mini Statement");
        card = new JLabel();
        trns = new JLabel();
        blnc = new JLabel();

        try{
            Connect conn = new Connect();
            ResultSet rs = conn.getStmnt().executeQuery("select * from login where pin = '"+pinNum+"'");
            while(rs.next()){
                card.setText("Card No.:  "+ rs.getString("cardno").substring(0,4)+"XXXXXXXXXXX"+
                        rs.getString("cardno").substring(15));
            }

            rs = conn.getStmnt().executeQuery("select * from bank where pin = '"+pinNum+"'");
            while(rs.next()){
                trns.setText(trns.getText()+"<html>"+rs.getString("date")+"&nbsp;&nbsp;&nbsp;"+
                        rs.getString("mode")+"&nbsp;&nbsp;&nbsp;&nbsp;"+rs.getDouble("amount")+ "<br><br><html>" );
            }
            double balance = 0.0;
            String q = "select * from bank where pin ='"+pinNum+"'";
            rs = conn.getStmnt().executeQuery(q);
            while(rs.next()) {
                if(rs.getString("mode").equals("Deposit")) {
                    balance += rs.getDouble("amount");
                } else{
                    balance -= rs.getDouble("amount");
                }
            }
            blnc.setText("Balance: "+balance);
        } catch (SQLException e){
            e.printStackTrace();
        }

        getContentPane().setLayout(null);

        stmt.setBounds(140,10,200,20);
        card.setBounds(100,50,400,20);
        trns.setBounds(50,90,400,300);
        blnc.setBounds(140,400,200,30);
        add(stmt);
        add(card);
        add(trns);
        add(blnc);

        setSize(400,500);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
