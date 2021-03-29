package geometry;
import java.util.ArrayList;

/**
 * The class that represents the Rectangle objects.
 * each rectangle object has:
 * a point - the upper left point of the rectangle position
 * a width - the rectangle width
 * a height - the rectangle height
 *
 * @author Ilan Bitan
 *
 */
public class Rectangle {

    private Point location;
    private double recWidth;
    private double recHeight;

    /**
     * a constructor.
     * Create a new rectangle with location and width/height.
     * @param upperLeft - the upper left point of the rectangle position
     * @param width - the rectangle width
     * @param height - the rectangle height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.location = upperLeft;
        this.recWidth = width;
        this.recHeight = height;
    }

    /**
     * return a (possibly empty) List of intersection points
     * with the specified line.
     * @param line - a given line.
     * @return an array list of all intersection points.
     */
    public java.util.List intersectionPoints(Line line) {

        //an array list - we will need to change the size dynamically.
        ArrayList<Point> intersectPoints = new ArrayList<Point>();

        //a list of type Line
        Line [] recLines = new Line[4];

        /*
         * the next lines computes the next:
         * create the points that components the lines of the rectangle.
         * then, create the lines. each line is being added to the recLines
         * list.
         */

        // line 1: the one that components the left line of the rectangle.
        Line leftRectangle = new Line(this.location, this.getLowerLeft());
        recLines[0] = leftRectangle;

        // line 2: the one that components the upper line of the rectangle.
        Line upperRectangle = new Line(this.location, this.getUpperRight());
        recLines[1] = upperRectangle;

        // line 3: the one that components the right line of the rectangle.
        Line rightrRectangle = new Line(this.getUpperRight(), this.getLowerRight());
        recLines[2] = rightrRectangle;

        // line 4: the one that components the lower line of the rectangle.
        Line lowerRectangle = new Line(this.getLowerLeft(), this.getLowerRight());
        recLines[3] = lowerRectangle;

        /*
         * go over the lines of the rectangle. if there is a line that
         * intersects with the given line, add the intersection point
         * to the intersectPoints arrayList.
         */
        for (int i = 0; i < 4; i++) {
            if (recLines[i].isIntersecting(line)) {
                Point newPoint = recLines[i].intersectionWith(line);
                intersectPoints.add(newPoint);
            }
        }
       return intersectPoints;
   }

   /**
    * Return the width of the rectangle.
    * @return recWidth - the width.
    */
    public double getWidth() {
        return this.recWidth;
    }

   /**
    * Return the height of the rectangle.
    * @return recHeight - the height.
    */
    public double getHeight() {
        return this.recHeight;
    }

   /**
    * Returns the upper-left point of the rectangle.
    * @return location - the point.
    */
    public Point getUpperLeft() {
        return this.location;
    }

    /**
     * Returns the lower-left point of the rectangle.
     * @return this.getLowerLeft() - the point.
     */
    public Point getLowerLeft() {
        // the point in the lower left of the rectangle
        Point lowerLeft = new Point(this.location.getX(),
                this.location.getY() + this.getHeight());
        return lowerLeft;
    }

    /**
     * Returns the upper-left point of the rectangle.
     * @return this.getUpperRight() - the point.
     */
    public Point getUpperRight() {
        // the point in the upper Right of the rectangle
        Point upperRight = new Point(this.location.getX() + this.getWidth(),
                this.location.getY());
        return upperRight;
    }

    /**
     * Returns the lower-right point of the rectangle.
     * @return this.getLowerRight() - the point.
     */
    public Point getLowerRight() {
        // the point in the lower right of the rectangle
        Point lowerRight = new Point(this.location.getX() + this.getWidth(),
                this.location.getY() + this.getHeight());
        return lowerRight;
    }

}
