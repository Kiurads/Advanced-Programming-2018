package model.states;

import model.data.Actions;
import model.data.GameData;

public class TunnelMovementAwaitSelection extends StateAdapter {
    public TunnelMovementAwaitSelection(GameData data) {
        super(data);
    }

    @Override
    public IStates execute(int action) {
        data.executeAction(Actions.TUNNEL_MOVEMENT, action);

        if(checkIfGameOver()) return new GameOver(data);
        else return new AwaitAction(data);
    }

    @Override
    public IStates cancel() {
        return new AwaitAction(data);
    }
}
