package geometry;
import java.util.ArrayList;

/**
 * This class is used to create and handle lines.
 *
 * @author Ilan Bitan
 *
 */
public class Line {

    private Point lineEnd;
    private Point lineStart;
    // coordinates of lineStart
    private double pointStartX;
    private double pointStartY;
    // coordinates of lineEnd
    private double pointEndX;
    private double pointEndY;

    /**
     * Constructor of new line, which has a start and end points.
     * @param start - start point of line.
     * @param end - end point of line.
     */

    public Line(Point start, Point end) {
        this.lineStart = start;
        this.lineEnd = end;
        this.pointStartX = start.getX();
        this.pointStartY = start.getY();
        this.pointEndX = end.getX();
        this.pointEndY = end.getY();
    }

    /**
     * Constructor of new line, which has coordinates for each start
     * and end point.
     * @param x1 - x coordinates of start point of line
     * @param y1 - y coordinates of start point of line
     * @param x2 - x coordinates of end point of line
     * @param y2 - y coordinates of end point of line
     */

    public Line(double x1, double y1, double x2, double y2) {
        lineStart = new Point(x1, y1);
        lineEnd = new Point(x2, y2);
        this.pointStartX = x1;
        this.pointStartY = y1;
        this.pointEndX = x2;
        this.pointEndY = y2;
    }


    /**
     * The following method returns the length of the line.
     * @return lineStart.distance(lineEnd) - length of a given line.
     */
    public double length() {
        return lineStart.distance(lineEnd);
    }


    /**
     * The following method returns the middle point of a given line.
     * @return middle - mid point of given line.
     */
    public Point middle() {
        double x = (this.lineStart.getX() + this.lineEnd.getX()) / 2;
        double y = (this.lineStart.getY() + this.lineEnd.getY()) / 2;
        Point middle = new Point(x, y);
        return middle;
    }


    /**
     * The following method returns the start point of a given line.
     * @return this.lineStart - start point of given line.
     */
    public Point start() {
        return this.lineStart;
    }


    /**
     * The following method returns the end point of a given line.
     * @return this.lineEnd - end point of a given line.
     */
    public Point end() {
        return this.lineEnd;
    }


    /**
     * The following method returns true if the lines intersect,
     * false otherwise.
     * @param other - a second line which is checked with given line.
     * @return true - if they intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        double x = 0;
        double y = 0;
        //find the m - the slope of the lines
        double m1 = slope(this);
        double m2 = slope(other);

        //find the b of the equation y = mx+b
        double b1 = this.pointStartY - (m1 * this.pointStartX);
        double b2 = other.pointStartY - (m2 * other.pointStartX);

        // if slope of one of them is undefined
        if (m1 == -1 || m2 == -2) {
            if (m1 == -1) {
                x = this.pointEndX;
                y = ((m2 * x) + b2);
            } else if (m2 == -1) {
                x = other.pointEndX;
                y = ((m1 * x) + b1);
            }
            if (Math.min(this.pointStartX, this.pointEndX) <= x
                    && x <= Math.max(this.pointStartX, this.pointEndX)
                    && Math.min(other.pointStartX, other.pointEndX) <= x
                    && x <= Math.max(other.pointStartX, other.pointEndX)
                    && y >= Math.min(this.pointStartY, this.pointEndY)
                    && y <= Math.max(this.pointStartY, this.pointEndY)
                    ) {
                return true;
            }
            return false;
          //first check to see if they are parallel.
        } else if (m1 == m2) {
            if (m1 != 0) {
                return false;
            }
            /*
             * if they are parallel, calculate the difference.
             * between b's and that is our x coordinate for intersect.
             */
        } else if (m1 == 0 && m2 == 0) {
            x = b2 - b1;
        } else {
            // otherwise x coordinate of intersect is calculated as follows:
            x = -(b1 - b2) / (m1 - m2);
        }

        /*
         * check if the the lines segments do intersect.
         * by checking that the intersection is between the.
         * x and y coordinates of the point is between those.
         * of the minimal and maximal x coordinates of both lines.
         */
        if (Math.min(this.pointStartX, this.pointEndX) <= x
                && x <= Math.max(this.pointStartX, this.pointEndX)
                && Math.min(other.pointStartX, other.pointEndX) <= x
                && x <= Math.max(other.pointStartX, other.pointEndX)) {
            return true;
        }
        return false;
    }


    /**
     * The following method calculates the slope of a given line.
     * @param givenLine - the given line to calculates its slope.
     * @return - y / x - the slope.
     */
    static double slope(Line givenLine) {
        double y = givenLine.pointStartY - givenLine.pointEndY;
        double x = givenLine.pointStartX - givenLine.pointEndX;
        //in case one of them equals zero, making sure equation is valid.
        if (x == 0) {
            return -1;
        }
        if (y == 0) {
            return 0;
        }
        return (y / x);
    }


    /**
     * The following method calculates the intersection point,
     * if the lines intersect.
     * @param other - second line given to check if intersection happens.
     * @return point - the intersection point or null otherwise.
     */
    public Point intersectionWith(Line other) {

        if (isIntersecting(other)) {
            double x;
            double y;
            //find the slope of the two lines.
            double m1 = slope(this);
            double m2 = slope(other);

         // calculate b of the y = mx+b equations
            double b1 = this.pointStartY - (m1 * this.pointStartX);
            double b2 = other.pointStartY - (m2 * other.pointStartX);

         // if slope of one of them is undefined
            if (m1 == -1) {
                x = this.pointEndX;
                y = ((m2 * x) + b2);
            } else if (m2 == -1) {
                x = other.pointEndX;
                y = ((m1 * x) + b1);
            } else {
                /*
                 * calculate x coordinates of intersect point:
                 * if they are parallel, calculate the difference.
                 * between b's and that is our x coordinate for intersect.
                 */
                if (m1 == 0 && m2 == 0) {
                    x = b2 - b1;
                } else {
                    // otherwise x coordinate of intersect is calculated as follows:
                    x = -(b1 - b2) / (m1 - m2);
                }
                //find y coordinate of intersect point.
                y = m1 * x + b1;
            }
            Point point = new Point(x, y);
            return point;
        } else {
            return null;
        }
    }


    /**
     * the following method returns true if the lines are equal,
     * and false otherwise.
     * @param other - the second line we check against.
     * @return true - if they are the same line, false otherwise.
     */
    public boolean equals(Line other) {
        if ((this.pointStartX == other.pointStartX) && (this.pointStartY == other.pointStartY)
                && (this.pointEndX == other.pointEndX) && (this.pointEndY == other.pointEndY)) {
            return true;
        }
        return false;
    }

    /**
    * The following method calculates the closest intersection point between
    * a rectangle and a line and the beginning of the line.
    * @param rect - the rectangle we check intersection points with.
    * @return closestPoint - the closest intersection point to line start.
    */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {

        // list of intersecting points received
        ArrayList intersectPoints = (ArrayList) rect.intersectionPoints(this);

        double shortestPath = -1.0;
        Point closestPoint = new Point(0, 0);

        //loops over all intersecting points received
        for (int i = 0; i < intersectPoints.size(); i++) {
            if (i == 0) {
                shortestPath = this.lineStart.distance((Point) (intersectPoints.get(i)));
                closestPoint = (Point) intersectPoints.get(i);
            } else {
                if (shortestPath > this.lineStart.distance((Point) (intersectPoints.get(i)))) {
                    shortestPath = this.lineStart.distance((Point) (intersectPoints.get(i)));
                    closestPoint = (Point) intersectPoints.get(i);
                }
            }
        }
        // if now line was found
        if (shortestPath == -1.0) {
            return null;
        } else {
            //otherwise:
            return closestPoint;
        }
    }

}
