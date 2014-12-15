import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Interface extends JPanel implements MouseListener, MouseMotionListener{

	static int mouseX = 5,mouseY = 5, newMouseX = 5,newMouseY = 5;
	int sizeOfSquare = 50;
	Graphics2D g2;
	boolean pressed = true;
	String movement = "";
	
	public void paintComponent(Graphics g)
	{
		/*
		 * They are painted in order, next draw/paint overlaps the previous one.
		 */
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		this.setBackground(Color.black);
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		Image chPieces;
		chPieces = new ImageIcon("C:/Users/eolochr/Documents/own/code/Ch/ChessPieces.png").getImage();
		for(int x=0; x<8; x++)
		{
			for(int y=0; y<8;y++)
			{
				if( (x%2==0 && y%2==0) || (x%2==1 && y%2==1))
				{
					g.setColor(Color.white);
				}
				else if((x%2==0 && y%2==1) || (x%2==1 && y%2==0))
				{
					g.setColor(Color.gray);
				}
				else
				{}
				g.fillRect(sizeOfSquare*x, sizeOfSquare*y, sizeOfSquare, sizeOfSquare);
				
				g.drawString(y + "", 420, 24 + (sizeOfSquare * y));
				g.drawString(x + "",  24 + (sizeOfSquare * x), 420 );
			}
			
		}
		
		for(int i=0; i<64; i++) //taken, not my logic
		{
			int j=-1, k=-1;
			switch(Movements.chessBoard[i/8][i%8])
			{
		
			case "R": 
				j = 2; k = 0;
				break;
			case "P": 
				j = 5; k = 0;
				break;
			case "K": 
				j = 4; k = 0;
				break;
			case "B": 
				j = 3; k = 0;
				break;
			case "Q": 
				j = 1; k = 0;
				break;	
			case "A":
				j = 0; k = 0;
				break;
				
			case "p": 
				j = 5; k = 1;
				break;
			case "r": 
				j = 2; k = 1;
				break;
			case "k": 
				j = 4; k = 1;
				break;
			case "b": 
				j = 3; k = 1;
				break;
			case "q": 
				j = 1; k = 1;
				break;	
			case "a":
				j = 0; k = 1;
				break;
				
			}
			if(j != -1 && k != -1)
			{
				 g.drawImage(chPieces, (i%8)*sizeOfSquare, (i/8)*sizeOfSquare, (i%8+1)*sizeOfSquare, (i/8+1)*sizeOfSquare, j*64, k*64, (j+1)*64, (k+1)*64, this);
			}
		}
		g.setColor(Color.GREEN);
		
		if(Movements.turnC)
		{
			g.drawString("White turn", 150,440);
		}
		else
		{
			g.drawString("Black turn", 150,450);
		}
		
		g.drawString(movement, 150,460);
		if(Movements.checkC)
			g.drawString("CHECK white", 150, 420);
		
	}


	@Override
	public void mouseDragged(MouseEvent arg0) {
			
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if( (e.getX() < (8*sizeOfSquare) &&  e.getY() < (8*sizeOfSquare)) && (!pressed)) //inside board
		{
	
			if(e.getButton() == MouseEvent.BUTTON1)
			{
				mouseX = e.getX()/sizeOfSquare;
				mouseY = e.getY()/sizeOfSquare;
				pressed = true;
			}
		}	
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		
		if( e.getX() < (8*sizeOfSquare) &&  e.getY() < (8*sizeOfSquare) &&  (pressed)) //inside board
		 {
			 if(e.getButton() == MouseEvent.BUTTON1)
				{
				 	newMouseX = e.getX()/sizeOfSquare;
				 	newMouseY = e.getY()/sizeOfSquare;
				 	movement = ""+ mouseY + mouseX + newMouseY + newMouseX + Movements.chessBoard[newMouseY][newMouseX];
				 	
				 
				 	//System.out.print((newMouseX +" != "+ mouseX)   + " OR " +(mouseY +" != " + newMouseY + "\n"));
				 	
				 	if( (newMouseX != mouseX) || (mouseY != newMouseY))
				 	{
				 		Movements.makeMove(movement);
				 		//System.out.println(""+ mouseY + mouseX + newMouseY + newMouseX + Main.chessBoard[newMouseY][newMouseX]);
				 		Movements.printLetterChessBoard();
				 	}else
				 		System.out.print("\n");
				 		pressed = false;
				}
		 }
		repaint();
	}
}

