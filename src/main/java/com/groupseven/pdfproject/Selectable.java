package com.groupseven.pdfproject;

import javafx.scene.shape.Shape;

public interface Selectable {
    void select();
    void unselect();
    Shape getSelection();
}
