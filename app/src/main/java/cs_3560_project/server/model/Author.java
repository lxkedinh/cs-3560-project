package cs_3560_project.server.model;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "author")
public class Author extends Creator {

  @Column(name = "subject")
  private String subject;

  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(name = "author_book", joinColumns = @JoinColumn(name = "author_id"),
      inverseJoinColumns = @JoinColumn(name = "item_code"))
  private List<Book> books;

  public Author() {};

  public Author(String name, String nationality, String subject) {
    super(name, nationality);
    this.subject = subject;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  // createBook()

  // listAllBooks()

}
