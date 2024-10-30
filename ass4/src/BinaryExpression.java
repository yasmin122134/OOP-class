// 329233472 yasmin haddad



/**
 * represents a binary expression.
 */
public abstract class BinaryExpression extends BaseExpression {

    private String type;
    private Expression expression1 = getExpression1();
    private Expression expression2 = getExpression2();

    /**
     * constructor.
     * @param expression1 the first expression
     * @param expression2 the second expression
     * @param type the type of the expression
     */
    public BinaryExpression(Expression expression1, Expression expression2, String type) {
        super(expression1, expression2);
        this.type = type;
    }


    @Override
    public String toString() {
        String s1 = this.expression1.toString();
        String s2 = this.expression2.toString();
        return "(" + s1 + " " + type + " " + s2 + ")";

    }
}
