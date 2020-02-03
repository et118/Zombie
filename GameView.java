import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.util.Date;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Color;
import java.awt.BasicStroke;

public class GameView extends JPanel {
    public int fps;
    private int fpsCounter;
    private long fpsStart;
    private long fpsEnd;
    private GroundTile[][] map;
    private Camera camera;
    private int scale;
    private int width;
    private int height;
    private Image grassImage;
    private Image sandImage;
    public GameView(GroundTile[][] map, Camera camera, int zoom,int width, int height) {
        this.fps = 0;
        this.map = map;
        this.camera = camera;
        this.scale = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() / zoom;
        this.width = width;
        this.height = height;
        this.grassImage = Toolkit.getDefaultToolkit().createImage("Images/grass.png");
        this.sandImage = Toolkit.getDefaultToolkit().createImage("Images/sand2.png");
        //System.out.println(scale);
    }

    private void drawGround(Graphics2D g2d) {
        double Vx;
        double Vy;
        double x_disp;
        double y_disp;
        GroundTile tile;
        // System.out.println(scale);
        x_disp = 0 - (camera.x * (scale) - (width / 2) - scale / 2 + 1 * scale);
        y_disp = camera.y * (scale) + (height / 2) - scale / 2;
        for (int x_count = 0; x_count < map.length; x_count++) {
            
            for (int y_count = 0; y_count < map[x_count].length; y_count++) {
                tile = map[x_count][y_count];
                int tileX = tile.getX();
                int tileY = -tile.getY();
                Vx = tileX * scale + x_disp;
                Vy = tileY * scale + y_disp;
                
                if ((Vx < width && Vx > -scale) && (Vy < height && Vy > -scale)) {
                    
                   
                    if(tile.type == 0) {
                        g2d.drawImage(grassImage, (int)Vx, (int)Vy, scale, scale, this);
                    } else {
                        g2d.drawImage(sandImage, (int)Vx, (int)Vy, scale, scale, this);
                        
                    }
                    g2d.drawString(Integer.toString(tile.getX()), (int)(Vx) + scale / 2, (int)Vy + scale / 2);
                    //if(tileX == 0 && tileY == 0) {
                    //g2d.drawString("str", (int)(Vx) + scale / 2, (int)Vy + scale / 2);
                    //}
                        
                    
                    
                    
                }

            }
        } // (player_image.getWidth(this) / 2)
    }

    private void drawEntities(Graphics2D g2d) {

    }
    
    private void drawPlayer(Graphics2D g2d) {
        
    }
    public void paint(Graphics g) {
        fpsStart = new Date().getTime();
        if(fpsStart >= fpsEnd) {
            fps = fpsCounter;
            fpsEnd = new Date().getTime() + 1000;
            fpsCounter = 0;
        }

        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawGround(g2d);
        drawPlayer(g2d);
        drawEntities(g2d);

        g2d.setColor(Color.black);
        g2d.setStroke(new BasicStroke(3.0f));
        g2d.drawString("Fps: " + fps, 10, 10);
        g2d.drawString("X: " + Double.toString(Math.round(camera.x * 100.0) / 100.0) + " Y: " + Double.toString(Math.round(camera.y * 100.0) / 100.0),50,10);
        g2d.drawLine((width / 2) - 10, height / 2, (width / 2) + 10, height / 2);
        g2d.drawLine((width / 2), (height / 2) - 10, (width / 2), (height / 2) + 10);
        

        fpsCounter++;
    }
}