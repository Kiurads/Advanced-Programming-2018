package model.states;

import model.data.GameData;

public class GameOver extends StateAdapter {

    public GameOver(GameData data) {
        super(data);
    }

    @Override
    public IStates quit() {
        return new AwaitBeginning(data);
    }
}
