/**
 *
 * @author Oscar
 * Last Edit Date: Sept. 17, 2018
 * JUnit 4.10
 *
 * EquationSolveTest.java
 * Testing the mathematical functionality of returnAnswer(), from EquationSolver.
 *
 *
 */
import org.junit.Test;
import javax.script.ScriptException;
import static org.junit.Assert.assertEquals;

public class EquationSolverTest {

    @Test
    public void returnAnswer() throws ScriptException {
        EquationSolver solver = new EquationSolver();

        solver.setExpression("1+2+3");
        assertEquals("6", solver.returnAnswer());

        solver.setExpression("1+2-3");
        assertEquals("0", solver.returnAnswer());

        solver.setExpression("1+2*10");
        assertEquals("21", solver.returnAnswer());

        solver.setExpression("100/5");
        assertEquals("20", solver.returnAnswer());

        solver.setExpression("-3+0");
        assertEquals("-3", solver.returnAnswer());

        solver.setExpression("(10*10) - 10 * 9");
        assertEquals("10", solver.returnAnswer());

        solver.setExpression("1/0");
        assertEquals("1/0",solver.returnAnswer());

        solver.setExpression("1+2*3/4%*2*3");
        assertEquals("901",solver.returnAnswer());

        solver.setExpression("(1+3)+(1+2)%*2");
        assertEquals("4.06", solver.returnAnswer());

        solver.setExpression("10%*10");
        assertEquals("1", solver.returnAnswer());

        solver.setExpression("10MOD10");
        assertEquals("0", solver.returnAnswer());
    }

}