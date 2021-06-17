package model.states;

import model.data.GameData;

public class EndDay extends StateAdapter {
    public EndDay(GameData data) {
        super(data);
    }

    @Override
    public IStates endDay() {

        if(data.getCurrentDay() == 3) return new WinState(data);
        else {
            data.nextDay();
            return new AwaitCard(data);
        }
    }
}
