package thegame;

/**
 * The Counter class.
 * @author Ilan Bitan
 *
 */
public class Counter {

    private int count;

    /**
     * a constructor.
     * @param current - the current counter.
     */
    public Counter(int current) {
        this.count = current;
    }

    /**
     * the method adds number to current count.
     * @param number - the number to be added.
     */
    void increase(int number) {
        this.count += number;
    }

    /**
     * the method subtracts number from current count.
     * @param number - the number to be decreased.
     */
    void decrease(int number) {
        this.count -= number;
    }

    /**
     * get current count.
     * @return the counter.
     */
    int getValue() {
        return this.count;
    }
 }