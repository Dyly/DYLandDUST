package com.pong.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.pong.framework.Border;
import com.pong.framework.GameObject;
import com.pong.framework.ObjectID;
import com.pong.window.Game;

public class Boundary extends GameObject{

	private static final int HEIGHT = 16;
	private static final int WIDTH = Game.WIDTH+9;
	private Border border;
	
	public Boundary(float x, float y, ObjectID id, Border border) {
		super(x, y, id);
		this.setBorder(border);
	}

	public static int getHeight() {
		return HEIGHT;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect((int)x, (int)y, WIDTH, HEIGHT);
		
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.LIGHT_GRAY);
		g2d.draw((Rectangle)getBounds());
		
	}

	@Override
	public Object getBounds() {
		return new Rectangle((int) x, (int) y, WIDTH, HEIGHT);
	}

	public Border getBorder() {
		return border;
	}

	public void setBorder(Border border) {
		this.border = border;
	}

}
