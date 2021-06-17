package ui.gui;

import model.ObservableGame;
import model.states.TunnelMovementAwaitSelection;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class TunnelMovementPanel extends JPanel implements Observer {
    ObservableGame game;
    JButton moveIntoTunnel;
    JButton moveInsideTunnel;
    JButton exitTunnel;
    JButton toFurthestExit;

    public TunnelMovementPanel(ObservableGame game) {
        this.game = game;
        game.addObserver(this);

        setupComponents();
        setupLayout();
    }

    private void setupLayout() {
        setLayout(new GridLayout(2, 2));

        add(moveIntoTunnel);
        add(moveInsideTunnel);
        add(exitTunnel);
        add(toFurthestExit);
    }

    private void setupComponents() {
        moveIntoTunnel = new JButton("Go to tunnel");
        moveInsideTunnel = new JButton("Move inside tunnel");
        exitTunnel = new JButton("Exit tunnel");
        toFurthestExit = new JButton("Go to away exit");
        
        setupListeners();
    }

    private void setupListeners() {
        moveIntoTunnel.addActionListener(e -> game.executeAction(0));
        moveInsideTunnel.addActionListener(e -> game.executeAction(1));
        exitTunnel.addActionListener(e -> game.executeAction(2));
        toFurthestExit.addActionListener(e -> game.executeAction(3));
    }

    @Override
    public void update(Observable o, Object arg) {
        moveIntoTunnel.setEnabled(game.getState() instanceof TunnelMovementAwaitSelection);
        moveInsideTunnel.setEnabled(game.getState() instanceof TunnelMovementAwaitSelection);
        toFurthestExit.setEnabled(game.getState() instanceof TunnelMovementAwaitSelection);
        exitTunnel.setEnabled(game.getState() instanceof TunnelMovementAwaitSelection);

        if(!game.tunnelActionIsPosible(0)) moveIntoTunnel.setEnabled(false);
        if(!game.tunnelActionIsPosible(1)) moveInsideTunnel.setEnabled(false);
        if(!game.tunnelActionIsPosible(2)) exitTunnel.setEnabled(false);
        if(!game.tunnelActionIsPosible(3)) toFurthestExit.setEnabled(false);
    }
}
