package com.groupseven.pdfproject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
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
    private VBox createViewbox() {
        return null;
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
    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        
        Menu fileMenu = createFileMenu();
        Menu drawingMenu = createDrawingMenu();
        Menu helpMenu = createHelpMenu();
        
        menuBar.getMenus().addAll(fileMenu, drawingMenu, helpMenu);
        
        return menuBar;
    }

    /// \ref t14.3 "task 14.3"
    private VBox createToolBox() {
        return null;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BarChart<String, Number> chart = new BarChart<>(new CategoryAxis(), new NumberAxis());
        Random rng = new Random();
        Series<String, Number> series = new Series<>();
        series.setName("Data");
        for (int i = 1 ; i<=10; i++) {
            series.getData().add(new Data<>("Group "+i, rng.nextDouble()));
        }
        chart.getData().add(series);

        Button save = new Button("Save to pdf");
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new ExtensionFilter("PDF files", "*.pdf"));
        save.setOnAction(e -> {
            File file = chooser.showSaveDialog(primaryStage);
            if (file != null) {
                try {
                    Image img = chart.snapshot(null, null);
                    ImageData imgData = ImageDataFactory.create(SwingFXUtils.fromFXImage(img, null), null);
                    com.itextpdf.layout.element.Image pdfImg = new com.itextpdf.layout.element.Image(imgData);

                    PdfWriter writer = new PdfWriter(new FileOutputStream(file));
                    PdfDocument pdfDoc = new PdfDocument(writer);
                    Document doc = new Document(pdfDoc);
                    doc.add(pdfImg);
                    doc.close();
                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            }
        });

        BorderPane.setAlignment(save, Pos.CENTER);
        BorderPane.setMargin(save, new Insets(10));
        BorderPane root = new BorderPane(chart, null, null, save, null);

        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
