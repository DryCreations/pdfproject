/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupseven.pdfproject;

import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Side;
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

/// \brief representation of a single page of a PDF 
///
/// \ref t14_1 "task 14.1"
public class PageModel {
    private MainCanvas canvas;
    private BufferedImage bufferedImage;
    private VBox node;
    private GraphicsContext graphicsContext;
    
    /// \brief create a new page from an Image.
    ///
    /// \ref t14_1 "task 14.1"
    public PageModel(BufferedImage image) {
        bufferedImage = image;
        Image fximage = SwingFXUtils.toFXImage(image, null);
        
        canvas = new MainCanvas(fximage.getWidth(), fximage.getHeight());
        
        graphicsContext = canvas.getCanvas().getGraphicsContext2D();
        
        node = new VBox(0);
        
        BackgroundPosition bp = new BackgroundPosition(Side.LEFT, 0, false, Side.TOP, 0, false);
        
        BackgroundImage backgroundImage = new BackgroundImage(
                fximage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                bp,
                BackgroundSize.DEFAULT);
        node.setBackground(new Background(backgroundImage));
        node.getChildren().add(canvas);
    }
    
    /// \brief get the javafx node for the page
    /// \return VBox containing page
    ///
    /// \ref t14_1 "task 14.1"
    public VBox getNode() {
        return node;
    }
    
    
    public void clear() {
        canvas.clearScreen();
    }
    
    public MainCanvas getCanvas() {
        return canvas;
    }
}
