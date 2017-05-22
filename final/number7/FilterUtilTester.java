import java.util.ArrayList;

/**
 * Created by rooke on 5/22/2017.
 */
public class FilterUtilTester {
    public static void main(String[] args) throws Exception {
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(new Country("Uruguay", 176220));
        countries.add(new Country("Thailand", 514000));
        countries.add(new Country("Belgium", 30510));

        Filter<Country> bigCountry = c -> c.getArea() > 500000;
        Filter<Object> evenHashCode = obj -> obj.hashCode() % 2 == 0;

        int result = FilterUtil.count(countries, evenHashCode);

        countries.forEach((c) -> System.out.println(c.hashCode()));

        System.out.println(result);

        System.out.println();
        System.out.println(FilterUtil.countConcurrent(countries, evenHashCode));
    }
}
