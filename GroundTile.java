public class GroundTile {
    public int x;
    public int y;
    public int type;
    public boolean collision;
    public boolean collide;
    public GroundTile(int x,int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.collide = false;
        if(type == 2) {
            this.collision = true;
        } else {
            this.collision = false;
        }
    }
}