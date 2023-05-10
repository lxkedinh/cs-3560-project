package cs_3560_project.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageScreen extends JFrame {

    private JComboBox<String> optionComboBox;
    private JPanel buttonPanel;
    private JLabel infoLabel;
    private JTextField textField;
    private JButton enterButton;
    private JButton backButton;
    private Container textFieldPanel;

    public ManageScreen() {
        setTitle("Management Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        createOptionComboBox();
        createButtonPanel();
        createInfoLabel();
        createTextField();
        createEnterButton();
        createBackButton();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(optionComboBox);
        mainPanel.add(buttonPanel);
        mainPanel.add(infoLabel);
        mainPanel.add(textField);
        mainPanel.add(enterButton);
        mainPanel.add(backButton);

        add(mainPanel, BorderLayout.CENTER);

        pack();
        setSize(800, 600); // Set the preferred size of the JFrame
        setLocationRelativeTo(null);
    }

    private void createOptionComboBox() {
        String[] options = { "Student", "Book", "Documentary" };
        optionComboBox = new JComboBox<>(options);
        optionComboBox.setSelectedIndex(-1); // No initial selection

        optionComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateButtonPanel();
            }
        });
    }

    private void createButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 20));

        JButton addButton = createButton("Add");
        JButton searchButton = createButton("Search");
        JButton updateButton = createButton("Update");
        JButton deleteButton = createButton("Delete");

        buttonPanel.add(addButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddFields();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSearchFields();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showUpdateFields();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDeleteFields();
            }
        });

        updateButtonPanel(); // Set initial buttons based on selected option
    }

    private void showAddFields() {
        // Clear the panel
        textFieldPanel.removeAll();

        // Add the required text fields for the "Add" operation
        textFieldPanel.add(new JLabel("Field 1:"));
        textFieldPanel.add(new JTextField(10));
        textFieldPanel.add(new JLabel("Field 2:"));
        textFieldPanel.add(new JTextField(10));
        // Add more text fields as needed

        // Repaint the panel
        textFieldPanel.revalidate();
        textFieldPanel.repaint();
    }

    private void showSearchFields() {
        // Clear the panel
        textFieldPanel.removeAll();

        // Add the required text fields for the "Search" operation
        textFieldPanel.add(new JLabel("Field 1:"));
        textFieldPanel.add(new JTextField(10));
        textFieldPanel.add(new JLabel("Field 2:"));
        textFieldPanel.add(new JTextField(10));
        // Add more text fields as needed

        // Repaint the panel
        textFieldPanel.revalidate();
        textFieldPanel.repaint();
    }

    private void showUpdateFields() {
        // Clear the panel
        textFieldPanel.removeAll();

        // Add the required text fields for the "Update" operation
        textFieldPanel.add(new JLabel("Field 1:"));
        textFieldPanel.add(new JTextField(10));
        textFieldPanel.add(new JLabel("Field 2:"));
        textFieldPanel.add(new JTextField(10));
        // Add more text fields as needed

        // Repaint the panel
        textFieldPanel.revalidate();
        textFieldPanel.repaint();
    }

    private void showDeleteFields() {
        // Clear the panel
        textFieldPanel.removeAll();

        // Add the required text fields for the "Delete" operation
        textFieldPanel.add(new JLabel("Field 1:"));
        textFieldPanel.add(new JTextField(10));
        textFieldPanel.add(new JLabel("Field 2:"));
        textFieldPanel.add(new JTextField(10));
        // Add more text fields as needed

        // Repaint the panel
        textFieldPanel.revalidate();
        textFieldPanel.repaint();
    }

    private void updateButtonPanel() {
        buttonPanel.removeAll();

        String selectedOption = (String) optionComboBox.getSelectedItem();
        if (selectedOption != null) {
            buttonPanel.add(createButton("Add"));
            buttonPanel.add(createButton("Search"));
            buttonPanel.add(createButton("Update"));
            buttonPanel.add(createButton("Delete"));
        }

        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    private JButton createButton(String label) {
        JButton button = new JButton(label);
        button.setPreferredSize(new Dimension(120, 40));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(240, 240, 240)); // Light gray background color
        button.setForeground(new Color(60, 72, 107)); // Dark blue text color

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleButtonClick(label);
            }
        });

        return button;
    }

    private void handleButtonClick(String buttonLabel) {
        String selectedOption = (String) optionComboBox.getSelectedItem();
        if (selectedOption != null) {
            // Handle button click based on selected option and button label
            String message = "Selected Option: " + selectedOption + ", Button: " + buttonLabel;
            JOptionPane.showMessageDialog(this, message);
        }
    }

    private void createInfoLabel() {
        infoLabel = new JLabel();
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoLabel.setText("Enter Details:");
        infoLabel.setFont(new Font("Arial", Font.BOLD, 16));
    }

    private void createTextField() {
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 30));
    }

    private void createEnterButton() {
        enterButton = new JButton("Enter");
        enterButton.setPreferredSize(new Dimension(100, 30));
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleEnterButtonClick();
            }
        });
    }

    private void handleEnterButtonClick() {
        String selectedOption = (String) optionComboBox.getSelectedItem();
        String enteredText = textField.getText();

        if (selectedOption != null && !enteredText.isEmpty()) {
            // Process the entered text based on the selected option
            String message = "Selected Option: " + selectedOption + ", Entered Text: " + enteredText;
            JOptionPane.showMessageDialog(this, message);
        }
    }

    private void createBackButton() {
        backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100, 30));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openMainScreen();
            }
        });
    }

    private void openMainScreen() {
        ButtonScreen mainScreen = new ButtonScreen();
        mainScreen.setVisible(true);
        dispose(); // Close the management screen
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ManagementScreen managementScreen = new ManagementScreen();
            managementScreen.setVisible(true);
        });
    }
}

// package cs_3560_project.app;

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// public class ManageScreen extends JFrame {
//     private JPanel buttonPanel;
//     private JButton loansButton;
//     private JButton inventoryButton;
//     private JButton managementButton;

//     private JPanel mainPanel;
//     private JComboBox<String> optionComboBox;
//     private JPanel textFieldPanel;
//     private JButton enterButton;
//     private JButton backButton;

//     public ManageScreen() {
//         setTitle("Management Screen");
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setPreferredSize(new Dimension(800, 600));
//         initComponents();
//         pack();
//         setLocationRelativeTo(null);
//         setVisible(true);
//     }

//     private void initComponents() {
//         createButtonPanel();
//         createMainPanel();

//         setLayout(new BorderLayout());
//         add(buttonPanel, BorderLayout.NORTH);
//         add(mainPanel, BorderLayout.CENTER);
//     }

//     private void createButtonPanel() {
//         buttonPanel = new JPanel();
//         buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 60));

//         loansButton = createButton("Loans");
//         inventoryButton = createButton("Inventory");
//         managementButton = createButton("Management");

//         buttonPanel.add(loansButton);
//         buttonPanel.add(inventoryButton);
//         buttonPanel.add(managementButton);

//         loansButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 openLoansScreen();
//             }
//         });

//         inventoryButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 openInventoryScreen();
//             }
//         });

//         managementButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 openManagementScreen();
//             }
//         });
//     }

//     private JButton createButton(String label) {
//         JButton button = new JButton(label);
//         button.setPreferredSize(new Dimension(120, 40));
//         button.setFont(new Font("Arial", Font.BOLD, 16));
//         button.setBackground(new Color(240, 240, 240)); // Light gray background color
//         button.setForeground(new Color(60, 72, 107)); // Dark blue text color
//         return button;
//     }

//     private void createMainPanel() {
//         mainPanel = new JPanel();
//         mainPanel.setLayout(new BorderLayout());

//         optionComboBox = new JComboBox<>(new String[]{"Student", "Book", "Documentary"});
//         optionComboBox.setSelectedIndex(-1);
//         optionComboBox.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 updateButtonPanel();
//             }
//         });

//         textFieldPanel = new JPanel();
//         textFieldPanel.setLayout(new GridLayout(0, 2, 10, 10));

//         enterButton = new JButton("Enter");
//         enterButton.setPreferredSize(new Dimension(100, 30));
//         enterButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 handleEnterButtonClick();
//             }
//         });

//         backButton = new JButton("Back");
//         backButton.setPreferredSize(new Dimension(100, 30));
//         backButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 openMainScreen();
//             }
//         });

//         JPanel buttonPanel = new JPanel();
//         buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
//         buttonPanel.add(enterButton);
//         buttonPanel.add(backButton);

//         mainPanel.add(optionComboBox, BorderLayout.NORTH);
//         mainPanel.add(textFieldPanel, BorderLayout.CENTER);
//         mainPanel.add(buttonPanel, BorderLayout.SOUTH);
//     }

// private void updateButtonPanel() {
//         textFieldPanel.removeAll();
//         textFieldPanel.revalidate();
//         textFieldPanel.repaint();

//         String selectedOption = (String) optionComboBox.getSelectedItem();

//         if (selectedOption == null) {
//             return;
//         }

//         if (selectedOption.equals("Student")) {
//             addTextField("Student ID:");
//             addTextField("Name:");
//             addTextField("Age:");
//             addTextField("Address:");
//             // Add more text fields as needed
//         } else if (selectedOption.equals("Book")) {
//             addTextField("ISBN:");
//             addTextField("Title:");
//             addTextField("Author:");
//             addTextField("Publisher:");
//             // Add more text fields as needed
//         } else if (selectedOption.equals("Documentary")) {
//             addTextField("Title:");
//             addTextField("Director:");
//             addTextField("Release Year:");
//             addTextField("Genre:");
//             // Add more text fields as needed
//         }

//         textFieldPanel.revalidate();
//         textFieldPanel.repaint();
//     }

//     private void addTextField(String label) {
//         JLabel fieldLabel = new JLabel(label);
//         JTextField textField = new JTextField();
//         textField.setPreferredSize(new Dimension(200, 25));
//         textFieldPanel.add(fieldLabel);
//         textFieldPanel.add(textField);
//     }

//     private void handleEnterButtonClick() {
//         String selectedOption = (String) optionComboBox.getSelectedItem();
//         if (selectedOption == null) {
//             return;
//         }

//         // Perform actions based on selected option and entered data
//         // Example:
//         if (selectedOption.equals("Student")) {
//             String studentId = getTextValue("Student ID:");
//             String name = getTextValue("Name:");
//             String age = getTextValue("Age:");
//             String address = getTextValue("Address:");
//             // Process the entered data
//         } else if (selectedOption.equals("Book")) {
//             String isbn = getTextValue("ISBN:");
//             String title = getTextValue("Title:");
//             String author = getTextValue("Author:");
//             String publisher = getTextValue("Publisher:");
//             // Process the entered data
//         } else if (selectedOption.equals("Documentary")) {
//             String title = getTextValue("Title:");
//             String director = getTextValue("Director:");
//             String releaseYear = getTextValue("Release Year:");
//             String genre = getTextValue("Genre:");
//             // Process the entered data
//         }

//         // Clear text fields
//         clearTextFields();
//     }

//     private String getTextValue(String label) {
//         Component[] components = textFieldPanel.getComponents();
//         for (int i = 0; i < components.length; i += 2) {
//             if (components[i] instanceof JLabel && ((JLabel) components[i]).getText().equals(label)) {
//                 JTextField textField = (JTextField) components[i + 1];
//                 return textField.getText();
//             }
//         }
//         return "";
//     }

//     private void clearTextFields() {
//         Component[] components = textFieldPanel.getComponents();
//         for (Component component : components) {
//             if (component instanceof JTextField) {
//                 JTextField textField = (JTextField) component;
//                 textField.setText("");
//             }
//         }
//     }

//     private void openMainScreen() {
//         // Code to open the main screen or go back to the previous screen
//     }

//     private void openLoansScreen() {
//         // Code to open the Loans screen
//     }

//     private void openInventoryScreen() {
//         // Code to open the Inventory screen
//     }

//     private void openManagementScreen() {
//         // Code to open the Management screen
//     }

//     public static void main(String[] args) {
//         SwingUtilities.invokeLater(new Runnable() {
//             @Override
//             public void run() {
//                 new ManagementScreen();
//             }
//         });
//     }
// }