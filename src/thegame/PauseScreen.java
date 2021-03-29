package thegame;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * The following class is used in order to pause the screen when
 * the letter 'p' is pressed on the keyboard.
 * @author Ilan Bitan
 *
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * This is the constructor for the PauseScreen class.
     * @param kbrd - received keyboard sensor,
     */
    public PauseScreen(KeyboardSensor kbrd) {
       this.keyboard = kbrd;
       this.stop = false;
    }
    /**
     * This moethod is used in order to display a gui image and pause
     * the game until the space bar is pressed.
     * @param d - the received drawSurface.
     */
    public void doOneFrame(DrawSurface d) {
       d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
       if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
           this.stop = true;
       }
    }
    /**
     * This method is used in order to switch the boolean in game level
     * in order to stop the game loop.
     * @return thi.stop - true for game should stop.
     */
    public boolean shouldStop() {
        return this.stop;
    }

 }