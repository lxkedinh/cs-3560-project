package cs_3560_project.app;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import cs_3560_project.server.model.Loan;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class OverdueLoansScreen extends JFrame {
    private JTable loansTable;

    public OverdueLoansScreen(LinkedList<Loan> loanList) {
        setTitle("All Loans");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        createTable(loanList);
        createButtonPanel();

        add(new JScrollPane(loansTable), BorderLayout.CENTER);
        pack();
        setSize(800, 700);
        setLocationRelativeTo(null);
    }

    private void createTable(LinkedList<Loan> loanList) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Loan Number");
        model.addColumn("Loan Date");
        model.addColumn("Due Date");
        model.addColumn("Student ID");
        model.addColumn("Book Code");
        model.addColumn("Return Date");
        model.addColumn("Total Price");

        for (Loan loan : loanList) {
            String date;
            String price;
            if (loan.getReturnDate() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                String loanString = loan.getLoanDate().format(formatter);
                date = loanString;
                price = Double.toString(loan.getTotalPrice());
            } else {
                date = "TBD";
                price = "TBD";
            }

            model.addRow(new Object[] {
                    loan.getNumber(),
                    loan.getLoanDate(),
                    loan.getDueDate(),
                    loan.getStudent().getBroncoId(),
                    loan.getItem().getCode(),
                    date,
                    price
            });
        }

        loansTable = new JTable(model);
        loansTable.setPreferredScrollableViewportSize(new Dimension(800, 500));
        loansTable.setFillsViewportHeight(true);

        // Set custom table header renderer
        JTableHeader header = loansTable.getTableHeader();
        header.setDefaultRenderer(new HeaderRenderer());
    }

    private void createButtonPanel() {
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            setVisible(false);
            LoansScreen buttonScreen = new LoansScreen();
            buttonScreen.setVisible(true);
        });

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBackground(new Color(240, 240, 240));
        buttonPanel.setPreferredSize(new Dimension(800, 50));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.add(backButton, BorderLayout.WEST);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Custom table header renderer
    private static class HeaderRenderer extends DefaultTableCellRenderer {
        public HeaderRenderer() {
            setHorizontalAlignment(JLabel.CENTER);
            setOpaque(false);
            setForeground(new Color(60, 72, 107)); // Set font color
            setFont(getFont().deriveFont(Font.BOLD)); // Set bold font
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus,
                int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            JTableHeader header = table.getTableHeader();
            setBackground(header.getBackground());

            return this;
        }
    }
}