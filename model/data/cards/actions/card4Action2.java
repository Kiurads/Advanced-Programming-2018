package model.data.cards.actions;

import model.data.GameData;
import model.data.Rank;

public class card4Action2 implements cardAction {
    @Override
    public void executeAction(GameData data) {
        data.enemyUnitAdvance(Rank.LADDER);
        data.enemyUnitAdvance(Rank.BATTERING_RAM);

        data.modifyEnemyUnitDRM(Rank.BATTERING_RAM, 1);
    }
}
