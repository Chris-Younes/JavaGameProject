package sample;

import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public abstract interface ObstacleBuilder {
    void reset();

    void setHealth(int health);

    void setWidth(int width);

    void setHeight(int height);

    void setImage(ImagePattern image);
}
