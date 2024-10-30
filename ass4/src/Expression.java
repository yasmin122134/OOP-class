// 329233472 yasmin haddad

import java.util.List;
import java.util.Map;

/**
 * represents an expression.
 */
public interface Expression {
    /**
     * Evaluate the expression using the variable values provided.
     * @param assignment the map of the variables and their values
     * @return the result of the evaluation
     * @throws Exception if the expression contains a variable that is not in the assignment.
     */
    Boolean evaluate(Map<String, Boolean> assignment) throws Exception;

    /**
     * A convenience method. Like the `evaluate(assignment)` method above,
     * @return the result of the evaluation
     * @throws Exception if the expression contains a variable.
     */
    Boolean evaluate() throws Exception;


    /**
     * Returns a list of the variables in the expression.
     * @return the list of the variables
     */
    List<String> getVariables();


    /**
     * Returns a nice string representation of the expression.
     * @return the string representation
     */
    String toString();

    /**
     * Returns a new expression in which all occurrences of the variable.
     * @param var the variable to replace
     * @param expression the expression to replace with
     * @return the new expression
     */
    Expression assign(String var, Expression expression);


    /**
     * calculates an equivalent expression to the current expression using only Nand operations.
     * @return the new expression
     */
    Expression nandify();

    /**
     * calculates an equivalent expression to the current expression using only Nor operations.
     * @return the new expression
     */
    Expression norify();


    /**
     * Returns a simplified version of the current expression.
     * @return the new expression
     */
    Expression simplify();
}
