package com.groupseven.pdfproject;

import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * \defgroup NavBar Navigation Pane
 * @author Cassandra Mae
 * 
 * @{
 *
 */
/// \ref User_Story_1
/// \ref task_1_1
/// This class provides the functionality for the navigation pane while 
/// accessing the PDF. 

public class NavigationPane extends Application{
	
	/// Ref: task 1.1
	/// Ref: task 1.2 
	/// 

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	/// Ref: task 1.1
		public VBox getNavigationPane() {
			VBox navBar =  new VBox();
			return navBar;
		}
	/// Ref: task 1.3 
	///	This method is called only after the [drag and drop event](Ref: task 1.2).	
		public void PdfReorder() {
				
		}

}
/**@}*/