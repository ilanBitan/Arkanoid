package thegame;

import biuoop.DrawSurface;
import gameobjects.Sprite;
import geometry.Point;
import geometry.Rectangle;

/**
 * The following class is used as an indeicator for the amount of
 * lives in the gui of the game.
 * @author Ilan Bitan
 *
 */
public class LivesIndicator implements Sprite {
    private Counter currentLives;

    /**
     * The following is the constructor for the level indicator class.
     * @param lives - counter that holds the amount of lives player has.
     */
    public LivesIndicator(Counter lives) {
        this.currentLives = lives;
    }

    @Override
    /**
     * the method draws the block on the blocks DrawSurface.
     * This block is used for displaying the current amount of lives.
     * if the surface was not given, the surface is the default one.
     * @param surface - the surface.
     */
    public void drawOn(DrawSurface surface) {
        String text;
        int x;
        int y;
        // rectangle that will hold the level indicator.
        Rectangle rect = new Rectangle(new Point(0, 0), 800, 30);
        // text that'll include the score of the player.
        text = "Lives: " + Integer.toString(this.currentLives.getValue());

        // the x coordinate of the text
        x = (int) (rect.getUpperLeft().getX() + 100);

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
     * the method adds the life counter to the game.
     * @param g - the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
