package com.groupseven.pdfproject.utilities;

import com.groupseven.pdfproject.MainCanvas;
import com.groupseven.pdfproject.model.Action;
import com.groupseven.pdfproject.model.Draggable;
import com.groupseven.pdfproject.model.Selectable;
import com.groupseven.pdfproject.utilities.DrawingAction;
import javafx.event.Event;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Charles Witherspoon
 * 
 * @{ \brief This class represents an action to select an object on the canvas \ref t18_1 "Task 18.1"
 */
public class Select implements Action {
    private MainCanvas _canvas;
    Selectable _selectedDrawing;

    public Select(MainCanvas canvas) {
        _canvas = canvas;
    }

    @Override
    public void execute() {
        if (_selectedDrawing != null) {
            _selectedDrawing.select();
            DrawingAction.SELECT.accept(_canvas, _selectedDrawing.getSelection());
        }
    }

    @Override
    public Action handle(Event event) {
        if (!(event instanceof MouseEvent))
            return this;

        MouseEvent mouseEvent = (MouseEvent) event;

        switch (mouseEvent.getEventType().getName()) {
        case ("MOUSE_PRESSED"):
            handlePress(mouseEvent);
            break;
        case ("MOUSE_DRAGGED"):
            handleDrag(mouseEvent);
            break;
        case ("MOUSE_RELEASED"):
            handleRelease(mouseEvent);
            break;
        }

        return this;
    }

    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public boolean contains(Point2D point) {
        return ((Action) _selectedDrawing).contains(point);
    }

    private void handlePress(MouseEvent mouseEvent) {
        if (_selectedDrawing != null)
            _selectedDrawing.unselect();

        List<Action> undos = new ArrayList<>(_canvas.getUndoStack());
        Collections.reverse(undos);
        Point2D mousePosition = new Point2D(mouseEvent.getX(), mouseEvent.getY());
        Optional<Action> firstActionContainingMousePosition = undos.stream()
                .filter(action -> action.contains(mousePosition) && action instanceof Selectable).findFirst();

        Selectable previousSelection = _selectedDrawing;
        _selectedDrawing = (Selectable) firstActionContainingMousePosition.orElse(null);

        if (previousSelection != null && previousSelection.equals(_selectedDrawing))
            _selectedDrawing.unselect();
    }

    private void handleDrag(MouseEvent mouseEvent) {
        if (_selectedDrawing != null && _selectedDrawing instanceof Draggable) {
            Action movedRectangle = ((Draggable) _selectedDrawing).dragTo(mouseEvent.getX(), mouseEvent.getY());
            _canvas.getUndoStack().push(movedRectangle);
            _selectedDrawing = (Selectable) movedRectangle;
        }
    }

    private void handleRelease(MouseEvent mouseEvent) {

    }
}
/**
 * @}
 */