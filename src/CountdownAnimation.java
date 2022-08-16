import biuoop.DrawSurface;

import java.awt.Color;

/**
 * CountdownAnimation is an on-screen countdown from 3 to 1,
 * which will show up at the beginning of each turn.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private double countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private boolean first;
    private long start;

    /**
     * constructor.
     * @param numOfSeconds seconds
     * @param countFrom the initial count
     * @param gameScreen the screen
     * */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.first = true;
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.first) {
            this.start = System.currentTimeMillis();
            this.first = false;
        }
        this.gameScreen.drawAllOn(d);
        long currentMillisecond = System.currentTimeMillis();
        double singleCountLength = this.numOfSeconds / this.countFrom;
        int number = (int) ((1 + this.countFrom) - (double) (currentMillisecond - this.start)
                / (singleCountLength * 1000.0D));
        // displays the countdown on the screen
        if (number > 0) {
            d.setColor(Color.MAGENTA);
            d.drawText(385, 400, String.valueOf((number)), 50);
        }
        if ((double) (currentMillisecond - this.start) > (this.numOfSeconds * 1000.0D)) {
            this.stop = true;
        }

    }

    @Override
    public boolean shouldStop() {
        return this.stop;

    }
}
