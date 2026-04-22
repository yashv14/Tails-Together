-- ========================================================
-- Tails-Together Setup
-- ========================================================

-- 1. Create the database and switch to it
CREATE DATABASE IF NOT EXISTS pet_clinic;
USE pet_clinic;

-- 2. Clear out old tables if they exist (Useful for resetting the project)
DROP TABLE IF EXISTS appointments;
DROP TABLE IF EXISTS users;

-- ========================================================
-- Table: users
-- Purpose: Stores login credentials for admins and customers
-- ========================================================
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL
);

-- Insert the Master Admin Account
INSERT INTO users (username, password) 
VALUES ('admin', 'password123');

-- Insert a sample user account for testing
INSERT INTO users (username, password) 
VALUES ('testuser', '123');

-- ========================================================
-- Table: appointments
-- Purpose: Stores all booking data and links it to a user
-- ========================================================
CREATE TABLE appointments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    owner_name VARCHAR(100) NOT NULL,
    pet_name VARCHAR(100) NOT NULL,
    service VARCHAR(50) NOT NULL,
    
    -- Using DATE and TIME data types specifically for Java compatibility
    appt_date DATE,
    appt_time TIME,
    
    notes TEXT,
    
    -- The username of the person who booked it (Links back to the users table)
    username VARCHAR(50) NOT NULL,
    
    -- Automatically records exactly when the appointment was booked
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);