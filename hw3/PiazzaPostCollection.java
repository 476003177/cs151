import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class PiazzaPostCollection<V> {
    private HashSet<Integer> emptySpots;
    private Map<Integer, V> followupDiscussions;

    public PiazzaPostCollection() {
        emptySpots = new HashSet<>();
        followupDiscussions = new HashMap<Integer, V>();
    }

    public int add(V value) {
        int id = (emptySpots.isEmpty()) ? followupDiscussions.size() : emptySpots.iterator().next();

        followupDiscussions.put(id, value);

        return id;
    }

    public V get(int id) {
        return followupDiscussions.get(id);
    }

    public V remove(int id) {
        V ret = followupDiscussions.remove(id);

        if (ret != null)
            emptySpots.add(id);

        return ret;
    }

    public int size() {
        return followupDiscussions.size();
    }
}