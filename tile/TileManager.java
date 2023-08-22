package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {
    
    GamePanel gPanel;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gPanel) {

        this.gPanel = gPanel;

        tile = new Tile[10];
        mapTileNum = new int[gPanel.maxWorldCol][gPanel.maxWorldRow];

        getTileImage();
        loadMap("/res/maps/world01.txt");

    }

    public void getTileImage(){

        setup(0, "grass01", false);
        setup(1, "wall", true);
        setup(2, "water", true);
        setup(3, "road00", false);
        setup(4, "tree", true);
        setup(5, "sand", false);

    }

public void setup( int index, String imageName, boolean collision) {

    UtilityTool utilityTool = new UtilityTool();

    try {
        tile[index] = new Tile();
        tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/" + imageName + ".png"));
        tile[index].image = utilityTool.scaleImage(tile[index].image, gPanel.tileSize, gPanel.tileSize);
        tile[index].collision = collision; 


    } catch (IOException e) {
        e.printStackTrace();
    }

}

public void loadMap(String mapFile){

    try {
        
        InputStream iStream = getClass().getResourceAsStream(mapFile);
        BufferedReader bReader = new BufferedReader(new InputStreamReader(iStream));

        int col = 0;
        int row =0;

        while (col < gPanel.maxWorldCol && row < gPanel.maxWorldRow) {

            String line = bReader.readLine();

            while (col < gPanel.maxWorldCol) {

                String numbers[] = line.split(" ");

                int num = Integer.parseInt(numbers[col]);

                mapTileNum[col][row] = num;
                col++;
            }
            if(col == gPanel.maxWorldCol){
                col = 0;
                row++;
            }
            
        }
        bReader.close(); 

    } catch (Exception e) {
        // TODO: handle exception
    }

}

    public void draw(Graphics2D graphics2d){

        int worldCol = 0;
        int worldRow = 0;


        while (worldCol < gPanel.maxWorldCol && worldRow < gPanel.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gPanel.tileSize;
            int worldY = worldRow * gPanel.tileSize;
            int screenX = worldX - gPanel.player.worldX + gPanel.player.screenX;
            int screenY = worldY - gPanel.player.worldY + gPanel.player.screenY;


            if (worldX + gPanel.tileSize > gPanel.player.worldX - gPanel.player.screenX &&
                worldX - gPanel.tileSize < gPanel.player.worldX + gPanel.player.screenX &&
                worldY + gPanel.tileSize > gPanel.player.worldY - gPanel.player.screenY &&
                worldY - gPanel.tileSize < gPanel.player.worldY + gPanel.player.screenY){

                graphics2d.drawImage(tile[tileNum].image, screenX , screenY, null);
            
            }
            worldCol++; 

            if(worldCol == gPanel.maxWorldCol){
                worldCol = 0;
                worldRow++;

            }
            
        }
    }

}
