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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class App extends Application {
    
    /// 14.1
    VBox createViewbox() {
        return null;
    }
    
    /// 14.2.1
    Menu createFileMenu() {
        return null;
    }
    
    /// 14.2.2
    Menu createDrawingMenu() {
        return null;
    }
    
    /// 14.2.3
    Menu createHelpMenu() {
        return null;
    }
    
    /// 14.2
    VBox createMenuBar() {
        return null;
    }
    
    /// 14.3
    VBox createToolBox() {
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
