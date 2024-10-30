// 329233472 yasmin haddad

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * represents a value.
 */
public class Val implements Expression {
    private boolean value;

    /**
     * constructor.
     * @param b the value
     */
    public Val(boolean b) {
        this.value = b;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return this.value;
    }


    /**
     * returns the value since it is the evaluation of the expression.
     * @return the value
     * @throws Exception if the expression contains a variable, but here it doesn't.
     */
    @Override
    public Boolean evaluate() throws Exception {
        return this.value;
    }


    @Override
    public List<String> getVariables() {
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        if (this.value) {
            return "T";
        }
        return "F";
    }


    @Override
    public Expression assign(String var, Expression expression) {
        return this.value ? new Val(true) : new Val(false);
    }

    @Override
    public Expression nandify() {
        return new Val(this.value);
    }

    @Override
    public Expression norify() {
        return new Val(this.value);
    }

    @Override
    public Expression simplify() {
        return new Val(this.value);
    }
}
