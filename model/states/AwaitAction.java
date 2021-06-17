package model.states;

import model.data.GameData;

public class AwaitAction extends StateAdapter {
    public AwaitAction(GameData data) {
        super(data);
    }

    @Override
    public IStates archersAttack() {
        return new ArchersAttackAwaitLane(data);
    }

    @Override
    public IStates boilingWaterAttack() {
        return new BoilingWaterAttackAwaitLane(data);
    }

    @Override
    public IStates closeCombatAttack() {
        return new CloseCombatAttackAwaitLane(data);
    }

    @Override
    public IStates coupure() {
        return new Coupure(data);
    }

    @Override
    public IStates rallyTroops() {
        return new RallyTroops(data);
    }

    @Override
    public IStates sabotage() {
        return new Sabotage(data);
    }

    @Override
    public IStates supplyRaid() {
        return new SupplyRaid(data);
    }

    @Override
    public IStates tunnelMovement() {
        return new TunnelMovementAwaitSelection(data);
    }

    @Override
    public IStates endTurn() {
        if(data.checkForInstantGameOver() || data.checkForGameOver()) return new GameOver(data);

        data.removeCard();

        if(data.noCardsLeft()) return new EndDay(data);
        else {
            data.endTurn();
            return new AwaitCard(data);
        }
    }

    @Override
    public IStates quit() {
        return new AwaitBeginning(data);
    }
}
