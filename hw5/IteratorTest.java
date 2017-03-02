import org.hamcrest.collection.IsArray;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class IteratorTest {

    @Test
    public void test1() {
        ArraySet arraySet = new ArraySet();

        for (int n = 0; n < 100; n++)
            arraySet.set(n);

        Iterator<Integer> iterator = arraySet.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}