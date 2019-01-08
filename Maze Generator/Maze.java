import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.*;
public class Maze extends JPanel
{
   private ImageIcon st = new ImageIcon("Images/start.png");
   private ImageIcon end = new ImageIcon("Images/end.png");
   private ImageIcon crossHair = new ImageIcon("Images/crossHair.GIF");
   private ImageIcon path = new ImageIcon("Images/dots.png");
   
   private static final int SIZE=15;
   
   private static int playerR=0;			
   private static int playerC=0;
   
   private static int robotR=0;			
   private static int robotC=0;
   
   public boolean solve=false;
   
   private static Square[][] board;
   
   private static Robot robot;
   
   public static MyStack<Square> stack = new MyStack();
   public static ArrayList<Point> points = new ArrayList();
   
   private int numRows = 40;		
   private int numColumns = 40;
   
   public Maze()
   {
      robot = new Robot();
      board = new Square[numRows][numColumns];
      for(int r=0;r<numRows;r++)
         for(int c=0;c<numColumns;c++)
            board[r][c]= new Square(r,c);
      stack.push(board[(int)(Math.random()*numRows)][(int)(Math.random()*numColumns)]);
      create(stack);
   }
   
   public void redraw()
   {
      playerR=0;			
      playerC=0;
      robotR=0;			
      robotC=0;
      solve=false;
      points=new ArrayList();
      board = new Square[numRows][numColumns];
      for(int r=0;r<numRows;r++)
         for(int c=0;c<numColumns;c++)
            board[r][c]= new Square(r,c);
      stack.push(board[(int)(Math.random()*numRows)][(int)(Math.random()*numColumns)]);
      create(stack);
   }
   
   public void create(MyStack<Square> s)
   {
      if(!s.isEmpty())
      {  
         s.peek().setVisited(true);
         int x=s.peek().getX();
         int y=s.peek().getY();
         int numSides=0;
         if(y-1>-1 && !board[x][y-1].getVisited())
         {
            numSides++;
         }
         if(y+1<numColumns && !board[x][y+1].getVisited())
         {
            numSides++;
         }
         if(x-1>-1 && !board[x-1][y].getVisited())
         {
            numSides++;
         }
         if(x+1<numRows && !board[x+1][y].getVisited())
         {
            numSides++;
         }
         if(numSides==0 /*|| (x==numRows-1 && y==numColumns-1) ||(x==0 && y==0)*/)
         {
            s.pop();
            create(s);
         }
         else
         {
            int rnd= (int)(Math.random()*numSides)+1;
            if(y-1>-1 && !board[x][y-1].getVisited())//right to left
            {
               rnd--;
               if(rnd==0)
               {
                  board[x][y-1].setVisited(true);
                  board[x][y-1].setSide(2,true);
                  board[x][y].setSide(4,true);
                  s.push(board[x][y-1]);
                  create(s);
               }
            }
            if(y+1<numColumns && !board[x][y+1].getVisited())//left to right
            {
               rnd--;
               if(rnd==0)
               {
                  board[x][y+1].setVisited(true);
                  board[x][y+1].setSide(4,true);
                  board[x][y].setSide(2,true);
                  s.push(board[x][y+1]);
                  create(s);
               }
            }
            if(x-1>-1 && !board[x-1][y].getVisited())//bottom to top
            {
               rnd--;
               if(rnd==0)
               {
                  board[x-1][y].setVisited(true);
                  board[x-1][y].setSide(3,true);
                  board[x][y].setSide(1,true);
                  s.push(board[x-1][y]);
                  create(s);
               }
            }
            if(x+1<numRows && !board[x+1][y].getVisited())//top to bottom
            {
               rnd--;
               if(rnd==0)
               {
                  board[x+1][y].setVisited(true);
                  board[x+1][y].setSide(1,true);
                  board[x][y].setSide(3,true);
                  s.push(board[x+1][y]);
                  create(s);
               }
            }
         }
      }
   }
   
   public void step()
   {
      points.add(new Point(robotR,robotC));
      boolean contains=false;
      for(int i=0;i<points.size()-1;i++)
      {
         if(points.size()>1 && points.get(points.size()-1).equals(points.get(i)))
         {
            points.remove(points.size()-2);
            i--;
         }
      }
      if(robotR==numRows-1 && robotC==numColumns-1)
      {
         solve=false;
      }
      else
      {
         if(robot.getDir()==0)
         {
            if(board[robotR][robotC].getLeft())
            {
               robot.setDir(-1);
               robotC--;
            }
            else if(board[robotR][robotC].getTop())
            {
               robotR--;
            }
            else
            {
               robot.setDir(1);
            }
         }
         else if(robot.getDir()==1)
         {
            if(board[robotR][robotC].getTop())
            {
               robot.setDir(-1);
               robotR--;
            }
            else if(board[robotR][robotC].getRight())
            {
               robotC++;
            }
            else
            {
               robot.setDir(1);
            }
         }
         else if(robot.getDir()==2)
         {
            if(board[robotR][robotC].getRight())
            {
               robot.setDir(-1);
               robotC++;
            }
            else if(board[robotR][robotC].getBottom())
            {
               robotR++;
            }
            else
            {
               robot.setDir(1);
            }
         }
         else if(robot.getDir()==3)
         {
            if(board[robotR][robotC].getBottom())
            {
               robot.setDir(-1);
               robotR++;
            }
            else if(board[robotR][robotC].getLeft())
            {
               robotC--;
            }
            else
            {
               robot.setDir(1);
            }
         }
      }
      repaint();
   }

   public void showBoard(Graphics g)	
   {
      int x =0, y=0;		//upper left corner location of where image will be drawn
      for(int r=0;r<board.length;r++)
      {
         x = 0;						//reset the row distance
         for(int c=0;c<board[0].length;c++)
         {
            g.drawImage(board[r][c].getPic().getImage(), x, y, SIZE, SIZE, null);
            if(x==0 && y==0)
               g.drawImage(st.getImage(), x, y, SIZE, SIZE, null);
            if(r==(numRows-1) && c==(numColumns-1))
               g.drawImage(end.getImage(), x, y, SIZE, SIZE, null);
            if(r==playerR && c==playerC)	//draw the crosshair on the board after the cell has been drawn
               g.drawImage(crossHair.getImage(), x, y, SIZE, SIZE, null);
            if(r==robotR && c==robotC)	//draw the crosshair on the board after the cell has been drawn
               g.drawImage(robot.getPicture().getImage(), x, y, SIZE, SIZE, null);
            if(!solve)//draws path from start to arrow                  
               for(int i =0; i<points.size();i++)
                  g.drawImage(path.getImage(), points.get(i).getC()*SIZE, points.get(i).getR()*SIZE, SIZE, SIZE, null);
         
            x+=SIZE;
         }
         y+=SIZE;
      }
   }
   
   public void processUserInput(int k)
   {
      if(k==KeyEvent.VK_Q || k==KeyEvent.VK_ESCAPE)					//End the program	
         System.exit(1);
      if(k==KeyEvent.VK_UP && playerR>0)
      {
         if(board[playerR][playerC].getTop())
            playerR--;
      }
      else 
         if(k==KeyEvent.VK_DOWN && playerR<board.length-1)
         {
            if(board[playerR][playerC].getBottom())
               playerR++;
         }
         else
            if(k==KeyEvent.VK_LEFT && playerC>0)
            {
               if(board[playerR][playerC].getLeft())
                  playerC--;
            }
            else 
               if(k==KeyEvent.VK_RIGHT && playerC<board[0].length-1)
               {
                  if(board[playerR][playerC].getRight())
                     playerC++;
               }
               else 
                  if(k==KeyEvent.VK_SPACE)
                     redraw();
                  else
                     if(k==KeyEvent.VK_S)
                        solve=!solve;
      repaint();			//refresh the screen
   }

   public void paintComponent(Graphics g)
   {
      super.paintComponent(g); 
      g.setColor(Color.blue);		//draw a blue boarder around the board
      g.fillRect(0, 0, (board[0].length*SIZE), (board.length*SIZE));
      showBoard(g);					//draw the contents of the array board on the screen
      if(solve)
         step();
   }
}