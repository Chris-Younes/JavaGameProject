package sample;

import javafx.scene.paint.Color;

public class Bullet extends GameObject {
    private int velX;
    private int velY;
    private String type;
    private boolean hit = false;


    public Bullet(int width, int height, Color color,String type) {
        super(width, height, color);
        this.type = type;
    }

    /*public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }*/

    public boolean getHit(){
        return hit;
    }

    public void setHit(boolean h){
        this.hit = h;
    }

    public void setType(String t){
        this.type = t;
    }

    public String getType(){
        return this.type;
    }

}
