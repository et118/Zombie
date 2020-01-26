import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.JFrame;

public class Game {
    private int seed;
    private JFrame frame;
    private GameView gameView;
    public Camera camera;
    public GroundTile[][] map;
    private int zoom = 10;
    private int mapSize = 100;
    private Random rand;
    private double xSpeed = 0.05;
    private double ySpeed = 0.05;
    private Player player;
    public Game(int seed) {
        this.seed = seed;
        this.rand = new Random(seed);
    }
    
    private GroundTile[][] generateMap(int size) {
        GroundTile[][] genMap = new GroundTile[size][size];
        for(int x = 0; x < size; x++) {
            for(int y = 0; y < size; y++) {
                
                int tileX = x - (size / 2);
                int tileY = y;
                tileY -= (size / 2);
                //System.out.println(tileY);
                genMap[x][y] = new GroundTile(tileX, tileY, rand.nextInt(2));
                //System.out.println(genMap[x][y].getY());
                
                //System.out.println(genMap[x][y].y);
                //genMap[x][y].y = 0 - genMap[x][y].y;
                //System.out.println(genMap[x][y].y);
                //System.out.println((0 - (y - (size / 2))));
                //System.out.println((y - (size / 2)));

                
                //System.out.println(x - (size / 2));
            }
        }

        return genMap;
    }
    
    public void start() throws InterruptedException {
        frame = new JFrame();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize((int)screenSize.getWidth(),(int)screenSize.getHeight());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.requestFocus();
        int width = (int)frame.getContentPane().getSize().getWidth();
        int height = (int)frame.getContentPane().getSize().getHeight();
        camera = new Camera(0, 0);
        player = new Player(xSpeed,ySpeed);
        map = generateMap(mapSize);
        gameView = new GameView(map, camera,zoom,width,height);
        frame.add(gameView);
        frame.addKeyListener(new KeyChecker(player));

        

        ViewLoop viewLoop = new ViewLoop(gameView);
        Thread viewThread = new Thread(viewLoop);
        viewThread.start();

        while(true) {
            camera.move(player);
            Thread.sleep(16);
            //System.out.println(map[0][0].y);
        }
    }
}