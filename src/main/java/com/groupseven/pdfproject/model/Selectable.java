package com.groupseven.pdfproject.model;

import javafx.scene.shape.Shape;

/**
 * @author Charles Witherspoon
 *
 *         \brief This interface represents a selectable canvas element \ref t18_1 "Task 18.1"
 */
public interface Selectable {
    /**
     * @return Shape representing the selected canvas element
     */
    Shape getSelection();
    
    /**
     * \brief  Sets the link provided to the selected canvas element
     * \ref t18_2 "Task 18.2"
     * @param uri is the link provided by the user
     */
    void setUri(String uri);
    
    /**
     * \brief keeps a check if the selected element is linked or not
     * 
     */
    void setisLinked(boolean linked);

    /**
     * @return the link attached to the selected canvas element
     */
    String getLink();

    /**
     * 
     * @return true if the selected canvas element has a link attached to it.
     */
    boolean getisLinked();
}