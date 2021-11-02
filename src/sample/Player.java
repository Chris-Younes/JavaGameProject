package sample;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.layout.Pane;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Player extends GameObject {
    private double velX = 0;
    private double velY = 0;
    private boolean shooting = false;
    private String PlayerType;
    private int CoolDownTime = 0;
    private int TopY;
    private int BottomY;
    private int CoolDownTimeLeft = 0;

    File file = new File("D:/laser1.wav");
    Media sound = new Media(file.toURI().toString());
    MediaPlayer mediaplayer = new MediaPlayer(sound);


    public Player(){super();}

    public Player(int width, int height, ImagePattern image, int health,String type) {
        super(width, height, image, health);
        this.PlayerType = type;
    }

    void setShooting(boolean s) {
        shooting = s;
    }

    boolean getShooting() {
        return shooting;
    }

    public double getVelX() {
        return velX;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public double getVelY() {
        return this.velY;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public void setPlayerType(String type){
        this.PlayerType = type;
    }

    public String getPlayerType(){
        return PlayerType;
    }

    public int getCoolDownTime(){
        return this.CoolDownTime;
    }

    public void setCoolDownTime(int t){
        this.CoolDownTime = t;
    }

    public void setCoolDownTimeLeft(int t){
        this.CoolDownTimeLeft = t;
    }

    public int getCoolDownTimeLeft(){
        return this.CoolDownTimeLeft;
    }

    public void setTopY(int y){
        this.TopY = y;
    }

    public void setBottomY(int y){
        this.BottomY = y;
    }

    public int getTopY(){
        return this.TopY;
    }

    public int getButtomY(){
        return this.BottomY;
    }

    public void shootleft(Pane pane, ArrayList<Bullet> bullets){
        File file = new File("D:/laser1.wav");
        Media sound = new Media(file.toURI().toString());
        MediaPlayer mediaplayer = new MediaPlayer(sound);
        mediaplayer.play();
        Bullet bullet = new Bullet(50, 10, Color.BLUE, this.getPlayerType());
        bullet.setX(this.getX() - 5);
        bullet.setY(this.getY() + this.getHeight() - 20);
        bullets.add(bullet);
        pane.getChildren().add(bullet);
    }
    public void shootright(Pane pane, ArrayList<Bullet> bullets){
        File file = new File("D:/laser1.wav");
        Media sound = new Media(file.toURI().toString());
        MediaPlayer mediaplayer = new MediaPlayer(sound);
        mediaplayer.play();
        Bullet bullet = new Bullet(50, 10, Color.BLUE, this.getPlayerType());
        bullet.setX(this.getX() - 5);
        bullet.setY(this.getY() + 20);
        bullets.add(bullet);
        pane.getChildren().add(bullet);
    }

    public void shoot(Pane pane, ArrayList<Bullet> bullets){
            File file = new File("D:/laser1.wav");
            Media sound = new Media(file.toURI().toString());
            MediaPlayer mediaplayer = new MediaPlayer(sound);
            mediaplayer.play();
            Bullet bullet = new Bullet(50, 10, Color.BLUE, this.getPlayerType());
            if (this.getPlayerType() == "player")
                bullet.setX(this.getX() + this.getWidth() - 20);
            else
                bullet.setX(this.getX() - 5);
            bullet.setY(this.getY() + this.getHeight() / 2-5);
            bullets.add(bullet);
            pane.getChildren().add(bullet);
    }

}
