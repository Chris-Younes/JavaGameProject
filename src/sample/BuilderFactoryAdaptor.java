package sample;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class BuilderFactoryAdaptor implements ObstacleFactory {
    TheObBuilder builder = new TheObBuilder();
    ObstacleDirector director = new ObstacleDirector();
    GameManager gm = GameManager.getInstane();

    @Override
    public void getObstacle(Pane pane, ArrayList<GameObject> obstacles) {
        ArrayList<GameObject> objects = this.CreateObstacle();
        int x = (int) pane.getWidth();
        for(GameObject ob : objects) {
            int numberOfObstacles = (int) Math.floor(pane.getHeight() / ob.getHeight());
            int y = 10;
            for (int i = 0; i < numberOfObstacles; i++) {
                GameObject cur = new GameObject((int) ob.getWidth(),(int) ob.getHeight(),ob.getImage(),ob.getHealth());
                cur.setY(y);
                cur.setX(x);
                obstacles.add(cur);
                pane.getChildren().add(cur);
                y += cur.getHeight()+10;
            }
            x += ob.getWidth() + 100;
        }
    }

    @Override
    public ArrayList<GameObject> CreateObstacle() {
        ArrayList<GameObject> go = new ArrayList<GameObject>();

        if(gm.getLevel() >= 1){
            director.ConstructFirstLine(builder);
            GameObject ob = builder.getObstacle();;
            go.add(ob);
        }

        if(gm.getLevel() >= 2){
            director.ConstructSecondLine(builder);
            go.add(builder.getObstacle());
        }

        if(gm.getLevel() >= 3){
            director.ConstructThirdLine(builder);
            go.add(builder.getObstacle());
        }

        return go;
    }

}
