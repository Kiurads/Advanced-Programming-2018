import model.ObservableGame;
import ui.gui.ActionButtonsView;
import ui.gui.CardSiegeView;
import ui.gui.TunnelMovementView;

public class Main {
    public static void main(String[] args) {
        ObservableGame game = new ObservableGame();
        CardSiegeView gui = new CardSiegeView(game);
        ActionButtonsView actionButtonsView = new ActionButtonsView(game);
        TunnelMovementView tunnelMovementView = new TunnelMovementView(game);
    }
}
