package model.states;

import model.data.Actions;
import model.data.GameData;

public class Sabotage extends StateAdapter {
    public Sabotage(GameData data) {
        super(data);
    }

    @Override
    public IStates execute() {
        data.executeAction(Actions.SABOTAGE);

        if(checkIfGameOver()) return new GameOver(data);
        else return new AwaitAction(data);
    }

    @Override
    public IStates cancel() {
        return new AwaitAction(data);
    }
}
