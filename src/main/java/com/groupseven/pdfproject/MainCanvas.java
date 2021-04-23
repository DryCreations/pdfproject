
package com.groupseven.pdfproject;

import com.groupseven.pdfproject.model.Action;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

import java.util.Stack;

///
/// @author Cassandra Mae
/// 
/// \defgroup canvas
/// @{ 
/// \brief This is the main canvas which show PDF page and allow to draw elements on it and select the drawn element.
///    
/// \ref 18_1 "Task 18.1" 
/// To accomplish this task it is required to have a canvas which can keep the track of drawn elements to facilitate the selection of an element.
///

/// \brief a class to represent our canvas that will be able to be drawn on by the user, and represent an existing  pdf
/// \ref t_14_1 "task 14.1"
public class MainCanvas extends Pane {

    protected Canvas canvas;
    private final Stack<Action> _undoStack;
    private Stack<Action> _redoStack;

    /// \brief creates an empty canvas with the specified width and height
    ///
    /// \ref t18_1 "task 18.1"
    /// \ref t8_1 "task 8.1"
    /// \ref t14_1 "task 14.1"
    public MainCanvas(double width, double height) {
        _undoStack = new Stack<>();
        _redoStack = new Stack<>();
        canvas = new Canvas(width, height);
        getChildren().add(canvas);
    }

    /// \brief returns a reference to the canvas element representing the pdf
    /// \return Canvas
    ///
    /// \ref t18_1 "task 18.1"
    /// \ref t14_1 "task 14.1"
    public Canvas getCanvas() {
        return this.canvas;
    }

    /// \brief sets the event handler for the canvas to allow for multiple tools to be swapped in and out
    ///
    /// \ref t18_1 "task 18.1"
    public void setHandlerForTypes(EventHandler handler, EventType... eventTypes) {
        for (EventType type : eventTypes)
            canvas.addEventHandler(type, handler);
    }

    /// \brief undoes last action that is on the undo stack
    ///
    /// \ref t10_3 "task 10.3"
    public void undo() {
        if (_undoStack.empty())
            return;

        _redoStack.push(_undoStack.pop());
        refresh();
    }

    /// \brief The canvas is updated every time the shape is selected or deselected
    /// or whenever the backspace or delete key is pressed to remove the selected shape.
    ///
    /// \ref t18_1 "task 18.1"
    public void refresh() {
        clearScreen();
        _undoStack.forEach(Action::execute);
    }

    /// \brief redoes last action that is on the redo stack
    ///
    /// \ref t10_3 "task 10.3"
    public void redo() {
        if (_redoStack.empty())
            return;

        Action redo = _redoStack.pop();
        _undoStack.push(redo);
        refresh();
    }

    /// \brief clears the redo stack when an action happens
    ///
    /// \ref t10_3 "task 10.3"
    public void clearRedo() {
        _redoStack = new Stack<>();
    }

    /// \brief clears the canvas leaving a fresh new white background
    /// \ref t18_1 "task 18.1"
    public void clearScreen() {
        Canvas canvas = getCanvas();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    /// \brief returns current undo stack
    /// \return Stack<Action>
    ///
    /// \ref t10_3 "task 10.3"
    public Stack<Action> getUndoStack() {
        return _undoStack;
    }

    /// \brief returns current undo stack
    /// \return Stack<Action>
    ///
    /// \ref t10_3 "task 10.3"
    public Stack<Action> getRedoStack() {
        return _redoStack;
    }
}

/// @}
