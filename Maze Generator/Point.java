public class Point
{
   private int row, col;
   public Point(int r, int c)
   {
      row=r;
      col=c;
   }
   public int getR()
   {
      return row;
   }
   public int getC()
   {
      return col;
   }
   public boolean equals(Point p)
   {
      Point that = p;
      if(that.getR()==this.getR() && that.getC()==this.getC())
      {
         return true;
      }
      return false;
   }
}