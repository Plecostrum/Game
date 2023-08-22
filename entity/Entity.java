package entity;
 /* 
 *
 *  Store Variable that will be used to in player, NPC and monster classes.
 * 
 */

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {

    //Location
    public int worldX,worldY;
    public int speed;

    //Sprites
    public BufferedImage up1, up2, down1, down2 , left1, left2, right1, right2;
    public String direction;

    //Animation
    public int spriteCounter = 0;
    public int spriteNum = 1;

    //colision
    public Rectangle collisionArea;
    public int collisionAreaDefaultX, collisionAreaDefaultY;
    public boolean collisionOn = false;
    
}
