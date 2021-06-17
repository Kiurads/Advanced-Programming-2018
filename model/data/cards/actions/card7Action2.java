package model.data.cards.actions;

import model.data.GameData;
import model.data.Rank;

public class card7Action2 implements cardAction {
    @Override
    public void executeAction(GameData data) {
        data.enemyUnitAdvance(Rank.SIEGE_TOWER);

        data.modifyEnemyUnitDRM(Rank.SIEGE_TOWER, -1);
    }
}
