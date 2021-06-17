package model.data.cards.actions;

import model.data.GameData;
import model.data.Rank;
import model.data.Status;
import model.data.cards.actions.cardAction;

public class card3Action1 implements cardAction {
    @Override
    public void executeAction(GameData data) {
        data.enemyUnitAdvance(Rank.LADDER);
        data.reduceStatus(Status.SUPPLIES, 1);
    }
}
