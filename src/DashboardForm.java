import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DashboardForm extends JDialog {
    private JPanel dashboardPanel;
    private JLabel lbAdmin;
    private JButton btnRegister;
    private JButton btnLogin;
    private JButton btnBooking;



    //  MAKE A 3D ARRAY!!!!!!!!!!!!!!!!!!


    //    int tableplacement[][][][][][][][][][] = new int [][][];
    //    int flight = 0;
    //   while (flight < 5) {
    //       if ()

    public DashboardForm(JFrame parent) { //setting parameters
        super(parent);
        setTitle("Main Menu");
        setContentPane(dashboardPanel);
        setMinimumSize(new Dimension(800, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        btnRegister.addActionListener(new ActionListener() { // go to register
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
        btnLogin.addActionListener(new ActionListener() { // go to login
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm loginForm = new LoginForm(null);
                User user = loginForm.user;

                if (user != null) {
                    JOptionPane.showMessageDialog(DashboardForm.this,
                            "New user: " + user.name,
                            "you are logged in!",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        btnBooking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // go to menu
                dispose();
               MENU menu = new MENU(null);
                User user = menu.user;
                if (user != null) {
                    JOptionPane.showMessageDialog(DashboardForm.this,
                           "New user: " + user.name,
                            "you are logged in!",
                            JOptionPane.INFORMATION_MESSAGE);

                }
            }
        });

        setVisible(true);
    }




    private boolean connectToDatabase() throws Exception {
        boolean hasRegistredUsers = false;

        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "Eldaem03!");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM jdbc.user;");


        } catch (Exception e) {
            e.printStackTrace();
        }


        return hasRegistredUsers;
    }}





