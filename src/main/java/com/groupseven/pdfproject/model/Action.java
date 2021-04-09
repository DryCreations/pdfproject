package com.groupseven.pdfproject.model;

import javafx.event.Event;
import javafx.geometry.Point2D;

/**
 * @author Charles Witherspoon
 *
 * \brief This interface represents an action to be undone/redone
 * \ref t10_2 "Task 10.2"
 */
public interface Action {

    /**
     * Performs the action
     */
    void execute();

    /**
     * Sets up the environment for the action to take place
     * @param event Event triggering the action
     * @return a constructed action based on the Event source
     */
    Action handle(Event event);

    /**
     * @return true if the action is complete
     */
    boolean isComplete();

    /**
     * Evaluates whether a point is contained by the Action
     * @param point Point to be checked
     * @return true if Point is contained by the Action
     */
    boolean contains(Point2D point);
}