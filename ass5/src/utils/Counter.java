//329233472 yasmin haddad
package utils;

/**
 * describes a counter.
 */
public class Counter {
    private int counter = 0;
    // add number to current count.

    /**
     * increase the counter by a number.
     * @param number the number to increase the counter by
     */
    public void increase(int number) {
        this.counter += number;
    }

    // subtract number from current count.
    /**
     * decrease the counter by a number.
     * @param number the number to decrease the counter by
     */
    public void decrease(int number) {
        this.counter -= number;
    }
    // get current count.
    /**
     * get the value of the counter.
     * @return the value of the counter
     */
    public int getValue() {
        return this.counter;
    }
}