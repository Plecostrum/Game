package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Currency;

import javax.sound.midi.SysexMessage;
import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
    
    //SCREEN SETTINGS 
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; //48*48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; //768px
    public final int screenHeight = tileSize * maxScreenRow; //576px


    //WORLD SETTINGS
    public final  int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    //FPS
    int FPS = 60;

    //SYSTEM
    KeyHandler kHandler = new KeyHandler();
    Thread gameThread; 
    Sound music = new Sound();
    Sound FX = new Sound();


    TileManager tileManager = new TileManager(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public UI ui = new UI(this);

    //ENTITY AND OBJECT
    public Player player = new Player(this,kHandler);
    public SuperObject obj[] = new SuperObject[10];


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(kHandler);
        this.setFocusable(true);
    }

    public void setUpGame(){
        assetSetter.setObject();
        playMusic(0);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS; //0.01666 Seconds Intervals
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount =0;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer > 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer =0; 
            }
        }


    }

    public void update(){
        
        player.update();


    }

    //Draw graphics
    public void paintComponent(Graphics graphics){

        super.paintComponent(graphics);

        Graphics2D graphics2d = (Graphics2D)graphics;

        //DEBUG
        long drawStart = 0;
        if(kHandler.checkDrawTime){
            drawStart = System.nanoTime();
        }

        //TILE
        tileManager.draw(graphics2d);

        //OBJECT
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) { //Avoid null pointer error
                obj[i].draw(graphics2d, this);
                
            }
        }
        //PLAYER
        player.draw(graphics2d);
        
        //UI
        ui.draw(graphics2d);

        //DEBUG
        if(kHandler.checkDrawTime){
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            graphics2d.setColor(Color.white);
            graphics2d.drawString("Draw Time; " + passed , 10, 400);
            System.out.println("Draw Time; " + passed);
        }
         

        graphics2d.dispose();

    }


    //MUSIC
    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }
    public void playFX(int i){
        FX.setFile(i);
        FX.play();
    }
}
