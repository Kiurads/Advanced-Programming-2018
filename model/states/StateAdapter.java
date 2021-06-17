package model.states;

import model.data.GameData;
import model.data.Rank;

public class StateAdapter implements IStates{
    GameData data;
    public StateAdapter(GameData data) {
        this.data = data;
    }

    @Override
    public IStates start() {
        return this;
    }

    @Override
    public IStates playCard() {
        return this;
    }

    @Override
    public IStates archersAttack() {
        return this;
    }

    @Override
    public IStates boilingWaterAttack() {
        return this;
    }

    @Override
    public IStates closeCombatAttack() {
        return this;
    }

    @Override
    public IStates coupure() {
        return this;
    }

    @Override
    public IStates rallyTroops() {
        return this;
    }

    @Override
    public IStates sabotage() {
        return this;
    }

    @Override
    public IStates supplyRaid() {
        return this;
    }

    @Override
    public IStates tunnelMovement() {
        return this;
    }

    @Override
    public IStates execute() {
        return this;
    }

    @Override
    public IStates execute(Rank lane) {
        return this;
    }

    @Override
    public IStates execute(int action) {
        return this;
    }

    @Override
    public IStates execute(boolean useSupply) {
        return this;
    }

    @Override
    public IStates cancel() {
        return this;
    }

    @Override
    public IStates endTurn() {
        return this;
    }

    @Override
    public IStates endDay() {
        return this;
    }

    @Override
    public IStates quit() {
        return this;
    }

    @Override
    public IStates gameOver() {
        return new GameOver(data);
    }

    @Override
    public boolean checkIfGameOver() {
        return data.checkForInstantGameOver();
    }
}