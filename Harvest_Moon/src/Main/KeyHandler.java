package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    int upKey = KeyEvent.VK_W;
    int downKey = KeyEvent.VK_S;
    int leftKey = KeyEvent.VK_A;
    int rightKey = KeyEvent.VK_D;
    GamePanel gp;

    // Update constructor to accept GamePanel
    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == upKey) {
            upPressed = true;
        }
        if(code == downKey) {
            downPressed = true;
        }
        if(code == leftKey) {
            leftPressed = true;
        }
        if(code == rightKey) {
            rightPressed = true;
        }

        // Add debug hitbox toggle when F3 is pressed
        if(code == KeyEvent.VK_F3) {
            gp.showDebugHitboxes = !gp.showDebugHitboxes;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == upKey) {
            upPressed = false;
        }
        if(code == downKey) {
            downPressed = false;
        }
        if(code == leftKey) {
            leftPressed = false;
        }
        if(code == rightKey) {
            rightPressed = false;
        }

    }
    
}
