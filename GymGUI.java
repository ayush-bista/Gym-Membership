import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.border.TitledBorder;
import java.io.*;
import java.nio.file.*;
import javax.swing.table.*;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * GymGUI class provides a graphical user interface for a gym management system.
 * Handles member registration, management, and various membership operations.
 */
public class GymGUI extends JFrame {
    // Collection to store all gym members
    private ArrayList<GymMember> members;

    // Text Fields for personal information input
    private JTextField idField, nameField, locationField, phoneField, emailField;
    private JTextField referralField, paidAmountField, removalReasonField, trainerField;

    // Read-only Fields for displaying calculated values
    private JTextField planPriceField, premiumChargeField, discountField;

    // Date Combo Boxes for DOB and membership start date
    private JComboBox<String> dobYearComboBox, dobMonthComboBox, dobDayComboBox;
    private JComboBox<String> msYearComboBox, msMonthComboBox, msDayComboBox;

    // Radio Buttons for gender selection
    private JRadioButton maleButton, femaleButton;

    // Combo Box for membership plan selection
    private JComboBox<String> planComboBox;


    //Constructor initializes the main application window and sets up the UI.
    public GymGUI() {
        members = new ArrayList<>();
        setTitle("Strength Gym");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setupUI();
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Sets up date combo boxes with years, months, and days.
     * Initializes both DOB and membership start date selectors.
     */
    private void setupDateComboBoxes() {
        // Years from 1980 to 2030
        String[] years = new String[51];
        for (int i = 0; i < 51; i++) {
            years[i] = String.valueOf(1980 + i);
        }

        // Months
        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};

        // Days (1-31)
        String[] days = new String[31];
        for (int i = 0; i < 31; i++) {
            days[i] = String.valueOf(i + 1);
        }

        // Initialize combo boxes
        dobYearComboBox = new JComboBox<>(years);
        dobMonthComboBox = new JComboBox<>(months);
        dobDayComboBox = new JComboBox<>(days);

        msYearComboBox = new JComboBox<>(years);
        msMonthComboBox = new JComboBox<>(months);
        msDayComboBox = new JComboBox<>(days);
    }

    private void setupUI() {
        setLayout(new BorderLayout(5, 10));
        setupDateComboBoxes();
        
        // Main container
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        // Personal Information Panel
        JPanel personalInfoPanel = new JPanel(new GridLayout(0, 2, 8, 8));
        personalInfoPanel.setPreferredSize(new Dimension(800, 300));
        TitledBorder personalBorder = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(75, 0, 130), 2, true),
            "Personal Details"
        );
        personalBorder.setTitleFont(new Font("Arial", Font.BOLD, 14));
        personalInfoPanel.setBorder(personalBorder);

        // Personal Information Panel
        personalInfoPanel.add(new JLabel("ID:"));
        JPanel idPanel = new JPanel(new BorderLayout());
        idField = new JTextField();
        idField.setText("Enter ID");
        idField.setForeground(Color.GRAY);
        idField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (idField.getText().equals("Enter ID")) {
                    idField.setText("");
                    idField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (idField.getText().isEmpty()) {
                    idField.setText("Enter ID");
                    idField.setForeground(Color.GRAY);
                }
            }
        });
        personalInfoPanel.add(idField);

        personalInfoPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        nameField.setText("Enter Your Full Name");
        nameField.setForeground(Color.GRAY);
        nameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (nameField.getText().equals("Enter Your Full Name")) {
                    nameField.setText("");
                    nameField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (nameField.getText().isEmpty()) {
                    nameField.setText("Enter Your Full Name");
                    nameField.setForeground(Color.GRAY);
                }
            }
        });
        personalInfoPanel.add(nameField);

        personalInfoPanel.add(new JLabel("Location:"));
        locationField = new JTextField();
        personalInfoPanel.add(locationField);

        personalInfoPanel.add(new JLabel("Phone:"));
        phoneField = new JTextField();
        personalInfoPanel.add(phoneField);

        personalInfoPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        personalInfoPanel.add(emailField);

        // Gender Panel
        personalInfoPanel.add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ButtonGroup genderGroup = new ButtonGroup();
        maleButton = new JRadioButton("Male");
        femaleButton = new JRadioButton("Female");
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        personalInfoPanel.add(genderPanel);

        // Setup Date Combo Boxes
        setupDateComboBoxes();

        // DOB Panel
        personalInfoPanel.add(new JLabel("Date of Birth:"));
        JPanel dobPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dobPanel.add(dobDayComboBox);
        dobPanel.add(dobMonthComboBox);
        dobPanel.add(dobYearComboBox);
        personalInfoPanel.add(dobPanel);

        // Membership Start Date Panel
        personalInfoPanel.add(new JLabel("Membership Start Date:"));
        JPanel msPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        msPanel.add(msDayComboBox);
        msPanel.add(msMonthComboBox);
        msPanel.add(msYearComboBox);
        personalInfoPanel.add(msPanel);

        JPanel membershipPanel = new JPanel(new GridLayout(0, 2, 8, 8));
        membershipPanel.setPreferredSize(new Dimension(800, 300));
        TitledBorder membershipBorder = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(75, 0, 130), 2, true),
            "Membership Details"
        );
        membershipBorder.setTitleFont(new Font("Arial", Font.BOLD, 14));  // Reduced font size
        membershipPanel.setBorder(membershipBorder);

        membershipPanel.add(new JLabel("Referral Source:"));
        referralField = new JTextField();
        membershipPanel.add(referralField);

        membershipPanel.add(new JLabel("Paid Amount:"));
        paidAmountField = new JTextField();
        membershipPanel.add(paidAmountField);

        membershipPanel.add(new JLabel("Removal Reason:"));
        removalReasonField = new JTextField();
        membershipPanel.add(removalReasonField);

        membershipPanel.add(new JLabel("Trainer's Name:"));
        trainerField = new JTextField();
        membershipPanel.add(trainerField);

        
        membershipPanel.add(new JLabel("Discount Amount:"));
        discountField = new JTextField("0");
        discountField.setEditable(false);
        membershipPanel.add(discountField);

        membershipPanel.add(new JLabel("Plan:"));
        planComboBox = new JComboBox<>(new String[]{"Basic", "Standard", "Deluxe"});
        membershipPanel.add(planComboBox);

        membershipPanel.add(new JLabel("Regular Plan Price:"));
        planPriceField = new JTextField("6500");
        planPriceField.setEditable(false);
        membershipPanel.add(planPriceField);

        membershipPanel.add(new JLabel("Premium Plan Price:"));
        premiumChargeField = new JTextField("50000");
        premiumChargeField.setEditable(false);
        membershipPanel.add(premiumChargeField);

        // Button Panel with reduced size
        JPanel buttonPanel = new JPanel(new GridLayout(0, 2, 8, 8));
        buttonPanel.setPreferredSize(new Dimension(800, 250));
        TitledBorder actionBorder = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(75, 0, 130), 2, true),
            "Actions"
        );
        actionBorder.setTitleFont(new Font("Arial", Font.BOLD, 14));
        buttonPanel.setBorder(actionBorder);


        Font labelFont = new Font("Arial", Font.PLAIN, 12);
        Component[] components = personalInfoPanel.getComponents();
        for (Component comp : components) {
            if (comp instanceof JLabel) {
                ((JLabel) comp).setFont(labelFont);
            }
        }
        components = membershipPanel.getComponents();
        for (Component comp : components) {
            if (comp instanceof JLabel) {
                ((JLabel) comp).setFont(labelFont);
            }
        }

       

        // Adjust text field and combo box sizes
        Dimension fieldSize = new Dimension(150, 25);
        Dimension comboBoxSize = new Dimension(60, 25);
        Dimension monthComboBoxSize = new Dimension(80, 25);

        JButton addRegularButton = new JButton("Add as Regular Member");
        JButton addPremiumButton = new JButton("Add as Premium Member");
        JButton activateButton = new JButton("Activate Membership");
        JButton deactivateButton = new JButton("Deactivate Membership");
        JButton markAttendanceButton = new JButton("Mark Attendance");
        JButton upgradePlanButton = new JButton("Upgrade Plan");
        JButton calculateDiscountButton = new JButton("Calculate Discount");
        JButton revertRegularButton = new JButton("Revert Regular Member");
        JButton revertPremiumButton = new JButton("Revert Premium Member");
        JButton payDueButton = new JButton("Pay Due Amount");
        JButton displayButton = new JButton("Display all Members");
        JButton clearButton = new JButton("Clear");
        JButton saveButton = new JButton("Save to File");
        JButton readButton = new JButton("Read from File");

        buttonPanel.add(addRegularButton);
        buttonPanel.add(addPremiumButton);
        buttonPanel.add(activateButton);
        buttonPanel.add(deactivateButton);
        buttonPanel.add(markAttendanceButton);
        buttonPanel.add(upgradePlanButton);
        buttonPanel.add(calculateDiscountButton);
        buttonPanel.add(revertRegularButton);
        buttonPanel.add(revertPremiumButton);
        buttonPanel.add(payDueButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(readButton);

        // Add all panels to main panel
        mainPanel.add(personalInfoPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 12)));
        mainPanel.add(membershipPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 12)));
        mainPanel.add(buttonPanel);

        // Add main panel to frame
        add(new JScrollPane(mainPanel), BorderLayout.CENTER);

        // Set frame size
        setPreferredSize(new Dimension(1200, 999));

        // Add action listeners
        addRegularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRegularMember();
            }
        });
        addPremiumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPremiumMember();
            }
        });
        activateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                activateMembership();
            }
        });
        deactivateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deactivateMembership();
            }
        });
        markAttendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markAttendance();
            }
        });
        upgradePlanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                upgradePlan();
            }
        });
        calculateDiscountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateDiscount();
            }
        });
        revertRegularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                revertRegularMember();
            }
        });
        revertPremiumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                revertPremiumMember();
            }
        });
        payDueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                payDueAmount();
            }
        });
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayMembers();
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToFile();
            }
        });
        readButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readFromFile();
            }
        });
    }

    /**
     * Saves all member details to a text file.
     * Creates a formatted text file with member information.
     */
    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("MemberDetails.txt"))) {
            // Write header
            writer.println("ID\tName\tLocation\tPhone\tEmail\tMembership Start Date\tPlan\tPrice\tAttendance\tLoyalty Points\tActive Status\tFull Payment\tDiscount Amount\tTotal Amount Paid");
            writer.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            
            // Write member details
            for (GymMember member : members) {
                StringBuilder line = new StringBuilder();
                line.append(member.getId()).append("\t")
                    .append(member.getName()).append("\t")
                    .append(member.getLocation()).append("\t")
                    .append(member.getPhone()).append("\t")
                    .append(member.getEmail()).append("\t")
                    .append(member.getMembershipStartDate()).append("\t");

                if (member instanceof RegularMember) {
                    RegularMember rm = (RegularMember) member;
                    line.append(rm.getPlan()).append("\t")
                        .append(rm.getPrice()).append("\t");
                } else {
                    PremiumMember pm = (PremiumMember) member;
                    line.append("Premium\t")
                        .append(pm.getPremiumCharge()).append("\t");
                }

                line.append(member.getAttendance()).append("\t")
                    .append(member.getLoyaltyPoints()).append("\t")
                    .append(member.getActiveStatus()).append("\t");

                if (member instanceof PremiumMember) {
                    PremiumMember pm = (PremiumMember) member;
                    line.append(pm.getIsFullPayment()).append("\t")
                        .append(pm.getDiscountAmount()).append("\t")
                        .append(pm.getPaidAmount());
                } else {
                    line.append("NA\tNA\tNA");
                }

                writer.println(line.toString());
            }
            JOptionPane.showMessageDialog(this, "Member details saved successfully to MemberDetails.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving to file: " + e.getMessage());
        }
    }

    /**
     * Reads member details from the text file and displays them in a new frame.
     * Creates a table view of the member information.
     */
    private void readFromFile() {
        JFrame readFrame = new JFrame("Member Details from File");
        readFrame.setSize(1000, 600);
        readFrame.setLocationRelativeTo(this);

        try {
            List<String> lines = Files.readAllLines(Paths.get("MemberDetails.txt"));
            if (lines.size() < 2) {
                JOptionPane.showMessageDialog(this, "No data found in file.");
                return;
            }

            // Create table model with the header from file
            String[] columnNames = lines.get(0).split("\t");
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            // Skip header and separator line, add data rows
            for (int i = 2; i < lines.size(); i++) {
                model.addRow(lines.get(i).split("\t"));
            }

            // Create and configure table
            JTable table = new JTable(model);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            table.setFont(new Font("Arial", Font.PLAIN, 12));
            
            // Set column widths
            for (int i = 0; i < table.getColumnCount(); i++) {
                TableColumn column = table.getColumnModel().getColumn(i);
                column.setPreferredWidth(100);
            }

            // Add table to scroll pane
            JScrollPane scrollPane = new JScrollPane(table);
            readFrame.add(scrollPane);
            readFrame.setVisible(true);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading from file: " + e.getMessage());
        }
    }

    private void addRegularMember() {
        try {
            int id = Integer.parseInt(idField.getText());
            if (isIdDuplicate(id)) {
                JOptionPane.showMessageDialog(this, "This ID already exists");
                return;
            }

            String name = nameField.getText();
            String location = locationField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String gender = maleButton.isSelected() ? "Male" : "Female";
            String dob = dobDayComboBox.getSelectedItem() + "-" + 
                dobMonthComboBox.getSelectedItem() + "-" + 
                dobYearComboBox.getSelectedItem();
            String startDate = msDayComboBox.getSelectedItem() + "-" + 
                msMonthComboBox.getSelectedItem() + "-" + 
                msYearComboBox.getSelectedItem();
            String referral = referralField.getText();

            if (name.isEmpty() || location.isEmpty() || phone.isEmpty() || 
            email.isEmpty() || (!maleButton.isSelected() && !femaleButton.isSelected())) {
                JOptionPane.showMessageDialog(this, "Please fill all required fields");
                return;
            }

            RegularMember member = new RegularMember(id, name, location, phone, email, 
                    gender, dob, startDate, referral);
            members.add(member);
            JOptionPane.showMessageDialog(this, "New Regular Member has been added successfully.");
            clearFields();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter the valid Member ID.");
        }
    }

    private void addPremiumMember() {
        try {
            int id = Integer.parseInt(idField.getText());
            if (isIdDuplicate(id)) {
                JOptionPane.showMessageDialog(this, "This Member ID already exists");
                return;
            }

            String name = nameField.getText();
            String location = locationField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String gender = maleButton.isSelected() ? "Male" : "Female";
            String dob = dobDayComboBox.getSelectedItem() + "-" + 
                dobMonthComboBox.getSelectedItem() + "-" + 
                dobYearComboBox.getSelectedItem();
            String startDate = msDayComboBox.getSelectedItem() + "-" + 
                msMonthComboBox.getSelectedItem() + "-" + 
                msYearComboBox.getSelectedItem();
            String trainer = trainerField.getText();

            if (name.isEmpty() || location.isEmpty() || phone.isEmpty() || 
            email.isEmpty() || (!maleButton.isSelected() && !femaleButton.isSelected())) {
                JOptionPane.showMessageDialog(this, "Please fill all required fields");
                return;
            }

            PremiumMember member = new PremiumMember(id, name, location, phone, email, 
                    gender, dob, startDate, trainer);
            members.add(member);
            JOptionPane.showMessageDialog(this, "New Premium Member has been added successfully.");
            clearFields();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID.");
        }
    }

    private boolean isIdDuplicate(int id) {
        for (GymMember member : members) {
            if (member.getId() == id) {
                return true;
            }
        }
        return false;
    }


    private void activateMembership() {
        try {
            int id = Integer.parseInt(idField.getText());
            for (GymMember member : members) {
                if (member.getId() == id) {
                    member.activateMembership();
                    JOptionPane.showMessageDialog(this, "Membership activated successfully.");
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Member not found.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID.");
        }
    }

    private void deactivateMembership() {
        try {
            int id = Integer.parseInt(idField.getText());
            for (GymMember member : members) {
                if (member.getId() == id) {
                    member.deactivateMembership();
                    JOptionPane.showMessageDialog(this, "Membership deactivated successfully.");
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Member not found.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID.");
        }
    }

    private void markAttendance() {
        try {
            int id = Integer.parseInt(idField.getText());
            for (GymMember member : members) {
                if (member.getId() == id) {
                    if (member.getActiveStatus()) {
                        member.markAttendance();
                        JOptionPane.showMessageDialog(this, "Attendance marked successfully.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Membership is not activate.");
                    }
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Member not found.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID.");
        }
    }

    private void upgradePlan() {
        try {
            int id = Integer.parseInt(idField.getText());
            String selectedPlan = (String) planComboBox.getSelectedItem();

            for (GymMember member : members) {
                if (member.getId() == id) {
                    if (member instanceof RegularMember) {
                        RegularMember regularMember = (RegularMember) member;
                        if (member.getActiveStatus()) {
                            String result = regularMember.upgradePlan(selectedPlan);
                            JOptionPane.showMessageDialog(this, result);
                        } else {
                            JOptionPane.showMessageDialog(this, "Membership is not activated.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Not a Regular Member.");
                    }
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Member not found.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID.");
        }
    }

    private void calculateDiscount() {
        try {
            int id = Integer.parseInt(idField.getText());
            for (GymMember member : members) {
                if (member.getId() == id) {
                    if (member instanceof PremiumMember) {
                        PremiumMember premiumMember = (PremiumMember) member;
                        premiumMember.calculateDiscount();
                        discountField.setText(String.valueOf(premiumMember.getDiscountAmount()));
                        JOptionPane.showMessageDialog(this, "Discount calculated.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Not a Premium Member.");
                    }
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Member not found.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID.");
        }
    }

    private void revertRegularMember() {
        try {
            int id = Integer.parseInt(idField.getText());
            String reason = removalReasonField.getText();

            if (reason.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a removal reason.");
                return;
            }

            for (GymMember member : members) {
                if (member.getId() == id) {
                    if (member instanceof RegularMember) {
                        RegularMember regularMember = (RegularMember) member;
                        regularMember.revertRegularMember(reason);
                        JOptionPane.showMessageDialog(this, "Regular Member reverted successfully.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Not a Regular Member.");
                    }
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Member not found.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID.");
        }
    }

    private void revertPremiumMember() {
        try {
            int id = Integer.parseInt(idField.getText());
            for (GymMember member : members) {
                if (member.getId() == id) {
                    if (member instanceof PremiumMember) {
                        PremiumMember premiumMember = (PremiumMember) member;
                        premiumMember.revertPremiumMember();
                        JOptionPane.showMessageDialog(this, "Premium Member reverted successfully.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Not a Premium Member.");
                    }
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Member not found.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID.");
        }
    }

    private void payDueAmount() {
        try {
            int id = Integer.parseInt(idField.getText());
            double amount = Double.parseDouble(paidAmountField.getText());

            for (GymMember member : members) {
                if (member.getId() == id) {
                    if (member instanceof PremiumMember) {
                        PremiumMember premiumMember = (PremiumMember) member;
                        String result = premiumMember.payDueAmount(amount);
                        JOptionPane.showMessageDialog(this, result);
                    } else {
                        JOptionPane.showMessageDialog(this, "Not a Premium Member");
                    }
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Member not found");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers");
        }
    }

    private void displayMembers() {
        JFrame displayFrame = new JFrame("Member Details");
        JTextArea textArea = new JTextArea(18, 40);
        textArea.setEditable(false);

        if (members.isEmpty()) {
            textArea.setText("Members not found");
        } else {
            StringBuilder displayText = new StringBuilder();
            for (GymMember member : members) {
                if (member instanceof RegularMember) {
                    displayText.append("[ Regular Member ]\n");
                } else if (member instanceof PremiumMember) {
                    displayText.append("[ Premium Member ]\n");
                }

                displayText.append("Member's ID: ").append(member.getId()).append("\n");
                displayText.append("Name: ").append(member.getName()).append("\n");
                displayText.append("Location: ").append(member.getLocation()).append("\n");
                displayText.append("Phone: ").append(member.getPhone()).append("\n");
                displayText.append("Email: ").append(member.getEmail()).append("\n");
                displayText.append("Gender: ").append(member.getGender()).append("\n");
                displayText.append("DOB: ").append(member.getDOB()).append("\n");
                displayText.append("Membership Start Date: ").append(member.getMembershipStartDate()).append("\n");
                displayText.append("Attendance: ").append(member.getAttendance()).append("\n");
                displayText.append("Loyalty Points: ").append(member.getLoyaltyPoints()).append("\n");
                displayText.append("Active Status: ").append(member.getActiveStatus()).append("\n");

                if (member instanceof RegularMember) {
                    RegularMember regMember = (RegularMember) member;
                    displayText.append("Plan: ").append(regMember.getPlan()).append("\n");
                    displayText.append("Price: ").append(regMember.getPrice()).append("\n");
                    if (!regMember.getRemovalReason().isEmpty()) {
                        displayText.append("Removal Reason: ").append(regMember.getRemovalReason()).append("\n");
                    }
                } else if (member instanceof PremiumMember) {
                    PremiumMember premMember = (PremiumMember) member;
                    displayText.append("Personal Trainer: ").append(premMember.getPersonalTrainer()).append("\n");
                    displayText.append("Paid Amount: ").append(premMember.getPaidAmount()).append("\n");
                    displayText.append("Payment Status: ").append(premMember.getIsFullPayment() ? "Complete" : "Incomplete").append("\n");
                    double remainingAmount = premMember.getPremiumCharge() - premMember.getPaidAmount();
                    displayText.append("Remaining Amount: ").append(remainingAmount).append("\n");
                    if (premMember.getIsFullPayment()) {
                        displayText.append("Discount Amount: ").append(premMember.getDiscountAmount()).append("\n");
                    }
                }

                displayText.append("\n\n\n");
            }
            textArea.setText(displayText.toString());
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        displayFrame.add(scrollPane);
        displayFrame.pack();
        displayFrame.setLocationRelativeTo(this);
        displayFrame.setVisible(true);
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        locationField.setText("");
        phoneField.setText("");
        emailField.setText("");
        referralField.setText("");
        paidAmountField.setText("");
        removalReasonField.setText("");
        trainerField.setText("");
        planComboBox.setSelectedIndex(0);
        dobDayComboBox.setSelectedIndex(0);
        dobMonthComboBox.setSelectedIndex(0);
        dobYearComboBox.setSelectedIndex(0);
        msDayComboBox.setSelectedIndex(0);
        msMonthComboBox.setSelectedIndex(0);
        msYearComboBox.setSelectedIndex(0);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderGroup.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GymGUI().setVisible(true);
            }
        });
    }
}