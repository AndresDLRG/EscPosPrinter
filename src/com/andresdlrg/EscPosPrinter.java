package com.andresdlrg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <h1>Class to control an Epson printer</h1>
 * @author Andres De La Rosa
 * @version 1.0
 */
public class EscPosPrinter {

    private static final byte[] CUT = {29, 86, 0};
    private static final byte[] LINE_FEED = {10};
    private static final byte[] LINE_SPACING = {27, 51};
    private static final byte[] DIAGNOSTIC = {29, 40, 65, 2, 0, 0};
    private static final byte[] BAR_CODE = {29, 107, 0};
    private static final byte[] INITIALIZE_PRINTER = {27, 64};
    private static final byte[] SET_BAR_CODE_HEIGHT = {29, 104};
    private static final byte[] SET_BAR_CODE_WIDTH = {29, 119};
    private static final byte[] SET_HRI_PRINT_POSITION = {29, 72};
    private static final byte[] SET_HRI_FONT = {29, 102};
    private static final byte[] SET_FONT_SIZE = {29, 33};
    private static final byte[] TEXT_POSITION = {27, 97};

    /**
     * @return byte[] to cut paper.
     */
    public static byte[] cut() {
        return CUT;
    }

    /**
     * @return byte[] for a line break.
     */
    public static byte[] lineFeed() {
        return LINE_FEED;
    }

    /**
     * @param n The space between.
     * @return byte[] to set the space between lines.
     * @throws IllegalArgumentException when n &lt; 1 or &gt; 255.
     */
    public static byte[] lineSpacing(Integer n) throws IllegalArgumentException{
        if (n < 1 || n > 255) {
            throw new IllegalArgumentException("The acceptable values for \"n\" are from 1 to 255");
        }
        n = n & 0xFF;
        
        byte[] lineSpacer = new byte[LINE_SPACING.length + 1];
        System.arraycopy(LINE_SPACING, 0, lineSpacer, 0, LINE_SPACING.length);
        byte[] aux = new byte[]{ n.byteValue() };
        System.arraycopy(aux, 0, lineSpacer, LINE_SPACING.length, 1);
        
        return lineSpacer;
    }

    /**
     * @param m The type of diagnostic.
     * @return byte[] to print the diagnostic.
     * @throws IllegalArgumentException when m &lt; 1 || m &gt; 3.
     */
    public static byte[] printDiagnostic(Integer m) throws IllegalArgumentException{
        if (m < 1 || m > 3) {
            throw new IllegalArgumentException("The acceptable values for \"m\" "
                    + " are from 1 to 3");
        }

        byte[] diagnostic = new byte[DIAGNOSTIC.length + 1];
        System.arraycopy(DIAGNOSTIC, 0, diagnostic, 0, DIAGNOSTIC.length);
        byte[] aux = new byte[]{m.byteValue()};
        System.arraycopy(aux, 0, diagnostic, DIAGNOSTIC.length, 1);

        return diagnostic;
    }
    
    /**
     * @param code A number between 0  and 999999999999.
     * @return byte[] to print the bar code.
     * @throws IllegalArgumentException when code does not complete the 
     * requirements for a bar code.
     */
    public static byte[] printBarCode(Long code) throws IllegalArgumentException{
        if (code < 0 || code > 999_999_999_999L) {
            throw new IllegalArgumentException("Acceptable values are from 0 to 999999999999");
        }
        String sCode = String.valueOf(code);
        if (sCode.length() < 12) {
            String extra = "";
            for (int i = 0; i < 12 - sCode.length(); i++) {
                extra += "0";
            }
            sCode = extra + sCode;
        }
        
        String regex = "\\d{12}";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(sCode);
        
        if (! m.matches()) {
            throw new IllegalArgumentException("The code must be have only digits");
        }
        
        byte[] barCode = new byte[BAR_CODE.length + 13];
        System.arraycopy(BAR_CODE, 0, barCode, 0, BAR_CODE.length);
        System.arraycopy(sCode.getBytes(), 0, barCode, BAR_CODE.length, 12);
        barCode[barCode.length - 1] = 0;
        
        return barCode;
    }
    
    /**
     * @param n The height in points of the bar codes.
     * @return byte[} to set the height of bar codes.
     * @throws IllegalArgumentException when n &lt; 1 or n &gt; 255.
     */
    public static byte[] setBarCodeHeight(Integer n) throws IllegalArgumentException{
        if (n < 1 || n > 255) {
            throw new IllegalArgumentException("The acceptable values for \"n\" are from 1 to 255");
        }
        n = n & 0xFF;
        
        byte[] setHeight = new byte[SET_BAR_CODE_HEIGHT.length + 1];
        System.arraycopy(SET_BAR_CODE_HEIGHT, 0, setHeight, 0, 
                SET_BAR_CODE_HEIGHT.length);
        byte[] aux = new byte[]{ n.byteValue() };
        System.arraycopy(aux, 0, setHeight, SET_BAR_CODE_HEIGHT.length, 1);
        
        return setHeight;
    }
    
    /**
     * @param n The width of the bar codes.
     * @return byte[} to set the width of bar codes.
     * @throws IllegalArgumentException when n &lt; 1 or n &gt; 5.
     */
    public static byte[] setBarCodeWidth(Integer n) throws IllegalArgumentException{
        if (n < 1 || n > 5) {
            throw new IllegalArgumentException("The acceptable values for \"n\" are from 1 to 5");
        }
        
        byte[] setWidth = new byte[SET_BAR_CODE_WIDTH.length + 1];
        System.arraycopy(SET_BAR_CODE_WIDTH, 0, setWidth, 0, 
                SET_BAR_CODE_WIDTH.length);
        byte[] aux = new byte[]{ n.byteValue() };
        System.arraycopy(aux, 0, setWidth, SET_BAR_CODE_WIDTH.length, 1);
        
        return setWidth;
    }
    
    /**
     * Posibles values of  m: <br>
     * <ul>
     * <li><strong>0,48</strong> No print</li>
     * <li><strong>1,49</strong> Above bar code</li>
     * <li><strong>2,50</strong> Below bar code</li>
     * <li><strong>3,51</strong> Above and below bar code</li>
     * </ul>
     * @param m The position.
     * @return byte[] to set HRI print position.
     * @throws IllegalArgumentException when m &lt; 0 || m &gt; 3.
     */
    public static byte[] setHriPrintPosition(Integer m) throws IllegalArgumentException{
        if ((m < 0 || m > 3) && (m < 48 || m > 51)) {
            throw new IllegalArgumentException("The acceptable values for \"m\" "
                    + " are from 0 to 3");
        }

        byte[] setPosition = new byte[SET_HRI_PRINT_POSITION.length + 1];
        System.arraycopy(SET_HRI_PRINT_POSITION, 0, setPosition, 0, SET_HRI_PRINT_POSITION.length);
        byte[] aux = new byte[]{m.byteValue()};
        System.arraycopy(aux, 0, setPosition, SET_HRI_PRINT_POSITION.length, 1);

        return setPosition;
    }
    
    /**
     * Posibles values of  m: <br>
     * <ul>
     * <li><strong>0,48</strong> Font 12 x 24</li>
     * <li><strong>1,49</strong> Font 9 x17</li>
     * </ul>
     * @param m The font.
     * @return byte[] to set HRI font.
     * @throws IllegalArgumentException when m &lt; 0 || m &gt; 1.
     */
    public static byte[] setHriFont(Integer m) throws IllegalArgumentException{
        if (m < 0 || m > 1) {
            throw new IllegalArgumentException("The acceptable values for \"m\" "
                    + " are from 0 to 1");
        }

        byte[] setFont = new byte[SET_HRI_FONT.length + 1];
        System.arraycopy(SET_HRI_FONT, 0, setFont, 0, SET_HRI_FONT.length);
        byte[] aux = new byte[]{m.byteValue()};
        System.arraycopy(aux, 0, setFont, SET_HRI_FONT.length, 1);

        return setFont;
    }
    
    /**
     * Changes the font zise of the normal text
     * @param widthMagnification from 0 to 7
     * @param heightMagnification from 0 to 7
     * @return byte[] to set the font size.
     * @throws IllegalArgumentException when 
     * (widthMagnification &lt; 0 || widthMagnification &gt; 7) 
     * || (heightMagnification &lt; 0 || heightMagnification &gt; 7))
     */
    public static byte[] setFontSize(Integer widthMagnification,
            Integer heightMagnification) throws IllegalArgumentException{
        if ((widthMagnification < 0 || widthMagnification > 7) 
                || (heightMagnification < 0 || heightMagnification > 7)) {
            throw new IllegalArgumentException("The acceptable values for "
                    + "widthMagnification and heightMagnification are from 0 to 7");
        }
        Integer m = 16 * widthMagnification + heightMagnification;
        
        byte[] setFontSize = new byte[SET_FONT_SIZE.length + 1];
        System.arraycopy(SET_FONT_SIZE, 0, setFontSize, 0, SET_FONT_SIZE.length);
        byte[] aux = new byte[]{m.byteValue()};
        System.arraycopy(aux, 0, setFontSize, SET_FONT_SIZE.length, 1);

        return setFontSize;
    }
    
    /**
     * Set the printer to it default configuration
     * @return byte[] to initialize printer
     */
    public static byte[] initializePrinter() {
        return INITIALIZE_PRINTER;
    }
    
    /**
     * Posibles values of  m: <br>
     * <ul>
     * <li><strong>0</strong> Left</li>
     * <li><strong>1</strong> Center</li>
     * <li><strong>2</strong> Right</li>
     * </ul>
     * @param m The position.
     * @return byte[] to set the text position.
     * @throws IllegalArgumentException when m &lt; 0 || m &gt; 2.
     */
    public static byte[] setTextPosition(Integer m) {
        if (m < 0 || m > 2) {
            throw new IllegalArgumentException("The acceptable values for \"m\" "
                    + " are from 0 to 2 ");
        }

        byte[] textPosition = new byte[TEXT_POSITION.length + 1];
        System.arraycopy(TEXT_POSITION, 0, textPosition, 0, TEXT_POSITION.length);
        byte[] aux = new byte[]{m.byteValue()};
        System.arraycopy(aux, 0, textPosition, TEXT_POSITION.length, 1);

        return textPosition;
    }
    
    /**
     *
     * @param text text to be printed
     * @return byte[] of the text
     */
    public static byte[] printText(String text) {
        return text.getBytes();
    }
}
