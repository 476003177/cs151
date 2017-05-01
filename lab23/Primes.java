import java.math.BigInteger;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Rooke_000 on 5/1/2017.
 */
public class Primes {

    /**
     * Prints all of the prime numbers from the start to (the start + the length)
     * @param start the point to start printing primes from
     * @param length how many numbers ahead of start to look for primes
     * @return a runnable instance of this task
     */
    public static Runnable printPrimes(BigInteger start, long length) {
        return () ->
        {
            BigInteger n = start;
            for (long i = 0; i < length; i++)
            {
                if (n.isProbablePrime(100))
                    System.out.println(n);
                n = n.add(BigInteger.ONE);
            }
        };
    }

    public static void main(String[] args) {
        // You can tell this runs
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Runnable r1 = printPrimes(new BigInteger("1000000000000000"), 10000);
        Runnable r2 = printPrimes(new BigInteger("2000000000000000"), 10000);

        executorService.submit(r1);
        executorService.submit(r2);
    }

}
