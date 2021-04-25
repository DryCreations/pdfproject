package com.groupseven.pdfproject.utilities;

import com.groupseven.pdfproject.*;
import com.groupseven.pdfproject.model.Action;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/// @author Charles Witherspoon

/// \brief Class that performs different canvas actions based on the DrawingMode
///
/// \ref t9_1 "Task 9.1"
public class DrawingTool implements EventHandler<MouseEvent> {
    private MainCanvas _canvas;
    private DrawingMode _drawingMode;
    private Action _currentAction;
    private Color _color;

    public DrawingTool(MainCanvas canvas) {
        _canvas = canvas;
        _color = Color.BLACK;
    }

    /// \brief set the drawing mode of the tool
    ///
    /// \ref t9_1 "Task 9.1"
    public void setDrawingMode(DrawingMode drawingMode) {
        _drawingMode = drawingMode;
        _currentAction = null;
    }

    /// \brief handle passed in mouse event
    ///
    /// \ref t9_1 "Task 9.1"
    @Override
    public void handle(MouseEvent event) {
        EventType<? extends MouseEvent> eventType = event.getEventType();
        if (eventType != MouseEvent.MOUSE_PRESSED && eventType != MouseEvent.MOUSE_DRAGGED
                && eventType != MouseEvent.MOUSE_RELEASED)
            return;

        if (_currentAction == null)
            _currentAction = (_drawingMode == DrawingMode.PEN) ? new DrawLine(_canvas, _color)
                    : (_drawingMode == DrawingMode.ERASER) ? new Erase(_canvas) : (_drawingMode == DrawingMode.SELECT)
                            ? new Select(_canvas)
                            : (_drawingMode == DrawingMode.RECTANGLE) ? new DrawRectangle(_canvas, _color)
                                    : (_drawingMode == DrawingMode.TEXTFIELD) ? new DrawTextField(_canvas)
                                            : (_drawingMode == DrawingMode.RADIOBUTTON) ? new DrawRadioButton(_canvas)
                                                    : (_drawingMode == DrawingMode.CHECKBOX) ? new DrawCheckBox(_canvas)
                                                            : null;

        if (_currentAction != null) {
            _currentAction.handle(event);
            updateScreen();
        }

    }

    /// \brief set color
    ///
    /// \ref t9_1 "Task 9.1"
    public void setColor(Color color) {
        _color = color;
    }

    /// \brief update screen with changes
    ///
    /// \ref t9_1 "Task 9.1"
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
