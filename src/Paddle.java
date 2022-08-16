import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The Paddle is the player in the game.
 * It is a rectangle that is controlled by the arrow keys,
 * and moves according to the player key presses.
 */
public class Paddle implements Sprite, Collidable {
    private Rectangle rec;
    private Color color;
    private KeyboardSensor keyboard;
    private double rightBorder;
    private double leftBorder;
    private int paddleSpeed;

    /**
     * constructor.
     * @param rec paddle's shape - a rectangle
     * @param color paddle's color
     * @param keyboard paddle's keyboard
     * @param rightBorder right movement boundary
     * @param leftBorder left movement boundary
     * @param paddleSpeed paddle's speed
     */
    public Paddle(Rectangle rec, Color color, KeyboardSensor keyboard,
                  double rightBorder, double leftBorder, int paddleSpeed) {
        this.rec = rec;
        this.color = color;
        this.keyboard = keyboard;
        this.rightBorder = rightBorder;
        this.leftBorder = leftBorder;
        this.paddleSpeed = paddleSpeed;
    }

    /**
     * move the paddle some pixels to the left.
     */
    public void moveLeft() {
        double x = this.rec.getUpperLeft().getX();
        // if the paddle will move out of the border if it moves pixels,
        // we will only move it to the limit.
        double newX = Math.max(x - this.paddleSpeed, this.leftBorder);
        this.rec.setUpperLeft(new Point(newX, this.rec.getUpperLeft().getY()));
    }

    /**
     * move the paddle some pixels to the right.
     */
    public void moveRight() {
        double x = this.rec.getUpperLeft().getX();
        double newX;
        // if the paddle will move out of the border if it moves pixels,
        // we will only move it to the limit.
        if (x + this.rec.getWidth() + this.paddleSpeed < this.rightBorder) {
            newX = this.rec.getUpperLeft().getX() + this.paddleSpeed;
        } else {
            newX = this.rightBorder - this.rec.getWidth();
        }
        this.rec.setUpperLeft(new Point(newX, this.rec.getUpperLeft().getY()));
    }

    // Sprite
    @Override
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }

    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(), (int) this.rec.getHeight());
        d.setColor(Color.WHITE);
        d.drawRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(), (int) this.rec.getHeight());

    }

    // Collidable
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Line rightEdge = new Line(this.rec.getLowerRight(), this.rec.getUpperRight());
        Line leftEdge = new Line(this.rec.getUpperLeft(), this.rec.getLowerLeft());

        double xCollision = collisionPoint.getX();

        double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
        double angle;

        // collision with the left edge.
        if (leftEdge.containsPoint(collisionPoint)) {
            angle = -60;
        } else {
            // collision with the right edge.
            if (rightEdge.containsPoint(collisionPoint)) {
                angle = 60;
            } else {
                // collision with the upper edge.
                double xStart = this.rec.getUpperLeft().getX();
                double part = this.rec.getWidth() / 5;
                int partCollision = 0;
                // finding the collision segment
                for (int i = 0; i < 5; i++) {
                    if (xCollision <= xStart + part * (i + 1) && xCollision > xStart + part * i) {
                        partCollision = i + 1;
                        break;
                    }
                }
                // change the angle according to the collision segment
                switch (partCollision) {
                    case 1:
                        angle = -60;
                        break;
                    case 2:
                        angle = -30;
                        break;
                    case 3:
                        angle = 0;
                        break;
                    case 4:
                        angle = 30;
                        break;
                    default: angle = 60;
                }
            }
        }
        return Velocity.fromAngleAndSpeed(angle, speed);
    }

    // Add this paddle to the game.
    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

}
