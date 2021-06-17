package model.data;

import model.data.actions.*;
import model.data.cards.*;

import java.util.*;

public class GameData {
    private int currentDay;
    private int availableActions;
    private Dice dice;
    private List<Card> deck;
    private Map<Rank, EnemyUnit> enemyTracker;
    private Map<Status, StatusTracker> statusTracker;
    private Map<Actions, PlayerActions> playerActionsMap;
    private List<String> msgLog;
    private Soldiers soldiers;
    private Trebuchets trebuchets;

    public GameData() {
        currentDay = 1;
        deck = new ArrayList<>();
        msgLog = new ArrayList<>();
        dice = new Dice();
        enemyTracker = new HashMap<>();
        statusTracker = new HashMap<>();
        playerActionsMap = new HashMap<>();
        soldiers = new Soldiers();
        trebuchets = new Trebuchets();
        availableActions = 0;

        //Insert cards on the deck
        fillDeck();
        //Set enemy units
        setupEnemies();
        //Start status tracker
        setupStatusTracker();
        //Setup player actions
        setupPlayerActionsMap();
    }

    private void setupPlayerActionsMap() {
        playerActionsMap.put(Actions.ARCHERS_ATTACK, new ArchersAttack());
        playerActionsMap.put(Actions.BOILING_WATER_ATTACK, new BoilingWaterAttack());
        playerActionsMap.put(Actions.CLOSE_COMBAT_ATTACK, new CloseCombatAttack());
        playerActionsMap.put(Actions.COUPURE, new Coupure());
        playerActionsMap.put(Actions.RALLY_TROOPS, new RallyTroops());
        playerActionsMap.put(Actions.TUNNEL_MOVEMENT, new TunnelMovement());
        playerActionsMap.put(Actions.SUPPLY_RAID, new SupplyRaid());
        playerActionsMap.put(Actions.SABOTAGE, new Sabotage());
    }

    private void setupStatusTracker() {
        statusTracker.put(Status.WALL_STRENGTH, new StatusTracker());
        statusTracker.put(Status.MORALE, new StatusTracker());
        statusTracker.put(Status.SUPPLIES, new StatusTracker());
    }

    private void setupEnemies() {
        enemyTracker.put(Rank.LADDER, new EnemyUnit());
        enemyTracker.put(Rank.BATTERING_RAM, new EnemyUnit());
        enemyTracker.put(Rank.SIEGE_TOWER, new EnemyUnit());

        getEnemyUnit(Rank.LADDER).setStrength(2);
        getEnemyUnit(Rank.BATTERING_RAM).setStrength(3);
        getEnemyUnit(Rank.SIEGE_TOWER).setStrength(4);
    }

    private void fillDeck() {
        deck.clear();
        deck.add(new Card1());
        deck.add(new Card2());
        deck.add(new Card3());
        deck.add(new Card4());
        deck.add(new Card5());
        deck.add(new Card6());
        deck.add(new Card7());

        shuffleDeck();
    }

    public void addMsgLog(String msg) {
        msgLog.add(msg);
    }

    public int getCurrentDay() {
        return currentDay;
    }

    public Dice getDice() {
        return dice;
    }

    public Soldiers getSoldiers() {
        return soldiers;
    }

    public EnemyUnit getEnemyUnit(Rank index) {
        return enemyTracker.get(index);
    }

    public StatusTracker getStatus(Status index) {
        return statusTracker.get(index);
    }

    public void damageWall(int strength) {
        getStatus(Status.WALL_STRENGTH).reduceStatus(strength);
    }

    private Trebuchets getTrebuchets() {
        return trebuchets;
    }

    public int getAvailableActions() {
        return availableActions;
    }

    private void setAvailableActions(int availableActions) {
        this.availableActions = availableActions;
    }

    public void decreaseAvailableActions() {
        this.availableActions--;
    }

    private PlayerActions getPlayerAction(Actions index) {
        return playerActionsMap.get(index);
    }

    private Map<Rank, EnemyUnit> getEnemyTracker() {
        return enemyTracker;
    }

    private void shuffleDeck() {
        Collections.shuffle(deck);
    }

    public void playCard() {
        deck.get(0).executeAction(this);
        setAvailableActions(deck.get(0).getPlayerActions(getCurrentDay()));
    }

    public void moraleForAP() {
        getStatus(Status.MORALE).reduceStatus(1);
        setAvailableActions(getAvailableActions() + 1);
    }

    public void supplyForAP() {
        getStatus(Status.SUPPLIES).reduceStatus(1);
        setAvailableActions(getAvailableActions() + 1);
    }

    public boolean checkForInstantGameOver() {
        int unitsInCloseCombat = 0;
        int statusAtZero = 0;

        if (getEnemyUnitPosition(Rank.LADDER) == 0) unitsInCloseCombat++;
        if (getEnemyUnitPosition(Rank.BATTERING_RAM) == 0) unitsInCloseCombat++;
        if (getEnemyTracker().containsKey(Rank.SIEGE_TOWER)) {
            if (getEnemyUnitPosition(Rank.SIEGE_TOWER) == 0) unitsInCloseCombat++;
        }

        if (getStatusStatus(Status.MORALE) == 0) statusAtZero++;
        if (getStatusStatus(Status.WALL_STRENGTH) == 0) statusAtZero++;
        if (getStatusStatus(Status.SUPPLIES) == 0) statusAtZero++;

        return statusAtZero >= 2 || unitsInCloseCombat == 3;
    }

    public boolean checkForGameOver() {
        int unitsInCloseCombat = 0;
        int statusAtZero = 0;

        if (getEnemyUnitPosition(Rank.LADDER) == 0) unitsInCloseCombat++;
        if (getEnemyUnitPosition(Rank.BATTERING_RAM) == 0) unitsInCloseCombat++;
        if (getEnemyTracker().containsKey(Rank.SIEGE_TOWER)) {
            if (getEnemyUnitPosition(Rank.SIEGE_TOWER) == 0) unitsInCloseCombat++;
        }

        if (getStatusStatus(Status.MORALE) == 0) statusAtZero++;
        if (getStatusStatus(Status.WALL_STRENGTH) == 0) statusAtZero++;
        if (getStatusStatus(Status.SUPPLIES) == 0) statusAtZero++;

        return statusAtZero >= 1 || unitsInCloseCombat == 2;
    }

    public void nextDay() {
        resetDRM();
        getPlayerAction(Actions.TUNNEL_MOVEMENT).execute(this, 4);
        getSoldiers().dropSupplies(this);
        fillDeck();
        currentDay++;
    }

    private void resetDRM() {
        resetDiceDRM();
        resetEnemyUnitsDRM();
        resetStatusDRM();
        resetPlayerActionsDRM();
    }

    private void resetPlayerActionsDRM() {
        for (Map.Entry<Actions, PlayerActions> entry : playerActionsMap.entrySet()) {
            entry.getValue().resetDRM();
        }
    }

    private void resetStatusDRM() {
        getStatus(Status.MORALE).resetDRM();
        getStatus(Status.WALL_STRENGTH).resetDRM();
        getStatus(Status.SUPPLIES).resetDRM();
    }

    private void resetEnemyUnitsDRM() {
        getEnemyUnit(Rank.LADDER).resetDRM();
        getEnemyUnit(Rank.BATTERING_RAM).resetDRM();
        if (getEnemyTracker().containsKey(Rank.SIEGE_TOWER)) {
            getEnemyUnit(Rank.SIEGE_TOWER).resetDRM();
        }
    }

    private void resetDiceDRM() {
        getDice().resetDRM();
    }

    public void endTurn() {
        resetDRM();
    }

    public void trebuchetsAttack() {
        getTrebuchets().attackWall(this);
    }

    public void enemyUnitAdvance(Rank enemyUnit) {
        if (enemyTracker.containsKey(enemyUnit)) getEnemyUnit(enemyUnit).advance(this);
    }

    public void reduceStatus(Status status, int loss) {
        getStatus(status).reduceStatus(loss);
    }

    public void moveFurthest() {
        int max = getMaxPosition();

        if (getEnemyTracker().containsKey(Rank.SIEGE_TOWER)) {
            if (getEnemyUnitPosition(Rank.SIEGE_TOWER) == max) {
                enemyUnitAdvance(Rank.SIEGE_TOWER);
                addMsgLog("[ " + "Siege tower advanced successfully and is now in position " + getEnemyUnitPosition(Rank.SIEGE_TOWER) + " ]");
            }
        }

        if (getEnemyUnitPosition(Rank.BATTERING_RAM) == max) {
            enemyUnitAdvance(Rank.BATTERING_RAM);
            addMsgLog("[ " + "Battering ram advanced successfully and is now in position " + getEnemyUnitPosition(Rank.BATTERING_RAM) + " ]");
        }

        if (getEnemyUnitPosition(Rank.LADDER) == max) {
            enemyUnitAdvance(Rank.LADDER);
            addMsgLog("[ " + "Rank.LADDER advanced successfully and is now in position " + getEnemyUnitPosition(Rank.LADDER) + " ]");
        }
    }

    private int getMaxPosition() {
        int max = 0;

        if (getEnemyTracker().containsKey(Rank.SIEGE_TOWER)) {
            if (max <= getEnemyUnit(Rank.SIEGE_TOWER).getPosition())
                max = getEnemyUnit(Rank.SIEGE_TOWER).getPosition();
        }
        if (max <= getEnemyUnit(Rank.LADDER).getPosition())
            max = getEnemyUnit(Rank.LADDER).getPosition();
        if (max <= getEnemyUnit(Rank.BATTERING_RAM).getPosition())
            max = getEnemyUnit(Rank.BATTERING_RAM).getPosition();

        return max;
    }

    public void badWeather() {
        getPlayerAction(Actions.ARCHERS_ATTACK).setPosible(false);
        getPlayerAction(Actions.BOILING_WATER_ATTACK).setPosible(false);
        getPlayerAction(Actions.CLOSE_COMBAT_ATTACK).setPosible(false);
        getPlayerAction(Actions.COUPURE).setPosible(false);
        getPlayerAction(Actions.RALLY_TROOPS).setPosible(false);
        getPlayerAction(Actions.TUNNEL_MOVEMENT).setPosible(false);
    }

    public void increaseSpacesDRM(int position, int DRM) {
        getDice().modifyPositionDRM(position, DRM);
    }

    public void modifyEnemyUnitDRM(Rank enemyUnit, int DRM) {
        getEnemyTracker().get(enemyUnit).modifyDRM(DRM);
    }

    public void collapsed() {
        if(getEnemyUnitPosition(Rank.SIEGE_TOWER) == 4)
            getEnemyTracker().remove(Rank.SIEGE_TOWER);
    }

    public void addTrebuchet() {
        getTrebuchets().increaseStrength(this);
    }

    public void actionModifyDRM(Actions action, int DRM) {
        getPlayerAction(action).modifyDRM(DRM);
    }

    public void modifyStatusDRM(Status status, int DRM) {
        getStatus(status).modifyDRM(DRM);
    }

    public int getStatusDRM(Status status) {
        return getStatus(status).getDRM();
    }

    public int getEnemyUnitDRM(Rank lane) {
        return getEnemyUnit(lane).getDRM();
    }

    public int getPositionDRM(int position) {
        return getDice().getDRM(position);
    }

    public int getEnemyUnitPosition(Rank lane) {
        return getEnemyUnit(lane).getPosition();
    }

    public void rollDice() {
        getDice().roll();
    }

    public int getEnemyUnitStrength(Rank lane) {
        return getEnemyUnit(lane).getStrength();
    }

    public int getDiceNumber() {
        return getDice().getNumber();
    }

    public void enemyUnitRetreat(Rank lane) {
        getEnemyUnit(lane).retreat();
    }

    public int getStatusStatus(Status status) {
        return getStatus(status).getStatus();
    }

    public void increaseStatus(Status status, int gain) {
        getStatus(status).increaseStatus(gain);
    }

    public Location getSoldiersPosition() {
        return getSoldiers().getPosition();
    }

    public void destroyTrebuchet() {
        getTrebuchets().decreaseStrength(this);
    }

    public void soldiersGetCaptured() {
        getSoldiers().getCaptured(this);
    }

    public void supplyRaid() {
        getSoldiers().supplyRaid(this);
    }

    public void goToTunnel() {
        getSoldiers().goToTunnel();
    }

    public int getTrebuchetsStrength() {
        return getTrebuchets().getStrength();
    }

    public void setSoldiersPosition(Location position) {
        getSoldiers().setPosition(position);
    }

    public String getCurrentCard() {
        return deck.get(0).toString();
    }

    public boolean isActionPosible(Actions action) {
        return getPlayerAction(action).isPosible(this);
    }

    public boolean anyEnemyOut() {
        for (EnemyUnit unit : getEnemyTracker().values()) {
            if (unit.getPosition() < 4) return true;
        }
        return false;
    }

    public boolean anyEnemyOnPosition(int position) {
        for (EnemyUnit unit : getEnemyTracker().values()) {
            if (unit.getPosition() == position) return true;
        }
        return false;
    }

    public void executeAction(Actions action, Rank lane) {
        getPlayerAction(action).execute(this, lane);
    }

    public void executeAction(Actions action, boolean option) {
        getPlayerAction(action).execute(this, option);
    }

    public void executeAction(Actions action, int number) {
        getPlayerAction(action).execute(this, number);
    }

    public void executeAction(Actions action) {
        getPlayerAction(action).execute(this);
    }

    public boolean soldiersInPosition(Location position) {
        return getSoldiersPosition() == position;
    }

    public boolean tunnelActionIsPosible(int action) {
        return getPlayerAction(Actions.TUNNEL_MOVEMENT).actionIsPosible(this, action);
    }

    public int getSoldiersSupplies() {
        return getSoldiers().getSupplies();
    }

    public void removeCard() {
        deck.remove(0);
    }

    public boolean noCardsLeft() {
        return deck.isEmpty();
    }

    public int enemiesOnCloseCombat() {
        int i = 0;
        if(getEnemyUnitPosition(Rank.LADDER) == 0) i++;
        if(getEnemyUnitPosition(Rank.BATTERING_RAM) == 0) i++;
        if(getEnemyTracker().containsKey(Rank.SIEGE_TOWER))
            if(getEnemyUnitPosition(Rank.SIEGE_TOWER) == 0) i++;

        return i;
    }

    public boolean siegeTowerOut() {
        return !getEnemyTracker().containsKey(Rank.SIEGE_TOWER);
    }

    @Override
    public String toString() {
        String s;

        s = "Enemy stats:\n";
        s += "  Rank.LADDER - " + getEnemyUnitPosition(Rank.LADDER) + "\n";
        s += "  Battering Ram - " + getEnemyUnitPosition(Rank.BATTERING_RAM) + "\n";
        if (getEnemyTracker().containsKey(Rank.SIEGE_TOWER)) {
            s += "  Siege Tower - " + getEnemyUnitPosition(Rank.SIEGE_TOWER) + "\n";
        }
        s += "  Trebuchets - " + getTrebuchetsStrength() + "\n";
        s += "Stats:\n";
        s += "  Wall Strength - " + getStatusStatus(Status.WALL_STRENGTH) + "\n";
        s += "  Morale - " + getStatusStatus(Status.MORALE) + "\n";
        s += "  Supplies - " + getStatusStatus(Status.SUPPLIES) + "\n";
        s += "Units:\n";
        s += "  Position - " + getSoldiers().toString() + "\n";
        s += "  Supplies - " + getSoldiers().getSupplies() + "\n";
        s += "Day - " + getCurrentDay() + "\n";
        s += "AP - " + getAvailableActions();

        return s;
    }
}