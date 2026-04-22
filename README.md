# Tails-Together - Pet Clinic Management System

A full-stack MVC web application designed to manage veterinary appointments. Built with a focus on native database interactions and clean, responsive UI.

## Features
* **Role-Based Access Control (RBAC):** Distinct dashboards for standard users and administrators.
* **User Portal:** Pet owners can securely log in, book appointments, and track their personal visit history.
* **Admin Control:** Administrators can view all clinic appointments and cancel bookings in real-time.
* **Session Security:** Protected routes using Java `HttpSession` to prevent unauthorized access.

## Tech Stack
* **Frontend:** HTML5, CSS3, JavaScript, Bootstrap
* **Backend:** Java EE (Servlets, JSP)
* **Database:** MySQL (Native SQL Queries via JDBC)
* **Server:** Apache Tomcat 11

## Setup Instructions
1. Clone the repository: `git clone https://github.com/yashv14/TailsTogether.git`
2. Run the SQL script provided in the `/db` folder to initialize the MySQL database.
3. Update the `DBUtil.java` file with your MySQL credentials.
4. Deploy the project to an Apache Tomcat server.
