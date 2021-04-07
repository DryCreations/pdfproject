package com.groupseven.pdfproject;

import javafx.scene.shape.Shape;

public interface Selectable {
    void select();
    /// \ref t18_1 "task 18.1"
    void unselect();
    Shape getSelection();
}
