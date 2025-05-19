package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    public boolean upPressed, downPressed, leftPressed, rightPressed;
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

        if(code == KeyBind.upKey) {
            upPressed = true;
        }
        if(code == KeyBind.downKey) {
            downPressed = true;
        }
        if(code == KeyBind.leftKey) {
            leftPressed = true;
        }
        if(code == KeyBind.rightKey) {
            rightPressed = true;
        }

        // Add debug hitbox toggle when F3 is pressed
        if(code == KeyBind.hitbox) {
            gp.showDebugHitboxes = !gp.showDebugHitboxes;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyBind.upKey) {
            upPressed = false;
        }
        if(code == KeyBind.downKey) {
            downPressed = false;
        }
        if(code == KeyBind.leftKey) {
            leftPressed = false;
        }
        if(code == KeyBind.rightKey) {
            rightPressed = false;
        }

    }
    
}
