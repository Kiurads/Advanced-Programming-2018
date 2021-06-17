package ui.gui;

import model.ObservableGame;
import model.states.AwaitBeginning;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class DicePanel extends JPanel implements Observer, ConstantesGUI{
    ObservableGame game;
//    JButton rollDice;

    private static final String[] filePaths = {DICE1, DICE2, DICE3, DICE4, DICE5, DICE6};
    private static Image diceSides[] = new Image[filePaths.length];

    public DicePanel(ObservableGame game) {
        this.game = game;
        game.addObserver(this);

        setupComponents();
        setupLayout();
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Dice"));

//        add(rollDice, BorderLayout.SOUTH);
    }

    private void setupComponents() {
        setupImages();
//        rollDice = new JButton("Roll Dice");

//        rollDice.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                game.rollDice();
//            }
//        });
    }

    private void setupImages() {
        int i=0;

        for(String fileName:filePaths) {
            diceSides[i++] = Images.getImage(fileName);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Image dice = diceSides[game.getDiceNumber() - 1];
        g.drawImage(dice, getWidth() / 3, getHeight() / 3, getWidth() / 3, getHeight() / 3 + 30, this);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
