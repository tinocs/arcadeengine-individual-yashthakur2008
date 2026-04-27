package breakout;

import engine.Actor;
import javafx.scene.image.Image;
import java.util.List;

public class Ball extends Actor {

    private double dx;
    private double dy;
    private boolean atBottom;
    private double startX;
    private double startY;

    public Ball() {
        String path = getClass().getClassLoader().getResource("breakoutresources/ball.png").toString();
        setImage(new Image(path));
        dx = 5;
        dy = -5;
        atBottom = false;
    }

    public void setStart(double x, double y) {
        startX = x;
        startY = y;
        setX(x);
        setY(y);
    }

    public void reset() {
        setX(startX);
        setY(startY);
        dx = 5;
        dy = -5;
        atBottom = false;
    }

    @Override
    public void act(long now) {
        if (((BallWorld) getWorld()).isPaused()) {
            Paddle p = ((BallWorld) getWorld()).getPaddle();
            setX(p.getX() + p.getWidth() / 2 - getWidth() / 2);
            setY(p.getY() - getHeight() - 2);
            return;
        }
        move(dx, dy);

        if (getX() <= 0) {
            dx = Math.abs(dx);
        }
        if (getX() + getWidth() >= getWorld().getWidth()) {
            dx = -Math.abs(dx);
        }
        if (getY() <= 40) {
            dy = Math.abs(dy);
        }
        if (getY() + getHeight() >= getWorld().getHeight()) {
            dy = -Math.abs(dy);
            // only subtract once when it hits the bottom, not every frame
            if (!atBottom) {
                atBottom = true;
                Score s = ((BallWorld) getWorld()).getScore();
                s.setScore(s.getScore() - 1000);
                ((BallWorld) getWorld()).loseLife();
            }
        } else {
            atBottom = false;
        }

        List<Paddle> paddles = getIntersectingObjects(Paddle.class);
        for (Paddle p : paddles) {
            dy = -dy;
        }

        List<Brick> bricks = getIntersectingObjects(Brick.class);
        for (Brick brick : bricks) {
            double ballCenterX = getX() + getWidth() / 2;
            double ballCenterY = getY() + getHeight() / 2;
            if (ballCenterX >= brick.getX() && ballCenterX <= brick.getX() + brick.getWidth()) {
                dy = -dy;
            } else if (ballCenterY >= brick.getY() && ballCenterY <= brick.getY() + brick.getHeight()) {
                dx = -dx;
            } else {
                dx = -dx;
                dy = -dy;
            }
            getWorld().remove(brick);
            Score s = ((BallWorld) getWorld()).getScore();
            s.setScore(s.getScore() + 100);
        }
    }
}
