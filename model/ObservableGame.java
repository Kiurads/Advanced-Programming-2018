package model;

import model.data.Actions;
import model.data.Location;
import model.data.Rank;
import model.data.Status;
import model.states.IStates;
import java.util.Observable;

public class ObservableGame extends Observable {
    private Game game;

    public ObservableGame() {
        game = new Game();
    }

    public IStates getState() {
        return game.getState();
    }

    public int getEnemyUnitPosition(Rank rank) {
        return game.getEnemyUnitPosition(rank);
    }

    public int getTrebuchetStrength() {
        return game.getTrebuchetStrength();
    }

    public int getStatusStatus(Status status) {
        return game.getStatusStatus(status);
    }

    public int getDiceNumber() {
        return game.getDiceNumber();
    }

    public Game getGameModel() {
        return game;
    }

//    public void rollDice() {
//        game.rollDice();
//
//        setChanged();
//        notifyObservers();
//    }

    public String getCard() {
        return game.getCard();
    }

    public boolean isActionPosible(Actions action) {
        return game.isActionPosible(action);
    }

    public Location getSoldiersPosition() {
        return game.getSoldiersPosition();
    }

    public void startGame() {
        game.start();

        setChanged();
        notifyObservers();
    }

    public void playCard() {
        game.playCard();

        setChanged();
        notifyObservers();
    }

    public void archersAttack() {
        game.archersAttack();

        setChanged();
        notifyObservers();
    }

    public void boilingWaterAttack() {
        game.boilingWaterAttack();

        setChanged();
        notifyObservers();
    }

    public void closeCombatAttack() {
        game.closeCombatAttack();

        setChanged();
        notifyObservers();
    }

    public void coupure() {
        game.coupure();

        setChanged();
        notifyObservers();
    }

    public void rallyTroops() {
        game.rallyTroops();

        setChanged();
        notifyObservers();
    }

    public void sabotage() {
        game.sabotage();

        setChanged();
        notifyObservers();
    }

    public void supplyRaid() {
        game.supplyRaid();

        setChanged();
        notifyObservers();
    }

    public void tunnelMovement() {
        game.tunnelMovement();

        setChanged();
        notifyObservers();
    }

    public void executeAction() {
        game.executeAction();

        setChanged();
        notifyObservers();
    }

    public void executeAction(Rank lane) {
        game.executeAction(lane);

        setChanged();
        notifyObservers();
    }

    public void executeAction(boolean useSupplies) {
        game.executeAction(useSupplies);

        setChanged();
        notifyObservers();
    }

    public void cancel() {
        game.cancel();

        setChanged();
        notifyObservers();
    }

    public void moraleForAP() {
        game.moraleForAP();
        game.checkIfGameOver();

        setChanged();
        notifyObservers();
    }

    public void supplyForAP() {
        game.supplyForAP();
        game.checkIfGameOver();

        setChanged();
        notifyObservers();
    }

    public void endTurn() {
        game.endTurn();

        setChanged();
        notifyObservers();
    }

    public void quit() {
        game.quit();
        game = new Game();

        setChanged();
        notifyObservers();
    }

    public boolean soldiersInPosition(Location location) {
        return game.soldiersInPosition(location);
    }

    public boolean tunnelActionIsPosible(int action) {
        return game.tunnelActionIsPosible(action);
    }

    public void executeAction(int action) {
        game.executeAction(action);

        setChanged();
        notifyObservers();
    }

    public int getAvailableActions() {
        return game.getAvailableActions();
    }

    public int getCurrentDay() {
        return game.getCurrentDay();
    }

    public int enemiesOnCloseCombat() {
        return game.enemiesOnCloseCombat();
    }

    public void endDay() {
        game.endDay();

        setChanged();
        notifyObservers();
    }

    public boolean sigeTowerOut() {
        return game.siegeTowerOut();
    }

    public void setGameModel(Game gameModel) {
        this.game = gameModel;
    }

    public int getRaidedSupplies() {
        return game.getRaidedSupplies();
    }
}
