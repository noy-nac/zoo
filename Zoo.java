import javax.swing.*;
import java.awt.*;

import java.util.*;

public class Zoo extends JPanel {

    private int width, height;
    private ArrayList<ArrayList<LinkedList<ZooEntity>>> grid;

    public Zoo(int w, int h) {
        grid = new ArrayList<>(h);
        for(int y = 0; y < h; y++) {
            ArrayList<LinkedList<ZooEntity>> row = new ArrayList<>(w);
            for(int x = 0; x < w; x++) {
                row.add(new LinkedList<ZooEntity>());
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
                for(ZooEntity ze : grid.get(y).get(x)) {
                    ze.draw(g);
                }
            }
        }
	}

    public void tick() {
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                for(int i = grid.get(y).get(x).size() - 1; i >= 0; i--) {

                    ZooEntity ze = grid.get(y).get(x).remove(i);

                    if(ze.isAlive()) {
                        ze.tick(this);
                        grid.get(wrap(ze.getY(), height)).get(wrap(ze.getX(), width)).add(ze);

                        for 
                    }
                }
            }
        }
    }

    public ArrayList<ZooEntity> at(int x, int y) {
        return new ArrayList<ZooEntity>(grid.get(wrap(y, height)).get(wrap(x, width)));
    }

    public void add(ZooEntity ze) {
        grid.get(wrap(ze.getY(), height)).get(wrap(ze.getX(), width)).add(ze);
    }

    public static int wrap(int val, int limit) {
        if(val >= 0) return val % limit;
        else         return (limit - val) % limit;
    }

    public static void main(String[] args) {
        Zoo zoo = new Zoo(25, 25);

        Random rand = new Random();

        JFrame frame = new JFrame("Zoo");
		frame.setSize(500,500);
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
            Thread.sleep(100);
            }
            catch(Exception e) {
                e.printStackTrace();
            }

            if(i % 100 == 0) {
                zoo.add(new DeliHam(rand.nextInt(25), rand.nextInt(25)));
            }

            zoo.tick();
            // repaint code here
            zoo.revalidate();
            zoo.repaint();
            i++;
        }
    }

}
