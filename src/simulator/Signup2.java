package simulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Random;

public class Signup2 extends JFrame implements ActionListener {

    private JLabel img, form, page2, acType, card, cardNo, pin, pinX, service;
    private int formNo;
    private int fourthFour = getCardFour();
    private JRadioButton savings, current, fd, rd;
    private JCheckBox atm, mbl, chq, netBnk, alrt, eStmt, decl;
    private JButton sbmt, cncl;

    public Signup2(int formNo){
        this.formNo = formNo;

        setTitle("New Account Application Form- Page 2");

        form = new JLabel("Form No.: " + Integer.toString(formNo));
        form.setFont(new Font("Raleway", Font.BOLD, 14));

        page2 = new JLabel("Page 2: Account Details");
        page2.setFont(new Font("Raleway", Font.BOLD, 25));

        acType = new JLabel("Account Type: ");
        acType.setFont(new Font("Raleway", Font.BOLD, 18));

        savings = new JRadioButton("Savings Account");
        savings.setFont(new Font("Raleway", Font.BOLD, 15));
        savings.setBackground(Color.WHITE);

        current = new JRadioButton("Current Account");
        current.setFont(new Font("Raleway", Font.BOLD, 15));
        current.setBackground(Color.WHITE);

        fd = new JRadioButton("Fixed Deposit Account");
        fd.setFont(new Font("Raleway", Font.BOLD, 15));
        fd.setBackground(Color.WHITE);

        rd = new JRadioButton("Recurring Deposit Account");
        rd.setFont(new Font("Raleway", Font.BOLD, 15));
        rd.setBackground(Color.WHITE);

        ButtonGroup accGroup = new ButtonGroup();
        accGroup.add(savings);
        accGroup.add(current);
        accGroup.add(fd);
        accGroup.add(rd);

        card = new JLabel("Card No.: ");
        card.setFont(new Font("Raleway", Font.BOLD, 18));

        cardNo = new JLabel("XXXX-XXXX-XXXX-" + fourthFour);
        cardNo.setFont(new Font("Raleway", Font.BOLD, 15));

        pin = new JLabel("PIN: ");
        pin.setFont(new Font("Raleway", Font.BOLD, 18));

        pinX = new JLabel("XXXX");
        pinX.setFont(new Font("Raleway", Font.BOLD, 15));

        service = new JLabel("Services Required: ");
        service.setFont(new Font("Raleway", Font.BOLD, 18));

        atm = new JCheckBox("ATM Card");
        atm.setFont(new Font("Raleway", Font.BOLD, 15));
        atm.setBackground(Color.WHITE);

        netBnk = new JCheckBox("Internet Banking");
        netBnk.setFont(new Font("Raleway", Font.BOLD, 15));
        netBnk.setBackground(Color.WHITE);

        mbl = new JCheckBox("Mobile Banking");
        mbl.setFont(new Font("Raleway", Font.BOLD, 15));
        mbl.setBackground(Color.WHITE);

        alrt = new JCheckBox("Email Alerts");
        alrt.setFont(new Font("Raleway", Font.BOLD, 15));
        alrt.setBackground(Color.WHITE);

        chq = new JCheckBox("Cheque Book");
        chq.setFont(new Font("Raleway", Font.BOLD, 15));
        chq.setBackground(Color.WHITE);

        eStmt = new JCheckBox("e- Statement");
        eStmt.setFont(new Font("Raleway", Font.BOLD, 15));
        eStmt.setBackground(Color.WHITE);

        decl = new JCheckBox("I declare that the details are correct " +
                "to the best of my knowledge");
        decl.setFont(new Font("Raleway", Font.BOLD, 14));
        decl.setBackground(Color.WHITE);

        sbmt = new JButton("Submit");
        sbmt.setFont(new Font("Raleway", Font.BOLD, 15));
        sbmt.setBackground(Color.BLACK);
        sbmt.setForeground(Color.WHITE);
        sbmt.addActionListener(this);

        cncl = new JButton("Cancel");
        cncl.setFont(new Font("Raleway", Font.BOLD, 15));
        cncl.setBackground(Color.BLACK);
        cncl.setForeground(Color.WHITE);
        cncl.addActionListener(this);

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("simulator/icons/signup.png"));
        Image i2 = i1.getImage().getScaledInstance(60,60,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        img = new JLabel(i3);

        form.setBounds(400,10,150,30);
        img.setBounds(60,10,60,60);
        page2.setBounds(160,40,600,40);
        acType.setBounds(60,80,200,30);
        savings.setBounds(100,110,200,30);
        current.setBounds(330,110,200,30);
        fd.setBounds(100,140,200,30);
        rd.setBounds(330,140,300,30);
        card.setBounds(60,180,200,30);
        cardNo.setBounds(200,180,250,30);
        pin.setBounds(60,210,200,30);
        pinX.setBounds(200, 210,200,30);
        service.setBounds(60,240,200,30);
        atm.setBounds(100,270,200,30);
        netBnk.setBounds(350,270,200,30);
        mbl.setBounds(100,300,200,30);
        alrt.setBounds(350,300,200,30);
        chq.setBounds(100,330,200,30);
        eStmt.setBounds(350,330,200,30);
        decl.setBounds(60,380,600,20);
        sbmt.setBounds(150,410,100,30);
        cncl.setBounds(300,410,100,30);

        add(form);add(img);add(page2);add(acType);add(savings);
        add(current);add(fd);add(rd);add(card);add(cardNo);
        add(pin);add(pinX);add(service);add(atm);add(netBnk);
        add(mbl);add(alrt);add(chq);add(eStmt);add(decl);
        add(sbmt);add(cncl);

        getContentPane().setBackground(Color.WHITE);
        setSize(600,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    static int getCardFour(){
        Random ran = new Random();
        long fourDig = (ran.nextLong() % 9000L) + 1000L;
        return (int)(Math.abs(fourDig));
    }

    public void actionPerformed(ActionEvent ae){

        try{
            if(ae.getSource() == sbmt){
                String acc = null;
                if(savings.isSelected())
                    acc = "Savings account";
                if(current.isSelected())
                    acc = "Current account";
                if(fd.isSelected())
                    acc = "Fixed deposit account";
                if(rd.isSelected())
                    acc = "Recurring deposit account";

                String services = "";
                if(atm.isSelected())
                    services += "ATM Card ";
                if(netBnk.isSelected())
                    services += "Internet Banking ";
                if(mbl.isSelected())
                    services += "Mobile Banking ";
                if(alrt.isSelected())
                    services += "Email alert ";
                if(chq.isSelected())
                    services += "Cheque Book ";
                if(eStmt.isSelected())
                    services += "e-Statement ";

                String firstFour = Integer.toString(getCardFour());
                String secondFour = Integer.toString(getCardFour());
                String thirdFour = Integer.toString(getCardFour());

                String cardNo = firstFour + "-" + secondFour + "-" + thirdFour + "-" + fourthFour;

                String pinNum = Integer.toString(getCardFour());

                Connect conn = new Connect();
                if(!decl.isSelected() | services.isEmpty() | acc.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Fill all boxes");
                } else{

                    String q1 = "insert into signup2 values('"+formNo+"','"+acc+"','"+cardNo+"','"+pinNum+"','"+services+"')";
                    String q2 = "insert into login values('"+formNo+"','"+cardNo+"','"+pinNum+"')";
                    conn.getStmnt().executeUpdate(q1);
                    conn.getStmnt().executeUpdate(q2);
                    JOptionPane.showMessageDialog(null,"Card No.: "+cardNo+ "\nPin: " + pinNum);

                    new Deposit(pinNum).setVisible(true);
                    setVisible(false);
                }
            }
            if(ae.getSource() == cncl){
                Connect conn = new Connect();
                String q1 = "delete from signup where formno = '"+formNo+"'";
                conn.getStmnt().executeUpdate(q1);
                new Login().setVisible(true);
                setVisible(false);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
