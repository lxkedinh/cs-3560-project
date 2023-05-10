package cs_3560_project.server.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Creator extends Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "nationality")
  private String nationality;

  public Creator() {};

  public Creator(String name, String nationality) {
    super(name);
    this.nationality = nationality;
  }

  public int getId() {
    return id;
  }

  public String getNationality() {
    return nationality;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setNationality(String nationality) {
    this.nationality = nationality;
  }
}
