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

    public LoginForm(JFrame parent) {
        super(parent);
        setTitle("login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(800,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnOK.addActionListener(new ActionListener() {
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
                }
                else {
                    JOptionPane.showMessageDialog(LoginForm.this,
                            "Your Email or Password is Invalid",
                            "Please try again",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btncancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    public User user;
    private User getAuthenticatedUser(String email, String password) throws Exception{
        User user = null;

        Connection connection = DriverManager.getConnection("jdbc:ucanaccess://X/My Documents/Database.accdb");

        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery("Select * from user");
        while (resultset.next())
            System.out.println(resultset.getString(1) + "\t" + resultset.getString(2));

        connection.close();

        try{
            Connection conn = DriverManager.getConnection(String.valueOf(connection));

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM User WHERE email=? AND password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.name = resultSet.getString("fname");
                user.name = resultSet.getString("lname");
                user.email = resultSet.getString("email");
                user.phone = resultSet.getString("phone");
                user.address = resultSet.getString("address");
                user.password = resultSet.getString("password");
            }

            stmt.close();
            conn.close();

        }catch(Exception e){
            e.printStackTrace();
        }


        return user;
    }

    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm(null);
        User user = loginForm.user;
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
