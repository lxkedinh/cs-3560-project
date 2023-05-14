package cs_3560_project.server.model;

import java.util.LinkedList;
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
  private List<Documentary> documentaries;

  public Director() {};

  public Director(String name, int id, String nationality, String style) {
    super(name, id, nationality);
    this.style = style;
    documentaries = new LinkedList<>();
  }

  public String getStyle() {
    return style;
  }

  public void setStyle(String style) {
    this.style = style;
  }

  public void addDocumentary(Documentary documentary)
  {
    this.documentaries.add(documentary);
  }

  public void removeDocumentary(Documentary documentary)
  {
    this.documentaries.remove(this.documentaries.indexOf(documentary));
  }

  // TODO: createDocumentary()

  // TODO: displayAllDocumentaries()

}
