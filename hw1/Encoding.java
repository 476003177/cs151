import java.util.Set;
import java.util.TreeSet;

public class Encoding {

    public static Set<String> morseCodes(int m, int n) {
        Set<String> result = new TreeSet<>();
        Set<String> subPermutations;

        if (m == 1 && n <= 0) {
            result.add(".");
            return result;
        }
        if (n == 1 && m <= 0) {
            result.add("-");
            return result;
        }

        if (m >= 1) {
            subPermutations = morseCodes(m - 1, n);
            for (String str: subPermutations)
                result.add("." + str);
        }
        if (n >= 1) {
            subPermutations = morseCodes(m, n - 1);
            for (String str: subPermutations)
                result.add("-" + str);
        }

        return result;
    }

    public static void main(String[] args) {
        morseCodes(3,2);
    }

}