package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import animation.Animation;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, interactPressed;
    GamePanel gp;

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
                        gp.saveLoad1.load();
                        System.out.println("Load Complete");
                        gp.gameState = gp.playState;
                    }
                    if (gp.ui.commandNum == 2) {
                        System.exit(0);
                    }
                }
            } else if (gp.ui.titleScreenState == 1) {
                if (code == KeyBind.upKey) {
                    gp.playSFX(gp.sfx, 1);
                    gp.ui.customizeNum--;
                    if (gp.ui.customizeNum < 0) {
                        gp.ui.customizeNum = 4;
                    }
                }
                if (code == KeyBind.downKey) {
                    gp.playSFX(gp.sfx, 1);
                    gp.ui.customizeNum++;
                    if (gp.ui.customizeNum > 4) {
                        gp.ui.customizeNum = 0;
                    }
                }
                if (code == KeyBind.nextKey) {

                    if (gp.ui.customizeNum == 4) {
                        gp.playSFX(gp.sfx, 2);
                        gp.ui.titleScreenState = 0;
                        gp.gameState = gp.playState;
                        gp.player.setAnimation("idle");
                    }
                }

                if (code == KeyBind.rightKey) {
                    gp.playSFX(gp.sfx, 1);

                    if (gp.ui.customizeNum == 0) {
                        if (gp.player.bodyIndex < gp.player.listBody.length - 1) {
                            gp.player.bodyIndex++;
                            gp.player.changePath("body", gp.player.listBody[gp.player.bodyIndex]);
                        } else {
                            gp.player.bodyIndex = 0;
                            gp.player.changePath("body", gp.player.listBody[gp.player.bodyIndex]);
                        }
                    } else if (gp.ui.customizeNum == 1) {
                        if (gp.player.eyeIndex < gp.player.listEye.length - 1) {
                            gp.player.eyeIndex++;
                            gp.player.changePath("eye", gp.player.listEye[gp.player.eyeIndex]);
                        } else {
                            gp.player.eyeIndex = 0;
                            gp.player.changePath("eye", gp.player.listEye[gp.player.eyeIndex]);
                        }
                    } else if (gp.ui.customizeNum == 2) {
                        if (gp.player.hairIndex < gp.player.listHair.length - 1) {
                            gp.player.hairIndex++;
                            gp.player.changePath("hair", gp.player.listHair[gp.player.hairIndex]);
                        } else {
                            gp.player.hairIndex = 0;
                            gp.player.changePath("hair", gp.player.listHair[gp.player.hairIndex]);
                        }
                    } else if (gp.ui.customizeNum == 3) {
                        if (gp.player.outfitIndex < gp.player.listOutfit.length - 1) {
                            gp.player.outfitIndex++;
                            gp.player.changePath("outfit", gp.player.listOutfit[gp.player.outfitIndex]);
                        } else {
                            gp.player.outfitIndex = 0;
                            gp.player.changePath("outfit", gp.player.listOutfit[gp.player.outfitIndex]);
                        }
                    }
                    updatePlayerPath();
                } else if (code == KeyBind.leftKey) {
                    gp.playSFX(gp.sfx, 1);

                    if (gp.ui.customizeNum == 0) {
                        if (gp.player.bodyIndex > 0) {
                            gp.player.bodyIndex--;
                            gp.player.changePath("body", gp.player.listBody[gp.player.bodyIndex]);
                        } else {
                            gp.player.bodyIndex = gp.player.listBody.length - 1;
                            gp.player.changePath("body", gp.player.listBody[gp.player.bodyIndex]);
                        }
                    } else if (gp.ui.customizeNum == 1) {
                        if (gp.player.eyeIndex > 0) {
                            gp.player.eyeIndex--;
                            gp.player.changePath("eye", gp.player.listEye[gp.player.eyeIndex]);
                        } else {
                            gp.player.eyeIndex = gp.player.listEye.length - 1;
                            gp.player.changePath("eye", gp.player.listEye[gp.player.eyeIndex]);
                        }
                    } else if (gp.ui.customizeNum == 2) {
                        if (gp.player.hairIndex > 0) {
                            gp.player.hairIndex--;
                            gp.player.changePath("hair", gp.player.listHair[gp.player.hairIndex]);
                        } else {
                            gp.player.hairIndex = gp.player.listHair.length - 1;
                            gp.player.changePath("hair", gp.player.listHair[gp.player.hairIndex]);
                        }
                    } else if (gp.ui.customizeNum == 3) {
                        if (gp.player.outfitIndex > 0) {
                            gp.player.outfitIndex--;
                            gp.player.changePath("outfit", gp.player.listOutfit[gp.player.outfitIndex]);
                        } else {
                            gp.player.outfitIndex = gp.player.listOutfit.length - 1;
                            gp.player.changePath("outfit", gp.player.listOutfit[gp.player.outfitIndex]);
                        }
                    }

                    updatePlayerPath();
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
                        gp.ui.pauseScreenState = 1;
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

    public void updatePlayerPath() {
        gp.player.redeclareAnimation();
    }

}
