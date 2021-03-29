package levels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import geometry.Point;
import geometry.Rectangle;
import backgrounds.WideEasyBackground;
import gameobjects.Block;
import gameobjects.Sprite;
import gameobjects.Velocity;

/**
 * The following class is used to represent the needed specific
 * changes to create the Wide Easy level
 * it handles the balls, paddle and blocks.
 * @author Ilan Bitan
 *
 */
public class WideEasy implements LevelInformation {

    private Sprite sprite;
    private WideEasyBackground background;

    /**
     * the following is the constructor for the Direct Hit class.
     */
    public WideEasy() {
        this.background = new WideEasyBackground();
    }

    /**
     * The following method is used to return the exact number
     * of balls needed for this level.
     * @return 10 - the number of balls to return.
     */
    public int numberOfBalls() {
        return 10;
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

        int j = 310;
        // the angle for the left ball
        for (int i = 0; i < 10; i++) {
            // gives the angle for 9 more balls
            velocities.add(Velocity.fromAngleAndSpeed(j, 5));
            j += 10;
            if (j == 360) {
                j += 10;
            }
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
     * @return 600 - paddle width.
     */
    public int paddleWidth() {
        return 600;
    }

    @Override
    /**
     * This method holds the name of the level.
     * @return "Wide Easy" - level name.
     */
    public String levelName() {
        return "Wide Easy";
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
        Point p;
        Block block1;
        Block block2;
        Block block3;
        double x = 738;
        double y = 250;
        // set block's size
        double blockWidth = 52;
        double blockHeight = 25;

        for (int i = 0; i < 7; i++) {
            //block's color
            Random rand = new Random();
            Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));

            //block's location
            p = new Point(x, y);
            //create the block
            block1 = new Block(new Rectangle(p, blockWidth, blockHeight), color);
            x = x - blockWidth;

            p = new Point(x, y);
            //create the block
            block2 = new Block(new Rectangle(p, blockWidth, blockHeight), color);
            x = x - blockWidth;
            // adds blocks to the list
            blocks.add(block1);
            blocks.add(block2);

            if (i == 3) {
                p = new Point(x, y);
                //create the block
                block3 = new Block(new Rectangle(p, (double) blockWidth, (double) blockHeight), color);
                // adds blocks to the list
                blocks.add(block3);
                x = x - blockWidth;
            }
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