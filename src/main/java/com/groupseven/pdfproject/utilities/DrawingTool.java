package com.groupseven.pdfproject.utilities;

import com.groupseven.pdfproject.*;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;

/**
 * @author Charles Witherspoon
 *
 * \brief Class that performs different canvas actions based on the DrawingMode
 * \ref t9_1 "Task 9.1"
 */
public class DrawingTool implements EventHandler<MouseEvent> {
    private MainCanvas _canvas;
    private DrawingMode _drawingMode;
    private Action _currentAction;


    public DrawingTool(MainCanvas canvas) {
        _canvas = canvas;
    }

    public void setDrawingMode(DrawingMode drawingMode) {
        _drawingMode = drawingMode;
        _currentAction = null;
    }
    @Override
    public void handle(MouseEvent event) {
        EventType<? extends MouseEvent> eventType = event.getEventType();
        if (eventType != MouseEvent.MOUSE_PRESSED
                && eventType != MouseEvent.MOUSE_DRAGGED
                && eventType != MouseEvent.MOUSE_RELEASED)
            return;

        if (_currentAction == null)
            _currentAction = (_drawingMode == DrawingMode.PEN) ?
                    new DrawLine(_canvas)
                    : (_drawingMode == DrawingMode.ERASER) ?
                    new Erase(_canvas)
                    : (_drawingMode == DrawingMode.SELECT) ?
                    new Select(_canvas)
                    : (_drawingMode == DrawingMode.RECTANGLE) ?
                    new DrawRectangle(_canvas)
                    : null;

        if (_currentAction != null)
            _currentAction.handle(event);

        updateScreen();
    }

    private void updateScreen() {
        _canvas.refresh();
        _currentAction.execute();

        if (_currentAction.isComplete()) {
            _canvas.getUndoStack().push(_currentAction);
            _canvas.clearRedo();
            _currentAction = null;
        }
    }
}
