package model.data.cards.actions;

import model.data.GameData;

public class card6Action1 implements cardAction {
    @Override
    public void executeAction(GameData data) {
        data.moveFurthest();
    }
}
