package backgrounds;

import java.awt.Color;

import biuoop.DrawSurface;
import gameobjects.Sprite;
import thegame.GameLevel;

/**
 * The following class is used to create the background
 * for the third level: WideEasy.
 * @author Ilan Bitan
 *
 */
public class WideEasyBackground implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {

        // creating the background for the level
        d.setColor(Color.WHITE);
        d.drawRectangle(10, 10, 780, 600);
        d.fillRectangle(10, 10, 780, 600);

        //creating the sun rays
        Color color1 = new Color(236, 236, 202);
        d.setColor(color1);

        for (int i = 10; i < 700; i += 3) {
            d.drawLine(100, 100, i, 250);
        }

        //creation of the sun:
        d.fillCircle(100, 100, 70);

        Color color2 = new Color(219, 219, 0);
        d.setColor(color2);
        d.fillCircle(100, 100, 60);

        d.setColor(Color.YELLOW);
        d.fillCircle(100, 100, 50);

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