package breakout;

import engine.World;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyCode;
import javafx.event.EventHandler;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.List;

public class BallWorld extends World {

    private static final String[][] LEVELS = {
        {"5 9", "000010000", "000111000", "001111100", "011111110", "111111111"},
        {"4 10", "1212121212", "2121212121", "1212121212", "2121212121"}
    };

    private Paddle paddle;
    private Paddle topPaddle;
    private double brickW;
    private double brickH;
    private Score score;
    private Text levelLabel;
    private Ball ball1;
    private Ball ball2;
    private Ball ball3;
    private int level;
    private boolean inLevelMode;
    private int lives;
    private Text livesLabel;
    private boolean isPaused;
    private Text pauseMessage;
    private boolean isGameOver = false;

    public BallWorld() {
        setPrefSize(600, 500);
        setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        level = 1;
        inLevelMode = true;
        lives = 3;
        isPaused = true;
    }

    @Override
    public void onDimensionsInitialized() {
        Line topBorder = new Line(0, 40, getWidth(), 40);
        topBorder.setStroke(Color.YELLOW);
        topBorder.setStrokeWidth(2);
        getChildren().add(topBorder);

        Brick sample = new Brick();
        brickW = sample.getBoundsInLocal().getWidth();
        brickH = sample.getBoundsInLocal().getHeight();

        ball1 = new Ball();
        ball1.setStart(getWidth() / 2 - ball1.getWidth() / 2, getHeight() / 2 - ball1.getHeight() / 2);
        add(ball1);

        ball2 = new Ball();
        ball2.setStart(getWidth() / 4 - ball2.getWidth() / 2, getHeight() / 3 - ball2.getHeight() / 2);
        add(ball2);

        ball3 = new Ball();
        ball3.setStart(3 * getWidth() / 4 - ball3.getWidth() / 2, getHeight() / 3 - ball3.getHeight() / 2);
        add(ball3);

        paddle = new Paddle();
        paddle.setX(getWidth() / 2 - paddle.getWidth() / 2);
        paddle.setY(getHeight() - paddle.getHeight() - 30);
        add(paddle);

        topPaddle = new Paddle();
        topPaddle.setX(getWidth() / 2 - topPaddle.getWidth() / 2);
        topPaddle.setY(40);
        add(topPaddle);

        score = new Score();
        score.setX(getWidth() - 210);
        score.setY(28);
        getChildren().add(score);

        levelLabel = new Text("LEVEL 1");
        levelLabel.setFont(Font.font(14));
        levelLabel.setFill(Color.YELLOW);
        levelLabel.setX(10);
        levelLabel.setY(28);
        getChildren().add(levelLabel);

        livesLabel = new Text("LIVES: 3");
        livesLabel.setFont(Font.font(14));
        livesLabel.setFill(Color.WHITE);
        livesLabel.setX(getWidth() / 2 - 40);
        livesLabel.setY(28);
        getChildren().add(livesLabel);

        pauseMessage = new Text("PRESS SPACE TO START");
        pauseMessage.setFont(Font.font(16));
        pauseMessage.setFill(Color.WHITE);
        pauseMessage.setX(getWidth() / 2 - 145);
        pauseMessage.setY(getHeight() / 2 + 60);
        getChildren().add(pauseMessage);

        loadLevel(1);
        setOnKeyPressed(e -> {
            if (isGameOver && e.getCode() == KeyCode.SPACE) {
                Breakout.showTitleScreen();
            } else if (isPaused && e.getCode() == KeyCode.SPACE) {
                isPaused = false;
                pauseMessage.setVisible(false);
            }
        });
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                requestFocus();
                if (isGameOver) {
                    Breakout.showTitleScreen();
                } else if (isPaused) {
                    isPaused = false;
                    pauseMessage.setVisible(false);
                }
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

    public Score getScore() {
        return score;
    }

    public void loseLife() {
        lives--;
        livesLabel.setText("LIVES: " + lives);

        if (lives <= 0) {
            isPaused = true;
            isGameOver = true;

            pauseMessage.setText("GAME OVER\nPress SPACE or click to return");
            pauseMessage.setVisible(true);
        } else {
            isPaused = true;
            pauseMessage.setText("PRESS SPACE TO CONTINUE");
            pauseMessage.setVisible(true);
        }
    }

    public boolean isPaused() {
        return isPaused;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public int getLevel() {
        return level;
    }

    public void loadLevel(int lvl) {
        clearBricks();
        level = lvl;
        if (levelLabel != null) {
            levelLabel.setText("LEVEL " + lvl);
        }
        isPaused = true;
        isGameOver = false;
        String[] data = LEVELS[lvl - 1];
        String[] dims = data[0].split(" ");
        int rows = Integer.parseInt(dims[0]);
        int cols = Integer.parseInt(dims[1]);
        double startX = (getWidth() - cols * brickW) / 2;
        for (int row = 0; row < rows; row++) {
            String line = data[row + 1];
            for (int col = 0; col < cols; col++) {
                int type = Character.getNumericValue(line.charAt(col));
                if (type > 0) {
                    Brick brick = new Brick(type);
                    brick.setX(startX + col * brickW);
                    brick.setY(80 + row * brickH);
                    add(brick);
                }
            }
        }
    }

    public void jumpToLevel(int lvl) {
        inLevelMode = true;
        score.setScore(0);
        resetBalls();
        loadLevel(lvl);
    }

    public void setBallActive(int ballNum, boolean active) {
        Ball ball = (ballNum == 1) ? ball1 : (ballNum == 2) ? ball2 : ball3;
        if (active) {
            if (!getChildren().contains(ball)) {
                ball.reset();
                add(ball);
            }
        } else {
            if (getChildren().contains(ball)) {
                remove(ball);
            }
        }
    }

    public void setPaddleActive(String which, boolean active) {
        Paddle p = which.equals("top") ? topPaddle : paddle;
        if (active) {
            if (!getChildren().contains(p)) {
                add(p);
            }
        } else {
            if (getChildren().contains(p)) {
                remove(p);
            }
        }
    }

    private void resetBalls() {
        ball1.reset();
        ball2.reset();
        ball3.reset();
    }

    private void clearBricks() {
        List<Brick> bricks = getObjects(Brick.class);
        for (Brick b : bricks) {
            remove(b);
        }
    }

    public void setBrickStyle(String style) {
        inLevelMode = false;
        score.setScore(0);
        resetBalls();
        clearBricks();
        isPaused = true;
        isGameOver = false;
        if (pauseMessage != null) {
            pauseMessage.setText("PRESS SPACE TO START");
            pauseMessage.setVisible(true);
        }
        if (levelLabel != null) {
            levelLabel.setText("FREE PLAY");
        }
        switch (style) {
            case "Pyramid":    setupPyramid();   break;
            case "Square":     setupSquare();    break;
            case "Brick Wall": setupBrickWall(); break;
            case "Circle":     setupCircle();    break;
            case "Full House": setupFullHouse(); break;
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
        if (score == null) return;
        if (isPaused && !isGameOver && isKeyPressed(KeyCode.SPACE)) {
            isPaused = false;
            pauseMessage.setVisible(false);
        }
        if (inLevelMode && !isPaused && getObjects(Brick.class).isEmpty()) {
            level++;
            if (level <= 2) {
                score.setScore(0);
                resetBalls();
                loadLevel(level);
            } else {
                isPaused = true;
                isGameOver = true;
                pauseMessage.setText("YOU WIN!\nPress SPACE or click to return");
                pauseMessage.setVisible(true);
            }
        }
    }
}
