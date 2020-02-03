public class Camera {
    public double x;
    public double y;

    public Camera(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void move(Player player) {
        this.x = player.x;
        this.y = player.y;

    }

}