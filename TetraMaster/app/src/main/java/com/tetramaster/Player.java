package com.tetramaster;
import java.util.ArrayList;


public class Player {

	private ArrayList<Card> hand;
	private String name;
	private StandardDeck deck;
	
	public Player()
	{
		hand = new ArrayList<Card>();
		name = "Player";
		deck = new StandardDeck();
	}
	
	public void draw()
	{
		hand.add(deck.draw());
	}
	public void use(int i)
	{
		hand.remove(i);
	}
	
	public ArrayList<Card> getHand()
	{
		return this.hand;
	}
	public int getHandSize()
	{
		return this.hand.size();
	}
	public StandardDeck getDeck()
	{
		return this.deck;
	}
	public void setDeck(ArrayList<Card> deck) { this.deck.setDeck(deck);}
	public String getName()
	{
		return this.name;
	}
	
	
}
