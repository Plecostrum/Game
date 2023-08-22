package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.nio.file.WatchService;

import object.OBJ_Key;

public class UI {

    GamePanel gPanel;
    Font arial_40;
    BufferedImage keyImage;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false; 

    public UI( GamePanel gPanel){
        this.gPanel = gPanel;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        

        OBJ_Key key = new OBJ_Key(gPanel);
        keyImage = key.image;
    }
    
    public void displayMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D graphics2d){

        if (gameFinished) {

            graphics2d.setFont(arial_40);
            graphics2d.setColor(Color.white);

            String text;
            int textLength;
            int x, y;

            text = "You found the treasure!";
            textLength = (int)graphics2d.getFontMetrics().getStringBounds(text, graphics2d).getWidth();
            
            x = gPanel.screenWidth/2 - textLength/2;
            y = gPanel.screenHeight/2 - (gPanel.tileSize)*2;

            gPanel.gameThread = null;

            graphics2d.drawString(text, x, y);
        }
        else {

        

            graphics2d.setFont(arial_40);
            graphics2d.setColor(Color.white);
            graphics2d.drawImage(keyImage, gPanel.tileSize/2, gPanel.tileSize/2, gPanel.tileSize, gPanel.tileSize, null);;
            graphics2d.drawString("x " + gPanel.player.hasKey, 74,65);

            if (messageOn) {
                graphics2d.setFont(graphics2d.getFont().deriveFont(30F));
                graphics2d.drawString(message, gPanel.tileSize/2, gPanel.tileSize * 5);
                messageCounter++;

                if(messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }

        }
    }

}
