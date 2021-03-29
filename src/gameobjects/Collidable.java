package gameobjects;
import geometry.Point;
import geometry.Rectangle;

/**
 * The following interface is used for all collidable objects in the game.
 * it is used in order to know each objects location, size and what happens
 * once a collision occurs.
 *
 * @author Ilan Bitan
 *
 */
public interface Collidable {

    /**
     * Return the "collision shape" of the object.
     * @return object - the object shape we collided with.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * @param collisionPoint - point that collisions occurs.
     * @param currentVelocity - velocity prior collision.
     * @param hitter - the hitting ball.
     * @return newVelocity - calculated velocity according to calculations.
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter);
}