package engine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public abstract class World extends Pane {

    private AnimationTimer timer;
    private boolean stopped;
    private List<KeyCode> keysPressed;
    private boolean widthInitialized;
    private boolean heightInitialized;

    public World() {
        stopped = true;
        keysPressed = new ArrayList<>();
        widthInitialized = false;
        heightInitialized = false;

        widthProperty().addListener((obs, oldVal, newVal) -> {
            if (!widthInitialized && newVal.doubleValue() > 0) {
                widthInitialized = true;
                if (heightInitialized) {
                    onDimensionsInitialized();
                }
            }
        });

        heightProperty().addListener((obs, oldVal, newVal) -> {
            if (!heightInitialized && newVal.doubleValue() > 0) {
                heightInitialized = true;
                if (widthInitialized) {
                    onDimensionsInitialized();
                }
            }
        });

        sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                requestFocus();
            }
        });

        setOnKeyPressed(event -> keysPressed.add(event.getCode()));
        setOnKeyReleased(event -> keysPressed.remove(event.getCode()));

        timer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate < 16000000) return;
                lastUpdate = now;

                act(now);

                List<Actor> actors = getObjects(Actor.class);
                for (Actor actor : actors) {
                    if (actor.getWorld() == World.this) {
                        actor.act(now);
                    }
                }
            }
        };
    }

    public void start() {
        stopped = false;
        timer.start();
    }

    public void stop() {
        stopped = true;
        timer.stop();
    }

    public boolean isStopped() {
        return stopped;
    }

    public void add(Actor actor) {
        getChildren().add(actor);
        actor.addedToWorld();
    }

    public void remove(Actor actor) {
        getChildren().remove(actor);
    }

    public <A extends Actor> List<A> getObjects(Class<A> cls) {
        List<A> result = new ArrayList<>();
        for (Node child : getChildren()) {
            if (cls.isInstance(child)) {
                result.add(cls.cast(child));
            }
        }
        return result;
    }

    public <A extends Actor> List<A> getObjectsAt(double x, double y, Class<A> cls) {
        List<A> result = new ArrayList<>();
        for (A actor : getObjects(cls)) {
            Bounds bounds = actor.getBoundsInParent();
            if (bounds.contains(x, y)) {
                result.add(actor);
            }
        }
        return result;
    }

    public boolean isKeyPressed(KeyCode code) {
        return keysPressed.contains(code);
    }

    public abstract void onDimensionsInitialized();

    public abstract void act(long now);
}
