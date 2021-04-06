package com.groupseven.pdfproject;

import com.groupseven.pdfproject.utilities.DrawingAction;
import javafx.event.Event;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Charles Witherspoon
 *
 * @{
 * \brief This class represents an action that draws a line on the canvas
 * \ref t9_1_1 "Task 9.1.1"
 */
public class DrawLine implements Action {
    private MainCanvas _canvas;
    private List<Line> _lines;
    private Point2D _origin;
    private boolean _isComplete;

    public DrawLine(MainCanvas canvas) {
        _canvas = canvas;
        _lines = new ArrayList<>();
    }

    @Override
    public void execute() {
        _lines.forEach(
                line -> {
                    _canvas.getChildren().remove(line);
                    DrawingAction.DRAW_LINE.accept(_canvas, line);
                });
    }

    @Override
    public Action handle(Event event) {
        if (!(event instanceof MouseEvent))
            return this;

        MouseEvent mouseEvent = (MouseEvent) event;

        if (_origin != null)
            _lines.add(new Line(
                    _origin.getX(),
                    _origin.getY(),
                    mouseEvent.getX(),
                    mouseEvent.getY()));

        _origin = new Point2D(mouseEvent.getX(), mouseEvent.getY());
        _isComplete = (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED);

        return this;
    }

    @Override
    public boolean isComplete() {
        return _isComplete;
    }

    @Override
    public boolean contains(Point2D point) {
        return _lines.stream()
                .anyMatch(line -> line.contains(point));
    }
}
/**@}*/