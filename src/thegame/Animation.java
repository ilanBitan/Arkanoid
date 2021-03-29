package thegame;

import biuoop.DrawSurface;

/**
 * The Animation interface.
 * @author Ilan Bitan
 *
 */
public interface Animation {

    /**
     * the method that runs a one frame of the current game.
     * @param d - a DrawSurface object.
     */
    void doOneFrame(DrawSurface d);

    /**
     * the method returns true if the current game should stop or false
     * otherwise.
     * @return true - if the game should stop.
     */
    boolean shouldStop();
 }