import org.junit.*;

import java.util.Iterator;

import static org.junit.Assert.*;

public class HashSetTest {

    @Test
    public void test0() {
        HashSet set = new HashSet(10);
        set.add(1);
        set.add(7);
        set.add(2);
        set.add(9);

        Iterator iter = set.iterator();
        assertEquals(1, iter.next());
    }

    @Test
    public void test1() {
        HashSet set = new HashSet(5);
        set.add(0);
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        Iterator iter = set.iterator();
        assertEquals(0, iter.next());
        assertEquals(1, iter.next());
        assertEquals(2, iter.next());
        assertEquals(3, iter.next());
        assertEquals(4, iter.next());
        assertFalse(iter.hasNext());
        iter = set.iterator();
        iter.next();
        iter.remove();
        iter = set.iterator();
        assertEquals(1, iter.next());
        assertEquals(2, iter.next());
        assertEquals(3, iter.next());
        assertEquals(4, iter.next());
        assertFalse(iter.hasNext());
    }

}