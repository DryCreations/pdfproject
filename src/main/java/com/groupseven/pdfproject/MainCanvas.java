
package com.groupseven.pdfproject;

import com.groupseven.pdfproject.model.Action;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;

import java.util.Stack;

/**
 * @author Cassandra Mae \defgroup canvas
 * 
 * @{ \brief This is the main canvas which show PDF page and allow to draw elements on it and select the drawn element.
 *    \ref 18_1 "Task 18.1" To accomplish this task it is required to have a canvas which can keep the track of drawn
 *    elements to facilitate the selection of an element.
 */

public class MainCanvas extends Pane {

    protected Canvas canvas;
    private final Stack<Action> _undoStack;
    private Stack<Action> _redoStack;

    public MainCanvas(double width, double height) {
        _undoStack = new Stack<>();
        _redoStack = new Stack<>();
        canvas = new Canvas(width, height);
        getChildren().add(canvas);
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public void setHandlerForTypes(EventHandler handler, EventType... eventTypes) {
        for (EventType type : eventTypes)
            canvas.addEventHandler(type, handler);
    }

    public void undo() {
        if (_undoStack.empty())
            return;
        _redoStack.push(_undoStack.pop());
        refresh();
    }

    /// \brief The canvas is updated every time the shape is selected or deselected
    /// or whenever the backspace or delete key is pressed to remove the selected shape.
    /// \ref t18_1 "task 18.1"
    public void refresh() {
        clearScreen();
        _undoStack.forEach(Action::execute);
    }

    public void redo() {
        if (_redoStack.empty())
            return;

        Action redo = _redoStack.pop();
        _undoStack.push(redo);
        redo.execute();
    }

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

    public Stack<Action> getUndoStack() {
        return _undoStack;
    }
}

/** @} */
