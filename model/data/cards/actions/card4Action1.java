package model.data.cards.actions;

import model.data.GameData;
import model.data.Rank;
import model.data.Status;

public class card4Action1 implements cardAction {
    @Override
    public void executeAction(GameData data) {
        data.enemyUnitAdvance(Rank.LADDER);
        data.enemyUnitAdvance(Rank.SIEGE_TOWER);

        data.reduceStatus(Status.MORALE, 1);
    }
}
