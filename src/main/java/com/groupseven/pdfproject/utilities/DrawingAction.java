package com.groupseven.pdfproject.utilities;

import com.groupseven.pdfproject.MainCanvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import sun.applet.Main;

import java.util.function.BiConsumer;

public class DrawingAction {

    public static final BiConsumer<MainCanvas, Shape> DRAW = (canvas, object) -> {
        if (!(object instanceof Line))
            return;

        Line line = (Line) object;
        canvas.getCanvas()
                    .getGraphicsContext2D()
                    .strokeLine(
                            line.getStartX(),
                            line.getStartY(),
                            line.getEndX(),
                            line.getEndY());
    };

    public static final BiConsumer<MainCanvas, Shape> ERASE = (canvas, object) -> {
        if (!(object instanceof Rectangle))
            return;

        Rectangle rectangle = (Rectangle) object;
        canvas.getCanvas()
                .getGraphicsContext2D()
                .clearRect(
                        rectangle.getX() - 2,
                        rectangle.getY() - 2,
                        rectangle.getWidth(),
                        rectangle.getHeight()
        );
    };

    public static final BiConsumer<MainCanvas, Shape> DRAW_RECTANGLE = (canvas, object) -> {
        if (!(object instanceof Rectangle))
            return;

        Rectangle rectangle = (Rectangle) object;
        canvas.getCanvas()
                    .getGraphicsContext2D()
                    .fillRect(
                            rectangle.getX(),
                            rectangle.getY(),
                            rectangle.getWidth(),
                            rectangle.getHeight());
    };

    public static final BiConsumer<MainCanvas, Shape> SELECT = (canvas, object) -> {
        if (!(object instanceof Rectangle))
            return;

        Rectangle rectangle = (Rectangle) object;

        GraphicsContext gc = canvas.getCanvas().getGraphicsContext2D();
        Paint currentColor = gc.getFill();
        double currentLineWidth = gc.getLineWidth();
        Paint selectColor = Color.grayRgb(100, 0.2);
        double selectLineWidth = 7.0;

        gc.setFill(selectColor);
        gc.setLineWidth(selectLineWidth);

        DRAW_RECTANGLE.accept(canvas, rectangle);

        gc.setFill(currentColor);
        gc.setLineWidth(currentLineWidth);
    };


}
