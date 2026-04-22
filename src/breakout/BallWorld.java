package breakout;

import engine.World;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyCode;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import java.util.List;

public class BallWorld extends World {

    private Paddle paddle;
    private Paddle topPaddle;
    private double brickW;
    private double brickH;

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

        Line topBorder = new Line(0, 0, getWidth(), 0);
        topBorder.setStroke(Color.RED);
        topBorder.setStrokeWidth(3);
        getChildren().add(topBorder);

        Brick sample = new Brick();
        brickW = sample.getBoundsInLocal().getWidth();
        brickH = sample.getBoundsInLocal().getHeight();

        setupPyramid();

        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                requestFocus();
            }
        });

        setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                requestFocus();
                if (!isKeyPressed(KeyCode.LEFT) && !isKeyPressed(KeyCode.RIGHT)) {
                    paddle.setX(event.getX() - paddle.getWidth() / 2);
                    topPaddle.setX(event.getX() - topPaddle.getWidth() / 2);
                }
            }
        });
    }

    public void setBrickStyle(String style) {
        clearBricks();
        switch (style) {
            case "Pyramid":   setupPyramid();   break;
            case "Square":    setupSquare();    break;
            case "rick Wall": setupBrickWall(); break;
            case "Circle":     setupCircle();    break;
            case "Full House": setupFullHouse(); break;
        }
    }

    private void clearBricks() {
        List<Brick> bricks = getObjects(Brick.class);
        for (Brick b : bricks) {
            remove(b);
        }
    }

    private void setupPyramid() {
        // pyrameeeeeeeeeeeed :>
        int rows = 5;
        for (int row = 0; row < rows; row++) {
            int bricksInRow = rows - row;
            double startX = (getWidth() - bricksInRow * brickW) / 2;
            for (int col = 0; col < bricksInRow; col++) {
                Brick brick = new Brick();
                brick.setX(startX + col * brickW);
                brick.setY(80 + row * brickH);
                add(brick);
            }
        }
    }

    private void setupSquare() {
        int size = 6;
        double startX = (getWidth() - size * brickW) / 2;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Brick brick = new Brick();
                brick.setX(startX + col * brickW);
                brick.setY(80 + row * brickH);
                add(brick);
            }
        }
    }

    private void setupBrickWall() {
        int cols = 8;
        int rows = 5;
        for (int row = 0; row < rows; row++) {
            double offset = (row % 2 == 0) ? 0 : brickW / 2;
            double startX = (getWidth() - cols * brickW) / 2;
            for (int col = 0; col < cols; col++) {
                Brick brick = new Brick();
                brick.setX(startX + offset + col * brickW);
                brick.setY(80 + row * brickH);
                add(brick);
            }
        }
    }

    private void setupCircle() {
        int numBricks = 24;
        double radius = 120;
        double centerX = getWidth() / 2;
        double centerY = getHeight() / 2 - 50;
        for (int i = 0; i < numBricks; i++) {
            double angle = 2 * Math.PI * i / numBricks;
            Brick brick = new Brick();
            brick.setX(centerX + radius * Math.cos(angle) - brickW / 2);
            brick.setY(centerY + radius * Math.sin(angle) - brickH / 2);
            add(brick);
        }
    }

    private void setupFullHouse() {
        int cols = (int)(getWidth() / brickW) + 1;
        int rows = (int)((getHeight() / 2) / brickH);
        for (int row = 0; row < rows; row++) {
            double offset = (row % 2 == 0) ? 0 : brickW / 2;
            for (int col = 0; col < cols; col++) {
                Brick brick = new Brick();
                brick.setX(offset + col * brickW - brickW / 2);
                brick.setY(row * brickH);
                add(brick);
            }
        }
    }

    @Override
    public void act(long now) {
    }
}
