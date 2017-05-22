import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducePrimes
{
   private static BigInteger LAST = new BigInteger("111");
   private static ArrayBlockingQueue<BigInteger> queue = new ArrayBlockingQueue(1000);

   public static void main(String[] args) throws InterruptedException, ExecutionException
   {
      Runnable p1 = producePrimes(new BigInteger("1000000000000000"), 500000, queue);
      Runnable p2 = producePrimes(new BigInteger("1000000000500000"), 500000, queue);
      Runnable p3 = consumePrimes(queue);

      ExecutorService service = Executors.newFixedThreadPool(3);
      long start = System.currentTimeMillis();

      service.execute(p1);
      service.execute(p2);
      service.execute(p3);
      service.shutdown();

      long end = System.currentTimeMillis();
      System.out.println("Milliseconds: " + (end - start));
   }

   public static Runnable producePrimes(BigInteger start, long length, BlockingQueue<BigInteger> queue)
   {
      return () ->
      {
         try
         {
            BigInteger n = start;

            for (long i = 0; i < length; i++)
            {
               if (n.isProbablePrime(100))
                  queue.put(n);

               n = n.add(BigInteger.ONE);
            }
            
            queue.put(LAST);
         }
         catch (InterruptedException e)
         {
            // terminate task
         }
      };
   }

   public static Runnable consumePrimes(BlockingQueue<BigInteger> queue)
   {
      return () ->
      {
         try
         {
            boolean isLast = false;

            while (!isLast)
            {
               BigInteger n = queue.take();

               if (n == LAST)
               {
                  isLast = true;
                  System.out.println(n);
               }
               else if (distinct(n.toString()).length() <= 3)
                  System.out.println(n);
            }
         }
         catch (InterruptedException e)
         {
            // terminate task
         }
      };
   }

   private static String distinct(String s)
   {
      StringBuilder result = new StringBuilder();
      int i = 0;

      while (i < s.length())
      {
         int cp = s.codePointAt(i);
         int cc = Character.charCount(cp);
         if (result.indexOf(s.substring(i, i + cc)) == -1)
         result.appendCodePoint(cp);
         i += cc;
      }

      return result.toString();
   }
}