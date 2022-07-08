package factory;

import factory.common.IDManager;
import factory.items.Accessory;
import factory.items.Body;
import factory.items.Car;
import factory.items.Motor;
import threadpool.ThreadPool;
import java.util.logging.Logger;

public class Controller implements Runnable{
    private static Logger logger = Logger.getLogger(Dealer.class.getName());
    private int workerRequires = 0;
    private int carRequires = 0;
    ThreadPool threadPool;
    private Storage<Body> bodyStorage;
    private Storage<Accessory> accessoryStorage;
    private Storage<Motor> motorStorage;
    private Storage<Car> carStorage;
    private factory.common.IDManager IDManager;

    public Controller(Storage<Body> bodyStorage, Storage<Accessory> accessoryStorage, Storage<Motor> motorStorage,
                      Storage<Car> carStorage, IDManager IDManager, ThreadPool threadPool){
        this.bodyStorage = bodyStorage;
        this.accessoryStorage = accessoryStorage;
        this.motorStorage = motorStorage;
        this.carStorage = carStorage;
        this.IDManager = IDManager;
        this.threadPool = threadPool;
    }

    public void run(){
        while(true){
            while (workerRequires == 0) {
                try {
                    synchronized (this) {
                        this.wait();
                    }
                } catch (Exception exp) {
                    logger.info(exp.getMessage());
                    return;
                }
            }
            Worker worker = new Worker(bodyStorage, accessoryStorage, motorStorage, carStorage, IDManager);
            workerRequires--;
            threadPool.addTask(worker);
            notifyAllThreads();
        }
    }

    synchronized public void addRequire(){
        carRequires++;
        workerRequires++;
    }

    synchronized public void removeRequire(){
        carRequires--;
    }

    public int getCountRequires(){
        return carRequires;
    }

    synchronized private void notifyAllThreads(){
        notifyAll();
    }

}
