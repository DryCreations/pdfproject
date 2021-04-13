package com.groupseven.pdfproject.utilities;

import com.groupseven.pdfproject.MainCanvas;
import com.groupseven.pdfproject.model.Action;
import com.groupseven.pdfproject.utilities.DrawingAction;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import javafx.event.Event;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Charles Witherspoon
 *
 * @{ \brief This class represents an action that draws a line on the canvas \ref t9_1_1 "Task 9.1.1"
 */
public class DrawLine implements Action {
    private MainCanvas _canvas;
    private List<Line> _lines;
    private Point2D _origin;
    private boolean _isComplete;
    private Color _color;

    public DrawLine(MainCanvas canvas, Color color) {
        _canvas = canvas;
        _color = color;
        _lines = new ArrayList<>();
    }

    @Override
    public void execute() {
        _lines.forEach(line -> {
            _canvas.getChildren().remove(line);
            DrawingAction.DRAW_LINE.accept(_canvas, line);
        });
    }

    @Override
    public Action handle(Event event) {
        if (!(event instanceof MouseEvent))
            return this;

        MouseEvent mouseEvent = (MouseEvent) event;

        if (_origin != null) {
            Line line = new Line(_origin.getX(), _origin.getY(), mouseEvent.getX(), mouseEvent.getY());
            line.setStroke(_color);
            _lines.add(line);
        }

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
        return _lines.stream().anyMatch(line -> line.contains(point));
    }

    @Override
    public void pdfExecute(PdfCanvas canvas, PdfPage page) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change
        // body of generated methods, choose
        // Tools | Templates.
        _lines.forEach(line -> {
            PdfAction.DRAW_LINE.accept(canvas, page, line);
        });
        System.out.println("Line");
    }

}
/** @} */