public class Entity {
    public String type;
    private double x;
    private double y;

    public Entity(String type, double x, double y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
}