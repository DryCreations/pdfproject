package com.groupseven.pdfproject.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.groupseven.pdfproject.MainCanvas;
import com.groupseven.pdfproject.model.Action;
import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfButtonFormField;
import com.itextpdf.forms.fields.PdfFormField;
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

public class DrawCheckBox implements Action {
	
	public static final String SRC = "src/main/resources/test_pdf.pdf";
	public static final String DES = "src/main/resources/test_pdf2.pdf";

	public DrawCheckBox(MainCanvas _canvas) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
    public Action handle(Event event) {
        if (!(event instanceof MouseEvent))
            return this;

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
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			srcDoc.copyPagesTo(1, srcDoc.getNumberOfPages(), pdfDoc);
			
			Document doc = new Document(pdfDoc);
			PdfAcroForm form = PdfAcroForm.getAcroForm(doc.getPdfDocument(), true);
	        PdfButtonFormField checkField = PdfFormField.createCheckBox(doc.getPdfDocument(), new Rectangle(x, y, 15, 15),
	                    "experience".concat(String.valueOf(1)), "Off", PdfFormField.TYPE_CHECK);
	        form.addField(checkField);
	        
			pdfDoc.close();
			srcDoc.close();
			
			// Create an object of the File class
	        // Replace the file path with path of the directory
	        File file = new File("src/main/resources/test_pdf.pdf");
	  
	        // Create an object of the File class
	        // Replace the file path with path of the directory
	        File rename = new File("src/main/resources/test_pdf_old.pdf");
	        
	        if (rename.exists()) {
	        	rename.delete();
	        }
	  
	        // store the return value of renameTo() method in
	        // flag
	        boolean flag = file.renameTo(rename);
	  
	        // if renameTo() return true then if block is
	        // executed
	        if (flag == true) {
	            System.out.println("File Successfully Rename");
	        }
	        // if renameTo() return false then else block is
	        // executed
	        else {
	            System.out.println("Operation Failed");
	        }
	        
	        // Create an object of the File class
	        // Replace the file path with path of the directory
	        file = new File("src/main/resources/test_pdf2.pdf");
	  
	        // Create an object of the File class
	        // Replace the file path with path of the directory
	        rename = new File("src/main/resources/test_pdf.pdf");
	  
	        // store the return value of renameTo() method in
	        // flag
	        flag = file.renameTo(rename);
	  
	        // if renameTo() return true then if block is
	        // executed
	        if (flag == true) {
	            System.out.println("File Successfully Rename");
	        }
	        // if renameTo() return false then else block is
	        // executed
	        else {
	            System.out.println("Operation Failed");
	        }
			
			
		}
		

        return null;
    }


	
	@Override
	public boolean isComplete() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Point2D point) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void pdfExecute(PdfCanvas canvas, PdfPage page) {
		// TODO Auto-generated method stub
		
	}

}