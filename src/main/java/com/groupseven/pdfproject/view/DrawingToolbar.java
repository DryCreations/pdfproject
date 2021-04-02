package com.groupseven.pdfproject.view;

import com.groupseven.pdfproject.MainCanvas;
import com.groupseven.pdfproject.utilities.DrawingAction;
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
import javafx.scene.shape.Shape;

import java.util.function.BiConsumer;

public class DrawingToolbar extends ToolBar {

    private MainCanvas _canvas;
    private ToggleGroup _toggleGroup;
    private ColorPicker _colorPicker;
    private DrawingTool _drawingTool;

    public DrawingToolbar(MainCanvas canvas) {
        _canvas = canvas;
        _toggleGroup = new ToggleGroup();
        _colorPicker = new ColorPicker(Color.PINK);
        _drawingTool = new DrawingTool(_canvas);

        RadioButton penBtn = new RadioButton("Pen");
        penBtn.setToggleGroup(_toggleGroup);
        penBtn.setUserData(DrawingMode.PEN);
        RadioButton eraserBtn = new RadioButton("Eraser");
        eraserBtn.setToggleGroup(_toggleGroup);
        eraserBtn.setUserData(DrawingMode.ERASER);
        RadioButton rectangleBtn = new RadioButton("Rectangle");
        rectangleBtn.setToggleGroup(_toggleGroup);
        rectangleBtn.setUserData(DrawingMode.RECTANGLE);
        RadioButton selectBtn = new RadioButton("Select");
        selectBtn.setToggleGroup(_toggleGroup);
        selectBtn.setUserData(DrawingMode.SELECT);

        RadioButton[] buttons = new RadioButton[]{penBtn, eraserBtn, rectangleBtn, selectBtn};
        getItems().addAll(buttons);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        spacer.setMaxWidth(Region.USE_PREF_SIZE);
        getItems().addAll(spacer, _colorPicker);

        _toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            _drawingTool.setDrawingMode((DrawingMode) newValue.getUserData());
        });

        _canvas.setHandlerForTypes(_drawingTool, MouseEvent.ANY);
    }

}
