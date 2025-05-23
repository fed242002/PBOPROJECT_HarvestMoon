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

        if(code == KeyEvent.VK_ENTER){
            gp.gameState = gp.playState;
        }

        // Add debug hitbox toggle when F3 is pressed
        if(code == KeyBind.hitbox) {
            gp.showDebugHitboxes = !gp.showDebugHitboxes;
        }
        if(code == KeyBind.pauseKey) {
            if(gp.gameState == gp.playState){ //kalo play ke pause
                gp.gameState = gp.pauseState;
            }
            else if(gp.gameState == gp.pauseState){ //kalo pause ke play
                gp.gameState = gp.playState;
            }
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
