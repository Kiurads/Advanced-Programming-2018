package model.data.cards.actions;

import model.data.GameData;

public class card3Action2 implements cardAction {
    @Override
    public void executeAction(GameData data) {
        data.badWeather();
    }
}
