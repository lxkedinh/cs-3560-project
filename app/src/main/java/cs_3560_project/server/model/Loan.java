package cs_3560_project.server.model;

import java.time.LocalDate;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@FilterDef(name = "overdueFilter")
@Filter(name = "overdueFilter", condition = "is_overdue = TRUE")

@Entity
@Table(name = "loan")
public class Loan {

  @Id
  @Column(name = "number")
  private int number;

  @Column(name = "loan_date")
  private LocalDate loanDate;

  @Column(name = "due_date")
  private LocalDate dueDate;

  @Column(name = "return_date")
  private LocalDate returnDate;

  @Column(name = "is_overdue")
  private boolean isOverdue = false;

  @Column(name = "total_price")
  private double totalPrice;

  @OneToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "student_id")
  private Student student;

  @OneToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "item_code")
  private Item item;

  public Loan() {};

  public Loan(int number, LocalDate loanDate, LocalDate dueDate, Student student, Item item) {
    this.number = number;
    this.loanDate = loanDate;
    this.dueDate = dueDate;
    this.student = student;
    this.item = item;
  }

  public int getNumber() {
    return number;
  }

  public LocalDate getLoanDate() {
    return loanDate;
  }

  public LocalDate getDueDate() {
    return dueDate;
  }

  public LocalDate getReturnDate() {
    return returnDate;
  }

  public Student getStudent() {
    return student;
  }

  public Item getItem() {
    return item;
  }

  public double getTotalPrice() {
    return totalPrice;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public void setLoanDate(LocalDate loanDate) {
    this.loanDate = loanDate;
  }

  public void setDueDate(LocalDate dueDate) {
    this.dueDate = dueDate;
  }

  public void setReturnDate(LocalDate returnDate) {
    this.returnDate = returnDate;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  // TODO: printReceipt()

  public void calculateTotalPrice() {
    LocalDate d = LocalDate.now();
    if (!isOverDue()) {
      int numDaysLoaned = d.compareTo(loanDate);
      double onTimePrice = numDaysLoaned * item.getDailyPrice();
      totalPrice = onTimePrice;
    }

    int loanPeriod = dueDate.compareTo(loanDate);
    double priceBeforeLateFee = loanPeriod * item.getDailyPrice();
    int daysOverdue = d.compareTo(dueDate);

    double lateFee = .1 * item.getDailyPrice() + (daysOverdue * item.getDailyPrice());

    totalPrice = priceBeforeLateFee + lateFee;
  }

  public boolean isOverDue() {
    isOverdue = LocalDate.now().compareTo(dueDate) > 0;
    return isOverdue;
  }
}
