package com.pong.window;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.pong.framework.Handler;
import com.pong.framework.KeyInput;
import com.pong.framework.ObjectID;
import com.pong.framework.Player;
import com.pong.objects.Ball;
import com.pong.objects.Paddle;
import com.pong.objects.PaddleBlock;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = -3386671561033610107L;
	public static final int HEIGHT = 600;
	public static final int WIDTH = 1200;
	private boolean running = false;
	private Thread thread;
	Handler handler;
		
	// initialize method: load in assets, set BufferedImageLoader, etc before looping begins
	private void init(){
		
		handler = new Handler();
		handler.addObject(new Paddle(0, Game.HEIGHT/4, handler, ObjectID.Paddle, Player.One));
		handler.addObject(new Paddle(WIDTH-PaddleBlock.getSize()+9, Game.HEIGHT/4, handler, ObjectID.Paddle, Player.Two));
		handler.newBall();
		//handler.createWorld();
		
		this.addKeyListener(new KeyInput(handler));
		
	}
	
	public synchronized void start(){
		if(running) return; // fail safe in case start method somehow gets called during runtime
		
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}

	@Override
	//Main Game Loop
	public void run() {
		
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			} // exit while(delta >=1) loop
			
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
			
		} // exit while(running) loop
	}
	
	private void tick(){
		handler.tick();
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		//DRAW AFTER THIS LINE
		
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight()); // Fills canvas of Game with black background
		g.setColor(Color.white);
		g.drawRect(0, 0, getWidth(), getHeight());
		handler.render(g);
		
		g.dispose();
		bs.show();
		
	}
	public static void main(String args[]){
		new Window((int)WIDTH,(int)HEIGHT,new Game());
	}

}
