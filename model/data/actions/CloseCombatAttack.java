package model.data.actions;

import model.data.GameData;
import model.data.Rank;
import model.data.Status;

public class CloseCombatAttack extends PlayerActions {
    @Override
    public void execute(GameData data, Rank lane) {
        if(!isPosible(data)) {
            warningNotPossible(data);
            return;
        }

        int diceTotal;
        int DRM;

        data.rollDice();
        DRM = getDRM() + data.getPositionDRM(0);
        diceTotal = data.getDiceNumber() + DRM;
        data.addMsgLog("[ " + "You have a DRM of " + DRM + " which adds up to " + diceTotal + " ]");

        if(diceTotal > 4) {
            data.enemyUnitRetreat(lane);
        }

        if(data.getDiceNumber() == 1) {
            data.reduceStatus(Status.MORALE, 1);
        }

        data.decreaseAvailableActions();
    }

    @Override
    public boolean isPosible(GameData data) {
        return data.getAvailableActions() > 0 && data.anyEnemyOnPosition(0) && isPosible();
    }
}
