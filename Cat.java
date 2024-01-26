
import java.util.*;
import java.awt.*;

public class Cat extends Animal {

    private int lives;

    public Cat(String name, int x, int y) {
        super(name, x, y);
        this.hunger = 100;
        this.lives = 9;
    }

    @Override
    public void eat(Food food) {
        if(food.isAnimalProduct()) {
            if(food.isVegtableProduct()) {
                if(hunger > 10 && rand.nextDouble() > 0.99) {
                    hunger -= food.getNutrition();
                    food.beEaten(this);
                }
            }
            else {
                if(hunger > 10 && rand.nextDouble() > 0.50) {
                    hunger -= food.getNutrition();
                    food.beEaten(this);
                }
            }
        }
    }

    @Override
    public void tick(Zoo zoo) {
        if(isSick) {
            age += 2;
            if(Zoo.rand.nextDouble() < 0.1) {
                isSick = false;
            }
        }
        else {
            age++;
        }

        if(age > 2000) {
            isAlive = false;
            return;
        }

        if(age%10 == 0) {
            hunger += 5;
            move(zoo);
        }
        ArrayList<Entity> onNow = zoo.at(xPos, yPos);
        for(Entity other : onNow) {
            if(other instanceof Food) {
                eat((Food)other); // GACK (?)
            }
        }
    }

    @Override
    public void move(Zoo zoo) {
        int[] dxVals = {1, 0, -1};
        int[] dyVals = {1, 0, -1};

        ArrayList<int[]> chaseSpaces = new ArrayList<>();
        ArrayList<int[]> runSpaces = new ArrayList<>();

        for(int dx : dxVals) {
            for(int dy : dyVals) {

                ArrayList<Entity> here = zoo.at(xPos+dx, yPos+dy);

                if(here.size() > 0 && here.get(0) instanceof Food) {
                    chaseSpaces.add(new int[]{xPos+dx, yPos+dy});
                }
                else if(here.size() > 0 && here.get(0) instanceof Animal) {
                    runSpaces.add(new int[]{xPos+dx, yPos+dy});
                }
            }
        }
        
        if(chaseSpaces.size() > 0) {
            int xy[] = chaseSpaces.get(rand.nextInt(chaseSpaces.size()));
            xPos = xy[0];
            yPos = xy[1];
        }
        else {
            xPos += new int[]{1, 0, -1}[rand.nextInt(3)];
            yPos += new int[]{1, 0, -1}[rand.nextInt(3)];
        }

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(Zoo.wrap(xPos,Zoo.ZOO_COLS)*Zoo.SCALE, Zoo.wrap(yPos,Zoo.ZOO_ROWS)*Zoo.SCALE, Zoo.SCALE, Zoo.SCALE);
    }

}