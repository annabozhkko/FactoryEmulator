package factory.items;

public class Car {
    private int ID;
    private Body body;
    private Accessory accessory;
    private Motor motor;

    public Car(Body body, Accessory accessory, Motor motor, int ID){
        this.accessory = accessory;
        this.body = body;
        this.motor = motor;
        this.ID = ID;
    }

    public int getID(){
        return ID;
    }

    public int getBodyID(){
        return body.getID();
    }

    public int getMotorID(){
        return motor.getID();
    }

    public int getAccessoryID(){
        return accessory.getID();
    }
}
