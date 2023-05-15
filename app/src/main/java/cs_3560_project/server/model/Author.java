package cs_3560_project.server.model;

import java.util.ArrayList;
import java.util.List;
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

  @ManyToMany
  @JoinTable(name = "author_book", joinColumns = @JoinColumn(name = "author_id"), inverseJoinColumns = @JoinColumn(name = "item_code"))
  private List<Book> books;

  public Author() {
    this.books = new ArrayList<>();
  }

  public Author(String name, int id, String nationality, String subject) {
    super(name, id, nationality);
    this.subject = subject;
    this.books = new ArrayList<>();
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public void addBook(Book book) {
    this.books.add(book);
  }

  public void removeBook(Book book) {
    this.books.remove(this.books.indexOf(book));
  }

  // TODO: createBook()

  // TODO: listAllBooks()

}
