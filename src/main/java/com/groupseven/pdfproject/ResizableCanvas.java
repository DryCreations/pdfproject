package com.groupseven.pdfproject;

import javafx.scene.canvas.Canvas;

/// \brief resizable canvas capable of resizing after declaration
/// \ref t8.2 "task 8.2"
public class ResizableCanvas extends Canvas{

    /// \ref t8.2 "task 8.2"
    @Override
    public boolean isResizable(){
        return true;
    }

    /// \brief resize canvas to new dimensions
    /// \ref t8.2 "task 8.2"
    @Override
    public void resize(double width, double height){
        widthProperty().set(width);
        heightProperty().set(height);
    }

}
