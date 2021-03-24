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
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
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
    
    public DocumentModel(String filename) throws IOException{
        this.filename = filename;
        
        pages = new ArrayList<>();
        
        File pdfFile = new File(filename);
        
        RandomAccessFile raf = new RandomAccessFile(pdfFile, "r");
        FileChannel channel = raf.getChannel();
        ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
        PDFFile pdf = new PDFFile(buf);

        for (int pageNumber = 1; pageNumber <= pdf.getNumPages(); ++pageNumber) {
                double scale = 1;
                PDFPage page = pdf.getPage(pageNumber);
                Rectangle rect = new Rectangle(0, 0, (int) page.getBBox().getWidth(), (int) page.getBBox().getHeight());
                BufferedImage bufferedImage = new BufferedImage((int)(rect.width * scale), (int)(rect.height * scale), BufferedImage.TYPE_INT_RGB);
                Image image = page.getImage((int)(rect.width * scale), (int)(rect.height * scale), rect, null, true, true);
                Graphics2D bufImageGraphics = bufferedImage.createGraphics();
                bufImageGraphics.drawImage(image, 0, 0, null);
                bufImageGraphics.dispose();
                
                pages.add(new PageModel(bufferedImage));
        }

        raf.close();
        
        
    }
    
    public PageModel getPage(int i) {
        return pages.get(i);
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