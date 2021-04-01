/**
 * 
 */
package com.groupseven.pdfproject;


import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import java.util.Arrays;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 * @author Cassandra Mae
 *\defgroup Rectangle
 *@{
 *\ref t18_1 "task 18.1"
 *\brief This class creates a rectangle shape in order to demonstrate the selection operation.
 */
public class ShapeRectangle extends Shape{

    public ShapeRectangle() {
        pointsOfShape = Arrays.asList(null, null);
    }
    /**
     * \brief Draws the rectangle shape 
     * \ref t18_1 "task 18.1" Draws a rectangle which can be selected by the selection tool.
     */
    @Override
    public void draw(GraphicsContext gc) {
            // TODO Auto-generated method stub
        if (pointsOfShape.get(0) == null || pointsOfShape.get(1) == null)
            return;


        gc.setFill(color);
        javafx.scene.shape.Rectangle bound = getBound();
        gc.fillRect(bound.getX(), bound.getY(), bound.getWidth(), bound.getHeight());

}

    /**
     * \ref t18_1 "task 18.1"
     */
    @Override
    public void Event(MouseEvent mouseEvent) {
            // TODO Auto-generated method stub
        Point2D currentPoint = new Point2D(mouseEvent.getX(), mouseEvent.getY());
        if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED)
            pointsOfShape.set(0, currentPoint);
        else if (mouseEvent.getEventType() == MouseEvent.MOUSE_DRAGGED)
            pointsOfShape.set(1, currentPoint);
        else if (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED)
            finishedDrawing.run();


    }

    @Override
    public void Event(KeyEvent keyEvent) {
            // TODO Auto-generated method stub

    }

    /**
     * \ref t18_1 "task 18.1"
     * \brief highlights the rectangle when it is selected.
     */
    @Override
    public void HighlightShape(GraphicsContext gc) {
        // TODO Auto-generated method stub
        if(selected) {


            javafx.scene.shape.Rectangle bound = getBound();
            gc.setLineWidth(7.0);
            bound.setStroke(Color.RED);
            gc.strokeRect(bound.getX(), bound.getY(), bound.getWidth(), bound.getHeight());

            gc.setFill(color.darker());
            gc.fillRect(bound.getX(), bound.getY(), bound.getWidth(), bound.getHeight());

        }
    }

    @Override
    void drawOnPdfCanvas(PdfCanvas canvas, PdfPage page) {
        Rectangle bound = getBound();
        int red = (int) (color.getRed() * 255);
        int green = (int) (color.getGreen() * 255);
        int blue = (int) (color.getBlue() * 255);
        canvas.setColor(new DeviceRgb(red, green, blue), true);
        canvas.rectangle(bound.getX(), page.getPageSize().getHeight() - bound.getY() - bound.getHeight(), bound.getWidth(), bound.getHeight()).fill();
    }
}
/**@}**/
