package model.data.cards;

import model.data.cards.actions.card7Action1;
import model.data.cards.actions.card7Action2;
import model.data.cards.actions.card7Action3;
import model.data.cards.actions.cardAction;

public class Card7 extends Card {
    public Card7() {
        super(new int[]{3, 3, 3}, new cardAction[]{new card7Action1(), new card7Action2(), new card7Action3()});
    }

    @Override
    public String toString() {
        return CARD7;
    }
}
