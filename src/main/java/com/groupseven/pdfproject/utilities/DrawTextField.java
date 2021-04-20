package com.groupseven.pdfproject.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

import com.groupseven.pdfproject.MainCanvas;
import com.groupseven.pdfproject.model.Action;
import com.groupseven.pdfproject.model.Draggable;
import com.groupseven.pdfproject.model.SelectState;
import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfTextFormField;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;

import javafx.event.Event;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

/**
 * @author Hunter Gongloff
 * 
 * @{ \brief This is the class to create a text field on user click
 *    \ref 16_1 "Task 16.1" 
 */

public class DrawTextField implements Action {

    public static final String SRC = "src/main/resources/test_pdf.pdf";
    public static final String DES = "src/main/resources/test_pdf_old.pdf";
    
    /// \ref t16_1 "task 16.1"
    public DrawTextField(MainCanvas _canvas) {
   
    }
    /// \ref t16_1 "task 16.1"
    @Override
    public void execute() {
  

    }

    /// \brief on click creates text field at click location
    /// \ref t16_1 "task 16.1"
    @Override
    public Action handle(Event event) {
        if (!(event instanceof MouseEvent))
            return this;
        
        byte[] array = new byte[7];
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        MouseEvent mouseEvent = (MouseEvent) event;
        Point2D mousePosition = new Point2D(mouseEvent.getX(), mouseEvent.getY());

        Point2D currentPoint = new Point2D(mouseEvent.getX(), mouseEvent.getY());
        float x = (float) currentPoint.getX();

        float y = (float) (780.0 - currentPoint.getY());

        if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {

            PdfDocument pdfDoc = null;
            try {
                pdfDoc = new PdfDocument(new PdfWriter(DES).setSmartMode(true));
            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            PdfDocument srcDoc = null;
            try {
                srcDoc = new PdfDocument(new PdfReader(SRC));
            } catch (IOException e1) {
                
                e1.printStackTrace();
            }
            srcDoc.copyPagesTo(1, srcDoc.getNumberOfPages(), pdfDoc);

            Document doc = new Document(pdfDoc);

            PdfAcroForm form = PdfAcroForm.getAcroForm(doc.getPdfDocument(), true);
            PdfTextFormField nameField = PdfTextFormField.createText(doc.getPdfDocument(), new Rectangle(x, y, 150, 15),
                    generatedString, "");
            form.addField(nameField);
            pdfDoc.close();
            srcDoc.close();

            File file = new File("src/main/resources/test_pdf.pdf");

            File rename = new File("src/main/resources/test_pdf_old_old.pdf");

            boolean flag = file.renameTo(rename);

            if (flag == true) {
                System.out.println("File Successfully Rename");
            }
  
            else {
                System.out.println("Operation Failed");
            }

            file = new File("src/main/resources/test_pdf_old.pdf");

            rename = new File("src/main/resources/test_pdf.pdf");

 
            flag = file.renameTo(rename);

     
            if (flag == true) {
                System.out.println("File Successfully Rename");
            }
         
            else {
                System.out.println("Operation Failed");
            }

      
            file = new File("src/main/resources/test_pdf_old_old.pdf");

     
            rename = new File("src/main/resources/test_pdf_old.pdf");

       
            flag = file.renameTo(rename);

       
            if (flag == true) {
                System.out.println("File Successfully Rename");
            }
        
            else {
                System.out.println("Operation Failed");
            }

        }

        return null;
    }

    /// \ref t16_1 "task 16.1"
    @Override
    public boolean isComplete() {
       
        return false;
    }

    /// \ref t16_1 "task 16.1"
    @Override
    public boolean contains(Point2D point) {
       
        return false;
    }

    /// \ref t16_1 "task 16.1"
    @Override
    public void pdfExecute(PdfCanvas canvas, PdfPage page) {
        

    }

}
