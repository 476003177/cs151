import java.util.Collection;
import java.util.HashSet;

/**
 * Created by rooke on 5/22/2017.
 */
public class AllFilter<T> implements Filter<T> {

    private Collection<Filter<T>> filters;

    public AllFilter() {
        filters = new HashSet<>();
    }

    public boolean add(Filter<T> filter) {
        return filters.add(filter);
    }

    @Override
    public boolean accept(T element) {
        return false;
    }
}
