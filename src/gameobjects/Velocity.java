package gameobjects;
import geometry.Point;

/**
 * The following class is used to change the position of the x and y,
 * coordinates of a given object by adding a change to each axis accordingly.
 *
 * @author Ilan Bitan
 *
 */
public class Velocity {

    private double dxVal;
    private double dyVal;

    /**
     * constructor for the velocity change in each axis.
     * @param dx - change in x axis.
     * @param dy - change in y axis.
     */
    public Velocity(double dx, double dy) {
        dxVal = dx;
        dyVal = dy;
    }

    /**
     * the following method is a a static class which acts like a constructor.
     * @param angle - calculating the angle of the change.
     * @param speed - change in the direction according to angle.
     * @return the new velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(Math.toRadians(angle - 90));
        double dy = speed * Math.sin(Math.toRadians(angle - 90));
        return new Velocity(dx, dy);
     }

    /**
    * The following method calculates a point with position (x,y).
    * and return a new point with position (x+dx, y+dy).
    * @param changePoint - the point which we change its velocity.
    * @return newPoint - the new coordinates after the velocity change.
    */
    public Point applyToPoint(Point changePoint) {
        Point newPoint = new Point((changePoint.getX() + this.dxVal), (changePoint.getY() + this.dyVal));
        return newPoint;
    }

    /**
     * accessor to the dx.
     * @return the point's dx.
     */
    public double getDx() {
        return this.dxVal;
    }

    /**
     * accessor to the dy.
     * @return the point's dy.
     */
    public double getDy() {
        return this.dyVal;
    }

    /**
     * the following method calculates the speed from dx and dy.
     * @param dx - the change of speed in the x axis.
     * @param dy - the change of speed in the y axis.
     * @return speed - the new speed of the object.
     */
    public double fromdxdy(double dx, double dy) {
        double speed = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        return speed;
     }
}
