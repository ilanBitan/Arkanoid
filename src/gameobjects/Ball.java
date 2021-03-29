package gameobjects;
import java.awt.Color;

import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import thegame.GameEnvironment;
import thegame.GameLevel;

/**
 * The class that represents the Ball object.
 * each ball has:
 * location - which is the point of the ball's center,
 * a radius, a color, velocity.
 * a balls also has x, y, width, height values which determine the
 * ball's moving boundaries.
 * in addition, a ball has a game environment.
 *
 * @author Ilan Bitan
 *
 */
public class Ball implements Sprite {

    private Point location;
    private int radius;
    private java.awt.Color ballColor;
    private Velocity velocity;
    private int xSurface;
    private int ySurface;
    private int widthSurface;
    private int heightSurface;
    private GameEnvironment gameEnvironment;

    /**
     * a constructor.
     * since the boundaries are not given (see constructor below), the default
     * values are as written in this constructor.
     * @param center - the ball's center point.
     * @param r - the ball's radius.
     * @param color - the ball's color.
     */
    public Ball(Point center, int r, java.awt.Color color) {

        location = center;
        radius = r;
        ballColor = color;
        velocity = new Velocity(0.0, 0.0);
        xSurface = 0;
        ySurface = 0;
        widthSurface = 600;
        heightSurface = 600;
        gameEnvironment = new GameEnvironment();
    }

    /**
     * a constructor.
     * in this constructor the given parameters are:
     * @param center - the ball's center point.
     * @param r - the ball's radius.
     * @param color - the ball's color.
     * @param pSurface - the upper left point of the ball's moving surface.
     * @param width - the right x coordinate of the ball's moving surface.
     * @param height - the right y coordinate of the ball's moving surface.
     * @param environment - the game environment.
     */
    public Ball(Point center, int r, java.awt.Color color, Point pSurface,
            int width, int height, GameEnvironment environment) {

        location = center;
        radius = r;
        ballColor = color;
        velocity = new Velocity(0.0, 0.0);
        xSurface = (int) pSurface.getX();
        ySurface = (int) pSurface.getY();
        widthSurface = width;
        heightSurface = height;
        gameEnvironment = environment;
    }

    /**
     * accessor to the X coordinate.
     * @return the x coordinate of the ball center's point.
     */
    public int getX() {
        return (int) location.getX();
    }

    /**
     * accessor to the Y coordinate.
     * @return the y coordinate of the ball center's point.
     */
    public int getY() {
        return (int) location.getY();
    }

    /**
     * accessor to the radius.
     * @return the ball's radius.
     */
    public int getSize() {
        return radius;
    }

    /**
     * accessor to the color.
     * @return the ball's color.
     */
    public java.awt.Color getColor() {
        return ballColor;
    }

    /**
     * the method draws the ball on the ball's DrawSurface.
     * if the surface was not given, the surface is the default one.
     * @param surface - the surface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * the method sets the velocity of the ball.
     * @param v - the velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * the method sets the velocity of the ball according to the
     * dx and dy values.
     * @param dx - the dx component.
     * @param dy - the dy component.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * the method returns the ball's velocity.
     * @return the velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * the method moves the ball according to its location, radius,
     * velocity and the objects that can be collided with.
     */
    public void moveOneStep() {

        double x;
        double y;
        Point nextLocation;
        Line trajectory;

        // find the end point of the trajectory
        nextLocation = this.getVelocity().applyToPoint(this.location);

        // create the trajectory line
        trajectory = new Line(this.location, nextLocation);

        // the closest object that can be collided with
        CollisionInfo c = gameEnvironment.getClosestCollision(trajectory);

        // check if the closest object is null. if so, there is no collision
        if (c == null) {
            this.location = nextLocation;

        // there is a collision.
        } else {
            x = this.location.getX();
            y = this.location.getY();

            // find the point of the collision
            Point collisionPoint = c.collisionPoint();

            /*
             * find the x coordinate of the next location point,
             * considering the current x coordinate, the collision x
             * coordinate and the radius.
             */
            if (this.location.getX() > collisionPoint.getX()) {
                x = collisionPoint.getX() + this.radius;
            } else {
                x = collisionPoint.getX() - this.radius;
            }

            /*
             * find the y coordinate of the next location point,
             * considering the current y coordinate, the collision y
             * coordinate and the radius.
             */
            if (this.location.getY() > c.collisionPoint().getY()) {
                y = collisionPoint.getY() + this.radius;
            } else if (this.location.getY() < c.collisionPoint().getY()) {
                y = collisionPoint.getY() - this.radius;
            } else {
                y = collisionPoint.getY();
            }

            // create the next point of the ball
            this.location = new Point(x, y);

            // set the ball's velocity
            this.velocity = c.collisionObject().hit(collisionPoint, this.velocity, this);

        }
    }

    /**
     * the method notify the sprite that time has passed.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * the method adds the ball to the game.
     * @param g - the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
    /**
     * this method removes the ball from the game.
     * @param game - the current game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }

}