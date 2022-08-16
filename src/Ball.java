import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Balls have size (radius), color, location (a Point) and velocity.
 * Balls also know how to draw themselves on a DrawSurface.
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment game;

    /**
     * constructor.
     * @param center the center of the ball
     * @param r the radius of the ball
     * @param color the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.v = new Velocity(0, 0);
    }

    /**
     * constructor.
     * @param center the center of the ball
     * @param r the radius of the ball
     * @param color the color of the ball
     * @param game ball's game environment
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment game) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.game = game;
        this.v = new Velocity(0, 0);
    }

    /**
     * constructor.
     * @param x the x value of center of the ball
     * @param y the y value of center of the ball
     * @param r the radius of the ball
     * @param color the color of the ball
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this(new Point(x, y), r, color);
    }

    /**
     * The function returns the x value of the center of the ball.
     * @return the x value of the center of the ball
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * The function returns the y value of the center of the ball.
     * @return the y value of the center of the ball
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * The function returns the radius of the ball.
     * @return radius of the ball
     */
    public int getSize() {
        return this.r;
    }

    /**
     * The function returns the color of the ball.
     * @return color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * draw the ball on the given DrawSurface.
     * @param surface surface to draw
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.WHITE);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
    }

    /**
     * The function sets ths velocity of the ball.
     * @param newV velocity of the ball
     */
    public void setVelocity(Velocity newV) {
        this.v = newV;
    }

    /**
     * The function sets ths velocity of the ball.
     * @param dx change in the x axis
     * @param dy change in the y axis
     */
    public void setVelocity(double dx, double dy) {
        this.setVelocity(new Velocity(dx, dy));
    }

    /**
     * The function returns the velocity of the ball.
     * @return velocity of the ball
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * he function returns the GameEnvironment of the ball.
     * @return GameEnvironment of the ball
     */
    public GameEnvironment getGameEnvironment() {
        return this.game;
    }

    /**
     * The function sets the GameEnvironment of the ball.
     * @param newGame  GameEnvironment for the ball
     */
    public void setGameEnvironment(GameEnvironment newGame) {
        this.game = newGame;
    }

    /**
     * The function changes the position of the ball.
     */
    public void moveOneStep() {
       Line trajectory = new Line(this.center, this.getVelocity().applyToPoint(this.center));
       CollisionInfo coll = this.game.getClosestCollision(trajectory);
       // if no collision exists, move the ball according to his velocity
       if (coll == null) {
           this.center = this.getVelocity().applyToPoint(this.center);
       } else {
           double x = coll.collisionPoint().getX();
           double y = coll.collisionPoint().getY();
           // change the position of the ball
           // according to the direction of the collision
           if (this.v.getDx() > 0) {
               x -= 1;
           } else {
               if (this.v.getDx() != 0) {
                   x += 1;
               }
           }
           if (this.v.getDy() > 0) {
               y -= 1;
           } else {
               if (this.v.getDy() != 0) {
                   y += 1;
               }
           }
           this.center.setX(x);
           this.center.setY(y);
           this.setVelocity(coll.collisionObject().hit(this, coll.collisionPoint(), this.v));
       }
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * The function removes this ball from the given game.
     * @param g the game from which we will remove the ball
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}
