import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

public class CalculatorButton extends JButton {

    private static final Font buttonFont = new Font(Font.MONOSPACED, Font.BOLD, 40);

    public CalculatorButton(String s){
        this.setText(s);
        this.setFont(buttonFont);
        this.setForeground(Color.WHITE);
        this.setBackground(Color.darkGray);
    }

}
