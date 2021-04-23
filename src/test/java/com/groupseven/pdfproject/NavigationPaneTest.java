/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupseven.pdfproject;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dwkal
 */
public class NavigationPaneTest {
    
    public NavigationPaneTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of start method, of class NavigationPane.
     */
    @Test
    public void testStart() throws Exception {
        System.out.println("start");
        Stage arg0 = null;
        NavigationPane instance = new NavigationPane();
        instance.start(arg0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getNavigationPane method, of class NavigationPane.
     */
    @Test
    public void testGetNavigationPane() {
        System.out.println("getNavigationPane");
        NavigationPane instance = new NavigationPane();
        VBox expResult = null;
        VBox result = instance.getNavigationPane();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of PdfReorder method, of class NavigationPane.
     */
    @Test
    public void testPdfReorder() {
        System.out.println("PdfReorder");
        NavigationPane instance = new NavigationPane();
        instance.PdfReorder();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of convertPdfToImage method, of class NavigationPane.
     */
    @Test
    public void testConvertPdfToImage() {
        System.out.println("convertPdfToImage");
        NavigationPane instance = new NavigationPane();
        instance.convertPdfToImage();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getThumbnail method, of class NavigationPane.
     */
    @Test
    public void testGetThumbnail() {
        System.out.println("getThumbnail");
        NavigationPane.getThumbnail();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
