# Event_Management_System
A Java Swing application with SQL Server integration for managing event administration, staff, visitors, shows, tickets, and feedback. Features a tabbed GUI interface with CRUD operations for all entities.

📌 Features
1. Comprehensive Entity Management
Admin Panel: Manage administrator accounts
Sponsor Panel: Handle event sponsors
Staff Panel: Organize event staff members
Visitor Panel: Track event attendees
Show Panel: Schedule and manage events
Feedback Panel: Collect and view visitor feedback
Ticket Panel: Process ticket requests
Staff-Show Panel: Assign staff to events

2. Database Operations
SQL Server integration (JDBC)
CRUD operations for all entities
Data validation with error handling

3. User Interface
Tabbed interface for easy navigation
Form-based input for each entity
Text area output displaying operation results

🛠️ Technologies Used
Java Swing (GUI)
JDBC (SQL Server connectivity)
MSSQL (Database backend)
OOP Principles (Encapsulation, Inheritance, Polymorphism)

🚀 Getting Started
Prerequisites

Java JDK 8+
SQL Server with database 
Integrated Windows Authentication enabled
Running the Application
Clone the repository

Compile:
javac MAIN22.java

Run:
java MAIN22

📂 Project Structure
MAIN22.java                 # Main application class
  ├── Admin Panel           # Admin management
  ├── Sponsor Panel         # Sponsor management  
  ├── Staff Panel           # Staff management
  ├── Visitor Panel         # Visitor management
  ├── Show Panel            # Event management
  ├── Feedback Panel        # Feedback handling
  ├── Ticket Panel          # Ticket processing
  └── Staff-Show Panel      # Staff assignment
  
🔍 Code Highlights
Database Connection:
String url = "jdbc:sqlserver://localhost\\MSSQLSERVERR:1433;DatabaseName=12th_final;integratedSecurity=true;"

Tabbed Interface:
JTabbedPane tabbedPane = new JTabbedPane();
tabbedPane.addTab("Admin", createAdminPanel());

CRUD Operations:
// Example: Add Admin
String sql = "INSERT INTO Admin1 VALUES (?, ?, ?, ?, ?, ?)";
