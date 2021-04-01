package com.groupseven.pdfproject.utilities;

import com.groupseven.pdfproject.MainCanvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.util.function.BiConsumer;


public enum DrawingMode {

    PEN((BiConsumer<MainCanvas, Line>) (canvas, line) -> {
        canvas.getCanvas().getGraphicsContext2D().strokeLine(
                line.getStartX(),
                line.getStartY(),
                line.getEndX(),
                line.getEndY()
        );
    })
    , ERASER((BiConsumer<MainCanvas, Rectangle>) (canvas, rect) ->
            canvas.getCanvas().getGraphicsContext2D().clearRect(
                    rect.getX() - 2,
                    rect.getY() - 2,
                    rect.getWidth(),
                    rect.getHeight()
            ))
    , RECTANGLE((BiConsumer<MainCanvas, Rectangle>) (canvas, rect) -> {
        canvas.getCanvas().getGraphicsContext2D().fillRect(
                rect.getX(),
                rect.getY(),
                rect.getWidth(),
                rect.getHeight());
    })
    ;
    private BiConsumer<?, ?> _action;

    private <T, R> DrawingMode(BiConsumer<T, R> action) {
            _action = action;
    }


    public <T, R> BiConsumer<T, R> getAction() {
        return (BiConsumer<T, R>) _action;
    }
}
