package com.fleet.swing;

import com.fleet.model.Admin;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboard extends JFrame {

    private SessionFactory sessionFactory;
    private Admin admin;
    private JPanel contentPanel;

    public AdminDashboard(SessionFactory sessionFactory, Admin admin) {
        this.sessionFactory = sessionFactory;
        this.admin = admin;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Admin Dashboard - Smart Vehicle Fleet");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(13, 110, 253));
        headerPanel.setPreferredSize(new Dimension(1000, 70));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel titleLabel = new JLabel("Smart Vehicle Fleet - Admin Dashboard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.WEST);

        JLabel adminLabel = new JLabel("Welcome, " + admin.getFullName());
        adminLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        adminLabel.setForeground(Color.WHITE);
        headerPanel.add(adminLabel, BorderLayout.EAST);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(new Color(248, 249, 250));
        sidebarPanel.setPreferredSize(new Dimension(220, 630));
        sidebarPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JButton addVehicleBtn = createMenuButton("Add Vehicle");
        JButton manageVehiclesBtn = createMenuButton("Manage Vehicles");
        JButton viewBookingsBtn = createMenuButton("View Bookings");
        JButton logoutBtn = createMenuButton("Logout");

        sidebarPanel.add(addVehicleBtn);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebarPanel.add(manageVehiclesBtn);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebarPanel.add(viewBookingsBtn);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebarPanel.add(logoutBtn);

        mainPanel.add(sidebarPanel, BorderLayout.WEST);

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        showWelcomePanel();
        
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        addVehicleBtn.addActionListener(e -> {
            contentPanel.removeAll();
            contentPanel.add(new AddVehicleForm(sessionFactory), BorderLayout.CENTER);
            contentPanel.revalidate();
            contentPanel.repaint();
        });

        manageVehiclesBtn.addActionListener(e -> {
            contentPanel.removeAll();
            contentPanel.add(new ManageVehicles(sessionFactory), BorderLayout.CENTER);
            contentPanel.revalidate();
            contentPanel.repaint();
        });

        viewBookingsBtn.addActionListener(e -> {
            contentPanel.removeAll();
            contentPanel.add(new ViewBookings(sessionFactory), BorderLayout.CENTER);
            contentPanel.revalidate();
            contentPanel.repaint();
        });

        logoutBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to logout?",
                    "Confirm Logout",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dispose();
                new AdminLogin();
            }
        });

        add(mainPanel);
        setVisible(true);
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setMaximumSize(new Dimension(200, 45));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFocusPainted(false);
        button.setBackground(Color.WHITE);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(13, 110, 253), 1),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(13, 110, 253));
                button.setForeground(Color.WHITE);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.WHITE);
                button.setForeground(Color.BLACK);
            }
        });
        
        return button;
    }

    private void showWelcomePanel() {
        JPanel welcomePanel = new JPanel(new GridBagLayout());
        welcomePanel.setBackground(Color.WHITE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JLabel welcomeLabel = new JLabel("Welcome to Admin Dashboard");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 28));
        welcomeLabel.setForeground(new Color(13, 110, 253));
        welcomePanel.add(welcomeLabel, gbc);
        
        gbc.gridy = 1;
        JLabel instructionLabel = new JLabel("Select an option from the menu to get started");
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        instructionLabel.setForeground(Color.GRAY);
        welcomePanel.add(instructionLabel, gbc);
        
        contentPanel.add(welcomePanel, BorderLayout.CENTER);
    }
}