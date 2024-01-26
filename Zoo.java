import javax.swing.*;
import java.awt.*;

import java.util.*;

public class Zoo extends JPanel {

    public static final int ZOO_ROWS = 30; // height
    public static final int ZOO_COLS = 40; // width
    public static final int SCALE = 30;

    public static Random rand = new Random();

    private int width, height;
    private ArrayList<ArrayList<LinkedList<Entity>>> grid;

    public Zoo(int w, int h) {
        grid = new ArrayList<>(h);
        for(int y = 0; y < h; y++) {
            ArrayList<LinkedList<Entity>> row = new ArrayList<>(w);
            for(int x = 0; x < w; x++) {
                row.add(new LinkedList<Entity>());
            }
            grid.add(row);
        }
        width = w;
        height = h;
    }

	public void paintComponent(Graphics g){
		super.paintComponent(g); 
		setBackground(Color.GREEN);

        g.setColor(new Color(0, 200, 0)); // dark green
        for(int y = 0; y < height; y++) {
            g.drawLine(0, y * SCALE, width * SCALE, y * SCALE);
        }
        for(int x = 0; x < width; x++) {
            g.drawLine(x * SCALE, 0, x * SCALE, height * SCALE);
        }

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                for(Entity ze : grid.get(y).get(x)) {
                    ze.draw(g);
                }
            }
        }
	}

    public void tick() {
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                for(int i = grid.get(y).get(x).size() - 1; i >= 0; i--) {

                    Entity ze = grid.get(y).get(x).remove(i);

                    if(ze.isAlive()) {
                        ze.tick(this);
                        grid.get(wrap(ze.getY(), height)).get(wrap(ze.getX(), width)).add(ze);
                    }
                }
            }
        }
    }

    public ArrayList<Entity> at(int x, int y) {
        return new ArrayList<Entity>(grid.get(wrap(y, height)).get(wrap(x, width)));
    }

    public void add(Entity ze) {
        grid.get(wrap(ze.getY(), height)).get(wrap(ze.getX(), width)).add(ze);
    }

    public static int wrap(int val, int thresh) {
        if(val >= 0) return val % thresh;
        else         return (thresh - val) % thresh;
    }

    public static void main(String[] args) {
        Zoo zoo = new Zoo(ZOO_COLS, ZOO_ROWS);

        JFrame frame = new JFrame("Zoo");
		frame.setSize(ZOO_COLS * SCALE + SCALE/2, ZOO_ROWS * SCALE + SCALE/2 + 23);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(zoo);
		frame.setVisible(true);

        // Add some animals, food here
        zoo.add(new Cat("Leo", 0, 0));
        zoo.add(new Cat("Sam", 1, 1));
        zoo.add(new Cat("Max", 20, 20));
        zoo.add(new Cat("Mark", 24, 24));

        zoo.add(new DeliHam(10, 5));

        int ticks = 0;
        while(true) {
            try {
            Thread.sleep(50);
            }
            catch(Exception e) {
                e.printStackTrace();
            }

            if(ticks % 50 == 0) {
                zoo.add(new DeliHam(rand.nextInt(ZOO_COLS), rand.nextInt(ZOO_ROWS)));
            }

            zoo.tick();

            zoo.revalidate();
            zoo.repaint();

            ticks++;
        }
    }

}
