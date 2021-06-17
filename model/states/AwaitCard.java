package model.states;

import model.data.GameData;

public class AwaitCard extends StateAdapter{
    public AwaitCard(GameData data) {
        super(data);
    }

    @Override
    public IStates playCard() {
        data.playCard();
        return new AwaitAction(data);
    }
}
