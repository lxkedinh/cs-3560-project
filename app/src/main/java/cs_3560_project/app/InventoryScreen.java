package cs_3560_project.app;

import javax.swing.*;

import cs_3560_project.server.controllers.AuthorController;
import cs_3560_project.server.controllers.DirectorController;
import cs_3560_project.server.controllers.ItemController;
import cs_3560_project.server.dao.EntityNotFoundException;
import cs_3560_project.server.model.Author;
import cs_3560_project.server.model.Book;
import cs_3560_project.server.model.Director;
import cs_3560_project.server.model.Documentary;
import cs_3560_project.server.model.ItemStatus;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class InventoryScreen extends JFrame {
    private JPanel mainPanel;
    private JComboBox<String> dropdown;
    private JPanel buttonPanel;
    private JPanel formsPanel;
    private JButton backButton;
    private JButton enterButton;

    public InventoryScreen() {
        setTitle("Inventory Screen");
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

        dropdown = new JComboBox<>(new String[] { "Select Item", "Book", "Documentary"});
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
            LinkedList<JTextField> fields = new LinkedList<>();

            // Implement Forms
            if (actionButton.equals("Add")) {
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
                    fields = FormSpecification.getTextFields("Add Documentary", lables, formsPanel);
                }
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
                    fields = FormSpecification.getTextFields("Add Book", lables, formsPanel);
                }
            }
            if (actionButton.equals("Search")) {
                if (selectedItem.equals("Documentary")) {
                    LinkedList<String> lables = new LinkedList<>();
                    lables.add("Documentary Code: ");
                    fields = FormSpecification.getTextFields("Search For Documentary", lables, formsPanel);
                }
                if (selectedItem.equals("Book")) {
                    LinkedList<String> lables = new LinkedList<>();
                    lables.add("Book Code: ");
                    fields = FormSpecification.getTextFields("Search For Book", lables, formsPanel);
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

                                Documentary documentary = new Documentary(
                                    code,
                                    ef.get(1).getText(),
                                    ef.get(2).getText(),
                                    ef.get(3).getText(),
                                    price,
                                    ItemStatus.Available,
                                    length,
                                    releaseDate,
                                    director
                                );
                                try {
                                    // Hacky fix for the issue where adding documentary also adds director even though it exists
                                    DirectorController.deleteDirector(director.getId());
                                    ItemController.insertDocumentary(documentary);
                                } catch (Exception error) {
                                    throw new Exception("Exists");
                                }
                                alert(documentary.getTitle() + " has been added!");
                                clearFields(ef);
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
                                if (error.getMessage().equals("Length"))
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
                                    System.out.println(error.toString());
                                }
                            }
                        }
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
                                    authors = getAuthors(ef.get(8).getText());
                                } catch (Exception error) {
                                    throw new Exception("Author");
                                }

                                Book book = new Book(
                                        code,
                                        ef.get(1).getText(),
                                        ef.get(2).getText(),
                                        ef.get(3).getText(),
                                        price,
                                        ItemStatus.Available,
                                        pages,
                                        ef.get(6).getText(),
                                        releaseDate,
                                        authors);
                                try {
                                    ItemController.insertBook(book);
                                } catch (Exception error) {
                                    throw new Exception("Exists");
                                }
                                alert(book.getTitle() + " has been added!");
                                clearFields(ef);
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
                    }
                    if (actionButton.equals("Search")) {
                        if (selectedItem.equals("Documentary")) {
                            int id = Integer.parseInt(ef.get(0).getText());
                            try {
                                Documentary documentary = ItemController.fetchDocumentary(id);
                                ItemInformationScreen infoScreen = new ItemInformationScreen(documentary);
                                infoScreen.setVisible(true);
                                dispose();
                            } catch (Exception error) {
                                alert("Documentary with ID: " + id + " was not found.");
                                System.out.println(error.toString());
                            } finally {
                                clearFields(ef);
                            }
                        }
                        if (selectedItem.equals("Book")) {
                            int id = Integer.parseInt(ef.get(0).getText());
                            try {
                                Book book = ItemController.fetchBook(id);
                                ItemInformationScreen infoScreen = new ItemInformationScreen(book);
                                infoScreen.setVisible(true);
                                dispose();
                            } catch (Exception error) {
                                alert("Book with ID: " + id + " was not found.");
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

    public static LinkedList<Author> getAuthors(String text) throws NumberFormatException, EntityNotFoundException {
        String[] splitText = text.split(",");

        // Trim leading and trailing spaces from each string
        for (int i = 0; i < splitText.length; i++) {
            splitText[i] = splitText[i].trim();
        }

        LinkedList<Author> authors = new LinkedList<>();

        for (int i = 0; i < splitText.length; i++) {
            authors.add(AuthorController.fetchAuthor(Integer.parseInt(splitText[i])));
        }

        return authors;
    }
}