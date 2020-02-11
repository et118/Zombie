import java.util.Random;

public class Entity {
    public String type;
    public double x;
    public double y;
    public double size;
    public boolean collide;
    public Entity(String type, double x, double y,Random rand, int maxSize) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.collide = false;
        this.size = rand.nextInt(maxSize);
        size = (size + 50.0d) / 100.0d;
    }
}