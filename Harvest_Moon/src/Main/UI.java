package Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;

public class UI {

    Graphics2D g2;
    GamePanel gp;
    Font arial_40;
    BufferedImage image;
    public boolean messageOn = false;
    public String message = "";
    public int messageCounter = 0;
    public String currentDialogue = "";
    public String currentDialogueName = "";
    public int commandNum = 0; // 0: new game, 1: load game, 2: exit
    public int titleScreenState = 0; // 0: title screen, 1: new Game(customize character)
    public int pauseScreenState = 0; // 0: pausedmenu , 1: resume, 2: settings, 3: exit
    public int optionState = 0; // 0: top, 1: music, 2: se, 3: control, 4: end game
    int customizeNum = 0; //
    EnergyBar energyBar;
    public Entity currentEntityDialogue; // Entity that is currently interacting with the player
    int subState = 0;

    public UI(GamePanel gp) {
        this.gp = gp;
        // arial_40 = new Font("Arial", Font.PLAIN, 40);
        this.energyBar = new EnergyBar(70, 50, 200, 20, 10, gp.player.maxEnergy);

    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        // g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        if (gp.gameState == gp.playState) {
            drawGameUI();

        }

        if (gp.gameState == gp.pauseState) {
            if (pauseScreenState == 0) {
                drawPauseScreen();
            }

            if (pauseScreenState == 2) {
                drawOptionScreen();
            }
        }

        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }
        if (gp.gameState == gp.eventFoundState) {
            drawDialogueScreenEvent();
        }

    }

    public void drawGameUI() {
        // logo karakter

        // ini aku mau nambain kalo dia energy > 50 pake haappy yang mata nya buka kalo
        // ga pake yang sleep
        BufferedImage playerIcon = null;
        try {
            playerIcon = ImageIO
                    .read(getClass().getResourceAsStream("/assets/player/SLEEP/" + gp.player.getPath() + "0.png"));
        } catch (IOException e) {
            System.out.println("Error loading player image UI: " + e.getMessage());
        }
        g2.drawImage(playerIcon, 3, 8, gp.tileSize * 1 + 15, gp.tileSize * 2 + 15, null);
        // energy bar and name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F));
        g2.drawString(gp.player.name, 70, 40);
        this.energyBar.maxValue = gp.player.maxEnergy;
        energyBar.setValue(gp.player.energy);
        energyBar.draw(g2);

        // info panel
        BufferedImage infoPanel = null;
        try {
            infoPanel = ImageIO.read(getClass().getResourceAsStream("/assets/ui/infoPanel.png"));
        } catch (IOException e) {
            System.out.println("Error loading info panel image UI: " + e.getMessage());
        }
        g2.drawImage(infoPanel, gp.screenWidth - 250, 0, 242, 128, null);

        // info Panel gold
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 12F));
        g2.setColor(Color.BLACK);
        String gold = String.valueOf(gp.player.gold) + " G";
        int x = getXforRightAlignedText(gold, 729);
        g2.drawString(gold, x, 107);

    }

    public void drawTitleScreen() {

        Image mainMenuImage = null, newGameButton = null, loadGameButton = null, exitButton = null,
                newGameActive = null, loadGameActive = null, exitActive = null;

        if (titleScreenState == 0) {
            try {
                mainMenuImage = ImageIO.read(getClass().getResourceAsStream("/assets/ui/mainMenuBg.png"));
                newGameButton = ImageIO.read(getClass().getResourceAsStream("/assets/ui/newGame.png"));
                loadGameButton = ImageIO.read(getClass().getResourceAsStream("/assets/ui/loadGame.png"));
                exitButton = ImageIO.read(getClass().getResourceAsStream("/assets/ui/exit.png"));
                newGameActive = ImageIO.read(getClass().getResourceAsStream("/assets/ui/newGameActive.png"));
                loadGameActive = ImageIO.read(getClass().getResourceAsStream("/assets/ui/loadGameActive.png"));
                exitActive = ImageIO.read(getClass().getResourceAsStream("/assets/ui/exitActive.png"));
            } catch (IOException e) {
                System.out.println("Error loading title screen images: " + e.getMessage());
            }

            if (mainMenuImage != null) {
                g2.drawImage(mainMenuImage, 0, 0, gp.screenWidth, gp.screenHeight, null);

                // new game button
                if (commandNum == 0) {
                    g2.drawImage(newGameActive, gp.screenWidth / 2 - (gp.tileSize / 2) - 150, gp.tileSize * 5,
                            gp.tileSize * 8, gp.tileSize * 2, null);
                } else {
                    g2.drawImage(newGameButton, gp.screenWidth / 2 - (gp.tileSize / 2) - 150, gp.tileSize * 5,
                            gp.tileSize * 8, gp.tileSize * 2, null);
                }
                // load game button
                if (commandNum == 1) {
                    g2.drawImage(loadGameActive, gp.screenWidth / 2 - (gp.tileSize / 2) - 150, gp.tileSize * 7,
                            gp.tileSize * 8, gp.tileSize * 2, null);
                } else {
                    g2.drawImage(loadGameButton, gp.screenWidth / 2 - (gp.tileSize / 2) - 150, gp.tileSize * 7,
                            gp.tileSize * 8, gp.tileSize * 2, null);
                }
                // exit button
                if (commandNum == 2) {
                    g2.drawImage(exitActive, gp.screenWidth / 2 - (gp.tileSize / 2) - 150, gp.tileSize * 9,
                            gp.tileSize * 8, gp.tileSize * 2, null);
                } else {
                    g2.drawImage(exitButton, gp.screenWidth / 2 - (gp.tileSize / 2) - 150, gp.tileSize * 9,
                            gp.tileSize * 8, gp.tileSize * 2, null);
                }
            }

        }
        if (titleScreenState == 1) {
            
            BufferedImage customizeCharacter = null;
            try {
                customizeCharacter = ImageIO.read(getClass().getResourceAsStream("/assets/ui/customizeCharacter/page.png"));
            } catch (IOException e) {
                System.out.println("Error loading customize character image: " + e.getMessage());
            }

            if (customizeCharacter != null) {
                g2.drawImage(customizeCharacter, 0, 0, gp.screenWidth, gp.screenHeight, null);
            }


            BufferedImage eye = null, hair = null, outfit = null, body = null;
            BufferedImage eyeActive = null, hairActive = null, outfitActive = null, bodyActive = null;

            try {
                eye = ImageIO.read(getClass().getResourceAsStream("/assets/ui/customizeCharacter/eye/" + gp.player.listEye[gp.player.eyeIndex] + ".png"));
                hair = ImageIO.read(getClass().getResourceAsStream("/assets/ui/customizeCharacter/hair/" + gp.player.listHair[gp.player.hairIndex] + ".png"));
                outfit = ImageIO.read(getClass().getResourceAsStream("/assets/ui/customizeCharacter/outfit/" + gp.player.listOutfit[gp.player.outfitIndex] + ".png"));
                body = ImageIO.read(getClass().getResourceAsStream("/assets/ui/customizeCharacter/body/" + gp.player.listBody[gp.player.bodyIndex] + ".png"));

                eyeActive = ImageIO.read(getClass().getResourceAsStream("/assets/ui/customizeCharacter/eye/" + gp.player.listEye[gp.player.eyeIndex] + "Active.png"));
                hairActive = ImageIO.read(getClass().getResourceAsStream("/assets/ui/customizeCharacter/hair/" + gp.player.listHair[gp.player.hairIndex] + "Active.png"));
                outfitActive = ImageIO.read(getClass().getResourceAsStream("/assets/ui/customizeCharacter/outfit/" + gp.player.listOutfit[gp.player.outfitIndex] + "Active.png"));
                bodyActive = ImageIO.read(getClass().getResourceAsStream("/assets/ui/customizeCharacter/body/" + gp.player.listBody[gp.player.bodyIndex] + "Active.png"));

            } catch (IOException e) {
                System.out.println("Error loading character customization images: " + e.getMessage());
            }

            if(customizeNum == 0) {
                g2.drawImage(bodyActive, 400, 125, 335, 65, null);
            } else {
                g2.drawImage(body, 400, 125, 335, 65, null);
            }
            if(customizeNum == 1) {
                g2.drawImage(eyeActive, 400, 225, 335, 65, null);
            } else {
                g2.drawImage(eye, 400, 225, 335, 65, null);
            }
            if(customizeNum == 2) {
                g2.drawImage(hairActive, 400, 325, 335, 65, null);
            } else {
                g2.drawImage(hair, 400, 325, 335, 65, null);
            } 
            if(customizeNum == 3) {
                g2.drawImage(outfitActive, 400, 425, 335, 65, null);
            } else {
                g2.drawImage(outfit, 400, 425, 335, 65, null);
            }


            g2.drawImage(gp.player.animationList.get(1).down[0], 150, 125, gp.playerSizeX * 2, gp.playerSizeY * 2, null);
        }
    }

    public void drawPauseScreen() {

        // dim bg
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        if (pauseScreenState == 0) {
            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
            String text = "PAUSED";
            int x = getXforCenteredText(text);
            int y = gp.screenHeight / 5;

            g2.drawString(text, x, y);

            // pause menu
            BufferedImage resumeButton = null, settingsButton = null, exitButton = null, resumeActive = null,
                    settingsActive = null, exitActive = null, saveButton = null, saveActive = null;
            try {
                resumeButton = ImageIO.read(getClass().getResourceAsStream("/assets/ui/resume.png"));
                settingsButton = ImageIO.read(getClass().getResourceAsStream("/assets/ui/settings.png"));
                exitButton = ImageIO.read(getClass().getResourceAsStream("/assets/ui/mainMenu.png"));
                saveButton = ImageIO.read(getClass().getResourceAsStream("/assets/ui/saveGame.png"));
                resumeActive = ImageIO.read(getClass().getResourceAsStream("/assets/ui/resumeActive.png"));
                settingsActive = ImageIO.read(getClass().getResourceAsStream("/assets/ui/settingsActive.png"));
                exitActive = ImageIO.read(getClass().getResourceAsStream("/assets/ui/mainMenuActive.png"));
                saveActive = ImageIO.read(getClass().getResourceAsStream("/assets/ui/saveGameActive.png"));
            } catch (IOException e) {
                System.out.println("Error loading pause screen images: " + e.getMessage());
            }

            if (commandNum == 0) {
                g2.drawImage(resumeActive, gp.screenWidth / 2 - 319 / 2, gp.tileSize * 4, 319, 98, null);
            } else {
                g2.drawImage(resumeButton, gp.screenWidth / 2 - 319 / 2, gp.tileSize * 4, 319, 98, null);
            }

            if (commandNum == 1) {
                g2.drawImage(saveActive, gp.screenWidth / 2 - 319 / 2, gp.tileSize * 5 + 20, 319, 98, null);
            } else {
                g2.drawImage(saveButton, gp.screenWidth / 2 - 319 / 2, gp.tileSize * 5 + 20, 319, 98, null);
            }

            if (commandNum == 2) {
                g2.drawImage(settingsActive, gp.screenWidth / 2 - 319 / 2, gp.tileSize * 6 + 40, 319, 98, null);
            } else {
                g2.drawImage(settingsButton, gp.screenWidth / 2 - 319 / 2, gp.tileSize * 6 + 40, 319, 98, null);
            }

            if (commandNum == 3) {
                g2.drawImage(exitActive, gp.screenWidth / 2 - 319 / 2, gp.tileSize * 7 + 60, 319, 98, null);
            } else {
                g2.drawImage(exitButton, gp.screenWidth / 2 - 319 / 2, gp.tileSize * 7 + 60, 319, 98, null);
            }

        }
    }

    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }

    public int getXforCenteredText(String text, int x1, int x2) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int width = x2 - x1;
        int x = x1 + (width / 2) - (length / 2);
        return x;
    }

    public int getXforRightAlignedText(String text, int rightEdge) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = rightEdge - length;
        return x;
    }

    public void drawOptionScreen() {
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(32F));

        // sub window
        int frameX = gp.tileSize * 6;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 8;
        int frameHeight = gp.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch (subState) {
            case 0:
                option_Top(frameX, frameY);
                break;
            case 1:
                options_fullScreenNotification(frameX, frameY);
                break;
            case 2:
                break;
        }
        // gp.keyH.enterPressed = false; // reset enter pressed after drawing options
    }

    public void option_Top(int frameX, int frameY) {
        int textX;
        int textY;

        // title
        String text = "Options";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        // full screen on/off
        textX = frameX + gp.tileSize;
        textY += gp.tileSize * 2;
        g2.drawString("Full Screen ", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            // //if (gp.keyH.enterPressed == true) {
            // if (gp.fullScreen == false) {
            // gp.fullScreen = true;
            // } else if (gp.fullScreen == true) {
            // gp.fullScreen = false;
            // }
            // }
            subState = 1; // go to full screen notification
        }

        // music
        textY += gp.tileSize * 2;
        g2.drawString("Music ", textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
        }
        // se
        textY += gp.tileSize;
        g2.drawString("SE ", textX, textY);
        if (commandNum == 2) {
            g2.drawString(">", textX - 25, textY);
        }

        // back

        textY += gp.tileSize * 2;
        g2.drawString("Back ", textX, textY);
        if (commandNum == 3) {
            g2.drawString(">", textX - 25, textY);
            // if(gp.keyH.enterPressed == true) {
            pauseScreenState = 0; // go back to pause screen
            // }
        }

        // full screen check box
        int checkBoxSize = 24;
        int checkBoxOffset = 180; // distance from text to checkbox
        int musicBarOffset = 180;
        int seBarOffset = 180;

        // Full Screen
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize * 3;
        int checkBoxX = textX + checkBoxOffset;
        int checkBoxY = textY - checkBoxSize + 8; // align vertically with text

        g2.setStroke(new BasicStroke(3));
        g2.drawRect(checkBoxX, checkBoxY, checkBoxSize, checkBoxSize);
        if (gp.fullScreen) {
            g2.fillRect(checkBoxX, checkBoxY, checkBoxSize, checkBoxSize);
        }

        // Music volume bar
        textY += gp.tileSize * 2;
        int volumeBarWidth = 120;
        int volumeBarHeight = 24;
        int musicBarX = textX + musicBarOffset;
        int musicBarY = textY - volumeBarHeight + 8;
        g2.drawRect(musicBarX, musicBarY, volumeBarWidth, volumeBarHeight);
        int musicVolumeWidth = (int) ((volumeBarWidth / 5.0) * gp.masterMusic.volumeScale);
        g2.fillRect(musicBarX, musicBarY, musicVolumeWidth, volumeBarHeight);

        // SE volume bar
        textY += gp.tileSize;
        int seBarX = textX + seBarOffset;
        int seBarY = textY - volumeBarHeight + 8;
        g2.drawRect(seBarX, seBarY, volumeBarWidth, volumeBarHeight);
        int seVolumeWidth = (int) ((volumeBarWidth / 5.0) * gp.sfx.volumeScale);
        g2.fillRect(seBarX, seBarY, seVolumeWidth, volumeBarHeight);
    }

    public void drawDialogueScreen() {
        // bikin screen gelapin play screen dikit
        g2.setColor(new Color(0, 0, 0, 150)); // semi-transparent black
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // curren person yang ngomong

        g2.drawImage(currentEntityDialogue.animationList.get(1).down[0], 450, 25, gp.tileSize * 4, gp.tileSize * 8,
                null);

        // window
        int x = 0;
        int y = 0;
        int width = gp.screenWidth;
        int height = gp.screenHeight;
        BufferedImage dialogueWindow = null;
        try {
            if(currentEntityDialogue.specialNpc){
                dialogueWindow = ImageIO.read(getClass().getResourceAsStream("/assets/ui/dialogBoxSpecial.png"));
            }
            else{
                dialogueWindow = ImageIO.read(getClass().getResourceAsStream("/assets/ui/dialogBox.png"));
            }
        } catch (IOException e) {
            System.out.println("Error loading dialogue window image: " + e.getMessage());
        }
        g2.drawImage(dialogueWindow, x, y, width, height, null);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gp.tileSize;
        y += gp.tileSize;

        // nulis nama
        g2.setColor(new Color(94, 44, 19));
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
        g2.drawString(currentDialogueName, getXforCenteredText(currentDialogueName, 73, 286), 350);

        // for(String line : currentDialogue.split("\n")){
        // g2.drawString(line, x, y);
        // y += 40;
        // }
    }

    public void drawDialogueScreenEvent() {
        // bikin screen gelapin play screen dikit
        g2.setColor(new Color(0, 0, 0, 150)); // semi-transparent black
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // window
        int x = 0;
        int y = 0;
        int width = gp.screenWidth;
        int height = gp.screenHeight;
        BufferedImage dialogueWindow = null;
        try {
            dialogueWindow = ImageIO.read(getClass().getResourceAsStream("/assets/ui/dialogBox.png"));
        } catch (IOException e) {
            System.out.println("Error loading dialogue window image: " + e.getMessage());
        }
        g2.drawImage(dialogueWindow, x, y, width, height, null);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gp.tileSize;
        y += gp.tileSize;

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(currentDialogue, x, y);
            y += 40;
        }
    }

    public void options_fullScreenNotification(int frameX, int frameY) {
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize * 3;

        currentDialogue = "The change will take effect after you restart the game.";

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        // back
        textY += gp.tileSize * 9;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            // if(gp.keyH.enterPressed == true) {
            // subState = 0;
            // }
        }

    }

    public void drawSubWindow(int x, int y, int width, int height) {
        g2.setColor(new Color(0, 0, 0, 200));
        g2.fillRoundRect(x, y, width, height, 35, 35);

        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }
}
