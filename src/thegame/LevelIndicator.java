package thegame;

import biuoop.DrawSurface;
import gameobjects.Sprite;
import geometry.Point;
import geometry.Rectangle;
import levels.LevelInformation;

/**
 * The following class is used as an indeicator for the current
 * level in gui of the game.
 * @author Ilan Bitan
 *
 */
public class LevelIndicator implements Sprite {
    private LevelInformation currentLevel;
    /**
     * The following is the constructor for the level indicator class.
     * @param info - holds information for the current level.
     */
    public LevelIndicator(LevelInformation info) {
        this.currentLevel = info;
    }

    @Override
    /**
     * the method draws the block on the blocks DrawSurface.
     * This block is used for displaying the current level.
     * if the surface was not given, the surface is the default one.
     * @param surface - the surface.
     */
    public void drawOn(DrawSurface surface) {
        String text;
        int x;
        int y;
        // rectangle that will hold the level indicator.
        Rectangle rect = new Rectangle(new Point(0, 0), 800, 30);

        // text that'll include the current level name.
        text = "Level Name: " + this.currentLevel.levelName();
        // the x coordinate of the text
        x = (int) ((rect.getUpperLeft().getX()
                + rect.getUpperRight().getX())  - 200);

        // the y coordinate of the text
        y = (int) ((rect.getUpperLeft().getY()
                + rect.getLowerLeft().getY()) / 2);

        // set the text color to white
        surface.setColor(java.awt.Color.BLACK);

        // draw the number of hits
        surface.drawText(x, y, text, 15);
    }

    @Override
    /**
     * the method notify the sprite that time has passed.
     */
    public void timePassed() {

    }

    /**
     * the method adds the level counter to the game.
     * @param g - the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
