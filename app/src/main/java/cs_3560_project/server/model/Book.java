package cs_3560_project.server.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "author_book", joinColumns = @JoinColumn(name = "item_code"),
      inverseJoinColumns = @JoinColumn(name = "author_id"))
  private List<Author> authors;

  public Book() {
    this.authors = new ArrayList<>();
  }

  public Book(int code, String title, String description, String location, double dailyPrice,
      ItemStatus status, int pages, String publisher, LocalDate publicationDate,
      List<Author> authors) {
    super(code, title, description, location, dailyPrice, status);
    this.pages = pages;
    this.publisher = publisher;
    this.publicationDate = publicationDate;
    this.authors = authors;
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
}
