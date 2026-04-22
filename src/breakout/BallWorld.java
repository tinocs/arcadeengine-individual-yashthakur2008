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

        Ball ball2 = new Ball();
        ball2.setX(getWidth() / 4 - ball2.getWidth() / 2);
        ball2.setY(getHeight() / 3 - ball2.getHeight() / 2);
        add(ball2);

        Ball ball3 = new Ball();
        ball3.setX(3 * getWidth() / 4 - ball3.getWidth() / 2);
        ball3.setY(getHeight() / 3 - ball3.getHeight() / 2);
        add(ball3);

        Paddle paddle = new Paddle();
        paddle.setX(getWidth() / 2 - paddle.getWidth() / 2);
        paddle.setY(getHeight() - paddle.getHeight() - 30);
        add(paddle);
    }

    @Override
    public void act(long now) {
    }
}
