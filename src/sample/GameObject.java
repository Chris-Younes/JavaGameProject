package sample;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class GameObject extends Rectangle {
    ImagePattern image;
    int health;
    Color color;

    boolean dead = false;

    public GameObject() {
        super(0,0);
    }

    public GameObject(int width, int height,ImagePattern image) {
        super(width,height);
        this.setFill(image);
        this.image = image;
    }

    public GameObject(int width, int height,Color color,int health) {
        super(width,height);
        this.color = color;
        this.setFill(color);
        this.setStroke(color);
        this.health = health;
    }

    public GameObject(int width, int height,Color color) {
        super(width,height);
        this.color = color;
        this.setFill(color);
        this.setStroke(color);
    }

    public GameObject(int width, int height,ImagePattern image, int health) {
        super(width,height);
        this.setFill(image);
        this.health = health;
        this.image = image;
    }

    public void setImage(ImagePattern image){
        this.setFill( image );
        this.image = image;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth(){
        return health;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public ImagePattern getImage() {
        return image;
    }
}
