/**
 * 
 */
package com.groupseven.pdfproject;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import javax.swing.event.HyperlinkEvent;

import com.itextpdf.kernel.pdf.filters.DoNothingFilter;
import com.itextpdf.layout.element.Link;

import javafx.event.ActionEvent;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * @author Cassandra Mae \defgroup Selection
 * @{ \brief Selects the shape that is drawn on the canvas. \ref t18_1 "Task
 *    18.1"
 */
public class SelectTool implements HandlingEvents {

	private MainCanvas canvas;
	private Shape selectedShape;
	private Point2D pressedPoint;
	private ContextMenu contextMenu;

	/// \ref t18_1 "task 18.1"
	/// @param canvas the canvas to select the shape
	public SelectTool(MainCanvas canvas) {
		this.canvas = canvas;
	}

	/// \brief Selects the shape drawn on the canvas and allows to link the shape
	/// and go to that linked web page
	/// \ref t18_1 "Task 18.1"
	/// \ref t18_2 "Task 18.2"
	/// To provide link User is expected to right click and not press the
	/// right_mouse_button and drag.
	@Override
	public void Event(MouseEvent event) {
		Point2D currentPoint = new Point2D(event.getX(), event.getY());

		if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
			pressedPoint = currentPoint;
			selectedShape = null;
			for (Shape obj : canvas.getShapes()) {
				if (obj.contains(currentPoint)) {
					selectedShape = obj;
					obj.setElementSelected(true);

				} else {
					obj.setElementSelected(false);
				}
			}
			try {
				if (contextMenu.isShowing()) {
					contextMenu.hide();
				}
			} catch (Exception e) {
				// do nothing
			}
		}

		/**
		 * Only accepts right click and no mouse drag
		 */
		if (event.isSecondaryButtonDown()) {

			for (Shape obj : canvas.getShapes())
				if (obj.contains(currentPoint)) {

					contextMenu = new ContextMenu();
					MenuItem linkObj = new MenuItem("Link Object");
					contextMenu.getItems().add(linkObj);
					contextMenu.show(canvas, event.getSceneX(), event.getSceneY());

					linkObj.setOnAction(e -> {
						TextInputDialog dialogBox = new TextInputDialog("http://my%Link%here");
						dialogBox.setTitle("Link");
						dialogBox.setHeaderText("What is the desired Link ");
						dialogBox.setContentText("Link");

						Optional<String> linkProvided = dialogBox.showAndWait();
						if (linkProvided.isPresent()) {

							Hyperlink url = new Hyperlink();
							url.setText(linkProvided.get());
							obj.setIsElementLinked(true);
							obj.setUrl(url.getText());
							canvas.getChildren().add(url);

						}
					});
				}

		}

		/***
		 * 
		 * Takes to the linked web page only if "Control" key is pressed down while
		 * mouse clicked. User is expected to provide a proper link of a web page
		 */
		if (event.isControlDown()) {
			canvas.getScene().setCursor(Cursor.HAND);
			if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
				for (Shape obj : canvas.getShapes()) {
					if (obj.contains(currentPoint)) {
						if (obj.isLinked) {
							if (Desktop.isDesktopSupported()) {
								try {
									Desktop.getDesktop().browse(new URI(obj.link));
								} catch (IOException e1) {
									e1.printStackTrace();
								} catch (URISyntaxException e1) {
									e1.printStackTrace();
								}
							}
						}
					}
				}
			}

		} else {
			canvas.getScene().setCursor(Cursor.DEFAULT);
		}

		canvas.update();
	}

	/// \brief Removes the selected shape and changes the cursor when Control key is
	/// pressed.
	/// \ref t18_1 "task 18.1"
	/// \ref t18_2 "task 18.2"
	@Override
	public void Event(KeyEvent k_event) {
		if (selectedShape != null && k_event.getCode() == KeyCode.DELETE || k_event.getCode() == KeyCode.BACK_SPACE) {
			canvas.getShapes().remove(selectedShape);
			canvas.update();
		}
		if (k_event.isControlDown())
			canvas.getScene().setCursor(Cursor.HAND);
		else
			canvas.getScene().setCursor(Cursor.DEFAULT);

	}

}
/** @} **/
