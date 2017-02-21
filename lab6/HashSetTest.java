import org.junit.*;
import static org.junit.Assert.*;

import java.util.Iterator;

/**
 * Created by Rooke_000 on 2/20/2017.
 */
public class HashSetTest {
    @Test
    public void test() {
        HashSet set = new HashSet(10);
        set.add(1);
        set.add(7);
        set.add(2);
        set.add(9);

        Iterator iter = set.iterator();
        assertEquals(1, iter.next());
    }

    public static void main(String[] args) {
        new HashSetTest().test();
    }

}