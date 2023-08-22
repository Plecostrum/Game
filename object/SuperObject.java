package object;

import main.GamePanel;
import main.UtilityTool;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle collisionArea = new Rectangle(0,0,48,48);
    public int collisionAreaDefaultX = 0;
    public int collisionAreaDefaultY = 0;
    UtilityTool utilityTool = new UtilityTool();


    public void draw(Graphics2D graphics2d, GamePanel gPanel){

            int screenX = worldX - gPanel.player.worldX + gPanel.player.screenX;
            int screenY = worldY - gPanel.player.worldY + gPanel.player.screenY;


            if (worldX + gPanel.tileSize > gPanel.player.worldX - gPanel.player.screenX &&
                worldX - gPanel.tileSize < gPanel.player.worldX + gPanel.player.screenX &&
                worldY + gPanel.tileSize > gPanel.player.worldY - gPanel.player.screenY &&
                worldY - gPanel.tileSize < gPanel.player.worldY + gPanel.player.screenY){

                graphics2d.drawImage(image, screenX , screenY, gPanel.tileSize, gPanel.tileSize, null);
            
            }

    }
    
    
}
