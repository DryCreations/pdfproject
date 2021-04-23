package com.groupseven.pdfproject;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.groupseven.pdfproject.utilities.DrawingMode;
import com.groupseven.pdfproject.utilities.DrawingTool;
import com.groupseven.pdfproject.view.DrawingToolbar;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App extends Application {

    public static final int WINDOW_WIDTH = 700;
    public static final int WINDOW_HEIGHT = 790;

    private MainCanvas canvas;

    private DocumentModel doc;
    int currentPage;
    private Scene mainScene;
    private static DrawingToolbar _drawingToolBar;

    /// \ref t8_3 "task 8.3"
    private EventHandler handleImportAsset = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent e) {

        }
    };

    /// \brief creates a new instance of the Document Model to be displayed
    /// \return void
    ///
    /// \ref t14_1 "task 14.1"
    private void initializeDocument() {
        try {
            File file = new File("src/main/resources/test_pdf.pdf");
            doc = new DocumentModel(file);
            // doc = new DocumentModel();
            currentPage = 0;
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /// \brief creates a viewbox element containing a pdf document
    /// \return VBox displaying the pdf and canvas element
    ///
    /// \ref t14_1 "task 14.1"
    private VBox createViewbox(PageModel page) {
        VBox vbox = new VBox(0);
        vbox.setAlignment(Pos.BASELINE_RIGHT);
        vbox.getChildren().add(page.getNode());
        page.clear();

        return vbox;
    }

    /// \brief create file menu element
    /// \return Menu containing all relevant dropdown elements
    ///
    /// \ref t14_2_1 "task 14.2.1"
    private Menu createFileMenu() {
        Menu fileMenu = new Menu("File");

        MenuItem newDocument = new MenuItem("New Document");
        newDocument.setOnAction(e -> {
            DocumentModel newDoc = new DocumentModel();
            setDisplayDoc(newDoc, 0);
        });

        MenuItem openDocument = new MenuItem("Open Document");

        openDocument.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(null);

            try {
                DocumentModel newDoc = new DocumentModel(selectedFile);
                setDisplayDoc(newDoc, 0);
            } catch (IOException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        MenuItem saveDocument = new MenuItem("Save Document");

        /// ref t8_8 "task 8.8"
        saveDocument.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showSaveDialog(null);

            try {
                doc.export(selectedFile);
            } catch (IOException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        fileMenu.getItems().add(newDocument);
        fileMenu.getItems().add(openDocument);
        fileMenu.getItems().add(saveDocument);

        return fileMenu;
    }

    /// \brief create edit menu element
    /// \return Menu for editing functionalities
    ///
    /// \ref t19_1_3 "task 19.1.3"
    private Menu createEditMenu() {
        Menu editMenu = new Menu("Edit");

        MenuItem undoItem = new MenuItem("Undo");
        MenuItem redoItem = new MenuItem("Redo");

        undoItem.setOnAction(event -> {
            canvas.undo();
        });

        redoItem.setOnAction(event -> {
            canvas.redo();
        });

        editMenu.getItems().add(undoItem);
        editMenu.getItems().add(redoItem);

        return editMenu;
    }

    /// \brief create drawing menu element
    /// \return
    ///
    /// \ref t14_2_2 "task 14.2.2"
    /// \ref t9_1_1 "task 9.1.1"
    /// \ref t9_1_2 "task 9.1.2"
    private Menu createDrawingMenu() {
        Label drawLabel = new Label("Drawing");
        drawLabel.setOnMouseClicked(action -> {
            if (_drawingToolBar == null)
                _drawingToolBar = new DrawingToolbar(canvas);
            if (canvas.getChildren().contains(_drawingToolBar)) {
                canvas.getChildren().remove(_drawingToolBar);
            } else {
                canvas.getChildren().add(_drawingToolBar);

            }

        });

        Menu drawingMenu = new Menu();
        drawingMenu.setGraphic(drawLabel);
        return drawingMenu;
    }

    /// \brief create help menu element
    /// \return Menu containing all relevant dropdown elements
    ///
    /// \ref t14_2_3 "task 14.2.3"
    private Menu createHelpMenu() {
        Menu helpMenu = new Menu("Help");

        return helpMenu;
    }

    /// \brief create menu bar element
    /// \return Menu containing all relevant dropdown elements
    ///
    /// \ref t14.2 "task 14.2"
    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = createFileMenu();
        Menu editMenu = createEditMenu();
        Menu drawingMenu = createDrawingMenu();
        Menu helpMenu = createHelpMenu();

        menuBar.getMenus().addAll(fileMenu, editMenu, drawingMenu, helpMenu);

        return menuBar;
    }

    /// \brief Create Toolbox elements and populate with buttons
    /// \return GridPane element containing all relevant buttons
    ///
    /// \ref t14_3 "task 14.3"
    private GridPane createToolBox() {
        GridPane ToolWindow = new GridPane();

        Button doNothing = new Button();
        Button doNothing2 = new Button();

        ToolWindow.setMinWidth(300);
        ToolWindow.setAlignment(Pos.BASELINE_LEFT);
        ToolWindow.setStyle("-fx-background-color: Gray;");

        ToolWindow.getChildren().add(doNothing);
        ToolWindow.getChildren().add(doNothing2);

        return ToolWindow;
    }

    /// \brief create undo button element
    /// \return Button for activating undo feature
    ///
    /// \ref t10_1 "task 10.1"
    private Button createUndoButton() {
        Image undoimg = new Image("undoarrow.png");
        ImageView undoview = new ImageView(undoimg);

        Button undobutton = new Button();
        undobutton.setGraphic(undoview);
        undobutton.setOnAction(event -> canvas.undo());
        Tooltip undotip = new Tooltip("Undo");
        Tooltip.install(undobutton, undotip);
        return undobutton;
    }

    /// \brief create redo button element
    /// \return Button for activating redo feature
    ///
    /// \ref t10_1 "task 10.1"
    private Button createRedoButton() {
        Image redoimg = new Image("redoarrow.png");
        ImageView redoview = new ImageView(redoimg);

        Button redobutton = new Button();
        redobutton.setGraphic(redoview);
        redobutton.setOnAction(event -> canvas.redo());
        Tooltip redotip = new Tooltip("Redo");
        Tooltip.install(redobutton, redotip);
        return redobutton;
    }

    /// \brief create save document button element
    /// \return Button for activating save document feature
    ///
    /// \ref t19_1_1 "task 19.1.1"
    private Button createSaveDocumentButton() {
        Image savedocimg = new Image("savebutton.png");
        ImageView savedocview = new ImageView(savedocimg);

        Button savedocbutton = new Button();
        savedocbutton.setGraphic(savedocview);

        savedocbutton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showSaveDialog(null);

            try {
                doc.export(selectedFile);
            } catch (IOException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        Tooltip savedoctip = new Tooltip("Save Document");
        Tooltip.install(savedocbutton, savedoctip);

        savedocbutton.setPrefSize(22, 22);
        return savedocbutton;
    }

    /// \brief create new document button element
    /// \return Button for activating new document feature
    ///
    /// \ref t19_1_2 "task 19.1.2
    private Button createNewDocumentButton() {
        Image newdocimg = new Image("newbutton.png");
        ImageView newdocview = new ImageView(newdocimg);

        Button newdocbutton = new Button();
        newdocbutton.setGraphic(newdocview);

        newdocbutton.setOnAction(event -> {
            DocumentModel newDoc = new DocumentModel();
            setDisplayDoc(newDoc, 0);
        });

        Tooltip newdoctip = new Tooltip("New Document");
        Tooltip.install(newdocbutton, newdoctip);

        newdocbutton.setPrefSize(22, 22);
        return newdocbutton;
    }

    /// \brief starts javafx GUI
    ///
    /// \return void
    @Override
    public void start(Stage primaryStage) throws Exception {
        initializeDocument();
        primaryStage.setTitle("PDF Project");
        BorderPane root = new BorderPane();
        GridPane ToolBox = createToolBox();
        MenuBar menuBar = createMenuBar();
        Button undobutton = createUndoButton();
        Button redobutton = createRedoButton();
        Button savedocbutton = createSaveDocumentButton();
        Button newdocbutton = createNewDocumentButton();

        GridPane.setConstraints(savedocbutton, 0, 0);
        GridPane.setConstraints(newdocbutton, 1, 0);
        GridPane.setConstraints(undobutton, 2, 0);
        GridPane.setConstraints(redobutton, 3, 0);

        root.setTop(menuBar);
        root.setLeft(ToolBox);

        ToolBox.getChildren().addAll(savedocbutton, newdocbutton, undobutton, redobutton);

        mainScene = new Scene(root);
        primaryStage.setScene(mainScene);

        setDisplayDoc(doc, 0);

        primaryStage.show();

        canvas = doc.getPage(currentPage).getCanvas();
    }

    /// \brief Sets up the pdf canvas to display as the center of the javafx GUI
    public void setDisplayDoc(DocumentModel document, int pageNum) {
        /// preconditions
        assert (document != null);
        assert (pageNum >= 0);
        currentPage = 0;
        doc = document;

        /// Actual implementation of method
        PageModel page = document.getPage(pageNum);
        BorderPane root = (BorderPane) mainScene.getRoot();

        canvas = page.getCanvas();
        root.setCenter(createViewbox(page));
        /// End of Actual implementation of method

        /// post-conditions
        assert (doc.equals(document)); // document used is not changed
    }

    /// \brief main method of project
    public static void main(String[] args) {
        launch(args);
    }
}
