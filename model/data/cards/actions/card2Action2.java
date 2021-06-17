package model.data.cards.actions;

import model.data.GameData;

public class card2Action2 implements cardAction {
    @Override
    public void executeAction(GameData data) {
        data.moveFurthest();
    }
}
