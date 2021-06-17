package model.data.cards;

import model.data.cards.actions.card1Action1;
import model.data.cards.actions.card1Action2;
import model.data.cards.actions.card1Action3;
import model.data.cards.actions.cardAction;

public class Card1 extends Card{
    public Card1() {
        super(new int[]{3, 2, 1}, new cardAction[]{new card1Action1(), new card1Action2(), new card1Action3()});
    }

    @Override
    public String toString() {
        return CARD1;
    }
}
