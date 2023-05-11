package cs_3560_project.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageScreen extends JFrame {
    private JPanel mainPanel;
    private JComboBox<String> dropdown;
    private JPanel buttonPanel;
    private JPanel formsPanel;
    private JButton backButton;
    private JButton enterButton;

    public ManageScreen() {
        setTitle("Management Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240)); // Light gray background color

        createDropdown();
        createButtonPanel();
        createFormsPanel();
        createButtons();

        mainPanel.add(dropdown, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(formsPanel, BorderLayout.SOUTH);

        add(mainPanel);

        pack();
        setSize(800, 600); // Set the preferred size of the JFrame
        setLocationRelativeTo(null);
    }

    private void createDropdown() {
        dropdown = new JComboBox<>(new String[] { "Student", "Book", "Documentary" });
        dropdown.setPreferredSize(new Dimension(200, 40));
        dropdown.setFont(new Font("Arial", Font.BOLD, 16));
        dropdown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) dropdown.getSelectedItem();
                updateButtonPanel(selectedItem);
            }
        });
        dropdown.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void createButtonPanel() {
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(new Color(240, 240, 240)); // Light gray background color
        buttonPanel.setPreferredSize(new Dimension(400, 100));

        updateButtonPanel(null);
    }

    private void updateButtonPanel(String selectedItem) {
        buttonPanel.removeAll();

        if (selectedItem != null) {
            String[] buttons = { "Add", "Search", "Update", "Delete" };
            for (String buttonText : buttons) {
                JButton button = createButton(buttonText);
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        updateFormsPanel(selectedItem, buttonText);
                    }
                });
                buttonPanel.add(button);
            }
        }

        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    private void createFormsPanel() {
        formsPanel = new JPanel(new GridBagLayout());
        formsPanel.setBackground(new Color(240, 240, 240)); // Light gray background color
        formsPanel.setPreferredSize(new Dimension(600, 300));
        formsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Initialize the enterButton here
        enterButton = createButton("Enter");

        updateFormsPanel(null, null);
    }

    private void updateFormsPanel(String selectedItem, String actionButton) {
    formsPanel.removeAll();

    if (selectedItem != null && actionButton != null) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 0, 5, 20);

        JLabel actionLabel = new JLabel("Action: " + actionButton);
        formsPanel.add(actionLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 0, 5, 20);

        JLabel nameLabel = new JLabel("Name:");
        formsPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 0, 5, 0);

        JTextField nameTextField = new JTextField(20);
        formsPanel.add(nameTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 0, 5, 20);

        JLabel idLabel = new JLabel("ID:");
        formsPanel.add(idLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 0, 5, 0);

        JTextField idTextField = new JTextField(20);
        formsPanel.add(idTextField, gbc);

        // Add more labels and text fields as needed based on the selected option and action button

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(20, 0, 0, 0);

        enterButton = createButton("Enter");
        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Process form data
                JOptionPane.showMessageDialog(ManageScreen.this, "Form submitted successfully!");
            }
        });
        formsPanel.add(enterButton, gbc);
    }

    formsPanel.revalidate();
    formsPanel.repaint();
}

    private void createButtons() {
        backButton = createButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // Hide the current window
                // Show the previous window (assuming it's an instance of ButtonScreen)
                ButtonScreen buttonScreen = new ButtonScreen();
                buttonScreen.setVisible(true);
            }
        });

        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Process form data
                JOptionPane.showMessageDialog(ManageScreen.this, "Form submitted successfully!");
            }
        });

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBackground(new Color(240, 240, 240)); // Light gray background color
        buttonPanel.setPreferredSize(new Dimension(800, 50));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        buttonPanel.add(backButton, BorderLayout.WEST);
        buttonPanel.add(enterButton, BorderLayout.EAST);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JButton createButton(String label) {
        JButton button = new JButton(label);
        button.setPreferredSize(new Dimension(120, 40));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(240, 240, 240)); // Light gray background color
        button.setForeground(new Color(60, 72, 107)); // Dark blue text color
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ManageScreen manageScreen = new ManageScreen();
                manageScreen.setVisible(true);
            }
        });
    }
}