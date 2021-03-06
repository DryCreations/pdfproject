package com.groupseven.pdfproject.utilities;

import com.groupseven.pdfproject.TriConsumer;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

///
/// @author hayde
///

/// \brief This class performs the action of drawing line and rectangle, select an object and erasing the drawn shape or line on a PdfCanvas.
/// \ref t8_5 "Task 8.5"
/// \ref t9_1 "Task 9.1"
/// \ref t9_1_1 "Task 9.1.1"
/// \ref t9_1_2 "Task 9.1.2"
/// \ref t18_1 "Task 18.1"
public class PdfAction {

    /// \ref t9_1_1 "Task 9.1.1"
    /// \brief Draws a line on PDF Canvas
    public static final TriConsumer<PdfCanvas, PdfPage, Shape> DRAW_LINE = (canvas, page, object) -> {
        if (!(object instanceof Line))
            return;

        Line line = (Line) object;

        canvas.saveState();

        com.itextpdf.kernel.geom.Rectangle size = page.getPageSize();

        Color c = (Color) line.getStroke();

        int red = (int) (c.getRed() * 255);
        int green = (int) (c.getGreen() * 255);
        int blue = (int) (c.getBlue() * 255);

        canvas.setColor(new DeviceRgb(red, green, blue), true);

        canvas.setStrokeColor(new DeviceRgb(red, green, blue))
                .moveTo(line.getStartX(), size.getHeight() - line.getStartY())
                .lineTo(line.getEndX(), size.getHeight() - line.getEndY()).closePathStroke();

        canvas.restoreState();
    };

    /// \ref t9_1_2 "Task 9.1.2"
    /// \brief Erases the line or shape drawn on the PDF canvas.
    public static final TriConsumer<PdfCanvas, PdfPage, Shape> ERASE = (canvas, page, object) -> {
        if (!(object instanceof Rectangle))
            return;

        Rectangle rectangle = (Rectangle) object;

        canvas.saveState();

        com.itextpdf.kernel.geom.Rectangle size = page.getPageSize();

        Color c = (Color.WHITE);

        int red = (int) (c.getRed() * 255);
        int green = (int) (c.getGreen() * 255);
        int blue = (int) (c.getBlue() * 255);

        canvas.setColor(new DeviceRgb(red, green, blue), true);

        canvas.rectangle(rectangle.getX(), size.getHeight() - rectangle.getY() - rectangle.getHeight(),
                rectangle.getWidth(), rectangle.getHeight()).fill();

        canvas.restoreState();
    };

    /// \ref t8_5 "Task 8.5"
    /// \ref t9_1_1 "Task 9.1.1"
    /// \brief draws a rectangle on the PDF canvas
    public static final TriConsumer<PdfCanvas, PdfPage, Shape> DRAW_RECTANGLE = (canvas, page, object) -> {
        if (!(object instanceof Rectangle))
            return;

        Rectangle rectangle = (Rectangle) object;

        canvas.saveState();

        com.itextpdf.kernel.geom.Rectangle size = page.getPageSize();

        Color c = (Color) rectangle.getFill();

        int red = (int) (c.getRed() * 255);
        int green = (int) (c.getGreen() * 255);
        int blue = (int) (c.getBlue() * 255);

        canvas.setColor(new DeviceRgb(red, green, blue), true);

        canvas.rectangle(rectangle.getX(), size.getHeight() - rectangle.getY() - rectangle.getHeight(),
                rectangle.getWidth(), rectangle.getHeight()).fill();

        canvas.restoreState();
    };

    /// \ref t18_1 "Task 18.1"
    /// \brief selects the shape drawn on the PDF canvas
    public static final TriConsumer<PdfCanvas, PdfPage, Shape> SELECT = (canvas, page, object) -> {
        if (!(object instanceof Rectangle))
            return;

        Rectangle rectangle = (Rectangle) object;
    };
}
