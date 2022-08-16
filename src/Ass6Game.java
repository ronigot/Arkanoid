import biuoop.GUI;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class creates a game and runs it.
 */
public class Ass6Game {
    /**
     * This function creates a game and runs it.
     * @param args level numbers to run, in the specified order.
     */
    public static void main(String[] args) {
        int widthSurface = 800;
        int heightSurface = 600;
        GUI gui = new GUI("Arkanoid", widthSurface, heightSurface);
        AnimationRunner animationRunner = new AnimationRunner(gui);
        GameFlow game = new GameFlow(animationRunner, gui.getKeyboardSensor());

        // the default list
        List<LevelInformation> levels = new ArrayList<>();
        levels.add(new Level1());
        levels.add(new Level2());
        levels.add(new Level3());
        levels.add(new Level4());


        List<Integer> levelNumbers = new ArrayList<>();
        // find the level numbers
        Pattern pattern = Pattern.compile("\\b[1-4]\\b+");
        for (int i = 0; i < args.length; i++) {
            Matcher match = pattern.matcher(args[i]);
            while (match.find()) {
                String str = match.group();
                Integer num = Integer.parseInt(str);
                levelNumbers.add(num);
            }
        }

        List<LevelInformation> levelsToRun = new ArrayList<>();
        for (int j = 0; j < levelNumbers.size(); j++) {
            levelsToRun.add(levels.get(levelNumbers.get(j) - 1));
        }

        // When running without arguments, we start a game with four levels that run one after another.
        if (levelsToRun.isEmpty()) {
            levelsToRun.addAll(levels);
        }
        game.runLevels(levelsToRun);

    }
}
