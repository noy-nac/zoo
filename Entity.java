
import java.util.Random;

import java.awt.Graphics;

public abstract class Entity {

    protected static Random rand = new Random();

    private static int lastID = 0;
    protected int id;

    protected String name;
    protected int xPos, yPos;
    protected int age;
    protected boolean isAlive;

    public Entity(String name, int x, int y) {
        this.id = lastID;
        lastID = lastID + 1;
        this.name = name;
        this.xPos = x;
        this.yPos = y;
        this.age = 0;
        this.isAlive = true;
    }

    public abstract void tick(Zoo zoo);

    public abstract void draw(Graphics g);

    public boolean isAlive() {
        return isAlive;
    }

    public int getX() {
        return xPos;
    }

    public int getY() {
        return yPos;
    }

}