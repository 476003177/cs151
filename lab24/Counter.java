public class Counter
{
   public Counter()
   { count = 0; }

   public synchronized void increment() throws InterruptedException
   {
      count++;
      notifyAll();
   }

   public synchronized int get()
   { return count; }

   private int count;
}