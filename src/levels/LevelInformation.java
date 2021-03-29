package levels;

import java.util.List;

import gameobjects.Block;
import gameobjects.Sprite;
import gameobjects.Velocity;
/**
 * This interface specifies the information,
 * required to fully describe a level.
 * @author Ilan Bitan
 *
 */
public interface LevelInformation {
    /**
     * The following method is used to return the exact number
     * of balls needed for this level.
     * @return the number of balls to return.
     */
    int numberOfBalls();

    /**
     * The following method is used to initiate the velocity
     * for each ball in the level.
     * in this case we have three ball with a specific velocity.
     * Note that initialBallVelocities().size() == numberOfBalls()
     * @return velocities - the list of ball velocities
     */
    List<Velocity> initialBallVelocities();
    /**
     * This method creates and returns the speed of the
     * paddle movement.
     * @return paddleSpeed - the paddle velocity.
     */
    int paddleSpeed();
    /**
     * This method creates the size of the paddle in the game.
     * @return paddle width.
     */
    int paddleWidth();

    /**
     * This method holds the name of the level.
     * @return "Green 3" - level name.
     */
    String levelName();

    /**
     * This method sends the background to the game level.
     * @return background - the given background.
     */
    Sprite getBackground();

    /**
     * This method is used to create all blocks in this level.
     * contains size, color and location.
     * @return blocks - array list holding all blocks for this level.
     */
    List<Block> blocks();

    /**
     * Number of levels that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     * @return this.blocks().size() - the amount of blocks
     * to remove in level.
     */
    int numberOfBlocksToRemove();
 }