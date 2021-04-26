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
import javafx.scene.input.MouseEvent;

/**
 * @author Hunter Gongloff
 * 
 * @{ \brief This is the class to create a text field on user click \ref 16_1 "Task 16.1"
 */
public class DrawTextField implements Action {

    /// \ref t16_1 "task 16.1"
    public DrawTextField(MainCanvas _canvas) {
        // TODO Auto-generated constructor stub
    }

    /// \ref t16_1 "task 16.1"
    @Override
    public void execute() {
        // TODO Auto-generated method stub

    }

    /// \brief on click creates text field at click location
    /// \ref t16_1 "task 16.1"
    @Override
    public Action handle(Event event) {
        return null;

    }

    /// \ref t16_1 "task 16.1"
    @Override
    public boolean isComplete() {
        // TODO Auto-generated method stub
        return false;
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
        // TODO Auto-generated method stub

    }
}