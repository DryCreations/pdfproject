/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupseven.pdfproject.utilities;

import com.groupseven.pdfproject.TriConsumer;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author hayde
 */
public class PdfAction {
    public static final TriConsumer<PdfCanvas, PdfPage, Shape> DRAW_LINE = (canvas, page, object) -> {
        if (!(object instanceof Line))
            return;

        Line line = (Line) object;
        
        canvas.saveState();
        
        com.itextpdf.kernel.geom.Rectangle size = page.getPageSize();
        
        Color c = (Color) line.getStroke();
        
        int red = (int) (c.getRed() * 255);
        int green = (int) (c.getGreen() * 255);
        int blue = (int) (c.getBlue() * 255);
        
        canvas.setColor(new DeviceRgb(red, green, blue), true);
        
        canvas.setStrokeColor(new DeviceRgb(red, green, blue))
                .moveTo(line.getStartX(), size.getHeight() - line.getStartY())
                .lineTo(line.getEndX(), size.getHeight() - line.getEndY())
                .closePathStroke();
        
        canvas.restoreState();
        
    };

    public static final TriConsumer<PdfCanvas, PdfPage, Shape> ERASE = (canvas, page, object) -> {
        if (!(object instanceof Rectangle))
            return;

        Rectangle rectangle = (Rectangle) object;
        
        canvas.saveState();
        
        canvas.setExtGState(new PdfExtGState().setFillOpacity(0.1f));

        com.itextpdf.kernel.geom.Rectangle size = page.getPageSize();
        
        Color c = (Color.BLACK);
        
        int red = (int) (c.getRed() * 255);
        int green = (int) (c.getGreen() * 255);
        int blue = (int) (c.getBlue() * 255);
        
        canvas.setColor(new DeviceRgb(red, green, blue), true);
        
        canvas.rectangle(rectangle.getX(), size.getHeight() - rectangle.getY() - rectangle.getHeight(), rectangle.getWidth(), rectangle.getHeight()).fill();
        
        canvas.restoreState();
    };

    public static final TriConsumer<PdfCanvas, PdfPage, Shape> DRAW_RECTANGLE = (canvas, page, object) -> {
        if (!(object instanceof Rectangle))
            return;

        Rectangle rectangle = (Rectangle) object;
        
        canvas.saveState();
        
        com.itextpdf.kernel.geom.Rectangle size = page.getPageSize();
        
        Color c = (Color) rectangle.getFill();
        
        int red = (int) (c.getRed() * 255);
        int green = (int) (c.getGreen() * 255);
        int blue = (int) (c.getBlue() * 255);
        
        canvas.setColor(new DeviceRgb(red, green, blue), true);
        
        canvas.rectangle(rectangle.getX(), size.getHeight() - rectangle.getY() - rectangle.getHeight(), rectangle.getWidth(), rectangle.getHeight()).fill();
        
        canvas.restoreState();
    };

    public static final TriConsumer<PdfCanvas, PdfPage, Shape> SELECT = (canvas, page, object) -> {
        if (!(object instanceof Rectangle))
            return;

        Rectangle rectangle = (Rectangle) object;
    };
}
