package cs_3560_project.app;

import javax.swing.*;

import cs_3560_project.server.controllers.AuthorController;
import cs_3560_project.server.controllers.DirectorController;
import cs_3560_project.server.controllers.StudentController;
import cs_3560_project.server.dao.EntityNotFoundException;
import cs_3560_project.server.model.Author;
import cs_3560_project.server.model.Director;
import cs_3560_project.server.model.Student;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class PersonInformationScreen extends JFrame {
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JPanel formsPanel;
    private JButton backButton;
    private JButton enterButton;
    private Student student;
    private Author author;
    private Director director;

    public void initilize() {

        setTitle("Management Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240)); // Light gray background color

        createButtonPanel();
        createFormsPanel();
        createButtons();

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(formsPanel, BorderLayout.SOUTH);

        add(mainPanel);

        pack();
        setSize(800, 700); // Set the preferred size of the JFrame
        setLocationRelativeTo(null);
    }

    public PersonInformationScreen(Student student) {
        this.student = student;
        initilize();
    }

    public PersonInformationScreen(Author author) {
        this.author = author;
        initilize();
    }

    public PersonInformationScreen(Director director) {
        this.director = director;
        initilize();
    }

    private void createButtonPanel() {
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(new Color(240, 240, 240)); // Light gray background color
        buttonPanel.setPreferredSize(new Dimension(400, 100));

        String selectedItem;
        if (student != null)
            selectedItem = "Student";
        else if (author != null)
            selectedItem = "Author";
        else
            selectedItem = "Director";
        updateButtonPanel(selectedItem);
    }

    private void updateButtonPanel(String selectedItem) {
        buttonPanel.removeAll();

        if (selectedItem != null) {
            String[] buttons = { "Info", "Update", "Delete" };
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

        String selectedItem;
        if (student != null)
            selectedItem = "Student";
        else if (author != null)
            selectedItem = "Author";
        else
            selectedItem = "Director";
        updateFormsPanel(selectedItem, "Info");
    }

    private void updateFormsPanel(String selectedItem, String actionButton) {
        formsPanel.removeAll();
        if (selectedItem != null && actionButton != null) {
            LinkedList<JTextField> fields = new LinkedList<>();

            // Implement Forms
            if (actionButton.equals("Info")) {
                if (selectedItem.equals("Student")) {
                    LinkedList<String> lables = new LinkedList<>();
                    lables.add("Name: ");
                    lables.add("BroncoID: ");
                    lables.add("Course: ");
                    LinkedList<String> data = new LinkedList<>();
                    data.add(student.getName());
                    data.add(Integer.toString(student.getBroncoId()));
                    data.add(student.getCourse());
                    FormSpecification.showInfo("Student", lables, data, formsPanel);
                }
                if (selectedItem.equals("Author")) {
                    LinkedList<String> lables = new LinkedList<>();
                    lables.add("ID: ");
                    lables.add("Name: ");
                    lables.add("Nationality: ");
                    lables.add("Subject: ");
                    LinkedList<String> data = new LinkedList<>();
                    data.add(Integer.toString(author.getId()));
                    data.add(author.getName());
                    data.add(author.getNationality());
                    data.add(author.getSubject());
                    FormSpecification.showInfo("Author", lables, data, formsPanel);
                }
                if (selectedItem.equals("Director")) {
                    LinkedList<String> lables = new LinkedList<>();
                    lables.add("ID: ");
                    lables.add("Name: ");
                    lables.add("Nationality: ");
                    lables.add("Style: ");
                    LinkedList<String> data = new LinkedList<>();
                    data.add(Integer.toString(director.getId()));
                    data.add(director.getName());
                    data.add(director.getNationality());
                    data.add(director.getStyle());
                    FormSpecification.showInfo("Director", lables, data, formsPanel);
                }
            }
            if (actionButton.equals("Update")) {
                if (selectedItem.equals("Student")) {
                    LinkedList<String> lables = new LinkedList<>();
                    lables.add("Name: ");
                    lables.add("BroncoID: ");
                    lables.add("Course: ");
                    LinkedList<String> data = new LinkedList<>();
                    data.add(student.getName());
                    data.add(Integer.toString(student.getBroncoId()));
                    data.add(student.getCourse());
                    fields = FormSpecification.getTextDisplayFields("Update Student", lables, data, formsPanel);
                }
                if (selectedItem.equals("Author")) {
                    LinkedList<String> lables = new LinkedList<>();
                    lables.add("ID: ");
                    lables.add("Name: ");
                    lables.add("Nationality: ");
                    lables.add("Subject: ");
                    LinkedList<String> data = new LinkedList<>();
                    data.add(Integer.toString(author.getId()));
                    data.add(author.getName());
                    data.add(author.getNationality());
                    data.add(author.getSubject());
                    fields = FormSpecification.getTextDisplayFields("Update Author", lables, data, formsPanel);
                }
                if (selectedItem.equals("Director")) {
                    LinkedList<String> lables = new LinkedList<>();
                    lables.add("ID: ");
                    lables.add("Name: ");
                    lables.add("Nationality: ");
                    lables.add("Style: ");
                    LinkedList<String> data = new LinkedList<>();
                    data.add(Integer.toString(director.getId()));
                    data.add(director.getName());
                    data.add(director.getNationality());
                    data.add(director.getStyle());
                    FormSpecification.showInfo("Director", lables, data, formsPanel);
                    fields = FormSpecification.getTextDisplayFields("Update Director", lables, data, formsPanel);
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
            if (actionButton.equals("Delete"))
                enterButton = createButton("Confirm");
            else
                enterButton = createButton("Enter");
            enterButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Implement Field Use
                    if (actionButton.equals("Update")) {
                        if (selectedItem.equals("Student")) {
                            student.setName(ef.get(0).getText());
                            student.setBroncoId(Integer.parseInt(ef.get(1).getText()));
                            student.setCourse(ef.get(2).getText());
                            StudentController.updateStudent(student);
                            updateFormsPanel(selectedItem, actionButton);
                            alert(student.getName() + " has been updated!");
                        }
                        if (selectedItem.equals("Author")) {
                            author.setName(ef.get(1).getText());
                            author.setId(Integer.parseInt(ef.get(0).getText()));
                            author.setNationality(ef.get(2).getText());
                            author.setSubject(ef.get(3).getText());
                            AuthorController.updateAuthor(author);
                            updateFormsPanel(selectedItem, actionButton);
                            alert(author.getName() + " has been updated!");
                        }
                        if (selectedItem.equals("Director")) {
                            director.setName(ef.get(1).getText());
                            director.setId(Integer.parseInt(ef.get(0).getText()));
                            director.setNationality(ef.get(2).getText());
                            director.setStyle(ef.get(3).getText());
                            DirectorController.updateDirector(director);
                            alert(director.getName() + " has been updated!");
                            clearFields(ef);
                        }
                    }
                    if (actionButton.equals("Delete")) {
                        if (selectedItem.equals("Student")) {
                            try {
                                StudentController.deleteStudent(student.getBroncoId());
                                // Go Bacl
                                setVisible(false); 
                                ManageScreen buttonScreen = new ManageScreen();
                                buttonScreen.setVisible(true);
                                // Output Message
                                alert(student.getName() + " has been deleted.");
                            } catch (EntityNotFoundException error) {}
                        }
                        if (selectedItem.equals("Author")) {
                            try {
                                AuthorController.deleteAuthor(author.getId());
                                // Go Bacl
                                setVisible(false);
                                ManageScreen buttonScreen = new ManageScreen();
                                buttonScreen.setVisible(true);
                                // Output Message
                                alert(author.getName() + " has been deleted.");
                            } catch (EntityNotFoundException error) {}
                        }
                        if (selectedItem.equals("Director")) {
                            try {
                                DirectorController.deleteDirector(director.getId());
                                // Go Bacl
                                setVisible(false);
                                ManageScreen buttonScreen = new ManageScreen();
                                buttonScreen.setVisible(true);
                                // Output Message
                                alert(director.getName() + " has been deleted.");
                            } catch (EntityNotFoundException error) {}
                        }
                    }
                }
            });
            if (!actionButton.equals("Info"))
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
                ManageScreen buttonScreen = new ManageScreen();
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
        } else {
            button.setForeground(new Color(60, 72, 107)); // Dark blue text color
        }
        return button;
    }

    public static void alert(String message) {
        JOptionPane.showMessageDialog(null, message, "Alert", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void clearFields(LinkedList<JTextField> fields) {
        for (int i = 0; i < fields.size(); i++) {
            fields.get(i).setText("");
        }
    }
}