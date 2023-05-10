package cs_3560_project.app;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.SwingUtilities;

public class App extends Application {

  public static MainJFrame w;

  public static void main(String args[]) {
    // /* Set the Nimbus look and feel */
    // // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
    // // (optional) ">
    // /*
    //  * If Nimbus (introduced in Java SE 6) is not available, stay with the default
    //  * look and feel.
    //  * For details see
    //  * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
    //  */
    // try {
    //   for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
    //     if ("Nimbus".equals(info.getName())) {
    //       javax.swing.UIManager.setLookAndFeel(info.getClassName());
    //       break;
    //     }
    //   }
    // } catch (ClassNotFoundException ex) {
    //   java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    // } catch (InstantiationException ex) {
    //   java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    // } catch (IllegalAccessException ex) {
    //   java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    // } catch (javax.swing.UnsupportedLookAndFeelException ex) {
    //   java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    // }
    // // </editor-fold>

    // /* Create and display the form */
    // SwingUtilities.invokeLater(new Runnable() {
    //   @Override
    //   public void run() {
    //     try {
    //       MenuScreen menuScreen = new MenuScreen();
    //       LoanScreen loanScreen = new LoanScreen();
    //       ItemScreen inventoryScreen = new ItemScreen();
    //       ManagementScreen managementScreen = new ManagementScreen();

    //       w = new MainJFrame(menuScreen,
    //           loanScreen,
    //           inventoryScreen,
    //           managementScreen);
    //       w.setLocationRelativeTo(null);

    //     } catch (InterruptedException ex) {
    //       Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
    //     }
    //   }
    // });

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

  /**
   * public static void main(String[] args) {
   * launch();
   * }
   **/

  public String getGreeting() {
    return "Hello World!";
  }
}
