package model.data.cards.actions;

import model.data.GameData;
import model.data.Rank;

public class card5Action3 implements cardAction {
    @Override
    public void executeAction(GameData data) {
        data.enemyUnitAdvance(Rank.LADDER);

        data.addTrebuchet();
    }
}
