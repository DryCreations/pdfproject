package com.groupseven.pdfproject.utilities;

import com.groupseven.pdfproject.MainCanvas;
import com.groupseven.pdfproject.model.Action;
import com.groupseven.pdfproject.model.Draggable;
import com.groupseven.pdfproject.model.SelectState;
import com.groupseven.pdfproject.model.Selectable;
import com.groupseven.pdfproject.utilities.DrawingAction;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import javafx.event.Event;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author Charles Witherspoon
 */

/// \brief This class represents an action to select an object on the canvas
/// \ref t18_1 "Task 18.1"
public class Select implements Action {
    private MainCanvas _canvas;
    private Selectable _selectedDrawing;
    private SelectState _selectState;
    private Point2D _origin;
    private ContextMenu contextMenu;

    /// \brief constructor of Select class
    ///
    /// \ref t18_1 "task 18.1"
    public Select(MainCanvas canvas) {
        _canvas = canvas;
        _selectState = SelectState.UNSELECTED;
    }

    /// \brief Executes user interaction with Select tool
    ///
    /// \ref t18_1 "task 18.1"
    @Override
    public void execute() {
        if (_selectState == SelectState.SELECTED && _selectedDrawing instanceof Draggable
                && !((Draggable) _selectedDrawing).wasMoved())
            DrawingAction.SELECT.accept(_canvas, _selectedDrawing.getSelection());
    }

    /***
     * The user is expected to right click the mouse and not drag the mouse after pressing down the right mouse button
     * to get option to link the shape. The user is also expected to put in the valid link/url in the dialog box
     * prompted for the link to be attached to the shape.
     */
    /// \brief Handles user interaction with GUI using select tool
    ///
    /// \ref t18_1 "task 18.1"
    @Override
    public Action handle(Event event) {
        if (!(event instanceof MouseEvent))
            return this;

        MouseEvent mouseEvent = (MouseEvent) event;
        Point2D mousePosition = new Point2D(mouseEvent.getX(), mouseEvent.getY());

        if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED)
            handlePress(mousePosition);

        else if (mouseEvent.getEventType() == MouseEvent.MOUSE_DRAGGED)
            handleDrag();

        else if (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED)
            handleRelease(mousePosition);

        /**
         * Only accepts right click and no mouse drag
         */
        if (mouseEvent.isSecondaryButtonDown()) {
            handleRightClick(mouseEvent);
        }
        /***
         * 
         * Takes to the linked web page only if "Control" key is pressed down while mouse clicked. User is expected to
         * provide a proper link of a web page
         */
        if (mouseEvent.isControlDown()) {
            _canvas.getScene().setCursor(Cursor.HAND);
            if (event.getEventType() == MouseEvent.MOUSE_PRESSED)
                handleOpeningLink(mouseEvent);
            else {
                _canvas.getScene().setCursor(Cursor.DEFAULT);
            }
        }

        return _selectState == SelectState.MOVED && _selectedDrawing instanceof Action ? (Action) _selectedDrawing
                : this;
    }

    /// \brief checks if user interaction is completed
    /// \return boolean
    ///
    /// \ref t18_1 "task 18.1"
    @Override
    public boolean isComplete() {
        return _selectState == SelectState.MOVED;
    }

    /// \brief checks if selected drawing contains given 2D point
    /// \return boolean
    ///
    /// \ref t18_1 "task 18.1"
    @Override
    public boolean contains(Point2D point) {
        return ((Action) _selectedDrawing).contains(point);
    }

    private void handlePress(Point2D mousePosition) {
        if (_selectState == SelectState.SELECTED) {
            try {
                if (contextMenu.isShowing()) {
                    contextMenu.hide();
                }
            } catch (Exception e) {
                // do nothing

            }
        }
        Optional<Action> drawingAtMousePosition = getDrawingAtPoint(mousePosition);

        if (drawingAtMousePosition.isPresent() && drawingAtMousePosition.get() instanceof Draggable
                && ((Draggable) drawingAtMousePosition.get()).wasMoved())
            return;

        if (drawingAtMousePosition.isPresent() && (_selectState == SelectState.UNSELECTED
                || (_selectState == SelectState.SELECTED && drawingAtMousePosition.get() != _selectedDrawing))) {
            select(drawingAtMousePosition.get());

            try {
                if (contextMenu.isShowing()) {
                    contextMenu.hide();
                }
            } catch (Exception e) {
                // do nothing
            }
        }

        else if (_selectState == SelectState.SELECTED) {
            _selectState = SelectState.UNSELECTED;
        }

        _origin = mousePosition;
    }

    private void handleDrag() {
        if (_selectState == SelectState.SELECTED)
            _selectState = SelectState.MOVING;
    }

    private void handleRelease(Point2D mousePosition) {
        if (_selectState == SelectState.MOVING) {
            moveTo(mousePosition);
            _selectState = SelectState.MOVED;
        }
    }

    private Optional<Action> getDrawingAtPoint(Point2D mousePosition) {
        List<Action> drawings = new ArrayList<>(_canvas.getUndoStack());
        Collections.reverse(drawings);

        return drawings.stream().filter(actionContains(mousePosition)).findFirst();
    }

    private Predicate<Action> actionContains(Point2D mousePosition) {
        return action -> action instanceof Selectable && ((Selectable) action).getSelection().contains(mousePosition);
    }

    private void select(Action drawing) {
        _selectedDrawing = (Selectable) drawing;
        _selectState = SelectState.SELECTED;
    }

    private void moveTo(Point2D mousePosition) {
        if (_selectedDrawing instanceof Draggable) {
            _selectedDrawing = (Selectable) ((Draggable) _selectedDrawing).shift(_origin, mousePosition);
        }

    }

    @Override
    public void pdfExecute(PdfCanvas canvas, PdfPage page) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose
        // Tools | Templates.

        System.out.println("Select");
    }

    /// If the object is already linked calling this method will throw an error.
    /// if the object is already linked this function will not allow to provide another link
    public void handleRightClick(Event event) {
        /// pre-conditions
        if (event instanceof MouseEvent)
            assert true;
        assert (!_selectedDrawing.getisLinked());
        Selectable _selectedDrawing_pre = _selectedDrawing;// Only to check the post-condition

        ///////////////////////////////////////////////////
        /// Actual implementation of code
        contextMenu = new ContextMenu();
        MenuItem linkObj = new MenuItem("Link Object");
        contextMenu.getItems().add(linkObj);
        contextMenu.show(_canvas, ((MouseEvent) event).getScreenX(), ((MouseEvent) event).getScreenY());

        linkObj.setOnAction(e -> {
            TextInputDialog dialogBox = new TextInputDialog("http://my%Link%here");
            dialogBox.setTitle("Link");
            dialogBox.setHeaderText("What is the desired Link ");
            dialogBox.setContentText("Link");

            Optional<String> linkProvided = dialogBox.showAndWait();
            if (linkProvided.isPresent()) {

                Hyperlink url = new Hyperlink();
                url.setText(linkProvided.get());
                _selectedDrawing.setisLinked(true);
                _selectedDrawing.setUri(url.getText());

            }
        });
        /// End of the actual implementation of the function
        //////////////////////////////////////////////////////

        /// post-conditions
        if (_selectedDrawing_pre.equals(_selectedDrawing))
            assert true;
        /// The selected object is not changed by calling this function.
    }

    public void handleOpeningLink(Event event) {

        /// pre-conditions
        if (event instanceof MouseEvent)
            assert true;
        assert (_selectedDrawing.getisLinked());
        /// Only used to check the post-condition
        String link_pre = _selectedDrawing.getLink();

        /// Actual Implementation of the function
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI(_selectedDrawing.getLink()));
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (URISyntaxException e1) {
                e1.printStackTrace();
            }
        }
        /// End of the actual implementation of the function

        /// Post-condition
        assert (link_pre.contentEquals(_selectedDrawing.getLink()));
        /// The original link provided was not changed
    }
}
