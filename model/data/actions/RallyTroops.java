package model.data.actions;

import model.data.GameData;
import model.data.Status;

public class RallyTroops extends PlayerActions {
    @Override
    public void execute(GameData data, boolean useSupply) {
        if(!isPosible(data)) {
            warningNotPossible(data);
            return;
        }
        int diceTotal;
        int DRM;

        data.rollDice();
        DRM = getDRM() + data.getStatusDRM(Status.MORALE);
        if (useSupply) {
            data.reduceStatus(Status.SUPPLIES, 1);
            DRM += 1;
        }

        diceTotal = data.getDice().getNumber() + DRM;
        data.addMsgLog("[ " + "You have a DRM of " + DRM + " which adds up to " + diceTotal + " ]");

        if (diceTotal > 4) data.increaseStatus(Status.MORALE, 1);

        data.decreaseAvailableActions();
    }

    @Override
    public boolean isPosible(GameData data) {
        //System.out.println(data.getAvailableActions() > 0 && data.getStatusStatus(Status.MORALE) < 4 && isPosible());
        return data.getAvailableActions() > 0 && data.getStatusStatus(Status.MORALE) < 4 && isPosible();
    }
}
