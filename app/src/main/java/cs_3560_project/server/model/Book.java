package cs_3560_project.server.model;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")
@PrimaryKeyJoinColumn(name = "item_code")
public class Book extends Item {

  @Column(name = "pages")
  private int pages;

  @Column(name = "publisher")
  private String publisher;

  @Column(name = "publication_date")
  private LocalDate publicationDate;

  @ManyToMany(mappedBy = "books", cascade = CascadeType.PERSIST)
  private List<Author> authors;

  public Book() {};

  public Book(int code, String title, String description, String location, double dailyPrice,
      ItemStatus status, int pages, String publisher, LocalDate publicationDate) {
    super(code, title, description, location, dailyPrice, status);
    this.pages = pages;
    this.publisher = publisher;
    this.publicationDate = publicationDate;
  }

  public int getPages() {
    return pages;
  }

  public String getPublisher() {
    return publisher;
  }

  public LocalDate getPublicationDate() {
    return publicationDate;
  }

  public List<Author> getAllAuthors() {
    return authors;
  }

  public void setPages(int pages) {
    this.pages = pages;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public void setPublicationDate(LocalDate publicationDate) {
    this.publicationDate = publicationDate;
  }

  public void setAllAuthors(List<Author> authors) {
    this.authors = authors;
  }

  // TODO: toString()
}
