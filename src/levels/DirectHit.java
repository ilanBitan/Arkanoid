package levels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import geometry.Point;
import geometry.Rectangle;
import backgrounds.DirectHitBackground;
import gameobjects.Block;
import gameobjects.Sprite;
import gameobjects.Velocity;

/**
 * The following class is used to represent the needed specific
 * changes to create the Direct Hit level
 * it handles the balls, paddle and blocks.
 * @author Ilan Bitan
 *
 */
public class DirectHit implements LevelInformation {

    private Sprite sprite;
    private DirectHitBackground background;

    /**
     * the following is the constructor for the Direct Hit class.
     */
    public DirectHit() {
        this.background = new DirectHitBackground();
    }

    /**
     * The following method is used to return the exact number
     * of balls needed for this level.
     * @return 1 - the number of balls to return.
     */
    public int numberOfBalls() {
        return 1;
    }

    @Override
    /**
     * The following method is used to initiate the velocity
     * for each ball in the level.
     * in this case we have one ball with a specific velocity.
     * @return velocities - the list of ball velocities
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>();
        // ball shot up at right angle
        Velocity v = new Velocity(0, -10);
        velocities.add(v);
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
     * @return "Direct Hit" - level name.
     */
    public String levelName() {
        return "Direct Hit";
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
     * @return blocks - array list holding all blocks for this level.
     */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<Block>();
        //block's color
        Random rand = new Random();
        Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        // block's size
        int blockWidth = 40;
        int blockHeight = 40;
        //block's location
        Point p = new Point(380, 200);
        //create the block and add it to the list
        Block block = new Block(new Rectangle(p, (double) blockWidth, (double) blockHeight), color);
        blocks.add(block);

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
