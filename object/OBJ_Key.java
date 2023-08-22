package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Key extends SuperObject {

    GamePanel gPanel;

public OBJ_Key(GamePanel gPanel){

    name = "Key";
    try {
        image = ImageIO.read(getClass().getResourceAsStream("/res/objects/key.png"));
        utilityTool.scaleImage(image, gPanel.tileSize, gPanel.tileSize);
         
    } catch (IOException e) {
        e.printStackTrace();
    }

}
    
}
