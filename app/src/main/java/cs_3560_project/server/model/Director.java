package cs_3560_project.server.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "director")
public class Director extends Creator {

  @Column(name = "style")
  private String style;

  @OneToMany(mappedBy = "director", cascade = CascadeType.PERSIST)
  private List<Documentary> documentaries = new ArrayList<>();

  public Director() {};

  public Director(String name, String nationality, String style) {
    super(name, nationality);
    this.style = style;
  }

  public String getStyle() {
    return style;
  }

  public void setStyle(String style) {
    this.style = style;
  }

  // Adds a Documentary to the ArrayList of documentaries
  public void addDocumentary(Documentary documentary) {
    this.documentaries.add(documentary);
  }

  // displayAllDocumentaries()

  @Override
  public String toString() {
    return "ID: " + super.getId() + "\n" +
        "Name: " + super.getName() + "\n" +
        "Nationality: " + super.getNationality() + "\n" +
        "Subject: " + this.style + "\n";
  }
}
