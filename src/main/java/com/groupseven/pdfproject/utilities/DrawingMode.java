package com.groupseven.pdfproject.utilities;

///@author Charles Witherspoon

/// \brief This enum represents the different drawing modes available
///
/// \ref t9_1 "Task 9.1"
public enum DrawingMode {
    PEN, ERASER, RECTANGLE, SELECT, TEXTFIELD, RADIOBUTTON, CHECKBOX;

    /// \brief get a formatted string representation of the DrawingMode
    /// \return a formatted string representation of the DrawingMode
    ///
    /// \ref t9_1 "Task 9.1"
    public String getName() {
        char firstLetter = this.name().charAt(0);
        String restOfName = this.name().substring(1).toLowerCase();
        return firstLetter + restOfName;
    }
}
