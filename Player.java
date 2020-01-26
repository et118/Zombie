public class Player {
    
    public boolean w_pressed = false;
    public boolean s_pressed = false;
    public boolean d_pressed = false;
    public boolean a_pressed = false;
    public double xSpeed;
    public double ySpeed;
    public boolean run = false;

    public Player(double xSpeed, double ySpeed) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

}