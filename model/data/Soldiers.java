package model.data;

public class Soldiers {
    private Location position;
    private int supplies;
    
    public Soldiers() {
        position = Location.CASTLE;
        supplies = 0;
    }

    public void supplyRaid(GameData data) {
        data.getDice().roll();

        switch (data.getDice().getNumber()) {
            case (1):
                supplies = 0;
                getCaptured(data);
                break;
            case (6):
                if(supplies == 0) {
                    supplies += 2;
                    data.addMsgLog("[ " + "Successfully raided 2 supplies!" + " ]");
                }
                if (supplies == 1) {
                    supplies += 1;
                    data.addMsgLog("[ " + "Successfully raided 1 supplies!" + " ]");
                }
                break;
            default:
                supplies += 1;
                break;
        }
    }
    
    public void goToTunnel() {
        setPosition(getPosition() == Location.CASTLE ? Location.TUNNEL_1 : Location.TUNNEL_2);
    }

    public void exitTunnel(GameData data) {
        if(getPosition() == Location.TUNNEL_1) setPosition(Location.CASTLE);
        else setPosition(Location.ENEMY_LINES);

        if(position == Location.CASTLE) dropSupplies(data);
    }

    public void dropSupplies(GameData data) {
        data.addMsgLog("Hey, we brought some supplies!");
        data.increaseStatus(Status.SUPPLIES, supplies);
        supplies = 0;
    }

    public void moveThroughTunnel() {
        setPosition(getPosition() == Location.TUNNEL_1 ? Location.TUNNEL_2 : Location.TUNNEL_1);
    }

    public boolean areInEnemyLines() {
        return position == Location.ENEMY_LINES;
    }

    public void setPosition(Location position) {
        this.position = position;
    }

    public Location getPosition() {
        return position;
    }

    public void getCaptured(GameData data) {
        data.addMsgLog("[ " + "Your units got captured by the enemy! A new squad will prepare for battle!" + " ]");
        data.getStatus(Status.MORALE).reduceStatus(1);
        setSupplies(0);
        setPosition(Location.CASTLE);
    }

    public void setSupplies(int supplies) {
        this.supplies = supplies;
    }

    public int getSupplies() {
        return supplies;
    }

    @Override
    public String toString() {
        if(position == Location.CASTLE) return "Castle";
        if(position == Location.TUNNEL_1) return "Tunnel 1";
        if(position == Location.TUNNEL_2) return "Tunnel 2";
        if(position == Location.ENEMY_LINES) return "Enemy Lines";

        return null;
    }
}
