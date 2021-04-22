package com.groupseven.pdfproject.utilities;

import com.groupseven.pdfproject.MainCanvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.function.BiConsumer;


/// @author Charles Witherspoon

/// \brief This class contains the drawing actions to be performed on the canvas \ref t9_1 "Task 9.1"
///
/// \ref t9_1 "Task 9.1"
public class DrawingAction {

    /// \brief draw line on canvas functionality
    ///
    /// \ref t9_1 "Task 9.1"
    public static final BiConsumer<MainCanvas, Shape> DRAW_LINE = (canvas, shape) -> {
        if (!(shape instanceof Line))
            return;

        Line line = (Line) shape;
        GraphicsContext gc = canvas.getCanvas().getGraphicsContext2D();
        Paint currentColor = gc.getStroke();
        gc.setStroke(line.getStroke());
        gc.strokeLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
        gc.setStroke(currentColor);
    };

    /// \brief erase on canvas functionality
    ///
    /// \ref t9_1_2 "Task 9.1.2"
    public static final BiConsumer<MainCanvas, Shape> ERASE = (canvas, shape) -> {
        if (!(shape instanceof Rectangle))
            return;

        Rectangle rectangle = (Rectangle) shape;
        canvas.getCanvas().getGraphicsContext2D().clearRect(rectangle.getX() - 2, rectangle.getY() - 2,
                rectangle.getWidth(), rectangle.getHeight());
    };

    /// \brief draw rectangle on canvas functionality
    ///
    /// \ref t9_1 "Task 9.1"
    public static final BiConsumer<MainCanvas, Shape> DRAW_RECTANGLE = (canvas, shape) -> {
        if (!(shape instanceof Rectangle))
            return;

        Rectangle rectangle = (Rectangle) shape;
        GraphicsContext gc = canvas.getCanvas().getGraphicsContext2D();
        Paint currentFill = gc.getFill();
        gc.setFill(rectangle.getFill());
        gc.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
        gc.setFill(currentFill);
    };

    /// \brief select object on canvas functionality
    ///
    /// \ref t9_1 "Task 9.1"
    public static final BiConsumer<MainCanvas, Shape> SELECT = (canvas, shape) -> {
        if (!(shape instanceof Rectangle))
            return;

        Rectangle rectangle = (Rectangle) shape;
        GraphicsContext gc = canvas.getCanvas().getGraphicsContext2D();
        Paint currentColor = gc.getFill();
        Paint selectColor = Color.grayRgb(100, 0.2);
        gc.setFill(selectColor);
        gc.fillRect(rectangle.getX() - 2, rectangle.getY() - 2, rectangle.getWidth() + 4, rectangle.getHeight() + 4);
        gc.setFill(currentColor);
    };
}
