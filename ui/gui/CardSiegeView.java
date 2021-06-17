package ui.gui;

import model.Game;
import model.ObservableGame;
import model.data.files.FileUtility;
import model.states.AwaitBeginning;
import model.states.EndDay;
import model.states.GameOver;
import model.states.WinState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class CardSiegeView extends JFrame implements Observer {
    ObservableGame game;
    StartPanel startPanel;
    GameStatsContainer statsContainer;
    JMenuBar menuBar;

    public CardSiegeView(ObservableGame game) {
        super("9 Card Siege");

        this.game = game;
        game.addObserver(this);
        startPanel = new StartPanel(game);
        statsContainer = new GameStatsContainer(game);

        addComponents();
        setVisible(true);
        menus();
        setSize(882, 650);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        update(game, null);
        validate();
    }

    private void menus() {
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // game menu
        JMenu gameMenu = new JMenu("Game");
        gameMenu.setMnemonic(KeyEvent.VK_G);

        JMenuItem newObjJMI = new JMenuItem("Stop");
        newObjJMI.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));

        JMenuItem saveObjJMI = new JMenuItem("Save");
        saveObjJMI.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

        JMenuItem loadObjJMI = new JMenuItem("Load");
        loadObjJMI.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));

        JMenuItem exitJMI = new JMenuItem("Exit");
        exitJMI.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

        gameMenu.add(newObjJMI);
        gameMenu.add(saveObjJMI);
        gameMenu.add(loadObjJMI);
        gameMenu.addSeparator();
        gameMenu.add(exitJMI);
        menuBar.add(gameMenu);

        newObjJMI.addActionListener(new NewObjMenuBarListener());
        saveObjJMI.addActionListener(new SaveObjMenuBarListener());
        loadObjJMI.addActionListener(new LoadObjMenuBarListener());
        exitJMI.addActionListener(new ExitListener());
    }

    private void addComponents() {
        Container cp = getContentPane();

        cp.setLayout(new BorderLayout());
        cp.add(startPanel, BorderLayout.SOUTH);
        cp.add(statsContainer, BorderLayout.CENTER);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(game.getState() instanceof AwaitBeginning) {
            setSize(882, 650);
        }
        else {
            setSize(1500, 650);
        }

        if(game.getState() instanceof EndDay) {
            JOptionPane.showMessageDialog(null, "The day has ended");
            game.endDay();
        }

        if(game.getState() instanceof GameOver) {
            JOptionPane.showMessageDialog(null, "GAME OVER!");
            game.quit();
        }

        if(game.getState() instanceof WinState) {
            JOptionPane.showMessageDialog(null, "YOU WIN!");
            game.quit();
        }
    }

    class SaveObjMenuBarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fc = new JFileChooser("./data");
            int returnVal = fc.showSaveDialog(CardSiegeView.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                try{
                    FileUtility.SaveGameGUI(file,game.getGameModel());
                }catch(IOException ex){
                    JOptionPane.showMessageDialog(CardSiegeView.this, "Operação falhada: \r\n\r\n" + e);
                }
            } else {
                System.out.println("Operação cancelada");
            }
        }
    }

    class LoadObjMenuBarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fc = new JFileChooser("./data");
            int returnVal = fc.showOpenDialog(CardSiegeView.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                try{
                    game.setGameModel((Game)FileUtility.LoadGameGUI(file));
                    //modelo.setGameModel((Jogo)FicheiroJogo.LoadGame("Guardar"));
                }catch(IOException | ClassNotFoundException ex){
                    JOptionPane.showMessageDialog(CardSiegeView.this, "Operação falhada: \r\n\r\n" + e);
                }

            } else {
                System.out.println("Operation canceled ");
            }
        }
    }

    class NewObjMenuBarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            game.quit();
        }
    }

    class ExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
