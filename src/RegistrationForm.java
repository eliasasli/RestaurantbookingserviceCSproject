import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RegistrationForm extends JDialog {
    private JTextField tfEmail;
    private JTextField tfPhone;
    private JTextField tfAddress;
    private JTextField tffname;
    private JTextField tflname;
    private JPasswordField pfPassword;
    private JPasswordField pfConfirmPassword;
    private JButton btnRegister;
    private JButton btnCancel;
    private JPanel registerPanel;


    public RegistrationForm(DashboardForm parent) {
        super(parent);
        setTitle("create a new account");
        setContentPane(registerPanel);
        setMinimumSize(new Dimension(500,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    registerUser();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    private void registerUser() throws Exception {
        String name1 = tffname.getText();
        String name2 = tflname.getText();
        String email = tfEmail.getText();
        String phone = tfPhone.getText();
        String address = tfAddress.getText();
        String password = String.valueOf(pfPassword.getPassword());
        String confirmPassword = String.valueOf(pfConfirmPassword.getPassword());

        if (name1.isEmpty() || name2.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() ||password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "1 or more fields are empty",
                            "Please Try Again",
                           JOptionPane.ERROR_MESSAGE );
            return;

        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this,
                    "Your passwords do not match",
                    "Please Try Again",
                    JOptionPane.ERROR_MESSAGE );
            return;
        }


       try {

           user = writeToDatabase(name1, name2, email, password, phone, address);
           dispose();

       } catch (Exception e) {
           throw new RuntimeException(e);

       }
       }


    public User user;
    public static User writeToDatabase(String name1, String name2, String email, String password, String phone, String address) {
        User user2 = null;
        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "Eldaem03!");

            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "INSERT INTO User (name1, name2, email, password, phone, address) VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name1);
            preparedStatement.setString(2, name2);
            preparedStatement.setString(6, password);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, address);

            int row = preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error in thew SQL class: " + e);
        }

        return user2;
    }


    public static void main(String[] args) {
        RegistrationForm myForm = new RegistrationForm(null);
        User user = myForm.user;
        if (user != null) {
            System.out.println("Successful Registration of: " + user.name);

}
        else {
            System.out.println("registration Canceled");
        }
}}
