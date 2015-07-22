package com.pong.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

import com.pong.framework.GameObject;
import com.pong.framework.Handler;
import com.pong.framework.ObjectID;
import com.pong.framework.Player;
import com.pong.window.Game;

import javafx.scene.shape.Circle;

public class Ball extends GameObject{

	private int HEIGHT = 20;
	private int WIDTH = 20;
	private final float MAX_FALL_SPEED = 20;
	private final float MIN_TRAVERSE_SPEED = 5;
	private final float MAX_TRAVERSE_SPEED = 20;
	private float gravity = 0.5f;
	private boolean falling;
	private Handler handler;
	boolean tc = false;
	
	Random rand = new Random();
	
	public Ball(float x, float y, int width, int height, Handler handler, ObjectID id){
		super(x,y,id);
		this.WIDTH = width;
		this.HEIGHT = height;
		this.handler = handler;
		xVel = (float) ((MIN_TRAVERSE_SPEED + (rand.nextFloat()*2.0))*(float)getRandDirection());
		yVel = (float) (rand.nextFloat()*4.0*(float)getRandDirection());
		
		if(yVel > 0){
			falling = true;
		}else{
			falling = false;
		}
		
	}
	
	private int getRandDirection(){
		int dir = 0;
		dir = rand.nextInt(9);
		
		if(dir > 4){
			dir = -1;
		}else{
			dir = 1;
		}
		
		return dir;
	}
	
	@Override
	public void tick(LinkedList<GameObject> object) {
		
		x += xVel;
		y += yVel;
		
		if(Math.abs(xVel) > MAX_TRAVERSE_SPEED && xVel > 0){
			xVel = MAX_TRAVERSE_SPEED;
		}else if(Math.abs(xVel) > MAX_TRAVERSE_SPEED && xVel < 0){
			xVel = -MAX_TRAVERSE_SPEED;
		}
		
		if( this.y > Game.HEIGHT || this.y < 0){
			yVel = yVel * -1;
		}
		
		Collision(object);
		CheckStatus(object);
	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.yellow);
		g.fillOval((int)x,(int) y, this.WIDTH, this.HEIGHT);
			
	}

	@Override
	public Circle getBounds() {
		return new Circle(x, y, WIDTH/2);
	}
	
	public void Collision(LinkedList<GameObject>object){
		
		GameObject tempObject;
		
		for (int i = 0; i < object.size(); i++) {

			tempObject = handler.object.get(i);
						
			if(tempObject.getId() == ObjectID.Paddle){
				Paddle pad = (Paddle) tempObject;
				//System.out.println("Paddle: " + pad.getOwner());
				LinkedList<GameObject> list;
				list = pad.getBlock();
				
				
				for(int j = 0; j < list.size(); j++){
					
					PaddleBlock currentBlock = (PaddleBlock) list.get(j);
					
					if(currentBlock.isAlive()){
						if(intersectCirc(getBounds(), currentBlock.getBounds())){
							if(pad.getOwner() == Player.One){
								x = PaddleBlock.getSize()+50;
							}else{
								x = Game.WIDTH-PaddleBlock.getSize()-50;
							}
							
							currentBlock.setHealth(currentBlock.getHealth() - 10);
							xVel = xVel * -1;
						}
					}

				}

			}

		}
	}
	
	private boolean inBounds(){
		if(this.x < 0 || this.x > Game.WIDTH){
			return false;
		}else{
			return true;
		}
	}
	
	private void CheckStatus(LinkedList<GameObject>object){
		if(!inBounds()){
			if(this.x > 0){
				updateScore(Player.One,10, object);
			}else if(this.x < Game.WIDTH){
				updateScore(Player.Two,10, object);
			}
			
			handler.removeObject(this);
			handler.newBall();
			for(GameObject o : object){
				if(o.getId()==ObjectID.Paddle){
					Paddle p = (Paddle) o;
					p.resetBlocks();
				}
			}
		}
	}
	
	private void updateScore(Player player, int score, LinkedList<GameObject> object){
		for(GameObject obj : object){
			if(obj.getId() == ObjectID.Paddle){
				Paddle pad = (Paddle)obj;
				if(pad.getOwner() == player){
					pad.setScore(pad.getScore()+10);
					System.out.println("Player " + pad.getOwner() + " score: " + pad.getScore());
					break;
				}
			}
		}
	}
	public boolean intersectCirc(Circle circ, Object sqr){
		
		boolean result = false;
		Rectangle rect = (Rectangle) sqr;
		
		result = circ.getCenterX() > rect.getX() && circ.getCenterX() < rect.getMaxX() && 
				 circ.getCenterY() > rect.getY() && circ.getCenterY() < rect.getMaxY() ? true : false;
				 
		if(result || tc) System.out.println("Circle center: " + circ.getCenterX() + " // Object Left X: " + rect.getX() + " // Object Right X: " + rect.getMaxX() + " = " + result);
		return result;
	}
}
