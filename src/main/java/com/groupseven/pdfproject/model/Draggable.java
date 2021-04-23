package com.groupseven.pdfproject.model;

import javafx.geometry.Point2D;

/// \ @author Charles Witherspoon
/// \brief This interface represents a draggable canvas element \ref t8_6 "Task 8.6"
public interface Draggable {

     /// \Shifts a Draggable relative to a starting point and ending point
     ///
     /// \@param origin
     /// \           Point2D representing the starting point
     /// \ @param destination
     /// \           Point2D representing the ending point
     ///
     /// \@return A "dragging" Action

    Action shift(Point2D origin, Point2D destination);

    
    /// \@return true if the Draggable was moved
     
    boolean wasMoved();
}
