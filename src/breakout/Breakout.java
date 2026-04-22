package breakout;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Breakout extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Breakout");
        BorderPane root = new BorderPane();
        BallWorld world = new BallWorld();
        root.setCenter(world);

        Label label = new Label("Brick Style: ");
        label.setFocusTraversable(false);

        ComboBox<String> brickStyles = new ComboBox<>();
        brickStyles.getItems().addAll("Pyramid", "Square", "rick Wall", "Circle", "Full House");
        brickStyles.setValue("Pyramid");
        brickStyles.setFocusTraversable(false);
        brickStyles.setOnAction(e -> {
            world.setBrickStyle(brickStyles.getValue());
            world.requestFocus();
        });
        brickStyles.setOnHidden(e -> world.requestFocus());

        HBox toolbar = new HBox(10, label, brickStyles);
        toolbar.setFocusTraversable(false);
        root.setTop(toolbar);

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
        world.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
