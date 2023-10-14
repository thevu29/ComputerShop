package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;

import BUS.SupplierBUS;
import DTO.ProductDTO;
import DTO.SupplierDTO;
public class SupplierGUI {
    private DefaultTableModel supplierModel;
    private TableRowSorter<DefaultTableModel> sorter;
    private SupplierBUS supplierBUS;

    public SupplierGUI() {
        supplierBUS = new SupplierBUS();
        initTable();
        initTableData();

        btnCreateId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtSupplierId.setText(supplierBUS.createNewSupplierID());
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtSupplierId.getText();
                String name = txtSuppplierName.getText();
                String address = txtSupplierAddress.getText();
                String phone = txtSupplierPhone.getText();

                if(supplierBUS.addSupplier(id,name,address,phone))
                    reset();

            }
        });

        tblSuppliers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowSelected = tblSuppliers.getSelectedRow();

                if (rowSelected >= 0) {
                    String SupplierId = tblSuppliers.getValueAt(rowSelected, 0).toString();
                    SupplierDTO supplier = supplierBUS.getSupplierById(SupplierId);

                    txtSupplierId.setText(supplier.getSupplierId());
                    txtSuppplierName.setText(supplier.getSupplierName());
                    txtSupplierAddress.setText(supplier.getSupplierAddress());
                    txtSupplierPhone.setText(supplier.getSupplierPhone());
                }
            }
        });

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtSupplierId.getText();
                String name = txtSuppplierName.getText();
                String address = txtSupplierAddress.getText();
                String phone = txtSupplierPhone.getText();

                if (supplierBUS.updateSupplier(id,name,address,phone))
                    reset();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtSupplierId.getText();
                if(supplierBUS.deleteSupplier(id))
                    reset();
            }
        });

        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filter();
            }
        });
    }

    public void filter() {
        String searchType = cbxSearchType.getSelectedItem().toString();
        String searchInfo = txtSearch.getText().toLowerCase();

        sorter = new TableRowSorter<>(supplierModel);
        tblSuppliers.setRowSorter(sorter);

        RowFilter<DefaultTableModel, Object> filter = new RowFilter<DefaultTableModel, Object>() {
            @Override
            public boolean include(Entry<? extends DefaultTableModel, ?> entry) {
                String rowId = entry.getStringValue(0).toLowerCase();
                String rowName = entry.getStringValue(1).toLowerCase();
                String rowPhone = entry.getStringValue(2).toLowerCase();
                String rowAddress = entry.getStringValue(3).toLowerCase();

                switch (searchType) {
                    case "Mã nhà cung cấp":
                        return rowId.contains(searchInfo);
                    case "Tên nhà cung cấp":
                        return rowName.contains(searchInfo);
                    case "Số điện thoại":
                        return rowPhone.contains(searchInfo);
                    case "Địa chỉ":
                        return rowAddress.contains(searchInfo);
                    default:
                        return true;
                }
            }
        };

        sorter.setRowFilter(filter);
    }

    public void initTable(){
        supplierModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String[] cols = {"Mã nhà cung cấp", "Tên nhà cung cấp", "Số điện thoại", "Địa chỉ"};
        supplierModel.setColumnIdentifiers(cols);
        tblSuppliers.setModel(supplierModel);
        tblSuppliers.getTableHeader().setFont(new Font("Time News Roman", Font.BOLD, 14));

        DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
        centerRender.setHorizontalAlignment(JLabel.CENTER);
        for(int i = 0; i< tblSuppliers.getColumnCount(); i++){
            tblSuppliers.getColumnModel().getColumn(i).setCellRenderer(centerRender);
        }
    }

    public void reset() {
        txtSupplierId.setText("");
        txtSuppplierName.setText("");
        txtSupplierAddress.setText("");
        txtSupplierPhone.setText("");
        txtSearch.setText("");
        cbxSearchType.setSelectedIndex(0);
        initTableData();
    }

    public void initTableData() {
        supplierBUS.renderToSupplierTable(supplierModel);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private JTable tblSuppliers;
    private JComboBox cbxSearchType;
    private JTextField txtSearch;
    private JTextField txtSupplierId;
    private JTextField txtSuppplierName;
    private JTextField txtSupplierPhone;
    private JTextField txtSupplierAddress;
    private JPanel mainPanel;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnReset;
    private JButton btnCreateId;
}
