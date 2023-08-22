package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Boots extends SuperObject{
    
    GamePanel gPanel;

    public OBJ_Boots(GamePanel gPanel){

        name = "Boots";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/Boots.png"));
            utilityTool.scaleImage(image, gPanel.tileSize, gPanel.tileSize);

             
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    
    }
}
