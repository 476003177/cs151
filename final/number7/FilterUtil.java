import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FilterUtil
{
    /*
     * Didn't work before because Object is a superclass of Country,
     * and the count method didn't allow filters to be
     * a superclass of T.
     */
    public static <T> int count(List<T> values, Filter<? super T> filter)
    {
        int counter = 0;;
        for (int i = 0; i < values.size(); i++)
            if (filter.accept(values.get(i))) counter++;
        return counter;
    }

    public static <T> int countConcurrent(List<T> values, Filter<? super T> filter) throws Exception {
        Callable<Integer> c1 = () -> {
            int counter = 0;
            for (int i = 0; i < values.size() / 2; i++)
                if (filter.accept(values.get(i))) counter++;
            return counter;
        };

        Callable<Integer> c2 = () -> {
            int counter = 0;
            for (int i = values.size() / 2; i < values.size(); i++)
                if (filter.accept(values.get(i))) counter++;
            return counter;
        };

        Collection<Callable<Integer>> calls = new HashSet<>();
        calls.add(c1);
        calls.add(c2);

        ExecutorService service = Executors.newFixedThreadPool(2);

        int finalCount = 0;
        for (Future<Integer> result: service.invokeAll(calls)) {
            finalCount += result.get();
        }

        return finalCount;
    }
}