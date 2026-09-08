package gui;

import model.User;
import model.Manager;
import model.Cashier;
import Services.UserService;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private UserService userService;

    public LoginFrame() {

        userService = new UserService();

        setTitle("City Bookshop - Login");
        setSize(420, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        mainPanel.setBackground(new Color(245, 247, 250)); // Light background

        // Title
        JLabel titleLabel = new JLabel("City Bookshop");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(new Color(40, 40, 40));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(245, 247, 250));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 10, 12, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);

        // Username
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel userLabel = new JLabel("Username");
        userLabel.setFont(labelFont);
        userLabel.setForeground(new Color(60, 60, 60));
        formPanel.add(userLabel, gbc);

        gbc.gridx = 1;
        usernameField = new JTextField(15);
        usernameField.setFont(labelFont);
        usernameField.setPreferredSize(new Dimension(200, 32));
        usernameField.setBackground(Color.WHITE);
        usernameField.setBorder(BorderFactory.createEmptyBorder(5, 8, 5, 8));
        formPanel.add(usernameField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(labelFont);
        passLabel.setForeground(new Color(60, 60, 60));
        formPanel.add(passLabel, gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        passwordField.setFont(labelFont);
        passwordField.setPreferredSize(new Dimension(200, 32));
        passwordField.setBackground(Color.WHITE);
        passwordField.setBorder(BorderFactory.createEmptyBorder(5, 8, 5, 8));
        formPanel.add(passwordField, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 247, 250));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        styleButton(loginButton, new Color(52, 152, 219));    // Blue
        styleButton(registerButton, new Color(155, 89, 182)); // Purple

        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        loginButton.addActionListener(e -> login());
        registerButton.addActionListener(e -> register());

        setVisible(true);
    }

    // Rounded button style (5px radius look)
    private void styleButton(JButton button, Color bgColor) {

        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(120, 38));
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

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        User user = userService.login(username, password);

        if (user != null) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
            dispose();
            if (user.getRole().equals("Manager")) {
                new ManagerFrame(user);
            } else {
                new CashierFrame(user);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Credentials!");
        }
    }

    private void register() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fill all fields!");
            return;
        }

        String[] roles = {"Cashier", "Manager"};
        String role = (String) JOptionPane.showInputDialog(
                this,
                "Select Role:",
                "Role",
                JOptionPane.QUESTION_MESSAGE,
                null,
                roles,
                roles[0]
        );

        if (role != null) {
            if (role.equals("Manager")) {
                userService.registerUser(new Manager(username, password));
            } else {
                userService.registerUser(new Cashier(username, password));
            }
            JOptionPane.showMessageDialog(this, "Account Created Successfully!");
        }
    }
}