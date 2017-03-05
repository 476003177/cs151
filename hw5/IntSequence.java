import java.util.Iterator;

/**
 * Created by Tyler on 3/5/2017.
 */
public interface IntSequence {

    /**
     * Gives next int in sequence
     * @return the next int in the sequence or null if all elements have been visited
     */
    public Integer next();

    /**
     * Turns Iterator\<Integer\> into an IntSequence
     * @param iterator the iterator to turn into an IntSequence
     * @return the IntSequence created from the iterator.
     */
    public static IntSequence fromIterator(Iterator<Integer> iterator) {
        return () -> (iterator.hasNext() ? iterator.next() : null);
    }

    /**
     * Alternates between returning from this IntSequence, and the other IntSequence
     * @param other the other IntSequence to take from
     * @return a sequence alternately taking elements from this and the other sequence, until both are exhausted
     */
    public default IntSequence alternate(IntSequence other) {
        boolean[] giveThisNext = {true};
        return () -> {
            Integer thisNext = null;
            Integer otherNext = null;

            if (giveThisNext[0])
                thisNext = this.next();
            giveThisNext[0] = !giveThisNext[0];

            return (thisNext != null) ? thisNext : (((otherNext = other.next()) != null) ? otherNext : this.next());
        };
    }

}
