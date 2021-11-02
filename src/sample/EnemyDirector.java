package sample;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class EnemyDirector {
    //game manager
    private GameManager gm = GameManager.getInstane();

    //enemy images
    private Image sp1 = new Image("/enemy1.png");
    private Image sp2 = new Image("/enemy2.png");

    //boss images
    private Image boss1 = new Image("/boss1.png");
    private Image boss2 = new Image("/boss2.png");


    public void BuildEnemy(TheEnBuilder builder){
        //enemy list
        Image[] EnemyImages = new Image[2];
        EnemyImages[0] = sp1;
        EnemyImages[1] = sp2;

        builder.reset();

        builder.setHealth(gm.getLevel()+2);

        builder.setWidth(200);

        builder.setHeight(200);

        builder.setType("enemy");

        builder.setCoolDownTime((int) (Math.random() * (200 + 1) + 400));

        builder.setTopY((int) (Math.random() * (400 + 1) + 100));

        builder.setBottomY((int) (Math.random() * (400 + 1) + 500));

        builder.setVelY(Math.random() * (1));

        builder.setImage( new ImagePattern(EnemyImages[(int)Math.floor(Math.random()*(2))] ) );
    }

    public void BuildBoss(TheEnBuilder builder){
        //Boss images
        Image[] BossImages = new Image[2];
        BossImages[0] = boss1;
        BossImages[1] = boss2;

        builder.reset();

        builder.setHealth(gm.getLevel()+60);

        builder.setWidth(300);

        builder.setHeight(200);

        builder.setType("boss");

        builder.setCoolDownTime((int) (Math.random() * (200 + 1) + 500));

        builder.setTopY( 50 );

        builder.setBottomY( 750 );

        builder.setVelY(Math.random() * (1));

        builder.setImage( new ImagePattern(BossImages[(int)Math.floor(Math.random()*(2))]) );
    }
}