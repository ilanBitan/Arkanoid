package gameobjects;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import listener.HitListener;
import listener.HitNotifier;
import thegame.GameLevel;

/**
 * The following Class Block which represents and handles the collidable shapes,
 * and the velocity changes drived from the collisions with them.
 *
 * @author Ilan Bitan
 *
 */
public class Block implements Sprite, Collidable, HitNotifier {

    private Rectangle rect;
    private java.awt.Color rectColor;
    private int numOfHit;
    private List<HitListener> hitListeners;

    /**
     * The following constructor create a block which is a rectangle with color,
     * and the ability to be hit.
     * @param rect1 - the rectangle information for block.
     * @param color - given color for the block.
     */
    public Block(Rectangle rect1, java.awt.Color color) {
        rectColor = color;
        rect = rect1;
        numOfHit = 0;
        hitListeners = new ArrayList<HitListener>();
    }

    /**
     * a second constructor.
     * the constructor gets the rectangle, the color and the number
     * of hits the block can be hit.
     * @param rect1 - the rectangle information for block.
     * @param color - given color for the block.
     * @param hitNum - number of hits the block can receive.
     */
    public Block(Rectangle rect1, java.awt.Color color, int hitNum) {
        rectColor = color;
        rect = rect1;
        numOfHit = hitNum;
        hitListeners = new ArrayList<HitListener>();
    }

    /**
     * the following method returns the "collision shape" of the object.
     * @return rect - the rectangle shape we collided with.
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * accessor to the color of the block.
     * @return rectColor - the blocks color.
     */
    public java.awt.Color getColor() {
        return rectColor;
    }

    /**
     * the method draws the block on the blocks DrawSurface.
     * if the surface was not given, the surface is the default one.
     * @param surface - the surface.
     */
    public void drawOn(DrawSurface surface) {
        String text;
        int x;
        int y;

        // set the color of the rectangle
        surface.setColor(this.getColor());

        //fill the rectangle with its color
        surface.fillRectangle((int) this.rect.getUpperLeft().getX(),
                (int) this.rect.getUpperLeft().getY(), (int) this.rect.getWidth(),
                (int) this.rect.getHeight());

        // set the color to black
        surface.setColor(java.awt.Color.BLACK);

        // frame the rectangle
        surface.drawRectangle((int) this.rect.getUpperLeft().getX(),
                (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());

        /*
         * if the number of hits of the rectangle are 0 or below,
         * an X should be drawn on the rectangle.
         * else, the number of hits should be drawn on the rectangle.
         *
        if (this.numOfHit == 0) {
            text = "x";
        } else if (this.numOfHit > 0) {
            // parse the number of hits from int to string.
            text = Integer.toString(this.numOfHit);
        } else {
            return;
        }

        // the x coordinate of the text
        x = (int) ((this.rect.getUpperLeft().getX()
                + this.rect.getUpperRight().getX()) / 2);

        // the y coordinate of the text
        y = (int) ((this.rect.getUpperLeft().getY()
                + this.rect.getLowerLeft().getY()) / 2);

        // set the text color to white
        surface.setColor(java.awt.Color.WHITE);

        // draw the number of hits
        surface.drawText(x, y, text, 10);
        */
    }

    /**
     * This method notifies the sprite how much time has passed for block.
     */
    public void timePassed() {

    }

    /**
     * The following method checks if we have a collision with object and
     * changes the velocity according to hit made.
     * @param collisionPoint - point of collision with object.
     * @param currentVelocity - velocity at given position.
     * @param hitter - the hitting ball.
     * @return newVelocity - the changed velocity after the hit was made.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {
        Velocity newVelocity = currentVelocity;
        // hit was made on the corner of rectangle
        Point lL = rect.getLowerLeft();
        Point lR = rect.getLowerRight();
        Point uL = rect.getUpperLeft();
        Point uR = rect.getUpperRight();
        double x = collisionPoint.getX();
        double y = collisionPoint.getY();

        // a hit accord in the lowerLeft point
        if (x == lL.getX() && y == lL.getY()) {
            // set dx and dy in opposite direction
            newVelocity = new Velocity(-(currentVelocity.getDx()),
                    (-currentVelocity.getDy()));

        // a hit accord in the lowerRight point
        } else if (x == lR.getX() && y == lR.getY()) {
            newVelocity = new Velocity(-(currentVelocity.getDx()),
                    (-currentVelocity.getDy()));

        // a hit accord in the upperLeft point
        } else if (x == uL.getX() && y == uL.getY()) {
            newVelocity = new Velocity(-(currentVelocity.getDx()),
                    (-currentVelocity.getDy()));

        // a hit accord in the upperRight point
        } else if (x == uR.getX() && y == uR.getY()) {
            newVelocity = new Velocity(-(currentVelocity.getDx()),
                    (-currentVelocity.getDy()));

        // hit was made on right side
        } else if ((uR.getX() == x)
                && (uR.getY() < y)
                && (lR.getY() > y)) {
            // set dx in opposite direction
            newVelocity = new Velocity(-(currentVelocity.getDx()), currentVelocity.getDy());

        // hit was made on the top part of rectangle
        } else if ((uR.getY() == y)
                && (uR.getX() > x)
                && (uL.getX() < x)) {
            // set dy in opposite direction
            newVelocity = new Velocity(currentVelocity.getDx(), (-currentVelocity.getDy()));

        // hit was made on the bottom part of rectangle
        } else if ((lR.getY() == y)
                && (lR.getX() > x)
                && (lL.getX() < x)) {
            // set dy in opposite direction
            newVelocity = new Velocity(currentVelocity.getDx(), (-currentVelocity.getDy()));

        // hit was made on left side of rectangle
        } else if ((uL.getX() == x)
                && (uL.getY() < y)
                && (lL.getY() > y)) {
            // set dx in opposite direction
            newVelocity = new Velocity(-(currentVelocity.getDx()), currentVelocity.getDy());
        }

        this.notifyHit(hitter);
        this.numOfHits();
        return newVelocity;
    }

    /**
     * this method adds the block to the collidable and the
     * sprite array.
     * @param g - the current game.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable((Collidable) this);
        g.addSprite((Sprite) this);
    }

    /**
     * the method decreases the number of times the block
     * had been hit.
     */
    public void numOfHits() {
        if (this.numOfHit > 0) {
            this.numOfHit -= 1;
        }
    }
    /**
     * the method removes the block from the game.
     * @param game - the current game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    // Add hl as a listener to hit events.
    /**
     * the method adds the hl listener to the hitListener array.
     * @param hl - the listener.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * the method Removes hl from the list of hitListeners.
     * @param hl - the listener.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * the method notifies the hitListeners when a hit occurred.
     * @param hitter - the hitting ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
           hl.hitEvent(this, hitter);
        }
     }

    /**
     * a getter to the number of hits of the current block.
     * @return numOfHit - the number of hits.
     */
    public int getNumOfHit() {
        return this.numOfHit;
    }

    /**
     * a setter for the number of hits of a block.
     * @param num - the new number of hits.
     */
    public void setNumOfhit(int num) {
        this.numOfHit = num;
    }

}