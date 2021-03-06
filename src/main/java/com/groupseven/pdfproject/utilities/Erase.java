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
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

///
/// @author Charles Witherspoon
///
/// @{ 
/// \brief This class represents an action to erase a spot on the canvas.
/// \ref t9_1_2 "Task 9.1.2"
public class Erase implements Action {
    private MainCanvas _canvas;
    private List<Rectangle> _eraseAreas;
    private boolean _isComplete;

    /// \ref t9_1_2 "Task 9.1.2"
    public Erase(MainCanvas canvas) {
        _canvas = canvas;
        _eraseAreas = new ArrayList<>();
    }

    /// \ref t9_1_2"Task 9.1.2"
    @Override
    public void execute() {
        _eraseAreas.forEach(area -> DrawingAction.ERASE.accept(_canvas, area));
    }

    /// \ref t9_1_2 "Task 9.1.2"
    /// \brief Erases the pen strokes or the shapes drawn on the canvas on mouse events like mouse pressed and released
    /// or mouse drag.
    @Override
    public Action handle(Event event) {
        if (!(event instanceof MouseEvent))
            return this;

        MouseEvent mouseEvent = (MouseEvent) event;
        Rectangle eraser = new Rectangle(mouseEvent.getX(), mouseEvent.getY(), 5, 5);
        eraser.setFill(Color.TRANSPARENT);
        _eraseAreas.add(eraser);

        _isComplete = (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED);
        return this;
    }

    /// \ref t9_1_2 "Task 9.1.2"
    @Override
    public boolean isComplete() {
        return _isComplete;
    }

    /// \ref t9_1_2 "Task 9.1.2"
    @Override
    public boolean contains(Point2D point) {
        return false;
    }

    /// \ref t9_1_2 "Task 9.1.2"
    /// \breif Erases the the shape or the line on the PDF Canvas
    @Override
    public void pdfExecute(PdfCanvas canvas, PdfPage page) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose
        // Tools | Templates.
        _eraseAreas.forEach(area -> PdfAction.ERASE.accept(canvas, page, area));
        System.out.println("Erase");
    }

}
/// @}