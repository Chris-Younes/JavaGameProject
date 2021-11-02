package sample;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class TheEnBuilder implements EnemyBuilder {
    Player enemy = new Player();

    @Override
    public void reset() {
        enemy = new Player();
    }

    @Override
    public void setHealth(int health) {
        enemy.setHealth(health);
    }

    @Override
    public void setWidth(int width) {
        enemy.setWidth(width);
    }

    @Override
    public void setHeight(int height) {
        enemy.setHeight(height);
    }

    @Override
    public void setType(String type) {
        enemy.setPlayerType(type);
    }

    @Override
    public void setCoolDownTime(int time) {
        enemy.setCoolDownTime(time);
    }

    @Override
    public void setTopY(int y){
        enemy.setTopY(y);
    }

    @Override
    public void setBottomY(int y){
        enemy.setBottomY(y);
    }

    @Override
    public void setVelY(double y) {
        enemy.setVelY(y);
    }

    @Override
    public void setImage(ImagePattern image) {
        enemy.setImage(image);
    }

    public Player getEnemy(){
        return enemy;
    }
}
