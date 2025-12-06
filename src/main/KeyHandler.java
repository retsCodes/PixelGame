package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            System.out.println("Up Pressed");

        }
        if (code == KeyEvent.VK_A) {
            System.out.println("Left Pressed");

        }
        if (code == KeyEvent.VK_S) {
            System.out.println("Down Pressed");

        }
        if (code == KeyEvent.VK_D) {
            System.out.println("Right Pressed");

        }

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = true;

        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;

        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;

        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;

        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;

        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;

        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;

        }

    }

}
