package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Door extends SuperObject{
    
    GamePanel gGPanel;

    public OBJ_Door(GamePanel gPanel){

        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/Door.png"));
            utilityTool.scaleImage(image, gPanel.tileSize, gPanel.tileSize);

             
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    
    }
}
