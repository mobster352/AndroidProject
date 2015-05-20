package com.tetramaster;

public class Card {

	private int left;
	private int top;
	private int down;
	private int right;
	private String name;
	private int loc;              //spot on board
	private boolean flipped;
	
	public Card()
	{
		this.left = 0;
		this.top = 0;
		this.right = 0;
		this.down = 0;
		this.name = "void";
		this.loc = 0;
		this.flipped = false;
	}
	public Card(int l, int t, int r, int d)
	{
		this.left = l;
		this.top = t;
		this.right = r;
		this.down = d;
		this.name = "c"+l+t+r+d;
		this.loc = 0;
		this.flipped = false;
	}
	public boolean getFlipped()
	{
		return this.flipped;
	}
	public void setFlipped(boolean f)
	{
		this.flipped = f;
	}
	public String getName()
	{
		return this.name;
	}
	public int getLoc()
	{
		return this.loc;
	}
	public void setLoc(int loc)
	{
		this.loc = loc;
	}
	public int getLeft()
	{
		return this.left;
	}
	public int getTop()
	{
		return this.top;
	}
	public int getRight()
	{
		return this.right;
	}
	public int getDown()
	{
		return this.down;
	}
	public void setLeft(int l)
	{
		this.left = l;
	}
	public void setTop(int t)
	{
		this.left = t;
	}
	public void setRight(int r)
	{
		this.left = r;
	}
	public void setDown(int d)
	{
		this.left = d;
	}
	public String toString()
	{
        if(this.name.equals("void"))
            return "void card";
        else
            return this.name;
	}
}
