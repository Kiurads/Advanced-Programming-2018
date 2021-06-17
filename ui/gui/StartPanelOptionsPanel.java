package ui.gui;

import model.Game;
import model.ObservableGame;
import model.data.files.FileUtility;
import model.states.AwaitBeginning;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class StartPanelOptionsPanel extends JPanel {
    ObservableGame game;

    private JButton start;
    private JButton quit;

    public StartPanelOptionsPanel(ObservableGame game) {
        this.game = game;

        setBackground(Color.LIGHT_GRAY);
        setupComponents();
        setupLayout();

        setVisible(true);
    }

    private void setupLayout() {
        setLayout(new FlowLayout());

        Image playIcon = Images.getImage(ConstantesGUI.START_ICON);
        Image stopIcon = Images.getImage(ConstantesGUI.STOP_ICON);

        start.setIcon(new ImageIcon(playIcon.getScaledInstance(13,13,Image.SCALE_SMOOTH)));
        add(start);

        quit.setIcon(new ImageIcon(stopIcon.getScaledInstance(13,13,Image.SCALE_SMOOTH)));
        add(quit);

        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        validate();
    }

    private void setupComponents() {
        start = new JButton("Start");
        quit = new JButton("Quit");

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.startGame();
            }
        });

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
