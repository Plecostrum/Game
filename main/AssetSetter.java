package main;

import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {
    
    GamePanel gPanel;

    public AssetSetter(GamePanel gPanel){
        this.gPanel = gPanel;
    }

    public void setObject(){

        gPanel.obj[0] = new OBJ_Key(gPanel);
        gPanel.obj[0].worldX = 23 * gPanel.tileSize;
        gPanel.obj[0].worldY = 7 * gPanel.tileSize;

        gPanel.obj[1] = new OBJ_Key(gPanel);
        gPanel.obj[1].worldX = 23 * gPanel.tileSize;
        gPanel.obj[1].worldY = 40 * gPanel.tileSize;
        
        gPanel.obj[2] = new OBJ_Key(gPanel);
        gPanel.obj[2].worldX = 38 * gPanel.tileSize;
        gPanel.obj[2].worldY = 8 * gPanel.tileSize;

        gPanel.obj[3] = new OBJ_Door(gPanel);
        gPanel.obj[3].worldX = 10 * gPanel.tileSize;
        gPanel.obj[3].worldY = 11 * gPanel.tileSize;

        gPanel.obj[4] = new OBJ_Door(gPanel);
        gPanel.obj[4].worldX = 8 * gPanel.tileSize;
        gPanel.obj[4].worldY = 28 * gPanel.tileSize;

        gPanel.obj[5] = new OBJ_Door(gPanel);
        gPanel.obj[5].worldX = 12 * gPanel.tileSize;
        gPanel.obj[5].worldY = 22 * gPanel.tileSize;

        gPanel.obj[6] = new OBJ_Chest(gPanel);
        gPanel.obj[6].worldX = 10 * gPanel.tileSize;
        gPanel.obj[6].worldY = 7 * gPanel.tileSize;
        
        gPanel.obj[7] = new OBJ_Boots(gPanel);
        gPanel.obj[7].worldX = 37 * gPanel.tileSize;
        gPanel.obj[7].worldY = 42 * gPanel.tileSize;
    }

}
