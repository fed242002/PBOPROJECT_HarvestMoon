package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    public boolean upPressed, downPressed, leftPressed, rightPressed, interactPressed;
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

        if(gp.gameState == gp.titleState) {
            if(code == KeyBind.upKey) {
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2; 
                }
            }
            if(code == KeyBind.downKey) {
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
            }
            if(code == KeyBind.nextKey){
               if(gp.ui.commandNum == 0) {
                    gp.ui.titleScreenState = 1;
                }
                if(gp.ui.commandNum == 1) {
                    // Load game logic here
                }
                if(gp.ui.commandNum == 2) {
                    System.exit(0);
                }
            }
        }

        if(gp.gameState == gp.playState){
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
            
            if(code == KeyBind.interactKey){
                interactPressed = true;
            }
            
            // Add debug hitbox toggle when F3 is pressed
            if(code == KeyBind.hitbox) {
                gp.showDebugHitboxes = !gp.showDebugHitboxes;
            }
        }
        // pause state
        if(gp.gameState == gp.pauseState){
            if(code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playState;
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
        
        if(gp.gameState == gp.dialogueState){
            if(code == KeyBind.nextKey){
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
