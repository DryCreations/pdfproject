package com.groupseven.pdfproject;

import com.groupseven.pdfproject.model.Action;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
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
import java.util.Stack;

///
///
/// @author hayde
///

/// \brief Model representing a pdf document that contains all pages necessary to interact with the pdf.
///
/// \ref t8_1 "task 8.1"
public class DocumentModel {
    private File file;
    private List<PageModel> pages;

    /// \ref t8_1 "task 8.1"
    public DocumentModel() {
        this.pages = new ArrayList<>();
        BufferedImage bufferedImage = new BufferedImage(600, 700, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
        pages.add(new PageModel(bufferedImage));
    }

    /// \brief create document from a specific filename
    ///
    /// \ref t14_1 "task 14.1"
    public DocumentModel(File file) throws IOException {
        /// pre-condition
        if (file != null)
            assert true;

        this.file = file;
        this.pages = new ArrayList<>();

        RandomAccessFile raf = new RandomAccessFile(file, "r");
        FileChannel fileChannel = raf.getChannel();
        ByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
        PDFFile pdfFile = new PDFFile(buffer);

        if (pdfFile.getNumPages() > 0)
            assert true;
        /// End pre-conditions

        /// Actual Implementation of function
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
        /// End of Actual Implementation of function

        /// Post-condition
        if (this.file == file)
            assert true;
        /// the file passed did not change by calling this function
    }

    /// \brief get a specific page from the document
    /// \return PageModel representing the desired page
    ///
    /// \ref t14_1 "task 14.1"
    public PageModel getPage(int i) {
        return pages.get(i);
    }

    /// \brief sets dimension of current pdf document
    ///
    /// \ref t8.2 "task 8.2"
    public void setDimensions() {

    }

    /// \brief inserts asset onto document
    ///
    /// \ref t8_4 "task 8.4"
    public void insertAsset() {

    }

    /// \brief inserts shape onto document
    ///
    /// \ref t8_5 "task 8.5"
    public void insertShape() {

    }

    /// \brief moves object that is currently in document
    ///
    /// \ref t8_6 "task 8.6"
    public void moveObject() {

    }

    /// \brief inserts textbox into current document
    ///
    /// \ref t8_7 "task 8.7"
    public void insertTextBox() {

    }

    /// \brief exports current file to a file
    ///
    /// \ref t8_8 "task 8.8"
    public void export(File dest) throws IOException {
        /// pre-conditions
        assert (!dest.equals(null));
        assert (!pages.equals(null));
        int pgSize = pages.size(); // only for checking post-condition

        /// Actual implementation of method
        dest.getParentFile().mkdirs();
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdfDoc;
        if (file != null) {
            PdfReader reader = new PdfReader(file);
            pdfDoc = new PdfDocument(reader, writer);
        } else {
            pdfDoc = new PdfDocument(writer);
        }

        for (int i = 0; i < pages.size(); i++) {
            PdfPage page;
            PageModel pageModel = pages.get(i);
            if (file != null) {
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
        /// End implementation of Actual method

        /// post-condition
        assert (pgSize == pages.size());/// the size of the original document did not change
    }
}