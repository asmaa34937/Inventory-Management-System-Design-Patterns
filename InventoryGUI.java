package inventory.management.system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder; // لإضافة حدود للحقول

public class InventoryGUI extends JFrame {

    private JTextField txtSku, txtName, txtPrice, txtQty, txtThreshold;
    private JComboBox<ProductCategory> comboType;
    private JTable table;
    private DefaultTableModel tableModel;

    private InventoryManager manager;

    private IWarehouse mainWarehouse;
    private ProductFactory factory;

    private static final Color DARK_BACKGROUND = new Color(40, 44, 52);
    private static final Color LIGHT_TEXT_COLOR = new Color(200, 200, 200);
    private static final Color FIELD_BACKGROUND = new Color(50, 56, 66);

    private static final Color PRIMARY_BUTTON_COLOR = new Color(0, 122, 204);
    private static final Color SECONDARY_BUTTON_COLOR = new Color(76, 175, 80);
    private static final Color BUTTON_TEXT_COLOR = Color.WHITE; //

    public InventoryGUI() {

        manager = InventoryManager.getInstance();

        // (Lazy Initialization)
        mainWarehouse = new WarehouseProxy("Main Store");

        manager.addWarehouse(mainWarehouse);
        factory = new ProductFactory();

        setTitle("Inventory Management System (Design Patterns Project)");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        getContentPane().setBackground(DARK_BACKGROUND);

        // ------------------------------------------
        //          (Input Panel)
        // ------------------------------------------

        JPanel inputPanel = new JPanel(new GridLayout(3, 4, 15, 15));
        inputPanel.setBackground(DARK_BACKGROUND);


        inputPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder(
                                BorderFactory.createLineBorder(LIGHT_TEXT_COLOR),
                                "Add New Product",
                                javax.swing.border.TitledBorder.LEFT,
                                javax.swing.border.TitledBorder.TOP,
                                new Font("Segoe UI", Font.BOLD, 14),
                                LIGHT_TEXT_COLOR
                        ),
                        new EmptyBorder(10, 10, 10, 10)
                )
        );


        setupInputField(inputPanel, "Type:", comboType = new JComboBox<>(ProductCategory.values()));
        setupInputField(inputPanel, "SKU:", txtSku = new JTextField());
        setupInputField(inputPanel, "Name:", txtName = new JTextField());
        setupInputField(inputPanel, "Price:", txtPrice = new JTextField());
        setupInputField(inputPanel, "Quantity:", txtQty = new JTextField());
        setupInputField(inputPanel, "Threshold:", txtThreshold = new JTextField());


        comboType.setBackground(FIELD_BACKGROUND);
        comboType.setForeground(LIGHT_TEXT_COLOR);
        comboType.setBorder(new LineBorder(FIELD_BACKGROUND, 1));

        add(inputPanel, BorderLayout.NORTH);

        // ------------------------------------------
        //                 (Table)
        // ------------------------------------------

        String[] columnNames = {"SKU", "Type", "Name", "Price", "Qty", "Threshold"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);


        table.setBackground(FIELD_BACKGROUND);
        table.setForeground(LIGHT_TEXT_COLOR);
        table.setSelectionBackground(PRIMARY_BUTTON_COLOR);
        table.setGridColor(DARK_BACKGROUND.brighter());


        table.getTableHeader().setBackground(DARK_BACKGROUND.brighter());
        table.getTableHeader().setForeground(LIGHT_TEXT_COLOR);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(DARK_BACKGROUND.brighter(), 10));

        add(scrollPane, BorderLayout.CENTER);

        // ------------------------------------------
        //            (Buttons)
        // ------------------------------------------

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(DARK_BACKGROUND);

        JButton btnAdd = createStyledButton("Add Product (Factory)", PRIMARY_BUTTON_COLOR);
        JButton btnClone = createStyledButton("Clone Selected (Prototype)", SECONDARY_BUTTON_COLOR);
        JButton btnCheck = createStyledButton("Check Inventory (Strategy)", new Color(80, 80, 80));
        JButton btnDelete = createStyledButton("Delete Selected", new Color(220, 53, 69));
        buttonPanel.add(btnDelete);

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnClone);
        buttonPanel.add(btnCheck);
        add(buttonPanel, BorderLayout.SOUTH);



        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProductUsingFactory();
            }
        });

        btnCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                manager.performInventoryCheck();
                JOptionPane.showMessageDialog(null, "Inventory Check Done! Check Console for Alerts.");
            }
        });

        btnCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.performInventoryCheck();
                JOptionPane.showMessageDialog(null, "Inventory Check Done!\nCheck Console for Strategy output.");
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {

                    String sku = tableModel.getValueAt(selectedRow, 0).toString();


                    mainWarehouse.deleteProduct(sku);


                    tableModel.removeRow(selectedRow);

                    JOptionPane.showMessageDialog(null, "Product " + sku + " deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a product to delete.");
                }
            }
        });
    }


    private JButton createStyledButton(String text, Color background) {
        JButton button = new JButton(text);
        button.setBackground(background);
        button.setForeground(BUTTON_TEXT_COLOR);
        button.setMargin(new Insets(8, 15, 8, 15));
        return button;
    }

    private void setupInputField(JPanel parentPanel, String labelText, JComponent inputComponent) {
        JLabel label = new JLabel(labelText);
        label.setForeground(LIGHT_TEXT_COLOR);
        parentPanel.add(label);

        if (inputComponent instanceof JTextField) {
            JTextField field = (JTextField) inputComponent;
            field.setBackground(FIELD_BACKGROUND);
            field.setForeground(LIGHT_TEXT_COLOR);
            field.setCaretColor(LIGHT_TEXT_COLOR);
            field.setBorder(new LineBorder(FIELD_BACKGROUND, 1));
        }
        parentPanel.add(inputComponent);
    }

    // ... addProductUsingFactory , cloneSelectedProduct)

    private void addProductUsingFactory() {
        try {
            ProductCategory cat = (ProductCategory) comboType.getSelectedItem();
            String sku = txtSku.getText();
            String name = txtName.getText();
            double price = Double.parseDouble(txtPrice.getText());
            int qty = Integer.parseInt(txtQty.getText());
            int threshold = Integer.parseInt(txtThreshold.getText());

            //   Factory
            Product newProd = factory.createProduct(cat, sku, name, price, qty, threshold);


            mainWarehouse.addProduct(newProd, qty);


            tableModel.addRow(new Object[]{sku, cat, name, price, qty, threshold});

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        // Look and Feel Nimbus
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            //
        }

        SwingUtilities.invokeLater(() -> {
            new InventoryGUI().setVisible(true);
        });
    }
}