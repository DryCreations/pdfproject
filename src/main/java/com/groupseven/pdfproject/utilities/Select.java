package com.groupseven.pdfproject.utilities;

import com.groupseven.pdfproject.MainCanvas;
import com.groupseven.pdfproject.model.Action;
import com.groupseven.pdfproject.model.Draggable;
import com.groupseven.pdfproject.model.SelectState;
import com.groupseven.pdfproject.model.Selectable;
import javafx.event.Event;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author Charles Witherspoon
 * \brief This class represents an action to select an object on the canvas
 * \ref t18_1 "Task 18.1"
 */
public class Select implements Action {
    private MainCanvas _canvas;
    private Selectable _selectedDrawing;
    private SelectState _selectState;
    private Point2D _origin;

    public Select(MainCanvas canvas) {
        _canvas = canvas;
        _selectState = SelectState.UNSELECTED;
    }

    @Override
    public void execute() {
        if (_selectState == SelectState.SELECTED
                && _selectedDrawing instanceof Draggable
                && !((Draggable) _selectedDrawing).wasMoved())
            DrawingAction.SELECT.accept(_canvas, _selectedDrawing.getSelection());
    }

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

        return _selectState == SelectState.MOVED
                && _selectedDrawing instanceof Action ?
                (Action) _selectedDrawing
                : this;
    }

    @Override
    public boolean isComplete() {
        return _selectState == SelectState.MOVED;
    }

    @Override
    public boolean contains(Point2D point) {
        return ((Action) _selectedDrawing).contains(point);
    }

    private void handlePress(Point2D mousePosition) {
        Optional<Action> drawingAtMousePosition = getDrawingAtPoint(mousePosition);

        if (drawingAtMousePosition.isPresent()
                && drawingAtMousePosition.get() instanceof Draggable
                && ((Draggable) drawingAtMousePosition.get()).wasMoved())
            return;

        if (drawingAtMousePosition.isPresent()
                && (_selectState == SelectState.UNSELECTED
                || (_selectState == SelectState.SELECTED
                && drawingAtMousePosition.get() != _selectedDrawing)))
            select(drawingAtMousePosition.get());

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

        return drawings
                .stream()
                .filter(actionContains(mousePosition))
                .findFirst();
    }

    private Predicate<Action> actionContains(Point2D mousePosition) {
        return action -> action instanceof Selectable
                && ((Selectable) action)
                .getSelection()
                .contains(mousePosition);
    }

    private void select(Action drawing) {
        _selectedDrawing = (Selectable) drawing;
        _selectState = SelectState.SELECTED;
    }

    private void moveTo(Point2D mousePosition) {
        if (_selectedDrawing instanceof Draggable) {
            _selectedDrawing = (Selectable) ((Draggable) _selectedDrawing)
                    .shift(_origin, mousePosition);
        }

    }
}