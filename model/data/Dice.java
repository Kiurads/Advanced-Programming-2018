package model.data;

import java.util.Random;

public class Dice {
    private int[] positionDRM;
    private int number;

    public void modifyPositionDRM(int index, int add) {
        positionDRM[index] += add;
    }

    public void resetDRM() {
        for (int i = 0; i < positionDRM.length; i++) {
            positionDRM[i] = 0;
        }
    }

    public int getDRM(int index) {
        return positionDRM[index];
    }

    public Dice() {
        positionDRM = new int[]{0, 0, 0, 0, 0};
        number = 6;
    }

    public void roll() {
        number = new Random().nextInt(5) + 1;
    }

    public int getNumber() {
        return number;
    }
}
