/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validate;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

/**
 * This class is used to accept only number. Source:
 * https://stackoverflow.com/questions/11093326/restricting-jtextfield-input-to-integers
 *
 * @author Hovercraft Full Of Eels
 */
public class OnlyNum extends DocumentFilter {

    /**
     * Inserts some content into the document. Invoked prior to insertion of
     * text into the specified Document. Subclasses that want to conditionally
     * allow insertion should override this and only call supers implementation
     * as necessary, or call directly into the FilterBypass.
     *
     * @param fb FilterBypass that can be used to mutate Document
     * @param offset the starting offset &gt;= 0
     * @param string the string to insert; does nothing with null/empty strings
     * @param attr the attributes for the inserted content
     * @throws BadLocationException the given insert position is not a valid
     * position within the document
     */
    @Override
    public void insertString(FilterBypass fb, int offset, String string,
            AttributeSet attr) throws BadLocationException {

        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        sb.insert(offset, string);

        if (test(sb.toString())) {
            super.insertString(fb, offset, string, attr);
        } else {
            // warn the user and don't allow the insert
        }
    }

    /**
     * Check string with some rule
     *
     * @param text text want to check
     * @return result of check
     */
    private boolean test(String text) {
        try {
            for (int i = 0; i < text.length(); i++) {
                Integer.parseInt(text.substring(i, i + 1));
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Invoked prior to replacing a region of text in the specified Document.
     * Subclasses that want to conditionally allow replace should override this
     * and only call supers implementation as necessary, or call directly into
     * the FilterBypass.
     *
     * @param fb FilterBypass that can be used to mutate Document
     * @param offset Location in Document
     * @param length Length of text to delete
     * @param text Text to insert, null indicates no text to insert
     * @param attrs AttributeSet indicating attributes of inserted text, null is
     * legal.
     * @exception BadLocationException the given insert position is not a valid
     * position within the document
     */
    @Override
    public void replace(FilterBypass fb, int offset, int length, String text,
            AttributeSet attrs) throws BadLocationException {

        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        sb.replace(offset, offset + length, text);

        if (test(sb.toString())) {
            super.replace(fb, offset, length, text, attrs);
        } else {
            // warn the user and don't allow the insert
        }

    }

    /**
     * Invoked prior to removal of the specified region in the specified
     * Document. Subclasses that want to conditionally allow removal should
     * override this and only call supers implementation as necessary, or call
     * directly into the <code>FilterBypass</code> as necessary.
     *
     * @param fb FilterBypass that can be used to mutate Document
     * @param offset the offset from the beginning &gt;= 0
     * @param length the number of characters to remove &gt;= 0
     * @exception BadLocationException some portion of the removal range was not
     * a valid part of the document. The location in the exception is the first
     * bad position encountered.
     */
    @Override
    public void remove(FilterBypass fb, int offset, int length)
            throws BadLocationException {
        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        sb.delete(offset, offset + length);

        if (test(sb.toString())) {
            super.remove(fb, offset, length);
        } else {
            // warn the user and don't allow the insert
        }
    }
}
