package entity;

import main.KeyHandler;
import main.UtilityTool;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.Year;
import java.time.YearMonth;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Player extends Entity {
    
    GamePanel gPanel;
    KeyHandler kHandler;

    public final int screenX;
    public final int screenY;
    
    public int hasKey = 0;

    //MOVING TILE
    boolean moving = false;
    int pixelCounter = 0;

    //Animation Control
    int standCounter =0;

    public Player(GamePanel gPanel, KeyHandler kHandler) {
        this.gPanel = gPanel;
        this.kHandler = kHandler;

        screenX = gPanel.screenWidth/2 - gPanel.tileSize/2;
        screenY = gPanel.screenHeight/2 - gPanel.tileSize/2;

        collisionArea = new Rectangle();
        collisionArea.x = 1;
        collisionArea.y = 1;
        collisionArea.width = 46;
        collisionArea.height = 46;

        collisionAreaDefaultX = collisionArea.x;
        collisionAreaDefaultY = collisionArea.y;

        setDefaultValues();
        getPlayerImage();
    }
    
    public void setDefaultValues(){
        worldX = gPanel.tileSize *23;
        worldY = gPanel.tileSize *21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){         

            up1 = setup("boy_up_1");
            up2 = setup("boy_up_2");
            down1 = setup("boy_down_1");
            down2 = setup("boy_down_2");
            left1 = setup("boy_left_1");
            left2 = setup("boy_left_2");
            right1 = setup("boy_right_1");
            right2 = setup("boy_right_2");

            System.out.println("Image loading ended");

    }

    public BufferedImage setup(String imageName){
        
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/player/" + imageName +".png"));
            image = utilityTool.scaleImage(image, gPanel.tileSize, gPanel.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void update(){

        if(moving == false){
            if(kHandler.upPressed == true || kHandler.downPressed == true || kHandler.leftPressed == true || kHandler.rightPressed == true){

                if(kHandler.upPressed == true){
                    direction = "up";
                    
                }
                else if(kHandler.downPressed == true){
                    direction = "down";
                    
                }
                else if(kHandler.leftPressed == true){
                    direction = "left";
                    
                }
                else if(kHandler.rightPressed == true){
                    direction = "right";
                    
                }

                moving = true;

                //CHECK TILE COLLISION
                collisionOn = false;
                gPanel.collisionChecker.checkTile(this);

                //CHECK OBJECT COLLISION
                int objIndex = gPanel.collisionChecker.checkObj(this, true);
                pickUpObject(objIndex);  
            }
            else {
                standCounter++;

                if (standCounter == 12) {
                    spriteNum = 1;
                    standCounter = 0;
                }
            }
        }
        if(moving == true){
            //IF COLLISION IS FALSE PLAYER CAN MOVE
            if(collisionOn == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                    default:
                        break;
                }
            }

            //ANIMATION
            spriteCounter++;
            if(spriteCounter > 6){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

            pixelCounter += speed;

            if (pixelCounter == 48) {
                moving = false;
                pixelCounter = 0;
            }

        }
          
    }

    public void pickUpObject(int index){

        if (index != 999) {
            
            String objectName = gPanel.obj[index].name;
            switch (objectName) {
                case "Key":
                    hasKey++;
                    gPanel.obj[index] = null;
                    gPanel.playFX(1);
                    
                    gPanel.ui.displayMessage("You've found a key");
                    break;
                case "Door":
                    if (hasKey > 0) {
                        gPanel.obj[index] = null;
                        hasKey--;
                        gPanel.playFX(4);

                        gPanel.ui.displayMessage("You've opened a door");
                    }
                    else {
                    gPanel.ui.displayMessage("You need a key.");
                    }
                    break;
                case "Boots":
                        speed += 2;
                        gPanel.obj[index] = null;
                        gPanel.playFX(3);
                        gPanel.ui.displayMessage("Power UP: Speed up");

                    break;
                case "Chest":
                    gPanel.ui.gameFinished = true;
                    gPanel.stopMusic();
                    gPanel.playFX(2);

                    break;
            
                default:
                    break;
            }

        }
    }

    public void draw(Graphics2D graphics2d){
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }

                break;
        
            default:
                break;
        }
        graphics2d.drawImage(image, screenX, screenY, null);

        //DEBUG DISPLAY HITBOT OF PLAYER
        graphics2d.drawRect(screenX + collisionArea.x, screenY + collisionArea.y, collisionArea.width,collisionArea.height);
        
    }
}
