// 329233472 yasmin haddad

import java.util.Map;

/**
 * represents a nand expression.
 */
public class Nand extends BinaryExpression {
    private Expression delegator;
    private Expression expression1 = getExpression1();
    private Expression expression2 = getExpression2();

    /**
     * constructor.
     * @param ex1 the first expression
     * @param ex2 the second expression
     */
    public Nand(Expression ex1, Expression ex2) {
        super(ex1, ex2, "A");
        this.delegator = new Not(new And(ex1, ex2));
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return this.delegator.evaluate(assignment);
//        return !(expression1.evaluate(assignment) && expression2.evaluate(assignment));
    }

    @Override
    public Boolean evaluate() throws Exception {
        return this.delegator.evaluate();
//        return !(expression1.evaluate() && expression2.evaluate());
    }

    @Override
    public Expression assign(String var, Expression expression) {
//        return delegator.assign(var, expression);
        Expression newEx1 = this.expression1.assign(var, expression);
        Expression newEx2 = this.expression2.assign(var, expression);
        return new Nand(newEx1, newEx2);
    }


    @Override
    public Expression nandify() {
//        return delegator.nandify();
        return new Nand(this.expression1.nandify(), this.expression2.nandify());
    }


    @Override
    public Expression norify() {

//        return delegator.norify();

        Expression nory1 = this.expression1.norify();
        Expression nory2 = this.expression2.norify();
        Expression nor1 = new Nor(nory1, nory1);
        Expression nor2 = new Nor(nory2, nory2);
        return new Nor(new Nor(nor1, nor2), new Nor(nor1, nor2));
    }


    @Override
    public Expression simplify() {
        try {
            return new Val(this.evaluate());
        } catch (Exception e) {
            Expression ex1 = this.expression1.simplify();
            Expression ex2 = this.expression2.simplify();
            if (ex1.toString().equals("F") || ex2.toString().equals("F")) {
                return new Var("T");
            }
            if (ex1.toString().equals("T")) {
                return new Not(ex2).simplify();
            }
            if (ex2.toString().equals("T")) {
                return new Not(ex1).simplify();
            }
            if (ex1.toString().equals(ex2.toString())) {
                return new Not(ex1).simplify();
            }
            return new Nand(ex1, ex2);
        }
    }

}
