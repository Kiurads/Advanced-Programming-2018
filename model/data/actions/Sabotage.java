package model.data.actions;

import model.data.GameData;
import model.data.Location;

public class Sabotage extends PlayerActions {
    @Override
    public void execute(GameData data) {
        if(data.getAvailableActions() == 0 || data.getSoldiersPosition() != Location.ENEMY_LINES) {
            warningNotPossible(data);
            return;
        }

        int diceTotal;

        data.rollDice();
        diceTotal = data.getDiceNumber();
        diceTotal += getDRM();
        data.addMsgLog("[ " + "You have a DRM of " + getDRM() + " which adds up to " + diceTotal + " ]");

        if(diceTotal > 4) {
            data.destroyTrebuchet();
        }
        else if(diceTotal == 1) {
            data.soldiersGetCaptured();
        }
        else {
            data.addMsgLog("[ " + "Nothing happened" + " ]");
        }

        data.decreaseAvailableActions();
    }

    @Override
    public boolean isPosible(GameData data) {
        return data.getAvailableActions() > 0 && data.getSoldiersPosition() == Location.ENEMY_LINES && isPosible();
    }
}
