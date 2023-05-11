package cs_3560_project.app;

import javax.swing.*;

import cs_3560_project.server.controllers.StudentController;
import cs_3560_project.server.model.Student;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

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
        setSize(800, 700); // Set the preferred size of the JFrame
        setLocationRelativeTo(null);
    }

    private void createDropdown() {
        DefaultListCellRenderer renderer = new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (isSelected) {
                    component.setBackground(new Color(60, 72, 107)); // Set your desired highlight color
                    component.setForeground(new Color(240, 240, 240)); // Set the text color
                } else {
                    component.setBackground(list.getBackground());
                    component.setForeground(list.getForeground());
                }

                return component;
            }
        };

        dropdown = new JComboBox<>(new String[] { "Select Role", "Student", "Author", "Director" });
        dropdown.setRenderer(renderer);
        dropdown.setPreferredSize(new Dimension(50, 50));
        dropdown.setFont(new Font("Arial", Font.BOLD, 16));
        dropdown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!dropdown.getSelectedItem().equals("Select Role"))
                {
                    String selectedItem = (String) dropdown.getSelectedItem();
                    updateButtonPanel(selectedItem);
                }
            }
        });
        dropdown.setAlignmentX(Component.CENTER_ALIGNMENT);
        dropdown.setSelectedItem("Select Role");
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
        formsPanel.setPreferredSize(new Dimension(800, 500));
        formsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Initialize the enterButton here
        enterButton = createButton("Enter");

        updateFormsPanel(null, null);
    }

    private void updateFormsPanel(String selectedItem, String actionButton) {
        formsPanel.removeAll();
        if (selectedItem != null && actionButton != null) {
            LinkedList<JTextField> fields = new LinkedList<> ();

            // Implement Forms
            if (actionButton.equals("Add"))
            {
                if (selectedItem.equals("Student"))
                {
                    LinkedList<String> lables = new LinkedList<>();
                    lables.add("Name: ");
                    lables.add("BroncoID: ");
                    lables.add("Course: ");
                    fields = FormSpecification.getTextFields("Add Student", lables, formsPanel);
                }
            }

            // Shortened code for entered fields
            final LinkedList<JTextField> ef = fields;

            // Enter Button
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 10;
            gbc.gridy = 10;
            gbc.anchor = GridBagConstraints.EAST;
            gbc.insets = new Insets(20, 0, 0, 0);
            enterButton = createButton("Enter");
            enterButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Implement Field Use
                    if (actionButton.equals("Add")) {
                        if (selectedItem.equals("Student")) {
                            System.out.println("Start");
                            Student student = new Student(
                                ef.get(0).getText(),
                                Integer.parseInt(ef.get(1).getText()),
                                ef.get(2).getText()
                            );
                            StudentController.insertStudent(student);
                            System.out.println("Done");
                        }
                    }
                    else
                    {
                        System.out.println(actionButton + " -- " + selectedItem);
                    }
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

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBackground(new Color(240, 240, 240)); // Light gray background color
        buttonPanel.setPreferredSize(new Dimension(800, 50));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        buttonPanel.add(backButton, BorderLayout.WEST);

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