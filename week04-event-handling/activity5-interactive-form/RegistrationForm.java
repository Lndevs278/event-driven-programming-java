import javax.swing.*;
import java.awt.*;

public class RegistrationForm extends JFrame {
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JRadioButton maleRadio, femaleRadio, otherRadio;
    private ButtonGroup genderGroup;
    private JCheckBox sportsCheck, musicCheck, readingCheck;
    private JComboBox<String> countryBox;
    private JTextArea commentsArea;
    private JLabel statusLabel;

    public RegistrationForm() {
        setTitle("Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // ---- Title ----
        JLabel titleLabel = new JLabel("Registration Form", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(titleLabel, BorderLayout.NORTH);

        // ---- Form Panel ----
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        // Full Name
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Full Name:"), gbc);
        gbc.gridx = 1; 
        nameField = new JTextField(25);
        formPanel.add(nameField, gbc);

        // Email
        row++;
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; 
        emailField = new JTextField(25);
        formPanel.add(emailField, gbc);

        // Password
        row++;
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1; 
        passwordField = new JPasswordField(25);
        formPanel.add(passwordField, gbc);

        // Gender
        row++;
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Gender:"), gbc);
        gbc.gridx = 1;
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        otherRadio = new JRadioButton("Other");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderGroup.add(otherRadio);
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        genderPanel.add(otherRadio);
        formPanel.add(genderPanel, gbc);

        // Interests
        row++;
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Interests:"), gbc);
        gbc.gridx = 1;
        JPanel interestPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        sportsCheck = new JCheckBox("Sports");
        musicCheck = new JCheckBox("Music");
        readingCheck = new JCheckBox("Reading");
        interestPanel.add(sportsCheck);
        interestPanel.add(musicCheck);
        interestPanel.add(readingCheck);
        formPanel.add(interestPanel, gbc);

        // Country
        row++;
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Country:"), gbc);
        gbc.gridx = 1;
        String[] countries = {"Kenya", "Tanzania", "Uganda", "Rwanda"};
        countryBox = new JComboBox<>(countries);
        formPanel.add(countryBox, gbc);

        // Comments
        row++;
        gbc.gridx = 0; gbc.gridy = row;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        formPanel.add(new JLabel("Comments:"), gbc);
        gbc.gridx = 1;
        commentsArea = new JTextArea(3, 25);
        commentsArea.setLineWrap(true);
        commentsArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(commentsArea);
        formPanel.add(scrollPane, gbc);

        add(formPanel, BorderLayout.CENTER);

        // ---- Button & Status Panel (SOUTH) ----
        JPanel southPanel = new JPanel(new BorderLayout());
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(46, 139, 87));
        submitButton.setForeground(Color.WHITE);

        JButton clearButton = new JButton("Clear");
        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(178, 34, 34));
        exitButton.setForeground(Color.WHITE);

        buttonPanel.add(submitButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);
        southPanel.add(buttonPanel, BorderLayout.CENTER);

        statusLabel = new JLabel("Status: Ready", SwingConstants.CENTER);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));
        southPanel.add(statusLabel, BorderLayout.SOUTH);

        add(southPanel, BorderLayout.SOUTH);

        // ---- EVENT HANDLERS ----

        // Submit Action
        submitButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword());

            // Validation
            if (name.isEmpty()) {
                statusLabel.setText("Error: Please enter your name");
                statusLabel.setForeground(Color.RED);
                return;
            }
            if (email.isEmpty() || !email.contains("@")) {
                statusLabel.setText("Error: Please enter a valid email");
                statusLabel.setForeground(Color.RED);
                return;
            }
            if (password.length() < 6) {
                statusLabel.setText("Error: Password must be at least 6 characters");
                statusLabel.setForeground(Color.RED);
                return;
            }

            // Gender
            String gender = "Not specified";
            if (maleRadio.isSelected()) gender = "Male";
            else if (femaleRadio.isSelected()) gender = "Female";
            else if (otherRadio.isSelected()) gender = "Other";

            // Interests
            StringBuilder interests = new StringBuilder();
            if (sportsCheck.isSelected()) interests.append("Sports ");
            if (musicCheck.isSelected()) interests.append("Music ");
            if (readingCheck.isSelected()) interests.append("Reading ");

            // Country & Comments
            String country = (String) countryBox.getSelectedItem();
            String comments = commentsArea.getText().trim();

            // Summary Message
            String message = "Registration Successful!\n\n" +
                    "Name: " + name + "\n" +
                    "Email: " + email + "\n" +
                    "Gender: " + gender + "\n" +
                    "Interests: " + (interests.length() > 0 ? interests.toString() : "None") + "\n" +
                    "Country: " + country + "\n" +
                    "Comments: " + (comments.isEmpty() ? "None" : comments);

            JOptionPane.showMessageDialog(this, message, "Registration Complete", JOptionPane.INFORMATION_MESSAGE);

            statusLabel.setText("Registration submitted successfully!");
            statusLabel.setForeground(new Color(0, 128, 0));
        });

        // Clear Action
        clearButton.addActionListener(e -> {
            nameField.setText("");
            emailField.setText("");
            passwordField.setText("");
            genderGroup.clearSelection();
            sportsCheck.setSelected(false);
            musicCheck.setSelected(false);
            readingCheck.setSelected(false);
            countryBox.setSelectedIndex(0);
            commentsArea.setText("");
            statusLabel.setText("Status: Form cleared");
            statusLabel.setForeground(Color.BLACK);
        });

        // Exit Action
        exitButton.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to exit?",
                    "Confirm Exit",
                    JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistrationForm().setVisible(true));
    }
}