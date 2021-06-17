package ui.gui;

import model.ObservableGame;
import model.data.Rank;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class EnemiesPanel extends JPanel implements Observer {
    ObservableGame game;
    EnemyStatsPanel enemyStats;

    public EnemiesPanel(ObservableGame game) {
        this.game = game;
        game.addObserver(this);

//        setupComponents();
//        setupLayout();
    }

//    private void setupLayout() {
//        setLayout(new BorderLayout());
//
//        add(enemyStats, BorderLayout.CENTER);
//    }
//
//    private void setupComponents() {
//        enemyStats = new EnemyStatsPanel(game);
//    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Image card = Images.getImage(ConstantesGUI.CARD_ENEMY);
        Image token = Images.getImage(ConstantesGUI.COIN_ICON);
        g.drawImage(card, 10, 10, getWidth() - 10, getHeight() - 20, this);

        paintEnemyUnitToken(Rank.LADDER, token, g, 35);
        paintEnemyUnitToken(Rank.BATTERING_RAM, token, g, 160);
        if(!game.sigeTowerOut()) paintEnemyUnitToken(Rank.SIEGE_TOWER, token, g, 285);
        paintTrebuchet(token, g);
    }

    private void paintTrebuchet(Image token, Graphics g) {
        switch(game.getTrebuchetStrength()) {
            case 1:
                g.drawImage(token, 35, 515, 53, 59, this);
                break;
            case 2:
                g.drawImage(token, 160, 515, 53, 59, this);
                break;
            case 3:
                g.drawImage(token, 285, 515, 53, 59, this);
                break;
        }
    }

    private void paintEnemyUnitToken(Rank rank, Image token, Graphics g, int xpos) {
        switch(game.getEnemyUnitPosition(rank)) {
            case 4:
                g.drawImage(token, xpos, 400, 53, 59, this);
                break;
            case 3:
                g.drawImage(token, xpos, 305, 53, 59, this);
                break;
            case 2:
                g.drawImage(token, xpos, 210, 53, 59, this);
                break;
            case 1:
                g.drawImage(token, xpos, 115, 53, 59, this);
                break;
            case 0:
                g.drawImage(token, 160, 25, 53, 59, this);
                break;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
