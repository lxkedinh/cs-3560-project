package cs_3560_project.server.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student extends Person {

  @Id
  @Column(name = "bronco_id")
  private int broncoId;

  @Column(name = "course")
  private String course;

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

  public void setBroncoId(int broncoId) {
    this.broncoId = broncoId;
  }

  public void setCourse(String course) {
    this.course = course;
  }
}
