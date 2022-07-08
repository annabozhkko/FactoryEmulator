package factory;

import java.util.Queue;
import java.util.LinkedList;
import java.util.logging.Logger;

public class Storage <T>{
    private static Logger logger = Logger.getLogger(Main.class.getName());
    private Queue<T> items = new LinkedList<>();
    private int countItems = 0;
    private int allCountItems = 0;
    private int capacity;

    public Storage(int capacity){
        this.capacity = capacity;
    }

    synchronized public void addItem(T item){
        while(getCountItems() == getCapacity()){
            try {
                wait();
            }catch (Exception exp){
                logger.info(exp.getMessage());
            }
        }
        items.add(item);
        countItems++;
        allCountItems++;
        notifyAll();
    }

    synchronized public T getItem() {
        while(getCountItems() == 0) {
            try {
                wait();
            } catch (Exception exp) {
                logger.info(exp.getMessage());
            }
        }
        T item = items.remove();
        countItems--;
        notifyAll();
        return item;
    }

    public int getCountItems(){
        return countItems;
    }

    public int getCapacity(){
        return capacity;
    }

    public int getAllCountItems(){
        return allCountItems;
    }
}
