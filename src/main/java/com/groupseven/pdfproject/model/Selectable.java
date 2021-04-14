package com.groupseven.pdfproject.model;

import javafx.scene.shape.Shape;

/**
 * @author Charles Witherspoon
 *
 *         \brief This interface represents a selectable canvas element \ref t18_1 "Task 18.1"
 */
public interface Selectable {
    void select();

    void unselect();

    Shape getSelection();
}