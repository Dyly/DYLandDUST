package com.pong.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.pong.objects.Paddle;

public class KeyInput extends KeyAdapter{

	long lastPress;
	
	Handler handler;
	
	public KeyInput(Handler handler){
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode(); //stores key code
		
		for(int i = 0; i < handler.object.size(); i++){
			
			GameObject tmpObj = handler.object.get(i);
			
			if(tmpObj.getId() == ObjectID.Paddle){
				Paddle tempPaddle = (Paddle) tmpObj;
//				if(tempPaddle.getOwner() == Player.One){
					if(key == KeyEvent.VK_UP) tmpObj.setyVel((float) -5.0); //move paddle up
					if(key == KeyEvent.VK_DOWN) tmpObj.setyVel((float) 5.0); //move paddle down
//				}
			}
			
			if(key == KeyEvent.VK_ESCAPE){
				System.exit(1);
			}
			
			lastPress = System.currentTimeMillis();
			
		}
	}
	
	public void keyReleased(KeyEvent e){
		
		long delta = System.currentTimeMillis() - lastPress;
		
		System.out.println("Delta: " + delta);
		
		if(delta > 2){
				
			int key = e.getKeyCode(); //stores key code
			
			for(int i = 0; i < handler.object.size(); i++){
				
				GameObject tmpObj = handler.object.get(i);
				
				if(tmpObj.getId() == ObjectID.Paddle){
					if(key == KeyEvent.VK_UP) tmpObj.setyVel((float) 0); //move paddle up
					if(key == KeyEvent.VK_DOWN) tmpObj.setyVel((float) 0); //move paddle down
				}
				
				if(key == KeyEvent.VK_ESCAPE){
					System.exit(1);
				}
				
			}
		}
		
		delta = 0;
	}
}
