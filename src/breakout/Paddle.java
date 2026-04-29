package breakout;

import engine.Actor;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Paddle extends Actor {

    public Paddle() {
        String path = getClass().getClassLoader().getResource("breakoutresources/paddle.png").toString();
        setImage(new Image(path));
    }

    @Override
    public void act(long now) {
        BallWorld w = (BallWorld) getWorld();
        if (w.isKeyPressed(KeyCode.LEFT)) {
            move(-5, 0);
            if (getX() < 5) setX(5);
        }
        if (w.isKeyPressed(KeyCode.RIGHT)) {
            move(5, 0);
            if (getX() + getWidth() > w.getWidth() - 5) setX(w.getWidth() - getWidth() - 5);
        }
    }
}
