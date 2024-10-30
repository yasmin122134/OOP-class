// 329233472 yasmin haddad

import java.util.Map;

/**
 * represents a Xor expression.
 */
public class Xor extends BinaryExpression {
    private Expression expression1 = getExpression1();
    private Expression expression2 = getExpression2();

    /**
     * constructor.
     * @param ex1 the first expression
     * @param ex2 the second expression
     */
    public Xor(Expression ex1, Expression ex2) {
        super(ex1, ex2, "^");
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return this.expression1.evaluate(assignment) ^ this.expression2.evaluate(assignment);
    }


    @Override
    public Boolean evaluate() throws Exception {
        return this.expression1.evaluate() ^ this.expression2.evaluate();
    }


    @Override
    public Expression assign(String var, Expression expression) {
        Expression newEx1 = this.expression1.assign(var, expression);
        Expression newEx2 = this.expression2.assign(var, expression);
        return new Xor(newEx1, newEx2);
    }

    @Override
    public Expression nandify() {
        Expression ex1 = this.expression1.nandify();
        Expression ex2 = this.expression2.nandify();
        Expression nand12 = new Nand(ex1, ex2);
        return new Nand(new Nand(ex1, nand12), new Nand(ex2, nand12));
    }

    @Override
    public Expression norify() {
        Expression ex1 = this.expression1.norify();
        Expression ex2 = this.expression2.norify();

        Expression nor11 = new Nor(ex1, ex1);
        Expression nor22 = new Nor(ex2, ex2);
        Expression nor12 = new Nor(ex1, ex2);

        return new Nor(new Nor(nor11, nor22), nor12);
    }

    @Override
    public Expression simplify() {
        try {
            return new Val(this.evaluate());
        } catch (Exception e) {
            Expression ex1 = this.expression1.simplify();
            Expression ex2 = this.expression2.simplify();

            if (ex1.toString().equals("T")) {
                return new Not(ex2).simplify();
            }
            if (ex2.toString().equals("T")) {
                return new Not(ex1).simplify();
            }
            if (ex1.toString().equals("F")) {
                return ex2;
            }
            if (ex2.toString().equals("F")) {
                return ex1;
            }
            if (ex1.toString().equals(ex2.toString())) {
                return new Var("F");
            }

            return new Xor(ex1, ex2);
        }
    }
}
