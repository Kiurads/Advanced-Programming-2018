package model.data.cards.actions;

import model.data.Actions;
import model.data.GameData;
import model.data.Rank;

public class card6Action2 implements cardAction {
    @Override
    public void executeAction(GameData data) {
        data.enemyUnitAdvance(Rank.LADDER);

        data.actionModifyDRM(Actions.COUPURE, 1);
        data.actionModifyDRM(Actions.SUPPLY_RAID, 1);
        data.actionModifyDRM(Actions.SABOTAGE, 1);
    }
}
