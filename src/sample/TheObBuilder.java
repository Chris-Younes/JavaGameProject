package sample;

import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class TheObBuilder implements ObstacleBuilder{
    private GameObject obstacle = new GameObject();

    @Override
    public void reset() {
        obstacle = new GameObject();
    }

    @Override
    public void setHealth(int health) {
        obstacle.setHealth(health);
    }

    @Override
    public void setWidth(int width) {
        obstacle.setWidth(width);
    }

    @Override
    public void setHeight(int height) {
        obstacle.setHeight(height);
    }

    @Override
    public void setImage(ImagePattern image) {
        obstacle.setImage(image);
    }

    public GameObject getObstacle() {
        GameObject ob = obstacle;
        this.reset();
        return ob;
    }
}
