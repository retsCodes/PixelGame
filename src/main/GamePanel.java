package main;

import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this,keyH);


    int currentFPS;
    String playerDirection;
    int lastPlayerX, lastPlayerY;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);

            lastTime = currentTime;
            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000) {
                currentFPS = drawCount;
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {

        lastPlayerX = player.x;
        lastPlayerY = player.y;

        player.update();

        if (player.x > lastPlayerX) {
            playerDirection = "Right";
        } else if (player.x < lastPlayerX) {
            playerDirection = "Left";
        } else if (player.y > lastPlayerY) {
            playerDirection = "Down";
        } else if (player.y < lastPlayerY) {
            playerDirection = "Up";
        } else {
            playerDirection = "Stationary";
        }

    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        player.draw(g2);


        // text info
        g2.setColor(Color.CYAN);
        g2.drawString("FPS: " + currentFPS,  10, 20);
        g2.drawString("X: " + player.x, 10, 40);
        g2.drawString("Y: " + player.y, 10, 60);
        g2.drawString("Direction: " + playerDirection, 10, 80);



        g2.dispose();
    }
}
