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
public class ExtractCodeTest {

    public ExtractCodeTest() {
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
     * Test of getCodeFromPdf method, of class ExtractCode.
     */
    @Test
    public void testGetCodeFromPdf() {
        System.out.println("getCodeFromPdf");
        ExtractCode instance = new ExtractCode();
        instance.getCodeFromPdf();
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of extractTo method, of class ExtractCode.
     */
    @Test
    public void testExtractTo() {
        System.out.println("extractTo");
        ExtractCode instance = new ExtractCode();
        instance.extractTo();
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

}
