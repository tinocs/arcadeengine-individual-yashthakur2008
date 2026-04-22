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
        if (getWorld().isKeyPressed(KeyCode.LEFT)) {
            move(-5, 0);
        }
        if (getWorld().isKeyPressed(KeyCode.RIGHT)) {
            move(5, 0);
        }
    }
}
