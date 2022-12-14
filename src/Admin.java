import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Admin extends JDialog {
    private JPanel loginPanel;
    private JButton btnregistration;
    private JTextField tfEmail;
    private JPasswordField pfPassword;
    private JButton btnOK;
    private JButton btncancel;
    private JButton goBackToLoginButton;
    private JPanel loginpanel;
    User user;

    public Admin(DashboardForm parent) { //parameters
        super(parent);
        setTitle("create a new account");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(800, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnregistration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // go to registration form
                        dispose();
                        RegistrationForm registrationForm = new RegistrationForm(null);
                        User user = registrationForm.user;

                        if (user != null) {
                            JOptionPane.showMessageDialog(Admin.this,
                                    "New user: " + user.name,
                                    "you are logged in!",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }}
                });


        goBackToLoginButton.addActionListener(new ActionListener() { // go to loginform
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginForm loginForm = new LoginForm(null);
                User user = loginForm.user;

                if (user != null) {
                    JOptionPane.showMessageDialog(Admin.this,
                            "New user: " + user.name,
                            "you are logged in!",
                            JOptionPane.INFORMATION_MESSAGE);
                }}
        });
        setVisible(true);
        btncancel.addActionListener(new ActionListener() { //close admin form
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btncancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Email = tfEmail.getText();
                String Password = String.valueOf(pfPassword.getPassword());
                try {
                    user = getAdminUser(Email, Password);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                if (user != null) {
                    Adminmain adminmain = new Adminmain(null);
                    adminmain.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(Admin.this,
                            "Your Email or Password is Invalid",
                            "Please try again",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
            }

    private User getAdminUser(String Email, String Password) throws Exception{ // connection to database
        user = null;





        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "Eldaem03!");



        try{

            Statement stmt = connection.createStatement();
            String s1 = "SELECT * FROM jdbc.adminlogin WHERE Adminemail=? AND adminpassword=?";


            PreparedStatement statement = connection.prepareStatement(s1);{
                statement.setString(1, Email);
                statement.setString(2, Password);

            }
            stmt.close();
            connection.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
    public static void main(String[] args) throws Exception {
        Admin admin = new Admin(null);
       User user = admin.user;
        if (user != null) {
            System.out.println("admin authentication success");
        }
            else {
                System.out.println("canceled");
            }
        }
    }



