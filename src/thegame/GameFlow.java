package thegame;

import java.util.List;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import levels.LevelInformation;

/**
 * The GameFlow class.
 * this class is runs the levels, as sent from the main.
 * @author Ilan Bitan
 *
 */
public class GameFlow {

    private AnimationRunner runner;
    private KeyboardSensor keyBoard;
    private Counter countLives;
    private Counter countScore;
    private int initializedLives = 7;
    private GUI gui;
    private ScoreTrackingListener scoreListener;
    private int initialized = 0;

    /**
     * a constructor.
     * @param ar - an AnimationRunner.
     * @param ks - KeyboardSensor.
     * @param gui - a gui.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.runner = ar;
        this.keyBoard = ks;
        this.gui = gui;
        this.countLives = new Counter(this.initializedLives);
        this.countScore = new Counter(this.initialized);
        this.scoreListener = new ScoreTrackingListener(this.countScore);
    }

    /**
     * this method runs the levels, using the GameLevel class to initialize
     * the level and to run it.
     * @param levels - a list of the levels, as sent from the main.
     */
    public void runLevels(List<LevelInformation> levels) {
        // loop over the list, and as long as there are lives left, continue.
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.runner,
                    this.keyBoard, this.gui, this.countLives,
                    this.countScore, this.scoreListener);

            level.initialize();
            while ((level.getNumberOfBlocks().getValue() > 0)
                    && (level.getLevelLives().getValue() >= 0)) {
                level.playOneTurn();
            }

            if (this.countLives.getValue() < 0) {
                break;
            }
        }
    }

    /**
     * a setter for the counter of lives.
     * @param lives - the lives counter.
     */
    public void setLives(Counter lives) {
        this.countLives = lives;
    }

    /**
     * a getter for the lives.
     * @return the lives counter.
     */
    public Counter getLives() {
        return this.countLives;
    }

    /**
     * a getter for the score.
     * @return the score counter.
     */
    public Counter getScore() {
        return this.countScore;
    }
 }