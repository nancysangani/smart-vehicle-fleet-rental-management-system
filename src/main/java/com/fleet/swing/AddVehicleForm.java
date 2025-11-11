package com.fleet.swing;

import com.fleet.model.Vehicle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class AddVehicleForm extends JPanel {

    private SessionFactory sessionFactory;
    private JTextField makeField, modelField, yearField, licensePlateField;
    private JTextField seatingField, dailyRateField, imageUrlField;
    private JComboBox<String> typeCombo, fuelCombo, transmissionCombo;
    private JTextArea descriptionArea;

    public AddVehicleForm(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setBackground(Color.WHITE);
        JLabel headerLabel = new JLabel("Add New Vehicle");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 22));
        headerLabel.setForeground(new Color(13, 110, 253));
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 10, 8, 10);

        int row = 0;

        addFormField(formPanel, gbc, "Make:", row++, makeField = new JTextField(20));
        addFormField(formPanel, gbc, "Model:", row++, modelField = new JTextField(20));
        addFormField(formPanel, gbc, "Year:", row++, yearField = new JTextField(20));
        addFormField(formPanel, gbc, "License Plate:", row++, licensePlateField = new JTextField(20));

        String[] types = {"Sedan", "SUV", "Truck", "Van", "Coupe", "Hatchback"};
        typeCombo = new JComboBox<>(types);
        addFormField(formPanel, gbc, "Vehicle Type:", row++, typeCombo);

        String[] fuels = {"Petrol", "Diesel", "Electric", "Hybrid"};
        fuelCombo = new JComboBox<>(fuels);
        addFormField(formPanel, gbc, "Fuel Type:", row++, fuelCombo);

        String[] transmissions = {"Automatic", "Manual"};
        transmissionCombo = new JComboBox<>(transmissions);
        addFormField(formPanel, gbc, "Transmission:", row++, transmissionCombo);

        addFormField(formPanel, gbc, "Seating Capacity:", row++, seatingField = new JTextField(20));
        addFormField(formPanel, gbc, "Daily Rate ($):", row++, dailyRateField = new JTextField(20));
        addFormField(formPanel, gbc, "Image URL:", row++, imageUrlField = new JTextField(20));

        gbc.gridx = 0;
        gbc.gridy = row;
        JLabel descLabel = new JLabel("Description:");
        descLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(descLabel, gbc);

        gbc.gridx = 1;
        descriptionArea = new JTextArea(4, 20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        formPanel.add(scrollPane, gbc);

        row++;
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.insets = new Insets(20, 10, 10, 10);
        JButton addButton = new JButton("Add Vehicle");
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.setBackground(new Color(13, 110, 253));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setPreferredSize(new Dimension(150, 40));
        addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        formPanel.add(addButton, gbc);

        add(new JScrollPane(formPanel), BorderLayout.CENTER);

        addButton.addActionListener(e -> addVehicle());
    }

    private void addFormField(JPanel panel, GridBagConstraints gbc, String label, int row, Component field) {
        gbc.gridx = 0;
        gbc.gridy = row;
        JLabel jLabel = new JLabel(label);
        jLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(jLabel, gbc);

        gbc.gridx = 1;
        if (field instanceof JTextField) {
            ((JTextField) field).setFont(new Font("Arial", Font.PLAIN, 14));
            ((JTextField) field).setPreferredSize(new Dimension(250, 30));
        } else if (field instanceof JComboBox) {
            ((JComboBox<?>) field).setFont(new Font("Arial", Font.PLAIN, 14));
            ((JComboBox<?>) field).setPreferredSize(new Dimension(250, 30));
        }
        panel.add(field, gbc);
    }

    private void addVehicle() {
        try {
            String make = makeField.getText().trim();
            String model = modelField.getText().trim();
            String yearStr = yearField.getText().trim();
            String licensePlate = licensePlateField.getText().trim();
            String type = (String) typeCombo.getSelectedItem();
            String fuel = (String) fuelCombo.getSelectedItem();
            String transmission = (String) transmissionCombo.getSelectedItem();
            String seatingStr = seatingField.getText().trim();
            String rateStr = dailyRateField.getText().trim();
            String imageUrl = imageUrlField.getText().trim();
            String description = descriptionArea.getText().trim();

            if (make.isEmpty() || model.isEmpty() || yearStr.isEmpty() || 
                licensePlate.isEmpty() || seatingStr.isEmpty() || rateStr.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill in all required fields",
                        "Validation Error",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            int year = Integer.parseInt(yearStr);
            int seating = Integer.parseInt(seatingStr);
            BigDecimal dailyRate = new BigDecimal(rateStr);

            if (imageUrl.isEmpty()) {
                imageUrl = "https://via.placeholder.com/400x300?text=" + make + "+" + model;
            }

            Vehicle vehicle = new Vehicle(make, model, year, licensePlate, type,
                    fuel, transmission, seating, dailyRate, imageUrl, description);

            Transaction tx = null;
            try (Session session = sessionFactory.openSession()) {
                tx = session.beginTransaction();
                session.save(vehicle);
                tx.commit();

                JOptionPane.showMessageDialog(this,
                        "Vehicle added successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                clearForm();
            } catch (Exception ex) {
                if (tx != null) tx.rollback();
                throw ex;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid numbers for year, seating, and daily rate",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error adding vehicle: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        makeField.setText("");
        modelField.setText("");
        yearField.setText("");
        licensePlateField.setText("");
        seatingField.setText("");
        dailyRateField.setText("");
        imageUrlField.setText("");
        descriptionArea.setText("");
        typeCombo.setSelectedIndex(0);
        fuelCombo.setSelectedIndex(0);
        transmissionCombo.setSelectedIndex(0);
    }
}