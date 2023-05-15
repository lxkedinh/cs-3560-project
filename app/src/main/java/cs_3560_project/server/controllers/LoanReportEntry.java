package cs_3560_project.server.controllers;

import java.time.LocalDate;

// utility class for entries in generated financial reports
public class LoanReportEntry {
    private int loanNumber;
    private LocalDate returnDate;
    private String studentName;
    private int broncoId;
    private double payment;
    private String itemTitle;
    private int itemCode;

    public LoanReportEntry(int loanNumber, LocalDate returnDate, String studentName, int broncoId,
            double payment, String itemTitle, int itemCode) {
        this.loanNumber = loanNumber;
        this.returnDate = returnDate;
        this.studentName = studentName;
        this.broncoId = broncoId;
        this.payment = payment;
        this.itemTitle = itemTitle;
        this.itemCode = itemCode;
    }

    public int getLoanNumber() {
        return loanNumber;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getBroncoId() {
        return broncoId;
    }

    public double getPayment() {
        return payment;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public int getItemCode() {
        return itemCode;
    }
}
