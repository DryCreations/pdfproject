package com.groupseven.pdfproject.utilities;

import com.groupseven.pdfproject.MainCanvas;
import com.groupseven.pdfproject.model.Action;
import com.groupseven.pdfproject.model.Draggable;
import com.groupseven.pdfproject.model.Selectable;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import javafx.event.Event;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * @author Charles Witherspoon
 * \brief This class represents an action to draw a shape on the canvas
 * \ref t9_1 "Task 9.1"
 */
public class DrawRectangle implements Action, Selectable, Draggable {
    private MainCanvas _canvas;
    private Point2D _origin;
    private Rectangle _rectangle;
    private boolean _isComplete;
    private Color _color;
    private DrawRectangle _replacement;

    public DrawRectangle(MainCanvas canvas, Color color) {
        _canvas = canvas;
        _color = color;
    }

    @Override
    public void execute() {
        if (!wasMoved())
            DrawingAction.DRAW_RECTANGLE.accept(_canvas, _rectangle);
    }

    @Override
    public Action handle(Event event) {
        if (!(event instanceof MouseEvent))
            return this;

        MouseEvent mouseEvent = (MouseEvent) event;

        if (_origin == null)
            _origin = new Point2D(mouseEvent.getX(), mouseEvent.getY());
        else {
            _rectangle = new Rectangle(
                    _origin.getX(),
                    _origin.getY(),
                    mouseEvent.getX() - _origin.getX(),
                    mouseEvent.getY() - _origin.getY());
            _rectangle.setFill(_color);
        }

        _isComplete = (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED);
        return this;
    }

    /**
     * Sets the complete status to true for the Action
     */
    public void complete() {
        _isComplete = true;
    }

    @Override
    public boolean isComplete() {
        return _isComplete;
    }

    @Override
    public boolean contains(Point2D point) {
        return _rectangle.contains(point);
    }


    @Override
    public Shape getSelection() {
        return _rectangle;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DrawRectangle))
            return false;

        DrawRectangle other = (DrawRectangle) o;
        return this._rectangle.getX() == other._rectangle.getX()
                && this._rectangle.getY() == other._rectangle.getY()
                && this._rectangle.getWidth() == other._rectangle.getWidth()
                && this._rectangle.getHeight() == other._rectangle.getHeight();
    }

    @Override
    public Action shift(Point2D origin, Point2D destination) {
        DrawRectangle drawRectangle = new DrawRectangle(_canvas, _color);

        double xDiff = destination.getX() - origin.getX();
        double yDiff = destination.getY() - origin.getY();

        drawRectangle._rectangle = new Rectangle(
                _rectangle.getX() + xDiff,
                _rectangle.getY() + yDiff,
                _rectangle.getWidth(),
                _rectangle.getHeight());
        drawRectangle._rectangle.setFill(_color);
        _replacement = drawRectangle;
        drawRectangle.complete();
        return drawRectangle;
    }

    @Override
    public boolean wasMoved() {
        return _replacement != null
                && (_canvas.getUndoStack().contains(_replacement)
                || !_canvas.getRedoStack().contains(_replacement));
    }

    @Override
    public void pdfExecute(PdfCanvas canvas, PdfPage page) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose
        // Tools | Templates.
        PdfAction.DRAW_RECTANGLE.accept(canvas, page, _rectangle);
        System.out.println("Rectangle");
    }
}
