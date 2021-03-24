/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupseven.pdfproject;

import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
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
    
    public PageModel(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
        canvas = new Canvas(bufferedImage.getWidth(), bufferedImage.getHeight());
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        background = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, true, false));
        node.setBackground(new Background(background));
        node.getChildren().add(canvas);
    }
    
    public VBox getNode() {
        return node;
    }
}
