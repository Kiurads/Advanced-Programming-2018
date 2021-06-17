package model.states;

import model.data.GameData;

public class WinState extends StateAdapter {
    public WinState(GameData data) {
        super(data);
    }

    @Override
    public IStates quit() {
        return new AwaitBeginning(data);
    }
}
