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
    public boolean canMoveLeft;
    public boolean canMoveRight;
    public boolean canMoveUp;
    public boolean canMoveDown;
    public int slideX;
    public int slideY;
    public Player(double x, double y,double xSpeed, double ySpeed,double size) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.x = x;
        this.y = y;
        this.size = size;
        canMoveLeft = true;
        canMoveRight = true;
        canMoveUp = true;
        canMoveDown = true;
    }

    public void move() {
        double multi = 1;
        if(run) {
            multi = 3;
        } else {
            multi = 1;
        }
        //System.out.println(canMoveDown);
        if(w_pressed && !s_pressed && canMoveUp) {
            y += ySpeed * multi;
        }
        if(s_pressed && !w_pressed && canMoveDown){
            y -= ySpeed * multi;
        }
        if(a_pressed && !d_pressed && canMoveLeft){
            x -= xSpeed * multi;
        }
        if(d_pressed && !a_pressed && canMoveRight) {
            x += xSpeed * multi;
        }
        if(slideX != 0) {
            x += slideX * xSpeed * multi;
        }
        if(slideY != 0) {
            y += slideY * ySpeed * multi;
        }
    }

}