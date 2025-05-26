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
    public int titleScreenState = 0;
    public int pauseScreenState = 0; // 0: pausedmenu , 1: resume, 2: settings, 3: exit
    EnergyBar energyBar;
    public Entity currentEntityDialogue; // Entity that is currently interacting with the player

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
            drawPauseScreen();
        }

        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
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
            g2.setColor(new Color(70, 120, 80));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            // title name
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            String text = "LOAD GAME";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;

            // shadow
            g2.setColor(Color.BLACK);
            g2.drawString(text, x + 5, y + 5);

            // text
            g2.setColor(Color.WHITE);
            g2.drawString(text, x, y);
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
            dialogueWindow = ImageIO.read(getClass().getResourceAsStream("/assets/ui/dialogBox.png"));
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

    public void drawSubWindow(int x, int y, int width, int height) {
        g2.setColor(new Color(0, 0, 0, 200));
        g2.fillRoundRect(x, y, width, height, 35, 35);

        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }
}
