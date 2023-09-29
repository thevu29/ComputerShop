package GUI;

import javax.swing.*;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

public class AddDecentralizationGUI {


    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        JFrame frame = new JFrame("AddDecentralizationGUI");
        frame.setContentPane(new AddDecentralizationGUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel mainPanel;
    private JLabel lblTitle;
    private JComboBox cbxEmployeeID;
    private JTextField txtTenNV;
    private JComboBox cbxChonQuyen;
    private JButton cbxThuHoiQuyen;
    private JButton cbxCapQuyen;


}
