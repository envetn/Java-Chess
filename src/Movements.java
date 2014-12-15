import java.util.Arrays;
import java.util.Random;


public class Movements{

	public static String chessBoard[][]=
		    {  // 0   1   2   3   4   5   6   7 col
		        {"r","k","b","q","a","b","k","r"},//0
		        {"p","p","p","p","p","p","p","p"},//1
		        {" "," "," "," "," "," "," "," "},//2
		        {" "," "," "," "," "," "," "," "},//3
		        {" "," "," "," "," "," "," "," "},//4
		        {" "," "," "," "," "," "," "," "},//5
		        {"P","P","P","P","P","P","P","P"},//6
		        {"R","K","B","Q","A","B","K","R"} //7 row
		     };
	static int kingPosC = 42, kingPosL = 03;

	public static boolean turnC = true;
	public static boolean checkC = false;
	public static boolean checkL = false;
	  public String[][] getChessBoard()
	  {

		  return chessBoard;
	  }
	  public void printChessBoard()
	  {
			for (int i=0;i<8;i++)
		  	{
	            System.out.println(Arrays.toString(chessBoard[i]));
	        }
	  }
	  
	  public static void makeMove(String moves)
	    {
		  System.out.println(kingPosC + "<-King");
	    	//if(moves.charAt(4) != 'P') 
	    	//{
	    		if(turnC)
	    		{
		    		if(possibleMovesC().contains(moves))
		    		 {
		    	    	int x1,y1,x2,y2;
		    	    	x1 = Character.getNumericValue(moves.charAt(2));
		    	    	y1 = Character.getNumericValue(moves.charAt(3));
		    	    		
		    	    	x2 =  Character.getNumericValue(moves.charAt(0));
		    	    	y2 =  Character.getNumericValue(moves.charAt(1));
		    	    	if(chessBoard[x2][y2] == "A")
		    	    	{
		    	    		kingPosC = Integer.valueOf(x1 +"" + y1);
		    	    		
		    					
		    	    	}
		    	    	chessBoard[x1][y1] = chessBoard[x2][y2];
		    	    	chessBoard[x2][y2] = " ";
		    	    	turnC = false;
		    		 }
					
	    		}
	    		else if (!turnC)
	    		{
	    			if(possibleMovesL().contains(moves))
	    		  	{
	    	    		int x1,y1,x2,y2;
	    	    		x1 = Character.getNumericValue(moves.charAt(2));
	    	    		y1 = Character.getNumericValue(moves.charAt(3));
	    	    		
	    	    		x2 =  Character.getNumericValue(moves.charAt(0));
	    	    		y2 =  Character.getNumericValue(moves.charAt(1));
	    	    		if(chessBoard[x2][y2] == "A")
	    	    		{
	    	    			kingPosC = Integer.valueOf(x1 +"" + y1);
	    	    			System.out.println(kingPosC + "<-King");
	    					
	    	    		}
	    	    		chessBoard[x1][y1] = chessBoard[x2][y2];
	    	    		chessBoard[x2][y2] = " ";
	    	    		turnC = true;
	    		  	}
					// possibleMovesL());
					/*String posMoves = movement.randomMove(possibleMovesL());
					if (possibleMovesL().contains(posMoves + " ")) 
					{
						int x1, y1, x2, y2;
		
						x1 = Character.getNumericValue(moves.charAt(2));
						y1 = Character.getNumericValue(moves.charAt(3));
		
						x2 = Character.getNumericValue(moves.charAt(0));
						y2 = Character.getNumericValue(moves.charAt(1));
		
						chessBoard[x1][y1] = chessBoard[x2][y2];
						chessBoard[x2][y2] = " ";*/
						
					}
	    }
		
	    public static void undoMove(String moves)
	    {
	    	if (moves.charAt(4)!='P')  //hardcore
	    	{
	    		int x1,y1,x2,y2;
	    		x1 = Character.getNumericValue(moves.charAt(0));
	    		y1 = Character.getNumericValue(moves.charAt(1));
	    		
	    		x2 = Character.getNumericValue(moves.charAt(2));
	    		y2 = Character.getNumericValue(moves.charAt(3));
	            chessBoard[x1][y1] = chessBoard[x2][y2];
	            chessBoard[x2][y2] = String.valueOf(moves.charAt(4));
	        } 
	    }
	    public static String possibleMovesC()
	    {
	        String list="";
	        for (int i=0; i<64; i++) 
	        {
	            switch (chessBoard[i/8][i%8]) // 55 / 8 ~ 6 || 55 % 8 = 7
	            {
	                case "P": list += possibleP(i);//possibleP(i);
	                    break;
	                case "R": list += possibleR(i);
	                    break;
	                case "K": list += possibleK(i);
	                    break;
	                case "B": list += possibleB(i);
	                    break;
	                case "Q": list += possibleQ(i);
	                    break;
	                case "A": list += possibleA(i);
	                    break;
	            }
	        }
	        return list + "\n";
	    }
	    
	    public static String possibleMovesL()
	    {
	        String list="";
	        for (int i=0; i<64; i++) 
	        {
	            switch (chessBoard[i/8][i%8]) // 55 / 8 ~ 6 || 55 % 8 = 7
	            {
	                case "p": list += possible_p(i);//possibleP(i);
	                    break;
	                case "r": list += possible_r(i);
	                    break;
	                case "k": list += possible_k(i);
	                    break;
	                case "b": list += possible_b(i);
	                    break;
	            
	            }
	        }
	        return list + "\n";
	    }
	  /* CAPITAL LETTERS */ 
	    
	    public static String possibleP(int value) 
	    {
	        String list="" ,oldPiece = "";
	        int r = value/8, c=value%8;
	        int tmp = 1;
	        try
	        {
	        	if(" " == chessBoard[r-1][c])
	            { 
	        		if(" " == chessBoard[r-2][c] && r == 6)
	        		{
		           		oldPiece = chessBoard[r-2][c];
		           		chessBoard[r][c] = "";
		           		chessBoard[r-2][c] = "P";
		           		if(kingSafe())
		           		{
		           			list = list + (r) + c +  (r-2) + (c) + oldPiece;
		           		}
		           		chessBoard[r][c] = "P";
		           		chessBoard[r-2][c] = oldPiece;
	        		}
	            	oldPiece = chessBoard[r-1][c];
	            	if(kingSafe())
	            	{
	            		list = list + (r) +""+ c +  "" + (r-1) + (c) + oldPiece;
	            	}
	            }
	        }
	        catch(Exception e){	}
	        
	        for(int i=-1; i<=1; i+=2) // either 1 or -1 
	        {
	        	try
	        	{
	        		if(Character.isLowerCase(chessBoard[r - 1][c + 1 * i].charAt(0)))
	        		{
	        			oldPiece = chessBoard[r - 1][c + 1 * i];
	    				list += (r) + "" + c + "" + (r - 1) + (c + 1 * i) + oldPiece;
	        		}
	        	}
	        	catch(Exception e){}
	        }
	        return list;
	    }
	    public static String possibleR(int value) 
	    {
	    	String list = "", oldPiece;
			int r = value / 8, c = value % 8;
			int tmp = 1;
			for (int i = -1; i <= 1; i++) 
			{
				try 
				{
					while(" ".equals(chessBoard[r+tmp*i][c]))//row
					{
						oldPiece = chessBoard[r+tmp*i][c];
						chessBoard[r][c] = " ";
						chessBoard[r+tmp*i][c] = "R";
						if(kingSafe())
							{
								list = list + r+c+ "" + (r+tmp*i) + (c) + oldPiece;	
							}
						chessBoard[r][c] = "R";
						chessBoard[r+tmp*i][c] = oldPiece;
						tmp ++;
					}
					if(Character.isLowerCase(chessBoard[r+tmp*i][c].charAt(0))) //
					{
						oldPiece = chessBoard[r+tmp*i][c];
						chessBoard[r][c] = " ";
						chessBoard[r+tmp*i][c] = "R";
						if(kingSafe())
							{
								list = list + r+c+ "" + (r+tmp*i) + (c) + oldPiece;	
							}
						chessBoard[r][c] = "R";
						chessBoard[r+tmp*i][c] = oldPiece;
					}
				} catch (Exception e) {}
				tmp = 1;
				try
				{
					while(" ".equals(chessBoard[r][c+tmp*i]))//col
					{
						oldPiece = chessBoard[r][c+tmp*i]; ///////
						chessBoard[r][c] = " ";
						chessBoard[r][c+tmp*i] = "R";
						if(kingSafe())
							{
								list = list + r+c+ "" + (r) + (c+tmp*i) + oldPiece;	
							}
						chessBoard[r][c] = "R";
						chessBoard[r][c+tmp*i] = oldPiece;
						tmp ++;
					}
					if(Character.isLowerCase(chessBoard[r][c+tmp*i].charAt(0))) //
					{
						oldPiece = chessBoard[r][c+tmp*i];
						chessBoard[r][c] = " ";
						chessBoard[r][c+tmp*i] = "R";
						if(kingSafe())
							{
								list = list + r+c+  (r) + (c+tmp*i) + oldPiece;	
							}
						chessBoard[r][c] = "R";
						chessBoard[r][c+tmp*i] = oldPiece;
					}
					
				}catch(Exception e){}
			}
	        return list;
	    }
	    public static String possibleK(int value)
	    {
	    	String list = "", oldPiece = "";
			int r = value / 8, c = value % 8;
			int tmp = 1;
			for(int i=-2; i <= 2; i+=4)
			{
				for(int k=-1; k<=1; k+=2)
				{
					try
					{
						if (" ".equals(chessBoard[r + tmp * i][c + tmp * k])|| Character.isLowerCase(chessBoard[r + tmp * i][ c + tmp * k].charAt(0))) 
						{
							oldPiece = chessBoard[r + tmp * i][c + tmp * k];
							chessBoard[r][c] = " ";
							chessBoard[r + tmp * i][c + tmp * k] = "K";
							if(kingSafe())
							{
								list += r + "" + c + "" + (r + tmp * i) + (c + tmp * k) + oldPiece;
							}
							
							chessBoard[r][c] = "K";
							chessBoard[r + tmp * i][c + tmp * k] = oldPiece;

						}
						//change place on i and k	
						if(" ".equals(chessBoard[r + tmp * k][c + tmp * i]) || Character.isLowerCase(chessBoard[r + tmp * k][c + tmp * i].charAt(0)))
						{
							oldPiece = chessBoard[r + tmp * k][c + tmp * i];
							chessBoard[r][c] = " ";
							chessBoard[r + tmp * k][c + tmp * i] = "K";
							if(kingSafe())
							{
								list += r + "" + c + "" + (r + tmp * k) + (c + tmp * i) + oldPiece;
							}
							chessBoard[r][c] = "K";
							chessBoard[r + tmp * k][c + tmp * i] = oldPiece;
						}
					
					}
					catch(Exception e){}
				}
			}
	        return list;
	    }
	    public static String possibleB(int value)
	    {
	        String list="", oldPiece;
	        int r = value / 8, c = value % 8;
			int tmp = 1;
			for(int i = -1; i <= 1;i+=2) // -1 and +1 is direction left right
			{
				for(int k=-1; k <= 1; k+=2)
				{
					try
					{
						while(" ".equals(chessBoard[r+tmp*i][c+tmp*k]))
						{
							oldPiece = chessBoard[r+tmp*i][c+tmp*k];
							chessBoard[r][c] = " ";
							chessBoard[r+tmp*i][c+tmp*k] = "B";
							if (kingSafe())
							{
								list = list + r + c + "" + (r + tmp * i) + (c + tmp * k) + oldPiece;
							}
							chessBoard[r][c] = "B";
							chessBoard[r+tmp*i][c+tmp*k] = oldPiece;
							tmp ++;
							
						}
						if(Character.isLowerCase(chessBoard[r+tmp*i][c+tmp*k].charAt(0))) //
						{
							oldPiece = chessBoard[r+tmp*i][c+tmp*k];
							chessBoard[r][c] = " ";
							chessBoard[r+tmp*i][c+tmp*k] = "B";
							if(kingSafe())
	 						{
	 							list = list + r+c+ "" + (r+tmp*i) + (c+tmp*k) + oldPiece;	
	 						}
							chessBoard[r][c] = "B";
							chessBoard[r+tmp*i][c+tmp*k] = oldPiece;
						}
					}
					catch(Exception e){}
					tmp = 1;
				}
			
			}
	        return list;
	    }
	    public static String possibleQ(int value)
	    {
	    	String list = "", oldPiece;
			int r = value / 8, c = value % 8;
			int tmp = 1;
			for(int i = -1; i <= 1;i++) // -1 and +1 is direction left right
			{
				for(int k =- 1; k <= 1;k++) 
				{
					try
					{
						while(" ".equals(chessBoard[r+tmp*i][c+tmp*k]))
						{
							oldPiece = chessBoard[r+tmp*i][c+tmp*k];
							chessBoard[r][c] = " ";
							chessBoard[r+tmp*i][c+tmp*k] = "Q";
							if(kingSafe())
	 						{
	 							list = list + r+c+ "" + (r+tmp*i) + (c+tmp*k) + oldPiece;	
	 						}
							chessBoard[r][c] = "Q";
							chessBoard[r+tmp*i][c+tmp*k] = oldPiece;
							tmp ++;
						}
						if(Character.isLowerCase(chessBoard[r+tmp*i][c+tmp*k].charAt(0))) //
						{
							oldPiece = chessBoard[r+tmp*i][c+tmp*k];
							chessBoard[r][c] = " ";
							chessBoard[r+tmp*i][c+tmp*k] = "Q";
							if(kingSafe())
	 						{
	 							list = list + r+c+ "" + (r+tmp*i) + (c+tmp*k) + oldPiece;	
	 						}
							chessBoard[r][c] = "Q";
							chessBoard[r+tmp*i][c+tmp*k] = oldPiece;
						}
					}catch(Exception e){/*all out of the "board" is captured here*/}
					tmp = 1;
				}
				
			}
	        return list;
	    }
	    public static String possibleA(int value) 
	    {
	    	 String list="", oldPiece;
	         int r = value/8, c = value%8;
	         try
	         {
	        	 
	         
	         for(int i=-1; i<=1; i++)
	         {
	        	 for(int k=-1; k<=1; k++)
	        	 {
	        		 if(" ".equals(chessBoard[r + 1 * i][c + 1 * k]) || Character.isLowerCase(chessBoard[r + 1 * i][c + 1 * k].charAt(0)))
	        		 {
	        			 oldPiece = chessBoard[r + 1 * i][c + 1 * k];
	        			 chessBoard[r][c] = " ";
	        			 chessBoard[r + 1 * i][c + 1 * k] = "A";
	        			 int tempKing = kingPosC;
	        			 if(kingSafe())
	        				 list += (r)+"" + (c)+"" + (r + 1 * i) + (c + 1 * k) + oldPiece;
	        			chessBoard[r][c] = "A";
	        			chessBoard[r+1*i][c+1*k] = oldPiece;
	        			kingPosC = tempKing;
	        		 }
	        	 }
	        	 System.out.print("\n");
	         }
	         }catch(Exception e){}
	         
	         
	         /* for (int i=0; i<9; i++) //number of squares the king can move to.
	         {
	             if (i!=4) //middle one, the square king is standing on.
	             {
	                 try
	                 {	//either there is a enemy piece or empty
	                	 if( (" ".equals(chessBoard[r-1+i/3][c-1+i%3])) || (Character.isLowerCase(chessBoard[r-1+i/3][c-1+i%3].charAt(0))) )  // 
	                	 {
	 						oldPiece = chessBoard[r-1+i/3][c-1+i%3];
	 						chessBoard[r][c] = " ";
	 						chessBoard[r-1+i/3][c-1+i%3] = "A";
	 						int tempKing = kingPosC;
	 						kingPosC = value + (i/3) * 8 + i%3 - 9; 
	 						if(kingSafe())
	 						{
	 							list += + r+c+ "" + (r-1+i/3) + (c-1+i%3) + oldPiece;	
	 						}
	 						chessBoard[r][c] = "A";
	 						chessBoard[r-1+i/3][c-1+i%3] = oldPiece;
	 						
	 						kingPosC = tempKing;
	 					}
	                 } catch (Exception e) 
	                 {}
	             }
	         }*/
	        
	         return list;
	        
	    
	    }
	   
	    public static boolean kingSafe()
	    {
	      
	    	
	        return true;
	    }
	    
	/* lower letters */
	    /*********************************************************************************/
	    /*********************************************************************************/
	    /*********************************************************************************/
	    /*********************************************************************************/
	    /*********************************************************************************/
	    /*********************************************************************************/
	    /*********************************************************************************/
	    
	    
	    public static String possible_p(int value) 
	    {
	    	String list="" ,oldPiece = "";
	        int r = value/8, c=value%8;
	        int tmp = 1;
	    try{
	    	
	    
	        if(" " == chessBoard[r+1][c])
	        { 
	        	if(" " == chessBoard[r+2][c] && r == 1)
	        	 {
	        		oldPiece = chessBoard[r+2][c];
	        		chessBoard[r][c] = "";
	        		chessBoard[r+2][c] = "p";
	        		if(kingSafe())
	        		{
	        			list = list + (r) + c +  (r+2) + (c) + oldPiece;
	        		}
	        		chessBoard[r][c] = "p";
	        		chessBoard[r+2][c] = oldPiece;
	        	 }
	        	
	        	oldPiece = chessBoard[r+1][c];
	    		chessBoard[r][c] = "";
	    		chessBoard[r+1][c] = "p";
	    		if(kingSafe())
	    		{
	    			list = list + (r) + c +  (r+1) + (c) + oldPiece;
	    		}
	    		chessBoard[r][c] = "p";
	    		chessBoard[r+1][c] = oldPiece;
	        }
	    }catch(Exception e){}
	        for(int i=-1; i<=1; i+=2) // either 1 or -1 
	        {
	        	try
	        	{
	        		if(Character.isUpperCase(chessBoard[r + 1][c + 1 * i].charAt(0)))
	        		{
	        			oldPiece = chessBoard[r + 1][c + 1 * i];	 						//  save target-piece
						chessBoard[r][c] = " ";												//   clear current square
						chessBoard[r+1][c + 1 * i] = "p";									// 	 place current piece on target-square
						if(kingSafe())
							list += (r) + "" + c + "" + (r + 1) + (c + 1 * i) + oldPiece;	//If king is free from harm, valid move
						
						chessBoard[r][c] = "p";												//Return piece to start square
						chessBoard[r+1][c + 1 * i] = oldPiece;								//Return target piece to start square
	        		}
	        	}
	        	catch(Exception e){}
	        }
	        return list ;
	    }
	    public static String possible_r(int value) 
	    {
	    	String list="" ,oldPiece = "";
	        int r = value/8, c=value%8;
	        int tmp = 1;
			for (int i = -1; i <= 1; i++) 
			{
				try 
				{
					
					while(" ".equals(chessBoard[r+tmp*i][c]))//row
					{
						oldPiece = chessBoard[r+tmp*i][c];
						chessBoard[r][c] = " ";
						chessBoard[r+tmp*i][c] = "r";
						if(kingSafe())
							{
								list = list + r+c + (r+tmp*i) + (c) + oldPiece;	
							}
						chessBoard[r][c] = "r";
						chessBoard[r+tmp*i][c] = oldPiece;
						tmp ++;
					}
					if(Character.isUpperCase(chessBoard[r+tmp*i][c].charAt(0))) //
					{
						oldPiece = chessBoard[r+tmp*i][c];
						chessBoard[r][c] = " ";
						chessBoard[r+tmp*i][c] = "r";
						if(kingSafe())
							{
								list = list + r+c+ "" + (r+tmp*i) + (c) + oldPiece;	
							}
						chessBoard[r][c] = "r";
						chessBoard[r+tmp*i][c] = oldPiece;
					}
				} catch (Exception e) {}
				tmp = 1;
				try
				{
					while(" ".equals(chessBoard[r][c+tmp*i]))//col
					{
						oldPiece = chessBoard[r][c+tmp*i];
						chessBoard[r][c] = " ";
						chessBoard[r][c+tmp*i] = "r";
						if(kingSafe())
							{
								list = list + r+c+ "" + (r) + (c+tmp*i) + oldPiece;	
							}
						chessBoard[r][c] = "r";
						chessBoard[r][c+tmp*i] = oldPiece;
						tmp ++;
					}
					if(Character.isUpperCase(chessBoard[r][c+tmp*i].charAt(0))) //
					{
						oldPiece = chessBoard[r][c+tmp*i];
						chessBoard[r][c] = " ";
						chessBoard[r][c+tmp*i] = "r";
						if(kingSafe())
							{
								list = list + r+c+  (r) + (c+tmp*i) + oldPiece;	
							}
						chessBoard[r][c] = "r";
						chessBoard[r][c+tmp*i] = oldPiece;
					}
					
				}catch(Exception e){}
			}
	        return list;
	    }
	    public static String possible_k(int value) 
	    {
	    	String list = "", oldPiece = "";
			int r = value / 8, c = value % 8;
			int tmp = 1;
			for(int i=-2; i <= 2; i+=4)
			{
				for(int k=-1; k<=1; k+=2)
				{
					try
					{
						
						
						if (" ".equals(chessBoard[r + tmp * i][c + tmp * k])|| Character.isUpperCase(chessBoard[r + tmp * i][ c + tmp * k].charAt(0))) 
						{
							/*System.out.println("newR is: " + r + " + "+ tmp + " * " +i + " == " + (r + tmp * i));
							System.out.println("newC is : " + c + " + "+ tmp + " * " +k + " == " + (c + tmp * k));
							System.out.println("----------");*/
							oldPiece = chessBoard[r + tmp * i][c + tmp * k];
							chessBoard[r][c] = " ";
							chessBoard[r + tmp * i][c + tmp * k] = "k";
							if(kingSafe())
							{
								list += r + "" + c + "" + (r + tmp * i) + (c + tmp * k) + oldPiece;
							}
							chessBoard[r][c] = "k";
							chessBoard[r + tmp * i][c + tmp * k] = oldPiece;
							

						}
						//change place on i and k	
						if(" ".equals(chessBoard[r + tmp * k][c + tmp * i]) || Character.isUpperCase(chessBoard[r + tmp * k][c + tmp * i].charAt(0)))
						{
							
							oldPiece = chessBoard[r + tmp * k][c + tmp * i];
							chessBoard[r][c] = " ";
							chessBoard[r + tmp * k][c + tmp * i] = "k";
							if(kingSafe())
							{
								list = list+ r + "" + c + "" + (r + tmp * k) + (c + tmp * i) + oldPiece;
							}
							
							chessBoard[r][c] = "k";
							chessBoard[r + tmp * k][c + tmp * i] = oldPiece;
						}
					
					}
					catch(Exception e){}
				}
			}
	        return list;
	    }
	    public static String possible_b(int value) 
	    {
	    	 String list="", oldPiece;
	         int r = value / 8, c = value % 8;
	 		int tmp = 1;
	 		for(int i = -1; i <= 1;i+=2) // -1 and +1 is direction left right
	 		{
	 			for(int k=-1; k <= 1; k+=2)
	 			{
	 				try
	 				{
	 					while(" ".equals(chessBoard[r+tmp*i][c+tmp*k]))
	 					{
	 						oldPiece = chessBoard[r+tmp*i][c+tmp*k];
	 						chessBoard[r][c] = " ";
	 						chessBoard[r+tmp*i][c+tmp*k] = "b";
	 						if (kingSafe())
	 						{
	 							list = list + r + c + (r + tmp * i) + (c + tmp * k) + oldPiece;
	 						}
	 						chessBoard[r][c] = "b";
	 						chessBoard[r+tmp*i][c+tmp*k] = oldPiece;
	 						tmp ++;
	 						
	 					}
	 					if(Character.isUpperCase(chessBoard[r+tmp*i][c+tmp*k].charAt(0))) //
	 					{
	 						oldPiece = chessBoard[r+tmp*i][c+tmp*k];
	 						chessBoard[r][c] = " ";
	 						chessBoard[r+tmp*i][c+tmp*k] = "b";
	 						if(kingSafe())
	  						{
	  							list = list + r+c + (r+tmp*i) + (c+tmp*k) + oldPiece;	
	  						}
	 						chessBoard[r][c] = "b";
	 						chessBoard[r+tmp*i][c+tmp*k] = oldPiece;
	 					}
	 				}
	 				catch(Exception e){}
	 				tmp = 1;
	 				
	 			}
	 		
	 		}
	         return list;
	    }
	    public static String possible_q(int value) 
	    {
	    	String list="" ,oldPiece = "";
	        int r = value/8, c=value%8;
	        int tmp = 1;
	        
	        return list;
	    }
	    public static String possible_a(int value) 
	    {
	    	String list="" ,oldPiece = "";
	        int r = value/8, c=value%8;
	        int tmp = 1;
	        
	        return list;
	    }
	    public static void printLetterChessBoard()
	    {
	    	for (int i=0;i<8;i++) 
		  	{
	            System.out.println(Arrays.toString(chessBoard[i]));
	        }
			
			System.out.println("-----------------------");
			for (int i=0;i<8;i++) 
		  	{
	            System.out.println(Arrays.toString(chessBoard[i]));
	        }
			/*if(turnC)
				System.out.println("White: " + possibleMovesC());
			else
				System.out.println("Black: " + Main.possibleMovesL());*/
			System.out.println("White: " + possibleMovesC());
			System.out.println("Black: " + possibleMovesL());
	    }
	    public static String randomMove(String moves)
	    {
	    	//whitespace or letter
	    	int length = 0;
	    	for(int i=0; i<moves.length(); i++)
	    	{
	    		if(moves.charAt(i) == ' ' ||   Character.isUpperCase(moves.charAt(i)))
	    		{
	    			length ++;
	    		}
	    	}
	    	String[] possibleMoves = new String[length+1];
	    	int i=0;
	    	int pos = 0;
	    	try{
	    		
	    	
	    	while( i <= moves.length())
	    	{
	    		if(moves.charAt(i) == ' ' ||  Character.isUpperCase(moves.charAt(i)))
	    		{
	    			i++;
	    		}
	    		else
	    		{
	    			possibleMoves[pos] = (Character.getNumericValue(moves.charAt(i)) +""+ Character.getNumericValue(moves.charAt(i+1)) +""+ Character.getNumericValue(moves.charAt(i+2))+"" + Character.getNumericValue(moves.charAt(i+3)));
	    			
	    			i+=4;
	    			pos ++;
	    			
	    		}
	    		//randomMove("2743 7463 8394 2749 8473 9584A9483E");
	    	}
	    	}catch(Exception e){}
	    	
	    	 Random rand = new Random();

	    	 int randomNum = rand.nextInt((6 - 0) + 1) + 0;
	    	 System.out.println(possibleMoves[randomNum]);

	    	 
	    	return possibleMoves[randomNum];
	    }
	}
