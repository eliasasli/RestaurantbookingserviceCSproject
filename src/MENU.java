import javax.swing.*;
import java.awt.*;

public class MENU extends JDialog {
    User user;
    private JPanel panelMENU;
    private JPanel menu;


    public MENU(JFrame parent) { //setting parameters
        super(parent);
        setTitle("MENU");
        parent.setContentPane(menu);
        parent.setMinimumSize(new Dimension(1200, 1200));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
}}
