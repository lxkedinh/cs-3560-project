package cs_3560_project.app;

import cs_3560_project.server.controllers.LoanController;
import cs_3560_project.server.dao.EntityNotFoundException;
import cs_3560_project.server.model.Book;
import cs_3560_project.server.model.Documentary;
import cs_3560_project.server.model.Item;
import cs_3560_project.server.model.Loan;

import javax.swing.*;

import cs_3560_project.server.controllers.LoanController;
import cs_3560_project.server.dao.EntityNotFoundException;
import cs_3560_project.server.model.Loan;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class ButtonScreen extends JFrame {

    public ButtonScreen() {
        setTitle("CPP Library");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 100)); // Add spacing between buttons

        JButton loansButton = createButton("Loans");
        JButton inventoryButton = createButton("Inventory");
        JButton managementButton = createButton("Management");
        JButton reportButton = createButton("Financial");

        buttonPanel.add(loansButton);
        buttonPanel.add(inventoryButton);
        buttonPanel.add(managementButton);
        buttonPanel.add(reportButton);

        JLabel logoLabel = createLogoLabel(System.getProperty("user.dir") + "/src/main/java/cs_3560_project/app/logo.png"); // Specify the path to your PNG logo image
        add(logoLabel, BorderLayout.CENTER); // Place the logo label in the center

        add(buttonPanel, BorderLayout.SOUTH); // Place the button panel at the bottom

        pack();
        setSize(800, 700); // Set the preferred size of the JFrame
        setLocationRelativeTo(null);
    }

    private JButton createButton(String label) {
        JButton button = new JButton(label);
        button.setPreferredSize(new Dimension(120, 40));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(240, 240, 240)); // Light gray background color
        button.setForeground(new Color(60, 72, 107)); // Dark blue text color

        button.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                if (label.equals("Loans"))
                    openLoansScreen();
                else if (label.equals("Management"))
                    openManagementScreen();
                else if (label.equals("Inventory"))
                    openInventoryScreen();
                else if (label.equals("Financial"))
                    openFinancialReport();
            }
        });

        return button;
    }

    private JLabel createLogoLabel(String imagePath) {
        ImageIcon logoIcon = new ImageIcon(imagePath);
        JLabel logoLabel = new JLabel(logoIcon);
        return logoLabel;
    }

    private void openManagementScreen() {
        ManageScreen managementScreen = new ManageScreen();
        managementScreen.setVisible(true);
        dispose(); // Close the main screen
    }

    private void openInventoryScreen() {
        InventoryScreen inventoryScreen = new InventoryScreen();
        inventoryScreen.setVisible(true);
        dispose();
    }

    private void openLoansScreen() {
        LoansScreen loansScreen = new LoansScreen();
        loansScreen.setVisible(true);
        dispose(); // Close the main screen
    }
    
    private void openFinancialReport() {
        List<Loan> loanReport;
        try
        {
            loanReport = LoanController.fetchAllLoans();
        }
        catch (EntityNotFoundException error)
        {
            loanReport = new LinkedList<>();
        }
        FinancialTableScreen infoScreen = new FinancialTableScreen(loanReport);
        infoScreen.setVisible(true);
        dispose(); // Close the main screen
    }
}