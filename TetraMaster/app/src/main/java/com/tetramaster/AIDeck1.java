package com.tetramaster;

import java.util.ArrayList;
import java.util.Collections;


public class AIDeck1 
{
private ArrayList<CardAI> deck;
	
	public AIDeck1()
	{
		deck = new ArrayList<CardAI>();
		deck.add(new CardAI(0,1,2,3));
		deck.add(new CardAI(3,2,1,0));
		deck.add(new CardAI(3,2,1,0));
		deck.add(new CardAI(3,2,1,0));
		deck.add(new CardAI(0,1,2,3));
		deck.add(new CardAI(0,1,2,3));
		deck.add(new CardAI(0,1,2,3));
		deck.add(new CardAI(0,1,2,3));
		deck.add(new CardAI(0,1,2,3));
		deck.add(new CardAI(0,1,2,3));
	}
	
	public void shuffle()
	{
		Collections.shuffle(deck);
	}
	public int getSize()
	{
		return this.deck.size();
	}
	
	public CardAI draw()
	{
		CardAI c;
		c = deck.get(0);
		deck.remove(0);
		System.out.println(c+" was drawn");
		return c;
	}
	
	public ArrayList<CardAI> getDeck()
	{
		return this.deck;
	}
}
