package cs_3560_project.server;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

enum ItemStatus {
  Borrowed, Available
}

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "item")
public class Item {

  @Id
  @Column(name = "code")
  private int code;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "location")
  private String location;

  @Column(name = "daily_price")
  private double dailyPrice;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private ItemStatus status;

  @OneToOne(mappedBy = "item", cascade = CascadeType.PERSIST)
  @JoinColumn(name = "loan_number")
  private Loan loan;

  public Item() {};

  public Item(int code, String title, String description, String location, double dailyPrice,
      ItemStatus status) {
    this.code = code;
    this.title = title;
    this.description = description;
    this.location = location;
    this.dailyPrice = dailyPrice;
    this.status = status;
  }

  public int getCode() {
    return code;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public String getLocation() {
    return location;
  }

  public double getDailyPrice() {
    return dailyPrice;
  }

  public ItemStatus getStatus() {
    return status;
  }

  public Loan getLoan() {
    return loan;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public void setDailyPrice(double dailyPrice) {
    this.dailyPrice = dailyPrice;
  }

  public void setStatus(ItemStatus status) {
    this.status = status;
  }

  public void setLoan(Loan loan) {
    this.loan = loan;
  }

  // toString()
}
