📚 Library Management System (Java + MySQL)
A secure, multi-tier desktop application designed to automate library operations, inventory management, and member transactions.

🚀 Key Features
Secure Authentication: Integrated a librarian login gatekeeper using PreparedStatements to prevent SQL Injection attacks.

Full CRUD Support: Engineered complete Create, Read, Update, and Delete capabilities for book and member management.

ACID-Compliant Transactions: Managed complex "Issue" and "Return" logic using manual commit and rollback protocols to ensure data integrity.

Dynamic Search: Implemented fuzzy search functionality using SQL LIKE operators for real-time inventory filtering.

Automated Reporting: Built a file utility to export the current library inventory into professional .txt reports using Java File I/O.

🛠️ Technical Stack
Language: Java (JDK 22)

Database: MySQL 8.0

Driver: JDBC (MySQL Connector/J)

Architecture: N-Tier / DAO (Data Access Object) Pattern

📂 Project Structure
Plaintext
src/
├── DBConnection.java    # Centralized JDBC connection helper
├── Login.java           # Authentication & Security logic
├── Main.java            # Dashboard & UI Menu navigation
├── AddBook.java         # CRUD: Create logic
├── ViewBooks.java       # CRUD: Read logic (Formatting via printf)
├── SearchBook.java      # Dynamic SQL query logic
├── IssueBook.java       # Transaction Management (Update & Log)
├── ReturnBook.java      # Transaction Management (Update & Delete)
└── ExportInventory.java # File I/O operations
📝 Setup & Installation
Database: Execute the SQL scripts in your MySQL terminal to initialize the LibraryDB.

Driver: Add the mysql-connector-j JAR file to your project's Referenced Libraries.

Connection: Update the credentials in DBConnection.java with your local MySQL password.

Run: Launch the system by running Main.java in your IDE.
