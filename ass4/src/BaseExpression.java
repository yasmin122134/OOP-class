// 329233472 yasmin haddad

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * represents a base expression.
 */
public abstract class BaseExpression implements Expression {
    private Expression expression1;
    private Expression expression2;

    /**
     * getter for the first expression.
     * @return the first expression
     */
    public Expression getExpression1() {
        return expression1;
    }

    /**
     * getter for the second expression.
     * @return the second expression
     */
    public Expression getExpression2() {
        return expression2;
    }

    /**
     * constructor.
     * @param expression1 the first expression
     * @param expression2 the second expression
     */
    public BaseExpression(Expression expression1, Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }


    @Override
    public abstract Boolean evaluate(Map<String, Boolean> assignment) throws Exception;


    @Override
    public abstract Boolean evaluate() throws Exception;


    @Override
    public List<String> getVariables() {
        List<String> l1 = new ArrayList<>(), l2 = new ArrayList<>();
        l1 = this.expression1.getVariables();
        if (this.expression2 == null || l1 == null) {
            return l1;
        }
        l2 = this.expression2.getVariables();

        Set<String> set = new HashSet<String>();
        if (l1 != null) {
            set.addAll(l1);
        }

        if (!l2.isEmpty()) {
            for (String s : l2) {
                if (!set.contains(s)) {
                    set.add(s);
                     l1.add(s);
                }
            }
        }
        return new ArrayList<>(l1);
    }

    /**
     * abstract method to convert the expression to a string.
     * @return the string representation of the expression
     */
    @Override
    public abstract String toString();


    @Override
    public abstract Expression assign(String var, Expression expression);



    @Override
    public abstract Expression nandify();

    @Override
    public abstract Expression norify();



    @Override
    public abstract Expression simplify();
}
