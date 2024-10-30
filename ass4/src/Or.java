// 329233472 yasmin haddad

import java.util.Map;

/**
 * represents the Or expression.
 */
public class Or extends BinaryExpression {
    private Expression expression1 = getExpression1();
    private Expression expression2 = getExpression2();

    /**
     * constructor.
     * @param ex1 the first expression
     * @param ex2 the second expression
     */
    public Or(Expression ex1, Expression ex2) {
        super(ex1, ex2, "|");
    }


    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return this.expression1.evaluate(assignment) || this.expression2.evaluate(assignment);
    }

    @Override
    public Boolean evaluate() throws Exception {
        return this.expression1.evaluate() || this.expression2.evaluate();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression newEx1 = this.expression1.assign(var, expression);
        Expression newEx2 = this.expression2.assign(var, expression);
        return new Or(newEx1, newEx2);
    }

    @Override
    public Expression nandify() {
        Expression ex1 = this.expression1.nandify();
        Expression ex2 = this.expression2.nandify();
        return new Nand(new Nand(ex1, ex1), new Nand(ex2, ex2));
    }

    @Override
    public Expression norify() {
        Expression ex1 = this.expression1.norify();
        Expression ex2 = this.expression2.norify();
        return new Nor(new Nor(ex1, ex2), new Nor(ex1, ex2));
    }

    @Override
    public Expression simplify() {
        try {
            return new Val(this.evaluate());
        } catch (Exception e) {
            Expression ex1 = this.expression1.simplify();
            Expression ex2 = this.expression2.simplify();
            if (ex1.toString().equals("T") || ex2.toString().equals("T")) {
                return new Var("T");
            }
            if (ex1.toString().equals("F")) {
                return ex2;
            }
            if (ex2.toString().equals("F")) {
                return ex1;
            }
            if (ex1.toString().equals(ex2.toString())) {
                return ex1;
            }
            return new Or(ex1, ex2);
        }
    }

}
