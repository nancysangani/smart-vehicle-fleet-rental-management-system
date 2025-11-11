package com.fleet.swing;

import com.fleet.model.Booking;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewBookings extends JPanel {

    private SessionFactory sessionFactory;
    private JTable bookingTable;
    private DefaultTableModel tableModel;

    public ViewBookings(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        initializeUI();
        loadBookings();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        
        JLabel headerLabel = new JLabel("View All Bookings");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 22));
        headerLabel.setForeground(new Color(13, 110, 253));
        headerPanel.add(headerLabel, BorderLayout.WEST);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setFont(new Font("Arial", Font.PLAIN, 12));
        refreshButton.setBackground(new Color(13, 110, 253));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFocusPainted(false);
        refreshButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        refreshButton.addActionListener(e -> loadBookings());
        headerPanel.add(refreshButton, BorderLayout.EAST);

        add(headerPanel, BorderLayout.NORTH);

        String[] columnNames = {"Booking ID", "User", "Vehicle", "Start Date", 
                                "End Date", "Total Amount", "Status", "Booking Date"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        bookingTable = new JTable(tableModel);
        bookingTable.setFont(new Font("Arial", Font.PLAIN, 12));
        bookingTable.setRowHeight(30);
        bookingTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        bookingTable.getTableHeader().setBackground(new Color(13, 110, 253));
        bookingTable.getTableHeader().setForeground(Color.WHITE);
        bookingTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(bookingTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel statsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        statsPanel.setBackground(Color.WHITE);
        statsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel statsLabel = new JLabel("Total Bookings: 0");
        statsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        statsPanel.add(statsLabel);

        add(statsPanel, BorderLayout.SOUTH);
    }

    private void loadBookings() {
        tableModel.setRowCount(0);
        
        try (Session session = sessionFactory.openSession()) {
            Query<Booking> query = session.createQuery(
                "FROM Booking ORDER BY bookingDate DESC", Booking.class);
            List<Booking> bookings = query.list();

            for (Booking booking : bookings) {
                Object[] row = {
                    booking.getId(),
                    booking.getUser().getUsername(),
                    booking.getVehicle().getMake() + " " + booking.getVehicle().getModel(),
                    booking.getStartDate(),
                    booking.getEndDate(),
                    "$" + booking.getTotalAmount(),
                    booking.getStatus(),
                    booking.getBookingDate().toLocalDate()
                };
                tableModel.addRow(row);
            }

            JPanel statsPanel = (JPanel) getComponent(2);
            JLabel statsLabel = (JLabel) statsPanel.getComponent(0);
            statsLabel.setText("Total Bookings: " + bookings.size());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error loading bookings: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}