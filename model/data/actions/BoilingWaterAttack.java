package model.data.actions;

import model.data.GameData;
import model.data.Rank;
import model.data.Status;

public class BoilingWaterAttack extends PlayerActions {
    private boolean hasAttacked;

    public BoilingWaterAttack() {
        hasAttacked = false;
    }

    @Override
    public void execute(GameData data, Rank lane) {
        if(!isPosible(data)) {
            warningNotPossible(data);
            return;
        }

        int diceTotal;
        int DRM;

        modifyDRM(1);
        DRM = getDRM() + data.getEnemyUnitDRM(lane) + data.getPositionDRM(1);
        data.rollDice();

        diceTotal = data.getDiceNumber() + DRM;
        data.addMsgLog("[ " + "You have a DRM of " + DRM + " which adds up to " + diceTotal + " ]");

        if(diceTotal > data.getEnemyUnitStrength(lane)) {
            data.enemyUnitRetreat(lane);
        }

        if(data.getDiceNumber() == 1) {
            data.reduceStatus(Status.MORALE, 1);
        }

        hasAttacked = true;

        data.decreaseAvailableActions();
    }

    @Override
    public boolean isPosible(GameData data) {
        return data.getAvailableActions() > 0 && data.anyEnemyOnPosition(1) && !hasAttacked && isPosible();
    }

    @Override
    public void resetDRM() {
        super.resetDRM();
        hasAttacked = false;
    }
}
