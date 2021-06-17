package ui.gui;

import model.ObservableGame;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class DayStatsPanel extends JPanel implements Observer {
    ObservableGame game;
    JLabel currentDay;
    JLabel availableActions;

    public DayStatsPanel(ObservableGame game) {
        this.game = game;
        game.addObserver(this);

        setupComponents();
        setupLayout();
    }

    private void setupLayout() {
        setLayout(new GridLayout(1, 2));
        setBorder(BorderFactory.createTitledBorder("Day stats"));

        add(currentDay);
        add(availableActions);
    }

    private void setupComponents() {
        currentDay = new JLabel(String.valueOf(game.getCurrentDay()));
        availableActions = new JLabel("Action Points - " + game.getAvailableActions());

        setIcons();
    }

    private void setIcons() {
        Image sunIcon = Images.getImage(ConstantesGUI.SUN_ICON);

        currentDay.setIcon(new ImageIcon(sunIcon.getScaledInstance(32, 32, Image.SCALE_SMOOTH)));

        currentDay.setAlignmentX(CENTER_ALIGNMENT);
        availableActions.setAlignmentX(CENTER_ALIGNMENT);
    }

    @Override
    public void update(Observable o, Object arg) {
        currentDay.setText(String.valueOf(game.getCurrentDay()));
        availableActions.setText("Action Points - " + game.getAvailableActions());
    }
}
