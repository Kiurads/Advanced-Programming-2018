package model.data.cards;

import model.data.GameData;
import model.data.cards.actions.cardAction;
import ui.gui.ConstantesGUI;

public abstract class Card implements ConstantesGUI {
    private int[] playerActions;
    private cardAction[] cardActions;

    public Card(int[] playerActions, cardAction[] cardActions) {
        this.playerActions = playerActions;
        this.cardActions = cardActions;
    }

    public void executeAction(GameData data) {
        cardActions[data.getCurrentDay() - 1].executeAction(data);
    }

    public int getPlayerActions(int day) {
        return playerActions[day - 1];
    }
}
