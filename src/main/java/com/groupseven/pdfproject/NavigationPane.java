package com.groupseven.pdfproject;

import com.sun.pdfview.PDFPage;
import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.text.Document;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

/**
 * \defgroup NavBar Navigation Pane
 * 
 * @author Cassandra Mae
 * 
 * @{
 *
 */
///
/// \ref task_1_1 "Task 1.1"

/// This class provides the functionality for the navigation pane while 
/// accessing the PDF. 

public class NavigationPane extends Application{

	/// \ref t1_1
	/// \ref t1_2 
	/// 

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	public VBox getNavigationPane() {
		VBox template = new VBox();


		return template;
	}
	/// \ref t1_1
	public VBox getNavigationPane(DocumentModel read) throws IOException{
		VBox template = new VBox();

		for(int i = 0; i < read.pageNumReturn(); i++){
			PageModel  displayed = convertPdfToImage(read, i);
			template.getChildren().add(displayed.getNode());
		}
		return template;
	}
	/// \ref t1_3  
	///	This method is called only after the [drag and drop event](\ref t_2 "task 1.2").	
	public void PdfReorder() {

	}
	/// \ref t1_1
	public PageModel convertPdfToImage(DocumentModel fileName, int Num) throws IOException {

		return fileName.getPage(Num);
	}



	///\ref t1_1
	public static void getThumbnail() {

	}

}

/** @} */