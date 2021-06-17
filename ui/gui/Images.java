package ui.gui;

import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class Images implements ConstantesGUI{

    private static Map<String, Image> images = new HashMap<String, Image>();
  
    static {
        try {
            images.put(SPLASH_SCREEN, ImageIO.read(Resources.getResourceFile(PATH_IMG_9_CARD_SIEGE)));
            images.put(START_ICON, ImageIO.read(Resources.getResourceFile(PATH_IMG_START_ICON)));
            images.put(LOAD_ICON, ImageIO.read(Resources.getResourceFile(PATH_IMG_LOAD_ICON)));
            images.put(STOP_ICON, ImageIO.read(Resources.getResourceFile(PATH_IMG_STOP_ICON)));
            images.put(SUN_ICON, ImageIO.read(Resources.getResourceFile(PATH_IMG_SUN_ICON)));
            images.put(COIN_ICON, ImageIO.read(Resources.getResourceFile(PATH_IMG_COIN_ICON)));
            images.put(MORALE_ICON, ImageIO.read(Resources.getResourceFile(PATH_IMG_MORALE_ICON)));
            images.put(SUPPLIES_ICON, ImageIO.read(Resources.getResourceFile(PATH_IMG_SUPPLIES_ICON)));
            images.put(WALL_ICON, ImageIO.read(Resources.getResourceFile(PATH_IMG_WALL_ICON)));
            images.put(SHIELD_ICON, ImageIO.read(Resources.getResourceFile(PATH_IMG_SHIELD_ICON)));
            images.put(CARD0, ImageIO.read(Resources.getResourceFile(PATH_IMG_CARD0)));
            images.put(CARD1, ImageIO.read(Resources.getResourceFile(PATH_IMG_CARD1)));
            images.put(CARD2, ImageIO.read(Resources.getResourceFile(PATH_IMG_CARD2)));
            images.put(CARD3, ImageIO.read(Resources.getResourceFile(PATH_IMG_CARD3)));
            images.put(CARD4, ImageIO.read(Resources.getResourceFile(PATH_IMG_CARD4)));
            images.put(CARD5, ImageIO.read(Resources.getResourceFile(PATH_IMG_CARD5)));
            images.put(CARD6, ImageIO.read(Resources.getResourceFile(PATH_IMG_CARD6)));
            images.put(CARD7, ImageIO.read(Resources.getResourceFile(PATH_IMG_CARD7)));
            images.put(CARD_ENEMY, ImageIO.read(Resources.getResourceFile(PATH_IMG_CARD_ENEMY)));
            images.put(CARD_STATUS, ImageIO.read(Resources.getResourceFile(PATH_IMG_CARD_STATUS)));
            images.put(DICE1, ImageIO.read(Resources.getResourceFile(PATH_IMG_DICE1)));
            images.put(DICE2, ImageIO.read(Resources.getResourceFile(PATH_IMG_DICE2)));
            images.put(DICE3, ImageIO.read(Resources.getResourceFile(PATH_IMG_DICE3)));
            images.put(DICE4, ImageIO.read(Resources.getResourceFile(PATH_IMG_DICE4)));
            images.put(DICE5, ImageIO.read(Resources.getResourceFile(PATH_IMG_DICE5)));
            images.put(DICE6, ImageIO.read(Resources.getResourceFile(PATH_IMG_DICE6)));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    
    public static Image getImage(String nomeImagem) {
        return images.get(nomeImagem);
     }
    public static Map<String, Image> getImages() {
        return images;
    }
}
