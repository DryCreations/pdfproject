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
import com.itextpdf.forms.fields.PdfButtonFormField;
import com.itextpdf.forms.fields.PdfFormField;
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
import javafx.scene.paint.Color;

/**
 * @author Hunter Gongloff
 * 
 * @{ \brief This is the class to create radio buttons on user click \ref 16_2 "Task 16.2"
 */
public class DrawRadioButton implements Action {

    private MainCanvas _canvas;
    private Point2D _origin;
    private javafx.scene.shape.Rectangle _rectangle;
    private boolean _isComplete;
    protected boolean _isLinked;
    protected String _link;

    /// \ref t16_2 "task 16.2"
    public DrawRadioButton(MainCanvas canvas) {
        _canvas = canvas;
    }

    /// \ref t16_2 "task 16.2"
    @Override
    public void execute() {

        DrawingAction.DRAW_RECTANGLE.accept(_canvas, _rectangle);

    }

    /// \ref t16_2 "task 16.2"
    @Override
    public Action handle(Event event) {
        if (!(event instanceof MouseEvent))
            return this;

        MouseEvent mouseEvent = (MouseEvent) event;

        if (_origin == null)
            _origin = new Point2D(mouseEvent.getX(), mouseEvent.getY());
        else {
            double squareHeight = mouseEvent.getY() - _origin.getY();
            double squareWidth = mouseEvent.getX() - _origin.getX();

            if (squareHeight > squareWidth)
                squareWidth = squareHeight;
            else
                squareHeight = squareWidth;

            _rectangle = new javafx.scene.shape.Rectangle(_origin.getX(), _origin.getY(), squareWidth, squareHeight);
            _rectangle.setFill(Color.web("#F1F4FF"));
        }

        _isComplete = (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED);
        return this;

    }

    /// \ref t16_2 "task 16.2"
    @Override
    public boolean isComplete() {
        return _isComplete;
    }

    /// \ref t16_2 "task 16.2"
    @Override
    public boolean contains(Point2D point) {
        return false;
    }

    /// \ref t16_2 "task 16.2"
    @Override
    public void pdfExecute(PdfCanvas canvas, PdfPage page) {

        byte[] array = new byte[7];
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        float rectangleHeight = (float) _rectangle.getHeight();
        float rectangleWidth = (float) _rectangle.getWidth();

        PdfAcroForm form = PdfAcroForm.getAcroForm(canvas.getDocument(), true);
        PdfButtonFormField group = PdfFormField.createRadioGroup(canvas.getDocument(), "group", "");
        PdfFormField.createRadioButton(canvas.getDocument(),
                new Rectangle((float) _origin.getX(),
                        (float) (page.getPageSize().getHeight() - _origin.getY() - rectangleHeight), rectangleWidth,
                        rectangleHeight),
                group, "groups");
        form.addField(group);

    }

}
