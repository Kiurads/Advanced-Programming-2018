package model.data.cards;

import model.data.cards.actions.card5Action1;
import model.data.cards.actions.card5Action2;
import model.data.cards.actions.card5Action3;
import model.data.cards.actions.cardAction;

public class Card5 extends Card {
    public Card5() {
        super(new int[]{3, 2, 2}, new cardAction[]{new card5Action1(), new card5Action2(), new card5Action3()});
    }

    @Override
    public String toString() {
        return CARD5;
    }
}
