package model.data.cards;

import model.data.cards.actions.card4Action1;
import model.data.cards.actions.card4Action2;
import model.data.cards.actions.card4Action3;
import model.data.cards.actions.cardAction;

public class Card4 extends Card {
    public Card4() {
        super(new int[]{2, 2, 3}, new cardAction[]{new card4Action1(), new card4Action2(), new card4Action3()});
    }

    @Override
    public String toString() {
        return CARD4;
    }
}
