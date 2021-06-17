package model;

import model.data.*;
import model.data.cards.Card;
import model.states.AwaitBeginning;
import model.states.IStates;
import java.util.List;

public class Game {
    private GameData data;
    private IStates state;

    public Game() {
        data = new GameData();
        state = new AwaitBeginning(data);
    }

    public void setData(GameData data) {
        this.data = data;
    }

    public IStates getState() {
        return state;
    }

    public void setState(IStates state) {
        this.state = state;

        System.out.println(state);
    }

    public int getEnemyUnitPosition(Rank rank) {
        return data.getEnemyUnitPosition(rank);
    }

    public int getTrebuchetStrength() {
        return data.getTrebuchetsStrength();
    }

    public int getStatusStatus(Status status) {
        return data.getStatusStatus(status);
    }

    public int getDiceNumber() {
        return data.getDiceNumber();
    }

    public void rollDice() {
        data.rollDice();
    }

    public String getCard() {
        return data.getCurrentCard();
    }

    public boolean isActionPosible(Actions action) {
        return data.isActionPosible(action);
    }

    public Location getSoldiersPosition() {
        return data.getSoldiersPosition();
    }

    public boolean soldiersInPosition(Location location) {
        return data.soldiersInPosition(location);
    }

    public boolean tunnelActionIsPosible(int action) {
        return data.tunnelActionIsPosible(action);
    }

    public void start() {
        setState(getState().start());
    }

    public void playCard() {
        setState(getState().playCard());
    }

    public void archersAttack() {
        setState(getState().archersAttack());
    }

    public void boilingWaterAttack() {
        setState(getState().boilingWaterAttack());
    }

    public void closeCombatAttack() {
        setState(getState().closeCombatAttack());
    }

    public void coupure() {
        setState(getState().coupure());
    }

    public void rallyTroops() {
        setState(getState().rallyTroops());
    }

    public void sabotage() {
        setState(getState().sabotage());
    }

    public void supplyRaid() {
        setState(getState().supplyRaid());
    }

    public void tunnelMovement() {
        setState(getState().tunnelMovement());
    }

    public void executeAction(Rank lane) {
        setState(getState().execute(lane));
    }

    public void executeAction(boolean useSupplies) {
        setState(getState().execute(useSupplies));
    }

    public void executeAction(int action) {
        setState(getState().execute(action));
    }

    public void executeAction() {
        setState(getState().execute());
    }

    public void cancel() {
        setState(getState().cancel());
    }

    public void endTurn() {
        setState(getState().endTurn());
    }

    public void quit() {
        setState(getState().quit());
    }

    @Override
    public String toString() { return data.toString(); }

    public int getAvailableActions() {
        return data.getAvailableActions();
    }

    public int getCurrentDay() {
        return data.getCurrentDay();
    }

    public void moraleForAP() {
        data.moraleForAP();
    }

    public void supplyForAP() {
        data.supplyForAP();
    }

    public int enemiesOnCloseCombat() {
        return data.enemiesOnCloseCombat();
    }

    public void checkIfGameOver() {
        if(getState().checkIfGameOver()) setState(getState().gameOver());
    }

    public void endDay() {
        setState(getState().endDay());
    }

    public boolean siegeTowerOut() {
        return data.siegeTowerOut();
    }

    public int getRaidedSupplies() {
        return data.getSoldiersSupplies();
    }
}
