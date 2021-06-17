package model.states;

import model.data.Actions;
import model.data.GameData;
import model.data.Rank;

public class BoilingWaterAttackAwaitLane extends StateAdapter {
    public BoilingWaterAttackAwaitLane(GameData data) {
        super(data);
    }

    @Override
    public IStates execute(Rank lane) {
        data.executeAction(Actions.BOILING_WATER_ATTACK, lane);

        if(checkIfGameOver()) return new GameOver(data);
        return new AwaitAction(data);
    }
}
