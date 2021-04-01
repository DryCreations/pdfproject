package com.groupseven.pdfproject.model;

import com.groupseven.pdfproject.MainCanvas;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.shape.Line;

import java.util.function.BiConsumer;


public enum DrawingMode {

    PEN((BiConsumer<Canvas, Line>) (canvas, line) -> {
//        canvas.getChildren().add(line);
        canvas.getGraphicsContext2D().strokeLine(
                line.getStartX(),
                line.getStartY(),
                line.getEndX(),
                line.getEndY()
        );
    })
    , ERASER((BiConsumer<Canvas, Point2D>) (canvas, origin) ->
            canvas.getGraphicsContext2D().clearRect(
                    origin.getX() - 2,
                    origin.getY() - 2,
                    100,
                    100
            ))
    ;
    private BiConsumer<?, ?> _action;

    private <T, R> DrawingMode(BiConsumer<T, R> action) {
            _action = action;
    }


    public <T, R> BiConsumer<T, R> getAction() {
        return (BiConsumer<T, R>) _action;
    }
}
