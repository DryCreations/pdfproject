package com.groupseven.pdfproject.model;

import javafx.scene.shape.Shape;

/**
 * @author Charles Witherspoon
 *
 *         \brief This interface represents a selectable canvas element \ref t18_1 "Task 18.1" \ref t18_2 "Task 18.2"
 */
public interface Selectable {
    /**
     * @return Shape representing the selected canvas element
     */
    Shape getSelection();

    void setUri(String uri);

    void setisLinked(boolean linked);

    String getLink();

    boolean getisLinked();
}