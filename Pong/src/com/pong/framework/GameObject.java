package com.pong.framework;

import java.awt.Graphics;
import java.util.LinkedList;

import com.pong.window.Game;

public abstract class GameObject {

	protected float x, y;
	protected float yVel = 0, xVel = 0;
	protected ObjectID id;
	
	public GameObject(float x, float y, ObjectID id){
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick(LinkedList<GameObject> object);
	public abstract void render(Graphics g);
	public abstract Object getBounds(); // public abstract Rectangle getBounds() ?
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getyVel() {
		return yVel;
	}

	public void setyVel(float yVel) {
		this.yVel = yVel;
	}

	public float getxVel() {
		return xVel;
	}

	public void setxVel(float xVel) {
		this.xVel = xVel;
	}

	public ObjectID getId() {
		return id;
	}

	public void setId(ObjectID id) {
		this.id = id;
	}

}
