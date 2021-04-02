package com.groupseven.pdfproject.utilities;

import com.groupseven.pdfproject.MainCanvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import sun.applet.Main;

import java.util.function.BiConsumer;

public class DrawingActions {

    public static final BiConsumer<MainCanvas,Line> DRAW = (canvas, line) ->
            canvas.getCanvas()
                    .getGraphicsContext2D()
                    .strokeLine(
                            line.getStartX(),
                            line.getStartY(),
                            line.getEndX(),
                            line.getEndY());

    public static final BiConsumer<MainCanvas, Rectangle> ERASE = (canvas, rect) ->
            canvas.getCanvas()
                    .getGraphicsContext2D()
                    .clearRect(
                            rect.getX() - 2,
                            rect.getY() - 2,
                            rect.getWidth(),
                            rect.getHeight()
            );

    public static final BiConsumer<MainCanvas, Rectangle> DRAW_RECTANGLE = (canvas, rect) ->
            canvas.getCanvas()
                    .getGraphicsContext2D()
                    .fillRect(
                            rect.getX(),
                            rect.getY(),
                            rect.getWidth(),
                            rect.getHeight());

    public static final BiConsumer<MainCanvas, Rectangle> SELECT = (canvas, rectangle) -> {
        DRAW_RECTANGLE.accept(canvas, rectangle);
        Rectangle selectionCover = new Rectangle(
                rectangle.getX() - 2,
                rectangle.getY() - 2,
                rectangle.getWidth() + 4,
                rectangle.getHeight() + 4);

        GraphicsContext gc = canvas.getCanvas().getGraphicsContext2D();
        Paint currentColor = gc.getFill();
        gc.setFill(Color.grayRgb(100, 0.2));

        gc.fillRect(
                selectionCover.getX(),
                selectionCover.getY(),
                selectionCover.getWidth(),
                selectionCover.getHeight());

        gc.setFill(currentColor);
    };
}
