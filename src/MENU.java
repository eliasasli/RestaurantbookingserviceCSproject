import javax.swing.*;
import java.awt.*;

public class MENU extends JDialog {
    public User user;
    private JPanel panelMENU;


    public MENU(JFrame parent) { //setting parameters
        super(parent);
        setTitle("MENU");
        parent.setContentPane(panelMENU);
        parent.setMinimumSize(new Dimension(1200, 1200));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
}}
