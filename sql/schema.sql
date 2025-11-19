-- Create Database
CREATE DATABASE IF NOT EXISTS fleet_management;
USE fleet_management;

CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS admins (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS vehicles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    make VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    year INT NOT NULL,
    license_plate VARCHAR(20) UNIQUE NOT NULL,
    vehicle_type VARCHAR(30) NOT NULL,
    fuel_type VARCHAR(20) NOT NULL,
    transmission VARCHAR(20) NOT NULL,
    seating_capacity INT NOT NULL,
    daily_rate DECIMAL(10, 2) NOT NULL,
    status VARCHAR(20) DEFAULT 'AVAILABLE',
    image_url VARCHAR(255),
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS bookings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    vehicle_id BIGINT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    status VARCHAR(20) DEFAULT 'PENDING',
    booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(id) ON DELETE CASCADE
);

INSERT INTO admins (username, password, email, full_name) VALUES
('admin', 'admin123', 'admin@fleet.com', 'System Administrator'),
('manager', 'manager123', 'manager@fleet.com', 'Fleet Manager');

INSERT INTO vehicles (make, model, year, license_plate, vehicle_type, fuel_type, transmission, seating_capacity, daily_rate, status, image_url, description) VALUES
('Toyota', 'Camry', 2023, 'ABC-1234', 'Sedan', 'Petrol', 'Automatic', 5, 45.00, 'AVAILABLE', '/resources/images/vehicles/Toyota-Camry.jpg', 'Comfortable mid-size sedan with excellent fuel efficiency'),
('Honda', 'CR-V', 2023, 'XYZ-5678', 'SUV', 'Hybrid', 'Automatic', 7, 65.00, 'AVAILABLE', '/resources/images/vehicles/Honda-CR-V.jpg', 'Spacious SUV perfect for family trips'),
('Tesla', 'Model 3', 2024, 'TES-9012', 'Sedan', 'Electric', 'Automatic', 5, 85.00, 'AVAILABLE', '/resources/images/vehicles/Tesla-Model-3.jpg', 'Premium electric vehicle with autopilot'),
('Ford', 'F-150', 2023, 'FOR-3456', 'Truck', 'Diesel', 'Automatic', 5, 75.00, 'AVAILABLE', '/resources/images/vehicles/Ford-F-150.jpg', 'Heavy-duty pickup truck for work and adventure'),
('BMW', 'X5', 2024, 'BMW-7890', 'SUV', 'Petrol', 'Automatic', 7, 95.00, 'AVAILABLE', '/resources/images/vehicles/BMW-X5.jpg', 'Luxury SUV with premium features'),
('Mercedes', 'E-Class', 2024, 'MER-2345', 'Sedan', 'Petrol', 'Automatic', 5, 90.00, 'AVAILABLE', '/resources/images/vehicles/Mercedes-Benz-E-Class.jpg', 'Executive sedan with unmatched comfort'),
('Chevrolet', 'Tahoe', 2023, 'CHE-6789', 'SUV', 'Petrol', 'Automatic', 8, 80.00, 'AVAILABLE', '/resources/images/vehicles/Chevrolet-Tahoe.jpg', 'Large SUV ideal for group travel');

INSERT INTO users (username, password, email, full_name, phone) VALUES
('admin', 'admin123', 'admin@gmail.com', 'Admin', '+1-555-0101'),
('nancy', 'nancy29', 'nancy29', 'Nancy', '9876543210');