package com.pong.window;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window {
	
	public Window(int w, int h, Game game){
	game.setPreferredSize(new Dimension(w,h));
	game.setMaximumSize(new Dimension(w,h));
	game.setMinimumSize(new Dimension(w,h));
	
	JFrame frame = new JFrame("Pong");
	frame.add(game);
	frame.pack();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setResizable(false);
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);

	game.start();
	
	}
	
//	public Window(int w, int h, Game game){
//
//	JFrame frame = new JFrame("Pong");
//	
//	frame.setPreferredSize(new Dimension(w,h+200));
//	frame.setMaximumSize(new Dimension(w,h+200));
//	frame.setMinimumSize(new Dimension(w,h+200));
//	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	frame.setResizable(false);
//	frame.setLocationRelativeTo(null);
//	frame.setVisible(true);
//	
//	//JPanel info = new JPanel();
//	JPanel gameArea = new JPanel();
//	frame.add(gameArea);
//	gameArea.add(game);
//	frame.pack();
//
//
//	game.start();
//	
//	}

}
