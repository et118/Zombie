import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.util.Date;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.net.URL;
import java.awt.Image;
import java.awt.Color;
import java.awt.BasicStroke;

public class GameView extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
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
    private Player player;
    private Vector<Entity> entities;
    private Toolkit tk;
    public GameView(GroundTile[][] map, Camera camera, Player player,Vector<Entity> entities, int zoom,int width, int height) {
        Toolkit.getDefaultToolkit().sync();
        this.fps = 0;
        this.map = map;
        this.camera = camera;
        this.player = player;
        this.entities = entities;
        this.scale = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() / zoom;
        this.width = width;
        this.height = height;
        this.tk = Toolkit.getDefaultToolkit();
        URL url = this.getClass().getResource("Images/grass.png");
        this.grassImage = tk.getImage(url);
        //this.grassImage = Toolkit.getDefaultToolkit().createImage("Images/grass.png");
        //this.sandImage = Toolkit.getDefaultToolkit().createImage("Images/sand2.png");
        url = this.getClass().getResource("Images/sand2.png");
        this.sandImage = tk.getImage(url);
        //this.playerImage = Toolkit.getDefaultToolkit().createImage("Images/player.gif");
        //this.zombieImage = Toolkit.getDefaultToolkit().createImage("Images/zombie.png");
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
                int tileX = tile.x;
                int tileY = -tile.y;
                
                Vx = tileX * scale + x_disp;
                Vy = tileY * scale + y_disp;
                
                if ((Vx < width + scale && Vx > -scale) && (Vy < height + scale && Vy > -scale)) {
                    
                   
                    if(tile.type == 0) {
                        g2d.drawImage(grassImage, (int)Vx, (int)Vy, scale, scale, this);
                    } else {
                        g2d.drawImage(sandImage, (int)Vx, (int)Vy, scale, scale, this);
                        
                    }
                    g2d.drawString("X:" + Integer.toString(tile.x) + "Y:" + Integer.toString(tile.y), (int)(Vx) + (scale / 2) - 20, (int)Vy + scale / 2);
                    //if(tile.getY() == 3 && tile.getX() == 2) {
                    //    g2d.drawString("str", (int)(Vx) + scale / 2, (int)Vy + scale / 2);
                        //System.out.println(map[x_count][y_count].getY());
                        //System.out.println(tileY);
                    //}
                        
                    
                    
                    
                }

            }
        } // (player_image.getWidth(this) / 2)
    }

    private void drawEntities(Graphics2D g2d) {
        for(int x = 0; x < entities.size(); x++) {
            Entity entity = (Entity) entities.get(x);
            double Vx;
            double Vy;
            double x_disp;
            double y_disp;
            double playerX = entity.x;
            double playerY = -entity.y;
            double scaleModifier = entity.size;
            int playerscale = (int)((double)scale * scaleModifier);
            x_disp = 0 - (camera.x * (scale) - (width / 2) + playerscale / 2);
            y_disp = camera.y * (scale) + (height / 2) - playerscale / 2;
            Vx = playerX * scale + x_disp;
            Vy = playerY * scale + y_disp;
            if(!entity.collide) {
                g2d.setColor(Color.red);
            } else {
                g2d.setColor(Color.blue);
            }
            
            g2d.fillOval((int)Vx, (int)Vy, playerscale, playerscale);
            //g2d.drawLine((int)Vx, (int)Vy, (int)Vx + playerscale, (int)Vy + playerscale);
        }
    }
    
    private void drawPlayer(Graphics2D g2d) {
        double Vx;
        double Vy;
        double x_disp;
        double y_disp;
        double playerX = player.x;
        double playerY = -player.y;
        double scaleModifier = player.size;
        int playerscale = (int)((double)scale * scaleModifier);
        x_disp = 0 - (camera.x * (scale) - (width / 2) + playerscale / 2);// + 1 * playerscale);
        y_disp = camera.y * (scale) + (height / 2) - playerscale / 2;
        Vx = playerX * scale + x_disp;
        Vy = playerY * scale + y_disp;
        g2d.setColor(Color.green);
        g2d.fillOval((int)Vx, (int)Vy, playerscale, playerscale);
        //g2d.drawImage(playerImage, (int)Vx, (int)Vy, playerscale, playerscale, this);
        //g2d.drawLine((int)Vx, (int)Vy, (int)Vx + playerscale, (int)Vy + playerscale);
    }

    @Override
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
        g2d.drawString("X: " + Double.toString(Math.round(player.x * 100.0) / 100.0) + " Y: " + Double.toString(Math.round(player.y * 100.0) / 100.0),50,10);
        g2d.drawLine((width / 2) - 10, height / 2, (width / 2) + 10, height / 2);
        g2d.drawLine((width / 2), (height / 2) - 10, (width / 2), (height / 2) + 10);
        

        fpsCounter++;
    }
}