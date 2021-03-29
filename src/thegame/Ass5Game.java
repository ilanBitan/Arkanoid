package thegame;

import java.util.ArrayList;
import java.util.List;

import biuoop.GUI;
import levels.DirectHit;
import levels.FinalFour;
import levels.Green3;
import levels.LevelInformation;
import levels.WideEasy;

/**
 * The main class that runs the game.
 * the class creates a new game, initializes it and runs it.
 *
 * @author Ilan Bitan
 */
public class Ass5Game {

    /**
     * the main method of the main class.
     * @param args - none.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid ", 800, 600);
        AnimationRunner runner = new AnimationRunner(gui);
        GameFlow flow = new GameFlow(runner, gui.getKeyboardSensor(), gui);
        List<LevelInformation> levelList = new ArrayList<LevelInformation>();
        ArrayList<Integer> levelsToPlay = new ArrayList<Integer>();
        LevelInformation level1 = new DirectHit();
        LevelInformation level2 = new WideEasy();
        LevelInformation level3 = new Green3();
        LevelInformation level4 = new FinalFour();
        if (args.length == 0) {
            for (int i = 0; i < 4; i++) {
                levelsToPlay.add(i);
            }
        } else {
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("1") || args[i].equals("2") || args[i].equals("3")
                        || args[i].equals("4")) {
                    levelsToPlay.add(Integer.parseInt(args[i]));
                }
            }
        }
        if (levelsToPlay.isEmpty()) {
            System.out.println("arguments error :(");
            gui.close();
        }

        for (int i = 0; i < levelsToPlay.size(); i++) {
            switch (levelsToPlay.get(i)) {
            case 1:
                levelList.add(level1);
                break;
            case 2:
                levelList.add(level2);
                break;
            case 3:
                levelList.add(level3);
                break;
            case 4:
                levelList.add(level4);
                break;
            default:
                break;
            }
        }
        // flow of game
        flow.runLevels(levelList);
        // game ending screen
        runner.run(new EndScreen(gui.getKeyboardSensor(), flow.getScore(),
                flow.getLives()));
        // gui close
        gui.close();

    }
}
