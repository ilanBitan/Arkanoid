package gameobjects;
import java.util.ArrayList;

import biuoop.DrawSurface;

/**
 * The following class is used to hold a collection of sprites.
 * sprites are game objects that can be drawn on the screen,
 * and can be notified that time has passed.
 * This include: the ball, blocks and paddle of the game.
 *
 * @author Ilan Bitan
 *
 */
public class SpriteCollection {

    //an array list of all the sprite objects
    private ArrayList<Sprite> collectionOfSprite;

    /**
     * constructor of the sprite collection.
     * which creates an array list of sprites.
     */
    public SpriteCollection() {
        collectionOfSprite = new ArrayList<Sprite>();
    }
    /**
     * the method adds a Sprite object to the array of sprite objects.
     * @param s - the Sprite object
     */
    public void addSprite(Sprite s) {
        this.collectionOfSprite.add(s);
    }

    //DONE ###################################
    /**
     * the method removes a Sprite object from the array of sprite objects.
     * @param s - the Sprite object
     */
    public void removeSpriteObject(Sprite s) {
        this.collectionOfSprite.remove(s);
    }

    /**
     * the method call timePassed() on all Sprite objects
     * in the collectionOfSprite array.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < this.collectionOfSprite.size(); i++) {
            this.collectionOfSprite.get(i).timePassed();
        }
    }

    /**
     * this method calls drawOn for all sprite objects.
     * in the collectionOfSprite Array.
     * @param d - surface to be drawn.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < this.collectionOfSprite.size(); i++) {
            this.collectionOfSprite.get(i).drawOn(d);
        }
    }

}