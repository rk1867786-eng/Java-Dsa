import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class Item {
    String name;
    int quantity;
    double price;

    Item(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}

public class InventorySystemStylish extends JFrame {

    private Item[] items = new Item[100];
    private int count = 0;

    private DefaultTableModel tableModel;
    private JTable table;

    private JTextField nameField, qtyField, priceField, searchField;

    // ===== Constructor =====
    public InventorySystemStylish() {
        setTitle("🛒 Stylish Inventory Management System");
        setSize(900, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ===== Gradient Background =====
        JPanel bgPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(72, 61, 139),
                        getWidth(), getHeight(), new Color(123, 104, 238));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        bgPanel.setLayout(new BorderLayout());
        add(bgPanel);

        // ===== Title =====
        JLabel title = new JLabel("📦 Inventory Management System", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        title.setBorder(new EmptyBorder(15, 0, 15, 0));
        bgPanel.add(title, BorderLayout.NORTH);

        // ===== Table =====
        String[] columns = {"Item Name", "Quantity", "Price (₹)"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(106, 90, 205));
        table.getTableHeader().setForeground(Color.WHITE);
        bgPanel.add(new JScrollPane(table), BorderLayout.CENTER);

        // ===== Input Fields =====
        JPanel inputPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        inputPanel.setBackground(new Color(72, 61, 139));
        inputPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        nameField = new JTextField();
        qtyField = new JTextField();
        priceField = new JTextField();
        searchField = new JTextField();

        addLabelField(inputPanel, "Item Name:", nameField);
        addLabelField(inputPanel, "Quantity:", qtyField);
        addLabelField(inputPanel, "Price (₹):", priceField);
        addLabelField(inputPanel, "Search Item:", searchField);

        bgPanel.add(inputPanel, BorderLayout.NORTH);

        // ===== Buttons =====
        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 12, 12));
        buttonPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        buttonPanel.setBackground(new Color(72, 61, 139));

        JButton addBtn = createButton("➕ Add Item", new Color(46, 204, 113));
        JButton removeBtn = createButton("➖ Remove Item", new Color(231, 76, 60));
        JButton searchBtn = createButton("🔍 Search", new Color(52, 152, 219));
        JButton billBtn = createButton("🧾 Generate Bill", new Color(241, 196, 15));
        JButton exitBtn = createButton("❌ Exit", new Color(155, 89, 182));

        buttonPanel.add(addBtn);
        buttonPanel.add(removeBtn);
        buttonPanel.add(searchBtn);
        buttonPanel.add(billBtn);
        buttonPanel.add(exitBtn);
        bgPanel.add(buttonPanel, BorderLayout.SOUTH);

        // ===== Button Actions =====
        addBtn.addActionListener(e -> addItemAction());
        removeBtn.addActionListener(e -> removeItemAction());
        searchBtn.addActionListener(e -> searchItemAction());
        billBtn.addActionListener(e -> openBillingWindow());
        exitBtn.addActionListener(e -> System.exit(0));
    }

    // ===== Helper Methods =====
    private void addLabelField(JPanel panel, String label, JTextField field) {
        JLabel lbl = new JLabel(label);
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panel.add(lbl);
        panel.add(field);
    }

    private JButton createButton(String text, Color baseColor) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(baseColor);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(baseColor.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(baseColor);
            }
        });
        return btn;
    }

    // ===== Inventory Logic =====
    private void addItemAction() {
        try {
            String name = nameField.getText().trim();
            int qty = Integer.parseInt(qtyField.getText().trim());
            double price = Double.parseDouble(priceField.getText().trim());
            addItem(name, qty, price);
            refreshTable();
            clearFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "⚠ Invalid input!");
        }
    }

    private void removeItemAction() {
        try {
            String name = nameField.getText().trim();
            int qty = Integer.parseInt(qtyField.getText().trim());
            removeItem(name, qty);
            refreshTable();
            clearFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "⚠ Invalid input!");
        }
    }

    private void searchItemAction() {
        String search = searchField.getText().trim();
        searchItem(search);
    }

    void addItem(String name, int qty, double price) {
        for (int i = 0; i < count; i++) {
            if (items[i].name.equalsIgnoreCase(name)) {
                items[i].quantity += qty;
                JOptionPane.showMessageDialog(this, "✅ Added " + qty + " more of " + name);
                return;
            }
        }
        items[count++] = new Item(name, qty, price);
        JOptionPane.showMessageDialog(this, "✅ Added " + name + " successfully!");
    }

    void removeItem(String name, int qty) {
        for (int i = 0; i < count; i++) {
            if (items[i].name.equalsIgnoreCase(name)) {
                if (items[i].quantity >= qty) {
                    items[i].quantity -= qty;
                    if (items[i].quantity == 0) {
                        for (int j = i; j < count - 1; j++) {
                            items[j] = items[j + 1];
                        }
                        count--;
                        JOptionPane.showMessageDialog(this, "🗑 " + name + " removed completely!");
                    } else {
                        JOptionPane.showMessageDialog(this, "🗑 Removed " + qty + " " + name);
                    }
                    return;
                } else {
                    JOptionPane.showMessageDialog(this, "⚠ Not enough quantity!");
                    return;
                }
            }
        }
        JOptionPane.showMessageDialog(this, "❌ Item not found!");
    }

    void searchItem(String name) {
        for (int i = 0; i < count; i++) {
            if (items[i].name.equalsIgnoreCase(name)) {
                JOptionPane.showMessageDialog(this,
                        "📦 Item Found:\n\nName: " + items[i].name +
                                "\nQuantity: " + items[i].quantity +
                                "\nPrice: ₹" + items[i].price);
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "❌ Item not found.");
    }

    void refreshTable() {
        tableModel.setRowCount(0);
        for (int i = 0; i < count; i++) {
            tableModel.addRow(new Object[]{items[i].name, items[i].quantity, items[i].price});
        }
    }

    void clearFields() {
        nameField.setText("");
        qtyField.setText("");
        priceField.setText("");
        searchField.setText("");
    }

    // ===== Billing Section =====
    void openBillingWindow() {
        JFrame billFrame = new JFrame("🧾 Billing Section");
        billFrame.setSize(700, 450);
        billFrame.setLocationRelativeTo(this);
        billFrame.setLayout(new BorderLayout());
        billFrame.getContentPane().setBackground(new Color(230, 230, 250));

        JLabel totalLabel = new JLabel("Total: ₹0.00", SwingConstants.CENTER);
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        totalLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
        billFrame.add(totalLabel, BorderLayout.NORTH);

        DefaultTableModel billModel = new DefaultTableModel(new String[]{"Item", "Qty", "Price", "Total"}, 0);
        JTable billTable = new JTable(billModel);
        billTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        billTable.setRowHeight(28);
        billTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        billTable.getTableHeader().setBackground(new Color(106, 90, 205));
        billTable.getTableHeader().setForeground(Color.WHITE);
        billFrame.add(new JScrollPane(billTable), BorderLayout.CENTER);

        // ===== Centered Inputs + Buttons =====
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBackground(new Color(230, 230, 250));
        inputPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        // Row 1: Inputs
        JPanel inputRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        inputRow.setBackground(new Color(230, 230, 250));

        JLabel nameLbl = new JLabel("Item Name:");
        JLabel qtyLbl = new JLabel("Quantity:");
        JTextField itemName = new JTextField(10);
        JTextField itemQty = new JTextField(5);

        nameLbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
        qtyLbl.setFont(new Font("Segoe UI", Font.BOLD, 14));

        inputRow.add(nameLbl);
        inputRow.add(itemName);
        inputRow.add(qtyLbl);
        inputRow.add(itemQty);

        // Row 2: Buttons
        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 10));
        buttonRow.setBackground(new Color(230, 230, 250));

        JButton addBillBtn = createButton("➕ Add to Bill", new Color(52, 152, 219));
        JButton removeBillBtn = createButton("➖ Remove from Bill", new Color(231, 76, 60));
        JButton finalizeBtn = createButton("✅ Finalize Bill", new Color(46, 204, 113));

        Dimension btnSize = new Dimension(160, 40);
        addBillBtn.setPreferredSize(btnSize);
        removeBillBtn.setPreferredSize(btnSize);
        finalizeBtn.setPreferredSize(btnSize);

        buttonRow.add(addBillBtn);
        buttonRow.add(removeBillBtn);
        buttonRow.add(finalizeBtn);

        inputPanel.add(inputRow);
        inputPanel.add(Box.createVerticalStrut(10));
        inputPanel.add(buttonRow);

        billFrame.add(inputPanel, BorderLayout.SOUTH);

        final double[] totalAmount = {0};

        addBillBtn.addActionListener(e -> {
            String name = itemName.getText().trim();
            int qty;
            try {
                qty = Integer.parseInt(itemQty.getText().trim());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(billFrame, "⚠ Invalid quantity!");
                return;
            }

            for (int i = 0; i < count; i++) {
                if (items[i].name.equalsIgnoreCase(name)) {
                    if (qty > items[i].quantity) {
                        JOptionPane.showMessageDialog(billFrame, "⚠ Not enough stock!");
                        return;
                    }
                    double total = qty * items[i].price;
                    billModel.addRow(new Object[]{name, qty, items[i].price, total});
                    totalAmount[0] += total;
                    totalLabel.setText("Total: ₹" + String.format("%.2f", totalAmount[0]));
                    items[i].quantity -= qty;
                    refreshTable();
                    return;
                }
            }
            JOptionPane.showMessageDialog(billFrame, "❌ Item not found!");
        });

        removeBillBtn.addActionListener(e -> {
            int row = billTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(billFrame, "⚠ Select item to remove!");
                return;
            }
            String item = (String) billModel.getValueAt(row, 0);
            int qty = (int) billModel.getValueAt(row, 1);
            double total = (double) billModel.getValueAt(row, 3);

            totalAmount[0] -= total;
            totalLabel.setText("Total: ₹" + String.format("%.2f", totalAmount[0]));

            for (int i = 0; i < count; i++) {
                if (items[i].name.equalsIgnoreCase(item)) {
                    items[i].quantity += qty;
                    break;
                }
            }

            billModel.removeRow(row);
            refreshTable();
        });

        finalizeBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(billFrame, "💰 Final Bill: ₹" + String.format("%.2f", totalAmount[0]));
            billFrame.dispose();
        });

        billFrame.setVisible(true);
    }

    // ===== Main =====
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InventorySystemStylish().setVisible(true));
    }
}