package com.groupseven.pdfproject.utilities;

import com.groupseven.pdfproject.MainCanvas;
import com.groupseven.pdfproject.model.Action;
import com.groupseven.pdfproject.model.Draggable;
import com.groupseven.pdfproject.model.Selectable;
import com.groupseven.pdfproject.utilities.DrawingAction;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import javafx.event.Event;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Charles Witherspoon
 * 
 * @{ \brief This class represents an action to select an object on the canvas \ref t18_1 "Task 18.1"
 */
public class Select implements Action {
    private MainCanvas _canvas;
    Selectable _selectedDrawing;
    private ContextMenu contextMenu;

    public Select(MainCanvas canvas) {
        _canvas = canvas;
    }

    @Override
    public void execute() {
        if (_selectedDrawing != null) {
            _selectedDrawing.select();
            DrawingAction.SELECT.accept(_canvas, _selectedDrawing.getSelection());
        }
    }

    @Override
    public Action handle(MouseEvent event) {

     MouseEvent mouseEvent = (MouseEvent) event;

        if(event.getEventType()==MouseEvent.MOUSE_PRESSED)
        	 handlePress(mouseEvent);
        
        if(event.getEventType()==MouseEvent.MOUSE_DRAGGED)
        	handleDrag(mouseEvent);
        
        if(event.getEventType()==MouseEvent.MOUSE_RELEASED)
        	handleRelease(mouseEvent);
        
        /**
		 * Only accepts right click and no mouse drag
		 */
        if(event.isSecondaryButtonDown()) {
        	contextMenu = new ContextMenu();
			MenuItem linkObj = new MenuItem("Link Object");
			contextMenu.getItems().add(linkObj);
			contextMenu.show(_canvas, event.getScreenX(), event.getScreenY());

			linkObj.setOnAction(e -> {
				TextInputDialog dialogBox = new TextInputDialog("http://my%Link%here");
				dialogBox.setTitle("Link");
				dialogBox.setHeaderText("What is the desired Link ");
				dialogBox.setContentText("Link");

				Optional<String> linkProvided = dialogBox.showAndWait();
				if (linkProvided.isPresent()) {

					Hyperlink url = new Hyperlink();
					url.setText(linkProvided.get());
					_selectedDrawing.setisLinked(true);
					_selectedDrawing.setUri(url.getText());
					_canvas.getChildren().add(url);

				}
			});
		}
        
        /***
		 * 
		 * Takes to the linked web page only if "Control" key is pressed down while
		 * mouse clicked. User is expected to provide a proper link of a web page
		 */
		if (event.isControlDown()) {
			_canvas.getScene().setCursor(Cursor.HAND);
			if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
				
						if (_selectedDrawing.getisLinked()) {
							if(_selectedDrawing.getLink()!= null) {
							if (Desktop.isDesktopSupported()) {
								try {
									Desktop.getDesktop().browse(new URI(_selectedDrawing.getLink()));
								} catch (IOException e1) {
									e1.printStackTrace();
								} catch (URISyntaxException e1) {
									e1.printStackTrace();
								}
							}
						}
					}
			}

		} else {
			_canvas.getScene().setCursor(Cursor.DEFAULT);
		}
        
       	 
        return this;
    }

    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public boolean contains(Point2D point) {
        return ((Action) _selectedDrawing).contains(point);
    }

    private void handlePress(MouseEvent mouseEvent) {
        if (_selectedDrawing != null) {
        	try {
				if (contextMenu.isShowing()) {
					contextMenu.hide();
				}
			} catch (Exception e) {
				// do nothing
			}
        	 _selectedDrawing.unselect();
        }
           

        List<Action> undos = new ArrayList<>(_canvas.getUndoStack());
        Collections.reverse(undos);
        Point2D mousePosition = new Point2D(mouseEvent.getX(), mouseEvent.getY());
        Optional<Action> firstActionContainingMousePosition = undos.stream()
                .filter(action -> action.contains(mousePosition) && action instanceof Selectable).findFirst();

        Selectable previousSelection = _selectedDrawing;
        _selectedDrawing = (Selectable) firstActionContainingMousePosition.orElse(null);

        if (previousSelection != null && previousSelection.equals(_selectedDrawing))
            _selectedDrawing.unselect();
    }

    private void handleDrag(MouseEvent mouseEvent) {
        if (_selectedDrawing != null && _selectedDrawing instanceof Draggable) {
            Action movedRectangle = ((Draggable) _selectedDrawing).dragTo(mouseEvent.getX(), mouseEvent.getY());
            _canvas.getUndoStack().push(movedRectangle);
            _selectedDrawing = (Selectable) movedRectangle;
        }
    }

    private void handleRelease(MouseEvent mouseEvent) {

    }

    @Override
    public void pdfExecute(PdfCanvas canvas, PdfPage page) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose
        // Tools | Templates.

        System.out.println("Select");
    }


	@Override
	public void handle(KeyEvent k_event) {
		// TODO Auto-generated method stub
		if (k_event.isControlDown())
			_canvas.getScene().setCursor(Cursor.HAND);
		else
			_canvas.getScene().setCursor(Cursor.DEFAULT);

	}
}
/**
 * @}
 */