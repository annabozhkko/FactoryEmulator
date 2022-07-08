package factory.common;

import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private Properties properties = new Properties();

    public ConfigLoader(String configFile) throws Exception{
        InputStream fin = this.getClass().getClassLoader().getResourceAsStream(configFile);
        properties.load(fin);
    }

    public int getBodyStorageSize(){
        return Integer.parseInt(properties.getProperty("BodySize"));
    }

    public int getMotorStorageSize(){
        return Integer.parseInt(properties.getProperty("MotorSize"));
    }

    public int getAccessoryStorageSize(){
        return Integer.parseInt(properties.getProperty("AccessorySize"));
    }

    public int getCarStorageSize(){
        return Integer.parseInt(properties.getProperty("CarSize"));
    }

    public int getAccessorySuppliersCount(){
        return Integer.parseInt(properties.getProperty("AccessorySuppliers"));
    }

    public int getWorkersCount(){
        return Integer.parseInt(properties.getProperty("Workers"));
    }

    public int getDealersCount(){
        return Integer.parseInt(properties.getProperty("Dealers"));
    }
}
