package model.data.cards.actions;

import model.data.GameData;

public class card1Action3 implements cardAction {
    @Override
    public void executeAction(GameData data) {
        data.trebuchetsAttack();
    }
}
