package ui.gui;

import model.ObservableGame;
import model.data.Status;
import model.states.AwaitAction;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class ButtonsPanel extends JPanel implements Observer{
    ObservableGame game;
    JButton moraleForAP;
    JButton supplyForAP;
    JButton endTurn;

    public ButtonsPanel(ObservableGame game) {
        this.game = game;
        game.addObserver(this);

        setupComponents();
        setupLayout();
    }

    private void setupLayout() {
        setLayout(new GridLayout(3, 1, 10, 10));

        add(moraleForAP);
        add(supplyForAP);
        add(endTurn);
    }

    private void setupComponents() {
        moraleForAP = new JButton("Morale -> Action Point");
        supplyForAP = new JButton("Supply -> Action Point");
        endTurn = new JButton("End Turn");

        setupListeners();
    }

    private void setupListeners() {
        moraleForAP.addActionListener(e -> game.moraleForAP());
        supplyForAP.addActionListener(e -> game.supplyForAP());
        endTurn.addActionListener(e -> game.endTurn());
    }

    @Override
    public void update(Observable o, Object arg) {
        moraleForAP.setEnabled(game.getStatusStatus(Status.MORALE) > 0 && game.getState() instanceof AwaitAction);
        supplyForAP.setEnabled(game.getStatusStatus(Status.SUPPLIES) > 0 && game.getState() instanceof AwaitAction);
        endTurn.setEnabled(game.getState() instanceof AwaitAction);
    }
}
