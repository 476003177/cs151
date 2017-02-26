/**
 * An interface for sets containing integers
 */
public interface IntSet
{
    /**
     * Checks if n exists in object
     * @param n the number to check
     * @return true if n exists in object
     */
    boolean test(int n);

    /**
     * Adds number to set
     * @param n number to add to set
     */
    void set(int n);

    /**
     * Removes number from set
     * @param n number to remove
     */
    void clear(int n);

    /**
     * Finds min of the set
     * @return min of the set
     */
    int min();

    /**
     * Finds max of the set
     * @return max of the set
     */
    int max();

    /**
     * Returns the number of elements in the set
     * @return the number of elements in the set
     */
    int size();
}