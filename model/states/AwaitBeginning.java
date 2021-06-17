package model.states;

import model.data.GameData;

public class AwaitBeginning extends StateAdapter {

    public AwaitBeginning(GameData data) {
        super(data);
    }

    @Override
    public IStates start() {
        if(checkIfGameOver()) { return new GameOver(this.data); }
        else { return new AwaitCard(this.data); }
    }
}
