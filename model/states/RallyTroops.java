package model.states;

import model.data.Actions;
import model.data.GameData;

public class RallyTroops extends StateAdapter {
    public RallyTroops(GameData data) {
        super(data);
    }

    @Override
    public IStates execute(boolean useSupplies) {
        data.executeAction(Actions.RALLY_TROOPS, useSupplies);

        if(checkIfGameOver()) return new GameOver(data);
        else return new AwaitAction(data);
    }

    @Override
    public IStates cancel() {
        return new AwaitAction(data);
    }
}
