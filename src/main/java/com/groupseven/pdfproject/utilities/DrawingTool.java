package com.groupseven.pdfproject.utilities;

import com.groupseven.pdfproject.MainCanvas;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class DrawingTool implements EventHandler<MouseEvent> {

    private DrawingMode _drawingMode;
    private final MainCanvas _canvas;
    private Stack<Pair<BiConsumer<MainCanvas, Shape>, Shape>> _subActions;
    private double _xStart;
    private double _xEnd;
    private double _yStart;
    private double _yEnd;
    private double _fillWidth;
    private double _fillHeight;
    private boolean _shapeStarted;
    private boolean _isSelected;
    private Shape _selectedShape;

    public DrawingTool(MainCanvas canvas) {
        _canvas = canvas;
        _subActions = new Stack<>();
        _xStart = -1;
        _yStart = -1;
        _fillWidth = 5;
        _fillHeight = 5;
        _shapeStarted = false;
        _isSelected = false;
        _selectedShape = null;
    }

    public void setDrawingMode(DrawingMode drawingMode) {
        reset();
        _drawingMode = drawingMode;
    }

    private void reset() {
        _xStart = -1;
        _yStart = -1;
        _subActions = new Stack<>();
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED && _drawingMode == DrawingMode.SELECT)
            handleSelect(mouseEvent);

        if (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED) {
            addCombinedSubActionsToUndo();
            _shapeStarted = false;
            return;
        }

        if (_drawingMode == DrawingMode.RECTANGLE) {
            handleRectangle(mouseEvent);
            return;
        }

        if (mouseEvent.getEventType() != MouseEvent.MOUSE_DRAGGED)
            return;

        if (_xStart >= 0) {
            _xEnd = mouseEvent.getX();
            _yEnd = mouseEvent.getY();
            Shape methodArgs = getMethodArgs();

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

            Shape methodArgs = getMethodArgs();

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

    private boolean pointIsContainedBy(Shape shape) {
        return shape != null && shape.contains(new Point2D(_xEnd, _yEnd));
    }


    private void handleSelect(MouseEvent mouseEvent) {
        boolean selectedShapeWasFound = false;
            Stack<Pair<Consumer<Shape>, Shape>> undos = _canvas.getUndoStack();
            Stack<Pair<Consumer<Shape>, Shape>> dirtyRedos = new Stack<>();

            Shape currentShape = undos.peek().getValue();
            boolean currentShapeIsAtMousePosition = currentShape.contains(new Point2D(mouseEvent.getX(), mouseEvent.getY()));
            while (currentShapeIsAtMousePosition == false && undos.empty() == false) {
                if (currentShape == _selectedShape) {
                    selectedShapeWasFound = true;
                } else {
                    dirtyRedos.push(undos.pop());
                }
                currentShape = undos.peek().getValue();
                currentShapeIsAtMousePosition = currentShape.contains(new Point2D(mouseEvent.getX(), mouseEvent.getY()));
                if (currentShapeIsAtMousePosition) {
                    if (currentShape != _selectedShape) {
//                        addActionToUndo(currentShape);
                        Consumer<Shape> action = consumable -> _drawingMode.executeAction(_canvas, consumable);
                        _canvas.addAction(new Pair<>(action, currentShape));
                        _isSelected = true;
                        _selectedShape = currentShape;
                    }

                }
            }

            while (selectedShapeWasFound == false && _isSelected) {
                if (currentShape == _selectedShape) {
                    selectedShapeWasFound = true;
                    _isSelected = false;
                    _selectedShape = null;
                }
                else {
                    dirtyRedos.push(undos.pop());
                }
            }

            while (dirtyRedos.empty() == false)
                undos.push(dirtyRedos.pop());

            _canvas.refresh();
    }

    private void addActionToUndo(Shape shape) {
        Consumer<Shape> action = consumable ->  _drawingMode.executeAction(_canvas, consumable);
        _canvas.addAction(new Pair<>(action, shape));
        _drawingMode.executeAction(_canvas, shape);
    }

    private Shape getMethodArgs() {
        return _drawingMode == DrawingMode.PEN ?
                new Line(_xStart, _yStart, _xEnd, _yEnd)
                : _drawingMode == DrawingMode.ERASER ?
                new Rectangle(_xStart, _yStart, _fillWidth, _fillHeight)
                : _drawingMode == DrawingMode.RECTANGLE ?
                new Rectangle(_xStart, _yStart, (_xEnd - _xStart), (_yEnd - _yStart))
                : null;
    }

    private void addCombinedSubActionsToUndo() {

        if (_subActions.empty()) {
            reset();
            return;
        }

        List<Pair<BiConsumer<MainCanvas,Shape>,Shape>> subActions = new ArrayList<>(_subActions);
        Consumer<Shape> aggregateAction = __ -> {
            subActions.forEach(action -> action.getKey().accept(_canvas, action.getValue()));
        };

        _canvas.addAction(new Pair<>(aggregateAction, null));
        reset();
    }

    private void performActionAndPushOntoUndoStack(Shape shape) {
        _subActions.push(new Pair<>(_drawingMode.getAction(), shape));
        _drawingMode.executeAction(_canvas, shape);
    }
}
