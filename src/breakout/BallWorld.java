package breakout;

import engine.World;

public class BallWorld extends World {

    public BallWorld() {
        setPrefSize(600, 500);
    }

    @Override
    public void onDimensionsInitialized() {
        Ball ball = new Ball();
        ball.setX(getWidth() / 2 - ball.getWidth() / 2);
        ball.setY(getHeight() / 2 - ball.getHeight() / 2);
        add(ball);

        Paddle paddle = new Paddle();
        paddle.setX(getWidth() / 2 - paddle.getWidth() / 2);
        paddle.setY(getHeight() - paddle.getHeight() - 30);
        add(paddle);
    }

    @Override
    public void act(long now) {
    }
}
