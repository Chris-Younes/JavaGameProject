package sample;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public interface EnemyBuilder {
    void reset();

    void setHealth(int health);

    void setWidth(int width);

    void setHeight(int height);

    void setType(String type);

    void setCoolDownTime(int time);

    void setTopY(int y);

    void setBottomY(int y);

    void setVelY(double y);

    void setImage(ImagePattern image);
}
