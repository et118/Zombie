public class Camera {
    public double x;
    public double y;

    public Camera(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void move(Player player) {
        double multi = 1;
        if(player.run) {
            multi = 3;
        } else {
            multi = 1;
        }
        if(player.w_pressed && !player.s_pressed ) {
            y += player.ySpeed * multi;
        }
        if(player.s_pressed && !player.w_pressed){
            y -= player.ySpeed * multi;
        }
        if(player.a_pressed && !player.d_pressed ){
            x += player.xSpeed * multi;
        }
        if(player.d_pressed && !player.a_pressed) {
            x -= player.xSpeed * multi;
        }
        


    }

}