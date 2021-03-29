package backgrounds;

import java.awt.Color;

import biuoop.DrawSurface;
import gameobjects.Sprite;
import thegame.GameLevel;

/**
 * The following class is used to create the background
 * for the forth level: Final Four.
 * @author Ilan Bitan
 *
 */
public class FinalFourBackground implements Sprite {

    @Override
    /**
     * the method draw the sprite to the screen.
     * In this case drawing the background for Final Four.
     * @param d - the surface.
     */
    public void drawOn(DrawSurface d) {

        //create the blue color for the background
        Color color1 = new Color(36, 146, 255);
        d.setColor(color1);
        d.drawRectangle(10, 10, 780, 600);
        d.fillRectangle(10, 10, 780, 600);

        // creating the rain that comes out of the clouds
        //first cloud
        d.setColor(Color.WHITE);
        for (int i = 70; i < 170; i += 10) {
            d.drawLine(i, 350, i - 55, 800);
        }
        // second cloud
        d.setColor(Color.WHITE);
        for (int i = 550; i < 650; i += 10) {
            d.drawLine(i, 480, i - 55, 800);
        }

        // creation of the clouds
        //first cloud part
        d.setColor(Color.LIGHT_GRAY);
        d.fillCircle(550, 480, 30);
        d.fillCircle(570, 520, 33);
        d.fillCircle(70, 350, 30);
        d.fillCircle(90, 385, 33);
        //second cloud part
        Color color2 = new Color(179, 179, 179);
        d.setColor(color2);
        d.fillCircle(595, 480, 35);
        d.fillCircle(110, 345, 35);
        //third cloud part
        Color color3 = new Color(145, 145, 145);
        d.setColor(color3);
        d.fillCircle(610, 510, 26);
        d.fillCircle(635, 500, 40);
        d.fillCircle(130, 380, 26);
        d.fillCircle(150, 355, 40);


    }


    @Override
    /**
     * the method notify the sprite that time has passed.
     */
    public void timePassed() {

    }

    /**
     * The following method is used in order to add the sprite
     * object to the game.
     * @param game - the game level to which this background is added
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }


}