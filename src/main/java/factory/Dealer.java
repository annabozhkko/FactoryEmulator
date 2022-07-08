package factory;

import factory.items.Car;

import java.util.logging.Logger;

public class Dealer implements Runnable{
    private static Logger logger = Logger.getLogger(Dealer.class.getName());
    private int ID;
    private int waitTime = 5000;
    private Storage<Car> carStorage;
    private Controller controller;

    public Dealer(Storage<Car> carStorage, int ID, Controller controller){
        this.ID = ID;
        this.carStorage = carStorage;
        this.controller = controller;
    }

    public void run(){
        while(true){
            controller.addRequire();
            synchronized (controller) {
                controller.notify();
            }
            Car car = carStorage.getItem();
            controller.removeRequire();
            logger.info("Dealer " + ID + ": car " + car.getID() + "  (Body: " + car.getBodyID() + ", Motor: " +
                    car.getMotorID() + ", Accessory: " + car.getAccessoryID() + ")");
            try {
                Thread.sleep(waitTime);
            }catch (Exception exp){
                logger.info(exp.getMessage());
            }
        }
    }

    public void setWaitTime(int waitTime){
        this.waitTime = waitTime;
    }
}
