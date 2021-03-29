package backgrounds;

import java.awt.Color;

import biuoop.DrawSurface;
import gameobjects.Sprite;
import thegame.GameLevel;

/**
 * The following class is used to create the background
 * for the third level: Green3.
 * @author Ilan Bitan
 *
 */
public class Green3Background implements Sprite {

    @Override
    /**
     * the method draw the sprite to the screen.
     * In this case drawing the background for Green3.
     * @param d - the surface.
     */
    public void drawOn(DrawSurface d) {
        //create the green background
        Color color1 = new Color(56, 111, 0);
        d.setColor(color1);
        d.drawRectangle(10, 10, 780, 600);
        d.fillRectangle(10, 10, 780, 600);

        //creating the building
        d.setColor(Color.BLACK);
        d.fillRectangle(60, 450, 100, 150);

        d.setColor(Color.darkGray);
        d.fillRectangle(90, 385, 45, 65);

        Color color2 = new Color(110, 110, 110);
        d.setColor(color2);
        d.fillRectangle(107, 200, 10, 185);

        // creating building windows
        int y = 455;
        int x = 65;
        d.setColor(Color.WHITE);
        for (int i = 0; i < 5; i++) {
            y = 455;
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(x, y, 10, 20);
                y += 30;
            }
            x += 20;
        }
        // creating building anttena:
        d.setColor(Color.ORANGE);
        d.fillCircle(112, 200, 12);

        d.setColor(Color.RED);
        d.fillCircle(112, 200, 9);

        d.setColor(Color.WHITE);
        d.fillCircle(112, 200, 6);

    }



    @Override
    /**
    * the method notify the sprite that time has passed.
    */
    public void timePassed() {

    }

    /**
     * The following method is used in order to add the sprite.
     * object to the game.
     * @param game - the game level to which this background is added.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}