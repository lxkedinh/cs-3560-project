package cs_3560_project.app;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import cs_3560_project.server.model.Loan;

import java.awt.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FinancialTableScreen extends JFrame {
    private JTable loansTable;
    private JComboBox<String> monthComboBox;
    private JLabel totalLabel;
    private DefaultTableModel model;
    private Map<Month, Double> monthTotalMap;

    public FinancialTableScreen(List<Loan> loanList) {
        setTitle("All Loans");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        monthTotalMap = new TreeMap<>();
        createTable(loanList);
        double total = 0;
        for (int i = 0; i < loanList.size(); i++)
        {
            total = total + loanList.get(i).getTotalPrice();
        }
        createHeaderPanel(total);
        createButtonPanel();

        add(new JScrollPane(loansTable), BorderLayout.CENTER);
        pack();
        setSize(800, 700);
        setLocationRelativeTo(null);
    }

    private void createTable(List<Loan> loanList) {
        model = new DefaultTableModel();
        model.addColumn("Return Date");
        model.addColumn("Student ID");
        model.addColumn("Student Name");
        model.addColumn("Loan Number");
        model.addColumn("Payment");

        for (Loan loan : loanList) {
            String date;
            String price;

            if (loan.getReturnDate() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                String loanString = loan.getReturnDate().format(formatter);
                date = loanString;
                price = Double.toString(loan.getTotalPrice());
            } else {
                date = "TBD";
                price = "TBD";
            }

            model.addRow(new Object[] {
                    date,
                    loan.getStudent().getBroncoId(),
                    loan.getStudent().getName(),
                    loan.getNumber(),
                    price
            });

            // Calculate monthly total
            Month month = loan.getReturnDate() != null ? loan.getReturnDate().getMonth() : null;
            if (month != null) {
                double total = monthTotalMap.getOrDefault(month, 0.0);
                total += loan.getTotalPrice();
                monthTotalMap.put(month, total);
            }
        }

        loansTable = new JTable(model);
        loansTable.setPreferredScrollableViewportSize(new Dimension(800, 500));
        loansTable.setFillsViewportHeight(true);

        // Set custom table header renderer
        JTableHeader header = loansTable.getTableHeader();
        header.setDefaultRenderer(new HeaderRenderer());
    }

    private void createHeaderPanel(double total) {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(240, 240, 240));

        // Month selection dropdown
        JLabel monthLabel = new JLabel("Month:");
        monthComboBox = new JComboBox<>(getMonths());
        monthComboBox.addActionListener(e -> updateTable());

        // Total amount label
        totalLabel = new JLabel("Total: $" + total);

        JPanel innerHeaderPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        innerHeaderPanel.add(monthLabel);
        innerHeaderPanel.add(monthComboBox);
        innerHeaderPanel.add(totalLabel);

        headerPanel.add(innerHeaderPanel, BorderLayout.CENTER);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(headerPanel, BorderLayout.NORTH);
    }

    private String[] getMonths() {
        String[] months = new String[13];
        months[0] = "All";
        for (int i = 1; i <= 12; i++) {
            months[i] = Month.of(i).toString();
        }
        return months;
    }

    private void updateTable() {
        String selectedMonth = (String) monthComboBox.getSelectedItem();
        double total = 0.0;

        if (selectedMonth.equals("All")) {
            loansTable.setRowSorter(null); // Reset table sorting
            total = calculateTotalPayment();
        } else {
            Month month = Month.valueOf(selectedMonth);
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
            loansTable.setRowSorter(sorter);
            RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
                @Override
                public boolean include(Entry<? extends Object, ? extends Object> entry) {
                    int row = (int) entry.getIdentifier();
                    String returnDate = (String) model.getValueAt(row, 0);
                    if (!returnDate.equals("TBD")) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                        LocalDate loanDate = LocalDate.parse(returnDate, formatter);
                        return loanDate.getMonth() == month;
                    }
                    return false;
                }
            };
            sorter.setRowFilter(filter);
            total = monthTotalMap.getOrDefault(month, 0.0);
        }

        totalLabel.setText(String.format("Total: $%.2f", total));
    }

    private double calculateTotalPayment() {
        double total = 0.0;
        for (int i = 0; i < model.getRowCount(); i++) {
            total += Double.parseDouble(model.getValueAt(i, 4).toString());
        }
        return total;
    }

    private void createButtonPanel() {
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            setVisible(false);
            ButtonScreen buttonScreen = new ButtonScreen();
            buttonScreen.setVisible(true);
        });

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBackground(new Color(240, 240, 240));
        buttonPanel.setPreferredSize(new Dimension(800, 50));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.add(backButton, BorderLayout.WEST);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static String convertMonthsToNumbers(String capitalizedMonths) {
        StringBuilder numbers = new StringBuilder();
        String[] months = capitalizedMonths.split("\\s+");

        for (String month : months) {
            try {
                Month m = Month.valueOf(month.toUpperCase());
                int monthNumber = m.getValue();
                numbers.append(monthNumber).append(" ");
            } catch (IllegalArgumentException e) {
                // Month not found, ignore or handle accordingly
            }
        }

        return numbers.toString().trim();
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