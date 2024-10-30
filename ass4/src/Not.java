// 329233472 yasmin haddad

import java.util.Map;

/**
 * represents a not expression.
 */
public class Not extends UnaryExpression {

    private Expression expression1 = getExpression1();
    /**
     * constructor.
     * @param expression the expression
     */
    public Not(Expression expression) {
        super(expression, "~");
    }



    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return !this.expression1.evaluate(assignment);
    }

    @Override
    public Boolean evaluate() throws Exception {
        return !this.expression1.evaluate();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression newEx = this.expression1.assign(var, expression);
        return new Not(newEx);
    }

    @Override
    public Expression nandify() {
        return new Nand(this.expression1.nandify(), this.expression1.nandify());
    }

    @Override
    public Expression norify() {
        return new Nor(this.expression1.norify(), this.expression1.norify());
    }

    @Override
    public Expression simplify() {
        try {
            return new Val(this.evaluate());
        } catch (Exception e) {
            Expression ex = this.expression1.simplify();
            if (ex.toString().equals("T")) {
                return new Var("F");
            }
            if (ex.toString().equals("F")) {
                return new Var("T");
            }
            return new Not(ex);
        }
    }

}
