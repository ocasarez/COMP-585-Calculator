/**
 *
 * @author Oscar
 * Last Edit Date: Sept. 17, 2018
 * CalculatorFrame.java
 * Creates Calculator Frame, using CalculatorPanel and CalculatorMenu.
 *
 */
import javax.swing.JFrame;

public class CalculatorFrame extends JFrame {

    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 800;

    private CalculatorPanel main_panel;
    private CalculatorMenu menu;


    public CalculatorFrame (){
        setTitle("Calculator");
        createCalculatorMenu();
        createPanel();
        setResizable(false);
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
    }

    private void createCalculatorMenu(){
        menu = new CalculatorMenu();
        this.setJMenuBar(menu);
    }

    private void createPanel(){
        main_panel = new CalculatorPanel();
        add(main_panel);
    }
}
