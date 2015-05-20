package com.tetramaster;
import java.util.ArrayList;
import java.util.Collections;


public class StandardDeck {
	
	private ArrayList<Card> deck;
	
	public StandardDeck() {
		deck = new ArrayList<Card>();
		deck.add(new Card(0, 1, 2, 3));
		deck.add(new Card(3, 2, 1, 0));
		deck.add(new Card(2, 2, 2, 2));
		deck.add(new Card(3, 2, 1, 0));
		deck.add(new Card(0, 1, 2, 3));
		deck.add(new Card(2, 2, 2, 2));
		deck.add(new Card(2, 2, 2, 2));
		deck.add(new Card(2, 2, 2, 2));
		deck.add(new Card(2, 2, 2, 2));
		deck.add(new Card(2, 2, 2, 2));
	}

	public StandardDeck(ArrayList<Card> aList)
	{
		deck = aList;
	}
	
	public void shuffle()
	{
		Collections.shuffle(deck);
	}
	public int getSize()
	{
		return this.deck.size();
	}
	
	public Card draw()
	{
		Card c;
		c = deck.get(0);
		deck.remove(0);
		System.out.println(c+" was drawn");
		return c;
	}
	
	public ArrayList<Card> getDeck()
	{
		return this.deck;
	}
	
	public void setDeck(ArrayList<Card> deck) {this.deck = deck;}

	public void setEmptyDeck() {this.deck = new ArrayList<Card>();}

}
