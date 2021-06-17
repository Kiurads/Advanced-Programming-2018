package model.data.cards.actions;

import model.data.GameData;
import model.data.Rank;

public class card5Action1 implements cardAction {
    @Override
    public void executeAction(GameData data) {
        data.enemyUnitAdvance(Rank.BATTERING_RAM);

        data.modifyEnemyUnitDRM(Rank.LADDER, 1);
        data.modifyEnemyUnitDRM(Rank.BATTERING_RAM, 1);
        data.modifyEnemyUnitDRM(Rank.SIEGE_TOWER, 1);
    }
}
