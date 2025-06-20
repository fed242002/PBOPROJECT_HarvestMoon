package Main;

import entity.Entity;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, interactPressed, isSprint, undoToolsPressed,
            useTool, enterPressed, escPressed;
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



        // Handle general key states
        if(code == KeyEvent.VK_ESCAPE) {
            escPressed = true;
        }
        if(code == KeyBind.nextKey) {
            enterPressed = true;
        }

        // Check for W and S keys
        if(code == KeyBind.upKey) {
            upPressed = true;
        }
        if(code == KeyBind.downKey) {
            downPressed = true;
        }
        if (code == KeyBind.hitbox) {
            gp.showDebugHitboxes = !gp.showDebugHitboxes;
        }


        else if (gp.gameState == gp.titleState) {
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
                        gp.player.preview();
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
                        gp.player.redeclareAnimation();
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
                    gp.player.preview();
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

                    gp.player.preview();
                }

            }

            // new game

        }

        else if (gp.gameState == gp.playState) {
            
            if (code == KeyEvent.VK_O) {
                for(Entity x : gp.farmObj) {
                    x.reset();
                }
                    for(Entity crop : gp.cropObj) {
                    crop.dayPassed();
                }

            }
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
            if (code == KeyBind.undoToolsKey && gp.player.currentTools == "shovel") {
                undoToolsPressed = true;
            }

            // kalo mau use tools
            if (code == KeyBind.useToolKey) {
                useTool = !useTool;
            }

            // debug pertools tools an
            if (code == KeyBind.unequipedAll) {
                gp.player.currentTools = null;
                gp.player.currentItem = null;
            }
            if (code == KeyBind.equipShovel) {
                gp.player.currentTools = "shovel";
            }
            if (code == KeyBind.equipAxe) {
                gp.player.currentTools = "axe";
            }
            if (code == KeyBind.equipFishingRod) {
                gp.player.currentTools = "fishRod";
            }
            if(code == KeyBind.equipWateringCan) {
                gp.player.currentTools = "wateringCan";
            }

            // sprint
            if (code == KeyBind.sprintKey) {
                isSprint = true;
                gp.player.speed = gp.player.maxSpeed;
                gp.player.spriteDraw = 4;

            }

            // buat inventory
            if (code == KeyBind.inventoryKey) {
                if (gp.gameState == gp.playState) {
                    gp.gameState = gp.inventoryState;
                }
            }

            // pause game in play state
            if (code == KeyBind.pauseKey) {
                gp.ui.pauseScreenState = 0; // Reset pause screen state
                gp.gameState = gp.pauseState;
                gp.ui.commandNum = 0; // Reset command number
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
                        gp.ui.commandNum = 0; // Reset command number
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

        // kalo inventory
        else if (gp.gameState == gp.inventoryState) {

            playerInventory(code);

            if (code == KeyBind.nextKey) {
                gp.playSFX(gp.sfx, 2);

                gp.player.selectItem();
            }

            if (code == KeyBind.pauseKey) { // pencet escape
                gp.gameState = gp.playState; // Exit inventory and return to play state
            }
        }
        else if(gp.gameState == gp.foodItemChooseState){
            if(code == KeyBind.upKey) {
                gp.playSFX(gp.sfx, 1);
                
                if(gp.ui.commandNum == 0)
                gp.ui.commandNum = 1;
                else
                gp.ui.commandNum = 0;
            }
            if(code == KeyBind.downKey) {
                gp.playSFX(gp.sfx, 1);
                if(gp.ui.commandNum ==0)
                    gp.ui.commandNum = 1;
                else
                    gp.ui.commandNum = 0;
            }
            if(code == KeyBind.nextKey) {
                gp.playSFX(gp.sfx, 2);

                if(gp.ui.commandNum == 0) {
                    //kalo makan
                    gp.player.energy += gp.player.currentItem.energyGiven; // Add energy from held food
                    System.out.println("Player ate food: " + gp.player.currentItem.name + " and gained " + gp.player.currentItem.energyGiven + " energy.");
                    gp.player.inventory.remove(gp.player.currentItem); // Remove food from inventory
                    gp.player.currentItem = null; // Clear current item
                    gp.gameState = gp.playState;
                } else if(gp.ui.commandNum == 1) {
                    //kalo hold makanan
                    gp.player.isHolding = true; // Set hold food to true
                    gp.gameState = gp.playState;
                }
            }
        }


        else if (gp.gameState == gp.dialogueState && gp.ui.currentEntityDialogue != null) {
             if (!gp.ui.currentEntityDialogue.exitDialogueDisable) { //no tradeable
                if (code == KeyBind.nextKey) {    
                    if(!gp.player.dialogueDone){
                            gp.ui.dialoguePage++;
                        
                        gp.ui.charIndex = 0; // Reset character index for dialogue
                        gp.ui.combinedText = ""; // Reset combined text for dialogu
                    }else{
                        gp.ui.charIndex = 0; // Reset character index for dialogue
                        gp.ui.combinedText = ""; // Reset combined text for dialogu
                        gp.ui.currentEntityDialogue.dialogueIndex++;
                        gp.ui.dialoguePage = 0;
                        gp.gameState = gp.playState;
                        gp.player.dialogueDone = false; // Reset dialogue done state

                    }
                }           
            }else{
                if(gp.ui.currentEntityDialogue != null && 
                gp.ui.currentEntityDialogue.getClass().getSimpleName().equals("Npc_Merchant")) {
                    if(code == KeyBind.upKey) {
                        gp.playSFX(gp.sfx, 1);
                        gp.ui.merchantChoice--;
                        if(gp.ui.merchantChoice < 0) {
                            gp.ui.merchantChoice = 2;
                        }
                    }
                    if(code == KeyBind.downKey) {
                        gp.playSFX(gp.sfx, 1);
                        gp.ui.merchantChoice++;
                        if(gp.ui.merchantChoice > 2) {
                            gp.ui.merchantChoice = 0;
                        }
                    }
                }

            }
    }

        else if (gp.gameState == gp.inventoryState) {

            if (code == KeyBind.nextKey) {
            gp.playSFX(gp.sfx, 2);

                gp.gameState = gp.playState;
            }
        }

        else if (gp.gameState == gp.eventFoundState) {
            if (code == KeyBind.nextKey) {
                gp.gameState = gp.playState;
            }
        }
        else if(gp.gameState == gp.confirmationState) {
            if (code == KeyBind.upKey) {
                gp.playSFX(gp.sfx, 1);
                
                if(gp.ui.commandNum == 0) {
                    gp.ui.commandNum = 1;
                } else {
                    gp.ui.commandNum = 0;
                }
            }
            if( code == KeyBind.downKey) {
                gp.playSFX(gp.sfx, 1);
                if(gp.ui.commandNum == 0) {
                    gp.ui.commandNum = 1;
                } else {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyBind.nextKey) {
                gp.playSFX(gp.sfx, 2);
                if (gp.ui.commandNum == 0) {
                    gp.ui.confirmation = true;
                    System.out.println("Confirmation accepted");
                } else if (gp.ui.commandNum == 1)  {
                    gp.ui.confirmation = false;
                    System.out.println("Confirmation rejected");
                }
                gp.gameState = gp.playState; // Exit confirmation state
                gp.ui.confirmationPageOn = false; // Reset confirmation page state
            }
        }
        else if (code == KeyBind.grid) {
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
            isSprint = false;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            escPressed = false;
        }
        if (code == KeyBind.nextKey) {
            enterPressed = false;
        }

    }

    public void updatePlayerPath() {
        gp.player.redeclareAnimation();
    }

    public void playerInventory(int code) {
        if (code == KeyBind.upKey) {
            if (gp.ui.playerSlotRow != 0) {
                gp.ui.playerSlotRow--;
            }

        }

        if (code == KeyBind.downKey) {
            if (gp.ui.playerSlotRow != 5) {
                gp.ui.playerSlotRow++;
            }
        }

        if (code == KeyBind.rightKey) {
            if (gp.ui.playerSlotCol != 11) {
                gp.ui.playerSlotCol++;
            }
        }

        if (code == KeyBind.leftKey) {
            if (gp.ui.playerSlotCol != 0) {
                gp.ui.playerSlotCol--;
            }
        }
    }

}
