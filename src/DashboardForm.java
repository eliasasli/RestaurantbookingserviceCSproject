import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DashboardForm extends JFrame {
    private JPanel dashboardPanel;
    private JLabel lbAdmin;
    private JButton btnRegister;

    public DashboardForm() throws Exception {
        setTitle("Main Menu");
        setContentPane(dashboardPanel);
        setMinimumSize(new Dimension(500, 429));
        setSize(1300,700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        boolean hasRegistredUsers = connectToDatabase();
        if (hasRegistredUsers) {
            LoginForm loginForm = new LoginForm(this);
            User user = loginForm.user;

            if (user != null) {
                lbAdmin.setText("User: " + user.name);
                setLocationRelativeTo(null);
                setVisible(true);
            }
            else {
                dispose();
            }
        }

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrationForm registrationForm = new RegistrationForm(DashboardForm.this);
                User user = registrationForm.user;

                if (user != null) {
                    JOptionPane.showMessageDialog(DashboardForm.this,
                            "New user: " + user.name,
                            "You have now been registered",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    private boolean connectToDatabase() throws Exception {
        boolean hasRegistredUsers = false;

        Connection connection = DriverManager.getConnection("jdbc:ucanaccess:/X/My Documents/Database.accdb");

        Statement st = connection.createStatement();
        ResultSet resultset = st.executeQuery("Select * from user");
        while (resultset.next())
            System.out.println(resultset.getString(1) + "\t" + resultset.getString(2));

        connection.close();

        return hasRegistredUsers;
    }

    public static void main(String[] args) throws Exception {
        DashboardForm myForm = new DashboardForm();
    }
}

