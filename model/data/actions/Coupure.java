package model.data.actions;

import model.data.GameData;
import model.data.Status;

public class Coupure extends PlayerActions {
    @Override
    public void execute(GameData data) {
        if(!isPosible(data)) {
            warningNotPossible(data);
            return;
        }

        int diceTotal;
        int DRM;

        data.rollDice();
        DRM = getDRM() + data.getStatusDRM(Status.WALL_STRENGTH);
        diceTotal = data.getDiceNumber() + DRM;
        data.addMsgLog("[ " + "You have a DRM of " + DRM + " which adds up to " + diceTotal + " ]");

        if(diceTotal > 4) {
            data.increaseStatus(Status.WALL_STRENGTH, 1);
        }

        data.decreaseAvailableActions();
    }

    @Override
    public boolean isPosible(GameData data) {
        //System.out.println(data.getAvailableActions() > 0 && data.getStatusStatus(Status.WALL_STRENGTH) < 4 && isPosible());
        return data.getAvailableActions() > 0 && data.getStatusStatus(Status.WALL_STRENGTH) < 4 && isPosible();
    }
}
