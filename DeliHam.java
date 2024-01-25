
import java.awt.*;

public class DeliHam extends Food {

    public DeliHam(int x, int y) {
        super("Ham", x, y, true, false, 10);
    }

    public void tick(Zoo zoo) {
        age++;
    }

    public void draw(Graphics g) {
        g.setColor(Color.PINK);
        g.fillRect(Zoo.wrap(xPos,25)*20, Zoo.wrap(yPos,25)*20, 20, 20);
    }

    public void beEaten() {
        isAlive = false;
    }

}
