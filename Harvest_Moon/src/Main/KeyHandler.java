package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

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

<<<<<<< HEAD
        if (gp.gameState == gp.titleState) {
            // main menu
            if (gp.ui.titleScreenState == 0) {
                if (code == KeyBind.upKey) {
=======
        // Add debug hitbox toggle when F3 is pressed
            if(code == KeyBind.hitbox) {
                gp.showDebugHitboxes = !gp.showDebugHitboxes;
            }

        
        if(gp.gameState == gp.titleState) {
            //main menu
            if(gp.ui.titleScreenState == 0) {
                if(code == KeyBind.upKey) {
>>>>>>> a0fc6a1680931db3efdc9d1ce767095d6cb95fa0
                    gp.playSFX(gp.sfx, 1);
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 2;
                    }
                }
                if (code == KeyBind.downKey) {
                    gp.playSFX(gp.sfx, 1);
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 2) {
                        gp.ui.commandNum = 0;
                    }
                }
                if (code == KeyBind.nextKey) {
                    gp.playSFX(gp.sfx, 2);
                    if (gp.ui.commandNum == 0) {
                        gp.ui.titleScreenState = 1;
                    }
                    if (gp.ui.commandNum == 1) {
                        // Load game logic here
                    }
                    if (gp.ui.commandNum == 2) {
                        System.exit(0);
                    }
                }
            }

            // new game

        }

        if (gp.gameState == gp.playState) {
            if (code == KeyBind.upKey) {
                upPressed = true;
            }
            if (code == KeyBind.downKey) {
                downPressed = true;
            }
            if (code == KeyBind.leftKey) {
                leftPressed = true;
            }
            if (code == KeyBind.rightKey) {
                rightPressed = true;
            }

            if (code == KeyBind.interactKey) {
                interactPressed = true;
            }
<<<<<<< HEAD

            // Add debug hitbox toggle when F3 is pressed
            if (code == KeyBind.hitbox) {
                gp.showDebugHitboxes = !gp.showDebugHitboxes;
            }

            // sprint
            if (code == KeyBind.sprintKey) {
=======
            
            
            
            //sprint
            if(code == KeyBind.sprintKey) {
>>>>>>> a0fc6a1680931db3efdc9d1ce767095d6cb95fa0
                gp.player.speed = gp.player.maxSpeed;
                gp.player.spriteDraw = 4;
            }

<<<<<<< HEAD
            // pause game
            if (code == KeyBind.pauseKey) {
                gp.playSFX(gp.sfx, 3);
                if (gp.gameState == gp.playState) { // kalo play ke pause
                    gp.gameState = gp.pauseState;
                }
=======


            //pause game in play state
            if(code == KeyBind.pauseKey) {
                gp.gameState = gp.pauseState;
>>>>>>> a0fc6a1680931db3efdc9d1ce767095d6cb95fa0
            }
        }

        // pause state
<<<<<<< HEAD
        if (gp.gameState == gp.pauseState) {
            if (code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;
            }
            if (code == KeyBind.pauseKey) {
                gp.playSFX(gp.sfx, 3);
                if (gp.gameState == gp.pauseState) { // kalo pause ke play
                    gp.gameState = gp.playState;
                }
            }
=======
        else if(gp.gameState == gp.pauseState){
            if(gp.ui.pauseScreenState == 0) {
                if(code == KeyBind.upKey) {
                    gp.playSFX(gp.sfx, 1);
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 3; 
                    }
                }
                if(code == KeyBind.downKey) {
                    gp.playSFX(gp.sfx, 1);
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 3) {
                        gp.ui.commandNum = 0;
                    }
                }
                if(code == KeyBind.nextKey){
                    
                    gp.playSFX(gp.sfx, 2);
                    if(gp.ui.commandNum == 0) {
                        gp.gameState = gp.playState;
                    }
                    if(gp.ui.commandNum == 1) {
                        // Save game logic here
                    }
                    if(gp.ui.commandNum == 2) {
                        // setting game logic here
                    }
                    if(gp.ui.commandNum == 3) {
                        gp.gameState = gp.titleState; // Return to title screen
                    }
                }
            }

            System.out.println(gp.ui.commandNum);

>>>>>>> a0fc6a1680931db3efdc9d1ce767095d6cb95fa0
        }

        if (gp.gameState == gp.dialogueState) {
            if (code == KeyBind.nextKey) {
                gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyBind.upKey) {
            upPressed = false;
        }
        if (code == KeyBind.downKey) {
            downPressed = false;
        }
        if (code == KeyBind.leftKey) {
            leftPressed = false;
        }
        if (code == KeyBind.rightKey) {
            rightPressed = false;
        }
        if (code == KeyBind.sprintKey) {
            gp.player.speed = gp.player.normalSpeed; // Reset speed to normal when sprint key is released
            gp.player.spriteDraw = 10;
        }

    }

}
