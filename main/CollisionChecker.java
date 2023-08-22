package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gPanel;

    public CollisionChecker(GamePanel gPanel){
        this.gPanel = gPanel;
    }
    
    public int checkObj(Entity entity, boolean player){
        int index = 999;

        for (int i = 0; i < gPanel.obj.length; i++) {
            if(gPanel.obj[i] != null){
                //Get entity's collision area position
                entity.collisionArea.x = entity.worldX + entity.collisionArea.x;
                entity.collisionArea.y = entity.worldY + entity.collisionArea.y;

                //Get object's collision area position
                gPanel.obj[i].collisionArea.x = gPanel.obj[i].worldX + gPanel.obj[i].collisionArea.x;
                gPanel.obj[i].collisionArea.y = gPanel.obj[i].worldY + gPanel.obj[i].collisionArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.collisionArea.y -= entity.speed;
                        if(entity.collisionArea.intersects(gPanel.obj[i].collisionArea)){
                            if(gPanel.obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.collisionArea.y += entity.speed;
                        if(entity.collisionArea.intersects(gPanel.obj[i].collisionArea)){
                            if(gPanel.obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.collisionArea.x -= entity.speed;
                        if(entity.collisionArea.intersects(gPanel.obj[i].collisionArea)){
                            if(gPanel.obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.collisionArea.x += entity.speed;
                        if(entity.collisionArea.intersects(gPanel.obj[i].collisionArea)){
                            if(gPanel.obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;

                    default:
                        break;
                }
                entity.collisionArea.x = entity.collisionAreaDefaultX;
                entity.collisionArea.y = entity.collisionAreaDefaultY;
                gPanel.obj[i].collisionArea.x = gPanel.obj[i].collisionAreaDefaultX;
                gPanel.obj[i].collisionArea.y = gPanel.obj[i].collisionAreaDefaultY;
            }
            

        }

        return index;
    }

    public void checkTile(Entity entity){

        int entityLeftWorldX = entity.worldX + entity.collisionArea.x;
        int entityRighttWorldX = entity.worldX + entity.collisionArea.x + entity.collisionArea.width;
        int entityTopWorldY = entity.worldY + entity.collisionArea.y;
        int entityBottomWorldY = entity.worldY + entity.collisionArea.y + entity.collisionArea.height;

        int entityLeftCol = entityLeftWorldX/gPanel.tileSize;
        int entityRightCol = entityRighttWorldX/gPanel.tileSize;
        int entityTopRow = entityTopWorldY/gPanel.tileSize;
        int entityBottomRow = entityBottomWorldY/gPanel.tileSize;

        int tileNum1;
        int tileNum2;
        
        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gPanel.tileSize;
                tileNum1 = gPanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gPanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                if(gPanel.tileManager.tile[tileNum1].collision == true || gPanel.tileManager.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY - entity.speed)/gPanel.tileSize;
                tileNum1 = gPanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gPanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if(gPanel.tileManager.tile[tileNum1].collision == true || gPanel.tileManager.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gPanel.tileSize;
                tileNum1 = gPanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gPanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                if(gPanel.tileManager.tile[tileNum1].collision == true || gPanel.tileManager.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRighttWorldX + entity.speed)/gPanel.tileSize;
                tileNum1 = gPanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gPanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if(gPanel.tileManager.tile[tileNum1].collision == true || gPanel.tileManager.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
        
            default:
                break;
        }

    }
}
