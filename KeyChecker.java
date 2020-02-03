import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


class KeyChecker extends KeyAdapter {
    public Player player;

    public KeyChecker(Player player) {
        this.player = player;
    }

    @Override
    public void keyPressed(KeyEvent k) {
        char ch = k.getKeyChar();
        ch = Character.toLowerCase(ch);
        //System.out.println(k.getKeyText(k.getKeyCode()));
        if (KeyEvent.getKeyText(k.getKeyCode()) == "Shift") {
            player.run = true;
        }
        if (ch == 'w') {
            //player.ym = -1;
            player.w_pressed = true;
        } else if (ch == 's') {
            //player.ym = 1;
        
            player.s_pressed = true;
              
        } else if (ch == 'd') {
            //player.xm = 1;
            player.d_pressed = true;
        } else if (ch == 'a') {
            //player.xm = -1;
            player.a_pressed = true;
        }

    }

    public void keyReleased(KeyEvent k) {
        char ch = k.getKeyChar();
        ch = Character.toLowerCase(ch);

        if (KeyEvent.getKeyText(k.getKeyCode()) == "Shift") {
            player.run = false;
        }
        if (ch == 'w') {
            player.w_pressed = false;
        } else if (ch == 's') {
            player.s_pressed = false;
            
            
        } else if (ch == 'a') {
            player.a_pressed = false;

        } else if (ch == 'd') {
            player.d_pressed = false;
        }
    }

    
}