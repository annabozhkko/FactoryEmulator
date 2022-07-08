package factory;

import factory.common.IDManager;
import factory.items.Item;

import java.util.logging.Logger;

public class Supplier<T extends Item> implements Runnable{
    private static Logger logger = Logger.getLogger(Supplier.class.getName());
    private Storage<T> storage;
    private final Class<T> classItem;
    private int waitTime = 5000;
    private factory.common.IDManager IDManager;

    public Supplier(Class<T> classItem, Storage<T> storage, IDManager IDManager){
        this.classItem = classItem;
        this.storage = storage;
        this.IDManager = IDManager;
    }

    @Override
    public void run(){
        try {
            while (true) {
                T item = classItem.getDeclaredConstructor(int.class).newInstance(IDManager.getID());
                logger.info("Created " + classItem.getName() + " " + item.getID());
                storage.addItem(item);
                logger.info("Send " + classItem.getName() + " " + item.getID() + " to storage");
                Thread.sleep(waitTime);
            }
        }catch (Exception exp) {
            logger.info(exp.getMessage());
        }
    }

    public void setWaitTime(int waitTime){
        this.waitTime = waitTime;
    }
}

