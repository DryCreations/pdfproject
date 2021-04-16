package com.groupseven.pdfproject.view;

import com.groupseven.pdfproject.MainCanvas;
import com.groupseven.pdfproject.utilities.DrawingMode;
import com.groupseven.pdfproject.utilities.DrawingTool;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Charles Witherspoon
 * 
 * @{ \brief This class represents a toolbar that provides different functionality for interacting with the canvas \ref
 *    t9_1 "Task 9.1"
 */
public class DrawingToolbar extends ToolBar {

    private MainCanvas _canvas;
    private ToggleGroup _toggleGroup;
    private ColorPicker _colorPicker;
    private DrawingTool _drawingTool;

    public DrawingToolbar(MainCanvas canvas) {
        _canvas = canvas;
        _toggleGroup = new ToggleGroup();
        _colorPicker = new ColorPicker(Color.BLACK);
        _drawingTool = new DrawingTool(_canvas);

        List<RadioButton> radioButtons = createRadioButtons(_toggleGroup, DrawingMode.PEN, DrawingMode.ERASER,
                DrawingMode.RECTANGLE, DrawingMode.SELECT);

        getItems().addAll(radioButtons);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        spacer.setMaxWidth(Region.USE_PREF_SIZE);
        getItems().addAll(spacer, _colorPicker);
        _colorPicker.setOnAction(__ -> _drawingTool.setColor(_colorPicker.getValue()));
        _toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            _drawingTool.setDrawingMode((DrawingMode) newValue.getUserData());
        });

        _canvas.setHandlerForTypes(_drawingTool, MouseEvent.ANY);
        radioButtons.get(0).setSelected(true);
    }

    private List<RadioButton> createRadioButtons(ToggleGroup toggleGroup, DrawingMode... modes) {
        List<RadioButton> radioButtons = new ArrayList<>();

        for (DrawingMode mode : modes) {
            RadioButton button = new RadioButton(mode.getName());
            button.setToggleGroup(toggleGroup);
            button.setUserData(mode);
            radioButtons.add(button);
        }

        return radioButtons;
    }
}
/** @} */
