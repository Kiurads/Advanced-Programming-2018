package model.data.actions;

import model.Game;
import model.data.GameData;
import model.data.Rank;

public abstract class PlayerActions {
    private boolean posible;
    private int DRM;

    public PlayerActions() {
        DRM = 0;
        posible = true;
    }

    public boolean isPosible(GameData data) {
        return posible;
    }

    public void setPosible(boolean posible) {
        this.posible = posible;
    }

    public int getDRM() {
        return DRM;
    }

    public void modifyDRM(int add) {
        DRM += add;
    }

    public void warningNotPossible(GameData data) {
        data.addMsgLog("[ " + "Action is not allowed" + " ]");
    }

    public void execute(GameData data) {}

    public void execute(GameData data, int action) {}

    public void execute(GameData data, Rank lane) {}

    public void execute(GameData data, boolean useSupply) {}

    public boolean isPosible() {
        return posible;
    }

    public void resetDRM() {
        DRM = 0;
        setPosible(true);
    }

    public boolean actionIsPosible(GameData data, int action) { return true; }
}
