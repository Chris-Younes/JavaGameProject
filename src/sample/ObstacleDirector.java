package sample;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class ObstacleDirector {
    GameManager gm = GameManager.getInstane();

    public void ConstructFirstLine(TheObBuilder builder){
        builder.reset();
        if (gm.getLevel()==1)
            builder.setHealth(gm.getLevel());
        else if(gm.getLevel()==2)
            builder.setHealth(gm.getLevel()-1);
        else
            builder.setHealth(gm.getLevel()-2);
        builder.setImage(new ImagePattern(new Image("/obsticale1.png")));
        builder.setHeight( 155 );
        builder.setWidth( 100 );
    }

    public void ConstructSecondLine(TheObBuilder builder){
        builder.reset();
        if (gm.getLevel()>=3)
            builder.setHealth(gm.getLevel()-1);
        else
            builder.setHealth(gm.getLevel());
        builder.setImage(new ImagePattern(new Image("/obsticale2.png")));
        builder.setHeight( 238 );
        builder.setWidth( 150 );
    }

    public void ConstructThirdLine(TheObBuilder builder){
        builder.reset();
        builder.setHealth(gm.getLevel());
        builder.setImage(new ImagePattern(new Image("/obsticale3.png")));
        builder.setHeight(320);
        builder.setWidth(200);
    }
}
