package cs_3560_project.server.model;

import java.time.LocalDate;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "documentary")
@PrimaryKeyJoinColumn(name = "item_code")
public class Documentary extends Item {

  @Column(name = "length")
  private double length;

  @Column(name = "release_date")
  private LocalDate releaseDate;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "director_id")
  private Director director;

  public Documentary() {};

  public Documentary(int code, String title, String description, String location, double dailyPrice,
      ItemStatus status, double length, LocalDate releaseDate, Director director) {
    super(code, title, description, location, dailyPrice, status);
    this.length = length;
    this.releaseDate = releaseDate;
    this.director = director;
  }

  public double getLength() {
    return length;
  }

  public LocalDate getReleaseDate() {
    return releaseDate;
  }

  public Director getDirector() {
    return director;
  }

  public void setLength(double length) {
    this.length = length;
  }

  public void setReleaseDate(LocalDate releaseDate) {
    this.releaseDate = releaseDate;
  }

  public void setDirector(Director director) {
    this.director = director;
  }
}
