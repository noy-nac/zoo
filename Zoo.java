import javax.swing.*;
import java.awt.*;

public class Zoo extends JPanel {

    private int width, height;
    private LinkedList<ZooEntity> grid[][];

    public Zoo(int w, int h) {
        grid = new LinkedList<ZooEntity>[h][w];
        width = w;
        height = h;
    }

	public void paintComponent(Graphics g){
		super.paintComponent(g); 
		setBackground(Color.GREEN);

        for(int h = 0; h < height; h++) {
            for(int w = 0; w < width; w++) {
                for(ze : grid[h][w]) {
                    grid[h][w].draw(g);
                }
            }
        }
	}

    public ZooEntity tick() {
        for(int h = 0; h < height; h++) {
            for(int w = 0; w < width; w++) {
                if(grid[h][w] != null) {
                    grid[h][w].tick(this);
                }
            }
        }
    }

    public ZooEntity at(int x, int y) {
        return grid[y % height][x % width];
    }

    public List<ZooEntity> getTouching(ZooEntity ze) {
        List<ZooEntity> touching = new ArrayList<>(grid[ze.getY() % height][ze.getX() % width]);
    }

    public static void main(String[] args) {
        Zoo zoo = new Zoo(25, 25);

        JFrame frame = new JFrame("Train");
		frame.setSize(500,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(zoo);
		frame.setVisible(true);

        while(true) {
            Thread.sleep(250);
            zoo.tick();
            // repaint code here
        }
    }

}
