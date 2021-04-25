package com.groupseven.pdfproject.utilities;

import java.nio.charset.Charset;
import java.util.Random;

import com.groupseven.pdfproject.MainCanvas;
import com.groupseven.pdfproject.model.Action;
import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfTextFormField;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import javafx.event.Event;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * @author Hunter Gongloff
 * 
 * @{ \brief This is the class to create a text field on user click \ref 16_1 "Task 16.1"
 */
public class DrawTextField implements Action {
	
    private MainCanvas _canvas;
    private Point2D _origin;
    private javafx.scene.shape.Rectangle _rectangle;
    private boolean _isComplete;
    protected boolean _isLinked;
    protected String _link;
    

    /// \ref t16_1 "task 16.1"
    public DrawTextField(MainCanvas canvas) {
        _canvas = canvas;
    }

    /// \ref t16_1 "task 16.1"
    @Override
    public void execute() {
        
    	DrawingAction.DRAW_RECTANGLE.accept(_canvas, _rectangle);

    }

    /// \ref t16_1 "task 16.1"
    @Override
    public Action handle(Event event) {
    	if (!(event instanceof MouseEvent))
            return this;

        MouseEvent mouseEvent = (MouseEvent) event;

        if (_origin == null)
            _origin = new Point2D(mouseEvent.getX(), mouseEvent.getY());
        else {
        	
            _rectangle = new javafx.scene.shape.Rectangle(_origin.getX(), _origin.getY(), mouseEvent.getX() - _origin.getX(),
                    mouseEvent.getY() - _origin.getY());
            _rectangle.setFill(Color.web("#F1F4FF"));
        }

        _isComplete = (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED);
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
        byte[] array = new byte[7]; 
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        
        float rectangleHeight = (float) _rectangle.getHeight();
        float rectangleWidth = (float) _rectangle.getWidth();
        
        PdfAcroForm form = PdfAcroForm.getAcroForm(canvas.getDocument(), true);
        PdfTextFormField nameField = PdfTextFormField
                .createText(
                        canvas.getDocument(), new Rectangle((float) _origin.getX(),
                                (float) (page.getPageSize().getHeight() - _origin.getY() - rectangleHeight), rectangleWidth, rectangleHeight),
                        generatedString, "");
        float fontSize = (float) (rectangleHeight * 0.8);
        nameField.setFontSize(fontSize);
        form.addField(nameField);
    }
}









