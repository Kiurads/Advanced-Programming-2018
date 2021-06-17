package model.data;

public class Trebuchets {
    private int strength;

    public Trebuchets() {
        strength = 3;
    }

    public void attackWall(GameData data) {
        data.addMsgLog("[ " + "Trebuchet attack incoming!" + " ]");
        switch (strength) {
            case 3:
                data.damageWall(2);
                break;
            case 2:
                data.damageWall(1);
                break;
            case 1:
                data.addMsgLog("[ " + "Trebuchet has 1 strength!" + " ]");
                data.getDice().roll();
                if(data.getDice().getNumber() >= 4) data.damageWall(1);
                else data.addMsgLog("[ " + "Wall hasn't been damaged! Nicely done!" + " ]");
                break;
        }
    }

    public void increaseStrength(GameData data) {
        if(strength < 3) {
            strength++;
            data.addMsgLog("[ " + "Trebuchet strength has been increased to " + strength + " ]");
        }
    }

    public void decreaseStrength(GameData data) {
        if(strength > 1) {
            strength--;
            data.addMsgLog("[ " + "Trebuchet strength has been decreased to " + strength + " ]");
        }
    }

    public int getStrength() {
        return strength;
    }
}
