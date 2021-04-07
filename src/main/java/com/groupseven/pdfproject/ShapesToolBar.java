/**
 * 
 */
package com.groupseven.pdfproject;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

/**
 * @author Cassandra Mae
 *\defgroup ShapesAndSelectTool
 *@{
 *\brief This class provides the tools available for the user to select and to draw.
 *\ref t18_1 "Task 18.1" Option Select tool is provided to select the shape. 
 */
public class ShapesToolBar extends ToolBar {

    public static final Color DEFAULT_COLOR = Color.PINK;
    private MainCanvas canvas;
    private ToggleGroup group;
    private ToolsOptions selectedTool;
    private ColorPicker colorPicker;
	 
    ///\brief sets up the tool bar for drawing and selecting the shape.
    public ShapesToolBar() {
        group = new ToggleGroup();
        colorPicker = new ColorPicker(DEFAULT_COLOR);
        setupItems();

    }

    public void setCanvas(MainCanvas canvas) {
        this.canvas = canvas;
        setupHandlers();
    }

    private void setupItems() {

        final RadioButton rectangleButton = new RadioButton("Rectangle");

        final RadioButton selectButton = new RadioButton("Select");


        rectangleButton.setToggleGroup(group);

        selectButton.setToggleGroup(group);



        rectangleButton.setUserData(ToolsOptions.RECTANGLE);

        selectButton.setUserData(ToolsOptions.SELECT);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        spacer.setMaxWidth(Region.USE_PREF_SIZE);
        getItems().addAll( rectangleButton,
        spacer, selectButton, colorPicker);
    }

    /// \brief sets up handler for selecting a shape or select tool and color picker.
    ///\ref t18_1 "task 18.1"
    private void setupHandlers() {
           group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
               @Override
               public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                   selectedTool = (ToolsOptions) group.getSelectedToggle().getUserData();
//                   canvas.setEventHandler(createEventHandler());
               }
           });

           colorPicker.setOnAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent event) {
//                   canvas.setEventHandler(createEventHandler());
               }
           });
       }

    /**
        * \brief This function creates an event handler according to the selected tool and color.
        * @return a new event handler
        */
        private HandlingEvents createEventHandler() {
            Color selectedColor = colorPicker.getValue();
            switch (selectedTool) {

                case RECTANGLE:
                    return new Drawing(canvas, ShapeRectangle::new, selectedColor);

                case SELECT:
                    return new SelectTool(canvas);
                default:
                    return null;
            }
        }
}

/**@}**/
