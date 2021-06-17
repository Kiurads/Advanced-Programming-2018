package model.data.cards;

import model.data.cards.actions.card6Action1;
import model.data.cards.actions.card6Action2;
import model.data.cards.actions.card6Action3;
import model.data.cards.actions.cardAction;

public class Card6 extends Card {
    public Card6() {
        super(new int[]{3, 3, 3}, new cardAction[]{new card6Action1(), new card6Action2(), new card6Action3()});
    }

    @Override
    public String toString() {
        return CARD6;
    }
}
