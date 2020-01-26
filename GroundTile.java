public class GroundTile {
    private int x;
    private int y;
    public int type;
    public GroundTile(int x,int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }
    public int getY() {
        return this.y;
    }
    public int getX() {
        return this.x;
    }
}