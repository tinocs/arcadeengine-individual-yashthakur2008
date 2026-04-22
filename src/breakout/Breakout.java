package breakout;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Breakout extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Breakout");
        BorderPane root = new BorderPane();
        BallWorld world = new BallWorld();
        root.setCenter(world);

        Scene scene = new Scene(root);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.RIGHT) {
                world.requestFocus();
            }
        });

        stage.setResizable(false);
        stage.setScene(scene);
        world.start();
        stage.show();
        Platform.runLater(() -> world.requestFocus());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
