package model.data.cards;

import model.data.cards.actions.card1Action2;
import model.data.cards.actions.card2Action1;
import model.data.cards.actions.card2Action3;
import model.data.cards.actions.cardAction;

public class Card2 extends Card {
    public Card2() {
        super(new int[]{2, 2, 1}, new cardAction[]{new card2Action1(), new card1Action2(), new card2Action3()});
    }

    @Override
    public String toString() {
        return CARD2;
    }
}
