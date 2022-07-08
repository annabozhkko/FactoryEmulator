package factory;

import factory.common.ConfigLoader;
import factory.common.IDManager;
import factory.items.Accessory;
import factory.items.Body;
import factory.items.Car;
import factory.items.Motor;
import threadpool.ThreadPool;
import view.View;

import java.util.logging.Logger;

public class Main {
    private static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws Exception{
        logger.info("Factory start");
        ConfigLoader factoryConfig = new ConfigLoader("config.properties");

        Storage<Accessory> accessoryStorage = new Storage<>(factoryConfig.getAccessoryStorageSize());
        Storage<Body> bodyStorage = new Storage<>(factoryConfig.getBodyStorageSize());
        Storage<Motor> motorStorage = new Storage<>(factoryConfig.getMotorStorageSize());
        Storage<Car> carStorage = new Storage<>(factoryConfig.getCarStorageSize());
        logger.info("Created storages");

        IDManager IDManager = new IDManager();
        ThreadPool threadPool = new ThreadPool(factoryConfig.getWorkersCount());
        Controller controller = new Controller(bodyStorage, accessoryStorage, motorStorage, carStorage, IDManager, threadPool);
        Thread threadController = new Thread(controller);
        threadController.start();

        Supplier<Accessory> accessorySuppliers[] = new Supplier[factoryConfig.getAccessorySuppliersCount()];
        for(int i = 0; i < factoryConfig.getAccessorySuppliersCount(); ++i){
            accessorySuppliers[i] = new Supplier<>(Accessory.class, accessoryStorage, IDManager);
            Thread threadSupplierAccessory = new Thread(accessorySuppliers[i]);
            threadSupplierAccessory.start();
        }

        Supplier<Body> bodySupplier = new Supplier<>(Body.class, bodyStorage, IDManager);
        Supplier<Motor> motorSupplier = new Supplier<>(Motor.class, motorStorage, IDManager);

        Thread threadSupplierBody = new Thread(bodySupplier);
        Thread threadSupplierMotor = new Thread(motorSupplier);

        threadSupplierBody.start();
        threadSupplierMotor.start();
        logger.info("Start suppliers");

        Dealer[] dealers = new Dealer[factoryConfig.getDealersCount()];
        for(int i = 0; i < factoryConfig.getDealersCount(); ++i){
            dealers[i] = new Dealer(carStorage, i + 1, controller);
            Thread threadDealer = new Thread(dealers[i]);
            threadDealer.start();
        }
        logger.info("Start dealers");

        FactoryModel factoryModel = new FactoryModel(bodyStorage, motorStorage, accessoryStorage, carStorage, bodySupplier,
                motorSupplier, accessorySuppliers, dealers, controller);
        View factoryView = new View(factoryModel);
    }
}
