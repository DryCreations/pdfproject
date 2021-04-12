package com.groupseven.pdfproject.model;

import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import javafx.event.Event;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * @author Charles Witherspoon
 *
 *         \brief This interface represents an action to be undone/redone \ref t10_2 "Task 10.2"
 */
public interface Action {
    void execute();

    Action handle(MouseEvent event);
    
    void handle(KeyEvent event);

    boolean isComplete();

    boolean contains(Point2D point);

    void pdfExecute(PdfCanvas canvas, PdfPage page);
    
    
    
    
}