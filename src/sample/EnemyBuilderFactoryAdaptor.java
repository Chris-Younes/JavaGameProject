package sample;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import java.awt.*;
import java.util.ArrayList;

public class EnemyBuilderFactoryAdaptor implements EnemyFactory{
    //game manager
    GameManager gm = GameManager.getInstane();

    @Override
    public void getEnemy(Pane pane, ArrayList<Player> obstacles) {
        System.out.println("I'm here in get enemy");
        ArrayList<Player> enemies = this.CreateEnemy();
        int x = (int) pane.getWidth();
        for(Player en : enemies) {
            System.out.println("I am in the boss setter");
            en.setX(x);
            en.setY(en.getTopY());
            obstacles.add(en);
            pane.getChildren().add(en);
            x += en.getWidth() + 100;
        }
    }

    @Override
    public ArrayList<Player> CreateEnemy() {
        ArrayList<Player> en = new ArrayList<Player>();
        EnemyDirector director = new EnemyDirector();
        TheEnBuilder builder = new TheEnBuilder();
        Player enemy;
        if(gm.getBossIsAlive() == false) {
            if (gm.getLevel() > 2) {
                for (int i = 0; i < 3; i++)
                    if (Math.random() < 0.4)
                        director.BuildEnemy(builder);
                enemy = builder.getEnemy();
                en.add(enemy);
            } else {
                director.BuildEnemy(builder);
                enemy = builder.getEnemy();
                en.add(enemy);
            }
        }else {
            System.out.println("i'm in the boss chamber");
            director.BuildBoss(builder);
            enemy = builder.getEnemy();
            en.add(enemy);
        }

        return en;

    }
}
