package factory.common;

public class IDManager {
    private int currentID = 0;

    synchronized public int getID(){
        return currentID++;
    }
}
