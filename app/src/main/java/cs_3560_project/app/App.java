package cs_3560_project.app;

import cs_3560_project.server.InventoryScreen;
import cs_3560_project.server.ItemScreen;
import cs_3560_project.server.LoanScreen;
import cs_3560_project.server.Main;
import cs_3560_project.server.MainJFrame;
import cs_3560_project.server.MenuScreen;
import cs_3560_project.server.StudentScreen;
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
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try { 
                    MenuScreen menuScreen = new cs_3560_project.server.MenuScreen();
                    LoanScreen loanScreen = new cs_3560_project.server.LoanScreen();
                    InventoryScreen inventoryScreen = new cs_3560_project.server.InventoryScreen();
                    StudentScreen studentScreen = new cs_3560_project.server.StudentScreen();
                    ItemScreen itemScreen =  new cs_3560_project.server.ItemScreen();
                    
                    w = new MainJFrame(menuScreen, 
                            loanScreen, 
                            inventoryScreen,
                            studentScreen,
                            itemScreen
                    );
                    w.setLocationRelativeTo(null);
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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

    /**public static void main(String[] args) {
        launch();
    }**/

    public String getGreeting() {
        return "Hello World!";
    }
}
