package io.codeforall.teamf;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Player implements KeyboardHandler {

    private Background background;
    private Bullet bullet = new Bullet(0,0, this, new Background());
    private String picRight = "resources/mario.png";
    private String picLeft = "resources/mario_reversed.png";

    private boolean lookingRight = true;
    private boolean isShooting = false;


    private Picture picture;
    private int speed = 20;

    public Player(Background background) {

        this.background = background;

        init();
    }

    private void init() {
        picture = new Picture(background.getX(), background.getMaxY(), picRight);
        picture.translate(0, -picture.getHeight());
        picture.draw();

        Keyboard keyboard = new Keyboard(this);

        KeyboardEvent pressedRight = new KeyboardEvent();
        pressedRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        pressedRight.setKey(KeyboardEvent.KEY_RIGHT);
        keyboard.addEventListener(pressedRight);

        KeyboardEvent pressedLeft = new KeyboardEvent();
        pressedLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        pressedLeft.setKey(KeyboardEvent.KEY_LEFT);
        keyboard.addEventListener(pressedLeft);


        KeyboardEvent pressedSpace = new KeyboardEvent();
        pressedSpace.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        pressedSpace.setKey(KeyboardEvent.KEY_SPACE);
        keyboard.addEventListener(pressedSpace);


    }

    private void moveRight() {

        if (picture.getMaxX() != background.getMaxX()){
            if (picture.getMaxX() + speed > background.getMaxX()) {
                picture.translate(background.getMaxX() - picture.getMaxX(), 0);
            } else {
                picture.translate(speed, 0);
            }
        }

        if (!lookingRight) {
            lookingRight = true;
            picture.load(picRight);
        }

    }

    private void moveLeft() {

        if (picture.getX() != background.getX()){
            if (picture.getX() - speed < background.getX()) {
                picture.translate(background.getX() - picture.getX(), 0);
            } else {
                picture.translate(-speed, 0);
            }
        }

        if (lookingRight) {
            lookingRight = false;
            picture.load(picLeft);
        }

    }

    private void shoot() {
        if (!isShooting) {
            bullet = new Bullet(picture.getX() + 20, picture.getY(), this, background);
            isShooting = true;
        }
    }

    public boolean isShooting() {
        return isShooting;
    }

    public void setShooting(boolean bool) {
        isShooting = bool;
    }

    public Bullet getBullet() {
        return bullet;
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {

            case KeyboardEvent.KEY_RIGHT:
                this.moveRight();
                break;

            case KeyboardEvent.KEY_LEFT:
                this.moveLeft();
                break;

            case KeyboardEvent.KEY_SPACE:
                this.shoot();
                isShooting = true;
                break;

        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
    }

}
