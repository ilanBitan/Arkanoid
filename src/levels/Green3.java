package levels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import geometry.Point;
import geometry.Rectangle;
import backgrounds.Green3Background;
import gameobjects.Block;
import gameobjects.Sprite;
import gameobjects.Velocity;

/**
 * The following class is used to represent the needed specific
 * changes to create the Green3 level
 * it handles the balls, paddle and blocks.
 * @author Ilan Bitan
 *
 */
public class Green3 implements LevelInformation {

    private Sprite sprite;
    private Green3Background background;

    /**
     * the following is the constructor for the Direct Hit class.
     */
    public Green3() {
        this.background = new Green3Background();
    }

    /**
     * The following method is used to return the exact number
     * of balls needed for this level.
     * @return 3 - the number of balls to return.
     */
    public int numberOfBalls() {
        return 2;
    }

    @Override
    /**
     * The following method is used to initiate the velocity
     * for each ball in the level.
     * in this case we have three ball with a specific velocity.
     * @return velocities - the list of ball velocities
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>();
        // the angle for the left ball
        int j = 315;
        for (int i = 0; i < 2; i++) {
            // gives the angle for 2 more balls
            velocities.add(Velocity.fromAngleAndSpeed(j, 4));
            j += 90;
        }
        return velocities;
    }

    @Override
    /**
     * This method creates and returns the speed of the
     * paddle movement.
     * @return paddleSpeed - the paddle velocity.
     */
    public int paddleSpeed() {
        return 6;
    }

    @Override
    /**
     * This method creates the size of the paddle in the game.
     * @return 100 - paddle width.
     */
    public int paddleWidth() {
        return 100;
    }

    @Override
    /**
     * This method holds the name of the level.
     * @return "Green 3" - level name.
     */
    public String levelName() {
        return "Green 3";
    }

    @Override
    /**
     * This method sends the background to the game level.
     * @return background - the given background.
     */
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    /**
     * This method is used to create all blocks in this level.
     * @returns blocks - array list holding all blocks for this level.
     */
    public List<Block> blocks() {

        List<Block> blocks = new ArrayList<Block>();
        Point p;
        int xStart = 745;
        int x;
        int y = 200;
        // set block's size
        int blockWidth = 45;
        int blockHeight = 25;

        Random rand = new Random();
        Block block;

        //now add the blocks in a loop:
        for (int i = 5; i > 0; i--) {
            x = xStart;
            //set color randomly
            Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            for (int j = 0; j < i + 5; j++) {
                //create the point of the current block
                p = new Point(x, y);
                //create the rectangle
                Rectangle rect = new Rectangle(p, (double) blockWidth, (double) blockHeight);
                //create the block
                if (i == 6) {
                    block = new Block(rect, color, 2);
                } else {
                    block = new Block(rect, color, 1);
                }
                blocks.add(block);
                x = x - blockWidth;
            }
            y = y + blockHeight;

        }

        return blocks;
    }

    @Override
    /**
     * Number of levels that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     * @return this.blocks().size() - the amount of blocks
     * to remove in level.
     */
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}