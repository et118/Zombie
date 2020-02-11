import java.util.Date;

public class ViewLoop implements Runnable {
    private GameView view;
    private long startTime;
    private long endTime;
    public ViewLoop(GameView view) {
        this.view = view;
    }

    public void run() {
        while (true) {
            try {
                startTime = new Date().getTime();
                view.repaint();
                endTime = new Date().getTime();
                long totalTime = startTime - endTime;
                Thread.sleep(17 - totalTime); 
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}