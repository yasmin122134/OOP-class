// 329233472 yasmin haddad

import java.util.Map;

/**
 * represents a nor expression.
 */
public class Nor extends BinaryExpression {
    private Expression delegate;
    private Expression expression1 = getExpression1();
    private Expression expression2 = getExpression2();
    /**
     * constructor.
     * @param ex1 the first expression
     * @param ex2 the second expression
     */
    public Nor(Expression ex1, Expression ex2) {
        super(ex1, ex2, "V");
        this.delegate = new Not(new Or(ex1, ex2));
    }



    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return this.delegate.evaluate(assignment);
//        return !(expression1.evaluate(assignment) || expression2.evaluate(assignment));
    }



    @Override
    public Boolean evaluate() throws Exception {
        return this.delegate.evaluate();
//        return !(expression1.evaluate() || expression2.evaluate());
    }



    @Override
    public Expression assign(String var, Expression expression) {
        Expression newEx1 = this.expression1.assign(var, expression);
        Expression newEx2 = this.expression2.assign(var, expression);
        return new Nor(newEx1, newEx2);
    }

    @Override
    public Expression nandify() {
//        return delegate.nandify();
        Expression ex1 = this.expression1.nandify();
        Expression ex2 = this.expression2.nandify();
        Expression nandy1 = new Nand(ex1, ex1);
        Expression nandy2 = new Nand(ex2, ex2);
        return new Nand(new Nand(nandy1, nandy2), new Nand(nandy1, nandy2));
    }


    @Override
    public Expression norify() {
//        return delegate.norify();
        return new Nor(this.expression1.norify(), this.expression2.norify());
    }


    @Override
    public Expression simplify() {
        try {
            return new Val(this.evaluate());
        } catch (Exception e) {
            Expression ex1 = this.expression1.simplify();
            Expression ex2 = this.expression2.simplify();
            if (ex1.toString().equals("T") || ex2.toString().equals("T")) {
                return new Var("F");
            }
            if (ex1.toString().equals("F")) {
                return new Not(ex2).simplify();
            }
            if (ex2.toString().equals("F")) {
                return new Not(ex1).simplify();
            }
            if (ex1.toString().equals(ex2.toString())) {
                return new Not(ex1).simplify();
            }
            return new Nor(ex1, ex2);
        }
    }

}
