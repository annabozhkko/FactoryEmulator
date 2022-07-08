package threadpool;

import java.util.*;

public class PooledThread extends Thread {
   private List taskQueue;

   public PooledThread(List taskQueue) {
      this.taskQueue = taskQueue;
   }

   public void run() {
      Task toExecute = null;
      while (true)
      {
         synchronized (taskQueue)
         {
            if (taskQueue.isEmpty())
            {
               try {
                 taskQueue.wait();
               }
               catch (InterruptedException ex) {
                  System.err.println("Thread was inetrrupted:" + getName());
               }
               continue;
            }
            else
            {
               toExecute = (Task)taskQueue.remove(0);
            }
         }

         toExecute.execute();
      }
   }
}