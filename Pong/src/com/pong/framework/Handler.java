package com.pong.framework;
import java.awt.Graphics;
import java.util.LinkedList;

import com.pong.objects.Ball;
import com.pong.objects.Paddle;
import com.pong.window.Game;

//Game Object Handler
public class Handler {

	public LinkedList<GameObject> object = new LinkedList<>();
	private GameObject tmpObj;
	
	public void addObject(GameObject object){
		this.object.add(object);
	}
	
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
	
	public void tick(){
		for(int i = 0; i < object.size(); i++){
			tmpObj = object.get(i);
			//System.out.println("Ticking: " + object.get(i));
			tmpObj.tick(object);
		}
	}
	
	public void render(Graphics g){
		for(int i =0;i <object.size(); i++){
			tmpObj = object.get(i);
			tmpObj.render(g);
		}
	}
	
	public void newBall(){
		addObject(new Ball(Game.WIDTH/2,Game.HEIGHT/4,10, 10, this, ObjectID.Ball));
	}
	
//	public void createWorld(){
//		this.addObject(new Boundary(0,0,ObjectID.Boundary, Border.Top));
//		this.addObject(new Boundary(0,Game.HEIGHT-Boundary.getHeight()+9,ObjectID.Boundary, Border.Bottom));
//	}
	
}