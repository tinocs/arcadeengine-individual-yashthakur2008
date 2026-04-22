package breakout;

import engine.Actor;
import javafx.scene.image.Image;

public class Paddle extends Actor {

    public Paddle() {
        String path = getClass().getClassLoader().getResource("breakoutresources/paddle.png").toString();
        setImage(new Image(path));
    }

    @Override
    public void act(long now) {
    }
}
