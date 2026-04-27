package breakout;

import engine.Actor;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class Brick extends Actor {

    private boolean dying;

    public Brick() {
        String path = getClass().getClassLoader().getResource("breakoutresources/brick.png").toString();
        setImage(new Image(path));
        dying = false;
    }

    public Brick(int type) {
        String file = (type == 2) ? "breakoutresources/brick2.png" : "breakoutresources/brick.png";
        String path = getClass().getClassLoader().getResource(file).toString();
        setImage(new Image(path));
        dying = false;
    }

    public boolean isDying() {
        return dying;
    }

    public void startDeathAnimation() {
        dying = true;

        FadeTransition ft = new FadeTransition(Duration.millis(200), this);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);

        ScaleTransition st = new ScaleTransition(Duration.millis(200), this);
        st.setToX(1.5);
        st.setToY(1.5);

        ParallelTransition pt = new ParallelTransition(ft, st);
        pt.setOnFinished(e -> getWorld().remove(this));
        pt.play();
    }

    @Override
    public void act(long now) {
    }
}
