/**
 * 
 */
package com.groupseven.pdfproject;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Cassandra Mae
 *  \defgroup canvas
 *  @{
 *  \brief This is the main canvas which show PDF page and allow to draw elements on it and 
 *  select the drawn element. 
 *  \ref 18_1 "Task 18.1" To accomplish this task it is required to have a canvas which can keep the 
 *  track of drawn elements to facilitate the selection of an element.
 */
public class MainCanvas extends Pane{
	
	protected Canvas canvas;
	private List<Shapes> shapes;
	private HandlingEvents eventHandler;
	
	
	public MainCanvas() {
        canvas = new Canvas(App.WINDOW_WIDTH, App.WINDOW_HEIGHT);
        shapes = new ArrayList<>();
        getChildren().add(canvas);

        EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                eventHandler.Event(e);
            }
        };

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, handler);
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, handler);
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, handler);
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
    }
	
	/// \brief keep the track of all the shapes on canvas
	/// \ref t18_1 "task 18.1" 
	
	public List<Shapes> getShapes() {
        return shapes;
    }
	
	/// \return the current event handler
	/// \ref t18_1 "task 18.1"
	 public HandlingEvents getEventHandler() {
	        return eventHandler;
	    }
	 
	 /// \brief sets the event handler
	/// \ref t18_1 "task 18.1"
	 /// @param  eventHandler the new event handler
	 public void setEventHandler(HandlingEvents eventHandler) {
	        this.eventHandler = eventHandler;
	    }
	 
	
	 public void clear(Color color) {
	        GraphicsContext gc = canvas.getGraphicsContext2D();
	        gc.setFill(color);
	        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
	    }
	 
	 /// \brief clears the canvas leaving a fresh new white background
	/// \ref t18_1 "task 18.1"
	 public void clear() {
	        clear(Color.WHITE);
	    }
	 
	 /// \brief draws the shape on the canvas and when selected highlights the shape
	/// \ref t18_1 "task 18.1"
	 private void render() {
	        GraphicsContext gc = canvas.getGraphicsContext2D(); 
	        for (Shapes myShape : shapes) {
	            myShape.draw(gc);
	            if(myShape.selected) {
	            	shapes.get(0).HghlightShape(gc);
	            }
	        }
	 }
	 
	 /// \brief The canvas is updated every time the shape is selected  or deselected 
	/// or whenever the backspace or delete key is pressed to remove the selected shape.
	/// \ref t18_1 "task 18.1"
	 public void update() {
	        clear();
	        render();
	    }
	 
	/// \ref t18_1 "task 18.1"
	 private void deselectShapes() {
	        for (Shapes shape : shapes) {
	            shape.setElementSelected(false);
	        }
	        update();
	 }

}

/**@}*/
