import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
            }
    public static void main(String[] args) throws Exception {
        Admin admin = new Admin(null);
       User user = admin.user;

    }
}


