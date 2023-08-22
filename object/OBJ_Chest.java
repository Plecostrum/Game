package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Chest extends SuperObject{

    GamePanel gPanel;
    
    public OBJ_Chest(GamePanel gPanel){

        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/Chest.png"));
            utilityTool.scaleImage(image, gPanel.tileSize, gPanel.tileSize);

             
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
}
