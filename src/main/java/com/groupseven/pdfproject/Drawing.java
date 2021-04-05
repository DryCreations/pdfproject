/**
 * 
 */
package com.groupseven.pdfproject;

import java.util.function.Supplier;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * @author Cassandra Mae
 *\defgroup Drawing
 *@{
 *\ref t18_1 "task 18.1" 
 *\brief This class helps in drawing the specific shape which can be selected to manipulate it. 
 */
public class Drawing implements HandlingEvents{
	
	private MainCanvas canvas;
	private Color color;
	private Shapes selectedShape;
	private Supplier<Shapes> provideShape;

	public Drawing(MainCanvas canvas, Supplier<Shapes> provider, Color color) {
        this.canvas = canvas;
        this.provideShape = provider;
        this.color = color;
    }
	
	
	@Override
    public void Event(MouseEvent m_event) {
        
        if (m_event.getEventType() == MouseEvent.MOUSE_PRESSED && selectedShape == null)
            addNewShape();

        if (selectedShape != null) {
            
            if (m_event.getEventType() == MouseEvent.MOUSE_RELEASED && !selectedShape.isValidShape())
                canvas.getShapes().remove(selectedShape);
            
            else
                selectedShape.Event(m_event);

            canvas.update();
        }
    }
	
	@Override
    public void Event(KeyEvent k_event) {
        if (selectedShape != null)
            selectedShape.Event(k_event);
    }
	
	private void addNewShape() {
        selectedShape = provideShape.get();
        selectedShape.setDidFinishDrawing(() -> selectedShape = null);
        selectedShape.setColor(color);
        canvas.getShapes().add(selectedShape);
    }
 

}
/**@}**/
