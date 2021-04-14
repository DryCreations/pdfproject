/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupseven.pdfproject;

import com.groupseven.pdfproject.model.Action;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 *
 * @author hayde
 */
public class DocumentModel {
    private String filename;
    private List<PageModel> pages;

    /// \ref t8_1 "task 8.1"
    public DocumentModel() {
        this.pages = new ArrayList<>();
        BufferedImage bufferedImage = new BufferedImage(595 , 842 , BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect ( 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight() );
        pages.add(new PageModel(bufferedImage));
    }

    /// \brief create document from a specific filename
    ///
    /// \ref t14_1 "task 14.1"
    public DocumentModel(String filename) throws IOException {
        this.filename = filename;
        this.pages = new ArrayList<>();

        File file = new File(filename);

        RandomAccessFile raf = new RandomAccessFile(file, "r");
        FileChannel fileChannel = raf.getChannel();
        ByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
        PDFFile pdfFile = new PDFFile(buffer);

        for (int i = 1; i <= pdfFile.getNumPages(); i++) {
            PDFPage page = pdfFile.getPage(i);
            Rectangle rect = new Rectangle(0, 0, (int) page.getBBox().getWidth(), (int) page.getBBox().getHeight());
            BufferedImage bufferedImage = new BufferedImage(rect.width, rect.height, BufferedImage.TYPE_INT_RGB);
            Image image = page.getImage(rect.width, rect.height, rect, null, true, true);
            Graphics2D graphicsContext = bufferedImage.createGraphics();
            graphicsContext.drawImage(image, 0, 0, null);
            graphicsContext.dispose();

            pages.add(new PageModel(bufferedImage));
        }

        raf.close();
    }

    /// \brief get a specific page from the document
    /// \return PageModel representing the desired page
    ///
    /// \ref t14_1 "task 14.1"
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
    public void export(File dest) throws IOException {
        dest.getParentFile().mkdirs();
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdfDoc;
        if (filename != null) {
            PdfReader reader = new PdfReader(filename);
            pdfDoc = new PdfDocument(reader, writer);
        } else {
            pdfDoc = new PdfDocument(writer);
        }

        for (int i = 0; i < pages.size(); i++) {
            PdfPage page;
            PageModel pageModel = pages.get(i);
            if (filename != null) {
                page = pdfDoc.getPage(i + 1);
            } else {
                PageSize pageSize = new PageSize((float) pageModel.getCanvas().getWidth(),
                        (float) pageModel.getCanvas().getHeight());
                page = pdfDoc.addNewPage(pageSize);
            }

            PdfCanvas canvas = new PdfCanvas(page);

            Stack<Action> undoStack = pageModel.getCanvas().getUndoStack();

            undoStack.forEach(action -> action.pdfExecute(canvas, page));

            canvas.release();

        }
        pdfDoc.close();
    }
}
