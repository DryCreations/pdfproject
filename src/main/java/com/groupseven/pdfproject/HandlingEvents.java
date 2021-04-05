/**
 * 
 */
package com.groupseven.pdfproject;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
/**
 * @author Cassandra Mae
 * \def EventHandler
 * @{
 *\brief this interface is for handling all the events like 
 *mouse clicked, mouse dragged, mouse pressed and key pressed
 *\ref t18_1 "task 18.1"
 */
public interface HandlingEvents {
    /**
     * \ref t18_1 "task 18.1"
     * @param mouseEvent the event to be handled when mouse clicked or dragged
     * or released or pressed.
     */
    void Event(MouseEvent mouseEvent);


    /**
     * \ref t18_1 "task 18.1"
     * @param keyEvent the event to be handled when backspace or delete key is pressed
     * or released or pressed.
     */
    void Event(KeyEvent keyEvent);
}
