/**
 * Created by rooke on 5/22/2017.
 */
public class AllFilterTester {
    public static void main(String[] args) {
        AllFilter<Number> allFilter = new AllFilter<>();
        allFilter.add((Number n) -> (n.intValue() > 3));
        allFilter.add((Number n) -> (n.intValue() % 2 == 1));
        allFilter.add((Number n) -> (n.doubleValue() < 5.5));

        System.out.println(allFilter.accept(4));
    }

}
