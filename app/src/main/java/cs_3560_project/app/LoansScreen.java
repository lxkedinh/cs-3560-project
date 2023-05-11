package cs_3560_project.app;

import javax.swing.*;

import cs_3560_project.server.controllers.ItemController;
import cs_3560_project.server.controllers.LoanController;
import cs_3560_project.server.controllers.StudentController;
import cs_3560_project.server.model.Book;
import cs_3560_project.server.model.ItemStatus;
import cs_3560_project.server.model.Loan;
import cs_3560_project.server.model.Student;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;

public class LoansScreen extends JFrame {
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JPanel formsPanel;
    private JComboBox<String> dropdown;
    private JButton backButton;
    private JButton enterButton;

    public LoansScreen() {
        setTitle("Loans Screen");
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

        dropdown = new JComboBox<>(new String[] { "Select Item", "Books", "Documentaries" });
        dropdown.setRenderer(renderer);
        dropdown.setPreferredSize(new Dimension(50, 50));
        dropdown.setFont(new Font("Arial", Font.BOLD, 16));
        dropdown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!dropdown.getSelectedItem().equals("Select Item")) {
                    String selectedItem = (String) dropdown.getSelectedItem();
                    updateButtonPanel(selectedItem);
                    formsPanel.removeAll();
                }
            }
        });
        dropdown.setAlignmentX(Component.CENTER_ALIGNMENT);
        dropdown.setSelectedItem("Select Item");
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
            String[] buttons = { "Add", "Search", "Overdue" };
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
        if (actionButton != null) {
            LinkedList<JTextField> fields = new LinkedList<>();

            // Implement Forms
            if (selectedItem.equals("Books")) {
                if (actionButton.equals("Add")) {
                    LinkedList<String> lables = new LinkedList<>();
                    lables.add("Number: ");
                    lables.add("Due Date (MM/DD/YYYY): ");
                    lables.add("Student ID: ");
                    lables.add("Book Code: ");
                    fields = FormSpecification.getTextFields("Add Loan", lables, formsPanel);
                }
                if (actionButton.equals("Search")) {

                }
                if (actionButton.equals("Overdue")) {

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
                    if (selectedItem.equals("Books")) {
                        if (actionButton.equals("Add")) {
                            try {
                                // Get due date
                                LocalDate dueDate = getDate(ef.get(1).getText());
                                if (dueDate == null)
                                    throw new Exception("Date");

                                // Get student
                                Student student;
                                try {
                                    student = StudentController.fetchStudent(Integer.parseInt(ef.get(2).getText()));
                                } catch (Exception error) {
                                    throw new Exception("Student");
                                }

                                // Get book
                                Book book;
                                try {
                                    book = ItemController.fetchBook(Integer.parseInt(ef.get(3).getText()));
                                } catch (Exception error) {
                                    throw new Exception("Book");
                                }

                                Loan loan = new Loan(
                                        Integer.parseInt(ef.get(0).getText()),
                                        LocalDate.now(),
                                        dueDate,
                                        student,
                                        book
                                        );

                                book.setStatus(ItemStatus.Borrowed);
                                LoanController.insertLoan(loan);
                                alert("Loan has been added!");
                                clearFields(ef);
                            }
                            catch(Exception error) {
                                if (error.getMessage().equals("Date"))
                                {
                                    alert("Due Date was not entered correctly.");
                                    ef.get(1).setText("");
                                }
                                else if (error.getMessage().equals("Student"))
                                {
                                    alert("Student was not found.");
                                    ef.get(2).setText("");
                                }
                                else if (error.getMessage().equals("Book"))
                                {
                                    alert("Book was not found.");
                                    ef.get(3).setText("");
                                }
                            }
                        }
                        if (actionButton.equals("Search")) {
                            
                        }
                        if (actionButton.equals("Overdue")) {

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

    public static LocalDate getDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate localDate = null;

        try {
            localDate = LocalDate.parse(dateString, formatter);

            // Extracting month, day, and year from the LocalDate object
            int month = localDate.getMonthValue();
            int day = localDate.getDayOfMonth();
            int year = localDate.getYear();

            return LocalDate.of(year, month, day);
        } catch (DateTimeParseException error) {
            return null;
        }
    }
}