package thegame;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The AnimationRunner class.
 * @author Ilan Bitan
 *
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;

    /**
     * a constructor.
     * @param gui - the gui.
     */
    public AnimationRunner(GUI gui) {
        framesPerSecond = 60;
        this.gui = gui;
    }

    /**
     * the method that runs a given animation, as long as shouldStop is false.
     * @param animation - a given Animation object.
     */
    public void run(Animation animation) {
       int millisecondsPerFrame = 1000 / framesPerSecond;
       while (!animation.shouldStop()) {
          long startTime = System.currentTimeMillis(); // timing
          DrawSurface d = this.gui.getDrawSurface();

          animation.doOneFrame(d);

          Sleeper sleeper = new Sleeper();

          gui.show(d);
          long usedTime = System.currentTimeMillis() - startTime;
          long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
          if (milliSecondLeftToSleep > 0) {
              sleeper.sleepFor(milliSecondLeftToSleep);
          }
       }
    }
 }