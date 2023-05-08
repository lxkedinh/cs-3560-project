package cs_3560_project.server.model;

import java.time.LocalDate;
import jakarta.persistence.CascadeType;
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

  public Student getStudent() {
    return student;
  }

  public Item getItem() {
    return item;
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

  public void setStudent(Student student) {
    this.student = student;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  // printReceipt()

  // returnItem()

  public double calculateTotalPrice() {
    if (!isOverDue()) {
      return 0.0;
    }

    LocalDate d = LocalDate.now();
    return .1 * item.getDailyPrice() + (d.compareTo(dueDate) * item.getDailyPrice());
  }

  public boolean isOverDue() {
    return LocalDate.now().compareTo(dueDate) > 0;
  }
}
