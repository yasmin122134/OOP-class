// 329233472 yasmin haddad

import java.util.Map;

/**
 * represents a logical And operation.
 */
public class And extends BinaryExpression {
    private Expression expression1 = getExpression1();
    private Expression expression2 = getExpression2();

    /**
     * constructor.
     * @param ex1 the first expression
     * @param ex2 the second expression
     */
    public And(Expression ex1, Expression ex2) {
        super(ex1, ex2, "&");
    }


    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return this.expression1.evaluate(assignment) && this.expression2.evaluate(assignment);
    }

    @Override
    public Boolean evaluate() throws Exception {
        return this.expression1.evaluate() && this.expression2.evaluate();
    }



    @Override
    public Expression assign(String var, Expression expression) {
        Expression newEx1 = expression1.assign(var, expression);
        Expression newEx2 = expression2.assign(var, expression);
        return new And(newEx1, newEx2);
    }


    @Override
    public Expression nandify() {
        Expression nand = new Nand(expression1.nandify(), expression2.nandify());
        return new Nand(nand, nand);
    }


    @Override
    public Expression norify() {
        Expression nordy1 = expression1.norify();
        Expression nordy2 = expression2.norify();
        return new Nor(new Nor(nordy1, nordy1), new Nor(nordy2, nordy2));
    }


    @Override
    public Expression simplify() {
        try {
            return new Val(this.evaluate());
        } catch (Exception e) {
            Expression ex1 = expression1.simplify();
            Expression ex2 = expression2.simplify();
            if (ex1.toString().equals("F") || ex2.toString().equals("F")) {
                return new Var("F");
            }
            if (ex1.toString().equals("T")) {
                return ex2;
            }
            if (ex2.toString().equals("T")) {
                return ex1;
            }
            if (ex1.toString().equals(ex2.toString())) {
                return ex1;
            }
            return new And(ex1, ex2);
        }
    }


}
