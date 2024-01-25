
import java.util.*;

import java.awt.*;

public class Cat extends Animal {

    private int lives;

    public Cat(String name, int x, int y) {
        super(name, x, y);

        this.lives = 9;
    }

    @Override
    public void eat(Food food) {
        if(food.isAnimalProduct()) {
            if(food.isVegtableProduct()) {
                if(hunger > 10 && rand.nextDouble() > 0.99) {
                    hunger -= food.getNutrition();
                    food.beEaten();
                }
            }
            else {
                if(hunger > 10 && rand.nextDouble() > 0.50) {
                    hunger -= food.getNutrition();
                    food.beEaten();
                }
            }
        }
    }

    @Override
    public void tick(Zoo zoo) {
        age++;

        if(age%10 == 0) {
            move(zoo);
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

                ArrayList<ZooEntity> here = zoo.at(xPos+dx, yPos+dy);

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
            System.out.println("chase" + xy[0] + " " + xy[1]);
            xPos = xy[0];
            yPos = xy[1];
        }
        /*else if(runSpaces.size() > 0) {
            int xy[] = runSpaces.get(rand.nextInt(runSpaces.size()));
            System.out.println("run " + xy[0] + " " + xy[1] + " " + runSpaces.size());
            xPos = xy[0];
            yPos = xy[1];
        }*/
        else {
            xPos += new int[]{1, 0, -1}[rand.nextInt(3)];
            yPos += new int[]{1, 0, -1}[rand.nextInt(3)];
        }
        System.out.println("moved");

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(Zoo.wrap(xPos,25)*20, Zoo.wrap(yPos,25)*20, 20, 20);
        System.out.println(xPos + " " + yPos);
    }

}