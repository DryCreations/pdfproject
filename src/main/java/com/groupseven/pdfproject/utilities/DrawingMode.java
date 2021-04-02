package com.groupseven.pdfproject.utilities;

import com.groupseven.pdfproject.MainCanvas;
import javafx.scene.shape.Rectangle;

import java.util.function.BiConsumer;


public enum DrawingMode {

    PEN(DrawingActions.DRAW),
    ERASER(DrawingActions.ERASE),
    RECTANGLE(DrawingActions.DRAW_RECTANGLE),
    SELECT(DrawingActions.SELECT)
    ;

    private BiConsumer<?, ?> _action;

    private <T, R> DrawingMode(BiConsumer<T, R> action) {
            _action = action;
    }

    public <T, R> BiConsumer<T, R> getAction() {
        return (BiConsumer<T, R>) _action;
    }
}
