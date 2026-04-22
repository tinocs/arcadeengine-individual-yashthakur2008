package breakout;

import engine.Actor;
import javafx.scene.image.Image;

public class Ball extends Actor {

    private double dx;
    private double dy;

    public Ball() {
        // Load ball image using the recommended resource-access pattern (Slide 37)
        String path = getClass().getClassLoader().getResource("breakoutresources/ball.png").toString();
        setImage(new Image(path));

        // Default speeds between 4 and 7 as required by Slide 35
        dx = 5;
        dy = 5;
    }

    @Override
    public void act(long now) {
        // Move the ball by dx, dy each frame
        move(dx, dy);

        // Bounce off left edge (upper-left corner is the x origin, so this is simple)
        if (getX() <= 0) {
            dx = Math.abs(dx);
        }

        // Bounce off right edge (must factor in ball width per Slide 35 note)
        if (getX() + getWidth() >= getWorld().getWidth()) {
            dx = -Math.abs(dx);
        }

        // Bounce off top edge
        if (getY() <= 0) {
            dy = Math.abs(dy);
        }

        // Bounce off bottom edge (must factor in ball height per Slide 35 note)
        if (getY() + getHeight() >= getWorld().getHeight()) {
            dy = -Math.abs(dy);
        }
    }
}
