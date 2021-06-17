package model.states;

import model.data.Rank;

public interface IStates {
    IStates start();
    IStates playCard();
    IStates archersAttack();
    IStates boilingWaterAttack();
    IStates closeCombatAttack();
    IStates coupure();
    IStates rallyTroops();
    IStates sabotage();
    IStates supplyRaid();
    IStates tunnelMovement();
    IStates execute();
    IStates execute(Rank lane);
    IStates execute(int action);
    IStates execute(boolean useSupply);
    IStates cancel();
    IStates endTurn();
    IStates endDay();
    IStates quit();
    IStates gameOver();
    boolean checkIfGameOver();
}