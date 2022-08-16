// ID: 322805029

/**
 * The Counter is used for counting things.
 */
public class Counter {
    private int counter;

    /**
     * constructor.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * add number to current count.
     * @param number the number we add
     */
    void increase(int number) {
        this.counter += number;
    }

    /**
     * subtract number from current count.
     * @param number the number we subtract.
     */
    void decrease(int number) {
        this.counter -= number;
    }

    /**
     * get current count.
     * @return the current count
     */
    int getValue() {
        return this.counter;
    }
}
