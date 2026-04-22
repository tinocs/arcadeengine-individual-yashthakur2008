package breakout;

import engine.Actor;
import javafx.scene.image.Image;
import java.util.List;

public class Ball extends Actor {

    private double dx;
    private double dy;
    private boolean atBottom;

    public Ball() {
        String path = getClass().getClassLoader().getResource("breakoutresources/ball.png").toString();
        setImage(new Image(path));
        dx = 5;
        dy = -5;
        atBottom = false;
    }

    @Override
    public void act(long now) {
        move(dx, dy);

        if (getX() <= 0) {
            dx = Math.abs(dx);
        }
        if (getX() + getWidth() >= getWorld().getWidth()) {
            dx = -Math.abs(dx);
        }
        if (getY() <= 0) {
            dy = Math.abs(dy);
        }
        if (getY() + getHeight() >= getWorld().getHeight()) {
            dy = -Math.abs(dy);
            if (!atBottom) {
                atBottom = true;
                Score s = ((BallWorld) getWorld()).getScore();
                s.setScore(s.getScore() - 1000);
            }
        } else {
            atBottom = false;
        }

        List<Paddle> paddles = getIntersectingObjects(Paddle.class);
        for (Paddle p : paddles) {
            double ballCenterX = getX() + getWidth() / 2;
            double ballCenterY = getY() + getHeight() / 2;
            double paddleCenterX = p.getX() + p.getWidth() / 2;
            double paddleCenterY = p.getY() + p.getHeight() / 2;
            if (ballCenterX >= p.getX() && ballCenterX <= p.getX() + p.getWidth()) {
                if (ballCenterY < paddleCenterY) {
                    dy = -Math.abs(dy);
                } else {
                    dy = Math.abs(dy);
                }
            } else if (ballCenterY >= p.getY() && ballCenterY <= p.getY() + p.getHeight()) {
                if (ballCenterX < paddleCenterX) {
                    dx = -Math.abs(dx);
                } else {
                    dx = Math.abs(dx);
                }
            } else {
                dx = -dx;
                dy = -dy;
            }
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
