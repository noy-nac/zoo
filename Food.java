

public abstract class Food extends ZooEntity  {

    protected boolean animal_product;
    protected boolean vegtable_product;

    protected int nutrition;

    public Food(String name, int x, int y, int size, boolean animal, boolean vegtable, int nutrition) {
        super(name, x, y, size);
        this.animal_product = animal;
        this.vegtable_product = vegtable;
        this.nutrition = nutrition;
    }

    public abstract void beEaten();

}