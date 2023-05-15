package cs_3560_project.server.model;

import java.time.LocalDate;
import cs_3560_project.server.dao.EntityNotFoundException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


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

  @Column(name = "total_price")
  private double totalPrice;

  @OneToOne
  @JoinColumn(name = "student_id")
  private Student student;

  @OneToOne
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

  public void calculateTotalPrice() throws EntityNotFoundException {
    if (!isOverDue()) {
      totalPrice = 0;
      return;
    }

    LocalDate d = LocalDate.now();
    int daysOverdue = d.compareTo(dueDate);

    totalPrice = 1.1 * daysOverdue * item.getDailyPrice();
  }

  public boolean isOverDue() {
    return LocalDate.now().compareTo(dueDate) > 0;
  }
}
