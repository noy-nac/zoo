

public abstract class ZooEntity {

    protected static Random rand = new Random();

    protected String name;
    protected int xPos, yPos;
    protected int age;
    protected boolean isAlive;

    public ZooEntity(String name, int x, int y) {
        this.name = name;
        this.xPos = x;
        this.yPos = y;
        this.age = 0;
        this.isAlive = true;
    }

    public abstract void tick(Zoo zoo);

    public abstract void draw();

}