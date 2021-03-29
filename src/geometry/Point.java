package geometry;

/**
 * The class that represents the point object.
 * each point has:
 * a x coordinate, and a y coordinate.
 *
 * @author Ilan Bitan
 */
public class Point {

    private double xVal;
    private double yVal;


    /**
     * a constructor.
     * @param x - the x coordinate.
     * @param y - the y coordinate.
     */
    public Point(double x, double y) {
        xVal = x;
        yVal = y;
    }

    /**
     * the method calculates the distance of this point to the other point.
     * @param other - a point.
     * @return the distance between two points.
     */
    public double distance(Point other) {
        double x2 = Math.pow((this.getX() - other.getX()), 2);
        double y2 = Math.pow((this.getY() - other.getY()), 2);
        return Math.sqrt(x2 + y2);
    }

    /**
     * the method checks if two points are equals.
     * @param other - a point.
     * @return true of the points are equal and false otherwise.
     */
    public boolean equals(Point other) {
        if (other.getX() != this.getX()) {
            return false;
        }
        if (other.getY() != this.getY()) {
            return false;
        }
        return true;
    }

    // Return the x and y values of this point
    /**
     * accessor to the x coordinate.
     * @return the point's x coordinate.
     */
    public double getX() {
        return this.xVal;
    }

    /**
     * accessor to the y coordinate.
     * @return the point's y coordinate.
     */
    public double getY() {
        return this.yVal;
    }
}
