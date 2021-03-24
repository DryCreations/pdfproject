package com.groupseven.pdfproject;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    
    DocumentModel doc;

    /// \ref t8_3 "task 8.3"
    private EventHandler handleImportAsset = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent e) {

        }
    };

    /// \ref t14_1 "task 14.2"
    private VBox createViewbox() {
        try {
            doc = new DocumentModel("src/main/resources/test_pdf.pdf");
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        PageModel page = doc.getPage(0);
        VBox vbox = new VBox(0);
        vbox.getChildren().add(page.getNode());
        return vbox;
    }

    /// \ref t14_2_1 "task 14.2.1"
    private Menu createFileMenu() {
        return null;
    }

    /// \ref t14_2_2 "task 14.2.2"
    private Menu createDrawingMenu() {
        return null;
    }

    /// \ref t14.2.3 "task 14.2.3"
    private Menu createHelpMenu() {
        return null;
    }

    /// \ref t14.2 "task 14.2"
    private VBox createMenuBar() {
        return null;
    }

    /// \ref t14.3 "task 14.3"
    private VBox createToolBox() {
        return null;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("PDF Project");
        Group root = new Group();
        VBox viewbox = createViewbox();
        root.getChildren().add(viewbox);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
