package com.andresdlrg;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * @author andres
 * @version 1.0
 */
public class EscPosPrinterTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of cut method, of class EscPosPrinter.
     */
    @Test
    public void testCut() {
        System.out.println("cut");
        byte[] expResult = {29, 86, 0};
        byte[] result = EscPosPrinter.cut();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of lineFeed method, of class EscPosPrinter.
     */
    @Test
    public void testLineFeed() {
        System.out.println("lineFeed");
        byte[] expResult = {10};
        byte[] result = EscPosPrinter.lineFeed();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of lineSpacing method, of class EscPosPrinter.
     */
    @Test
    public void testLineSpacing() {
        System.out.println("lineSpacing");
        Integer n = 1;
        byte[] expResult = {27, 51, 1};
        byte[] result = EscPosPrinter.lineSpacing(n);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of lineSpacing method, of class EscPosPrinter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLineSpacing2() {
        System.out.println("lineSpacing");
        Integer n = 256;
        EscPosPrinter.lineSpacing(n);
    }

    /**
     * Test of printDiagnostic method, of class EscPosPrinter.
     */
    @Test
    public void testPrintDiagnostic() {
        System.out.println("printDiagnostic");
        Integer m = 1;
        byte[] expResult = {29, 40, 65, 2, 0, 0, 1};
        byte[] result = EscPosPrinter.printDiagnostic(m);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of printDiagnostic method, of class EscPosPrinter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPrintDiagnostic2() {
        System.out.println("printDiagnostic");
        Integer m = 4;
        EscPosPrinter.printDiagnostic(m);
    }

    /**
     * Test of printBarCode method, of class EscPosPrinter.
     */
    @Test
    public void testPrintBarCode() {
        System.out.println("printBarCode");
        Long code = 987L;
        byte[] expResult = {29, 107, 0, 48, 48, 48, 48, 48, 48, 48, 48, 48, 57, 56, 55, 0};
        byte[] result = EscPosPrinter.printBarCode(code);
        assertArrayEquals(expResult, result);
    }
    
    /**
     * Test of printBarCode method, of class EscPosPrinter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPrintBarCode2() {
        System.out.println("printBarCode");
        Long code = -3L;
        EscPosPrinter.printBarCode(code);
    }

    /**
     * Test of setBarCodeHeight method, of class EscPosPrinter.
     */
    @Test
    public void testSetBarCodeHeight() {
        System.out.println("setBarCodeHeight");
        Integer n = 2;
        byte[] expResult = {29, 104, 2};
        byte[] result = EscPosPrinter.setBarCodeHeight(n);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of setBarCodeHeight method, of class EscPosPrinter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetBarCodeHeight2() {
        System.out.println("setBarCodeHeight");
        Integer n = -4;
        EscPosPrinter.setBarCodeHeight(n);
    }

    /**
     * Test of setBarCodeWidth method, of class EscPosPrinter.
     */
    @Test
    public void testSetBarCodeWidth() {
        System.out.println("setBarCodeWidth");
        Integer n = 1;
        byte[] expResult = {29, 119, 1};
        byte[] result = EscPosPrinter.setBarCodeWidth(n);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of setBarCodeWidth method, of class EscPosPrinter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetBarCodeWidth2() {
        System.out.println("setBarCodeWidth");
        Integer n = 256;
        EscPosPrinter.setBarCodeWidth(n);
    }

    /**
     * Test of setHriPrintPosition method, of class EscPosPrinter.
     */
    @Test
    public void testSetHriPrintPosition() {
        System.out.println("setHriPrintPosition");
        Integer m = 1;
        byte[] expResult = {29, 72, 1};
        byte[] result = EscPosPrinter.setHriPrintPosition(m);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of setHriPrintPosition method, of class EscPosPrinter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetHriPrintPosition2() {
        System.out.println("setHriPrintPosition");
        Integer m = 5;
        EscPosPrinter.setHriPrintPosition(m);
    }

    /**
     * Test of setHriFont method, of class EscPosPrinter.
     */
    @Test
    public void testSetHriFont() {
        System.out.println("setHriFont");
        Integer m = 1;
        byte[] expResult = {29, 102, 1};
        byte[] result = EscPosPrinter.setHriFont(m);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of setHriFont method, of class EscPosPrinter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetHriFont2() {
        System.out.println("setHriFont");
        Integer m = 7;
        EscPosPrinter.setHriFont(m);
    }

    /**
     * Test of setFontSize method, of class EscPosPrinter.
     */
    @Test
    public void testSetFontSize() {
        System.out.println("setFontSize");
        Integer widthMagnification = 5;
        Integer heightMagnification = 5;
        byte[] expResult = {29, 33, 85};
        byte[] result = EscPosPrinter.setFontSize(widthMagnification, heightMagnification);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of setFontSize method, of class EscPosPrinter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetFontSize2() {
        System.out.println("setFontSize");
        Integer widthMagnification = 8;
        Integer heightMagnification = 5;
        EscPosPrinter.setFontSize(widthMagnification, heightMagnification);
    }

    /**
     * Test of initializePrinter method, of class EscPosPrinter.
     */
    @Test
    public void testInitializePrinter() {
        System.out.println("initializePrinter");
        byte[] expResult = {27, 64};
        byte[] result = EscPosPrinter.initializePrinter();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of setTextPosition method, of class EscPosPrinter.
     */
    @Test
    public void testSetTextPosition() {
        System.out.println("setTextPosition");
        Integer m = 2;
        byte[] expResult = {27, 97, 2};
        byte[] result = EscPosPrinter.setTextPosition(m);
        assertArrayEquals(expResult, result);
    }
    
    /**
     * Test of setTextPosition method, of class EscPosPrinter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetTextPosition2() {
        System.out.println("setTextPosition");
        Integer m = 4;
        EscPosPrinter.setTextPosition(m);
    }

    /**
     * Test of printText method, of class EscPosPrinter.
     */
    @Test
    public void testPrintText() {
        System.out.println("printText");
        String text = "texto";
        byte[] expResult = text.getBytes();
        byte[] result = EscPosPrinter.printText(text);
        assertArrayEquals(expResult, result);
    }

}
