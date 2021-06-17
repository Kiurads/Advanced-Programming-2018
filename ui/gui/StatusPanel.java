package ui.gui;

import model.ObservableGame;
import model.data.Status;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class StatusPanel extends JPanel implements Observer {
    ObservableGame game;
    StatusStatsPanel statsPanel;

    public StatusPanel(ObservableGame game) {
        this.game = game;
        game.addObserver(this);

//        statsPanel = new StatusStatsPanel(game);
//
//        setLayout(new BorderLayout());
//        add(statsPanel, BorderLayout.CENTER);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Image card = Images.getImage(ConstantesGUI.CARD_STATUS);
        Image moraleIcon = Images.getImage(ConstantesGUI.MORALE_ICON);
        Image suppliesIcon = Images.getImage(ConstantesGUI.SUPPLIES_ICON);
        Image wallStrengthIcon = Images.getImage(ConstantesGUI.WALL_ICON);
        Image soldiersIcon = Images.getImage(ConstantesGUI.SHIELD_ICON);

        g.drawImage(card, 10, 10, getWidth() - 10, getHeight() - 20, this);

        paintStatusToken(Status.MORALE, moraleIcon, g, 150);
        paintStatusToken(Status.SUPPLIES, suppliesIcon, g, 265);
        paintStatusToken(Status.WALL_STRENGTH, wallStrengthIcon, g, 35);
        paintSoldiersPosition(soldiersIcon, g);
        paintRaidedSupplies(suppliesIcon, g);
    }

    private void paintRaidedSupplies(Image suppliesIcon, Graphics g) {
        switch (game.getRaidedSupplies()) {
            case 1:
                g.drawImage(suppliesIcon, 280, 510, 50, 60, this);
                break;
            case 2:
                g.drawImage(suppliesIcon, 280, 450, 50, 60, this);
                break;
        }
    }

    private void paintSoldiersPosition(Image soldiersIcon, Graphics g) {
        switch (game.getSoldiersPosition()) {
            case CASTLE:
                g.drawImage(soldiersIcon, 20, 510, 50, 60, this);
                break;
            case TUNNEL_1:
                g.drawImage(soldiersIcon, 80, 510, 50, 60, this);
                break;
            case TUNNEL_2:
                g.drawImage(soldiersIcon, 135, 510, 50, 60, this);
                break;
            case ENEMY_LINES:
                g.drawImage(soldiersIcon, 195, 510, 50, 60, this);
                break;
        }
    }

    private void paintStatusToken(Status status, Image token, Graphics g, int xpos) {
        switch(game.getStatusStatus(status)) {
            case 4:
                g.drawImage(token, xpos, 25, 53, 59, this);
                break;
            case 3:
                g.drawImage(token, xpos, 120, 53, 59, this);
                break;
            case 2:
                g.drawImage(token, xpos, 215, 53, 59, this);
                break;
            case 1:
                g.drawImage(token, xpos, 310, 53, 59, this);
                break;
            case 0:
                g.drawImage(token, 150, 405, 53, 59, this);
                break;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
