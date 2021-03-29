package thegame;
import java.util.ArrayList;

import gameobjects.Collidable;
import gameobjects.CollisionInfo;
import geometry.Line;
import geometry.Point;

/**
 * The class that holds collidable objects. meaning:
 * the GameEnvironment contains a collection - ArrayList that
 * its purpose is to contain all of the objects that are collidable.
 *
 * @author Ilan Bitan
 *
 */
public class GameEnvironment {

    //an array list of all the objects the can be collided.
    private ArrayList<Collidable> environment;

    /**
     * a constructor.
     * initializes the environment ArrayList.
     */
    public GameEnvironment() {
        environment = new ArrayList<Collidable>();
    }

    /**
     * the method adds a given collidable to the environment.
     * @param c - a Collidable object.
     */
    public void addCollidable(Collidable c) {
        this.environment.add(c);
    }

    /**
     * the method removes a Collidable object from the array of Collidable objects.
     * @param c - the Collidable object
     */
    public void removeCollidableObject(Collidable c) {
        this.environment.remove(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     * @param trajectory - a line.
     * @return CollisionInfo c - the information about the collision.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {

        Point min = null;
        Point temp = null;
        double distance1;
        double distance2;
        int objectIndex = 0;

        // go over the objects in environment, and check if they might collide with the line
        for (int i = 0; i < this.environment.size(); i++) {
            // get the intersection point of the current object
            Collidable rectemp = this.environment.get(i);
            temp = trajectory.closestIntersectionToStartOfLine(rectemp.getCollisionRectangle());

            // check which point is the closest to the moving object
            if (temp != null && min != null) {
                distance1 = min.distance(trajectory.start());
                distance2 = temp.distance(trajectory.start());

                // the new point is closest to the object
                if (distance2 < distance1) {
                    min = temp;
                    objectIndex = i;
                }

            // temp is the only point that can be collided with
            } else if (temp != null && min == null) {
                min = temp;
                objectIndex = i;
            }
        }

        // there was no object that can be collided with
        if (min == null) {
            return null;
        }
            //create the CollisionInfo object
            CollisionInfo c = new CollisionInfo(min, this.environment.get(objectIndex));
            return c;
    }

    /**
     * a getter for the game environment array.
     * @return - environment.
     */
    public ArrayList getEnvironment() {
        return this.environment;
    }

}
