package model.data.actions;

import model.data.GameData;
import model.data.Location;

public class TunnelMovement extends PlayerActions {
    private boolean hasMoved;
    public TunnelMovement() {
        hasMoved = false;
    }

    @Override
    public void execute(GameData data, int action) {
        switch (action) {
            case 0:
                moveIntoTunnel(data);
                break;
            case 1:
                justMoveInTunnel(data);
                break;
            case 2:
                exitTunnel(data);
                break;
            case 3:
                toFurthestExit(data);
                break;
            case 4:
                automatic(data);
                break;
        }
    }

    private void moveIntoTunnel(GameData data) {
        if(data.getAvailableActions() == 0) {
            warningNotPossible(data);
            return;
        }

        data.goToTunnel();

        data.decreaseAvailableActions();
    }

    private void justMoveInTunnel(GameData data) {
        if(hasMoved) {
            warningNotPossible(data);
            return;
        }
        data.getSoldiers().moveThroughTunnel();
        hasMoved = true;
    }

    private void exitTunnel(GameData data) {
        data.getSoldiers().exitTunnel(data);
    }

    private void toFurthestExit(GameData data) {
        if(data.getAvailableActions() == 0) {
            warningNotPossible(data);
            return;
        }
        moveThroughTunnel(data);
        exitTunnel(data);

        data.decreaseAvailableActions();
    }

    private void automatic(GameData data) {

        if(data.getSoldiersPosition() == Location.TUNNEL_1 || data.getSoldiersPosition() == Location.TUNNEL_2) {
            data.setSoldiersPosition(Location.CASTLE);
        }
        if(data.getSoldiersPosition() == Location.ENEMY_LINES) {
            data.soldiersGetCaptured();
        }
    }

    private void moveThroughTunnel(GameData data) {
        data.getSoldiers().moveThroughTunnel();
    }

    @Override
    public boolean actionIsPosible(GameData data, int action) {
        if (action == 0) return data.getAvailableActions() > 0 && (data.soldiersInPosition(Location.ENEMY_LINES) || data.soldiersInPosition(Location.CASTLE));
        if (action == 1 || action == 2) return !hasMoved && (data.soldiersInPosition(Location.TUNNEL_1) || data.soldiersInPosition(Location.TUNNEL_2));
        return action == 3 && data.getAvailableActions() > 0 && (data.soldiersInPosition(Location.TUNNEL_1) || data.soldiersInPosition(Location.TUNNEL_2));
    }

    @Override
    public void resetDRM() {
        super.resetDRM();
        hasMoved = false;
    }

    @Override
    public boolean isPosible(GameData data) {
        return actionIsPosible(data, 0) || actionIsPosible(data, 1) || actionIsPosible(data, 3);
    }
}
