package ui.gui;

import model.ObservableGame;
import model.states.AwaitBeginning;
import model.states.AwaitCard;
import model.states.EndDay;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

public class CardPanel extends JPanel implements Observer {
    ObservableGame game;
    Image card;

    public CardPanel(ObservableGame game) {
        this.game = game;
        game.addObserver(this);

        addMouseListener(new MouseClickEvent());
//        setupLayout();
    }

//    private void setupLayout() {
//        setBorder(BorderFactory.createTitledBorder("Current Card"));
//    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(game.getState() instanceof AwaitCard) card = Images.getImage(ConstantesGUI.CARD0);
        else if(!(game.getState() instanceof EndDay)){
            card = Images.getImage(game.getCard());
        }
        g.drawImage(card, 10, 10, getWidth() - 10, getHeight() - 20, this);
    }
    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    class MouseClickEvent extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            game.playCard();
        }
    }
}
