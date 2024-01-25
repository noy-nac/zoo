
import java.awt.*;

public class DeliHam extends Food {

    public DeliHam(int x, int y) {
        super("Ham", x, y, true, false, 10);
    }

    public void tick(Zoo zoo) {
        age++;

        if(age > 1000) {
            isAlive = false;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.PINK);
        g.fillRect(Zoo.wrap(xPos,25)*20, Zoo.wrap(yPos,25)*20, 20, 20);
    }

    public void beEaten(Animal eater) {
        isAlive = false;

        if(age > 500) {
            eater.setSick(true);
            System.out.println("A cat ate an old ham and got sick");
        }

        
    }

}
