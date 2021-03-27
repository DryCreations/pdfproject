/**
 * 
 */
package com.groupseven.pdfproject;

/**
 * @author Cassandra Mae
 *\defgroup DrawingTools 
 *@{
 *\brief The options available to draw and select the shape
 *\ref t18_1 "task 18.1"
 */
public enum ToolsOptions {
	RECTANGLE("Rectangle"),
	SELECT("Select");
	
	public final String label;
	
	
	ToolsOptions(String label) {
        this.label = label;
    }
}
/**@}**/