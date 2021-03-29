package thegame;

import biuoop.DrawSurface;
import gameobjects.Sprite;
import geometry.Point;
import geometry.Rectangle;

/**
 * The following class is used as an indicator for the player score.
 * @author Ilan Bitan
 *
 */
public class ScoreIndicator implements Sprite {
    private Counter currentScore;

    /**
     * The following is the constructor for the level indicator class.
     * @param score - counter that holds the player score.
     */
    public ScoreIndicator(Counter score) {
        this.currentScore = score;
    }

    @Override
    /**
     * the method draws the block on the blocks DrawSurface.
     * This block is used for displaying the player score.
     * if the surface was not given, the surface is the default one.
     * @param surface - the surface.
     */
    public void drawOn(DrawSurface surface) {
        String text;
        int x;
        int y;
        // rectangle that will hold the level indicator.
        Rectangle rect = new Rectangle(new Point(0, 0), 800, 30);

        surface.setColor(java.awt.Color.orange);

        //fill the rectangle with its color
        surface.fillRectangle((int) rect.getUpperLeft().getX(),
                (int) rect.getUpperLeft().getY(), (int) rect.getWidth(),
                (int) rect.getHeight());

        // set the color to black
        surface.setColor(java.awt.Color.BLACK);

        // frame the rectangle
        surface.drawRectangle((int) rect.getUpperLeft().getX(),
                (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());

        // text that'll include the score of the player.
        text = "Score: " + Integer.toString(this.currentScore.getValue());

        // the x coordinate of the text
        x = (int) ((rect.getUpperLeft().getX()
                + rect.getUpperRight().getX()) / 2 - 50);

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
     * the method adds the score indicator to the game.
     * @param g - the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
