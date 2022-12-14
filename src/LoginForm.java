import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginForm extends JDialog {
    private JTextField tfEmail;
    private JPasswordField pfPassword;
    private JButton btnOK;
    private JButton btncancel;
    private JPanel loginPanel;
    private JButton btnregistration;
    private JButton btnadmin;
    public User user;
    public LoginForm(RegistrationForm parent) { //parameters
        super(parent);
        setTitle("login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(800, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnOK.addActionListener(new ActionListener() { //fetching info from textfields then seing if they match any in the database, if so, close if not, invalid
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = tfEmail.getText();
                String password = String.valueOf(pfPassword.getPassword());

                try {
                    user = getAuthenticatedUser(email, password);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                if (user != null) {
                    dispose();
                    DashboardForm dashboardForm = new DashboardForm(null);
                    dashboardForm.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(LoginForm.this,
                            "Your Email or Password is Invalid",
                            "Please try again",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btncancel.addActionListener(new ActionListener() {//close
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        btnregistration.addActionListener(new ActionListener() {//go to registration form
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                RegistrationForm registrationForm = new RegistrationForm(null);
                User user = registrationForm.user;

                if (user != null) {
                    JOptionPane.showMessageDialog(LoginForm.this,
                            "New user: " + user.name,
                            "you are logged in!",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        btnadmin.addActionListener(new ActionListener() { // go to admin login
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Admin admin = new Admin(null);
                User user = admin.user;

                if (user != null) {
                    JOptionPane.showMessageDialog(LoginForm.this,
                            "New user: " + user.name,
                            "you are logged in!",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        setVisible(true);
    }


    public static User getAuthenticatedUser(String email, String password) throws Exception { // connection to database
        User user3 = null;

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "Eldaem03!");

        Statement statement1 = connection.createStatement();

        ResultSet rs = null;
        try {
            System.out.println("1");
            Statement stmt = connection.createStatement();
            String s1 = "SELECT * FROM jdbc.user WHERE email=@";
            String s2 = " AND password=@";
            String res = s1 + email + s2 + password;
            System.out.println(res);


            System.out.println("2");


            Statement statement = connection.createStatement();

            rs = statement.executeQuery(res);


            System.out.println("3");


            stmt.close();
            connection.close();
            System.out.println("4");
        } catch (Exception e) {
            e.printStackTrace();
            user3 = null;
        }

        user3 = rs;
        return user3;
    }

    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm(null);
        User user = loginForm.user;
        System.out.println(user);
        if (user != null) {
            System.out.println("Successful Authentication of: " + user.name);
            System.out.println("             Email: " + user.email);
            System.out.println("             Phone: " + user.phone);
            System.out.println("             Address: " + user.address);

        }
        else {
            System.out.println("Authentication Canceled");
        }
    }
}
