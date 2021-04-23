/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupseven.pdfproject;

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
public class DocumentModelTest {
    
    public DocumentModelTest() {
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
     * Test of getPage method, of class DocumentModel.
     */
    @Test
    public void testGetPage() {
        System.out.println("getPage");
        int i = 0;
        DocumentModel instance = new DocumentModel();
        PageModel expResult = null;
        PageModel result = instance.getPage(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDimensions method, of class DocumentModel.
     */
    @Test
    public void testSetDimensions() {
        System.out.println("setDimensions");
        DocumentModel instance = new DocumentModel();
        instance.setDimensions();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertAsset method, of class DocumentModel.
     */
    @Test
    public void testInsertAsset() {
        System.out.println("insertAsset");
        DocumentModel instance = new DocumentModel();
        instance.insertAsset();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertShape method, of class DocumentModel.
     */
    @Test
    public void testInsertShape() {
        System.out.println("insertShape");
        DocumentModel instance = new DocumentModel();
        instance.insertShape();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of moveObject method, of class DocumentModel.
     */
    @Test
    public void testMoveObject() {
        System.out.println("moveObject");
        DocumentModel instance = new DocumentModel();
        instance.moveObject();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertTextBox method, of class DocumentModel.
     */
    @Test
    public void testInsertTextBox() {
        System.out.println("insertTextBox");
        DocumentModel instance = new DocumentModel();
        instance.insertTextBox();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of export method, of class DocumentModel.
     */
    @Test
    public void testExport() {
        System.out.println("export");
        DocumentModel instance = new DocumentModel();
        instance.export();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
