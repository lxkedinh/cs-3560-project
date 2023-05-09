package cs_3560_project.app;

import java.time.LocalDate;
import cs_3560_project.server.dao.DAO;
import cs_3560_project.server.dao.EntityNotFoundException;
import cs_3560_project.server.model.Book;
import cs_3560_project.server.model.Item;
import cs_3560_project.server.model.ItemStatus;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

  @Override
  public void start(Stage stage) {
    String javaVersion = System.getProperty("java.version");
    String javafxVersion = System.getProperty("javafx.version");
    Label l =
        new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
    Scene scene = new Scene(new StackPane(l), 640, 480);
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    // launch();

    Book book = new Book(0, "CS 3560 Textbook", "Object Oriented Programming", "Floor 1", 0.75,
        ItemStatus.Available, 350, "Pearson", LocalDate.now());
    DAO.create(book);

    try {
      Item fetchedBook = DAO.read(Item.class, 0);

    } catch (EntityNotFoundException e) {
      e.printStackTrace();
    }
  }

  public String getGreeting() {
    return "Hello World!";
  }
}
