package com.tetramaster;

import java.util.ArrayList;

public class Trunk {
    private ArrayList<Card> deck;

    public Trunk()
    {
        deck = new ArrayList<Card>();
    }

    public ArrayList<Card> getDeck()
    {
        return this.deck;
    }

    public void add(Card c)
    {
        deck.add(c);
    }

    public void remove(int index)
    {
        deck.remove(index);
    }

    public void remove(Card c)
    {
        deck.remove(c);
    }

    public int getSize()
    {
        return this.deck.size();
    }
}
