package model.states;

import model.data.Actions;
import model.data.GameData;
import model.data.Rank;

public class ArchersAttackAwaitLane extends StateAdapter {
    public ArchersAttackAwaitLane(GameData data) {
        super(data);
    }

    @Override
    public IStates execute(Rank lane) {
        data.executeAction(Actions.ARCHERS_ATTACK, lane);

        if(checkIfGameOver()) return new GameOver(data);
        return new AwaitAction(data);
    }
}
