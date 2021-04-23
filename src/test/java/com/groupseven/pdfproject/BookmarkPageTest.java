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
public class BookmarkPageTest {

    public BookmarkPageTest() {
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
     * Test of jumpTo method, of class BookmarkPage.
     */
    @Test
    public void testJumpTo() {
        System.out.println("jumpTo");
        BookmarkPage instance = new BookmarkPage();
        instance.jumpTo();
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of bookmarkIt method, of class BookmarkPage.
     */
    @Test
    public void testBookmarkIt() {
        System.out.println("bookmarkIt");
        BookmarkPage instance = new BookmarkPage();
        instance.bookmarkIt();
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

}
