import java.util.List;

public class FilterUtil
{
    public static <T> int count(List<T> values, Filter<T> filter)
    {
        int counter = 0;;
        for (int i = 0; i < values.size(); i++)
            if (filter.accept(values.get(i))) counter++;
        return counter;
    }
}