/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupseven.pdfproject;

import java.util.EmptyStackException;
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
public class MoveStackTest {

    public MoveStackTest() {
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
     * Test of isEmpty method, of class MoveStack.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        MoveStack instance = new MoveStack();
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSize method, of class MoveStack.
     */
    @Test
    public void testGetSize() {
        System.out.println("getSize");
        MoveStack instance = new MoveStack();
        int expResult = 0;
        int result = instance.getSize();
        assertEquals(expResult, result);
    }

    /**
     * Test of push method by checking isEmpty, of class MoveStack.
     */
    @Test
    public void testPushIsEmpty() {
        System.out.println("push and isempty");
        Object val = null;
        MoveStack instance = new MoveStack();
        instance.push(val);
        boolean expResult = false;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
    }

    /**
     * Test of push method by checking getSize, of class MoveStack.
     */
    @Test
    public void testPushSize() {
        System.out.println("push and size");
        Object val = null;
        MoveStack instance = new MoveStack();
        instance.push(val);
        int expResult = 1;
        int result = instance.getSize();
        assertEquals(expResult, result);
    }

    /**
     * Test of pop method by checking isEmpty, of class MoveStack.
     */
    @Test
    public void testPopIsEmpty() {
        System.out.println("pop and isempty");
        Object val = null;
        MoveStack instance = new MoveStack();
        instance.push(val);
        Object popResult = instance.pop();
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
    }

    /**
     * Test of pop method by checking getSize, of class MoveStack.
     */
    @Test
    public void testPopSize() {
        System.out.println("pop and size");
        Object val = null;
        MoveStack instance = new MoveStack();
        instance.push(val);
        instance.push(val);
        instance.push(val);
        Object popResult = instance.pop();
        int expResult = 2;
        int result = instance.getSize();
        assertEquals(expResult, result);
    }

    /**
     * Test of peek method, of class MoveStack.
     */
    @Test
    public void testPeek() {
        System.out.println("peek");
        MoveStack instance = new MoveStack();
        instance.push(1);
        instance.push(2);
        instance.push(3);
        Object expResult = 3;
        Object result = instance.peek();
        assertEquals(expResult, result);
    }

    /**
     * Test of all methods, of class MoveStack.
     */
    @Test(expected = EmptyStackException.class)
    public void testAll() {
        System.out.println("all");
        MoveStack instance = new MoveStack();
        instance.push(1);
        instance.push(2);
        instance.push(3);
        assertEquals(3, instance.peek());
        assertEquals(3, instance.getSize());
        assertEquals(false, instance.isEmpty());

        instance.pop();
        assertEquals(2, instance.peek());
        assertEquals(2, instance.getSize());
        assertEquals(false, instance.isEmpty());

        instance.pop();
        assertEquals(1, instance.peek());
        assertEquals(1, instance.getSize());
        assertEquals(false, instance.isEmpty());

        instance.pop();
        // the line below is expected to throw an EmptyStackException
        assertEquals(null, instance.peek());
        assertEquals(0, instance.getSize());
        assertEquals(true, instance.isEmpty());
    }

}
