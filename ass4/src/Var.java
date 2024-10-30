// 329233472 yasmin haddad

import java.util.List;
import java.util.Map;

/**
 * represents a variable.
 */
public class Var implements Expression {
    private String name;

    /**
     * constructor.
     * @param name the name of the variable
     */
    public Var(String name) {
        this.name = name;
    }


    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        if (assignment.containsKey(this.name)) {
            return assignment.get(this.name);
        }
        throw new Exception("Variable not found in the assignment.");
    }



    @Override
    public Boolean evaluate() throws Exception {
        throw new Exception("could not evaluate without assignment");
    }


    @Override
    public List<String> getVariables() {
        List<String> l = new java.util.ArrayList<String>();
        l.add(this.name);
        return l;
    }


    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (this.name.equals(var)) {
            return expression;
        }
        return new Var(this.name);
    }

    @Override
    public Expression nandify() {
        return new Var(this.name);
    }

    @Override
    public Expression norify() {
        return new Var(this.name);
    }

    @Override
    public Expression simplify() {
        return new Var(this.name);
    }

}
