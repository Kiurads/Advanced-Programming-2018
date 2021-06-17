package ui.gui;

import model.ObservableGame;
import model.states.AwaitBeginning;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class StartPanel extends JPanel implements Observer{
    ObservableGame game;
    StartPanelOptionsPanel optionsPanel;
    JLabel cardSiege;

    public StartPanel(ObservableGame game) {
        this.game = game;
        game.addObserver(this);

        setupComponents();
        setupLayout();
        validate();
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        add(optionsPanel, BorderLayout.CENTER);
        add(cardSiege, BorderLayout.NORTH);
    }

    private void setupComponents() {
        optionsPanel = new StartPanelOptionsPanel(game);

        Image image = Images.getImage(ConstantesGUI.SPLASH_SCREEN);
        cardSiege = new JLabel(new ImageIcon(image));
    }

    @Override
    public void update(Observable o, Object arg) {
        if(game.getState() instanceof AwaitBeginning) setVisible(true);
        else setVisible(false);
    }
}
