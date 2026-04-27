package breakout;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Separator;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class DevPanel extends VBox {

    public DevPanel(BallWorld world) {
        setSpacing(10);
        setPadding(new Insets(16));
        setPrefWidth(220);
        setBackground(new Background(new BackgroundFill(Color.web("#1e1e1e"), null, null)));
        setAlignment(Pos.TOP_LEFT);

        Text title = new Text("Developer Mode");
        title.setFont(Font.font(15));
        title.setFill(Color.YELLOW);

        getChildren().addAll(title, new Separator());

        Text brickLabel = new Text("Brick Style");
        brickLabel.setFont(Font.font(11));
        brickLabel.setFill(Color.LIGHTGRAY);

        ComboBox<String> styleBox = new ComboBox<>();
        styleBox.getItems().addAll("Pyramid", "Square", "Brick Wall", "Circle", "Full House");
        styleBox.setValue("Pyramid");
        styleBox.setMaxWidth(Double.MAX_VALUE);
        styleBox.setOnAction(e -> world.setBrickStyle(styleBox.getValue()));

        getChildren().addAll(brickLabel, styleBox, new Separator());

        Text levelLabel = new Text("Level");
        levelLabel.setFont(Font.font(11));
        levelLabel.setFill(Color.LIGHTGRAY);

        Button lvl1Btn = new Button("1");
        lvl1Btn.setFont(Font.font(12));
        lvl1Btn.setOnAction(e -> world.jumpToLevel(1));

        Button lvl2Btn = new Button("2");
        lvl2Btn.setFont(Font.font(12));
        lvl2Btn.setOnAction(e -> world.jumpToLevel(2));

        HBox levelButtons = new HBox(8, lvl1Btn, lvl2Btn);

        getChildren().addAll(levelLabel, levelButtons, new Separator());

        Text ballLabel = new Text("Balls");
        ballLabel.setFont(Font.font(11));
        ballLabel.setFill(Color.LIGHTGRAY);

        CheckBox ball1Box = new CheckBox("Ball 1");
        ball1Box.setSelected(true);
        ball1Box.setTextFill(Color.WHITE);
        ball1Box.setOnAction(e -> world.setBallActive(1, ball1Box.isSelected()));

        CheckBox ball2Box = new CheckBox("Ball 2");
        ball2Box.setSelected(true);
        ball2Box.setTextFill(Color.WHITE);
        ball2Box.setOnAction(e -> world.setBallActive(2, ball2Box.isSelected()));

        CheckBox ball3Box = new CheckBox("Ball 3");
        ball3Box.setSelected(true);
        ball3Box.setTextFill(Color.WHITE);
        ball3Box.setOnAction(e -> world.setBallActive(3, ball3Box.isSelected()));

        getChildren().addAll(ballLabel, ball1Box, ball2Box, ball3Box, new Separator());

        Text paddleLabel = new Text("Paddles");
        paddleLabel.setFont(Font.font(11));
        paddleLabel.setFill(Color.LIGHTGRAY);

        CheckBox bottomBox = new CheckBox("Bottom");
        bottomBox.setSelected(true);
        bottomBox.setTextFill(Color.WHITE);
        bottomBox.setOnAction(e -> world.setPaddleActive("bottom", bottomBox.isSelected()));

        CheckBox topBox = new CheckBox("Top");
        topBox.setSelected(true);
        topBox.setTextFill(Color.WHITE);
        topBox.setOnAction(e -> world.setPaddleActive("top", topBox.isSelected()));

        getChildren().addAll(paddleLabel, bottomBox, topBox, new Separator());

        Text scoreLabel = new Text("Score");
        scoreLabel.setFont(Font.font(11));
        scoreLabel.setFill(Color.LIGHTGRAY);

        Button resetScore = new Button("Reset");
        resetScore.setFont(Font.font(12));
        resetScore.setMaxWidth(Double.MAX_VALUE);
        resetScore.setOnAction(e -> world.getScore().setScore(0));

        getChildren().addAll(scoreLabel, resetScore);
    }
}
