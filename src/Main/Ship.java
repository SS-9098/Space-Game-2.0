package Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Ship extends JPanel implements KeyListener
{
	JFrame frame;

	JPanel beam=new Beam();
	JLabel Score= new JLabel("00.00");
	
	Image ship;
	Image nul=new ImageIcon().getImage();
	
	int Ship_x,Ship_y=0;
	int Beam_x, Beam_y;
	int stop;
	double time=0;

	
	Timer timer;
	
	public void Initialize()
	{
		ship = new ImageIcon("spaceship.png").getImage();
		frame=new Frame();
		frame.addKeyListener(this);
		
		this.setBounds(0, 500, 500, 100);
		this.setOpaque(false);
		Score.setBounds(0,0,100,100);
		Score.setFont(new Font("",Font.PLAIN,30));
		Score.setForeground(Color.red);
		
		frame.add(Score);
		frame.add(this);
		frame.add(new Enemy());
		frame.add(beam);
	    
		
		frame.setVisible(true);
	}
	
	
	
	public void paint(Graphics g)
	{
		Graphics2D G=(Graphics2D) g;
		super.paint(G);
		
		G.drawImage(ship, Ship_x, Ship_y, 70, 60, null);
		
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		
		switch(e.getKeyCode())
		{
			case 65:
				if(Ship_x>0)
					Ship_x-=8;
				repaint();
				break;
			case 68:
				if(Ship_x<=500-86)
					Ship_x+=8;
				repaint();
				break;
			case 32:
				timer.stop();
				frame.add(new Beam());
		}
			
	}
	
	public class Beam extends JPanel implements ActionListener

	{
		public Beam() 
		{
			timer= new Timer(1,this);
			
			this.setBounds(0, 100, 500, 500);
			this.setBackground(Color.BLUE);
			this.setOpaque(false);
			
			Beam_x=Ship_x+35;
			Beam_y=500;
			
			timer.start();
		}
		
		
		public void paint(Graphics g)
		{
			Graphics2D G = (Graphics2D) g;
			
			super.paint(G);
			
			G.setPaint(Color.yellow);
			G.setStroke(new BasicStroke(3));
			G.drawLine(Beam_x, Beam_y, Beam_x, Beam_y+30);
			
		}


		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource()==timer)
				Beam_y=Beam_y-15;
			repaint();
			
		}
		
		

	}
	public class Enemy extends JPanel implements ActionListener
	{
		
		Image[] enemy;
		int[] x;
		int[] y;
		int[] xVelo;
		int[] yVelo;
		int EnemyNo = 10;
		int panel_width = 500, panel_height = 400;
		
		Timer move,  direction;
		Ship obj = new Ship();
		
		public Enemy() 
		{
			enemy = new Image[EnemyNo];
			x = new int[EnemyNo];
			y = new int[EnemyNo];
			xVelo = new int[EnemyNo];
			yVelo = new int[EnemyNo];
			
			for(int i=0 ; i<EnemyNo ; i++)
			{
				enemy[i] = new ImageIcon("enemy.png").getImage();
				x[i] = 50;
				y[i] = 50;
				xVelo[i]=1;
				yVelo[i]=1;
			}
			
			this.setBounds(0, 100, panel_width, panel_height);
			this.setOpaque(false);
			
			move = new Timer(10,this);
			direction = new Timer(500,this);
			
			move.start();
			direction.start();
		}

		public void paint(Graphics g)
		{
			Graphics2D G = (Graphics2D) g;
			super.paint(G);
			
			for(int i=0 ; i< EnemyNo ; i++)
			{
				G.drawImage(enemy[i], x[i], y[i], 45, 45, null);
			}
		}
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			for(int i=0 ; i<EnemyNo ; i++)
			{
				if(e.getSource()==direction) 
				{
					xVelo[i]=(int) (Math.random()*(4+4)-4);
					yVelo[i]=(int) (Math.random()*(3+3)-3);
				}
			
					if(x[i]>=panel_width-50)
						xVelo[i]=(int) (Math.random()*(0-7));
					if(x[i]<0)
						xVelo[i]=(int) (Math.random()*(7-0)+0);
					if(y[i]>=panel_height-50)
						yVelo[i]=(int) (Math.random()*(0-5)+0);
					if(y[i]<0)
						yVelo[i]=(int) (Math.random()*(5-0)+0);
					x[i]+=xVelo[i];
					y[i]+=yVelo[i];
					repaint();
			}
			Collision();
			time=time+0.02;
			Score.setText(String.valueOf(time));
			if(stop>=EnemyNo)
				{
					move.stop();
					direction.stop();
					timer.stop();
				}
			
		}
		
		
		public void Collision()
		{
			for(int i=0 ; i<EnemyNo ; i++)
			{
				if(new Rectangle(x[i], y[i], 45, 45).intersects(new Rectangle(Beam_x, Beam_y, 3, 30)))
				{
					if(enemy[i]!=nul)
						stop++;
					enemy[i]=nul;
					//timer.stop();
					
					
				}
			}
		}
		

	}
	
	
	
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}




}
