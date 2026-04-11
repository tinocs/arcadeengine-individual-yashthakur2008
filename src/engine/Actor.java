package engine;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.ImageView;

public abstract class Actor extends ImageView {

    public Actor() {
    }

    public void move(double dx, double dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }

    public World getWorld() {
        return (World) getParent();
    }

    public double getWidth() {
        return getBoundsInParent().getWidth();
    }

    public double getHeight() {
        return getBoundsInParent().getHeight();
    }


    public <A extends Actor> List<A> getIntersectingObjects(Class<A> cls) {
        List<A> result = new ArrayList<>();
        World world = getWorld();
        if (world == null) return result;
        for (A other : world.getObjects(cls)) {
            if (other != this && getBoundsInParent().intersects(other.getBoundsInParent())) {
                result.add(other);
            }
        }
        return result;
    }


    public <A extends Actor> A getOneIntersectingObject(Class<A> cls) {
        List<A> list = getIntersectingObjects(cls);
        if (list.isEmpty()) return null;
        return list.get(0);
    }

    public abstract void act(long now);

    public void addedToWorld() {
    }
}
