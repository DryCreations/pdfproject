/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupseven.pdfproject;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Image;
import com.itextpdf.pdfrender.PdfToImageRenderer;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.Canvas;

/**
 *
 * @author hayde
 */
public class DocumentModel {
    private String filename;
    private List<PageModel> pages;
    
    /// \ref t8_1 "task 8.1"
    public DocumentModel() {

    }
    
    public DocumentModel(String filename) throws IOException {
        this.filename = filename;
        
        pages = new ArrayList<>();
        
        PdfToImageRenderer.renderPdf(new FileInputStream(filename), new PdfToImageRenderer.BufferedImageReadyListener() {
            @Override
            public void bufferedImageReady(BufferedImage bi, int i) {
                pages.add(new PageModel(bi));
            }
        });
    }
    
    /// \ref t8.2 "task 8.2"
    public void setDimensions() {

    }

    /// \ref t8_4 "task 8.4"
    public void insertAsset() {

    }

    /// \ref t8_5 "task 8.5"
    public void insertShape() {

    }

    /// \ref t8_6 "task 8.6"
    public void moveObject() {

    }

    /// \ref t8_7 "task 8.7"
    public void insertTextBox() {

    }

    /// \ref t8_8 "task 8.8"
    public void export() {

    }
}