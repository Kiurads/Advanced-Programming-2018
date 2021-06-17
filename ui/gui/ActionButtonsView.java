package ui.gui;

import model.ObservableGame;
import model.states.AwaitBeginning;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observer;

public class ActionButtonsView extends JFrame implements Observer {
    ObservableGame game;
    ActionButtonsPanel buttonsPanel;

    public ActionButtonsView(ObservableGame game) {
        super("Actions");
        this.game = game;
        game.addObserver(this);

        setupComponents();
        setupLayout();
        setSize(350, 300);
        setLocation(new Point(1500, 100));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        validate();
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        add(buttonsPanel, BorderLayout.CENTER);
    }

    private void setupComponents() {
        buttonsPanel = new ActionButtonsPanel(game);
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        if(game.getState() instanceof AwaitBeginning) setVisible(false);
        else setVisible(true);
    }
}