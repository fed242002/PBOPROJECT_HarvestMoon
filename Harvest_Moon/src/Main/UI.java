package Main;

import entity.Entity;
import entity.Item;
import entity.ItemList;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

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
    public int customizeNum = 0; // 0: body, 1: eye, 2: hair, 3: outfit, 4: confirm
    EnergyBar energyBar;
    public Entity currentEntityDialogue; // Entity that is currently interacting with the player
    int subState = 0;
    public int playerSlotCol = 0; // inventory slot column
    public int playerSlotRow = 0; // inventory slot row
    public int merchantChoice = 0;
    public BufferedImage imageLighting;
    public String path2 = "";
    public int charIndex = 0;
    public String combinedText = "";
    public BufferedImage bodyPreview;
    public BufferedImage eyePreview;
    public BufferedImage hairPreview;
    public BufferedImage outfitPreview;
    public String name;
    
    public boolean confirmation = false;
    public boolean confirmationPageOn = false;
    public String confirmationText = "";


    public ArrayList<String> dialogueList;
    public int dialoguePage = 0;

    public void showConfirmation(String text){
        gp.gameState = gp.confirmationState;
        confirmationText = text;
        confirmationPageOn = true;
        commandNum = 0;
    }

    

    public UI(GamePanel gp) {
        this.gp = gp;
        // arial_40 = new Font("drae", Font.PLAIN, 40);
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
        if(gp.gameState == gp.confirmationState){
            drawConfirmationPage();
        }
        

        if (gp.gameState == gp.pauseState) {
            if (pauseScreenState == 0) {
                drawPauseScreen();
            }

            if (pauseScreenState == 1) {
                gp.saveLoad1.save();
                System.out.println("Progress has been saved");
                gp.ui.pauseScreenState = 0; // balikin ke pause screen
            }

            if (pauseScreenState == 2) {
                drawOptionScreen();
            }
        }

        if (gp.gameState == gp.dialogueState) {
            handleMerchantNavigation();
            drawDialogueScreen();
            // Add this to draw trade screen when talking to merchant
            if (currentEntityDialogue != null && 
                currentEntityDialogue.getClass().getSimpleName().equals("Npc_Merchant")) {
                drawTradeScreen();
            }
        }
        if (gp.gameState == gp.eventFoundState) {
            drawDialogueScreenEvent();
        }

        // buat inventory
        if (gp.gameState == gp.inventoryState) {
            drawInventory(gp.player, true);
        }

        if(gp.gameState == gp.foodItemChooseState){
            chooseFoodItem();
        }
    }

    public void drawConfirmationPage(){

        g2.setColor(new Color(0, 0, 0, 150)); // semi-transparent black
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        BufferedImage yes = gp.setImage("/assets/ui/yes.png");
        BufferedImage yesActive = gp.setImage("/assets/ui/yesActive.png");
        BufferedImage no = gp.setImage("/assets/ui/no.png");
        BufferedImage noActive = gp.setImage("/assets/ui/noActive.png");


        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 25F));
        g2.drawString(confirmationText,getXforCenteredText(confirmationText) , gp.tileSize * 3 + 20);
        if(commandNum == 0){
            g2.drawImage(yesActive, gp.screenWidth / 2 - 288 / 2, gp.tileSize * 4, 288, 48, null);
        }else{
            g2.drawImage(yes, gp.screenWidth / 2 - 288 / 2, gp.tileSize * 4, 288, 48, null);
        }
        if(commandNum == 1){
            g2.drawImage(noActive, gp.screenWidth / 2 - 288 / 2, gp.tileSize * 5 , 288, 48, null);
        }else{
            g2.drawImage(no, gp.screenWidth / 2 - 288 / 2, gp.tileSize * 5 , 288, 48, null);
        }

    }

    public void chooseFoodItem(){
        BufferedImage use = gp.setImage("/assets/ui/use.png");
        BufferedImage useActive = gp.setImage("/assets/ui/useActive.png");
        BufferedImage hold = gp.setImage("/assets/ui/hold.png");
        BufferedImage holdActive = gp.setImage("/assets/ui/holdActive.png");


        //bikin bg dim
        g2.setColor(new Color(0, 0, 0, 150)); // semi-transparent black
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        if(commandNum == 0){
            g2.drawImage(useActive, gp.screenWidth / 2 - 288 / 2, gp.tileSize * 4, 288, 48, null);
        }else{
            g2.drawImage(use, gp.screenWidth / 2 - 288 / 2, gp.tileSize * 4, 288, 48, null);
        }

        if(commandNum == 1){
            g2.drawImage(holdActive, gp.screenWidth / 2 - 288 / 2, gp.tileSize * 5 , 288, 48, null);
        }else{
            g2.drawImage(hold, gp.screenWidth / 2 - 288 / 2, gp.tileSize * 5  , 288, 48, null);
        }
        

    }

    public void drawGameUI() {
        // logo karakter

        // ini aku mau nambain kalo dia energy > 50 pake haappy yang mata nya buka kalo
        // ga pake yang sleep
        BufferedImage playerbody = null;
        BufferedImage playereye = null;
        BufferedImage playerhair = null;
        playerbody = gp.player.animationList.get(2).body_down[0]; // get the body image from the animation list
        playereye = gp.player.animationList.get(2).eye_down[0]; // get the eye image from the animation list
        playerhair = gp.player.animationList.get(2).hair_down[0]; // get the hair image from the animation list
        g2.drawImage(playerbody, 3, 8, gp.tileSize * 1 + 15, gp.tileSize * 2 + 15, null);
        g2.drawImage(playereye, 3, 8, gp.tileSize * 1 + 15, gp.tileSize * 2 + 15, null);
        g2.drawImage(playerhair, 3, 8, gp.tileSize * 1 + 15, gp.tileSize * 2 + 15, null);
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

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 12F));
        g2.setColor(Color.BLACK);

        // info Panel day
        g2.drawString("Date: " + gp.date[gp.currDate] +  " " + gp.day[gp.currDay],550 , 35);
        g2.drawString("Month: " + gp.month[gp.currentMonth], 550, 55);
        g2.drawString("Time: " + gp.hour + ":" + String.format("%02d", gp.minute), 550, 75); // Display the time in 
        if(gp.hour >= 6 && gp.hour < 13)  // 6 AM to 1 PM
            {
                path2 = "/assets/Lighting/Sunny.png";
                imageLighting = gp.setImage(path2);
            }
            else if(gp.hour >= 13 && gp.hour < 18) // 1 PM to 6 PM
            {
                path2 = "/assets/Lighting/Dusk.png";
                imageLighting = gp.setImage(path2);
            }
            else if (gp.hour >= 18 || gp.hour < 2) // 6 PM to 2 AM (spans midnight)
            {
                path2 = "/assets/Lighting/Moon.png";
                imageLighting = gp.setImage(path2);
            }
            else if(gp.hour >= 2 && gp.hour < 6) // 2 AM to 6 AM
            {
                path2 = "/assets/Lighting/Dawn.png";
                imageLighting = gp.setImage(path2);
            }
        g2.drawImage(imageLighting, 690, 25,48,48,null);

        // info Panel gold
        String gold = String.valueOf(gp.player.gold) + " G";
        int x = getXforRightAlignedText(gold, 729);
        g2.drawString(gold, x, 107);


        //draw currentActiveItem

        //frame
        BufferedImage currentActiveItemframe = gp.setImage("/assets/ui/currenActiveItem.png");
        g2.drawImage(currentActiveItemframe, 10, 576-144+10, 158, 144, null);
        
        //item
        if(gp.player.currentItem != null){
            BufferedImage currentActiveItem = null;
            currentActiveItem = gp.player.currentItem.image;
            g2.drawImage(currentActiveItem, 25 + 35, 576-144+35, 64, 64, null);
        }
       



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
                customizeCharacter = ImageIO
                        .read(getClass().getResourceAsStream("/assets/ui/customizeCharacter/page.png"));
            } catch (IOException e) {
                System.out.println("Error loading customize character image: " + e.getMessage());
            }

            if (customizeCharacter != null) {
                g2.drawImage(customizeCharacter, 0, 0, gp.screenWidth, gp.screenHeight, null);
            }

            BufferedImage eye = null, hair = null, outfit = null, body = null;
            BufferedImage eyeActive = null, hairActive = null, outfitActive = null, bodyActive = null;
            BufferedImage confirm = null, confirmActive = null;

            try {
                eye = ImageIO.read(getClass().getResourceAsStream(
                        "/assets/ui/customizeCharacter/eye/" + gp.player.listEye[gp.player.eyeIndex] + ".png"));
                hair = ImageIO.read(getClass().getResourceAsStream(
                        "/assets/ui/customizeCharacter/hair/" + gp.player.listHair[gp.player.hairIndex] + ".png"));
                outfit = ImageIO.read(getClass().getResourceAsStream("/assets/ui/customizeCharacter/outfit/"
                        + gp.player.listOutfit[gp.player.outfitIndex] + ".png"));
                body = ImageIO.read(getClass().getResourceAsStream(
                        "/assets/ui/customizeCharacter/body/" + gp.player.listBody[gp.player.bodyIndex] + ".png"));
                confirm = ImageIO.read(getClass().getResourceAsStream("/assets/ui/customizeCharacter/confirm.png"));

                

                eyeActive = ImageIO.read(getClass().getResourceAsStream(
                        "/assets/ui/customizeCharacter/eye/" + gp.player.listEye[gp.player.eyeIndex] + "Active.png"));
                hairActive = ImageIO.read(getClass().getResourceAsStream("/assets/ui/customizeCharacter/hair/"
                        + gp.player.listHair[gp.player.hairIndex] + "Active.png"));
                outfitActive = ImageIO.read(getClass().getResourceAsStream("/assets/ui/customizeCharacter/outfit/"
                        + gp.player.listOutfit[gp.player.outfitIndex] + "Active.png"));
                bodyActive = ImageIO.read(getClass().getResourceAsStream("/assets/ui/customizeCharacter/body/"
                        + gp.player.listBody[gp.player.bodyIndex] + "Active.png"));
                confirmActive = ImageIO
                        .read(getClass().getResourceAsStream("/assets/ui/customizeCharacter/confirmActive.png"));

            } catch (IOException e) {
                System.out.println("Error loading character customization images: " + e.getMessage());
            }

            if (customizeNum == 0) {
                g2.drawImage(bodyActive, 400, 125, 335, 65, null);
            } else {
                g2.drawImage(body, 400, 125, 335, 65, null);
            }
            if (customizeNum == 1) {
                g2.drawImage(eyeActive, 400, 225, 335, 65, null);
            } else {
                g2.drawImage(eye, 400, 225, 335, 65, null);
            }
            if (customizeNum == 2) {
                g2.drawImage(hairActive, 400, 325, 335, 65, null);
            } else {
                g2.drawImage(hair, 400, 325, 335, 65, null);
            }
            if (customizeNum == 3) {
                g2.drawImage(outfitActive, 400, 425, 335, 65, null);
            } else {
                g2.drawImage(outfit, 400, 425, 335, 65, null);
            }
            if (customizeNum == 4) {
                g2.drawImage(confirmActive, 40, 400, 335, 65, null);
            } else {
                g2.drawImage(confirm, 40, 400, 335, 65, null);
            }

            // preview player
            g2.drawImage(bodyPreview, 150, 125, gp.playerSizeX * 2, gp.playerSizeY * 2,null);
            g2.drawImage(eyePreview, 150, 125, gp.playerSizeX * 2, gp.playerSizeY * 2, null);
            g2.drawImage(hairPreview, 150, 125, gp.playerSizeX * 2, gp.playerSizeY * 2, null);
            g2.drawImage(outfitPreview, 150, 125, gp.playerSizeX * 2, gp.playerSizeY * 2, null);
                   

        }

        if (titleScreenState == 2) {
            // background ireng
            g2.setColor(new Color(0, 0, 0, 180));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            // kotak
            int boxWidth = 400;
            int boxHeight = 120;
            int boxX = gp.screenWidth / 2 - boxWidth / 2;
            int boxY = gp.screenHeight / 2 - boxHeight / 2;
            g2.setColor(new Color(255, 255, 255, 230));
            g2.fillRoundRect(boxX, boxY, boxWidth, boxHeight, 30, 30);
            g2.setColor(new Color(101, 67, 33));
            g2.setStroke(new BasicStroke(4));
            g2.drawRoundRect(boxX, boxY, boxWidth, boxHeight, 30, 30);

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 28F));
            String prompt = "Enter your name:";
            int promptX = getXforCenteredText(prompt);
            g2.drawString(prompt, promptX, boxY + 40);

            // gambar namanya
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 26F));
            String displayName = name != null ? name : "";
            String cursor = (System.currentTimeMillis() / 500) % 2 == 0 ? "|" : "";
            String nameWithCursor = displayName + cursor;
            int nameX = getXforCenteredText(nameWithCursor);
            g2.drawString(nameWithCursor, nameX, boxY + 80);

            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 18F));
            String instr = "Press ENTER to confirm";
            int instrX = getXforCenteredText(instr);
            g2.drawString(instr, instrX, boxY + boxHeight - 10);
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

    public void drawInventory(Entity entity, boolean cursor) // buat inventory
    {
        // frame nya
        int frameX = 0;
        int frameY = 0;
        int frameWidth = 0;
        int frameHeight = 0;
        int slotRow = 0;
        int slotCol = 0;

        if (entity == gp.player) {
            frameX = 48;
            frameY = 48;
            // frameWidth = gp.tileSize * 6;
            // frameHeight = gp.tileSize * 5;
            frameWidth = 672;
            frameHeight = 336;
            slotCol = playerSlotCol; // inventory slot column
            slotRow = playerSlotRow; // inventory slot row
        }
        // tambahin npc mu disini

        // frame
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // slot invent
        final int slotXStart = frameX + 20;
        final int slotYStart = frameY + 20;
        int slotX = slotXStart;
        int slotY = slotYStart;
        int slotSize = gp.tileSize + 3; // size of each slot

        // draw players item
        for (int i = 0; i < entity.inventory.size(); i++) {

            // equip cursor
            // if (entity.inventory.get(i) == entity.equippedItem) { // kalo dipake
            // dan itu equipment
            // g2.setColor(new Color(240, 190, 90)); // gold color with transparency
            // g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
            // }

            g2.drawImage(entity.inventory.get(i).image, slotX + 8, slotY + 8,32,32, null);

            // display amount
            if (entity.inventory.get(i).amount > 1) {
                g2.setFont(g2.getFont().deriveFont(32F));
                int amountX;
                int amountY;

                String s = "" + entity.inventory.get(i).amount;
                amountX = getXforRightAlignedText(s, slotX + 44);
                amountY = slotY + gp.tileSize; // adjust Y position to fit in the slot

                // shadow
                g2.setColor(new Color(60, 60, 60));
                g2.drawString(s, amountX, amountY);
                // number
                g2.setColor(Color.WHITE);
                g2.drawString(s, amountX - 3, amountY - 3);
            }

            slotX += slotSize; // move to next slot in row

            if (i % 12 == 11) {
                slotX = slotXStart; // reset to start of row
                slotY += slotSize; // move to next row
            }
        }

        // cursor
        if (cursor == true) {
            int cursorX = slotXStart + (slotSize * slotCol);
            int cursorY = slotYStart + (slotSize * slotRow);
            int cursorWidth = gp.tileSize;
            int cursorHeight = gp.tileSize;

            // draw cursor
            // g2.setColor(Color.WHITE);
            g2.setColor(new Color(101, 67, 33));
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

            // description frame nya
            int dFrameX = frameX;
            int dFrameY = frameY + frameHeight;
            int dFrameWidth = frameWidth;
            int dFrameHeight = gp.tileSize * 3; // height for description

            // draw desc text
            int textX = dFrameX + 20;
            int textY = dFrameY + gp.tileSize;
            g2.setFont(g2.getFont().deriveFont(28F));

            int itemIndex = getItemIndexOnSlot(slotCol, slotRow); // get iditem index based on slotCol and slotRow

            if (itemIndex < entity.inventory.size()) {

                drawSubWindow(dFrameX, dFrameY + 10, dFrameWidth, dFrameHeight);

                for (String line : entity.inventory.get(itemIndex).name.split("\n")) {
                    textY += 5;
                    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
                    g2.drawString(line, textX, textY);
                    textY += 40;
                }

                formatDialogText(entity.inventory.get(itemIndex).description, 576);

                for (String line : entity.inventory.get(itemIndex).description.split("\n")) {
                    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 25F));
                    g2.drawString(line, textX, textY);
                    textY += 32;
                }

            }
        }

    }

    public int getItemIndexOnSlot(int slotCol, int slotRow) {
        int itemIndex = slotCol + (slotRow * 12); // 12 items per row
        return itemIndex;
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
        //g2.drawString("Full Screen ", textX, textY);
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

        // g2.setStroke(new BasicStroke(3));
        // g2.drawRect(checkBoxX, checkBoxY, checkBoxSize, checkBoxSize);
        // if (gp.fullScreen) {
        //     g2.fillRect(checkBoxX, checkBoxY, checkBoxSize, checkBoxSize);
        // }

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


        if(currentEntityDialogue != null) {

            

            if(dialoguePage >= dialogueList.size()-1) {
                gp.player.dialogueDone = true;               
            }

            char characters[] = dialogueList.get(dialoguePage).toCharArray();

                if(charIndex < characters.length) {
                    String s = String.valueOf(characters[charIndex]);
                    combinedText = combinedText + s;
                    currentDialogue = combinedText;
                    // System.out.println(currentEntityDialogue.dialogueIndex);
                    charIndex++;
                }
        }

        // Draw the NPC portrait
        if (currentEntityDialogue != null ) {
            g2.drawImage(currentEntityDialogue.animationList.get(0).down[0], 
                450, 25, gp.tileSize * 4, gp.tileSize * 8, null);
        }


        // window
        int x = 0;
        int y = 0;
        int width = gp.screenWidth;
        int height = gp.screenHeight;
        BufferedImage dialogueWindow = null;
        try {
            
            dialogueWindow = ImageIO.read(getClass().getResourceAsStream("/assets/ui/dialogBox.png"));
            
            if(currentEntityDialogue != null) {
            if (currentEntityDialogue.specialNpc) {
                dialogueWindow = ImageIO.read(getClass().getResourceAsStream("/assets/ui/dialogBoxSpecial.png"));
            }}
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

        int nameX = getXforCenteredText(currentDialogueName, 73, 286);
        int nameY = 350;
        g2.drawString(currentDialogueName, nameX, nameY);



        // Posisi teks dialog di tengah bagian bawah layar

        int dialogBoxHeight = gp.tileSize * 4;

        int dialogBoxY = gp.screenHeight - dialogBoxHeight;



        // Hitung jumlah baris dalam dialog untuk penempatan vertikal yang tepat

        String[] lines = currentDialogue.split("\n");

        int lineHeight = 35;

        int totalTextHeight = (lines.length * lineHeight);

        

        // Posisi Y awal untuk teks dialog

        int textY = dialogBoxY + (dialogBoxHeight - totalTextHeight) / 2;

        // Gambar teks dialog yang terpusat
        g2.setColor(new Color(94, 44, 19));

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));

        for(String line : currentDialogue.split("\n")) {

            int textX = getXforCenteredText(line);

            g2.drawString(line, textX, textY);

            textY += lineHeight;

        }
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
        y += gp.tileSize;        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
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
        g2.setColor(new Color(230, 200, 177)); // semi-transparent white
        g2.fillRoundRect(x, y, width, height, 35, 35);

        g2.setColor(new Color(101, 67, 33));
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }    
    
    public void drawTradeScreen() {
        // Draw the appropriate screen based on subState
        switch (subState) {
            case 1:
                trade_select();
                break;
            case 2:
                System.out.println("masuk case 2 trade buy");
                trade_buy();
                break;
            case 3:
                drawInventorySellMode(); // <- tidak dipakai lagi
                break;
            case 4:
                drawInventorySellMode(); // ✅ tambahkan ini
                break;
        }
        gp.keyH.enterPressed = false;
    }

    public void drawInventorySellMode() {
        // Window
        int x = gp.tileSize * 2;
        int y = gp.tileSize;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.screenHeight - (gp.tileSize * 2);
        drawSubWindow(x, y, width, height);

        // Judul
        g2.setColor(new Color(94, 44, 19));
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        g2.drawString("Sell Inventory", x + 30, y + gp.tileSize);

        // Slot grid config
        int slotXStart = x + 30;
        int slotYStart = y + gp.tileSize * 2;
        int slotSize = gp.tileSize + 10;
        int slotPadding = 10;
        int maxCol = 5;

        // Ambil inventory
        ArrayList<Item> items = new ArrayList<>();
        for (Entity e : gp.player.inventory) {
            if (e instanceof Item) {
                items.add((Item) e);
            }
        }

        // Gambar kotak dan ikon
        for (int i = 0; i < items.size(); i++) {
            int col = i % maxCol;
            int row = i / maxCol;

            int slotX = slotXStart + (slotSize + slotPadding) * col;
            int slotY = slotYStart + (slotSize + slotPadding) * row;

            // Highlight jika dipilih
            if (col == playerSlotCol && row == playerSlotRow) {
                g2.setColor(new Color(255, 255, 0, 128));
                g2.fillRoundRect(slotX, slotY, slotSize, slotSize, 10, 10);
            }

            // Gambar ikon
            BufferedImage icon = items.get(i).image;
            g2.drawImage(icon, slotX + 8, slotY + 8, gp.tileSize - 4, gp.tileSize - 4, null);

            // Gambar jumlah
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(20f));
            g2.drawString("x" + items.get(i).amount, slotX + 2, slotY + slotSize - 4);
        }

        // Info item yang dipilih
        int index = playerSlotRow * maxCol + playerSlotCol;
        if (index >= 0 && index < items.size()) {
            Item selectedItem = items.get(index);
            g2.setColor(new Color(94, 44, 19));
            g2.setFont(g2.getFont().deriveFont(20f));
            g2.drawString("Name: " + selectedItem.name, x + 30, y + height - 60);
            g2.drawString("Sell Price: " + selectedItem.sellPrice + "G", x + 30, y + height - 30);
        }

        // Navigasi
        if (gp.keyH.upPressed) {
            if (playerSlotRow > 0) playerSlotRow--;
            gp.keyH.upPressed = false;
        }
        if (gp.keyH.downPressed) {
            if ((playerSlotRow + 1) * maxCol + playerSlotCol < items.size()) playerSlotRow++;
            gp.keyH.downPressed = false;
        }
        // if (gp.keyH.leftPressed) {
        //     if (playerSlotCol > 0) playerSlotCol--;
        //     gp.keyH.leftPressed = false;
        // }
        // if (gp.keyH.rightPressed) {
        //     if (playerSlotCol < maxCol - 1 && playerSlotRow * maxCol + (playerSlotCol + 1) < items.size()) playerSlotCol++;
        //     gp.keyH.rightPressed = false;
        // }
        // Navigasi kiri-kanan A / D
        if (gp.keyH.leftPressed) {
            if (playerSlotCol > 0) {
                playerSlotCol--;
            }
            gp.keyH.leftPressed = false;
        }

        // Navigasi ke kanan (D / panah kanan)
        if (gp.keyH.rightPressed) {
            if (playerSlotCol < maxCol - 1 &&
                playerSlotRow * maxCol + (playerSlotCol + 1) < items.size()) {
                playerSlotCol++;
            }
            gp.keyH.rightPressed = false;
        }


        // Jual saat ENTER
        if (gp.keyH.enterPressed) {
            int indexSell = playerSlotRow * maxCol + playerSlotCol;
            if (indexSell >= 0 && indexSell < items.size()) {
                Item item = items.get(indexSell);
                gp.player.gold += item.sellPrice;
                item.amount--;
                if (item.amount <= 0) {
                    gp.player.inventory.remove(item);

                    // Hitung ulang total item dan batasi index agar tidak out of bounds
                    int totalItem = gp.player.inventory.size();
                    int newIndex = playerSlotRow * 5 + playerSlotCol;
                    if (newIndex >= totalItem) {
                        playerSlotCol = 0;
                        playerSlotRow = 0;
                    }
                }

                showMessage("Sold 1x " + item.name + " for " + item.sellPrice + "G");
            }
            gp.keyH.enterPressed = false;
        }

        // ESC untuk kembali
        if (gp.keyH.escPressed) {
            subState = 0;
            playerSlotCol = 0;
            playerSlotRow = 0;
            gp.keyH.escPressed = false;
        }
    }




    public void trade_select() {
        int nameX = getXforCenteredText(currentDialogueName, 73, 286);
        int baseY = 100;
        int buttonWidth = 158;
        int buttonHeight = 50;
        int buttonSpacing = 10;

        // Load button images
        BufferedImage buyBtn = gp.setImage("/assets/ui/buy.png");
        BufferedImage sellBtn = gp.setImage("/assets/ui/sell.png");
        BufferedImage leaveBtn = gp.setImage("/assets/ui/leave.png");
        BufferedImage buyBtnActive = gp.setImage("/assets/ui/buyActive.png");
        BufferedImage sellBtnActive = gp.setImage("/assets/ui/sellActive.png");
        BufferedImage leaveBtnActive = gp.setImage("/assets/ui/leaveActive.png");

        // Draw buttons with active states
        if(merchantChoice == 0) {
            g2.drawImage(buyBtnActive, nameX, baseY, buttonWidth, buttonHeight, null);
        } else {
            g2.drawImage(buyBtn, nameX, baseY, buttonWidth, buttonHeight, null);
        }

        if(merchantChoice == 1) {
            g2.drawImage(sellBtnActive, nameX, baseY + buttonHeight + buttonSpacing, buttonWidth, buttonHeight, null);
        } else {
            g2.drawImage(sellBtn, nameX, baseY + buttonHeight + buttonSpacing, buttonWidth, buttonHeight, null);
        }

        if(merchantChoice == 2) {
            g2.drawImage(leaveBtnActive, nameX, baseY + (buttonHeight + buttonSpacing) * 2, buttonWidth, buttonHeight, null);
        } else {
            g2.drawImage(leaveBtn, nameX, baseY + (buttonHeight + buttonSpacing) * 2, buttonWidth, buttonHeight, null);
        }

        System.out.println("tssss");

        // Handle button selection with enter key
        if(gp.keyH.enterPressed) {
            // debug
            System.out.println("Merchant choice: " + merchantChoice);
            gp.playSFX(gp.sfx, 2); // Play selection sound            
            if(merchantChoice == 0) {
                subState = 2; // Switch to buy menu
                commandNum = 0; // Reset selection for buy menu
            }
            else if(merchantChoice == 1) {
                subState = 3; // Switch to sell menu
                commandNum = 0; // Reset selection for sell menu
            }
            else if(merchantChoice == 2) {
                currentDialogue = "Come again!";
                gp.gameState = gp.playState;
                subState = 0; // Reset menu state
            }
            gp.keyH.enterPressed = false;
        }
    }



    private ArrayList<Item> getShopItems() {
        ArrayList<Item> shopItems = new ArrayList<>();
        
        // Add items with their prices
        Item carrotSeed = new Item(gp, "carrotSeedBag", 2, "Contains seeds for growing orange root vegetables.", 6, 1, 20, 10);
        Item waterCan = new Item(gp, "wateringCan", 1, "Used to water crops. Remember to refill it regularly!", 180, 90);
        Item axeTool = new Item(gp, "axe", 1, "A sturdy tool for chopping trees and collecting wood.", 250, 125);
        shopItems.add(carrotSeed);
        shopItems.add(waterCan);
        shopItems.add(axeTool);
        
        return shopItems;
    }
      public void trade_buy() {
        System.out.println("aaaaaaaaaaa");
        // Window
        int x = gp.tileSize * 2;
        int y = gp.tileSize;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.screenHeight - (gp.tileSize * 2);
        drawSubWindow(x, y, width, height);

        // Title
        g2.setColor(new Color(94, 44, 19)); // Match dialog text color
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        String text = "Buy Items";
        int textX = getXforCenteredText(text);
        g2.drawString(text, textX, y + gp.tileSize);

        // Draw player's gold
        g2.setFont(g2.getFont().deriveFont(24F));
        g2.drawString("Your Gold: " + gp.player.gold + "G", x + 30, y + gp.tileSize * 2);

        // Get and display items
        ArrayList<Item> shopItems = getShopItems();
        int itemStartY = y + gp.tileSize * 3;
        int itemSpacing = 40;

        // Draw items with selection highlight
        for(int i = 0; i < shopItems.size(); i++) {
            Item item = shopItems.get(i);
            
            // Highlight selected item
            if(i == commandNum) {
                g2.setColor(new Color(249, 228, 183, 100)); // Light brown highlight
                g2.fillRect(x + 20, itemStartY + (i * itemSpacing) - 20, width - 40, 40);
            }

            
            // Draw item details
            drawItemBox(item.name, item.path, x + 30, itemStartY + (i * itemSpacing), item.buyPrice);
        }

        // Draw selected item description
        if(!shopItems.isEmpty() && commandNum < shopItems.size()) {
            Item selectedItem = shopItems.get(commandNum);
            g2.setColor(new Color(94, 44, 19));
            g2.setFont(g2.getFont().deriveFont(24F));
            int descY = y + height - gp.tileSize * 3;
            String tem = formatDialogText(selectedItem.description, width - 60);
            for (String line : tem.split("\n")) {
                g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 25F));
                g2.drawString(line, x + 10, descY);
                descY += 32;
            }

            // g2.drawString(tem, x + 30, descY);

            // Draw purchase instructions
            if(gp.player.gold >= selectedItem.buyPrice) {
                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24F));
                g2.drawString("[ENTER] Buy   [ESC] Back", x + 30, descY + 40);
            } else {
                g2.setColor(Color.RED);
                g2.drawString("Not enough gold!", x + 30, descY + 40);
            }
        }

        // Handle item selection
        if(gp.keyH.upPressed) {
            commandNum--;
            if(commandNum < 0) {
                commandNum = shopItems.size() - 1;
            }
            gp.playSFX(gp.sfx, 1);
            gp.keyH.upPressed = false;
        }

        if(gp.keyH.downPressed) {
            commandNum++;
            if(commandNum >= shopItems.size()) {
                commandNum = 0;
            }
            gp.playSFX(gp.sfx, 1);
            gp.keyH.downPressed = false;
        }

        // Handle purchase
        if(gp.keyH.enterPressed) {
            if(!shopItems.isEmpty() && commandNum < shopItems.size()) {
                Item selectedItem = shopItems.get(commandNum);
                if(gp.player.gold >= selectedItem.buyPrice) {
                    gp.player.gold -= selectedItem.buyPrice;
                    gp.player.addItemToInventory(selectedItem);
                    gp.playSFX(gp.sfx, 2);
                }
            }
            gp.keyH.enterPressed = false;
        }

        // Handle back
        if(gp.keyH.escPressed) {
            subState = 1; // Go back to trade select
            commandNum = 0; // Reset selection
            gp.keyH.escPressed = false;
        }
    }

    public void trade_sell() {
        System.out.println("masuk sell");
    // Window
    int x = gp.tileSize * 2;
    int y = gp.tileSize;
    int width = gp.screenWidth - (gp.tileSize * 4);
    int height = gp.screenHeight - (gp.tileSize * 2);
    drawSubWindow(x, y, width, height);

    g2.setColor(Color.white);
    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
    g2.drawString("Sell All Items", x + 20, y + gp.tileSize);

    // Calculate total sell price
    int totalSell = 0;
    for (Entity e : gp.player.inventory) {
    if (e instanceof Item) {
        Item item = (Item) e;
        totalSell += item.sellPrice * item.amount;
    }
}


    g2.setFont(g2.getFont().deriveFont(24F));
    g2.drawString("Total: " + totalSell + "G", x + 20, y + gp.tileSize * 2);

    g2.setFont(g2.getFont().deriveFont(20F));
    g2.drawString("[ENTER] Sell All   [ESC] Cancel", x + 20, y + height - gp.tileSize);

    // Handle input
    if (gp.keyH.enterPressed) {
        // Add gold and clear inventory
        gp.player.gold += totalSell;
        gp.player.inventory.clear();
        gp.playSFX(gp.sfx, 2);
        showMessage("You sold everything for " + totalSell + "G!");
        subState = 1; // go back to trade menu
        gp.keyH.enterPressed = false;
    }

    if (gp.keyH.escPressed) {
        subState = 1; // go back to trade menu
        gp.keyH.escPressed = false;
    }
}

    public void handleMerchantNavigation() {
        // Only handle navigation if we're talking to a merchant
        if (currentEntityDialogue != null && 
            currentEntityDialogue.getClass().getSimpleName().equals("Npc_Merchant")) {
    
            // Initialize merchant menu if not already initialized
            if(subState == 0) {
                subState = 1;
                merchantChoice = 0;
            }
        }
    }


        public String formatDialogText(String text, int maxWidth) {
        String[] words = text.split(" ");
        StringBuilder result = new StringBuilder();
        StringBuilder currentLine = new StringBuilder();
        
        for (String word : words) {
            if (currentLine.length() + word.length() + 1 <= maxWidth) {
                // Add to current line
                if (currentLine.length() > 0) {
                    currentLine.append(" ");
                }

                currentLine.append(word);
            } else {
                // Add current line to result and start a new line
                result.append(currentLine).append("\n");
                currentLine = new StringBuilder(word);
            }
        }
        
        // Add the last line
        if (currentLine.length() > 0) {
            result.append(currentLine);
        }
        
        return result.toString();
    }

    // public void drawItemBox(String title, String imagePath, int x, int y, int price) {
    //     final int BOX_WIDTH = 300;
    //     final int BOX_HEIGHT = 35;
        
    //     // Draw the box background
    //     g2.setColor(new Color(255, 250, 240)); // Cream/ivory color background
    //     g2.fillRect(x, y, BOX_WIDTH, BOX_HEIGHT);
        
    //     // Draw border
    //     g2.setColor(new Color(101, 67, 33)); // Dark brown border
    //     g2.setStroke(new BasicStroke(1));
    //     g2.drawRect(x, y, BOX_WIDTH, BOX_HEIGHT);
        
    //     // Draw item icon
    //     if (imagePath != null) {
    //         BufferedImage itemIcon = gp.setImage(imagePath);
    //         if (itemIcon != null) {
    //             g2.drawImage(itemIcon, x + 5, y + 2, 30, 30, null);
    //         }
    //     }
        
    //     // Draw title text
    //     g2.setColor(Color.black);
    //     g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 16F));
    //     g2.drawString(title, x + 40, y + 22);
        
    //     // Draw price if greater than 0
    //     if(price > 0) {
    //         String priceText = String.format("%,d", price);
            
    //         // Draw G icon
    //         BufferedImage gIcon = gp.setImage("/assets/ui/coinG.png");
    //         if (gIcon != null) {
    //             g2.drawImage(gIcon, x + BOX_WIDTH - 60, y + 7, 20, 20, null);
    //         }
            
    //         // Draw price text aligned to the right
    //         g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 16F));
    //         int textWidth = g2.getFontMetrics().stringWidth(priceText);
    //         g2.drawString(priceText, x + BOX_WIDTH - textWidth - 10, y + 22);
    //     }
    // }

    // Method untuk menampilkan list item
    public void drawItemList(ArrayList<Item> items, String title, int startX, int startY, boolean buy) {
        // Draw title first
        g2.setColor(Color.black);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F));
        g2.drawString(title, startX, startY - 10);
        
        // Draw items
        final int SPACING = 40; // Less spacing between items
        for(int i = 0; i < items.size(); i++) {
            Item item = ItemList.getItem(items.get(i).name); // Get item by name from ItemList
            
            int price = item.buyPrice;
            if(buy)
                price = item.buyPrice;

            drawItemBox(
                item.name,
                item.path,
                startX,
                startY + (i * SPACING),
                price

            );
        }
    }

    public void startTrading(Entity merchant) {
        currentEntityDialogue = merchant;
        gp.gameState = gp.dialogueState;
        if(merchant.getClass().getSimpleName().equals("Npc_Merchant")) {
            subState = 1; // Start with trade select menu
            commandNum = 0; // Reset selection
        }
    }

    private void drawItemBox(String name, String imagePath, int x, int y, int price) {
        // Draw item icon
        BufferedImage itemImage = gp.setImage(imagePath);
        if(itemImage != null) {
            g2.drawImage(itemImage, x, y - 15, 32, 32, null);
        }
        
        // Draw item name
        g2.setColor(new Color(94, 44, 19));
        g2.setFont(g2.getFont().deriveFont(24F));
        g2.drawString(name, x + 40, y + 10);
        
        // Draw price with G for gold
        String priceText = price + "G";
        int priceWidth = (int)g2.getFontMetrics().getStringBounds(priceText, g2).getWidth();
        g2.drawString(priceText, x + 450 - priceWidth, y + 10);
    }
}