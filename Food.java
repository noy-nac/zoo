

public abstract class Food extends ZooEntity  {

    protected boolean animal_product;
    protected boolean vegtable_product;

    protected int nutrition;

    public Food(String name, int x, int y, boolean animal, boolean vegtable, int nutrition) {
        super(name, x, y);
        this.animal_product = animal;
        this.vegtable_product = vegtable;
        this.nutrition = nutrition;
    }

    public abstract void beEaten();

    public int getNutrition() {
        return nutrition;
    }

    public boolean isVegtableProduct() {
        return vegtable_product;
    }

    public boolean isAnimalProduct() {
        return animal_product;
    }

}