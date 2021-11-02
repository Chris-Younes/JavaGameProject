package sample;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public interface ObstacleFactory {
    public void getObstacle(Pane pane, ArrayList<GameObject> obstacles);

    public abstract ArrayList<GameObject> CreateObstacle();
}
