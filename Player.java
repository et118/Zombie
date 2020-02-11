public class Player {
    
    public boolean w_pressed = false;
    public boolean s_pressed = false;
    public boolean d_pressed = false;
    public boolean a_pressed = false;
    public double xSpeed;
    public double ySpeed;
    public boolean run = false;
    public double x;
    public double y;
    public double size;
    public Player(double x, double y,double xSpeed, double ySpeed,double size) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void move() {
        double multi = 1;
        if(run) {
            multi = 3;
        } else {
            multi = 1;
        }
        if(w_pressed && !s_pressed ) {
            y += ySpeed * multi;
        }
        if(s_pressed && !w_pressed){
            y -= ySpeed * multi;
        }
        if(a_pressed && !d_pressed ){
            x -= xSpeed * multi;
        }
        if(d_pressed && !a_pressed) {
            x += xSpeed * multi;
        }
    }

}