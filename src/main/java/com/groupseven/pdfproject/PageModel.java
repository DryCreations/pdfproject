/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupseven.pdfproject;

import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;

/**
 *
 * @author hayde
 */
public class PageModel {
    private Canvas canvas;
    private BufferedImage bufferedImage;
    private BackgroundImage background;
    private VBox node;
    private GraphicsContext graphicsContext;
    
    public PageModel(BufferedImage image) {
        bufferedImage = image;
        Image fximage = SwingFXUtils.toFXImage(image, null);
        canvas = new Canvas(fximage.getWidth(), fximage.getHeight());
        graphicsContext = canvas.getGraphicsContext2D();
        node = new VBox(0);
        background = new BackgroundImage(fximage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, true, false));
        node.setBackground(new Background(background));
        node.getChildren().add(canvas);
    }
    
    public VBox getNode() {
        return node;
    }
}
