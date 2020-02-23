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

    private void entityCollide() {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = (Entity) entities.get(i);
            
            if (Math.pow(player.x - entity.x, 2) + Math.pow(entity.y - player.y, 2) <= Math
                    .pow(entity.size / 2 + player.size / 2, 2)) {
                entity.collide = true;

                if(entity.y > player.y) {
                    player.canMoveUp = false;
                } else {
                    player.canMoveDown = false;
                }
                if(entity.x > player.x) {
                    player.canMoveRight = false;
                } else {
                    player.canMoveRight = false;
                }

            } else {
                entity.collide = false;
            }

        }
    }

    private void tileCollide() {
        player.canMoveDown = true;
        player.canMoveUp = true;
        player.canMoveLeft = true;
        player.canMoveRight = true;
        for (int x = 0; x < mapSize; x++) {
            for (int y = 0; y < mapSize; y++) {
                GroundTile tile = map[x][y];
                if(tile.collision) {
                    double cx = player.x;
                    double cy = player.y;
                    double r = player.size;

                    double rx = tile.x - 0.5;
                    double ry = tile.y + 0.5;
                    double rw = 1;
                    double rh = 1;
                    double testX = cx;
                    double testY = cy;

                    String directionX = "";
                    String directionY = "";
                    if(cx < rx) {
                        testX = rx;
                        directionX = "left";
                    }
                    else if(cx > rx + rw) {
                        testX = rx+rw;
                        directionX = "right";
                    }
                    if(cy > ry) {
                        testY = ry;
                        directionY = "up";
                    }
                    else if(cy < ry-rh) {
                        testY = ry-rh;
                        directionY = "down";
                    }

                    double distX = cx-testX;
                    double distY = cy-testY;
                    double distance = Math.sqrt((distX*distX) + (distY*distY));

                    if(distance <= r - 0.5) {
                        tile.collide = true;
                        if(directionX == (String)"left") {
                            player.canMoveRight = false;
                            
                        }
                        else if(directionX == (String)"right") {
                            player.canMoveLeft = false;
                        }
                        
                        if(directionY == (String)"up") {

                            player.canMoveDown = false;
                            
                        }
                        else if(directionY == (String)"down") {
                            player.canMoveUp = false;
                        }
                        
                        
                    } else {
                        
                        tile.collide = false;

                    }
                }
            }
        }
    }
    private void checkCollisions() {
        tileCollide();
        entityCollide();


    }

    private GroundTile[][] generateMap(int size) {
        GroundTile[][] genMap = new GroundTile[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {

                int tileX = x - (size / 2);
                int tileY = y - (size / 2);

                int type = rand.nextInt(10);
                if(type < 4) {
                    type = 0;
                } else if(type > 4 && type < 10) {
                    type = 1;
                } else {
                    type = 2;
                }
                genMap[x][y] = new GroundTile(tileX, tileY, type);
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
            checkCollisions();
            player.move();
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