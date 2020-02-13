import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Date;
import java.util.Random;
import java.util.Vector;

import javax.swing.JFrame;

public class Game {
    private JFrame frame;
    private GameView gameView;
    public Camera camera;
    public GroundTile[][] map;
    private Vector<Entity> entities;
    private int zoom = 10;
    private int mapSize = 100;
    private Random rand;
    private double xSpeed = 0.05;
    private double ySpeed = 0.05;
    private Player player;
    private long startTime;
    private long endTime;

    public Game(int seed) {
        if (seed == 1) {
            this.rand = new Random();
        } else {
            this.rand = new Random(seed);
        }
        this.entities = new Vector<Entity>(1);
    }

    public void checkCollisions() {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = (Entity) entities.get(i);
            boolean collide;
            if (Math.pow(player.x - entity.x, 2) + Math.pow(entity.y - player.y, 2) <= Math
                    .pow(entity.size / 2 + player.size / 2, 2)) {
                collide = true;
            } else {
                collide = false;
            }
            if (collide) {
                entity.collide = collide;
            } else {
                entity.collide = false;
            }
        }
    }

    private GroundTile[][] generateMap(int size) {
        GroundTile[][] genMap = new GroundTile[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {

                int tileX = x - (size / 2);
                int tileY = y - (size / 2);
                // tileY = tileY;
                // System.out.println(tileY);
                genMap[x][y] = new GroundTile(tileX, tileY, rand.nextInt(2));
                // System.out.println(genMap[x][y].getY());

                // System.out.println(genMap[x][y].y);
                // genMap[x][y].y = 0 - genMap[x][y].y;
                // System.out.println(genMap[x][y].y);
                // System.out.println((0 - (y - (size / 2))));
                // System.out.println((y - (size / 2)));

                // System.out.println(x - (size / 2));
            }
        }

        return genMap;
    }

    public void start() throws InterruptedException {
        Toolkit.getDefaultToolkit().sync();
        frame = new JFrame();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize((int)screenSize.getWidth(),(int)screenSize.getHeight());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.requestFocus();
        int width = (int)frame.getContentPane().getSize().getWidth();
        int height = (int)frame.getContentPane().getSize().getHeight();
        
        player = new Player(0,0,xSpeed,ySpeed, 1);
        entities.add(new Entity("zombie",2,2,rand,300));
        entities.add(new Entity("zombie",-5,-3,rand,300));
        camera = new Camera(player.x, player.y);
        map = generateMap(mapSize);
        gameView = new GameView(map, camera,player,entities,zoom,width,height);
        frame.add(gameView);
        frame.addKeyListener(new KeyChecker(player));

        while(true) {
            startTime = new Date().getTime();
            player.move();
            checkCollisions();
            camera.move(player);
            gameView.repaint();
            endTime = new Date().getTime();
            long totalTime = startTime - endTime;
            if(16 - totalTime < 0) {
                totalTime = 16;
            }
            Thread.sleep(16 - totalTime); 
            //System.out.println(map[0][0].y);
        }
    }
}