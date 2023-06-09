package cs_3560_project.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.SwingUtilities;

public class App extends Application {

  public static void main(String args[]) {
    SwingUtilities.invokeLater(() -> {
      ButtonScreen buttonScreen = new ButtonScreen();
      buttonScreen.setVisible(true);
    });
  }

  @Override
  public void start(Stage stage) {
    String javaVersion = System.getProperty("java.version");
    String javafxVersion = System.getProperty("javafx.version");
    Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
    Scene scene = new Scene(new StackPane(l), 640, 480);
    stage.setScene(scene);
    stage.show();
  }

  public String getGreeting() {
    return "Hello World!";
  }
}
