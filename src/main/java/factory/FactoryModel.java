package factory;

import factory.items.Accessory;
import factory.items.Body;
import factory.items.Motor;
import factory.items.Car;

public class FactoryModel {
    private Storage<Body> bodyStorage;
    private Storage<Motor> motorStorage;
    private Storage<Accessory> accessoryStorage;
    private Storage<Car> carStorage;
    private Supplier<Body> bodySupplier;
    private Supplier<Motor> motorSupplier;
    private Supplier<Accessory> accessorySuppliers[];
    private Dealer []dealers;
    private Controller controller;

    public FactoryModel(Storage<Body> bodyStorage, Storage<Motor> motorStorage, Storage<Accessory> accessoryStorage, Storage<Car> carStorage,
            Supplier<Body> bodySupplier, Supplier<Motor> motorSupplier, Supplier<Accessory> accessorySuppliers[], Dealer []dealers, Controller controller){
        this.bodyStorage = bodyStorage;
        this.motorStorage = motorStorage;
        this.accessoryStorage = accessoryStorage;
        this.carStorage = carStorage;
        this.bodySupplier = bodySupplier;
        this.motorSupplier = motorSupplier;
        this.accessorySuppliers = accessorySuppliers;
        this.dealers = dealers;
        this.controller = controller;
    }

    public Storage<Body> getBodyStorage(){
        return bodyStorage;
    }

    public Storage<Motor> getMotorStorage(){
        return motorStorage;
    }

    public Storage<Accessory> getAccessoryStorage(){
        return accessoryStorage;
    }

    public Storage<Car> getCarStorage(){
        return carStorage;
    }

    public Supplier<Body> getBodySupplier(){
        return bodySupplier;
    }

    public Supplier<Accessory>[] getAccessorySuppliers(){
        return accessorySuppliers;
    }

    public Supplier<Motor> getMotorSupplier(){
        return motorSupplier;
    }

    public Dealer[] getDealers(){
        return dealers;
    }

    public Controller getController(){
        return controller;
    }
}
