// 329233472 yasmin haddad

import java.util.Map;
import java.util.TreeMap;

/**
 * the main class.
 */
public class ExpressionsTest {
    /**
     * the main method.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        try {
            Expression ex = new And(new Or(new Var("y"), new Var("s")), new Var("x"));
            System.out.println(ex);
            Map<String, Boolean> assignment = new TreeMap<>();
            assignment.put("x", true);
            assignment.put("y", false);
            assignment.put("s", false);
            Boolean value = ex.evaluate(assignment);
            System.out.println(value);
            System.out.println(ex.nandify());
            System.out.println(ex.norify());
            System.out.println(ex.simplify());
        } catch (Exception e) {
            System.out.println((e.getMessage()));
        }
    }
}