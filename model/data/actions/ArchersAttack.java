package model.data.actions;

import model.data.GameData;
import model.data.Rank;

public class ArchersAttack extends PlayerActions {

    @Override
    public void execute(GameData data, Rank lane) {
        if((!isPosible(data))) {
            warningNotPossible(data);
            return;
        }

        int diceTotal;
        int DRM;

        data.rollDice();
        DRM = getDRM() + data.getEnemyUnitDRM(lane) + data.getPositionDRM(data.getEnemyUnitPosition(lane));
        diceTotal = data.getDiceNumber() + DRM;

        data.addMsgLog("[ " + "You have a DRM of " + DRM + " which adds up to " + diceTotal + " ]");

        if(diceTotal > data.getEnemyUnitStrength(lane)) { data.getEnemyUnit(lane).retreat(); }
        else data.addMsgLog("[ " + "Enemy unit has not retreated" + " ]");

        data.decreaseAvailableActions();
    }

    @Override
    public boolean isPosible(GameData data) {
        return data.getAvailableActions() > 0 && data.anyEnemyOut() && isPosible();
    }
}
