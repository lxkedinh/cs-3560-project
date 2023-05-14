package cs_3560_project.app;

import javax.swing.*;

import cs_3560_project.server.controllers.DirectorController;
import cs_3560_project.server.controllers.ItemController;
import cs_3560_project.server.dao.EntityNotFoundException;
import cs_3560_project.server.model.Author;
import cs_3560_project.server.model.Book;
import cs_3560_project.server.model.Director;
import cs_3560_project.server.model.Documentary;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class ItemInformationScreen extends JFrame {
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JPanel formsPanel;
    private JButton backButton;
    private JButton enterButton;
    private Book book;
    private Documentary documentary;
    

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

    public ItemInformationScreen(Book book) {
        this.book = book;
        initilize();
    }

    public ItemInformationScreen(Documentary documentary) {
        this.documentary = documentary;
        initilize();
    }

    private void createButtonPanel() {
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(new Color(240, 240, 240)); // Light gray background color
        buttonPanel.setPreferredSize(new Dimension(400, 100));

        String selectedItem;
        if (book != null)
            selectedItem = "Book";
        else
            selectedItem = "Documentary";
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
        if (book != null)
            selectedItem = "Book";
        else
            selectedItem = "Documentary";
        updateFormsPanel(selectedItem, "Info");
    }

    private void updateFormsPanel(String selectedItem, String actionButton) {
        formsPanel.removeAll();
        if (selectedItem != null && actionButton != null) {
            LinkedList<JTextField> fields = new LinkedList<>();

            // Implement Forms
            if (actionButton.equals("Info")) {
                if (selectedItem.equals("Book")) {
                    LinkedList<String> lables = new LinkedList<>();
                    lables.add("Code: ");
                    lables.add("Title: ");
                    lables.add("Description: ");
                    lables.add("Location: ");
                    lables.add("Daily Price: ");
                    lables.add("Pages: ");
                    lables.add("Publisher: ");
                    lables.add("Publication Date (MM/DD/YYYY): ");
                    lables.add("Author IDs (Seperate By Comma): ");
                    LinkedList<String> data = new LinkedList<>();
                    data.add(Integer.toString(book.getCode()));
                    data.add(book.getTitle());
                    data.add(book.getDescription());
                    data.add(book.getLocation());
                    data.add(Double.toString(book.getDailyPrice()));
                    data.add(Integer.toString(book.getPages()));
                    data.add(book.getPublisher());
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    String dateString = book.getPublicationDate().format(formatter);
                    data.add(dateString);
                    LinkedList<String> stringAuthor = new LinkedList<>();
                    List<Author> arrayAuthor = book.getAllAuthors();
                    for (int i = 0; i < arrayAuthor.size(); i++) {
                        stringAuthor.add(Integer.toString(arrayAuthor.get(i).getId()));
                    }
                    data.add(String.join(", ", stringAuthor));
                    FormSpecification.showInfo("Book", lables, data, formsPanel);
                }
                if (selectedItem.equals("Documentary")) {
                    LinkedList<String> lables = new LinkedList<>();
                    lables.add("Code: ");
                    lables.add("Title: ");
                    lables.add("Description: ");
                    lables.add("Location: ");
                    lables.add("Daily Price: ");
                    lables.add("Length: ");
                    lables.add("Release Date (MM/DD/YYYY): ");
                    lables.add("Director ID: ");
                    LinkedList<String> data = new LinkedList<>();
                    data.add(Integer.toString(documentary.getCode()));
                    data.add(documentary.getTitle());
                    data.add(documentary.getDescription());
                    data.add(documentary.getLocation());
                    data.add(Double.toString(documentary.getDailyPrice()));
                    data.add(Double.toString(documentary.getLength()));
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    String dateString = documentary.getReleaseDate().format(formatter);
                    data.add(dateString);
                    data.add(Integer.toString(documentary.getDirector().getId()));
                    FormSpecification.showInfo("Documentary", lables, data, formsPanel);
                }
            }
            if (actionButton.equals("Update")) {
                if (selectedItem.equals("Book")) {
                    LinkedList<String> lables = new LinkedList<>();
                    lables.add("Code: ");
                    lables.add("Title: ");
                    lables.add("Description: ");
                    lables.add("Location: ");
                    lables.add("Daily Price: ");
                    lables.add("Pages: ");
                    lables.add("Publisher: ");
                    lables.add("Publication Date (MM/DD/YYYY): ");
                    lables.add("Author IDs (Seperate By Comma): ");
                    LinkedList<String> data = new LinkedList<>();
                    data.add(Integer.toString(book.getCode()));
                    data.add(book.getTitle());
                    data.add(book.getDescription());
                    data.add(book.getLocation());
                    data.add(Double.toString(book.getDailyPrice()));
                    data.add(Integer.toString(book.getPages()));
                    data.add(book.getPublisher());
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    String dateString = book.getPublicationDate().format(formatter);
                    data.add(dateString);
                    LinkedList<String> stringAuthor = new LinkedList<>();
                    List<Author> arrayAuthor = book.getAllAuthors();
                    for (int i = 0; i < arrayAuthor.size(); i++) {
                        stringAuthor.add(Integer.toString(arrayAuthor.get(i).getId()));
                    }
                    data.add(String.join(", ", stringAuthor));
                    fields = FormSpecification.getTextDisplayFields("Update Book", lables, data, formsPanel);
                }
                if (selectedItem.equals("Documentary")) {
                    LinkedList<String> lables = new LinkedList<>();
                    lables.add("Code: ");
                    lables.add("Title: ");
                    lables.add("Description: ");
                    lables.add("Location: ");
                    lables.add("Daily Price: ");
                    lables.add("Length: ");
                    lables.add("Release Date (MM/DD/YYYY): ");
                    lables.add("Director ID: ");
                    LinkedList<String> data = new LinkedList<>();
                    data.add(Integer.toString(documentary.getCode()));
                    data.add(documentary.getTitle());
                    data.add(documentary.getDescription());
                    data.add(documentary.getLocation());
                    data.add(Double.toString(documentary.getDailyPrice()));
                    data.add(Double.toString(documentary.getLength()));
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    String dateString = documentary.getReleaseDate().format(formatter);
                    data.add(dateString);
                    data.add(Integer.toString(documentary.getDirector().getId()));
                    fields = FormSpecification.getTextDisplayFields("Update Documentary", lables, data, formsPanel);
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
                        if (selectedItem.equals("Book")) {
                            try {
                                int code;
                                try {
                                    code = Integer.parseInt(ef.get(0).getText());
                                } catch (NumberFormatException error) {
                                    throw new Exception("Code");
                                }

                                double price;
                                try {
                                    price = Double.parseDouble(ef.get(4).getText());
                                } catch (NumberFormatException error) {
                                    throw new Exception("Price");
                                }

                                int pages;
                                try {
                                    pages = Integer.parseInt(ef.get(5).getText());
                                } catch (NumberFormatException error) {
                                    throw new Exception("Pages");
                                }

                                LocalDate releaseDate = LoansScreen.getDate(ef.get(7).getText());
                                if (releaseDate == null)
                                    throw new Exception("Date");

                                List<Author> authors;
                                try {
                                    authors = InventoryScreen.getAuthors(ef.get(8).getText());
                                } catch (Exception error) {
                                    throw new Exception("Author");
                                }
                                book.setCode(code);
                                book.setTitle(ef.get(1).getText());
                                book.setDescription(ef.get(2).getText());
                                book.setLocation(ef.get(3).getText());
                                book.setDailyPrice(price);
                                book.setPages(pages);
                                book.setPublisher(ef.get(6).getText());
                                book.setPublicationDate(releaseDate);
                                book.setAllAuthors(authors);
                                ItemController.updateBook(book);
                                updateFormsPanel(selectedItem, actionButton);
                                alert(book.getTitle() + " has been updated!");
                            } catch (Exception error) {
                                if (error.getMessage().equals("Code"))
                                {
                                    alert("Book Code must only be Integers.");
                                    ef.get(0).setText("");
                                }
                                if (error.getMessage().equals("Price"))
                                {
                                    alert("Book Price must only be Numbers. Decimal Allowed.");
                                    ef.get(4).setText("");
                                }
                                if (error.getMessage().equals("Pages"))
                                {
                                    alert("Book Pages must only be Integers.");
                                    ef.get(5).setText("");
                                }
                                if (error.getMessage().equals("Date"))
                                {
                                    alert("Publication Date was not entered correctly.");
                                    ef.get(7).setText("");
                                }
                                if (error.getMessage().equals("Author"))
                                {
                                    alert("Author with one of the IDs " + ef.get(8).getText() + " does not exist.");
                                    ef.get(8).setText("");
                                }
                                if (error.getMessage().equals("Exists"))
                                {
                                    alert("Book with Code " + ef.get(0).getText() + " already exists.");
                                    ef.get(0).setText("");
                                }
                            }
                        }
                        if (selectedItem.equals("Documentary")) {
                            try {
                                int code;
                                try {
                                    code = Integer.parseInt(ef.get(0).getText());
                                } catch (NumberFormatException error) 
                                { throw new Exception("Code"); }

                                double price;
                                try {
                                    price = Double.parseDouble(ef.get(4).getText());
                                } catch (NumberFormatException error) 
                                { throw new Exception("Price"); }

                                double length;
                                try {
                                    length = Double.parseDouble(ef.get(5).getText());
                                } catch (NumberFormatException error) 
                                { throw new Exception("Length"); }

                                LocalDate releaseDate = LoansScreen.getDate(ef.get(6).getText());
                                if (releaseDate == null)
                                    throw new Exception("Date");

                                Director director;
                                try {
                                    director = DirectorController.fetchDirector(
                                        Integer.parseInt(ef.get(7).getText())
                                    );
                                } catch (Exception error) 
                                { throw new Exception("Director"); }

                                documentary.setCode(code);
                                documentary.setTitle(ef.get(1).getText());
                                documentary.setDescription(ef.get(2).getText());
                                documentary.setLocation(ef.get(3).getText());
                                documentary.setDailyPrice(price);
                                documentary.setLength(length);
                                documentary.setReleaseDate(releaseDate);
                                documentary.setDirector(director);
                                ItemController.updateDocumentary(documentary);
                                updateFormsPanel(selectedItem, actionButton);
                                alert(documentary.getTitle() + " has been updated!");
                            }
                            catch (Exception error) {
                                if (error.getMessage().equals("Code"))
                                {
                                    alert("Documentary Code must only be Integers.");
                                    ef.get(0).setText("");
                                }
                                if (error.getMessage().equals("Price"))
                                {
                                    alert("Documentary Price must only be Numbers. Decimal Allowed.");
                                    ef.get(4).setText("");
                                }
                                if (error.getMessage().equals("Pages"))
                                {
                                    alert("Documentary Length must only be Integers.");
                                    ef.get(5).setText("");
                                }
                                if (error.getMessage().equals("Date"))
                                {
                                    alert("Release Date was not entered correctly.");
                                    ef.get(6).setText("");
                                }
                                if (error.getMessage().equals("Director"))
                                {
                                    alert("Director with ID " + ef.get(7).getText() + " does not exist.");
                                    ef.get(7).setText("");
                                }
                                if (error.getMessage().equals("Exists"))
                                {
                                    alert("Documentary with Code " + ef.get(0).getText() + " already exists.");
                                    ef.get(0).setText("");
                                }
                            }
                        }
                    }
                    if (actionButton.equals("Delete")) {
                        if (selectedItem.equals("Book")) {
                            try {
                                ItemController.deleteBook(book.getCode());
                                // Go Bacl
                                setVisible(false);
                                InventoryScreen buttonScreen = new InventoryScreen();
                                buttonScreen.setVisible(true);
                                // Output Message
                                alert(book.getTitle() + " has been deleted.");
                            } catch (EntityNotFoundException error) {
                            }
                        }
                        if (selectedItem.equals("Documentary")) {
                            try {
                                ItemController.deleteDocumentary(documentary.getCode());
                                // Go Back
                                setVisible(false);
                                InventoryScreen buttonScreen = new InventoryScreen();
                                buttonScreen.setVisible(true);
                                // Output Message
                                alert(documentary.getTitle() + " has been deleted.");
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
                InventoryScreen buttonScreen = new InventoryScreen();
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