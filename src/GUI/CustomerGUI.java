package GUI;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CustomerGUI {
    private DefaultTableModel model;

    public CustomerGUI() {
        initDateChooser();
        initProductTable();
    }

    public void initProductTable() {
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String[] cols = {"Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Email", "Điểm tích lũy"};
        model.setColumnIdentifiers(cols);
        tblCustomers.setModel(model);
        tblCustomers.getTableHeader().setFont(new Font("Time News Roman", Font.BOLD, 14));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < cols.length; i++) {
            tblCustomers.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void initDateChooser() {
        customerDOB = new JDateChooser();
        customerDOB.setPreferredSize(new Dimension(-1, 30));
        datePanel.add(customerDOB);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private JPanel mainPanel;
    private JPanel panelBottom;
    private JPanel Left;
    private JTable tblCustomers;
    private JPanel Right;
    private JComboBox cbxFilterGender;
    private JComboBox cbxFilterPoint;
    private JTextField txtCustomerPoint;
    private JTextField txtCustomerName;
    private JTextField txtCustomerPhone;
    private JTextField txtCustomerAddress;
    private JComboBox cbxGender;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnReset;
    private JTextField txtCustomerId;
    private JPanel datePanel;
    private JComboBox cbxSearchType;
    private JTextField txtSearch;
    private JDateChooser customerDOB;
}