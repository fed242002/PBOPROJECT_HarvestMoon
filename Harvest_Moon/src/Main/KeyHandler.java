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

        // Add debug hitbox toggle when F3 is pressed
        if (code == KeyBind.hitbox) {
            gp.showDebugHitboxes = !gp.showDebugHitboxes;
        }

        if (gp.gameState == gp.titleState) {
            // main menu
            if (gp.ui.titleScreenState == 0) {
                if (code == KeyBind.upKey) {
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

            // sprint
            if (code == KeyBind.sprintKey) {
                gp.player.speed = gp.player.maxSpeed;
                gp.player.spriteDraw = 4;
            }

            // pause game in play state
            if (code == KeyBind.pauseKey) {
                gp.ui.pauseScreenState = 0; // Reset pause screen state
                gp.gameState = gp.pauseState;
            }
        }

        // pause state
        else if (gp.gameState == gp.pauseState) {
            if (gp.ui.pauseScreenState == 0) {
                if (code == KeyBind.upKey) {
                    gp.playSFX(gp.sfx, 1);
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 3;
                    }
                }
                if (code == KeyBind.downKey) {
                    gp.playSFX(gp.sfx, 1);
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 3) {
                        gp.ui.commandNum = 0;
                    }
                }
                if (code == KeyBind.nextKey) {

                    gp.playSFX(gp.sfx, 2);
                    if (gp.ui.commandNum == 0) {
                        gp.gameState = gp.playState;
                    }
                    if (gp.ui.commandNum == 1) {
                        // Save game logic here
                    }
                    if (gp.ui.commandNum == 2) {
                        gp.ui.pauseScreenState = 2;
                    }
                    if (gp.ui.commandNum == 3) {
                        gp.gameState = gp.titleState; // Return to title screen
                    }
                }
            }
            if (gp.ui.pauseScreenState == 2) {
                if (code == KeyBind.upKey) {
                    gp.playSFX(gp.sfx, 1);
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 3;
                    }

                }
                if (code == KeyBind.downKey) {
                    gp.playSFX(gp.sfx, 1);
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 3) {
                        gp.ui.commandNum = 0;
                    }

                }
                if (code == KeyBind.leftKey) {
                    gp.playSFX(gp.sfx, 1);
                    if (gp.ui.subState == 0) {
                        if (gp.ui.commandNum == 1 && gp.masterMusic.volumeScale > 0) {
                            gp.masterMusic.volumeScale--;
                            gp.masterMusic.checkVolume();
                        }
                        if (gp.ui.commandNum == 2 && gp.sfx.volumeScale > 0) {
                            gp.sfx.volumeScale--;
                            gp.sfx.checkVolume();
                        }
                    }
                }
                if (code == KeyBind.rightKey) {
                    gp.playSFX(gp.sfx, 1);
                    if (gp.ui.subState == 0) {
                        if (gp.ui.commandNum == 1 && gp.masterMusic.volumeScale < 5) {
                            gp.masterMusic.volumeScale++;
                            gp.masterMusic.checkVolume();
                        }
                        if (gp.ui.commandNum == 2 && gp.sfx.volumeScale < 5) {
                            gp.sfx.volumeScale++;
                            gp.sfx.checkVolume();
                        }
                    }
                }
            }

        }

        if (gp.gameState == gp.dialogueState) {
            if (code == KeyBind.nextKey) {
                gp.gameState = gp.playState;
            }
        }

        if (gp.gameState == gp.eventFoundState) {
            if (code == KeyBind.nextKey) {
                gp.gameState = gp.playState;
            }
        }
        if (code == KeyBind.grid) {
            gp.showGrid = !gp.showGrid;
            System.out.println("Grid display: " + (gp.showGrid ? "ON" : "OFF"));
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
