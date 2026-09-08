package gui;

import model.Book;
import model.Cashier;
import model.Manager;
import model.User;
import Services.BookService;
import Services.UserService;

import javax.swing.*;
import java.awt.*;

public class ManagerFrame extends CashierFrame {

    private BookService bookService;
    private UserService userService;

    public ManagerFrame(User user) {
        super(user);

        bookService = new BookService();
        userService = new UserService();

        setTitle("Manager Dashboard - " + user.getUsername());

        addManagerControls();
    }

    private void addManagerControls() {

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.setBackground(new Color(245, 247, 250));

        JButton addBookButton = new JButton("Add Book");
        JButton createUserButton = new JButton("Create Account");

        styleButton(addBookButton, new Color(46, 204, 113));   // Green
        styleButton(createUserButton, new Color(155, 89, 182)); // Purple

        bottomPanel.add(addBookButton);
        bottomPanel.add(createUserButton);

        add(bottomPanel, BorderLayout.SOUTH);

        addBookButton.addActionListener(e -> addBook());
        createUserButton.addActionListener(e -> createUser());
    }

    // SAME rounded button style as CashierFrame
    private void styleButton(JButton button, Color bgColor) {

        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(160, 40));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));

        button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void installUI(JComponent c) {
                super.installUI(c);
                button.setOpaque(false);
            }

            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(bgColor);
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 10, 10);

                super.paint(g, c);
                g2.dispose();
            }
        });
    }

    private void addBook() {

        JTextField titleField = new JTextField(15);
        JTextField categoryField = new JTextField(15);
        JTextField priceField = new JTextField(15);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBackground(new Color(245, 247, 250));

        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Category:"));
        panel.add(categoryField);
        panel.add(new JLabel("Price:"));
        panel.add(priceField);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Add New Book",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {
            try {
                String title = titleField.getText();
                String category = categoryField.getText();
                double price = Double.parseDouble(priceField.getText());

                Book book = new Book(title, category, price);
                bookService.addBook(book);

                JOptionPane.showMessageDialog(this, "Book Added Successfully!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Invalid Input!");
            }
        }
    }

    private void createUser() {

        JTextField usernameField = new JTextField(15);
        JTextField passwordField = new JTextField(15);

        String[] roles = {"Cashier", "Manager"};
        JComboBox<String> roleBox = new JComboBox<>(roles);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBackground(new Color(245, 247, 250));

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel("Role:"));
        panel.add(roleBox);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Create New User",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String role = (String) roleBox.getSelectedItem();

            if (role.equals("Manager")) {
                userService.registerUser(new Manager(username, password));
            } else {
                userService.registerUser(new Cashier(username, password));
            }

            JOptionPane.showMessageDialog(this, "User Created Successfully!");
        }
    }
}