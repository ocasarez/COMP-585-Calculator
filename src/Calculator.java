/**
 *
 * @author Oscar
 * Calculator.java
 * Calculator, creates the CalculatorFrame.
 * setLocationRelativeTo(null) places the Frame in the center.
 *
 */
import javax.swing.JFrame;

public class Calculator extends JFrame {
    public static void main(String[] args){
        JFrame frame = new CalculatorFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }
}
