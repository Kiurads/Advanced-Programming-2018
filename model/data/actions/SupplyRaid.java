package model.data.actions;

import model.data.GameData;
import model.data.Location;

public class SupplyRaid extends PlayerActions {
    @Override
    public void execute(GameData data) {
        if(!isPosible(data)) {
            warningNotPossible(data);
            return;
        }

        data.supplyRaid();

        data.decreaseAvailableActions();
    }

    @Override
    public boolean isPosible(GameData data) {
        return data.getAvailableActions() > 0 && data.getSoldiersPosition() == Location.ENEMY_LINES && data.getSoldiersSupplies() < 2 && isPosible();
    }
}
