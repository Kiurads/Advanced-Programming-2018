package model.data.cards.actions;

import model.data.GameData;
import model.data.Rank;

public class card7Action1 implements cardAction {
    @Override
    public void executeAction(GameData data) {
        data.enemyUnitAdvance(Rank.BATTERING_RAM);

        data.modifyEnemyUnitDRM(Rank.BATTERING_RAM, -1);
    }
}
