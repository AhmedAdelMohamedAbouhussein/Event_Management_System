import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class MAIN22 {

    // Admin Panel Fields
    private Connection connection;

    // Admin1 Fields
    private JTextField adminIdField, fnameField, mnameField, lnameField, phoneField, emailField;
    private JTextArea adminOutputArea;

    // Sponser_AD Fields
    private JTextField adIdField, sponsorNameField, adminIdSponsorField;
    private JTextArea sponsorOutputArea;

    // Staff Fields
    private JTextField staffIdField, staffFnameField, staffMnameField, staffLnameField, staffEmailField, adminIdStaffField;
    private JTextArea staffOutputArea;

    // Visitor Fields
    private JTextField visitorIdField, visitorFnameField, visitorMnameField, visitorLnameField, visitorEmailField, adminIdVisitorField;
    private JTextArea visitorOutputArea;

    // Show Fields
    private JTextField showIdField, showNameField, showTimeField;
    private JTextArea showOutputArea;

    // Feedback Fields
    private JTextField feedbackQuestionIdField, feedbackQuestionField, feedbackField, reviewField, ratingField, adminIdFeedbackField, visitorIdFeedbackField;
    private JTextArea feedbackOutputArea;

    // Ticket Fields
    private JTextField ticketIdField, ticketPriceField, requestDateField, requestStatusField, ticketShowIdField, adminIdTicketField, ticketVisitorIdField;
    private JTextArea ticketOutputArea;

    // Staff_Show Fields
    private JTextField staffShowShowIdField, staffShowIdField;
    private JTextArea staffShowOutputArea;


    public MAIN22() {
        initialize();
        connectToDatabase();
    }


    private void initialize() {
        JFrame frame = new JFrame("Event Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Admin", createAdminPanel());
        tabbedPane.addTab("Sponsor", createSponsorPanel());
        tabbedPane.addTab("Staff", createStaffPanel());
        tabbedPane.addTab("Visitor", createVisitorPanel());
        tabbedPane.addTab("Show", createShowPanel());
        tabbedPane.addTab("Feedback Questions", createFeedbackQuestionsPanel());
        tabbedPane.addTab("Ticket", createTicketsPanel());
        tabbedPane.addTab("Staff Show", createStaffShowPanel());

        frame.add(tabbedPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public void connectToDatabase() {
        String url = "jdbc:sqlserver://LENOVO_LOQ\\MSSQLSERVERR:1433;databaseName=12th_final;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";

        try {

            connection = DriverManager.getConnection(url);
            System.out.println("Database connected!");
        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        }
    }

    // Admin Panel
    private JPanel createAdminPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Input Fields
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        inputPanel.add(new JLabel("Admin ID:"));
        adminIdField = new JTextField();
        inputPanel.add(adminIdField);

        inputPanel.add(new JLabel("First Name:"));
        fnameField = new JTextField();
        inputPanel.add(fnameField);

        inputPanel.add(new JLabel("Middle Name:"));
        mnameField = new JTextField();
        inputPanel.add(mnameField);

        inputPanel.add(new JLabel("Last Name:"));
        lnameField = new JTextField();
        inputPanel.add(lnameField);

        inputPanel.add(new JLabel("Phone Number:"));
        phoneField = new JTextField();
        inputPanel.add(phoneField);

        inputPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        inputPanel.add(emailField);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton addAdminButton = new JButton("Add Admin");
        addAdminButton.addActionListener(e -> addAdmin());
        buttonPanel.add(addAdminButton);

        JButton updateAdminButton = new JButton("Update Admin");
        updateAdminButton.addActionListener(e -> updateAdmin());
        buttonPanel.add(updateAdminButton);

        JButton deleteAdminButton = new JButton("Delete Admin");
        deleteAdminButton.addActionListener(e -> deleteAdmin());
        buttonPanel.add(deleteAdminButton);

        JButton displayAdminButton = new JButton("Display Admins");
        displayAdminButton .addActionListener(e -> displayAdmins());
        buttonPanel.add(displayAdminButton);

        // Output Area
        adminOutputArea = new JTextArea();
        adminOutputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(adminOutputArea);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(scrollPane, BorderLayout.SOUTH);

        return panel;
    }

    private void addAdmin() {
        String adminId = adminIdField.getText();
        String fname = fnameField.getText();
        String mname = mnameField.getText();
        String lname = lnameField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();

        String sql = "INSERT INTO Admin1 (Admin_ID, fname, Mname, Lname, Phone_number, Email) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(adminId));
            pstmt.setString(2, fname);
            pstmt.setString(3, mname);
            pstmt.setString(4, lname);
            pstmt.setLong(5, Long.parseLong(phone));
            pstmt.setString(6, email);
            pstmt.executeUpdate();
            adminOutputArea.append("Admin added successfully!\n");
            clearAdminFields();
        } catch (SQLException e) {
            adminOutputArea.append("Error adding admin: " + e.getMessage() + "\n");
        } catch (NumberFormatException e) {
            adminOutputArea.append("Invalid input for Admin ID or Phone Number: " + e.getMessage() + "\n");
        }
    }

    private void updateAdmin() {
        String adminId = adminIdField.getText();
        String fname = fnameField.getText();
        String mname = mnameField.getText();
        String lname = lnameField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();

        String sql = "UPDATE Admin1 SET fname = ?, Mname = ?, Lname = ?, Phone_number = ?, Email = ? WHERE Admin_ID = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, fname);
            pstmt.setString(2, mname);
            pstmt.setString(3, lname);
            pstmt.setLong(4, Long.parseLong(phone));
            pstmt.setString(5, email);
            pstmt.setInt(6, Integer.parseInt(adminId));
            pstmt.executeUpdate();
            adminOutputArea.append("Admin updated successfully!\n");
            clearAdminFields();
        } catch (SQLException e) {
            adminOutputArea.append("Error updating admin: " + e.getMessage() + "\n");
        } catch (NumberFormatException e) {
            adminOutputArea.append("Invalid input for Phone Number or Admin ID: " + e.getMessage() + "\n");
        }
    }

    private void deleteAdmin() {
        String adminId = adminIdField.getText();

        String sql = "DELETE FROM Admin1 WHERE Admin_ID = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(adminId));
            pstmt.executeUpdate();
            adminOutputArea.append("Admin deleted successfully!\n");
            clearAdminFields();
        } catch (SQLException e) {
            adminOutputArea.append("Error deleting admin: " + e.getMessage() + "\n");
        } catch (NumberFormatException e) {
            adminOutputArea.append("Invalid input for Admin ID: " + e.getMessage() + "\n");
        }
    }

    private void displayAdmins() {
        String sql = "SELECT * FROM Admin1";
        adminOutputArea.setText("");

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int adminId = rs.getInt("Admin_ID");
                String fname = rs.getString("fname");
                String mname = rs.getString("Mname");
                String lname = rs.getString("Lname");
                long phone = rs.getLong("Phone_number");
                String email = rs.getString("Email");

                adminOutputArea.append("Admin ID: " + adminId + ", Name: " + fname + " " + (mname != null ? mname + " " : "") + lname +
                        ", Phone: " + phone + ", Email: " + email + "\n");
            }
        } catch (SQLException e) {
            adminOutputArea.append("Error retrieving admins: " + e.getMessage() + "\n");
        }
    }

    private void clearAdminFields() {
        adminIdField.setText("");
        fnameField.setText("");
        mnameField.setText("");
        lnameField.setText("");
        phoneField.setText("");
        emailField.setText("");
    }

    // Sponsor Panel
    private JPanel createSponsorPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Input Fields
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("AD ID:"));
        adIdField = new JTextField();
        inputPanel.add(adIdField);

        inputPanel.add(new JLabel("Sponsor Name:"));
        sponsorNameField = new JTextField();
        inputPanel.add(sponsorNameField);

        inputPanel.add(new JLabel("Admin ID:"));
        adminIdSponsorField = new JTextField();
        inputPanel.add(adminIdSponsorField);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton addSponsorButton = new JButton("Add Sponsor");
        addSponsorButton.addActionListener(e -> addSponsor());
        buttonPanel.add(addSponsorButton);

        JButton updateSponsorButton = new JButton("Update Sponsor");
        updateSponsorButton.addActionListener(e -> updateSponsor());
        buttonPanel.add(updateSponsorButton);

        JButton deleteSponsorButton = new JButton("Delete Sponsor");
        deleteSponsorButton.addActionListener(e -> deleteSponsor());
        buttonPanel.add(deleteSponsorButton);

        JButton displaySponsorButton = new JButton("Display Sponsors");
        displaySponsorButton.addActionListener(e -> displaySponsors());
        buttonPanel.add(displaySponsorButton);

        // Output Area
        sponsorOutputArea = new JTextArea();
        sponsorOutputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(sponsorOutputArea);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(scrollPane, BorderLayout.SOUTH);

        return panel;
    }

    private void addSponsor() {
        String adId = adIdField.getText();
        String sponsorName = sponsorNameField.getText();
        String adminId = adminIdSponsorField.getText();

        String sql = "INSERT INTO Sponser_AD (AD_ID, name, admin_ID) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(adId));
            pstmt.setString(2, sponsorName);
            pstmt.setInt(3, Integer.parseInt(adminId));
            pstmt.executeUpdate();
            sponsorOutputArea.append("Sponsor added successfully!\n");
            clearSponsorFields();
        } catch (SQLException e) {
            sponsorOutputArea.append("Error adding sponsor: " + e.getMessage() + "\n");
        } catch (NumberFormatException e) {
            sponsorOutputArea.append("Invalid input for AD ID or Admin ID: " + e.getMessage() + "\n");
        }
    }

    private void updateSponsor() {
        String adId = adIdField.getText();
        String sponsorName = sponsorNameField.getText();
        String adminId = adminIdSponsorField.getText();

        String sql = "UPDATE Sponser_AD SET name = ?, admin_ID = ? WHERE AD_ID = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, sponsorName);
            pstmt.setInt(2, Integer.parseInt(adminId));
            pstmt.setInt(3, Integer.parseInt(adId));
            pstmt.executeUpdate();
            sponsorOutputArea.append("Sponsor updated successfully!\n");
            clearSponsorFields();
        } catch (SQLException e) {
            sponsorOutputArea.append("Error updating sponsor: " + e.getMessage() + "\n");
        } catch (NumberFormatException e) {
            sponsorOutputArea.append("Invalid input for AD ID or Admin ID: " + e.getMessage() + "\n");
        }
    }

    private void deleteSponsor() {
        String adId = adIdField.getText();

        String sql = "DELETE FROM Sponser_AD WHERE AD_ID = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(adId));
            pstmt.executeUpdate();
            sponsorOutputArea.append("Sponsor deleted successfully!\n");
            clearSponsorFields();
        } catch (SQLException e) {
            sponsorOutputArea.append("Error deleting sponsor: " + e.getMessage() + "\n");
        } catch (NumberFormatException e) {
            sponsorOutputArea.append("Invalid input for AD ID: " + e.getMessage() + "\n");
        }
    }

    private void displaySponsors() {
        String sql = "SELECT * FROM Sponser_AD";
        sponsorOutputArea.setText("");

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int adId = rs.getInt("AD_ID");
                String name = rs.getString("name");
                int adminId = rs.getInt("admin_ID");

                sponsorOutputArea.append("AD ID: " + adId + ", Name: " + name + ", Admin ID: " + adminId + "\n");
            }
        } catch (SQLException e) {
            sponsorOutputArea.append("Error retrieving sponsors: " + e.getMessage() + "\n");
        }
    }

    private void clearSponsorFields() {
        adIdField.setText("");
        sponsorNameField.setText("");
        adminIdSponsorField.setText("");
    }

    // Staff Panel
    private JPanel createStaffPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Input Fields
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        inputPanel.add(new JLabel("Staff ID:"));
        staffIdField = new JTextField();
        inputPanel.add(staffIdField);

        inputPanel.add(new JLabel("First Name:"));
        staffFnameField = new JTextField();
        inputPanel.add(staffFnameField);

        inputPanel.add(new JLabel("Middle Name:"));
        staffMnameField = new JTextField();
        inputPanel.add(staffMnameField);

        inputPanel.add(new JLabel("Last Name:"));
        staffLnameField = new JTextField();
        inputPanel.add(staffLnameField);

        inputPanel.add(new JLabel("Email:"));
        staffEmailField = new JTextField();
        inputPanel.add(staffEmailField);

        inputPanel.add(new JLabel("Admin ID:"));
        adminIdStaffField = new JTextField();
        inputPanel.add(adminIdStaffField);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton addStaffButton = new JButton("Add Staff");
        addStaffButton.addActionListener(e -> addStaff());
        buttonPanel.add(addStaffButton);

        JButton updateStaffButton = new JButton("Update Staff");
        updateStaffButton.addActionListener(e -> updateStaff());
        buttonPanel.add(updateStaffButton);

        JButton deleteStaffButton = new JButton("Delete Staff");
        deleteStaffButton.addActionListener(e -> deleteStaff());
        buttonPanel.add(deleteStaffButton);

        JButton displayStaffButton = new JButton("Display Staff");
        displayStaffButton.addActionListener(e -> displayStaff());
        buttonPanel.add(displayStaffButton);

        // Output Area
        staffOutputArea = new JTextArea();
        staffOutputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(staffOutputArea);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(scrollPane, BorderLayout.SOUTH);

        return panel;
    }

    private void addStaff() {
        String staffId = staffIdField.getText();
        String fname = staffFnameField.getText();
        String mname = staffMnameField.getText();
        String lname = staffLnameField.getText();
        String email = staffEmailField.getText();
        String adminId = adminIdStaffField.getText();

        String sql = "INSERT INTO Staff (Staff_ID, fname, Mname, Lname, Email, admin_ID) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(staffId));
            pstmt.setString(2, fname);
            pstmt.setString(3, mname);
            pstmt.setString(4, lname);
            pstmt.setString(5, email);
            pstmt.setInt(6, Integer.parseInt(adminId));
            pstmt.executeUpdate();
            staffOutputArea.append("Staff added successfully!\n");
            clearStaffFields();
        } catch (SQLException e) {
            staffOutputArea.append("Error adding staff: " + e.getMessage() + "\n");
        } catch (NumberFormatException e) {
            staffOutputArea.append("Invalid input for Staff ID or Admin ID: " + e.getMessage() + "\n");
        }
    }

    private void updateStaff() {
        String staffId = staffIdField.getText();
        String fname = staffFnameField.getText();
        String mname = staffMnameField.getText();
        String lname = staffLnameField.getText();
        String email = staffEmailField.getText();
        String adminId = adminIdStaffField.getText();

        String sql = "UPDATE Staff SET fname = ?, Mname = ?, Lname = ?, Email = ?, admin_ID = ? WHERE Staff_ID = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, fname);
            pstmt.setString(2, mname);
            pstmt.setString(3, lname);
            pstmt.setString(4, email);
            pstmt.setInt(5, Integer.parseInt(adminId));
            pstmt.setInt(6, Integer.parseInt(staffId));
            pstmt.executeUpdate();
            staffOutputArea.append("Staff updated successfully!\n");
            clearStaffFields();
        } catch (SQLException e) {
            staffOutputArea.append("Error updating staff: " + e.getMessage() + "\n");
        } catch (NumberFormatException e) {
            staffOutputArea.append("Invalid input for Staff ID or Admin ID: " + e.getMessage() + "\n");
        }
    }

    private void deleteStaff() {
        String staffId = staffIdField.getText();

        String sql = "DELETE FROM Staff WHERE Staff_ID = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(staffId));
            pstmt.executeUpdate();
            staffOutputArea.append("Staff deleted successfully!\n");
            clearStaffFields();
        } catch (SQLException e) {
            staffOutputArea.append("Error deleting staff: " + e.getMessage() + "\n");
        } catch (NumberFormatException e) {
            staffOutputArea.append("Invalid input for Staff ID: " + e.getMessage() + "\n");
        }
    }

    private void displayStaff() {
        String sql = "SELECT * FROM Staff";
        staffOutputArea.setText("");

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int staffId = rs.getInt("Staff_ID");
                String fname = rs.getString("fname");
                String mname = rs.getString("Mname");
                String lname = rs.getString("Lname");
                String email = rs.getString("Email");
                int adminId = rs.getInt("admin_ID");

                staffOutputArea.append("Staff ID: " + staffId + ", Name: " + fname + " " + (mname != null ? mname + " " : "") + lname +
                        ", Email: " + email + ", Admin ID: " + adminId + "\n");
            }
        } catch (SQLException e) {
            staffOutputArea.append("Error retrieving staff: " + e.getMessage() + "\n");
        }
    }

    private void clearStaffFields() {
        staffIdField.setText("");
        staffFnameField.setText("");
        staffMnameField.setText("");
        staffLnameField.setText("");
        staffEmailField.setText("");
        adminIdStaffField.setText("");
    }

    // Visitor Panel
    private JPanel createVisitorPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Input Fields
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        inputPanel.add(new JLabel("Visitor ID:"));
        visitorIdField = new JTextField();
        inputPanel.add(visitorIdField);

        inputPanel.add(new JLabel("First Name:"));
        visitorFnameField = new JTextField();
        inputPanel.add(visitorFnameField);

        inputPanel.add(new JLabel("Middle Name:"));
        visitorMnameField = new JTextField();
        inputPanel.add(visitorMnameField);

        inputPanel.add(new JLabel("Last Name:"));
        visitorLnameField = new JTextField();
        inputPanel.add(visitorLnameField);

        inputPanel.add(new JLabel("Email:"));
        visitorEmailField = new JTextField();
        inputPanel.add(visitorEmailField);

        inputPanel.add(new JLabel("Admin ID:"));
        adminIdVisitorField = new JTextField();
        inputPanel.add(adminIdVisitorField);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton addVisitorButton = new JButton("Add Visitor");
        addVisitorButton.addActionListener(e -> addVisitor());
        buttonPanel.add(addVisitorButton);

        JButton updateVisitorButton = new JButton("Update Visitor");
        updateVisitorButton.addActionListener(e -> updateVisitor());
        buttonPanel.add(updateVisitorButton);

        JButton deleteVisitorButton = new JButton("Delete Visitor");
        deleteVisitorButton.addActionListener(e -> deleteVisitor());
        buttonPanel.add(deleteVisitorButton);

        JButton displayVisitorButton = new JButton("Display Visitors");
        displayVisitorButton.addActionListener(e -> displayVisitors());
        buttonPanel.add(displayVisitorButton);

        // Output Area
        visitorOutputArea = new JTextArea();
        visitorOutputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(visitorOutputArea);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(scrollPane, BorderLayout.SOUTH);

        return panel;
    }

    private void addVisitor() {
        String visitorId = visitorIdField.getText();
        String fname = visitorFnameField.getText();
        String mname = visitorMnameField.getText();
        String lname = visitorLnameField.getText();
        String email = visitorEmailField.getText();
        String adminId = adminIdVisitorField.getText();

        String sql = "INSERT INTO Visitor (Visitor_ID, fname, Mname, Lname, Email, admin_ID) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(visitorId));
            pstmt.setString(2, fname);
            pstmt.setString(3, mname);
            pstmt.setString(4, lname);
            pstmt.setString(5, email);
            pstmt.setInt(6, Integer.parseInt(adminId));
            pstmt.executeUpdate();
            visitorOutputArea.append("Visitor added successfully!\n");
            clearVisitorFields();
        } catch (SQLException e) {
            visitorOutputArea.append("Error adding visitor: " + e.getMessage() + "\n");
        } catch (NumberFormatException e) {
            visitorOutputArea.append("Invalid input for Visitor ID or Admin ID: " + e.getMessage() + "\n");
        }
    }

    private void updateVisitor() {
        String visitorId = visitorIdField.getText();
        String fname = visitorFnameField.getText();
        String mname = visitorMnameField.getText();
        String lname = visitorLnameField.getText();
        String email = visitorEmailField.getText();
        String adminId = adminIdVisitorField.getText();

        String sql = "UPDATE Visitor SET fname = ?, Mname = ?, Lname = ?, Email = ?, admin_ID = ? WHERE Visitor_ID = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, fname);
            pstmt.setString(2, mname);
            pstmt.setString(3, lname);
            pstmt.setString(4, email);
            pstmt.setInt(5, Integer.parseInt(adminId));
            pstmt.setInt(6, Integer.parseInt(visitorId));
            pstmt.executeUpdate();
            visitorOutputArea.append("Visitor updated successfully!\n");
            clearVisitorFields();
        } catch (SQLException e) {
            visitorOutputArea.append("Error updating visitor: " + e.getMessage() + "\n");
        } catch (NumberFormatException e) {
            visitorOutputArea.append("Invalid input for Visitor ID or Admin ID: " + e.getMessage() + "\n");
        }
    }

    private void deleteVisitor() {
        String visitorId = visitorIdField.getText();

        String sql = "DELETE FROM Visitor WHERE Visitor_ID = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(visitorId));
            pstmt.executeUpdate();
            visitorOutputArea.append("Visitor deleted successfully!\n");
            clearVisitorFields();
        } catch (SQLException e) {
            visitorOutputArea.append("Error deleting visitor: " + e.getMessage() + "\n");
        } catch (NumberFormatException e) {
            visitorOutputArea.append("Invalid input for Visitor ID: " + e.getMessage() + "\n");
        }
    }

    private void displayVisitors() {
        String sql = "SELECT * FROM Visitor";
        visitorOutputArea.setText("");

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int visitorId = rs.getInt("Visitor_ID");
                String fname = rs.getString("fname");
                String mname = rs.getString("Mname");
                String lname = rs.getString("Lname");
                String email = rs.getString("Email");
                int adminId = rs.getInt("admin_ID");

                visitorOutputArea.append("Visitor ID: " + visitorId + ", Name: " + fname + " " + (mname != null ? mname + " " : "") + lname +
                        ", Email: " + email + ", Admin ID: " + adminId + "\n");
            }
        } catch (SQLException e) {
            visitorOutputArea.append("Error retrieving visitors: " + e.getMessage() + "\n");
        }
    }

    private void clearVisitorFields() {
        visitorIdField.setText("");
        visitorFnameField.setText("");
        visitorMnameField.setText("");
        visitorLnameField.setText("");
        visitorEmailField.setText("");
        adminIdVisitorField.setText("");
    }

    // Show Panel
    private JPanel createShowPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Input Fields
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Show ID:"));
        showIdField = new JTextField();
        inputPanel.add(showIdField);

        inputPanel.add(new JLabel("Show Name:"));
        showNameField = new JTextField ();
        inputPanel.add(showNameField);

        inputPanel.add(new JLabel("Show Time:"));
        showTimeField = new JTextField();
        inputPanel.add(showTimeField);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton addShowButton = new JButton("Add Show");
        addShowButton.addActionListener(e -> addShow());
        buttonPanel.add(addShowButton);

        JButton updateShowButton = new JButton("Update Show");
        updateShowButton.addActionListener(e -> updateShow());
        buttonPanel.add(updateShowButton);

        JButton deleteShowButton = new JButton("Delete Show");
        deleteShowButton.addActionListener(e -> deleteShow());
        buttonPanel.add(deleteShowButton);

        JButton displayShowButton = new JButton("Display Shows");
        displayShowButton.addActionListener(e -> displayShows());
        buttonPanel.add(displayShowButton);

        // Output Area
        showOutputArea = new JTextArea();
        showOutputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(showOutputArea);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(scrollPane, BorderLayout.SOUTH);

        return panel;
    }

    private void addShow() {
        String showId = showIdField.getText();
        String showName = showNameField.getText();
        String showTime = showTimeField.getText();

        String sql = "INSERT INTO show (Show_ID, name, show_date) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(showId));
            pstmt.setString(2, showName);
            pstmt.setString(3, showTime);
            pstmt.executeUpdate();
            showOutputArea.append("Show added successfully!\n");
            clearShowFields();
        } catch (SQLException e) {
            showOutputArea.append("Error adding show: " + e.getMessage() + "\n");
        } catch (NumberFormatException e) {
            showOutputArea.append("Invalid input for Show ID: " + e.getMessage() + "\n");
        }
    }

    private void updateShow() {
        String showId = showIdField.getText();
        String showName = showNameField.getText();
        String showTime = showTimeField.getText();

        String sql = "UPDATE show SET name = ?, show_date = ? WHERE Show_ID = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, showName);
            pstmt.setString(2, showTime);
            pstmt.setInt(3, Integer.parseInt(showId));
            pstmt.executeUpdate();
            showOutputArea.append("Show updated successfully!\n");
            clearShowFields();
        } catch (SQLException e) {
            showOutputArea.append("Error updating show: " + e.getMessage() + "\n");
        } catch (NumberFormatException e) {
            showOutputArea.append("Invalid input for Show ID: " + e.getMessage() + "\n");
        }
    }

    private void deleteShow() {
        String showId = showIdField.getText();

        String sql = "DELETE FROM show WHERE Show_ID = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(showId));
            pstmt.executeUpdate();
            showOutputArea.append("Show deleted successfully!\n");
            clearShowFields();
        } catch (SQLException e) {
            showOutputArea.append("Error deleting show: " + e.getMessage() + "\n");
        } catch (NumberFormatException e) {
            showOutputArea.append("Invalid input for Show ID: " + e.getMessage() + "\n");
        }
    }

    private void displayShows() {
        String sql = "SELECT * FROM show";
        showOutputArea.setText("");

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int showId = rs.getInt("Show_ID");
                String showName = rs.getString("name");
                String showTime = rs.getString("show_date");

                showOutputArea.append("Show ID: " + showId + ", Name: " + showName + ", Time: " + showTime + "\n");
            }
        } catch (SQLException e) {
            showOutputArea.append("Error retrieving shows: " + e.getMessage() + "\n");
        }
    }

    private void clearShowFields() {
        showIdField.setText("");
        showNameField.setText("");
        showTimeField.setText("");
    }

    // Feedback Questions Panel
    private JPanel createFeedbackQuestionsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Input Fields
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Question ID:"));
        feedbackQuestionIdField = new JTextField();
        inputPanel.add(feedbackQuestionIdField);

        inputPanel.add(new JLabel("Question:"));
        feedbackQuestionField = new JTextField();
        inputPanel.add(feedbackQuestionField);

        inputPanel.add(new JLabel("Feedback:"));
        feedbackField = new JTextField();
        inputPanel.add(feedbackField);

        inputPanel.add(new JLabel("Review:"));
        reviewField = new JTextField();
        inputPanel.add(reviewField);

        inputPanel.add(new JLabel("Rating:"));
        ratingField = new JTextField();
        inputPanel.add(ratingField);

        inputPanel.add(new JLabel("Admin ID:"));
        adminIdFeedbackField = new JTextField();
        inputPanel.add(adminIdFeedbackField);

        inputPanel.add(new JLabel("Visitor ID:"));
        visitorIdFeedbackField = new JTextField();
        inputPanel.add(visitorIdFeedbackField);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton addFeedbackQuestionButton = new JButton("Add Question");
        addFeedbackQuestionButton.addActionListener(e -> addFeedbackQuestion());
        buttonPanel.add(addFeedbackQuestionButton);

        JButton updateFeedbackQuestionButton = new JButton("Update Question");
        updateFeedbackQuestionButton.addActionListener(e -> updateFeedbackQuestion());
        buttonPanel.add(updateFeedbackQuestionButton);

        JButton deleteFeedbackQuestionButton = new JButton("Delete Question");
        deleteFeedbackQuestionButton.addActionListener(e -> deleteFeedbackQuestion());
        buttonPanel.add(deleteFeedbackQuestionButton);

        JButton displayFeedbackQuestionsButton = new JButton("Display Questions");
        displayFeedbackQuestionsButton.addActionListener(e -> displayFeedbackQuestions());
        buttonPanel.add(displayFeedbackQuestionsButton);

        // Output Area
        feedbackOutputArea = new JTextArea();
        feedbackOutputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(feedbackOutputArea);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(scrollPane, BorderLayout.SOUTH);

        return panel;
    }

    private void addFeedbackQuestion() {
        String questionId = feedbackQuestionIdField.getText();
        String question = feedbackQuestionField.getText();
        String feedback = feedbackField.getText();
        String review = reviewField.getText();
        String rating = ratingField.getText();
        String adminId = adminIdFeedbackField.getText();
        String visitorId = visitorIdFeedbackField.getText();

        String sql = "INSERT INTO feedback_Questions (Feedback_number, Question, feedback, review, rating, admin_ID, visitor_ID) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(questionId));
            pstmt.setString(2, question);
            pstmt.setString(3, feedback);
            pstmt.setString(4, review);
            pstmt.setInt(5, Integer.parseInt(rating));
            pstmt.setInt(6, Integer.parseInt(adminId));
            pstmt.setInt(7, Integer.parseInt(visitorId));
            pstmt.executeUpdate();
            feedbackOutputArea.append("Feedback question added successfully!\n");
            clearFeedbackFields();
        } catch (SQLException e) {
            feedbackOutputArea.append("Error adding feedback question: " + e.getMessage() + "\n");
        } catch (NumberFormatException e) {
            feedbackOutputArea.append("Invalid input for Question ID, Rating, Admin ID, or Visitor ID: " + e.getMessage() + "\n");
        }
    }

    private void updateFeedbackQuestion() {
        String questionId = feedbackQuestionIdField.getText();
        String question = feedbackQuestionField.getText();
        String feedback = feedbackField.getText();
        String review = reviewField.getText();
        String rating = ratingField.getText();
        String adminId = adminIdFeedbackField.getText();
        String visitorId = visitorIdFeedbackField.getText();

        String sql = "UPDATE feedback_Questions SET Question = ?, feedback = ?, review = ?, rating = ?, admin_ID = ?, visitor_ID = ? WHERE Feedback_number = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, question);
            pstmt.setString(2, feedback);
            pstmt.setString(3, review);
            pstmt.setInt(4, Integer.parseInt(rating));
            pstmt.setInt(5, Integer.parseInt(adminId));
            pstmt.setInt(6, Integer.parseInt(visitorId));
            pstmt.setInt(7, Integer.parseInt(questionId));
            pstmt.executeUpdate();
            feedbackOutputArea.append("Feedback question updated successfully!\n");
            clearFeedbackFields();
        } catch (SQLException e) {
            feedbackOutputArea.append("Error updating feedback question : " + e.getMessage() + "\n");
        } catch (NumberFormatException e) {
            feedbackOutputArea.append("Invalid input for Question ID, Rating, Admin ID, or Visitor ID: " + e.getMessage() + "\n");
        }
    }

    private void deleteFeedbackQuestion() {
        String questionId = feedbackQuestionIdField.getText();

        String sql = "DELETE FROM feedback_Questions WHERE Feedback_number = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(questionId));
            pstmt.executeUpdate();
            feedbackOutputArea.append("Feedback question deleted successfully!\n");
            clearFeedbackFields();
        } catch (SQLException e) {
            feedbackOutputArea.append("Error deleting feedback question: " + e.getMessage() + "\n");
        } catch (NumberFormatException e) {
            feedbackOutputArea.append("Invalid input for Question ID: " + e.getMessage() + "\n");
        }
    }

    private void displayFeedbackQuestions() {
        String sql = "SELECT * FROM feedback_Questions";
        feedbackOutputArea.setText("");

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int questionId = rs.getInt("Feedback_number");
                String question = rs.getString("Question");
                String feedback = rs.getString("feedback");
                String review = rs.getString("review");
                int rating = rs.getInt("rating");
                int adminId = rs.getInt("admin_ID");
                int visitorId = rs.getInt("visitor_ID");

                feedbackOutputArea.append("Question ID: " + questionId + ", Question: " + question + ", Feedback: " + feedback +
                        ", Review: " + review + ", Rating: " + rating + ", Admin ID: " + adminId + ", Visitor ID: " + visitorId + "\n");
            }
        } catch (SQLException e) {
            feedbackOutputArea.append("Error retrieving feedback questions: " + e.getMessage() + "\n");
        }
    }

    private void clearFeedbackFields() {
        feedbackQuestionIdField.setText("");
        feedbackQuestionField.setText("");
        feedbackField.setText("");
        reviewField.setText("");
        ratingField.setText("");
        adminIdFeedbackField.setText("");
        visitorIdFeedbackField.setText("");
    }

    // Tickets Panel
    private JPanel createTicketsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Input Fields
        JPanel inputPanel = new JPanel(new GridLayout(7, 2));
        inputPanel.add(new JLabel("Ticket ID:"));
        ticketIdField = new JTextField();
        inputPanel.add(ticketIdField);

        inputPanel.add(new JLabel("Price:"));
        ticketPriceField = new JTextField();
        inputPanel.add(ticketPriceField);

        inputPanel.add(new JLabel("Request Date:"));
        requestDateField = new JTextField();
        inputPanel.add(requestDateField);

        inputPanel.add(new JLabel("Request Status:"));
        requestStatusField = new JTextField();
        inputPanel.add(requestStatusField);

        inputPanel.add(new JLabel("Show ID:"));
        ticketShowIdField = new JTextField();
        inputPanel.add(ticketShowIdField);

        inputPanel.add(new JLabel("Admin ID:"));
        adminIdTicketField = new JTextField();
        inputPanel.add(adminIdTicketField);

        inputPanel.add(new JLabel("Visitor ID:"));
        ticketVisitorIdField = new JTextField();
        inputPanel.add(ticketVisitorIdField);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton addTicketButton = new JButton("Add Ticket");
        addTicketButton.addActionListener(e -> addTicket());
        buttonPanel.add(addTicketButton);

        JButton updateTicketButton = new JButton("Update Ticket");
        updateTicketButton.addActionListener(e -> updateTicket());
        buttonPanel.add(updateTicketButton);

        JButton deleteTicketButton = new JButton("Delete Ticket");
        deleteTicketButton.addActionListener(e -> deleteTicket());
        buttonPanel.add(deleteTicketButton);

        JButton displayTicketsButton = new JButton("Display Tickets");
        displayTicketsButton.addActionListener(e -> displayTickets());
        buttonPanel.add(displayTicketsButton);

        // Output Area
        ticketOutputArea = new JTextArea();
        ticketOutputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(ticketOutputArea);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(scrollPane, BorderLayout.SOUTH);

        return panel;
    }

    private void addTicket() {
        String ticketId = ticketIdField.getText();
        String price = ticketPriceField.getText();
        String requestDate = requestDateField.getText();
        String requestStatus = requestStatusField.getText();
        String showId = ticketShowIdField.getText();
        String adminId = adminIdTicketField.getText();
        String visitorId = ticketVisitorIdField.getText();

        String sql = "INSERT INTO Tickets (Ticket_ID, price, Request_date, request_status, show_ID, admin_ID, visitor_ID) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(ticketId));
            pstmt.setDouble(2, Double.parseDouble(price));
            pstmt.setString(3, requestDate);
            pstmt.setInt(4, Integer.parseInt(requestStatus));
            pstmt.setInt(5, Integer.parseInt(showId));
            pstmt.setInt(6, Integer.parseInt(adminId));
            pstmt.setInt(7, Integer.parseInt(visitorId));
            pstmt.executeUpdate();
            ticketOutputArea.append("Ticket added successfully!\n");
            clearTicketFields();
        } catch (SQLException e) {
            ticketOutputArea.append("Error adding ticket: " + e.getMessage() + "\n");
        } catch (NumberFormatException e) {
            ticketOutputArea.append("Invalid input for Ticket ID, Show ID, Visitor ID, or Price: " + e.getMessage() + "\n");
        }
    }

    private void updateTicket() {
        String ticketId = ticketIdField.getText();
        String price = ticketPriceField.getText();
        String requestDate = requestDateField.getText();
        String requestStatus = requestStatusField.getText();
        String showId = ticketShowIdField.getText();
        String adminId = adminIdTicketField.getText();
        String visitorId = ticketVisitorIdField.getText();

        String sql = "UPDATE Tickets SET price = ?, Request_date = ?, request_status = ?, show_ID = ?, admin_ID = ?, visitor_ID = ? WHERE Ticket_ID = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, Double.parseDouble(price));
            pstmt.setString(2, requestDate);
            pstmt.setInt(3, Integer.parseInt(requestStatus));
            pstmt.setInt(4, Integer.parseInt(showId));
            pstmt.setInt(5, Integer.parseInt(adminId));
            pstmt.setInt(6, Integer.parseInt(visitorId));
            pstmt.setInt(7, Integer.parseInt(ticketId));
            pstmt.executeUpdate();
            ticketOutputArea.append("Ticket updated successfully!\n");
            clearTicketFields();
        } catch (SQLException e) {
            ticketOutputArea.append("Error updating ticket: " + e.getMessage() + "\n");
        } catch (NumberFormatException e) {
            ticketOutputArea.append("Invalid input for Ticket ID, Show ID, Visitor ID, or Price: " + e.getMessage() + "\n");
        }
    }

    private void deleteTicket() {
        String ticketId = ticketIdField.getText();

        String sql = "DELETE FROM Tickets WHERE Ticket_ID = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(ticketId));
            pstmt.executeUpdate();
            ticketOutputArea.append("Ticket deleted successfully!\n");
            clearTicketFields();
        } catch (SQLException e) {
            ticketOutputArea.append("Error deleting ticket: " + e.getMessage() + "\n");
        } catch (NumberFormatException e) {
            ticketOutputArea.append("Invalid input for Ticket ID: " + e.getMessage() + "\n");
        }
    }

    private void displayTickets() {
        String sql = "SELECT * FROM Tickets";
        ticketOutputArea.setText("");

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int ticketId = rs.getInt("Ticket_ID");
                double price = rs.getDouble("price");
                String requestDate = rs.getString("Request_date");
                int requestStatus = rs.getInt("request_status");
                int showId = rs.getInt("show_ID");
                int adminId = rs.getInt("admin_ID");
                int visitorId = rs.getInt("visitor_ID");

                ticketOutputArea.append("Ticket ID: " + ticketId + ", Price: " + price + ", Request Date: " + requestDate +
                        ", Status: " + requestStatus + ", Show ID: " + showId + ", Admin ID: " + adminId + ", Visitor ID: " + visitorId + "\n");
            }
        } catch (SQLException e) {
            ticketOutputArea.append("Error retrieving tickets: " + e.getMessage() + "\n");
        }
    }

    private void clearTicketFields() {
        ticketIdField.setText("");
        ticketPriceField.setText("");
        requestDateField.setText("");
        requestStatusField.setText("");
        ticketShowIdField.setText("");
        adminIdTicketField.setText("");
        ticketVisitorIdField.setText("");
    }

    // Staff Show Panel
    private JPanel createStaffShowPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Input Fields
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Staff ID:"));
        staffShowIdField = new JTextField();
        inputPanel.add(staffShowIdField);

        inputPanel.add(new JLabel("Show ID:"));
        staffShowShowIdField = new JTextField();
        inputPanel.add(staffShowShowIdField);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton addStaffShowButton = new JButton("Add Staff Show");
        addStaffShowButton.addActionListener(e -> addStaffShow());
        buttonPanel.add(addStaffShowButton);

        JButton updateStaffShowButton = new JButton("Update Staff Show");
        updateStaffShowButton.addActionListener(e -> updateStaffShow());
        buttonPanel.add(updateStaffShowButton);

        JButton deleteStaffShowButton = new JButton("Delete Staff Show");
        deleteStaffShowButton.addActionListener(e -> deleteStaffShow());
        buttonPanel.add(deleteStaffShowButton);

        JButton displayStaffShowButton = new JButton("Display Staff Shows");
        displayStaffShowButton.addActionListener(e -> displayStaffShows());
        buttonPanel.add(displayStaffShowButton);

        // Output Area
        staffShowOutputArea = new JTextArea();
        staffShowOutputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(staffShowOutputArea);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(scrollPane, BorderLayout.SOUTH);

        return panel;
    }

    private void addStaffShow() {
        String staffId = staffShowIdField.getText();
        String showId = staffShowShowIdField.getText();

        String sql = "INSERT INTO staff_show (Staff_ID, Show_ID) VALUES (?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(staffId));
            pstmt.setInt(2, Integer.parseInt(showId));
            pstmt.executeUpdate();
            staffShowOutputArea.append("Staff Show added successfully!\n");
            clearStaffShowFields();
        } catch (SQLException e) {
            staffShowOutputArea.append("Error adding staff show: " + e.getMessage() + "\n");
        } catch (NumberFormatException e) {
            staffShowOutputArea.append("Invalid input for Staff ID or Show ID: " + e.getMessage() + "\n");
        }
    }

    private void updateStaffShow() {
        String staffId = staffShowIdField.getText();
        String showId = staffShowShowIdField.getText();

        String sql = "UPDATE staff_show SET Show_ID = ? WHERE Staff_ID = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(showId));
            pstmt.setInt(2, Integer.parseInt(staffId));
            pstmt.executeUpdate();
            staffShowOutputArea.append("Staff Show updated successfully!\n");
            clearStaffShowFields();
        } catch (SQLException e) {
            staffShowOutputArea.append("Error updating staff show: " + e.getMessage() + "\n");
        } catch (NumberFormatException e) {
            staffShowOutputArea.append("Invalid input for Staff ID or Show ID: " + e.getMessage() + "\n");
        }
    }

    private void deleteStaffShow() {
        String staffId = staffShowIdField.getText();

        String sql = "DELETE FROM staff_show WHERE Staff_ID = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(staffId));
            pstmt.executeUpdate();
            staffShowOutputArea.append("Staff Show deleted successfully!\n");
            clearStaffShowFields();
        } catch (SQLException e) {
            staffShowOutputArea.append("Error deleting staff show: " + e.getMessage() + "\n");
        } catch (NumberFormatException e) {
            staffShowOutputArea.append("Invalid input for Staff ID: " + e.getMessage() + "\n");
        }
    }

    private void displayStaffShows() {
        String sql = "SELECT * FROM staff_show";
        staffShowOutputArea.setText("");

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int staffId = rs.getInt("Staff_ID");
                int showId = rs.getInt("Show_ID");

                staffShowOutputArea.append("Staff ID: " + staffId + ", Show ID: " + showId + "\n");
            }
        } catch (SQLException e) {
            staffShowOutputArea.append("Error retrieving staff shows: " + e.getMessage() + "\n");
        }
    }

    private void clearStaffShowFields() {
        staffShowIdField.setText("");
        staffShowShowIdField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MAIN22();
        });
    }
}