/**
 *
 * @author Oscar
 * Last Edit Date: Sept. 17, 2018
 *
 */

import javax.swing.JTextField;
import java.awt.Font;

public class CalculatorTextField extends JTextField{

    private static final Font textFieldFont = new Font(Font.DIALOG_INPUT, Font.PLAIN, 50);

    /**
     *
     * setEditable(false), prevents unwanted key-input without being updated by private methods.
     *
     */
    public CalculatorTextField() {
        setText("");
        setFont(textFieldFont);
        setEditable(false);
    }

}
