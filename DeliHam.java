
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
        g.setColor(Color.RED);
        g.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 25));
        g.drawString("🍖", Zoo.wrap(xPos,Zoo.ZOO_COLS)*Zoo.SCALE, Zoo.wrap(yPos,Zoo.ZOO_ROWS)*Zoo.SCALE+25);
        //g.fillOval(Zoo.wrap(xPos,Zoo.ZOO_COLS)*Zoo.SCALE, Zoo.wrap(yPos,Zoo.ZOO_ROWS)*Zoo.SCALE, Zoo.SCALE, Zoo.SCALE);
    }

    public void beEaten(Animal eater) {
        isAlive = false;

        if(age > 700) {
            eater.setSick(true);
            System.out.println("A cat ate an old ham and got sick");
        }

        
    }

}
