package breakout;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.Optional;

public class TitleScreen extends VBox {

    public TitleScreen() {
        setAlignment(Pos.CENTER);
        setSpacing(30);
        setPrefSize(600, 500);
        setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        Text title = new Text("BREAKOUT");
        title.setFont(Font.font(64));
        title.setFill(Color.YELLOW);

        Button playButton = new Button("Play");
        playButton.setFont(Font.font(22));
        playButton.setOnAction(e -> Breakout.startGame());

        Button devButton = new Button("Developer Mode");
        devButton.setFont(Font.font(13));
        devButton.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Developer Mode");
            dialog.setHeaderText("Enter password:");
            dialog.setContentText("Password:");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(password -> {
                // very secret password fr
                if (password.equals("secretsauce")) {
                    Breakout.devMode = true;
                    Breakout.startGame();
                }
            });
        });

        getChildren().addAll(title, playButton, devButton);
    }
}
