import javax.swing.ImageIcon;
public class Robot
{
   private ImageIcon u = new ImageIcon("Images/arrowup.png");
   private ImageIcon r = new ImageIcon("Images/arrowright.png");
   private ImageIcon d = new ImageIcon("Images/arrowdown.png");
   private ImageIcon l = new ImageIcon("Images/arrowleft.png");
   
   private ImageIcon curr;
   
   private int direction;
      
   public Robot()
   {
      direction =0;
      curr=u;
   }
   
   public ImageIcon getPicture()
   {
      return curr;
   }
   
   public int getDir()
   {
      return direction;
   }
   
   public void setDir(int a)
   {
      direction+=a;
      if(direction<0)
         direction=3;
      if(direction>3)
         direction=0;
      if(direction==0)
         curr=u;
      else if(direction==1)
         curr=r;
      else if(direction==2)
         curr=d;
      else
         curr=l;
   }
      
   
}