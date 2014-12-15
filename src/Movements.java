import java.util.Arrays;


public class Movements{

	private static String chessBoard[][]=
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
	  static int kingPosC, kingPosL;
	  public String[][] getChessBoard()

		  return chessBoard;
	  }
	  public void printChessBoard()
	  {
			for (int i=0;i<8;i++)
		  	{
	            System.out.println(Arrays.toString(chessBoard[i]));
	        }
	  }
	  /* CAPITAL LETTERS */

	    public String possibleP(int value)
	    {
	        String list="" ,oldPiece = "";
	        int r = value/8, c=value%8;
	        int tmp = 1;

	        if(" " == chessBoard[r-1][c])
	        {
	        	oldPiece = chessBoard[r-1][c];
	        	list = list + (r) +""+ c +  "" + (r-1) + (c) + oldPiece;
	        }
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
	        return list = "";
	    }
	    public String possibleR(int value)
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
	    public String possibleK(int value)
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
	    public String possibleB(int value)
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
	    public String possibleQ(int value)
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
	    public String possibleA(int value)
	    {
	    	 String list="", oldPiece;
	         int r = value/8, c = value%8;
	         for (int i=0; i<9; i++) //number of squares the king can move to.
	         {
	             if (i!=4) //middle one, the square king is standing on.
	             {
	                 try
	                 {	//either there is a enemy piece or empty
	                	 if(Character.isLowerCase(chessBoard[r-1+i/3][c-1+i%3].charAt(0)) || " ".equals(chessBoard[r-1+i/3][c-1+i%3])) //
	                	 {
	 						oldPiece = chessBoard[r-1+i/3][c-1+i%3];
	 						chessBoard[r][c] = " ";
	 						chessBoard[r-1+i/3][c-1+i%3] = "A";
	 						int tempKing = kingPosC;
	 						kingPosC = value + (i/3) * 8 + i%3 - 9;
	 						if(kingSafe())
	 						{
	 							list = list + r+c+ "" + (r-1+i/3) + (c-1+i%3) + " ";
	 						}
	 						chessBoard[r][c] = "A";
	 						chessBoard[r-1+i/3][c-1+i%3] = oldPiece;
	 						kingPosC = tempKing;
	 					}
	                 } catch (Exception e)
	                 {}
	             }
	         }

	         return list;


	    }

	    public static boolean kingSafe() {
	        return true;
	    }



	/* lower letters */
	    /*********************************************************************************/

	    public String possible_p(int value)
	    {
	    	String list="" ,oldPiece = "";
	        int r = value/8, c=value%8;
	        int tmp = 1;

	        if(" " == chessBoard[r+1][c])
	        {
	        	oldPiece = chessBoard[r+1][c];
	        	list = list + (r) +""+ c +  "" + (r+1) + (c) + oldPiece;
	        }
	        for(int i=-1; i<=1; i+=2) // either 1 or -1
	        {
	        	try
	        	{
	        		if(Character.isUpperCase(chessBoard[r + 1][c + 1 * i].charAt(0)))
	        		{
	        			oldPiece = chessBoard[r + 1][c + 1 * i];
	    				list += (r) + "" + c + "" + (r + 1) + (c + 1 * i) + oldPiece;
	        		}
	        	}
	        	catch(Exception e){}
	        }
	        return list;
	    }
	    public String possible_r(int value)
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

	    public  String possible_k(int value)
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
							oldPiece = chessBoard[r + tmp * i][c + tmp * k];
							list += r + "" + c + "" + (r + tmp * i) + (c + tmp * k) + oldPiece;

						}
						//change place on i and k
						if(" ".equals(chessBoard[r + tmp * k][c + tmp * i]) || Character.isUpperCase(chessBoard[r + tmp * k][c + tmp * i].charAt(0)))
						{
							oldPiece = chessBoard[r + tmp * k][c + tmp * i];
							list += r + "" + c + "" + (r) + (c + tmp * i) + oldPiece;
						}

					}
					catch(Exception e){}
				}
			}
	        return list;
	    }

	    public  String possible_b(int value)
	    {
	    	String list="" ,oldPiece = "";
	        int r = value/8, c=value%8;
	        int tmp = 1;

	        return list;
	    }
	    public String possible_q(int value)
	    {
	    	String list="" ,oldPiece = "";
	        int r = value/8, c=value%8;
	        int tmp = 1;

	        return list;
	    }
	    public String possible_a(int value)
	    {
	    	String list="" ,oldPiece = "";
	        int r = value/8, c=value%8;
	        int tmp = 1;

	        return list;
	    }
}
