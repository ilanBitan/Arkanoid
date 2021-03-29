package thegame;

import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import gameobjects.SpriteCollection;

/**
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) secods, before
 * it is replaced with the next one.
 * @author Ilan Bitan
 *
 */
public class CountdownAnimation implements Animation {
    private double numOfSecondsToCount;
    private int countDownNum;
    private Counter countSeconds;
    private SpriteCollection screen;
    private boolean stop;
    private Sleeper sleeper = new Sleeper();

    /**
     * a constructor.
     * @param numOfSeconds - the number of seconds.
     * @param countFrom - the number to start count from.
     * @param gameScreen - the game screen.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSecondsToCount = numOfSeconds;
        this.countDownNum = countFrom;
        this.screen = gameScreen;
        this.stop = false;
        this.countSeconds = new Counter(3);
    }

    /**
     * the method draw on the screen the count down before the game starts.
     * @param d - a DrawSurface.
     */
    public void doOneFrame(DrawSurface d) {

        this.screen.drawAllOn(d);
        d.setColor(Color.BLUE);

        if (this.countSeconds.getValue() > 0) {
        d.drawText(400, 500, "" + String.valueOf(this.countSeconds.getValue()) , 32);
        }
        if (this.countSeconds.getValue() == 0) {
            d.drawText(400, 500, "GO!" , 32);
        }
        sleeper.sleepFor((long) (1000 * (this.numOfSecondsToCount / this.countDownNum)));
        this.countSeconds.decrease(1);
        if (this.countSeconds.getValue() < -1) {
            this.stop = true;
        }
    }

    /**
     * the method returns true if the doOneFrame method should stop
     * showing the count down, false otherwise.
     * @return true or false.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}