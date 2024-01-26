import javax.swing.*;
import java.awt.*;

import java.util.*;

public class Zoo extends JPanel {

    public static final int ZOO_ROWS = 30;
    public static final int ZOO_COLS = 30;
    public static final int SCALE = 25;

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

    public static int wrap(int val, int limit) {
        if(val >= 0) return val % limit;
        else         return (limit - val) % limit;
    }

    public static void main(String[] args) {
        Zoo zoo = new Zoo(25, 25);

        

        JFrame frame = new JFrame("Zoo");
		frame.setSize(ZOO_COLS * SCALE, ZOO_ROWS * SCALE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(zoo);
		frame.setVisible(true);

        zoo.add(new Cat("Leo", 0, 0));
        zoo.add(new Cat("Sam", 1, 1));
        zoo.add(new Cat("Max", 20, 20));
        zoo.add(new Cat("Mark", 24, 24));

        zoo.add(new DeliHam(10, 5));

        int i = 0;
        while(true) {
            try {
            Thread.sleep(50);
            }
            catch(Exception e) {
                e.printStackTrace();
            }

            if(i % 50 == 0) {
                zoo.add(new DeliHam(rand.nextInt(ZOO_COLS), rand.nextInt(ZOO_ROWS)));
            }

            zoo.tick();
            // repaint code here
            zoo.revalidate();
            zoo.repaint();
            i++;
        }
    }

}
