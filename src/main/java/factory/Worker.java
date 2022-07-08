package factory;

import factory.common.IDManager;
import factory.items.Accessory;
import factory.items.Body;
import factory.items.Car;
import factory.items.Motor;
import threadpool.Task;

import java.util.logging.Logger;

public class Worker implements Task{
    private static Logger logger = Logger.getLogger(Worker.class.getName());
    private Storage<Body> bodyStorage;
    private Storage<Accessory> accessoryStorage;
    private Storage<Motor> motorStorage;
    private Storage<Car> carStorage;
    private factory.common.IDManager IDManager;

    public Worker(Storage<Body> bodyStorage, Storage<Accessory> accessoryStorage, Storage<Motor> motorStorage, Storage<Car> carStorage, IDManager IDManager){
        this.bodyStorage = bodyStorage;
        this.accessoryStorage = accessoryStorage;
        this.motorStorage = motorStorage;
        this.carStorage = carStorage;
        this.IDManager = IDManager;
    }

    public void execute(){
        Body body = bodyStorage.getItem();
        Motor motor = motorStorage.getItem();
        Accessory accessory = accessoryStorage.getItem();

        Car car = new Car(body, accessory, motor, IDManager.getID());
        logger.info("Created car " + car.getID());
        carStorage.addItem(car);
        logger.info("Send car " + car.getID() + " to storage");
    }
}
