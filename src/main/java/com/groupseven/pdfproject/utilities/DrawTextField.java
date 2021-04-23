package com.groupseven.pdfproject.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
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
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * @author Hunter Gongloff
 * 
 * @{ \brief This is the class to create a text field on user click \ref 16_1 "Task 16.1"
 */
public class DrawTextField implements Action {

    private MainCanvas _canvas;
    private Point2D _origin;
    private boolean _isComplete;
    
    /// \ref t16_1 "task 16.1"
    public DrawTextField(MainCanvas canvas) {
        _canvas = canvas;
    }

    /// \ref t16_1 "task 16.1"
    @Override
    public void execute() {
        System.out.println("test");
        GraphicsContext gc = _canvas.getCanvas().getGraphicsContext2D();
        Paint currentFill = gc.getFill();
        gc.setFill(Color.ALICEBLUE);
        gc.fillRect(_origin.getX(), _origin.getY(), 100, 10);
        gc.setFill(currentFill);

    }

    /// \brief on click creates text field at click location
    /// \ref t16_1 "task 16.1"
    @Override
    public Action handle(Event event) {
        if (!(event instanceof MouseEvent))
            return this;

        MouseEvent mouseEvent = (MouseEvent) event;

        _origin = new Point2D(mouseEvent.getX(), mouseEvent.getY());
        _isComplete = true;
        return this;
    }

    /// \ref t16_1 "task 16.1"
    @Override
    public boolean isComplete() {
        // TODO Auto-generated method stub
        return _isComplete;
    }

    /// \ref t16_1 "task 16.1"
    @Override
    public boolean contains(Point2D point) {
        // TODO Auto-generated method stub
        return false;
    }

    /// \ref t16_1 "task 16.1"
    @Override
    public void pdfExecute(PdfCanvas canvas, PdfPage page) {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        PdfAcroForm form = PdfAcroForm.getAcroForm(canvas.getDocument(), true);
        PdfTextFormField nameField = PdfTextFormField.createText(canvas.getDocument(), new Rectangle((float)_origin.getX(), (float)(page.getPageSize().getHeight() - _origin.getY()), 150, 15),generatedString, "");
        form.addField(nameField);
    }
}