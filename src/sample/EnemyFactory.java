package sample;

import javafx.scene.layout.Pane;
import java.util.ArrayList;

public interface EnemyFactory {
    void getEnemy(Pane pane, ArrayList<Player> obstacles);

    ArrayList<Player> CreateEnemy();
}
