package gameobjects;
import biuoop.DrawSurface;
import thegame.GameLevel;

/**
 * The sprite interface.
 * the classes that implements this interface must
 * contain the drawOn method and the timePassed method.
 *
 * @author Ilan Bitan
 *
 */
public interface Sprite {

    /**
     * the method draw the sprite to the screen.
     * @param d - the surface.
     */
    void drawOn(DrawSurface d);

    /**
     * the method notify the sprite that time has passed.
     */
    void timePassed();
    /**
     * the method adds sprite to game.
     * @param game - the current game.
     */
    void addToGame(GameLevel game);
}
