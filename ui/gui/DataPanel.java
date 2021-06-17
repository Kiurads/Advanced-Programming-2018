package ui.gui;

import model.ObservableGame;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class DataPanel extends JPanel{
    ObservableGame game;
    DayStatsPanel dayStatsPanel;
    ButtonsPanel buttonsPanel;

    public DataPanel(ObservableGame game) {
        this.game = game;

        setupComponents();
        setupLayout();
    }

    private void setupLayout() {
        setLayout(new GridLayout(2, 1));

        add(dayStatsPanel);
        add(buttonsPanel);
    }

    private void setupComponents() {
        dayStatsPanel = new DayStatsPanel(game);
        buttonsPanel = new ButtonsPanel(game);
    }
}
