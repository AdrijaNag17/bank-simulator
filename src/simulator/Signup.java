package simulator;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Random;

public class Signup extends JFrame implements ActionListener {
    private JLabel img, form, page, name, father, dob, gender, email, address, state, pin;
    private JTextField nameTxt, fatherTxt, emailTxt, adrsTxt, pinTxt, stateTxt;
    private JDateChooser cal;
    private JRadioButton male, female;
    private JButton next;
    static int formNo = randomNumberGen();


    public Signup(){

        setTitle("New Account Application Form");

        form = new JLabel("Application Form No: " + formNo);
        form.setFont(new Font("Raleway", Font.BOLD, 20));

        page = new JLabel("Page 1: Personal Details");
        page.setFont(new Font("Raleway", Font.BOLD, 15));

        name = new JLabel("Name: ");
        name.setFont(new Font("Raleway", Font.BOLD, 15));

        father = new JLabel("Father: ");
        father.setFont(new Font("Raleway", Font.BOLD, 15));

        dob = new JLabel("Date Of Birth: ");
        dob.setFont(new Font("Raleway", Font.BOLD, 15));

        cal = new JDateChooser();
        cal.setForeground(Color.BLUE);

        gender = new JLabel("Gender: ");
        gender.setFont(new Font("Raleway", Font.BOLD, 15));

        email = new JLabel("Email: ");
        email.setFont(new Font("Raleway", Font.BOLD, 15));

        address = new JLabel("Address: ");
        address.setFont(new Font("Raleway", Font.BOLD, 15));

        pin = new JLabel("Pin Code: ");
        pin.setFont(new Font("Raleway", Font.BOLD, 15));

        state = new JLabel("State: ");
        state.setFont(new Font("Raleway", Font.BOLD, 15));

        next = new JButton("Next");
        next.setFont(new Font("Arial", Font.BOLD, 15));
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);

        male = new JRadioButton("Male");
        male.setFont(new Font("Arial", Font.BOLD, 10));
        male.setBackground(Color.WHITE);

        female = new JRadioButton("Female");
        female.setFont(new Font("Arial", Font.BOLD, 10));
        female.setBackground(Color.WHITE);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        nameTxt = new JTextField();
        nameTxt.setFont(new Font("Arial", Font.BOLD, 10));
        fatherTxt = new JTextField();
        fatherTxt.setFont(new Font("Arial", Font.BOLD, 10));
        emailTxt = new JTextField();
        emailTxt.setFont(new Font("Arial", Font.BOLD, 10));
        adrsTxt = new JTextField();
        adrsTxt.setFont(new Font("Arial", Font.BOLD, 10));
        stateTxt = new JTextField();
        stateTxt.setFont(new Font("Arial", Font.BOLD, 10));
        pinTxt = new JTextField();
        pinTxt.setFont(new Font("Arial", Font.BOLD, 10));

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("simulator/icons/signup.png"));
        Image i2 = i1.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        img = new JLabel(i3);

        form.setBounds(160,10,600,40);
        img.setBounds(60,10,70,70);
        page.setBounds(200,50,600,30);
        name.setBounds(60,90,150,30);
        nameTxt.setBounds(200,90,200,30);
        father.setBounds(60,130,150,30);
        fatherTxt.setBounds(200,130,200,30);
        dob.setBounds(60,170,150,30);
        cal.setBounds(200,170,200,30);
        gender.setBounds(60,210,150,30);
        male.setBounds(200,210,60,30);
        female.setBounds(300,210,90,30);
        email.setBounds(60,250,150,30);
        emailTxt.setBounds(200,250,200,30);
        address.setBounds(60,290,150,30);
        adrsTxt.setBounds(200,290,200,30);
        state.setBounds(60,330,150,30);
        stateTxt.setBounds(200,330,200,30);
        pin.setBounds(60,370,150,30);
        pinTxt.setBounds(200,370,200,30);
        next.setBounds(460,400,80,30);

        add(img);add(form);add(page);add(name);add(nameTxt);
        add(father);add(fatherTxt);add(dob);add(cal);add(gender);
        add(male);add(female);add(email);add(emailTxt);
        add(address);add(adrsTxt);add(state);add(stateTxt);
        add(pin);add(pinTxt);add(next);

        next.addActionListener(this);

        getContentPane().setBackground(Color.WHITE);
        setSize(600,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){

        String name = nameTxt.getText();
        String father = fatherTxt.getText();
        String dob = ((JTextField)cal.getDateEditor().getUiComponent()).getText();
        String gender = null;
        if(male.isSelected())
            gender = "Male";
        else if(female.isSelected())
            gender = "Female";

        String email = emailTxt.getText();
        String adrs = adrsTxt.getText();
        String state = stateTxt.getText();
        String pinCode = pinTxt.getText();

        try{
            if(ae.getSource() == next){
                Connect con = new Connect();
                String q = "insert into signup values('"+ formNo +"','"+name+"','"+father+"','"+dob+"','"+
                        gender+"','"+email+"','"+adrs+"','"+state+"','"+pinCode+"')";
                con.getStmnt().executeUpdate(q);

                new Signup2(formNo).setVisible(true);
                setVisible(false);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static int randomNumberGen(){
        Random ranNum = new Random();
        long fourDig = (ranNum.nextLong() % 9000L) + 1000L;
        return (int)(Math.abs(fourDig));
    }
}
