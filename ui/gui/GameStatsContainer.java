package ui.gui;

import model.ObservableGame;
import model.states.AwaitBeginning;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

public class GameStatsContainer extends Container implements Observer{
    ObservableGame game;
    Container dataContainer;
    EnemiesPanel enemiesPanel;
    StatusPanel statusPanel;
    DicePanel dicePanel;
    CardPanel cardPanel;
    DataPanel dataPanel;

    public GameStatsContainer(ObservableGame game) {
        this.game = game;
        game.addObserver(this);
        dataContainer = new Container();
        enemiesPanel = new EnemiesPanel(game);
        statusPanel = new StatusPanel(game);
        dicePanel = new DicePanel(game);
        cardPanel = new CardPanel(game);
        dataPanel = new DataPanel(game);

        addComponents();
        setVisible(false);
        validate();
    }

    private void addComponents() {
        setLayout(new GridLayout(1, 4));
        dataContainer.setLayout(new GridLayout(2, 1));
        dataContainer.add(dicePanel);
        dataContainer.add(dataPanel);
        dataContainer.setVisible(true);

        add(dataContainer);
        add(enemiesPanel);
        add(statusPanel);
        add(cardPanel);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(game.getState() instanceof AwaitBeginning) setVisible(false);
        else setVisible(true);
    }
}
