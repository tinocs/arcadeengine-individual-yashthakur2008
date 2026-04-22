package breakout;

import engine.World;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

public class BallWorld extends World {

    private Paddle paddle;
    private Paddle topPaddle;
    private Paddle leftPaddle;
    private Paddle rightPaddle;

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

        paddle = new Paddle();
        paddle.setX(getWidth() / 2 - paddle.getWidth() / 2);
        paddle.setY(getHeight() - paddle.getHeight() - 30);
        add(paddle);

        topPaddle = new Paddle();
        topPaddle.setX(getWidth() / 2 - topPaddle.getWidth() / 2);
        topPaddle.setY(0);
        add(topPaddle);

        leftPaddle = new Paddle();
        double pW = leftPaddle.getBoundsInLocal().getWidth();
        double pH = leftPaddle.getBoundsInLocal().getHeight();
        leftPaddle.setRotate(90);
        leftPaddle.setX(pH / 2 - pW / 2);
        leftPaddle.setY(getHeight() / 2 - pH / 2);
        add(leftPaddle);

        rightPaddle = new Paddle();
        rightPaddle.setRotate(90);
        rightPaddle.setX(getWidth() - pW / 2 - pH / 2);
        rightPaddle.setY(getHeight() / 2 - pH / 2);
        add(rightPaddle);

        setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                requestFocus();
                paddle.setX(event.getX() - paddle.getWidth() / 2);
                topPaddle.setX(event.getX() - topPaddle.getWidth() / 2);
                leftPaddle.setY(event.getY() - leftPaddle.getHeight() / 2);
                rightPaddle.setY(event.getY() - rightPaddle.getHeight() / 2);
            }
        });
    }

    @Override
    public void act(long now) {
    }
}
