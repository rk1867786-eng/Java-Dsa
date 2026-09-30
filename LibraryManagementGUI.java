import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

// Node structure (Linked List)
class Book {
    int id;
    String title, author, status;
    Book next;

    Book(int id, String title, String author, String status) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.status = status;
        this.next = null;
    }
}

// Main GUI Class
public class LibraryManagementGUI extends JFrame {
    private Book head = null;
    private JTable table;
    private DefaultTableModel model;
    private JTextField idField, titleField, authorField, statusField;

    public LibraryManagementGUI() {
        // ===== Window Design =====
        setTitle("📚 Library Book Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 550);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(230, 245, 255));
        setLayout(new BorderLayout(10, 10));

        JLabel heading = new JLabel("📘 Library Book Management System", SwingConstants.CENTER);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 22));
        heading.setOpaque(true);
        heading.setBackground(new Color(0, 102, 204));
        heading.setForeground(Color.WHITE);
        heading.setBorder(new EmptyBorder(15, 0, 15, 0));
        add(heading, BorderLayout.NORTH);

        // ===== Input Panel =====
        JPanel inputPanel = new JPanel(new GridLayout(2, 5, 10, 10));
        inputPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
        inputPanel.setBackground(new Color(230, 245, 255));

        idField = new JTextField();
        titleField = new JTextField();
        authorField = new JTextField();
        statusField = new JTextField();

        inputPanel.add(new JLabel("Book ID:"));
        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(new JLabel("Author:"));
        inputPanel.add(new JLabel("Status:"));
        inputPanel.add(new JLabel(""));

        inputPanel.add(idField);
        inputPanel.add(titleField);
        inputPanel.add(authorField);
        inputPanel.add(statusField);

        JButton addBtn = createButton("➕ Add Book", new Color(0, 153, 76));
        inputPanel.add(addBtn);
        add(inputPanel, BorderLayout.PAGE_START);

        // ===== Table =====
        String[] columns = {"Book ID", "Title", "Author", "Status"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.setBackground(Color.WHITE);
        table.setGridColor(new Color(200, 200, 200));

        // Alternate row colors
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable tbl, Object val, boolean sel, boolean foc, int row, int col) {
                Component c = super.getTableCellRendererComponent(tbl, val, sel, foc, row, col);
                if (!sel) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(230, 245, 255));
                }
                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));
        add(scrollPane, BorderLayout.CENTER);

        // ===== Button Panel =====
        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(230, 245, 255));

        JButton deleteBtn = createButton("🗑 Delete", new Color(204, 0, 0));
        JButton updateBtn = createButton("✏ Update Status", new Color(255, 153, 0));
        JButton searchBtn = createButton("🔍 Search", new Color(0, 102, 204));
        JButton showAllBtn = createButton("📖 Show All", new Color(51, 153, 255));
        JButton exitBtn = createButton("🚪 Exit", new Color(128, 128, 128));

        btnPanel.add(deleteBtn);
        btnPanel.add(updateBtn);
        btnPanel.add(searchBtn);
        btnPanel.add(showAllBtn);
        btnPanel.add(exitBtn);

        add(btnPanel, BorderLayout.SOUTH);

        // ===== Preload Books =====
        preloadBooks();
        refreshTable();

        // ===== Button Actions =====
        addBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String title = titleField.getText().trim();
                String author = authorField.getText().trim();
                String status = statusField.getText().trim();

                if (title.isEmpty() || author.isEmpty() || status.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "⚠ Please fill all fields!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                addBook(id, title, author, status);
                refreshTable();
                clearFields();
                JOptionPane.showMessageDialog(this, "✅ Book added successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "❌ Book ID must be numeric!");
            }
        });

        deleteBtn.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Enter Book ID to delete:");
            if (idStr == null) return;
            try {
                int id = Integer.parseInt(idStr);
                deleteBook(id);
                refreshTable();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "❌ Invalid ID!");
            }
        });

        updateBtn.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Enter Book ID to update:");
            if (idStr == null) return;
            try {
                int id = Integer.parseInt(idStr);
                Book b = searchBook(id);
                if (b == null) {
                    JOptionPane.showMessageDialog(this, "Book not found!");
                } else {
                    String newStatus = JOptionPane.showInputDialog(this, "Enter new status:", b.status);
                    if (newStatus != null && !newStatus.trim().isEmpty()) {
                        b.status = newStatus.trim();
                        refreshTable();
                        JOptionPane.showMessageDialog(this, "✅ Status updated!");
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID!");
            }
        });

        searchBtn.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Enter Book ID to search:");
            if (idStr == null) return;
            try {
                int id = Integer.parseInt(idStr);
                Book b = searchBook(id);
                if (b == null) {
                    JOptionPane.showMessageDialog(this, "❌ Book not found!");
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Book Found:\n\n📖 ID: " + b.id + "\n📗 Title: " + b.title + "\n✍ Author: " + b.author + "\n📦 Status: " + b.status,
                            "Book Details", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID!");
            }
        });

        showAllBtn.addActionListener(e -> refreshTable());
        exitBtn.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    // ==== Utility Methods ====
    private JButton createButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(bgColor);
        btn.setFocusPainted(false);
        btn.setBorder(new RoundedBorder(10));
        btn.setPreferredSize(new Dimension(150, 40));
        return btn;
    }

    // Custom rounded border
    private static class RoundedBorder extends LineBorder {
        RoundedBorder(int radius) {
            super(Color.WHITE, 2, true);
        }
    }

    private void addBook(int id, String title, String author, String status) {
        Book newBook = new Book(id, title, author, status);
        if (head == null) head = newBook;
        else {
            Book temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = newBook;
        }
    }

    private void deleteBook(int id) {
        if (head == null) {
            JOptionPane.showMessageDialog(this, "No books to delete!");
            return;
        }
        if (head.id == id) {
            head = head.next;
            JOptionPane.showMessageDialog(this, "Book deleted!");
            return;
        }
        Book temp = head;
        while (temp.next != null && temp.next.id != id) temp = temp.next;
        if (temp.next == null)
            JOptionPane.showMessageDialog(this, "Book not found!");
        else {
            temp.next = temp.next.next;
            JOptionPane.showMessageDialog(this, "Book deleted!");
        }
    }

    private Book searchBook(int id) {
        Book temp = head;
        while (temp != null) {
            if (temp.id == id) return temp;
            temp = temp.next;
        }
        return null;
    }

    private void refreshTable() {
        model.setRowCount(0);
        Book temp = head;
        while (temp != null) {
            model.addRow(new Object[]{temp.id, temp.title, temp.author, temp.status});
            temp = temp.next;
        }
    }

    private void clearFields() {
        idField.setText("");
        titleField.setText("");
        authorField.setText("");
        statusField.setText("");
    }

    private void preloadBooks() {
        addBook(101, "Let Us C", "Yashavant Kanetkar", "Available");
        addBook(102, "Programming in ANSI C", "Balagurusamy", "Issued");
        addBook(103, "Data Structures", "Seymour Lipschutz", "Available");
        addBook(104, "Oper  `ating System", "Galvin", "Available");
        addBook(105, "Computer Networks", "Tanenbaum", "Issued");
        addBook(106, "Database System Concepts", "Korth", "Available");
        addBook(107, "Java Complete Reference", "Herbert Schildt", "Available");
        addBook(108, "C++ Primer", "Stanley Lippman", "Available");
        addBook(109, "Python Crash Course", "Eric Matthes", "Issued");
        addBook(110, "Algorithms in C", "Robert Sedgewick", "Available");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LibraryManagementGUI::new);
    }
}