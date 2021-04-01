package com.groupseven.pdfproject.utilities;

import com.groupseven.pdfproject.MainCanvas;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class DrawingTool implements EventHandler<MouseEvent> {

    private DrawingMode _mode;
    private final MainCanvas _canvas;
    private Stack<Pair<BiConsumer,?>> _subActions;
    private double _x;
    private double _y;
    private double _fillWidth;
    private double _fillHeight;
    private boolean _shapeStarted;

    public DrawingTool(MainCanvas canvas) {
        _canvas = canvas;
        _subActions = new Stack<>();
        _x = -1;
        _y = -1;
        _fillWidth = 5;
        _fillHeight = 5;
        _shapeStarted = false;
    }

    public void setDrawingMode(DrawingMode drawingMode) {
        reset();
        _mode = drawingMode;
    }

    private void reset() {
        _x = -1;
        _y = -1;
        _subActions = new Stack<>();
    }

    @Override
    public void handle(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
            addCombinedSubActionsToUndo();
            _shapeStarted = false;
            return;
        }

        if (_mode == DrawingMode.RECTANGLE) {
            handleRectangle(event);
            return;
        }

        if (event.getEventType() != MouseEvent.MOUSE_DRAGGED)
            return;

        if (_x >= 0) {
            double xEnd = event.getX();
            double yEnd = event.getY();
            Object methodArgs = getMethodArgs(xEnd, yEnd);

            if (methodArgs != null)
                performActionAndPushOntoUndoStack(methodArgs);
            _canvas.clearRedo();
        }

        _x = event.getX();
        _y = event.getY();
    }

    private void handleRectangle(MouseEvent event) {

        if (event.getEventType() != MouseEvent.MOUSE_DRAGGED)
            return;

        if (_x >= 0) {
            Object methodArgs = getMethodArgs(event.getX(), event.getY());

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
            _x = event.getX();
            _y = event.getY();
        }
    }

    private <T> void addActionToUndo(T t) {
        Consumer<T> action = consumable -> _mode.getAction()
                .accept(_canvas, consumable);

        _canvas.addAction(new Pair<>(action, t));
        _mode.getAction().accept(_canvas, t);
    }

    private Object getMethodArgs(double xEnd, double yEnd) {
        return _mode == DrawingMode.PEN ?
                new Line(_x, _y, xEnd, yEnd)
                : _mode == DrawingMode.ERASER ?
                new Rectangle(xEnd, yEnd, _fillWidth, _fillHeight)
                : _mode == DrawingMode.RECTANGLE ?
                new Rectangle(_x, _y, (xEnd - _x), (yEnd - _y))
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
