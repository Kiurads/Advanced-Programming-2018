package model.data.cards.actions;

import model.data.GameData;

public class card1Action1 implements cardAction {
    @Override
    public void executeAction(GameData data) {
        data.trebuchetsAttack();
    }
}
