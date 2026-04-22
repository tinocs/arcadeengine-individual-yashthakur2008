package breakout;

import engine.Actor;
import javafx.scene.image.Image;

public class Ball extends Actor {

    private double dx;
    private double dy;

    public Ball() {
        String path = getClass().getClassLoader().getResource("breakoutresources/ball.png").toString();
        setImage(new Image(path));
        dx = 5;
        dy = 5;
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
        }
    }
}
