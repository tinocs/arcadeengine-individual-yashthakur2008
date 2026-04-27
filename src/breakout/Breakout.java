package breakout;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Breakout extends Application {

    public static Stage stage;
    public static boolean devMode = false;
    private static Stage devStage;

    @Override
    public void start(Stage stage) {
        Breakout.stage = stage;
        stage.setTitle("Breakout");
        stage.setResizable(false);
        showTitleScreen();
        stage.show();
    }

    public static void showTitleScreen() {
        devMode = false;
        if (devStage != null) {
            devStage.close();
            devStage = null;
        }
        stage.setScene(new Scene(new TitleScreen()));
    }

    public static void showEndScreen(int finalScore) {
        if (devStage != null) {
            devStage.close();
            devStage = null;
        }
        stage.setScene(new Scene(new EndScreen(finalScore)));
    }

    public static void startGame() {
        BallWorld world = new BallWorld();
        BorderPane root = new BorderPane();
        root.setCenter(world);

        Scene scene = new Scene(root);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.RIGHT) {
                world.requestFocus();
            }
        });

        stage.setScene(scene);
        world.start();
        Platform.runLater(() -> world.requestFocus());

        if (devMode) {
            devStage = new Stage();
            devStage.setTitle("Developer Mode");
            devStage.initStyle(StageStyle.UTILITY);
            devStage.setResizable(false);
            devStage.setAlwaysOnTop(true);
            devStage.setScene(new Scene(new DevPanel(world)));
            devStage.setX(stage.getX() + stage.getWidth() + 10);
            devStage.setY(stage.getY());
            devStage.show();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
