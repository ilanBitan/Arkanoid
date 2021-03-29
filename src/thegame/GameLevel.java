package thegame;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
//import java.util.Random;
import biuoop.KeyboardSensor;
import gameobjects.Ball;
import gameobjects.Block;
import gameobjects.Collidable;
import gameobjects.Paddle;
import gameobjects.Sprite;
import gameobjects.SpriteCollection;
import geometry.Point;
import geometry.Rectangle;
import biuoop.GUI;
//import levels.DirectHit;
//import levels.Green3;
import levels.LevelInformation;
//import levels.WideEasy;
//import levels.FinalFour;
//import listener.HitListener;
//import listener.PrintingHitListener;
import biuoop.DrawSurface;

/**
 * The following class Game, is used in order to initialize all objects
 * and components in the game and then run the game accordingly.
 *
 * @author Ilan Bitan
 *
 */
public class GameLevel implements Animation {
    //hold all collection of sprite.
    private SpriteCollection sprites;
    //holds the list of collidables.
    private GameEnvironment environment;
    // counters
    private Counter countBlocks;
    private Counter countBalls;
    private Counter countScore;
    private Counter countLives;
    private GUI gui;

    private int initialized = 0;
    private int modifyLives = 1;
    private int noBlocks = 100;
    //private int initializedLevel = 1;

    private BlockRemover blockRemover;
    private BallRemover ballRemover;

    private Block deathZone;

    private AnimationRunner runner;
    private boolean running = true;
    private List<Ball> ballsArray = new ArrayList<Ball>();
    private Paddle paddle;
    private LevelInformation levelInfo;
    private ScoreTrackingListener scoreListener;

    /**
     * constructor for game.
     * @param info - the level.
     * @param run - AnimationRunner object.
     * @param ks - KeyboardSensor.
     * @param gameGui - a gui.
     * @param lives - a counter for player lives.
     * @param score - a counter of the game's score.
     * @param scoreLstnr  - ScoreTrackingListener.
     */
    public GameLevel(LevelInformation info, AnimationRunner run,
            KeyboardSensor ks, GUI gameGui, Counter lives,
            Counter score, ScoreTrackingListener scoreLstnr) {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        this.countBlocks = new Counter(this.initialized);
        this.countBalls = new Counter(this.initialized);
        this.countScore = score;
        this.countLives = lives;
        this.blockRemover = new BlockRemover(this, this.countBlocks);
        this.ballRemover = new BallRemover(this, this.countBalls);
        this.deathZone = new Block(new Rectangle(new Point(0, 800), 800, 0), java.awt.Color.BLACK);
        this.runner = run;
        this.levelInfo = info;
        this.gui = gameGui;
        this.scoreListener = scoreLstnr;
    }

    /**
     * the method adds a Collidable object to environment.
     * @param c - the Collidable object.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * the method adds a Sprite object to sprites.
     * @param s - the Sprite object.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * The following method is used to initialize a new game:
     * create the game's frames, blocks, the indicators, and add
     * them to the game.
     */
    public void initialize() {

        // the game blocks that defines the frames
        Block frame1 = new Block(new Rectangle(new Point(0, 30), 800, 10), java.awt.Color.GRAY);
        Block frame2 = new Block(new Rectangle(new Point(790, 30), 10, 800), java.awt.Color.GRAY);
        Block frame3 = new Block(new Rectangle(new Point(0, 30), 10, 790), java.awt.Color.GRAY);

        // create indicators
        ScoreIndicator gameScore = new ScoreIndicator(this.countScore);
        LivesIndicator lives =  new LivesIndicator(this.countLives);
        LevelIndicator showLevel = new LevelIndicator(this.levelInfo);

        this.levelInfo.getBackground().addToGame(this);

        // add the indicators to the game.
        gameScore.addToGame(this);
        lives.addToGame(this);
        showLevel.addToGame(this);
        // add the frames to the game
        frame1.addToGame(this);
        frame2.addToGame(this);
        frame3.addToGame(this);

        deathZone.addToGame(this);
        deathZone.addHitListener(ballRemover);

        countBlocks.increase(this.levelInfo.numberOfBlocksToRemove());
        List<Block> blockList = new ArrayList<Block>(this.levelInfo.blocks());
        for (Block block : blockList) {
            block.addToGame(this);
            block.addHitListener(this.blockRemover);
            block.addHitListener(scoreListener);
        }
    }

    @Override
    /**
     * the method start one frame, and checks if all the balls hit the ground,
     * if there are no block left.
     */
    public void doOneFrame(DrawSurface d) {

        this.sprites.drawAllOn(d);
        this.deathZone.setNumOfhit(this.levelInfo.numberOfBalls());
        this.sprites.notifyAllTimePassed();

        if (gui.getKeyboardSensor().isPressed("p")) {
            this.runner.run(new PauseScreen(gui.getKeyboardSensor()));
        }

        // all the balls hit the ground, so the number of lives should decrease.
        if (this.countBalls.getValue() == 0) {
            this.countLives.decrease(modifyLives);
            this.paddle.removeFromGame(this);
            this.running = false;
        } else if (this.countBlocks.getValue() == 0) {
            this.countScore.increase(noBlocks);
            this.sprites.drawAllOn(d);
            // remove remaining balls from game
            removeBalls();
            this.running = false;
        }
    }

    @Override
    /**
     * the method returns true if the game should stop, false otherwise.
     * @return true if the game should stop.
     */
    public boolean shouldStop() {
        return !this.running;
    }
    /**
     * The following method is used toRun the game:
     * start the animation loop.
     */
    public void playOneTurn() {
        this.createBallsOnTopOfPaddle(); // or a similar method
        // countdown before turn starts.
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * the method creates the balls and the paddle.
     */
    public void createBallsOnTopOfPaddle() {

        //add paddle to game
        Point paddleP = new Point(400 - (this.levelInfo.paddleWidth() / 2), 575);
        Rectangle paddleR = new Rectangle(paddleP, this.levelInfo.paddleWidth(), 15);
        this.paddle = new Paddle(paddleR, java.awt.Color.YELLOW, gui.getKeyboardSensor(),
                gui.getDrawSurface().getWidth(), 10, this.levelInfo.paddleSpeed());
        this.paddle.addToGame(this);

        Point point1 = new Point(400, 570);
        Point pSurface = new Point(0, 0);

        for (int i = 0; i < this.levelInfo.numberOfBalls(); i++) {
            Ball ball = new Ball(point1, 5, Color.WHITE, pSurface, 800, 600, this.environment);
            ball.setVelocity(this.levelInfo.initialBallVelocities().get(i));
            ball.addToGame(this);
            /*
             * the ballsArray is used when there are balls that did not hit the ground,
             * and the game continues. then, the balls are removed from the game.
             */
            this.ballsArray.add(ball);
        }
        this.countBalls.increase(this.levelInfo.numberOfBalls());
    }

    /**
     * the method removes the balls in the ballArray from the game.
     */
    public void removeBalls() {
        for (int i = 0; i < this.ballsArray.size(); i++) {
            this.ballsArray.get(i).removeFromGame(this);
        }
    }

    /**
     * a getter for the paddle.
     * @return the paddle.
     */
    public Paddle getPaddle() {
        return this.paddle;
    }

    /**
     * a getter for the lives counter.
     * @return the lives counter.
     */
    public Counter getLevelLives() {
        return this.countLives;
    }

    /**
     * a getter for the blocks counter.
     * @return the blocks counter.
     */
    public Counter getNumberOfBlocks() {
        return this.countBlocks;
    }

    /**
     * the method uses the removeCollidableObject to remove an object.
     * @param c - Collidable object.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidableObject(c);
    }

    /**
     * the method uses the removeSpriteObject to remove an object.
     * @param s - Sprite object.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSpriteObject(s);
    }
}