package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class gamePanel extends JPanel implements ActionListener,KeyListener{
	int worldX=-1600;
	int playerY=650;
	int jumpY;
	int pvel=0;
	int bullX=355;
	int bvel=0;
	boolean rb,lb;
	boolean bull=false;
	boolean jump=false;
	boolean rg=false,lg=false;
	Timer loop;
	Image back,grass,right,left,still,player,msg;
	gamePanel(){
		this.setPreferredSize(new Dimension(800,800));
		this.setFocusable(true);
		this.addKeyListener(this);
		this.setBackground(Color.black);
		loop =new Timer(1000/60,this);
		right = new ImageIcon(getClass().getResource("/res/right.gif")).getImage();
		left = new ImageIcon(getClass().getResource("/res/left.gif")).getImage();
		still = new ImageIcon(getClass().getResource("/res/still.png")).getImage();
		back = new ImageIcon(getClass().getResource("/res/world.png")).getImage();
		grass = new ImageIcon(getClass().getResource("/res/grass.png")).getImage();
		msg = new ImageIcon(getClass().getResource("/res/message.jpg")).getImage();
		player=still;
		loop.start();
		jumpY=playerY;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	public void draw(Graphics g) {
		
		g.drawImage(back, worldX, 0, 2400,800, null);
		g.drawImage(grass, worldX, 600, 2400, 200,null);
		g.drawImage(msg, worldX+2000, 400, 250, 150, null);
		g.setColor(Color.RED);
		g.drawImage(player,350, playerY, 50, 50,null);
		g.fillRect(bullX, playerY+20, 5, 5);
		g.fillRect(worldX+1500, 600, 100, 200);
		//g.fillRect(350, playerY, 50, 50);
		
	}
	public void jump() {
		
		
		
		    if (jump) {
		        playerY += pvel; // Apply current velocity
		        pvel += 1; // Gravity (falling down)
		        if (playerY >= jumpY) {
		            
		            playerY = jumpY;
		            pvel = 0;  // Stop falling
		            jump = false; // Stop jumping
		        }
		    }
		

		
	}
	public void collision() {
	    
		if (350 + 50 > worldX + 1500 && 350 < worldX + 1600 && playerY + 50 >= 600 && playerY <= 800) {
	          // Stop moving right
	        worldX = -1500;  // Position the world at a fixed value to stop moving
	    }

	   
	    if (350 < worldX + 1500 && 350 + 50 > worldX + 1400 && playerY + 50 >= 600 && playerY <= 800) {
	         
	        worldX = -1400;  
	    }
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		move();
		jump();
		bull();
		collision();
	}
	public void bull() {
		if(rb) {
			if(bullX>=800) {
				bullX=355;
				bvel=0;
				rb=false;
			}
		}
		if(lb) {
			if(bullX<=0) {
				bullX=355;
				bvel=0;
				lb=false;
			}
		}
	}
	public void move() {
		if(rg) {
			worldX+=-10;
		}
		if(lg) {
			worldX+=10;
		}
		if(worldX==0) {
			lg=false;
			player=still;
		}
		if(worldX==-1600) {
			rg=false;
			player=still;
		}
		collision();
	}



	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
    


	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_A&&worldX<0) {
			lg=true;
			player=left;
		}
		if(e.getKeyCode()==KeyEvent.VK_E&&!rb&&!lb) {
			bvel=15;
			rb=true;
		}
        if(e.getKeyCode()==KeyEvent.VK_Q&&!rb&&!lb) {
			bvel=-15;
			lb=true;
		}
		
		if(e.getKeyCode()==KeyEvent.VK_D&&worldX>=-1550) {
			rg=true;
			player=right;
		}
		if(e.getKeyCode()==KeyEvent.VK_SPACE&&playerY==jumpY) {
			jumpY=playerY;
			jump=true;
			pvel=-20;
		}
		
		
	}



	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_A&&worldX<=-5) {
			lg=false;
			player=still;
		}
		if(e.getKeyCode()==KeyEvent.VK_D&&worldX<=5) {
			rg=false;
			player=still;
		}
		
	}

}
