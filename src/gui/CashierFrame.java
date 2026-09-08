package gui;

import model.Book;
import model.User;
import Services.BookService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CashierFrame extends JFrame {

    protected BookService bookService;
    protected JTextArea textArea;

    public CashierFrame(User user) {

        bookService = new BookService();

        setTitle("Cashier Dashboard - " + user.getUsername());
        setSize(750, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        // Main Panel (Light Theme)
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
        mainPanel.setBackground(new Color(245, 247, 250));

        // Top Panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        topPanel.setBackground(new Color(245, 247, 250));

        JButton viewButton = new JButton("View All Books");
        JButton searchButton = new JButton("Search By Name");
        JButton logoutButton = new JButton("Logout");

        styleButton(viewButton, new Color(52, 152, 219));    // Blue
        styleButton(searchButton, new Color(52, 152, 219));  // Blue
        styleButton(logoutButton, new Color(231, 76, 60));   // Red

        topPanel.add(viewButton);
        topPanel.add(searchButton);
        topPanel.add(logoutButton);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Text Area
        textArea = new JTextArea();
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(Color.WHITE);
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.WHITE);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);

        // Button Actions
        viewButton.addActionListener(e -> viewBooks());
        searchButton.addActionListener(e -> searchBooks());
        logoutButton.addActionListener(e -> logout());

        setVisible(true);
    }

    // Rounded modern button style
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

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
    }

    private void viewBooks() {
        textArea.setText("");
        List<Book> books = bookService.getAllBooks();

        if (books.isEmpty()) {
            textArea.setText("No books available.");
        } else {
            for (Book b : books) {
                textArea.append(b.toString() + "\n");
            }
        }
    }

    private void searchBooks() {
        String name = JOptionPane.showInputDialog(this, "Enter Book Name:");

        if (name != null && !name.isEmpty()) {
            textArea.setText("");
            List<Book> books = bookService.searchByName(name);

            if (books.isEmpty()) {
                textArea.setText("No books found.");
            } else {
                for (Book b : books) {
                    textArea.append(b.toString() + "\n");
                }
            }
        }
    }

    private void logout() {
        dispose();
        new LoginFrame();
    }
}