import java.util.List;

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
}