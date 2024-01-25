
public abstract class Animal extends ZooEntity {

    protected int hunger;

    public Animal(String name, int x, int y, int size) {
        super(name, x, y, size);
        this.hunger = 0;
    }

    public abstract void eat(Food food);

    public abstract void move(Zoo zoo);
}