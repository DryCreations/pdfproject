/**
 * 
 */
package com.groupseven.pdfproject;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;

import com.groupseven.pdfproject.HandlingEvents;
import com.groupseven.pdfproject.MainCanvas;
/**
 * @author Cassandra Mae
 *\defgroup Shapes
 *@{
 *\brief Base class for all the shapes.
 *\ref t18_1 "task 18.1" This class creates the shapes required in order to select them.
 */
public abstract class Shapes implements HandlingEvents{
	public MainCanvas canvas;
	protected List<Point2D> pointsOfShape;
	protected Color color;
    protected static boolean selected;
	protected Runnable finishedDrawing;
	 

	 public Shapes() {
	        selected = false;
	       
	    }
	 
	 public void setColor(Color color) {
	        this.color = color;
	    }
	 
	 /**
	  * \ref t18_1 "task 18.1"
	  * 
	  */
	 public void setElementSelected(boolean selected) {
	        this.selected = selected;
	    }
	 
	 
	 public void setDidFinishDrawing(Runnable didFinishDrawingCallback) {
	        this.finishedDrawing = didFinishDrawingCallback;
	    }
	 
	 /**
	  * \brief Draws the respective shape
	  * @param gc graphic context from the canvas
	  */
	 public abstract void draw(final GraphicsContext gc);
	 
	 /**
	  * \brief Highlights the respective shape
	  * @param gc graphic context from the canvas
	  */   
	    public abstract void highlightShape(final GraphicsContext gc);
	    
	    public boolean isValidShape() {
	        for (Point2D p : pointsOfShape)
	            if (p == null)
	                return false;

	        return true;
	    }
	    
	    public boolean contains(final Point2D point) {
	        final Point2D topLeft = getTopLeftPoint();
	        final Point2D bottomRight = getBottomRightPoint();

	        return point.getX() >= topLeft.getX() && point.getX() <= bottomRight.getX()
	                && point.getY() >= topLeft.getY() && point.getY() <= bottomRight.getY();
	    }
	    
	    /**
	     * \ref t18_1 "task 18.1" 
	     * @return the coordinates of the top left corner of the shape
	     */
	    public Point2D getTopLeftPoint() {
	        if (pointsOfShape.isEmpty())
	            return null;

	        double x = pointsOfShape.get(0).getX();
	        double y = pointsOfShape.get(0).getY();

	        for (int i = 1; i < pointsOfShape.size(); i++) {
	            Point2D p = pointsOfShape.get(i);
	            if (p.getX() < x)
	                x = p.getX();
	            if (p.getY() < y)
	                y = p.getY();
	        }

	        return new Point2D(x, y);
	    }
	    
	    /**
	     * \ref t18_1 "task 18.1" 
	     * @return the coordinates of the bottom right corner of the shape
	     */
	    public Point2D getBottomRightPoint() {
	        if (pointsOfShape.isEmpty())
	            return null;

	        double x = pointsOfShape.get(0).getX();
	        double y = pointsOfShape.get(0).getY();

	        for (int i = 1; i < pointsOfShape.size(); i++) {
	            Point2D p = pointsOfShape.get(i);
	            if (p.getX() > x)
	                x = p.getX();
	            if (p.getY() > y)
	                y = p.getY();
	        }

	        return new Point2D(x, y);
	    }
	    
	    public Rectangle getBound() {
	        final Point2D topLeft = getTopLeftPoint();
	        final Point2D bottomRight = getBottomRightPoint();
	        final Point2D delta = bottomRight.subtract(topLeft);
	        return new Rectangle(topLeft.getX(), topLeft.getY(), delta.getX(), delta.getY());
	    }

}
/**@}**/
