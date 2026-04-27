package breakout;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class EndScreen extends VBox {

    public EndScreen(int finalScore) {
        setAlignment(Pos.CENTER);
        setSpacing(28);
        setPrefSize(600, 500);
        setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        Text congrats = new Text("YOU WIN!");
        congrats.setFont(Font.font(52));
        congrats.setFill(Color.YELLOW);

        Text scoreText = new Text("SCORE: " + finalScore);
        scoreText.setFont(Font.font(28));
        scoreText.setFill(Color.WHITE);

        Text sub = new Text("Thanks for playing");
        sub.setFont(Font.font(16));
        sub.setFill(Color.LIGHTGRAY);

        Button menuButton = new Button("Main Menu");
        menuButton.setFont(Font.font(16));
        menuButton.setOnAction(e -> Breakout.showTitleScreen());

        getChildren().addAll(congrats, scoreText, sub, menuButton);
    }
}
