package ui.gui;

import model.ObservableGame;
import model.states.TunnelMovementAwaitSelection;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class TunnelMovementView extends JFrame implements Observer {
    ObservableGame game;
    TunnelMovementPanel panel;

    public TunnelMovementView(ObservableGame game) {
        super("Choose movement");
        this.game = game;
        game.addObserver(this);

        setupComponents();
        setupLayout();
        setSize(350, 150);
        setLocation(new Point(900, 100));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        validate();
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        add(panel, BorderLayout.CENTER);
    }

    private void setupComponents() {
        panel = new TunnelMovementPanel(game);
    }

    @Override
    public void update(Observable o, Object arg) {
        setVisible(game.getState() instanceof TunnelMovementAwaitSelection);
    }
}
