public class ViewLoop implements Runnable {
    private GameView view;

    public ViewLoop(GameView view) {
        this.view = view;
    }

    public void run() {
        while (true) {
            view.repaint();
            try {
                Thread.sleep(15, 700000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}