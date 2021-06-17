package ui.gui;

import model.ObservableGame;
import model.data.Rank;
import model.states.AwaitBeginning;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class EnemyStatsPanel extends JPanel implements Observer{
    ObservableGame game;
    private boolean removed = false;

    JLabel ladder;
    JLabel batteringRam;
    JLabel siegeTower;
    JLabel trebuchet;

    public EnemyStatsPanel(ObservableGame game) {
        this.game = game;
        game.addObserver(this);

        setupComponents();
        setupLayout();
    }

    private void setupLayout() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder("Enemy Stats"));

        ladder.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(ladder);

        batteringRam.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(batteringRam);

        siegeTower.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(siegeTower);

        trebuchet.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(trebuchet);
    }

    private void setupComponents() {
        ladder = new JLabel(String.valueOf(game.getEnemyUnitPosition(Rank.LADDER)));
        batteringRam = new JLabel(String.valueOf(game.getEnemyUnitPosition(Rank.BATTERING_RAM)));
        siegeTower = new JLabel(String.valueOf(game.getEnemyUnitPosition(Rank.SIEGE_TOWER)));
        trebuchet = new JLabel(String.valueOf(game.getTrebuchetStrength()));

        setIcons();
    }

    private void setIcons() {
        Image ladderIcon = null;
        Image batteringRamIcon = null;
        Image siegeTowerIcon = null;
        Image trebuchetIcon = null;

        try {
            ladderIcon = ImageIO.read(Resources.getResourceFile("images/enemiesPanel/Ladder.png"));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        assert ladderIcon != null;
        ladder.setIcon(new ImageIcon(ladderIcon.getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
        try {
            batteringRamIcon = ImageIO.read(Resources.getResourceFile("images/enemiesPanel/Battering Ram.png"));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        assert batteringRamIcon != null;
        batteringRam.setIcon(new ImageIcon(batteringRamIcon.getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
        try {
            siegeTowerIcon = ImageIO.read(Resources.getResourceFile("images/enemiesPanel/Siege.png"));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        assert siegeTowerIcon != null;
        siegeTower.setIcon(new ImageIcon(siegeTowerIcon.getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
        try {
            trebuchetIcon = ImageIO.read(Resources.getResourceFile("images/enemiesPanel/Trebuchet.png"));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        assert trebuchetIcon != null;
        trebuchet.setIcon(new ImageIcon(trebuchetIcon.getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
    }

    @Override
    public void update(Observable o, Object arg) {
        ladder.setText(String.valueOf(game.getEnemyUnitPosition(Rank.LADDER)));
        batteringRam.setText(String.valueOf(game.getEnemyUnitPosition(Rank.BATTERING_RAM)));
        siegeTower.setText(String.valueOf(game.getEnemyUnitPosition(Rank.SIEGE_TOWER)));
        trebuchet.setText(String.valueOf(game.getTrebuchetStrength()));

        if(game.sigeTowerOut() && !removed) {
            remove(siegeTower);
            removed = true;
        }
    }
}
