package factory.items;

public abstract class Item {
    private int ID;

    public Item(int ID){
        this.ID = ID;
    }
    public int getID(){return ID; }
}


