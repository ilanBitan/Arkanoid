package thegame;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The class of the ending game screen. shows details of the score, and if
 * the player won or not.
 * @author Ilan Bitan
 *
 */
public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter gameScore;
    private Counter playerLives;

    /**
     * a constructor.
     * @param kbrd - a KeyboardSensor.
     * @param score - a counter of the score.
     * @param lives - a counter of the lives.
     */
    public EndScreen(KeyboardSensor kbrd, Counter score, Counter lives) {
       this.keyboard = kbrd;
       this.stop = false;
       this.gameScore = score;
       this.playerLives = lives;
    }

    /**
     * the method draw on the screen the game result:
     * if the player won than a matching message is printed.
     * @param d - a DrawSurface.
     */
    public void doOneFrame(DrawSurface d) {
        if (this.playerLives.getValue() < 0) {
            d.drawText(10, d.getHeight() / 2, "Game Over Your Score is: " + Integer.toString(gameScore.getValue()), 32);
        } else {
            d.drawText(10, d.getHeight() / 2, "You Win! Your Score is: " + Integer.toString(gameScore.getValue()), 32);
        }
        d.drawText(740, d.getHeight()/30,"בסד",25);
        d.drawText(530, 4*d.getHeight()/5, "ILAN BITAN ©", 40);
        d.drawText(20, 4*d.getHeight()/5, "PEGI-3", 40);
       if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
           this.stop = true;
       }
    }

    /**
     * the method returns true if the doOneFrame should stop.
     * @return stop - true if doOneFrame should stop.
     */
    public boolean shouldStop() {
        return this.stop;
    }

 }