package com.pong.objects;

import java.awt.Graphics;
import java.util.LinkedList;
import com.pong.framework.GameObject;
import com.pong.framework.Handler;
import com.pong.framework.ObjectID;
import com.pong.framework.Player;
import com.pong.window.Game;

public class Paddle extends GameObject{

	private static final int size = 150;
	private static final int cols = 5;
	private static final int rows = (int) size/cols;
	private boolean alive = true;
	@SuppressWarnings("unused")
	private Handler handler;
	private LinkedList<GameObject> block;
	private GameObject tempObj;
	private Player owner;
	private int score = 0;
	
	public Paddle(float x, float y,Handler handler, ObjectID id, Player player){
		super(x,y,id);
		
		block = new LinkedList<>();
		int i = 0;
		if(player == Player.One){
		for(int j = 0; j < cols; j++){
			for(int z= 0; z < rows; z++){
				block.add(new PaddleBlock(x+(PaddleBlock.getSize()*j),y+(PaddleBlock.getSize()*z),ObjectID.PaddleBlock, i, player));
				i++;
				
				}
			}
		}else{
			
			int playerTwoAdjustX = Game.WIDTH-(cols*PaddleBlock.getSize());

				for(int j = 0; j < cols; j++){
					for(int z= 0; z < rows; z++){
						block.add(new PaddleBlock(playerTwoAdjustX+(PaddleBlock.getSize()*j),y+(PaddleBlock.getSize()*z),ObjectID.PaddleBlock, i, player));
						i++;
					}
				}
		}
		
		this.setOwner(player);
		this.handler = handler;
	}

	@Override
	public void setyVel(float yVel){
		super.setyVel(yVel);
		
		for(int i = 0; i < block.size(); i++){
			tempObj = block.get(i);
			tempObj.setyVel(yVel);
		}
				
	}
	
	public LinkedList<GameObject> getBlock() {
		return block;
	}

	public void setBlock(LinkedList<GameObject> block) {
		this.block = block;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {

		for(int i = 0; i < block.size(); i++){
			tempObj = block.get(i);
			tempObj.tick(block);
		}
		
	}

	@Override
	public void render(Graphics g) {
		
		for(int i = 0; i < block.size(); i++){
				tempObj = block.get(i);
				tempObj.render(g);
		}
		
	}

	@Override
	public Object getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public Player getOwner() {
		return this.owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void resetBlocks(){
		for(GameObject b : block){
			PaddleBlock pb = (PaddleBlock) b;
			pb.setHealth(PaddleBlock.getMaxhealth());
			pb.setAlive(true);
		}
	}
	
}

	
	