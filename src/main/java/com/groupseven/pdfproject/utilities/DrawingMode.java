package com.groupseven.pdfproject.utilities;

import com.groupseven.pdfproject.MainCanvas;
import javafx.scene.shape.Shape;

import java.util.function.BiConsumer;


public enum DrawingMode {

    PEN(DrawingAction.DRAW),
    ERASER(DrawingAction.ERASE),
    RECTANGLE(DrawingAction.DRAW_RECTANGLE),
    SELECT(DrawingAction.SELECT)
    ;

    private final BiConsumer<MainCanvas, Shape> _action;

    private DrawingMode(BiConsumer<MainCanvas, Shape> action) {
            _action = action;
    }


    public BiConsumer<MainCanvas, Shape> getAction() {
        return _action;
    }

    public void executeAction(MainCanvas canvas, Shape shape) {
        _action.accept(canvas, shape);
    }
}
