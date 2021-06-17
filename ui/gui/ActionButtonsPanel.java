package ui.gui;

import model.ObservableGame;
import model.data.Actions;
import model.data.Rank;
import model.states.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class ActionButtonsPanel extends JPanel implements Observer {
    ObservableGame game;
    private Map<Actions, JButton> buttonMap;
    private boolean removed;
    private String[] optionsRanks;
    private Rank[] ranks;

    public ActionButtonsPanel(ObservableGame game) {
        this.game = game;
        game.addObserver(this);

        setupComponents();
        setupLayout();
        setVisible(true);
        validate();
        removed = false;
        optionsRanks = new String[]{"Ladder", "Battering Ram", "Siege Tower"};
        ranks = new Rank[]{Rank.LADDER, Rank.BATTERING_RAM, Rank.SIEGE_TOWER};
    }

    private void setupLayout() {
        setLayout(new GridLayout(4, 2, 10, 10));
        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        setBorder(BorderFactory.createTitledBorder("Select an action"));

        add(buttonMap.get(Actions.ARCHERS_ATTACK));
        add(buttonMap.get(Actions.BOILING_WATER_ATTACK));
        add(buttonMap.get(Actions.CLOSE_COMBAT_ATTACK));
        add(buttonMap.get(Actions.COUPURE));
        add(buttonMap.get(Actions.RALLY_TROOPS));
        add(buttonMap.get(Actions.TUNNEL_MOVEMENT));
        add(buttonMap.get(Actions.SUPPLY_RAID));
        add(buttonMap.get(Actions.SABOTAGE));
    }

    private void setupComponents() {
        buttonMap = new HashMap<>();

        buttonMap.put(Actions.ARCHERS_ATTACK, new JButton("Archers Attack"));
        buttonMap.put(Actions.BOILING_WATER_ATTACK, new JButton("Boiling Water Attack"));
        buttonMap.put(Actions.CLOSE_COMBAT_ATTACK, new JButton("Close Combat Attack"));
        buttonMap.put(Actions.COUPURE, new JButton("Coupure"));
        buttonMap.put(Actions.RALLY_TROOPS, new JButton("Rally Troops"));
        buttonMap.put(Actions.TUNNEL_MOVEMENT, new JButton("Tunnel Movement"));
        buttonMap.put(Actions.SUPPLY_RAID, new JButton("Supply Raid"));
        buttonMap.put(Actions.SABOTAGE, new JButton("Sabotage"));

        setupListeners();
    }

    private void setupListeners() {
        buttonMap.get(Actions.ARCHERS_ATTACK).addActionListener(e -> game.archersAttack());

        buttonMap.get(Actions.BOILING_WATER_ATTACK).addActionListener(e -> game.boilingWaterAttack());

        buttonMap.get(Actions.CLOSE_COMBAT_ATTACK).addActionListener(e -> game.closeCombatAttack());

        buttonMap.get(Actions.COUPURE).addActionListener(e -> game.coupure());

        buttonMap.get(Actions.RALLY_TROOPS).addActionListener(e -> game.rallyTroops());

        buttonMap.get(Actions.SABOTAGE).addActionListener(e -> game.sabotage());

        buttonMap.get(Actions.SUPPLY_RAID).addActionListener(e -> game.supplyRaid());

        buttonMap.get(Actions.TUNNEL_MOVEMENT).addActionListener(e -> game.tunnelMovement());
    }


    @Override
    public void update(Observable o, Object arg) {
        for (Map.Entry<Actions, JButton> entry : buttonMap.entrySet()) {
            Actions action = entry.getKey();
            JButton button = entry.getValue();

            button.setEnabled(game.isActionPosible(action) && game.getState() instanceof AwaitAction);
        }

        if(game.getState() instanceof ArchersAttackAwaitLane) {
            setAvailableRanksAfter(4);
            game.executeAction(ranks[getRank()]);
        }

        if(game.getState() instanceof BoilingWaterAttackAwaitLane) {
            setAvailableRanksIn(1);
            game.executeAction(ranks[getRank()]);
        }

        if(game.getState() instanceof CloseCombatAttackAwaitLane) {
            setAvailableRanksIn(0);
            game.executeAction(ranks[getRank()]);
        }

        if(game.getState() instanceof Coupure || game.getState() instanceof Sabotage || game.getState() instanceof SupplyRaid) {
            game.executeAction();
        }

        if(game.getState() instanceof RallyTroops) {
            game.executeAction(useSupplies() == 0);
        }

        if(game.sigeTowerOut() && !removed) {
            optionsRanks = new String[]{"Ladder", "Battering Ram"};
            ranks = new Rank[]{Rank.LADDER, Rank.BATTERING_RAM};
            removed = true;
        }

        if(game.enemiesOnCloseCombat() == 2) {
            JOptionPane.showMessageDialog(null, "Two enemies on close combat! Attack NOW!");
            game.closeCombatAttack();
        }
    }

    private void setAvailableRanksIn(int position) {
        int i = 0;

        if(game.getEnemyUnitPosition(Rank.LADDER) == position) i += 1;
        if(game.getEnemyUnitPosition(Rank.BATTERING_RAM) == position) i += 2;
        if(!game.sigeTowerOut()) if(game.getEnemyUnitPosition(Rank.SIEGE_TOWER) == position) i += 5;

        switch(i) {
            case 1:
                ranks = new Rank[]{Rank.LADDER};
                optionsRanks = new String[]{"Ladder"};
                break;
            case 2:
                ranks = new Rank[]{Rank.BATTERING_RAM};
                optionsRanks = new String[]{"Battering Ram"};
                break;
            case 3:
                ranks = new Rank[]{Rank.LADDER, Rank.BATTERING_RAM};
                optionsRanks = new String[]{"Ladder", "Battering Ram"};
                break;
            case 5:
                ranks = new Rank[]{Rank.SIEGE_TOWER};
                optionsRanks = new String[]{"Siege Tower"};
                break;
            case 6:
                ranks = new Rank[]{Rank.LADDER, Rank.SIEGE_TOWER};
                optionsRanks = new String[]{"Ladder", "Siege Tower"};
                break;
            case 7:
                ranks = new Rank[]{Rank.BATTERING_RAM, Rank.SIEGE_TOWER};
                optionsRanks = new String[]{"Battering Ram", "Siege Tower"};
                break;
            case 8:
                ranks = new Rank[]{Rank.LADDER, Rank.BATTERING_RAM, Rank.SIEGE_TOWER};
                optionsRanks = new String[]{"Ladder", "Battering Ram", "Siege Tower"};
                break;
        }
    }

    private void setAvailableRanksAfter(int position) {
        int i = 0;

        if(game.getEnemyUnitPosition(Rank.LADDER) < position) i += 1;
        if(game.getEnemyUnitPosition(Rank.BATTERING_RAM) < position) i += 2;
        if(!game.sigeTowerOut()) if(game.getEnemyUnitPosition(Rank.SIEGE_TOWER) < position) i += 5;

        switch(i) {
            case 1:
                ranks = new Rank[]{Rank.LADDER};
                optionsRanks = new String[]{"Ladder"};
                break;
            case 2:
                ranks = new Rank[]{Rank.BATTERING_RAM};
                optionsRanks = new String[]{"Battering Ram"};
                break;
            case 3:
                ranks = new Rank[]{Rank.LADDER, Rank.BATTERING_RAM};
                optionsRanks = new String[]{"Ladder", "Battering Ram"};
                break;
            case 5:
                ranks = new Rank[]{Rank.SIEGE_TOWER};
                optionsRanks = new String[]{"Siege Tower"};
                break;
            case 6:
                ranks = new Rank[]{Rank.LADDER, Rank.SIEGE_TOWER};
                optionsRanks = new String[]{"Ladder", "Siege Tower"};
                break;
            case 7:
                ranks = new Rank[]{Rank.BATTERING_RAM, Rank.SIEGE_TOWER};
                optionsRanks = new String[]{"Battering Ram", "Siege Tower"};
                break;
            case 8:
                ranks = new Rank[]{Rank.LADDER, Rank.BATTERING_RAM, Rank.SIEGE_TOWER};
                optionsRanks = new String[]{"Ladder", "Battering Ram", "Siege Tower"};
                break;
        }
    }

    private int useSupplies() {
        return JOptionPane.showConfirmDialog (null, "Use supply for +1 DRM?","Confirm", JOptionPane.YES_NO_OPTION);
    }

    public int getRank() {
        return JOptionPane.showOptionDialog(null, "Which lane will be attacked?", "Choose option", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionsRanks, null);
    }
}
