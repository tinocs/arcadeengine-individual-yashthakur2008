package breakout;

import engine.Actor;
import javafx.scene.image.Image;

public class Brick extends Actor {

    public Brick() {
        String path = getClass().getClassLoader().getResource("breakoutresources/brick.png").toString();
        setImage(new Image(path));
    }

    @Override
    public void act(long now) {
    }
}
