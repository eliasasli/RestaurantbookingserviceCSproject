import javax.swing.*;
import java.awt.*;

public class Adminmain extends JDialog {
    private JPanel ADMINMAINPANEL;
    private JButton button1;
    private JButton btnveiwbookings;


    public Adminmain(JFrame parent) { //parameters
        super(parent);
        setTitle("main admin page");
        setContentPane(ADMINMAINPANEL);
        setMinimumSize(new Dimension(800, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

setVisible(true);
}}
