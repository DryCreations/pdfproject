/**
<<<<<<< HEAD
 *
 */
package com.groupseven.pdfproject;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.FieldAccessor_Ref;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Pair;
import sun.security.util.Cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
=======
 *
 */
package com.groupseven.pdfproject;

import java.awt.image.BufferedImage;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
>>>>>>> origin/main


/**
 * @author Cassandra Mae
 *  \defgroup canvas
 *  @{
 *  \brief This is the main canvas which show PDF page and allow to draw elements on it and 
 *  select the drawn element. 
 *  \ref 18_1 "Task 18.1" To accomplish this task it is required to have a canvas which can keep the 
 *  track of drawn elements to facilitate the selection of an element.
 */
<<<<<<< HEAD
public class MainCanvas extends Pane {

    protected Canvas canvas;
    private List<Shapes> shapes;
    private HandlingEvents eventHandler;
    private final Stack<Pair<Consumer<Shape>,Shape>> _undoStack;
    private Stack<Pair<Consumer<Shape>,Shape>> _redoStack;

    public MainCanvas() {
        _undoStack = new Stack<>();
        _redoStack = new Stack<>();
        canvas = new Canvas(App.WINDOW_WIDTH, App.WINDOW_HEIGHT);
        canvas.getGraphicsContext2D().setFill(Color.PINK); // TODO Charles: Make Dynamic
        shapes = new ArrayList<>();
        getChildren().add(canvas);
    }

    public Canvas getCanvas() {
        return this.canvas;
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

    public void setHandlerForTypes(EventHandler handler, EventType... eventTypes) {
        for (EventType type: eventTypes)
            canvas.addEventHandler(type, handler);
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
            if (myShape.selected) {
                shapes.get(0).highlightShape(gc);
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


    public void addAction(Pair<Consumer<Shape>, Shape> actionAndArgPair){
        _undoStack.push(actionAndArgPair);
    }

    public void undo() {
        if (_undoStack.empty())
            return;
        _redoStack.push(_undoStack.pop());
        refresh();
    }

    public void refresh() {
        clearScreen();
        _undoStack.forEach(
                consumerPair -> consumerPair.getKey().accept(consumerPair.getValue()));
    }

    public void redo() {
        if (_redoStack.empty())
            return;

        Pair<Consumer<Shape>, Shape> redoPair = _redoStack.pop();
        _undoStack.push(redoPair);
        redoPair.getKey().accept(redoPair.getValue());
    }

    public void clearRedo() {
        _redoStack = new Stack<>();
    }

    private void clearScreen() {
        Canvas canvas = getCanvas();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0,canvas.getWidth(), canvas.getWidth());
    }

    public Stack<Pair<Consumer<Shape>, Shape>> getUndoStack() {
        return _undoStack;
    }

    public Stack<Pair<Consumer<Shape>, Shape>> getRedoStack() {
        return _redoStack;
    }

    public void setRedoStack(Stack<Pair<Consumer<Shape>, Shape>> stack) {
        _redoStack = stack;
    }
=======
public class MainCanvas extends Pane{

	protected Canvas canvas;
	private List<Shape> shapes;
	private HandlingEvents eventHandler;
        private Image bg;
        private double width, height;

	public MainCanvas(double width, double height, Image bg) {
            this.width = width;
            this.height = height;
            canvas = new Canvas(width, height);
            shapes = new ArrayList<>();
            getChildren().add(canvas);
            this.bg = bg;

            // default event handler to avoid errors in console.
            eventHandler = new HandlingEvents() {
                @Override
                public void Event(MouseEvent mouseEvent) {

                }

                @Override
                public void Event(KeyEvent keyEvent) {

                }
            };

            EventHandler<MouseEvent> handler = (MouseEvent e) -> {
                eventHandler.Event(e);
            };

            canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, handler);
            canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, handler);
            canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, handler);
            canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
        }

	/// \brief keep the track of all the shapes on canvas
	/// \ref t18_1 "task 18.1"

	public List<Shape> getShapes() {
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
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.drawImage(bg,0.0,0.0,canvas.getWidth(),canvas.getHeight());
        }

	 /// \brief draws the shape on the canvas and when selected highlights the shape
	/// \ref t18_1 "task 18.1"
	 private void render() {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            for (Shape myShape : shapes) {
                myShape.draw(gc);
                if(myShape.selected) {
                    myShape.HighlightShape(gc);
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
            for (Shape shape : shapes) {
                shape.setElementSelected(false);
            }
            update();
	 }

         public GraphicsContext getGraphicsContext() {
             return canvas.getGraphicsContext2D();
         }
>>>>>>> origin/main
}

/**@}*/
