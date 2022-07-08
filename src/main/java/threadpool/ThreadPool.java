package threadpool;
import java.util.*;

public class ThreadPool{
   private List taskQueue = new LinkedList();
   private Set availableThreads = new HashSet();

   public void addTask(Task task) {
      synchronized ( taskQueue ) {
         taskQueue.add(task);
         taskQueue.notify();
      }
   }

   public ThreadPool(int countThreads) {
      for (int i = 0; i < countThreads; i++)
         availableThreads.add(new PooledThread(taskQueue));

      for (Iterator iter = availableThreads.iterator(); iter.hasNext(); )
         ((Thread)iter.next()).start();
   }
}
