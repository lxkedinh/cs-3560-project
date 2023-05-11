package cs_3560_project.app;

import javax.swing.*;

import cs_3560_project.server.controllers.AuthorController;
import cs_3560_project.server.controllers.DirectorController;
import cs_3560_project.server.controllers.StudentController;
import cs_3560_project.server.model.Author;
import cs_3560_project.server.model.Director;
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
                    formsPanel.removeAll();
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
            String[] buttons = { "Add", "Search" };
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
                if (selectedItem.equals("Author"))
                {
                    LinkedList<String> lables = new LinkedList<>();
                    lables.add("ID: ");
                    lables.add("Name: ");
                    lables.add("Nationality: ");
                    lables.add("Subject: ");
                    fields = FormSpecification.getTextFields("Add Author", lables, formsPanel);
                }
                if (selectedItem.equals("Director")) {
                    LinkedList<String> lables = new LinkedList<>();
                    lables.add("ID: ");
                    lables.add("Name: ");
                    lables.add("Nationality: ");
                    lables.add("Style: ");
                    fields = FormSpecification.getTextFields("Add Director", lables, formsPanel);
                }
            }
            if (actionButton.equals("Search"))
            {
                if (selectedItem.equals("Student")) {
                    LinkedList<String> lables = new LinkedList<>();
                    lables.add("Student ID: ");
                    fields = FormSpecification.getTextFields("Search For Student", lables, formsPanel);
                }
                if (selectedItem.equals("Author")) {
                    LinkedList<String> lables = new LinkedList<>();
                    lables.add("Author ID: ");
                    fields = FormSpecification.getTextFields("Search For Author", lables, formsPanel);
                }
                if (selectedItem.equals("Director")) {
                    LinkedList<String> lables = new LinkedList<>();
                    lables.add("Director ID: ");
                    fields = FormSpecification.getTextFields("Search For Director", lables, formsPanel);
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
                            Student student = new Student(
                                ef.get(0).getText(),
                                Integer.parseInt(ef.get(1).getText()),
                                ef.get(2).getText()
                            );
                            StudentController.insertStudent(student);
                            alert(student.getName() + " has been added!");
                            clearFields(ef);
                        }
                        if (selectedItem.equals("Author")) {
                            Author author = new Author(
                                ef.get(1).getText(),
                                Integer.parseInt(ef.get(0).getText()),
                                ef.get(2).getText(),
                                ef.get(3).getText());
                            AuthorController.insertAuthor(author);
                            alert(author.getName() + " has been added!");
                            clearFields(ef);
                        }
                        if (selectedItem.equals("Director")) {
                            Director director = new Director(
                                    ef.get(1).getText(),
                                    Integer.parseInt(ef.get(0).getText()),
                                    ef.get(2).getText(),
                                    ef.get(3).getText());
                            DirectorController.insertDirector(director);
                            alert(director.getName() + " has been added!");
                            clearFields(ef);
                        }
                    }
                    if (actionButton.equals("Search")) {
                        if (selectedItem.equals("Student")) {
                            int id = Integer.parseInt(ef.get(0).getText());
                            try {
                                Student student = StudentController.fetchStudent(id);
                                PersonInformationScreen infoScreen = new PersonInformationScreen(student);
                                infoScreen.setVisible(true);
                                dispose();
                            }
                            catch (Exception error) {
                                alert("Student with ID: " + id + " was not found.");
                                System.out.println(error.toString());
                            }
                            finally {
                                clearFields(ef);
                            }
                        }
                        if (selectedItem.equals("Author")) {
                            int id = Integer.parseInt(ef.get(0).getText());
                            try {
                                Author author = AuthorController.fetchAuthor(id);
                                PersonInformationScreen infoScreen = new PersonInformationScreen(author);
                                infoScreen.setVisible(true);
                                dispose();
                            } catch (Exception error) {
                                alert("Author with ID: " + id + " was not found.");
                                System.out.println(error.toString());
                            } finally {
                                clearFields(ef);
                            }
                        }
                        if (selectedItem.equals("Director")) {
                            int id = Integer.parseInt(ef.get(0).getText());
                            try {
                                Director director = DirectorController.fetchDirector(id);
                                PersonInformationScreen infoScreen = new PersonInformationScreen(director);
                                infoScreen.setVisible(true);
                                dispose();
                            } catch (Exception error) {
                                alert("Director with ID: " + id + " was not found.");
                                System.out.println(error.toString());
                            } finally {
                                clearFields(ef);
                            }
                        }
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
        if (label.equals("Back")) {
            button.setForeground(new Color(244, 80, 80));
        }
        else {
            button.setForeground(new Color(60, 72, 107)); // Dark blue text color
        }
        return button;
    }

    public static void alert(String message) {
        JOptionPane.showMessageDialog(null, message, "Alert", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void clearFields(LinkedList<JTextField> fields) {
        for (int i = 0; i < fields.size(); i++)
        {
            fields.get(i).setText("");
        }
    }
}