/**
 *
 * @author Oscar
 * Last Edit Date: Sept. 17, 2018
 *
 * EquationSolver uses the ScriptEngineManger to use JavaScript to solve
 * mathematical expressions expressed as a String.
 *
 * Supported Operations
 *  Addition
 *  Subtraction
 *  Division
 *  Multiplication
 *  Percent
 *  Modulo
 *
 */
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.text.DecimalFormat;

public class EquationSolver {

    private DecimalFormat df;
    private ScriptEngineManager manager;
    private ScriptEngine engine;

    private String expression;


    public EquationSolver(){
        df = new DecimalFormat("#.####");
        manager = new ScriptEngineManager();
        engine = manager.getEngineByName("JavaScript");
    }

    /**
     *
     * @return String   solved Math Expression
     * @throws ScriptException
     */
    public String returnAnswer() throws ScriptException{
        String result;
        // Applying Percentage operation prior to calling engine.eval(expression)
        if(expression.contains("%")){
            if(expression.contains(")%")){
                expression = expression.replaceAll("%","/100");

            }else{
                String[] split = expression.split("[-+*/]");
                for(String exp: split){
                    if(exp.contains("%")){
                        Double number = Double.parseDouble(exp.replaceAll("[%]", "")) / 100;
                        expression = expression.replace(exp, Double.toString(number));
                    }else if(exp.contains(")%")){

                    }
                }
            }
        } if(expression.contains("MOD")){
            // Replace MOD with %, for engine.eval(expression) to accept input
            expression = expression.replace("MOD", "%");

        }
        // If expression returns an undefined answer return the expression.
        if (engine.eval(expression).toString().equals("∞") || engine.eval(expression).toString().equals("Infinity")
            || engine.eval(expression).toString().equals("NaN")) {
            return expression;
        }

        result = df.format(engine.eval(expression));
        return result;
    }

    // Setters
    public void setExpression(String s){
        expression = s;
    }

}
