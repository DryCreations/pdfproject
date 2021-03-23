package com.groupseven.pdfproject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import javafx.scene.canvas.Canvas;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class App extends Application {

    /// \ref t8_3 "task 8.3"
    private EventHandler handleImportAsset = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent e) {

        }
    };

    /// \ref t14_1 "task 14.2"
    private VBox createViewbox(int width, int height) {
        Canvas canvas = new Canvas(width, height);
        
        VBox vbox = new VBox(0);
        
        vbox.getChildren().add(canvas);
        
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
        VBox viewbox = createViewbox(300, 300);
        root.getChildren().add(viewbox);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
