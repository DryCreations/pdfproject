package com.groupseven.pdfproject.utilities;

import com.groupseven.pdfproject.MainCanvas;
import com.groupseven.pdfproject.model.Action;
import com.groupseven.pdfproject.model.Draggable;
import com.groupseven.pdfproject.model.SelectState;
import com.groupseven.pdfproject.model.Selectable;
import javafx.event.Event;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

import java.util.*;
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

    public Select(MainCanvas canvas) {
        _canvas = canvas;
        _selectState = SelectState.UNSELECTED;
    }

    @Override
    public void execute() {

        if (_selectState == SelectState.SELECTED)
            DrawingAction.SELECT.accept(_canvas, _selectedDrawing.getSelection());

        else if (_selectState == SelectState.MOVED && _selectedDrawing instanceof Action) {
            if (_canvas.getUndoStack().contains(this)) {
                _canvas.clearScreen();

                Stack<Action> undos = _canvas.getUndoStack();
                for (int i = 0; i < undos.size() - 1; i++) {

                    Action currentAction = undos.get(i);
                    if (currentAction != this)
                        currentAction.execute();
                }
            }
            ((Action) _selectedDrawing).execute();
        }
    }

    @Override
    public Action handle(Event event) {
        if (!(event instanceof MouseEvent))
            return this;

        MouseEvent mouseEvent = (MouseEvent) event;
        Point2D mousePosition = new Point2D(mouseEvent.getX(), mouseEvent.getY());

        Optional<Action> drawingAtMousePosition = getDrawingAtPoint(mousePosition);

        if (_selectState == SelectState.UNSELECTED && mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED && drawingAtMousePosition.isPresent()) {
            select(drawingAtMousePosition.get());
            _selectState = SelectState.SELECTED;
        } else if (_selectState == SelectState.SELECTED && mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED && !drawingAtMousePosition.isPresent()) {
            _selectState = SelectState.UNSELECTED;
        } else if (_selectState == SelectState.SELECTED && mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED && drawingAtMousePosition.isPresent() && drawingAtMousePosition.get() == _selectedDrawing) {
            _selectState = SelectState.UNSELECTED;
        } else if (_selectState == SelectState.SELECTED && mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED && drawingAtMousePosition.isPresent() && drawingAtMousePosition.get() != _selectedDrawing) {
            select(drawingAtMousePosition.get());
            _selectState = SelectState.SELECTED;
        } else if (_selectState == SelectState.SELECTED && mouseEvent.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            _selectState = SelectState.MOVING;
        } else if (_selectState == SelectState.MOVING && mouseEvent.getEventType() == MouseEvent.MOUSE_DRAGGED) {
//            System.out.println("MOVING\tMOUSE_DRAGGED");
        } else if (_selectState == SelectState.MOVING && mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED) {
            moveTo(mousePosition);
            _selectState = SelectState.MOVED;
        }

        return _selectState == SelectState.MOVED
                && _selectedDrawing instanceof Action ?
                (Action) _selectedDrawing
                : this;
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
    }

    private void moveTo(Point2D mousePosition) {
        if (_selectedDrawing instanceof Draggable) {
            _selectedDrawing = (Selectable) ((Draggable) _selectedDrawing)
                    .dragTo(mousePosition.getX(), mousePosition.getY());
            if (_selectedDrawing instanceof DrawRectangle)
                ((DrawRectangle) _selectedDrawing).complete();
        }

    }

    @Override
    public boolean isComplete() {
        return _selectState == SelectState.MOVED;
    }

    @Override
    public boolean contains(Point2D point) {
        return ((Action) _selectedDrawing).contains(point);
    }
}