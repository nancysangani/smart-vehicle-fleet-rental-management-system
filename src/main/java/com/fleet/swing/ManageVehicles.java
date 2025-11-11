package com.fleet.swing;

import com.fleet.model.Vehicle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ManageVehicles extends JPanel {

    private SessionFactory sessionFactory;
    private JTable vehicleTable;
    private DefaultTableModel tableModel;

    public ManageVehicles(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        initializeUI();
        loadVehicles();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        
        JLabel headerLabel = new JLabel("Manage Vehicles");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 22));
        headerLabel.setForeground(new Color(13, 110, 253));
        headerPanel.add(headerLabel, BorderLayout.WEST);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setFont(new Font("Arial", Font.PLAIN, 12));
        refreshButton.setBackground(new Color(13, 110, 253));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFocusPainted(false);
        refreshButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        refreshButton.addActionListener(e -> loadVehicles());
        headerPanel.add(refreshButton, BorderLayout.EAST);

        add(headerPanel, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Make", "Model", "Year", "License Plate", 
                                "Type", "Status", "Daily Rate"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        vehicleTable = new JTable(tableModel);
        vehicleTable.setFont(new Font("Arial", Font.PLAIN, 12));
        vehicleTable.setRowHeight(30);
        vehicleTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        vehicleTable.getTableHeader().setBackground(new Color(13, 110, 253));
        vehicleTable.getTableHeader().setForeground(Color.WHITE);
        vehicleTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(vehicleTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        buttonPanel.setBackground(Color.WHITE);

        JButton updateButton = createActionButton("Update");
        JButton deleteButton = createActionButton("Delete");
        JButton toggleStatusButton = createActionButton("Toggle Status");

        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(toggleStatusButton);

        add(buttonPanel, BorderLayout.SOUTH);

        updateButton.addActionListener(e -> updateVehicle());
        deleteButton.addActionListener(e -> deleteVehicle());
        toggleStatusButton.addActionListener(e -> toggleVehicleStatus());
    }

    private JButton createActionButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setBackground(new Color(13, 110, 253));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(130, 35));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void loadVehicles() {
        tableModel.setRowCount(0);
        
        try (Session session = sessionFactory.openSession()) {
            Query<Vehicle> query = session.createQuery("FROM Vehicle ORDER BY id DESC", Vehicle.class);
            List<Vehicle> vehicles = query.list();

            for (Vehicle vehicle : vehicles) {
                Object[] row = {
                    vehicle.getId(),
                    vehicle.getMake(),
                    vehicle.getModel(),
                    vehicle.getYear(),
                    vehicle.getLicensePlate(),
                    vehicle.getVehicleType(),
                    vehicle.getStatus(),
                    "$" + vehicle.getDailyRate()
                };
                tableModel.addRow(row);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error loading vehicles: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateVehicle() {
        int selectedRow = vehicleTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a vehicle to update",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Long vehicleId = (Long) tableModel.getValueAt(selectedRow, 0);
        
        try (Session session = sessionFactory.openSession()) {
            Vehicle vehicle = session.get(Vehicle.class, vehicleId);
            if (vehicle != null) {
                String newRate = JOptionPane.showInputDialog(this,
                        "Enter new daily rate:",
                        vehicle.getDailyRate().toString());
                
                if (newRate != null && !newRate.trim().isEmpty()) {
                    Transaction tx = session.beginTransaction();
                    vehicle.setDailyRate(new java.math.BigDecimal(newRate));
                    session.update(vehicle);
                    tx.commit();
                    
                    JOptionPane.showMessageDialog(this,
                            "Vehicle updated successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    loadVehicles();
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error updating vehicle: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteVehicle() {
        int selectedRow = vehicleTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a vehicle to delete",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this vehicle?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            Long vehicleId = (Long) tableModel.getValueAt(selectedRow, 0);
            
            Transaction tx = null;
            try (Session session = sessionFactory.openSession()) {
                tx = session.beginTransaction();
                Vehicle vehicle = session.get(Vehicle.class, vehicleId);
                if (vehicle != null) {
                    session.delete(vehicle);
                    tx.commit();
                    
                    JOptionPane.showMessageDialog(this,
                            "Vehicle deleted successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    loadVehicles();
                }
            } catch (Exception ex) {
                if (tx != null) tx.rollback();
                JOptionPane.showMessageDialog(this,
                        "Error deleting vehicle: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void toggleVehicleStatus() {
        int selectedRow = vehicleTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a vehicle",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Long vehicleId = (Long) tableModel.getValueAt(selectedRow, 0);
        
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Vehicle vehicle = session.get(Vehicle.class, vehicleId);
            if (vehicle != null) {
                String newStatus = vehicle.getStatus().equals("AVAILABLE") ? "BOOKED" : "AVAILABLE";
                vehicle.setStatus(newStatus);
                session.update(vehicle);
                tx.commit();
                
                JOptionPane.showMessageDialog(this,
                        "Vehicle status updated to " + newStatus,
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                loadVehicles();
            }
        } catch (Exception ex) {
            if (tx != null) tx.rollback();
            JOptionPane.showMessageDialog(this,
                    "Error updating status: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}