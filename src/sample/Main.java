package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{


        File file1 = new File("D:/TryandSolveThisLoopable.wav");
        Media sound1 = new Media(file1.toURI().toString());
        MediaPlayer Menuplayer = new MediaPlayer(sound1);
        Menuplayer.setAutoPlay(true);

        File file2 = new File("D:/TakeCoverLoopable.wav");
        Media sound2 = new Media(file2.toURI().toString());
        MediaPlayer gameplayer = new MediaPlayer(sound2);

        File file3 = new File("D:/LaserQuestLoopable.wav");
        Media sound3 = new Media(file3.toURI().toString());
        MediaPlayer bossaplayer = new MediaPlayer(sound3);

        //start menue
        Pane startmenue = new Pane();
        startmenue.setStyle("-fx-background-color: black;");
        ImagePattern ilogo = new ImagePattern(new Image("/logo.png"));
        GameObject logo = new GameObject(300,400,ilogo);
        ImagePattern iplay = new ImagePattern(new Image("/Play.png"));
        GameObject play = new GameObject(300,100,iplay);
        play.setX(1000-play.getWidth()/2);
        play.setY(500);
        ImagePattern iupgrade = new ImagePattern(new Image("/upgrade.png"));
        GameObject upgrade = new GameObject(300,100,iupgrade);
        upgrade.setX(1000-upgrade.getWidth()/2);
        upgrade.setY(play.getY()+play.getHeight()+50);
        logo.setX(1000-logo.getWidth()/2);
        logo.setY(play.getY()-50-logo.getHeight());
        startmenue.getChildren().addAll(logo,play,upgrade);
        Scene MainMenue = new Scene(startmenue,2000,1000);
        primaryStage.setScene(MainMenue);

        //upgrade menue
        Pane UpdradePane = new Pane();
        UpdradePane.setStyle("-fx-background-color: black;");
        ImagePattern ifire = new ImagePattern(new Image("/Firerate.png"));
        GameObject fire = new GameObject(300,100,ifire);
        ImagePattern ispeed = new ImagePattern(new Image("/SPeed.png"));
        GameObject speed = new GameObject(300,100,ispeed);
        fire.setX(1000-fire.getWidth()/2);
        fire.setY(300);
        ImagePattern iback = new ImagePattern(new Image("/back.png"));
        GameObject back = new GameObject(300,100,iback);
        back.setX(1000-back.getWidth()/2);
        back.setY(fire.getY()+500);
        speed.setX(1000-speed.getWidth()/2);
        speed.setY(fire.getY()+200);
        UpdradePane.getChildren().addAll(fire,speed,back);
        Scene UpgradeMenu = new Scene(UpdradePane,2000,1000);

        //trackers
        ArrayList<Bullet> BulletTracker = new ArrayList<Bullet>();
        ArrayList<GameObject> ObstacleTracker = new ArrayList<GameObject>();
        ArrayList<Player> EnemyTracker = new ArrayList<Player>();

        //game manager
        GameManager gm = GameManager.getInstane();
        gm.setLevel(2);
        gm.setBossTimerLeft(gm.getBossTimer());
        gm.setBossBattleStatus(false);

        //game obstacles
        BuilderFactoryAdaptor obstacleSpawner = new BuilderFactoryAdaptor();
        EnemyBuilderFactoryAdaptor enemySpawner = new EnemyBuilderFactoryAdaptor();

        //pane and scene
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: black;");
        Scene scene = new Scene(pane,2000,1000);

        //won sign
        ImagePattern iwon = new ImagePattern(new Image("/bassbattle.png"));
        GameObject won = new GameObject(500,150,iwon);
        won.setX((pane.getWidth()-won.getWidth())/2);
        won.setY((pane.getHeight()-won.getHeight())/2);

        //health
        Text health = new Text();

        //Player
        ImagePattern playerImage = new ImagePattern(new Image("/spaceship2.png"));
        Player player = new Player(200,200,playerImage,30,"player");
        pane.getChildren().add(player);
        player.setCoolDownTimeLeft(-1);
        player.setCoolDownTime(gm.getPfire());

        //test
        /*Rectangle rec = new Rectangle();
        rec.setX(100);
        rec.setY(100);
        rec.setHeight(100);
        rec.setWidth(100);
        rec.setFill(new ImagePattern(new Image("/obsticale1.png")));
        pane.getChildren().add(rec);*/

        //player input
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()){
                case UP:
                    if(player.getY()>0) {
                        player.setVelY(-gm.getPspeed());
                    }
                    break;
                case DOWN:
                    if(player.getY()<1000-player.getHeight())
                        player.setVelY(gm.getPspeed());
                    break;
                case LEFT:
                    if(player.getX()>0)
                        player.setVelX(-gm.getPspeed());
                    break;
                case RIGHT:
                    if(player.getX()<2000-player.getWidth())
                        player.setVelX(gm.getPspeed());
                    break;
                case E:
                    player.setShooting(true);
                    break;
            }
        });
        scene.setOnKeyReleased(e -> {
            switch (e.getCode()){
                case UP:
                    player.setVelY(0);
                    break;
                case DOWN:
                    player.setVelY(0);
                    break;
                case LEFT:
                    player.setVelX(0);
                    break;
                case RIGHT:
                    player.setVelX(0);
                    break;
                case E:
                    player.setShooting(false);
                    break;
            }
        });



        //game loop
        AnimationTimer gameloop = new AnimationTimer()
        {
            int wonduration = -1;
            int rshotimer = 200;
            int lshotimer = 200;
            int boosTimer = 10000;

            @Override
            public void handle(long l) {
                boosTimer--;
                if(boosTimer<0){
                    gm.setBossBattleStatus(true);
                }
                if(gm.getPlayerIsAlive() == true) {
                    if (player.getShooting()) {
                        player.setCoolDownTimeLeft(player.getCoolDownTimeLeft() -1 );
                        if (player.getCoolDownTimeLeft() < 0) {
                            player.shoot(pane, BulletTracker);
                            player.setCoolDownTimeLeft(player.getCoolDownTime());
                        }
                    }

                    //player movement
                    player.setX(player.getX() + player.getVelX());
                    player.setY(player.getY() + player.getVelY());

                    //bullet manager
                    for (Bullet b : BulletTracker) {
                        if (b.getType() == "player")
                            b.setX(b.getX() + 10);
                        else
                            b.setX(b.getX() - 10);

                        if (b.getX() - player.getX() > 2000)
                            pane.getChildren().remove(b);
                    }

                    if(gm.getBossBattleStatus() == false) {
                        //obstacle creator
                        gm.setObstacleCoolDown(gm.getObstacleCoolDown() - 1);
                        if (gm.getObstacleCoolDown() < 0) {
                            if (Math.random() < 0.8)
                                obstacleSpawner.getObstacle(pane, ObstacleTracker);
                            gm.setObstacleCoolDown(1500);
                        }

                        //enemy creator
                        gm.setEnemyCoolDown(gm.getEnemyCoolDown() - 1);
                        if (gm.getEnemyCoolDown() < 0) {
                            if (Math.random() < 0.7)
                                enemySpawner.getEnemy(pane, EnemyTracker);
                            gm.setEnemyCoolDown(1000);
                        }
                    }else{
                        //boss code
                        gm.setBossTimerLeft(gm.getBossTimerLeft()-1);
                        System.out.println(gm.getBossTimerLeft());
                        if(gm.getBossTimerLeft()==0)
                            gm.setBossIsAlive(true);

                        if(gm.getBossIsAlive()) {
                            enemySpawner.getEnemy(pane, EnemyTracker);
                            for (Player boss: EnemyTracker) {
                                boss.setY((pane.getHeight()-boss.getHeight())/2);
                            }
                            gm.setBossIsAlive(false);
                        }

                        for (Player boss:EnemyTracker) {
                            if(boss.getPlayerType() == "boss"){
                                if(boss.getX()>pane.getWidth()-boss.getWidth()-70)
                                    boss.setX(boss.getX()-0.75);
                                else {
                                    lshotimer--;
                                    rshotimer--;
                                    boss.setY(boss.getY()+boss.getVelY());
                                    System.out.println(boss.getVelY());
                                    if(boss.getY()<boss.getTopY() || boss.getY()>boss.getButtomY())
                                        boss.setVelY(-boss.getVelY());
                                    else {
                                        if (Math.random() < 0.5 && lshotimer<0) {
                                            boss.shootleft(pane, BulletTracker);
                                            lshotimer = 200;
                                        }
                                        if (Math.random() < 0.5 && rshotimer<0) {
                                            boss.shootright(pane, BulletTracker);
                                            rshotimer = 200;
                                        }
                                    }
                                }
                            }
                        }

                    }

                    //obstacle manager
                    for (GameObject ob : ObstacleTracker) {
                        ob.setX(ob.getX() - 0.75);
                        if (ob.getX() < 0 - ob.getWidth())
                            pane.getChildren().remove(ob);
                        if (ob.getBoundsInParent().intersects(player.getBoundsInParent())) {
                            gm.setPlayerIsAlive(false);
                        }
                    }

                    //enemy manager
                    for (Player en : EnemyTracker) {
                        if(en.getPlayerType() == "enemy") {
                            en.setX(en.getX() - 0.75);

                            en.setY(en.getY() + en.getVelY());

                            if (en.getX() < 0 - en.getWidth())
                                pane.getChildren().remove(en);

                            if (en.getY() > en.getButtomY() || en.getY() < en.getTopY())
                                en.setVelY(-en.getVelY());

                            en.setCoolDownTimeLeft(en.getCoolDownTimeLeft() - 1);
                            if (en.getCoolDownTimeLeft() <= 0) {
                                en.shoot(pane, BulletTracker);
                                en.setCoolDownTimeLeft(en.getCoolDownTime());
                            }
                        }
                    }

                    //check bullet intersection
                    for (Bullet b : BulletTracker) {
                        if(b.getX()>pane.getWidth() && b.getX()<0-b.getWidth())
                            pane.getChildren().remove(b);
                        for (GameObject ob : ObstacleTracker) {
                            if (b.getBoundsInParent().intersects(ob.getBoundsInParent()) && b.getType() == "player") {
                                pane.getChildren().remove(b);
                                BulletTracker.remove(b);
                                if (b.getHit() == false) {
                                    ob.setHealth(ob.getHealth() - 1);
                                    b.setHit(true);
                                }
                            }
                            if (ob.getHealth() <= 0) {
                                pane.getChildren().remove(ob);
                                ObstacleTracker.remove(ob);
                            }
                        }

                        for (Player en : EnemyTracker) {
                            if (b.getBoundsInParent().intersects(en.getBoundsInParent()) && b.getType() == "player") {
                                pane.getChildren().remove(b);
                                BulletTracker.remove(b);
                                if (b.getHit() == false) {
                                    en.setHealth(en.getHealth() - 1);
                                    b.setHit(true);
                                }
                                if (en.getHealth() <= 0) {
                                    if(en.getPlayerType() == "boss") {
                                        gm.setBossBattleStatus(false);
                                        gm.setBossIsAlive(false);
                                        gm.setObstacleCoolDown(gm.getAfterBossTimer());
                                        gm.setEnemyCoolDown(gm.getAfterBossTimer());
                                        pane.getChildren().add(won);
                                        wonduration = gm.getWonDuration();
                                        gm.setLevel(gm.getLevel()+1);
                                        bossaplayer.pause();
                                        gameplayer.setAutoPlay(true);
                                        Menuplayer.pause();
                                    }
                                    pane.getChildren().remove(en);
                                    EnemyTracker.remove(en);
                                }
                            }
                        }

                        if (b.getBoundsInParent().intersects(player.getBoundsInParent()) && (b.getType() == "enemy" || b.getType() == "boss")) {
                            primaryStage.setScene(MainMenue);
                            player.setHealth(player.getHealth() - 1);
                            if (player.getHealth() <= 0) {
                                gm.setPlayerIsAlive(false);
                            }
                        }
                    }
                }
                    if(wonduration > 0 ) {
                        wonduration--;
                        if(wonduration<=0)
                            pane.getChildren().remove(won);
                    }

                    //if player dies
                    if(gm.getPlayerIsAlive() == false){
                        System.out.println("I'm here in the dead");
                        for (Bullet b:BulletTracker) {
                            pane.getChildren().remove(b);
                        }
                        for (Player en:EnemyTracker) {
                            pane.getChildren().remove(en);
                        }
                        for (GameObject ob:ObstacleTracker) {
                            pane.getChildren().remove(ob);
                        }
                        BulletTracker.clear();
                        ObstacleTracker.clear();
                        EnemyTracker.clear();
                    }
                }
            };

        //Scene managment
        play.setOnMouseClicked(e->{
            gameplayer.setAutoPlay(true);
            Menuplayer.pause();
            bossaplayer.pause();
            primaryStage.setScene(scene);
            player.setX(200);
            player.setY((int) ( pane.getHeight()/2 - player.getHeight()/2 ));
            gameloop.start();
            gm.setPlayerIsAlive(true);
        });
        upgrade.setOnMouseClicked(e->{
            primaryStage.setScene(UpgradeMenu);
        });
        back.setOnMouseClicked(e->{
            primaryStage.setScene(MainMenue);
        });
        fire.setOnMouseClicked(e->{
            gm.setPfire(gm.getPfire()-1);
        });
        speed.setOnMouseClicked(e->{
            gm.setPspeed(gm.getPspeed()+0.2);
        });

        if(gm.getPlayerIsAlive() == false){
            primaryStage.setScene(MainMenue);
            gameloop.stop();
            gm.setPlayerIsAlive(true);
        }

        if(gm.getBossBattleStatus() == true){
            bossaplayer.setAutoPlay(true);
            gameplayer.pause();
            Menuplayer.pause();
        }
        primaryStage.show();
        }

    public static void main(String[] args) {
        launch(args);
    }
}
