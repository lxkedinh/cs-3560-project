package cs_3560_project.server.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student extends Person {

  @Id
  @Column(name = "bronco_id")
  private int broncoId;

  @Column(name = "course")
  private String course;

  @OneToOne(mappedBy = "student", cascade = CascadeType.PERSIST)
  @JoinColumn(name = "loan_number")
  private Loan loan;

  public Student() {};

  public Student(String name, int broncoId, String course) {
    super(name);
    this.broncoId = broncoId;
    this.course = course;
  }

  public int getBroncoId() {
    return broncoId;
  }

  public String getCourse() {
    return course;
  }

  public Loan getLoan() {
    return loan;
  }

  public void setBroncoId(int broncoId) {
    this.broncoId = broncoId;
  }

  public void setCourse(String course) {
    this.course = course;
  }

  public void setLoan(Loan loan) {
    this.loan = loan;
  }

  @Override
  public String toString() {
    return "Name: " + super.getName() + "\n" +
        "Bronco ID: " + this.broncoId + "\n" +
        "Course: " + this.course + "\n";
  }
}
