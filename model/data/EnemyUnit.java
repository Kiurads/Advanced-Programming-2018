package model.data;

public class EnemyUnit {
    private int position;
    private int strength;
    private int DRM;

    public void modifyDRM(int add) {
        DRM += add;
    }

    public void resetDRM() {
        DRM = 0;
    }

    public int getDRM() {
        return DRM;
    }

    public EnemyUnit() {
        position = 4;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public boolean advance(GameData data) {
        if(position > 0) {
            position--;
            if(position == 0)
                data.reduceStatus(Status.MORALE, 1);
            return true;
        }
        return false;
    }

    public void retreat() {
        if(position < 4) {
            position++;
            //getData().addMsgLog("[ " + "Enemy unit has retreated" + " ]");
            //return;
        }
        //getData().addMsgLog("[ " + "Enemy unit has not retreated" + " ]");
    }
}
