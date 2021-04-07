/**
 * 
 */
package com.groupseven.pdfproject;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
/**
 * @author Cassandra Mae
 *\defgroup Selection
 *@{
 *\brief Selects the shape that is drawn on the canvas.
 *\ref t18_1 "Task 18.1"
 */
public class SelectTool implements HandlingEvents{
	
	private MainCanvas canvas;
	private Shapes selectedShape;
	private Point2D pressedPoint;
	
	///\ref t18_1 "task 18.1"
	/// @param canvas the canvas to select the shape 
	public SelectTool(MainCanvas canvas) {
		this.canvas = canvas;
	}
	
	/// \brief Selects the shape drawn on the canvas
	/// \ref t18_1 "Task 18.1"
	@Override
	public void Event(MouseEvent event) {
		Point2D currentPoint = new Point2D(event.getX(), event.getY());

        if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
            pressedPoint = currentPoint;
            selectedShape = null;
            for (Shapes obj : canvas.getShapes())
                if (obj.contains(currentPoint)) {
                    selectedShape = obj;
                    obj.setElementSelected(true);
                } else
                    obj.setElementSelected(false);

}
        

//        canvas.update();
    }

	/// \brief Removes the selected shape
	/// \ref t18_1 "task 18.1"
    @Override
    public void Event(KeyEvent k_event) {
        if (selectedShape != null && k_event.getCode() == KeyCode.DELETE || k_event.getCode() == KeyCode.BACK_SPACE) {
            canvas.getShapes().remove(selectedShape);
//            canvas.update();
        }
    }

}
/**@}**/
