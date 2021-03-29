package gameobjects;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import thegame.GameLevel;

/**
 * The following class is used to define the paddle that is used in the game.
 * The class is used for paddle movement,
 * hit calculations and more aspects of the paddle object.
 *
 * @author shoshal2 chamiel
 */
public class Paddle implements Sprite, Collidable {
   private biuoop.KeyboardSensor keyboard;
   private Rectangle rect;
   private java.awt.Color rectColor;
   private int guiWidth;
   private double barrier;
   private int speed;

   /**
    * The following constructor is used to create a paddle.
    * @param rect1 - the rectangle information for paddle.
    * @param color - given color for the paddle.
    * @param keyboard - keyboard sensor for paddle control.
    * @param width - width of gui screen.
    * @param givenBarrier - width of gui screen side barriers.
    * @param paddleSpeed - the speed of the paddle.
    */
   public Paddle(Rectangle rect1, java.awt.Color color,
           biuoop.KeyboardSensor keyboard, int width,
           double givenBarrier, int paddleSpeed) {
       this.rectColor = color;
       this.rect = rect1;
       this.keyboard = keyboard;
       this.guiWidth = width;
       this.barrier = givenBarrier;
       this.speed = paddleSpeed;
   }

   /**
    * The following method is called once the left keyboard arrow is pressed.
    * it is called by the timePassed method.
    */
   public void moveLeft() {
       if (rect.getUpperLeft().getX() > this.barrier) {
           // get the point of top left of paddle.
           double x = rect.getUpperLeft().getX();
           double y = rect.getUpperLeft().getY();
           //create a new position based on the given position.
           Point newPoint = new Point(x - this.speed, y);
           // recreate paddle by new position.
           Rectangle newRect = new Rectangle(newPoint, rect.getWidth(), rect.getHeight());
           this.rect = newRect;
       }
   }
   /**
    * The following method is called once the right keyboard arrow is pressed.
    * it is called by the timePassed method.
    */
   public void moveRight() {
       if (rect.getUpperRight().getX() < this.guiWidth - this.barrier) {
           // get the point of top right of paddle.
           double x = rect.getUpperLeft().getX();
           double y = rect.getUpperLeft().getY();
           //create a new position based on the given position.
           Point newPoint = new Point(x + this.speed, y);
           // recreate paddle by new position.
           Rectangle newRect = new Rectangle(newPoint, rect.getWidth(), rect.getHeight());
           this.rect = newRect;
       }
   }

   /**
    * The following method is used to check pressed keys each time the game loops.
    */
   public void timePassed() {
       //check if left key was pressed:
       if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
           this.moveLeft();
       }
       //check if right key was pressed:
       if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
           this.moveRight();
       }
   }

   /**
    * accessor to the color.
    * @return the blocks color.
    */
   public java.awt.Color getColor() {
       return rectColor;
   }
   /**
    * the method draws the paddle on the paddle DrawSurface.
    * if the surface was not given, the surface is the default one.
    * @param surface - the surface.
    */
   public void drawOn(DrawSurface surface) {
       // sets color of paddle (given).
       surface.setColor(this.getColor());
       // calls the fill rectangle method to fill the color
       surface.fillRectangle((int) this.rect.getUpperLeft().getX(),
               (int) this.rect.getUpperLeft().getY(), (int) this.rect.getWidth(),
               (int) this.rect.getHeight());
   }

   /**
    * returns the rectangle that is the paddle.
    * @return rect - rectangle that is the paddle.
    */
   public Rectangle getCollisionRectangle() {
       return this.rect;
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
       // get velocity that was given before the hit:
       Velocity newVelocity = currentVelocity;

       Point uL = rect.getUpperLeft();
       Point uR = rect.getUpperRight();
       double x = collisionPoint.getX();
       double y = collisionPoint.getY();
       // get the paddle length:
       double paddleLength = uR.getX() - uL.getX();
       // devide to sections:
       double paddleRegion = paddleLength / 5;
       double dx = currentVelocity.getDx();
       double dy = currentVelocity.getDy();
       // all 5 sections of paddle - each section returns different hit return angle.
       Line part1 = new Line(uL.getX(), uL.getY(), uL.getX() + paddleRegion, uL.getY());
       Line part2 = new Line(uL.getX() + paddleRegion, uL.getY(), uL.getX() + (2 * paddleRegion), uL.getY());
       Line part3 = new Line(uL.getX() + (2 * paddleRegion), uL.getY(), uL.getX() + (3 * paddleRegion), uL.getY());
       Line part4 = new Line(uL.getX() + (3 * paddleRegion), uL.getY(), uL.getX() + (4 * paddleRegion), uL.getY());
       Line part5 = new Line(uL.getX() + (4 * paddleRegion), uL.getY(), uL.getX() + (5 * paddleRegion), uL.getY());
       // if we hit the most left part return at 330 degree angle - (0 = 360 degree is up):
       if (x >= part1.start().getX() && x < part1.end().getX() && y == uR.getY()) {
           newVelocity = Velocity.fromAngleAndSpeed(330, newVelocity.fromdxdy(dx, -dy));

       // if we hit the most left part return at 300 degree angle - (0 = 360 degree is up):
       } else if (x >= part2.start().getX() && x < part2.end().getX() && y == uR.getY()) {
           newVelocity = Velocity.fromAngleAndSpeed(300, newVelocity.fromdxdy(dx, -dy));

       // if we hit the most left part return at 0 degree angle - (0 = 360 degree is up):
       } else if (x >= part3.start().getX() && x < part3.end().getX() && y == uR.getY()) {
           newVelocity = Velocity.fromAngleAndSpeed(1, newVelocity.fromdxdy(dx, -dy));

       // if we hit the most left part return at 30 degree angle - (0 = 360 degree is up):
       } else if (x >= part4.start().getX() && x < part4.end().getX() && y == uR.getY()) {
           newVelocity = Velocity.fromAngleAndSpeed(30, newVelocity.fromdxdy(dx, -dy));

       // if we hit the most left part return at 60 degree angle - (0 = 360 degree is up):
       } else if (x >= part5.start().getX() && x <= part5.end().getX() && y == uR.getY()) {
           newVelocity = Velocity.fromAngleAndSpeed(60, newVelocity.fromdxdy(dx, -dy));
       }

       return newVelocity;
   }

   /**
    * this method adds the paddle to the collidable and the sprite array.
    * @param g - the current game.
    */
   public void addToGame(GameLevel g) {
       g.addCollidable((Collidable) this);
       g.addSprite((Sprite) this);
   }

   /**
    * the method removes the paddle from the game.
    * @param game - the current game.
    */
   public void removeFromGame(GameLevel game) {
       game.removeCollidable(this);
       game.removeSprite(this);
   }
}