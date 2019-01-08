import java.awt.Graphics;
import javax.swing.ImageIcon;
public class Square
{
   private ImageIcon t = new ImageIcon("Images/top.png");
   private ImageIcon r = new ImageIcon("Images/right.png");
   private ImageIcon b = new ImageIcon("Images/bottom.png");
   private ImageIcon l = new ImageIcon("Images/left.png");
   
   private ImageIcon tr = new ImageIcon("Images/topright.png");
   private ImageIcon tb = new ImageIcon("Images/topbottom.png");
   private ImageIcon tl = new ImageIcon("Images/lefttop.png");
   private ImageIcon rb = new ImageIcon("Images/rightbottom.png");
   private ImageIcon rl = new ImageIcon("Images/rightleft.png");
   private ImageIcon bl = new ImageIcon("Images/bottomleft.png");
   
   private ImageIcon trb = new ImageIcon("Images/toprightbottom.png");
   private ImageIcon trl = new ImageIcon("Images/lefttopright.png");
   private ImageIcon tbl = new ImageIcon("Images/bottomlefttop.png");
   private ImageIcon rbl = new ImageIcon("Images/rightbottomleft.png");
   
   private ImageIcon trbl = new ImageIcon("Images/all.png");
   
   private ImageIcon none = new ImageIcon("Images/none.png");
   
   private ImageIcon curr;
   
   private int x,y;
      
   private boolean top, right, bottom, left,visited;
   
   public Square(int r, int c)
   {
      top=right=bottom=left=visited=false;
      curr=none;
      x=r;
      y=c;
   }
   
   public int getX()
   {
      return x;
   }
   
   public int getY()
   {
      return y;
   }
   
   public boolean getVisited()
   {
      return visited;
   }
   
   public boolean getTop()
   {
      return top;
   }
   
   public boolean getRight()
   {
      return right;
   }
   
   public boolean getBottom()
   {
      return bottom;
   }
   
   public boolean getLeft()
   {
      return left;
   }
   
   public void setVisited(boolean b)
   {
      visited=b;
   }
   
   public ImageIcon getPic()
   {
      return curr;
   }
   
   public void setSide(int n, boolean ba)
   {
      if(n==1)
         top=ba;
      else if(n==2)
         right=ba;
      else if(n==3)
         bottom=ba;
      else
         left=ba;
      if(top)
      {
         if(right)
         {
            if(bottom)
            {
               if(left)
               {
                  curr=trbl;
                  return;
               }
               curr=trb;
               return;
            }
            else if(left)
            {
               curr=trl;
               return;
            }
            curr=tr;
            return;
         }
         if(bottom)
         {
            if(left)
            {
               curr=tbl;
               return;
            }
            curr=tb;
            return;
         }
         else if(left)
         {
            curr=tl;
            return;
         }
         curr=t;
         return;
      }
      if(right)
         {
            if(bottom)
            {
               if(left)
               {
                  curr=rbl;
                  return;
               }
               curr=rb;
               return;
            }
            else if(left)
            {
               curr=rl;
               return;
            }
            curr=r;
            return;
         }
         if(bottom)
         {
            if(left)
            {
               curr=bl;
               return;
            }
            curr=b;
            return;
         }
         else if(left)
         {
            curr=l;
            return;
         }
      curr=none;
   }
   
}