package backgrounds;

import java.awt.Color;

import biuoop.DrawSurface;
import gameobjects.Sprite;
import thegame.GameLevel;
/**
 * The following class is used to create the background.
 * for the first level: Direct Hit.
 * @author Ilan Bitan
 *
 */
public class DirectHitBackground implements Sprite {

    @Override
    /**
     * the method draw the sprite to the screen.
     * In this case drawing the background for Direct Hit.
     * @param d - the surface.
     */
    public void drawOn(DrawSurface d) {
        // background color for all screen
        d.setColor(Color.BLACK);
        d.drawRectangle(10, 10, 780, 600);
        d.fillRectangle(10, 10, 780, 600);

        // creation of the scope circles in blue
        d.setColor(Color.BLUE);
        d.drawCircle(400, 220, 175);

        d.setColor(Color.BLUE);
        d.drawCircle(400, 220, 135);

        d.setColor(Color.BLUE);
        d.drawCircle(400, 220, 90);

        // creation of the scope lines in blue
        d.drawLine(400, 450, 400, 270);
        d.drawLine(400, 0, 400, 170);
        d.drawLine(150, 220, 350, 220);
        d.drawLine(450, 220, 650, 220);

    }

    @Override
    /**
     * the method notify the sprite that time has passed.
     */
    public void timePassed() {

    }

    /**
     * The followng method is used in order to add the sprite
     * object to the game.
     * @param game - the game level to which this background is added
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }


}
