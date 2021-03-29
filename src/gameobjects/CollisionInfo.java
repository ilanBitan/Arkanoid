package gameobjects;
import geometry.Point;

/**
 * The following class is used to hold the information of the
 * collision point and the closest collision object when called.
 *
 * @author Ilan Bitan
 *
 */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * constructor for CollisionInfo:
     * constructs an object that includes collisionPoint and collisionObject.
     * @param point - point of collision.
     * @param object - nearest object of collision.
     */
    public CollisionInfo(Point point, Collidable object) {
        this.collisionPoint = point;
        this.collisionObject = object;
    }

    /**
     * This method returns the informations of the point of collisions
     * that was set.
     * @return collisionPoint - the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * This method returns the informations
     * of the object of collisions that was set.
     * @return collisionObject - the closest object to collide with.
     */
    public Collidable collisionObject() {
        return collisionObject;
    }
}
