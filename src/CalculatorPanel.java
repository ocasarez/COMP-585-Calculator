/**
 *
 * @author Oscar
 * CalculatorPanel.java
 * Last Edit Date: Sept. 17, 2018
 *
 * @see #updateTextField(Object) Object passed is a button created by pressing a key or CalculatorButton
 * @see #handleKeyPressed(KeyEvent) supports Shift + Key, Number Pad, Individial Keys
 * @see #createButtons()
 * @see #createTextField()
 * @see #createEquationSolver()
 *
 *      * Below are helper functions, for flags, keyPresses, String Checker Functions
 *      * ----> Flags:
 *      * @see #setEqualPressed()
 *      * @see #setNumberPressed()
 *      * @see #setOperatorPressed()
 *      * @see #setDecPressed()
 *      * @see #setLeftParenthesisPressed()
 *      * @see #setRightParenthesisPressed()
 *      * @see #setAllFlagsFalse()
 *      *
 *      * ----> Key Presses
 *      * 'C' - Clear Text
 *      * @see #clearTextField()
 *      *
 *      * BACK_SPACE - Delete last character
 *      * @see #backSpacePressed()
 *      *
 *      * ----> String Checker Functions:
 *      * @see #isAnOperator(String) - Checks paramter if it is an Operator.
 *      * @see #validExpressionChecker() - Splits TextField, by operators with the regex "[-+/%||MOD]"
 *      *      and checks if the length of the String[] > 2. i.e. 2 + 2, [2,2]. Means current expression is valid.
 *
 **/
import javax.script.ScriptException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CalculatorPanel extends JPanel{

    private static final String[] NUM_KEYS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private static final String[] OPERATORS = {"+", "-", "*", "/", "%"};

    private boolean isEqualPressed = false;
    private boolean isOperatorPressed = false;
    private boolean isDecPressed = false;
    private boolean isNumberPressed = false;
    private boolean isLeftParenthesisPressed = false;

    private int numOfLeftP = 0;
    private int numOfRightP = 0;

    private CalculatorTextField field;
    private JPanel standard_buttons;
    private KeyListener keyListener;
    private EquationSolver solver;

    private CalculatorButton num0;
    private CalculatorButton num1;
    private CalculatorButton num2;
    private CalculatorButton num3;
    private CalculatorButton num4;
    private CalculatorButton num5;
    private CalculatorButton num6;
    private CalculatorButton num7;
    private CalculatorButton num8;
    private CalculatorButton num9;
    private CalculatorButton mod;
    private CalculatorButton clear;
    private CalculatorButton add;
    private CalculatorButton sub;
    private CalculatorButton mul;
    private CalculatorButton div;
    private CalculatorButton percent;
    private CalculatorButton dec;
    private CalculatorButton equ;
    private CalculatorButton left_parenthesis;

    public CalculatorPanel(){
        createButtons();
        addListeners();
        createKeyListener();
        createTextField();
        createEquationSolver();
        setLayout(new BorderLayout());
        add(field, BorderLayout.NORTH);
        add(standard_buttons, BorderLayout.CENTER);
    }

    /**
     *      Create Methods for CalculatorButton, CalculatorTextField, EquationSolver
     *      Add Methods for Listeners
     *
     */

    // Create Calculator Buttons
    private void createButtons(){
        num0 = new CalculatorButton(NUM_KEYS[0]);
        num0.setBackground(Color.BLACK);
        num1 = new CalculatorButton(NUM_KEYS[1]);
        num1.setBackground(Color.BLACK);
        num2 = new CalculatorButton(NUM_KEYS[2]);
        num2.setBackground(Color.BLACK);
        num3 = new CalculatorButton(NUM_KEYS[3]);
        num3.setBackground(Color.BLACK);
        num4 = new CalculatorButton(NUM_KEYS[4]);
        num4.setBackground(Color.BLACK);
        num5 = new CalculatorButton(NUM_KEYS[5]);
        num5.setBackground(Color.BLACK);
        num6 = new CalculatorButton(NUM_KEYS[6]);
        num6.setBackground(Color.BLACK);
        num7 = new CalculatorButton(NUM_KEYS[7]);
        num7.setBackground(Color.BLACK);
        num8 = new CalculatorButton(NUM_KEYS[8]);
        num8.setBackground(Color.BLACK);
        num9 = new CalculatorButton(NUM_KEYS[9]);
        num9.setBackground(Color.BLACK);
        mod = new CalculatorButton("MOD");
        clear = new CalculatorButton("C");
        add = new CalculatorButton(OPERATORS[0]);
        sub = new CalculatorButton(OPERATORS[1]);
        mul = new CalculatorButton(OPERATORS[2]);
        div = new CalculatorButton(OPERATORS[3]);
        percent = new CalculatorButton("%");
        dec = new CalculatorButton(".");
        equ = new CalculatorButton("=");
        left_parenthesis = new CalculatorButton("()");

        standard_buttons = new JPanel();
        standard_buttons.setLayout(new GridLayout(5,4));

        // Row 1
        standard_buttons.add(clear); // Col 1
        standard_buttons.add(dec);   // Col 2
        standard_buttons.add(percent);   // Col 3
        standard_buttons.add(div);   // Col 4

        // Row 2
        standard_buttons.add(num7);  // Col 1
        standard_buttons.add(num8);  // Col 2
        standard_buttons.add(num9);  // Col 3
        standard_buttons.add(mul);   // COl 4

        // Row 3
        standard_buttons.add(num4);  // Col 1
        standard_buttons.add(num5);  // Col 2
        standard_buttons.add(num6);  // Col 3
        standard_buttons.add(sub);   // Col 4

        // Row 4
        standard_buttons.add(num1);  // Col 1
        standard_buttons.add(num2);  // Col 2
        standard_buttons.add(num3);  // Col 3
        standard_buttons.add(add);   // Col 4

        //Row 5
        standard_buttons.add(left_parenthesis);   // Col 1
        standard_buttons.add(num0);  // Col 2
        standard_buttons.add(mod);   // Col 3
        standard_buttons.add(equ);   // Col 4
    }

    // Create Calculator Text Field
    private void createTextField(){
        field = new CalculatorTextField();
        field.addKeyListener(keyListener);
    }

    private void createEquationSolver(){
        solver = new EquationSolver();
    }

    // Add Listeners to Buttons
    private void addListeners(){
        ClickListener listener = new ClickListener();
        num0.addActionListener(listener);
        num1.addActionListener(listener);
        num2.addActionListener(listener);
        num3.addActionListener(listener);
        num4.addActionListener(listener);
        num5.addActionListener(listener);
        num6.addActionListener(listener);
        num7.addActionListener(listener);
        num8.addActionListener(listener);
        num9.addActionListener(listener);
        mod.addActionListener(listener);
        left_parenthesis.addActionListener(listener);
        add.addActionListener(listener);
        sub.addActionListener(listener);
        div.addActionListener(listener);
        mul.addActionListener(listener);
        percent.addActionListener(listener);
        dec.addActionListener(listener);
        clear.addActionListener(listener);
        equ.addActionListener(listener);
    }

    /**
     * ClickListener Class for Calculator Buttons && Key Listener Method for Keys
     */

    public class ClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateTextField(e.getSource());
        }
    }
    // Key Listener for Key Presses
    private void createKeyListener(){
        keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
    }

    /**
     *
     * Acceptable Input from Keyboard is handled by handleKeyPressed:
     *
     *      Keys:
     *      1, 2, 3, 4, 5, 6, 7, 8, 9, 0
     *      +, - , /, *, %, ., =
     *      'm' or 'M' - MOD
     *      'c' or 'C' - Clear
     *      BACK_SPACE - Delete last char
     *      ESCAPE - Exit
     *      Enter - Equal
     *      Number Pad Support
     *
     */

    // KeyEvents are Handled by this method
    private void handleKeyPressed(KeyEvent e){
        System.out.println("keyPressed "+ e.getKeyChar() + ": " + e.getKeyCode());
        int keyCode = e.getKeyCode();
        if(e.getKeyChar() == '+'){
            updateTextField(new CalculatorButton("+"));
        }else if(e.getKeyChar() == '*'){
            updateTextField(new CalculatorButton("*"));
        }else if(e.getKeyChar() == '%'){
            updateTextField(new CalculatorButton("%"));
        }else if(e.getKeyChar() == '('){
            updateTextField(new CalculatorButton("()"));
        }else if(e.getKeyChar() == ')'){
            updateTextField(new CalculatorButton("()"));
        }else if(keyCode == KeyEvent.VK_BACK_SPACE){
            backSpacePressed();
            e.consume(); // Prevents Audio Error
        }else if (keyCode >= 45 && keyCode <= 57 || keyCode == 37 || keyCode == 42 || keyCode == 43 || keyCode == 61){
            updateTextField(new CalculatorButton(Character.toString((char) keyCode)));
        }else if (keyCode == KeyEvent.VK_ENTER){
            updateTextField(new CalculatorButton("="));
        }else if(keyCode == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }else if(keyCode == 67){
            updateTextField(new CalculatorButton("C"));
        }else if(e.getKeyCode() >= 96 && e.getKeyCode() <= 111){
            updateTextField(new CalculatorButton(Character.toString(e.getKeyChar())));
        }else if(e.getKeyChar() == 'm' || e.getKeyChar() == 'M'){
            updateTextField(new CalculatorButton("MOD"));
        }
    }

    /**
     *
     * updateTextField()
     * Filters all the input so to EquationSolver accepts the string.
     * Input followers calculator rules and conventions.
     *
     */
    // Update Text Field when a Button is pressed
    private void updateTextField(Object source){
        String buttonText = "";

        if(source instanceof CalculatorButton){
            CalculatorButton button = (CalculatorButton) source;

            switch(button.getText()){
                case "0":
                    if(field.getText().equals("0") && button.getText().equals("0")) {
                        break;
                    }else if(field.getText().length() > 2 && isAnOperator(Character.toString(field.getText().charAt(field.getText().length()-2)))
                            && field.getText().charAt(field.getText().length()-1) == '0' && button.getText().equals("0")){
                        break;
                    }
                    buttonText = button.getText();
                    setNumberPressed();
                    break;
                case "()":
                    if(!isNumberPressed){
                        buttonText = "(";
                        setLeftParenthesisPressed();
                        break;
                    }
                    else if(numOfLeftP == numOfRightP){
                        buttonText = "*" + "(";
                        setOperatorPressed();
                        setLeftParenthesisPressed();
                        break;
                    }else if(isNumberPressed && isOperatorPressed && !validExpressionChecker()){
                        buttonText = "(";
                        setLeftParenthesisPressed();
                        break;
                    }
                    if(isLeftParenthesisPressed && isNumberPressed){
                        if(numOfRightP == numOfLeftP){
                            break;
                        }
                        buttonText = ")";
                        setRightParenthesisPressed();
                    }
                    break;
                case "C":
                    clearTextField();
                    break;
                case ".":

                    if(isDecPressed){
                        break;
                    } else if(!isNumberPressed){
                        buttonText = "0" + button.getText();
                        setNumberPressed();
                        setDecPressed();
                        break;
                    }
                    buttonText = button.getText();
                    setDecPressed();
                    break;
                case "+":
                    if(!isNumberPressed){
                        break;
                    }
                    buttonText = button.getText();
                    setOperatorPressed();
                    break;
                case "-":
                    if(field.getText().equals("-")){
                        break;
                    }
                    buttonText = button.getText();
                    setOperatorPressed();
                    break;
                case "/":
                    if(!isNumberPressed){
                        break;
                    }
                    buttonText = button.getText();
                    setOperatorPressed();
                    break;
                case "*":
                    if(!isNumberPressed){
                        break;
                    }
                    buttonText = button.getText();
                    setOperatorPressed();
                    break;
                case "MOD":
                    if(!isNumberPressed){
                        break;
                    }
                    buttonText = button.getText();
                    setOperatorPressed();
                case "%":
                    if(!isNumberPressed){
                        break;
                    }
                    buttonText = button.getText() + "*";
                    setOperatorPressed();
                    break;
                case "=":
                    if(numOfRightP != numOfLeftP){
                        JOptionPane.showMessageDialog(this, "Invalid Format: Missing Parenthesis","Warning",JOptionPane.WARNING_MESSAGE);
                        break;
                    }
                    String result = "";
                    if(validExpressionChecker()){
                        solver.setExpression(field.getText());
                        try {
                            result = solver.returnAnswer();
                            if(field.getText().equals(result)){
                                JOptionPane.showMessageDialog(this, "Answer Undefined.", "Warning", JOptionPane.WARNING_MESSAGE);
                                break;
                            }
                        }catch (ScriptException e){

                        }
                        field.setText(result);
                        if(result.contains(".")){
                            isNumberPressed = true;
                            isDecPressed = true;
                            isOperatorPressed = false;
                        }
                        else{
                            isNumberPressed = true;
                            isDecPressed = false;
                            isOperatorPressed = false;
                        }
                    }else {
                        JOptionPane.showMessageDialog(this, "Invalid Expression.", "Warning",JOptionPane.WARNING_MESSAGE);
                        break;
                    }
                    setEqualPressed();
                    break;
                default: // Default is Number buttons
                    if(isEqualPressed){
                        clearTextField();
                    }else if((field.getText().length() == 1 && field.getText().equals("0")) ||
                            isOperatorPressed && field.getText().charAt(field.getText().length() - 1) == '0'
                            && isAnOperator(Character.toString(field.getText().charAt(field.getText().length() - 2)))){

                        backSpacePressed();
                    }
                    setNumberPressed();
                    buttonText = button.getText();
                    break;
            }

            field.setText(field.getText() + buttonText);
            System.out.println(field.getText());
        }
    }

    // Flags for if certain buttons where pressed
    private void setEqualPressed(){
        isEqualPressed = true;
    }
    private void setOperatorPressed(){
        isOperatorPressed = true;
        isNumberPressed = false;
        isDecPressed = false;
        isEqualPressed = false;
    }
    private void setDecPressed(){
        isDecPressed = true;
        isOperatorPressed = false;
    }
    private void setNumberPressed(){
        isNumberPressed = true;
        //isOperatorPressed = false;
        isEqualPressed = false;
    }
    private void setLeftParenthesisPressed(){
        isLeftParenthesisPressed = true;
        numOfLeftP++;
    }
    private void setRightParenthesisPressed(){
        numOfRightP++;
        isOperatorPressed = false;
    }
    private void setAllFlagsFalse(){
        isLeftParenthesisPressed = false;
        isNumberPressed = false;
        isOperatorPressed = false;
        isDecPressed = false;
        isEqualPressed = false;
    }

    // Checks if the given string is an operator
    private boolean isAnOperator(String s){
        for(int i = 0; i < OPERATORS.length; i++){
            if(s.equals(OPERATORS[i])){
                return true;
            }
        }
        return false;
    }

    // When 'C' pressed, will clear TextField
    private void clearTextField() {
        field.setText("");
        isNumberPressed = false;
        isOperatorPressed = false;
        isDecPressed = false;
        isLeftParenthesisPressed = false;
        numOfRightP = 0;
        numOfLeftP = 0;
    }

    // Delete one character when back space key is pressed and update flags accordingly.
    private void backSpacePressed(){
        if(field.getText().length() == 0){
            setAllFlagsFalse();
            return;
        }
        int lastIndex = field.getText().length() - 1;
        char lastCharacter = field.getText().charAt(lastIndex);

        if(isAnOperator(Character.toString(lastCharacter))){
            isOperatorPressed = false;
            isNumberPressed = true;
        } else if(lastCharacter == '.'){
            isDecPressed = false;
        } else if(lastCharacter == '('){
            numOfLeftP--;
            if(numOfLeftP == 0){
                isLeftParenthesisPressed = false;
            }
        } else if(lastCharacter == ')'){
            numOfRightP--;
            if(numOfRightP == 0){
            }
        }
        if(field.getText().length() == 1){
            field.setText("");
            return;
        }
        field.setText(field.getText().substring(0,lastIndex));
    }

    // Checks if there are at least 2 inputs and 1 operator
    private boolean validExpressionChecker(){
        boolean validExpression = false;
        if(field.getText().split("[-+*/%]").length > 1 && (field.getText().contains("+") || field.getText().contains("-")
        || field.getText().contains("*") || field.getText().contains("/") || field.getText().contains("%"))){
            validExpression = true;
        }
        if(field.getText().split("MOD").length > 1 && field.getText().contains("MOD")){
            validExpression = true;
        }
        return validExpression;
    }
}