package model.data.cards.actions;

import model.data.GameData;
import model.data.Rank;
import model.data.Status;

public class card7Action3 implements cardAction {
    @Override
    public void executeAction(GameData data) {
        data.enemyUnitAdvance(Rank.LADDER);
        data.enemyUnitAdvance(Rank.BATTERING_RAM);
        data.enemyUnitAdvance(Rank.SIEGE_TOWER);

        data.modifyEnemyUnitDRM(Rank.BATTERING_RAM, 1);
        data.modifyEnemyUnitDRM(Rank.LADDER, 1);
        data.modifyStatusDRM(Status.MORALE, 1);
    }
}
