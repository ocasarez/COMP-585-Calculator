/**
 *
 * @author Oscar
 * Last Edit Date: Sept. 17, 2018
 * CalaculatorMenu.java
 *
 * CalculatorMenu, has one menu and one menu item. The menu (APP) has an menuItem (Exit).
 * (Exit) will close the program, if clicked.
 *
 */
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CalculatorMenu extends JMenuBar {

    private static final Font jMenuFont = new Font(Font.MONOSPACED, Font.BOLD, 25);

    private JMenu menu;
    private JMenuItem exit;

    public CalculatorMenu(){
        setPreferredSize(new Dimension(40,40));
        createMenuItem();
        createMenu();
        createMenuBar();
        addMenuListeners();
    }

    private void createMenuBar(){
        this.add(menu);
    }

    private void createMenuItem(){
        exit = new JMenuItem("Exit");
        exit.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 15));

    }

    private void createMenu(){
        menu = new JMenu("App");
        menu.setFont(jMenuFont);
        menu.add(exit);
    }

    // Menu Listener for Exit Menu Item
    private void addMenuListeners(){
        exit.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){exitActionPerformed();}
        });
    }

    private void exitActionPerformed(){
        System.exit(0);
    }
}
