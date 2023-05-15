package cs_3560_project.app;

import javax.swing.*;

import cs_3560_project.server.controllers.LoanController;
import cs_3560_project.server.dao.EntityNotFoundException;
import cs_3560_project.server.model.Book;
import cs_3560_project.server.model.Loan;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class LoanInformationScreen extends JFrame {
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JPanel formsPanel;
    private JButton backButton;
    private JButton enterButton;
    private Loan loan;

    public void initilize() {

        setTitle("Information Screen");
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

    public LoanInformationScreen(Loan loan) {
        this.loan = loan;
        initilize();
    }

    private void createButtonPanel() {
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(new Color(240, 240, 240)); // Light gray background color
        buttonPanel.setPreferredSize(new Dimension(400, 100));

        String selectedItem;
        if (loan.getItem() instanceof Book)
            selectedItem = "Book";
        else
            selectedItem = "Documentary";
        updateButtonPanel(selectedItem);
    }

    private void updateButtonPanel(String selectedItem) {
        buttonPanel.removeAll();

        if (selectedItem != null) {
            String[] buttons = { "Info", "Return", "Delete" };
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
        if (loan.getItem() instanceof Book)
            selectedItem = "Book";
        else
            selectedItem = "Documentary";
        updateFormsPanel(selectedItem, "Info");
    }

    private void updateFormsPanel(String selectedItem, String actionButton) {
        formsPanel.removeAll();
        if (selectedItem != null && actionButton != null) {

            // Implement Forms
            if (actionButton.equals("Info")) {
                if (selectedItem.equals("Book")) {
                    LinkedList<String> lables = new LinkedList<>();
                    lables.add("Number: ");
                    lables.add("Loan Date (MM/DD/YYYY): ");
                    lables.add("Due Date (MM/DD/YYYY): ");
                    lables.add("Student ID: ");
                    lables.add("Book Code: ");
                    lables.add("Return Date(MM/DD/YYYY): ");
                    lables.add("Total Price: ");
                    LinkedList<String> data = new LinkedList<>();
                    data.add(Integer.toString(loan.getNumber()));
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    String loanString = loan.getLoanDate().format(formatter);
                    data.add(loanString);
                    String dateString = loan.getDueDate().format(formatter);
                    data.add(dateString);
                    data.add(Integer.toString(loan.getStudent().getBroncoId()));
                    data.add(Integer.toString(loan.getItem().getCode()));
                    if (loan.getReturnDate() != null)
                    {
                        String returnString = loan.getReturnDate().format(formatter);
                        data.add(returnString);
                        data.add(Double.toString(loan.getTotalPrice()));
                    }
                    else
                    {
                        data.add("TBD");
                        data.add("TBD");
                    }
                    FormSpecification.showInfo("Book Loan", lables, data, formsPanel);
                }
                if (selectedItem.equals("Documentary")) {
                    LinkedList<String> lables = new LinkedList<>();
                    lables.add("Number: ");
                    lables.add("Loan Date (MM/DD/YYYY): ");
                    lables.add("Due Date (MM/DD/YYYY): ");
                    lables.add("Student ID: ");
                    lables.add("Documentary Code: ");
                    lables.add("Return Date(MM/DD/YYYY): ");
                    lables.add("Total Price: ");
                    LinkedList<String> data = new LinkedList<>();
                    data.add(Integer.toString(loan.getNumber()));
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    String loanString = loan.getLoanDate().format(formatter);
                    data.add(loanString);
                    String dateString = loan.getDueDate().format(formatter);
                    data.add(dateString);
                    data.add(Integer.toString(loan.getStudent().getBroncoId()));
                    data.add(Integer.toString(loan.getItem().getCode()));
                    if (loan.getReturnDate() != null) {
                        String returnString = loan.getReturnDate().format(formatter);
                        data.add(returnString);
                        data.add(Double.toString(loan.getTotalPrice()));
                    } else {
                        data.add("TBD");
                        data.add("TBD");
                    }
                    FormSpecification.showInfo("Documentary Loan", lables, data, formsPanel);
                }
            }
            else if (actionButton.equals("Return")) {
                if (selectedItem.equals("Book")) {
                    LinkedList<String> lables = new LinkedList<>();
                    lables.add("Total Price: ");
                    LinkedList<String> data = new LinkedList<>();
                    double calculatedPrice = 1.1 * (LocalDate.now().compareTo(loan.getDueDate())) * loan.getItem().getDailyPrice();
                    if (calculatedPrice < 0)
                        calculatedPrice = 0.0;
                    data.add(Double.toString(calculatedPrice));
                    FormSpecification.showInfo("Return Book", lables, data, formsPanel);
                }
                if (selectedItem.equals("Documentary")) {
                    LinkedList<String> lables = new LinkedList<>();
                    lables.add("Total Price: ");
                    LinkedList<String> data = new LinkedList<>();
                    double calculatedPrice = 1.1 * (LocalDate.now().compareTo(loan.getDueDate())) * loan.getItem().getDailyPrice();
                    if (calculatedPrice < 0)
                        calculatedPrice = 0.0;
                    data.add(Double.toString(calculatedPrice));
                    FormSpecification.showInfo("Return Documentary", lables, data, formsPanel);
                }
            }

            // Shortened code for entered fields

            // Enter Button
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 10;
            gbc.gridy = 10;
            gbc.anchor = GridBagConstraints.EAST;
            gbc.insets = new Insets(20, 0, 0, 0);
            if (actionButton.equals("Delete"))
                enterButton = createButton("Confirm");
            else if (actionButton.equals("Return"))
                enterButton = createButton("Confirm");
            else
                enterButton = createButton("Enter");
            enterButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Implement Field Use
                    if (actionButton.equals("Return")) {
                        if (selectedItem.equals("Book")) {
                            try {
                                if (loan.getReturnDate() != null)
                                    throw new Exception("");
                                LoanController.returnItem(loan.getNumber());
                                updateFormsPanel(selectedItem, actionButton);
                                loan = LoanController.fetchLoan(loan.getNumber());
                                alert("Book has been returned!");
                            } catch (Exception error) {
                                alert("Book Loan already returned.");
                            }
                        }
                        if (selectedItem.equals("Documentary")) {
                            try {
                                if (loan.getReturnDate() != null)
                                    throw new Exception("");
                                LoanController.returnItem(loan.getNumber());
                                updateFormsPanel(selectedItem, actionButton);
                                loan = LoanController.fetchLoan(loan.getNumber());
                                alert("Documentary has been returned!");
                            } catch (Exception error) {
                                alert("Documentary Loan already returned.");
                            }
                        }
                    }
                    if (actionButton.equals("Delete")) {
                        if (selectedItem.equals("Book")) {
                            try {
                                if (loan.getReturnDate() == null)
                                    LoanController.returnItem(loan.getNumber());
                                LoanController.deleteLoan(loan.getNumber());
                                // Go Back
                                setVisible(false);
                                LoansScreen buttonScreen = new LoansScreen();
                                buttonScreen.setVisible(true);
                                // Output Message
                                alert("Book Loan has been deleted.");
                            } catch (EntityNotFoundException error) {
                            }
                        }
                        if (selectedItem.equals("Documentary")) {
                            try {
                                if (loan.getReturnDate() == null)
                                    LoanController.returnItem(loan.getNumber());
                                LoanController.deleteLoan(loan.getNumber());
                                // Go Back
                                setVisible(false);
                                LoansScreen buttonScreen = new LoansScreen();
                                buttonScreen.setVisible(true);
                                // Output Message
                                alert("Documentary Loan has been deleted.");
                            } catch (EntityNotFoundException error) {
                            }
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
                LoansScreen buttonScreen = new LoansScreen();
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