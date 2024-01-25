
public abstract class Animal extends Entity {

    protected int hunger;

    protected boolean isSick;

    public Animal(String name, int x, int y) {
        super(name, x, y);
        this.hunger = 0;
        this.isSick = false;
    }

    public abstract void eat(Food food);

    public abstract void move(Zoo zoo);

    public void setSick(boolean sick) {
        isSick = sick;
    }
}