
import java.util.Random;

public class Cat extends Animal {

    private int lives;

    public Cat(String name, int x, int y, int size) {
        super(name, x, y, size);

        this.lives = 9;
    }

    @Override
    public void eat(Food food) {
        if(food.isAnimalProduct()) {
            if(food.isVegtableProduct()) {
                if(hunger > 10 && rand.nextDouble() > 0.99) {
                    hunger -= food.getNutrition()
                    food.beEaten()
                }
            }
            else {
                if(hunger > 10 && rand.nextDouble() > 0.50) {
                    hunger -= food.getNutrition()
                    food.beEaten()
                }
            }
        }
    }

    @Override
    public void tick(Zoo zoo) {
        age++;

        if(tick%10 == 0) {
            move(zoo);
        }
    }

    @Override
    public void move(Zoo zoo) {
        int[] dxVals = {1, -1};
        int[] dyVals = {1, -1};

        ArrayList<int[2]> chaseSpaces = new ArrayList<>();
        ArrayList<int[2]> runSpaces = new ArrayList<>();

        for(dx : dxVals) {
            for(dy : dyVals) {
                if(zoo.at(x+dx, y+dy) instanceof Food) {
                    chaseSpaces.add(new int[]{x+dx, y+dy});
                }
                else if(zoo.at(x+dx, y+dy) instanceof Animal) {
                    runSpaces.add(new int[]{x+dx, y+dy});
                }
            }
        }
        
        if(chaseSpaces.size() > 0) {
            int[2] dxy = chaseSpaces.get(rand.nextInt(chaseSpaces.size()))
            x += dxy[0];
            y += dxy[1];
        }
        else if(runSpaces.size() > 0) {
            int[2] dxy = runSpaces.get(rand.nextInt(runSpaces.size()))
            x += dxy[0];
            y += dxy[1];
        }
        else {
            x += dyVals[rand.nextInt(2)];
            y += dyVals[rand.nextInt(2)];
        }

    }

}