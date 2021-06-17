package model.data.cards.actions;

import model.data.GameData;
import model.data.Rank;

public class card6Action3 implements cardAction {
    @Override
    public void executeAction(GameData data) {
        data.enemyUnitAdvance(Rank.LADDER);
        data.enemyUnitAdvance(Rank.SIEGE_TOWER);

        data.increaseSpacesDRM(0, 1);
        data.increaseSpacesDRM(1, 1);
    }
}
