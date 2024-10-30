// 329233472 yasmin haddad

/**
 * represents a unary expression.
 */
public abstract class UnaryExpression extends BaseExpression {
    private String type;
    private Expression expression1 = getExpression1();

    /**
     * constructor.
     * @param expression the expression
     * @param type the type of the expression
     */
    public UnaryExpression(Expression expression, String type) {
        super(expression, null);
        this.type = type;
    }


@Override
    public String toString() {
        String s = this.expression1.toString();
        return type + "(" + s + ")";
    }
}
