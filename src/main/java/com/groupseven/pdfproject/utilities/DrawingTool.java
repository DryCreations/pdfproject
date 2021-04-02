package com.groupseven.pdfproject.utilities;

import com.groupseven.pdfproject.MainCanvas;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class DrawingTool implements EventHandler<MouseEvent> {

    private DrawingMode _mode;
    private final MainCanvas _canvas;
    private Stack<Pair<BiConsumer,?>> _subActions;
    private double _xStart;
    private double _xEnd;
    private double _yStart;
    private double _yEnd;
    private double _fillWidth;
    private double _fillHeight;
    private boolean _shapeStarted;

    public DrawingTool(MainCanvas canvas) {
        _canvas = canvas;
        _subActions = new Stack<>();
        _xStart = -1;
        _yStart = -1;
        _fillWidth = 5;
        _fillHeight = 5;
        _shapeStarted = false;
    }

    public void setDrawingMode(DrawingMode drawingMode) {
        reset();
        _mode = drawingMode;
    }

    private void reset() {
        _xStart = -1;
        _yStart = -1;
        _subActions = new Stack<>();
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED && _mode == DrawingMode.SELECT)
            handleSelect(mouseEvent);

        if (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED) {
            addCombinedSubActionsToUndo();
            _shapeStarted = false;
            return;
        }

        if (_mode == DrawingMode.RECTANGLE) {
            handleRectangle(mouseEvent);
            return;
        }

        if (mouseEvent.getEventType() != MouseEvent.MOUSE_DRAGGED)
            return;

        if (_xStart >= 0) {
            _xEnd = mouseEvent.getX();
            _yEnd = mouseEvent.getY();
            Object methodArgs = getMethodArgs();

            if (methodArgs != null)
                performActionAndPushOntoUndoStack(methodArgs);
            _canvas.clearRedo();
        }

        _xStart = mouseEvent.getX();
        _yStart = mouseEvent.getY();
    }

    private void handleRectangle(MouseEvent event) {

        if (event.getEventType() != MouseEvent.MOUSE_DRAGGED)
            return;

        if (_xStart >= 0) {
            _xEnd = event.getX();
            _yEnd = event.getY();

            Object methodArgs = getMethodArgs();

            if (methodArgs != null) {
                if (_shapeStarted )
                    _canvas.undo();
                else
                    _shapeStarted = true;
                addActionToUndo(methodArgs);
            }
            _canvas.clearRedo();
        }
        else {
            _xStart = event.getX();
            _yStart = event.getY();
        }
    }

    private boolean shapeContainsPoint(Object shape) {
        return ((Rectangle) shape).contains(new Point2D(_xEnd, _yEnd));
    }


    private void handleSelect(MouseEvent mouseEvent) {
        _xEnd = mouseEvent.getX();
        _yEnd = mouseEvent.getY();

        Stack<Pair<Consumer, ?>> undos = _canvas.getUndoStack();
        while (!undos.empty() && !shapeContainsPoint(undos.peek().getValue())) {
            _canvas.undo();
        }

        Stack<Pair<Consumer, ?>> redos = _canvas.getRedoStack();



        // undo until this point
        // wrap the object
        // redo everything

    }

    private <T> void addActionToUndo(T t) {
        Consumer<T> action = consumable -> _mode.getAction()
                .accept(_canvas, consumable);

        _canvas.addAction(new Pair<>(action, t));
        _mode.getAction().accept(_canvas, t);
    }

    private Object getMethodArgs() {
        return _mode == DrawingMode.PEN ?
                new Line(_xStart, _yStart, _xEnd, _yEnd)
                : _mode == DrawingMode.ERASER ?
                new Rectangle(_xStart, _yStart, _fillWidth, _fillHeight)
                : _mode == DrawingMode.RECTANGLE ?
                new Rectangle(_xStart, _yStart, (_xEnd - _xStart), (_yEnd - _yStart))
                : null;
    }

    private void addCombinedSubActionsToUndo() {
        Consumer<List<Pair<BiConsumer,?>>> aggregateAction =  actions -> {
            actions.forEach(
                    action -> action.getKey().accept(
                            _canvas,
                            action.getValue()));
        };

        _canvas.addAction(new Pair<>(aggregateAction, new ArrayList(_subActions)));
        reset();
    }

    private <T> void performActionAndPushOntoUndoStack(T t) {
        _subActions.push(new Pair<>(_mode.getAction(), t));
        _mode.getAction().accept(_canvas, t);
    }
}
