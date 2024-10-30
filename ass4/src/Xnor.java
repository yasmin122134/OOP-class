// 329233472 yasmin haddad

import java.util.Map;

/**
 * represents the Xnor operation.
 */
public class Xnor extends BinaryExpression {

    private Expression expression1 = getExpression1();
    private Expression expression2 = getExpression2();
    private Expression delegate;

    /**
     * constructor.
     * @param ex1 the first expression
     * @param ex2 the second expression
     */
    public Xnor(Expression ex1, Expression ex2) {
        super(ex1, ex2, "#");
        this.delegate = new Not(new Xor(ex1, ex2));
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return this.delegate.evaluate(assignment);
    }


    @Override
    public Boolean evaluate() throws Exception {
        return this.delegate.evaluate();
    }


    @Override
    public Expression assign(String var, Expression expression) {
        Expression newEx1 = this.expression1.assign(var, expression);
        Expression newEx2 = this.expression2.assign(var, expression);
        return new Xnor(newEx1, newEx2);
    }

    @Override
    public Expression nandify() {
//        return delegate.nandify();
        Expression ex1 = this.expression1.nandify();
        Expression ex2 = this.expression2.nandify();
        Expression nand11 = new Nand(ex1, ex1);
        Expression nand22 = new Nand(ex2, ex2);
        Expression nand12 = new Nand(ex1, ex2);
        return new Nand(new Nand(nand11, nand22), nand12);
    }

    @Override
    public Expression norify() {
        Expression ex1 = this.expression1.norify();
        Expression ex2 = this.expression2.norify();
        Expression nor12 = new Nor(ex1, ex2);

        return new Nor(new Nor(ex1, nor12), new Nor(ex2, nor12));
    }

    @Override
    public Expression simplify() {
        try {
            return new Val(this.evaluate());
        } catch (Exception e) {
            Expression ex1 = this.expression1.simplify();
            Expression ex2 = this.expression2.simplify();

            if (ex1.toString().equals(ex2.toString())) {
                return new Val(true);
            }

            return new Xnor(ex1, ex2);
        }
    }


}
