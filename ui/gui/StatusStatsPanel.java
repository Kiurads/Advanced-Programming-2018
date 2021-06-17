package ui.gui;

import model.ObservableGame;
import model.data.Status;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class StatusStatsPanel extends JPanel implements Observer{
    ObservableGame game;

    JLabel morale;
    JLabel supplies;
    JLabel wallStrength;
    JLabel soldiersPosition;

    public StatusStatsPanel(ObservableGame game) {
        this.game = game;
        game.addObserver(this);

        setupComponents();
        setupLayout();
    }

    private void setupLayout() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder("Fortress Status"));

        morale.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(morale);

        supplies.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(supplies);

        wallStrength.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(wallStrength);

        soldiersPosition.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(soldiersPosition);
    }

    private void setupComponents() {
        morale = new JLabel(String.valueOf(game.getStatusStatus(Status.MORALE)));
        supplies = new JLabel(String.valueOf(game.getStatusStatus(Status.SUPPLIES)));
        wallStrength = new JLabel(String.valueOf(game.getStatusStatus(Status.WALL_STRENGTH)));
        soldiersPosition = new JLabel(String.valueOf(game.getSoldiersPosition()));

        setIcons();
    }

    private void setIcons() {
        Image moraleIcon = Images.getImage(ConstantesGUI.MORALE_ICON);
        Image suppliesIcon = Images.getImage(ConstantesGUI.SUPPLIES_ICON);
        Image wallStrengthIcon = Images.getImage(ConstantesGUI.WALL_ICON);

        morale.setIcon(new ImageIcon(moraleIcon.getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
        supplies.setIcon(new ImageIcon(suppliesIcon.getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
        wallStrength.setIcon(new ImageIcon(wallStrengthIcon.getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
    }

    @Override
    public void update(Observable o, Object arg) {
        morale.setText(String.valueOf(game.getStatusStatus(Status.MORALE)));
        supplies.setText(String.valueOf(game.getStatusStatus(Status.SUPPLIES)));
        wallStrength.setText(String.valueOf(game.getStatusStatus(Status.WALL_STRENGTH)));
        soldiersPosition.setText(String.valueOf(game.getSoldiersPosition().toString()));
    }
}
