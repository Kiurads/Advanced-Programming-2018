package model.data.cards;

import model.data.cards.actions.card3Action1;
import model.data.cards.actions.card3Action2;
import model.data.cards.actions.card3Action3;
import model.data.cards.actions.cardAction;

public class Card3 extends Card {
    public Card3() {
        super(new int[]{2, 2, 2}, new cardAction[]{new card3Action1(), new card3Action2(), new card3Action3()});
    }

    @Override
    public String toString() {
        return CARD3;
    }
}