package sample;

public class GameManager {
    public static GameManager instane = null;
    private int level;

    private boolean PlayerIsAlive = true;
    private boolean BossBattleStatus = false;
    private int BossTimer = 1000;
    private int AfterBossTimer = 700;
    private int BossTimerLeft;
    private boolean BossIsAlive = false;

    private int ObstacleCoolDown = 300;
    private int EnemyCoolDown = 2000;

    //player
    private double pspeed = 4;
    private int pfire = 50;

    private int wonduration = 500;

    private GameManager() {}

    public static GameManager getInstane(){
        if(instane == null) {
            instane = new GameManager();
            instane.setLevel(1);
        }
        return instane;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel(){
        return level;
    }

    public void setObstacleCoolDown(int v){
        this.ObstacleCoolDown = v;
    }

    public int getObstacleCoolDown(){
        return this.ObstacleCoolDown;
    }

    public void setEnemyCoolDown(int v){
        this.EnemyCoolDown = v;
    }

    public int getEnemyCoolDown(){
        return this.EnemyCoolDown;
    }

    public void setPlayerIsAlive(boolean playerIsAlive) {
        this.PlayerIsAlive = playerIsAlive;
    }

    public boolean getPlayerIsAlive(){
        return this.PlayerIsAlive;
    }

    public void setBossBattleStatus(boolean s){
        this.BossBattleStatus = s;
    }

    public boolean getBossBattleStatus(){
        return this.BossBattleStatus;
    }

    public int getBossTimer() {
        return BossTimer;
    }

    public int getAfterBossTimer() {
        return AfterBossTimer;
    }

    public void setBossTimerLeft(int t){
        this.BossTimerLeft= t;
    }

    public int getBossTimerLeft(){
        return this.BossTimerLeft;
    }

    public boolean getBossIsAlive(){
        return BossIsAlive;
    }

    public void setBossIsAlive(boolean b){
        this.BossIsAlive = b;
    }

    public int getWonDuration(){
        return wonduration;
    }

    public void setPspeed(double s){
        this.pspeed = s;
    }

    public double getPspeed(){
        return this.pspeed;
    }

    public void setPfire(int f){
        this.pfire = f;
    }

    public int getPfire(){
        return this.pfire;
    }

}
