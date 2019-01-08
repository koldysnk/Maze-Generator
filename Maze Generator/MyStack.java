import java.util.*;

public class MyStack<anyType> implements Stackable<anyType>
{
   private List<anyType> list;
   private int numE;
   public MyStack()
   {
      list = new LinkedList();
      numE=0;
   }
   public void push(anyType x)
   {
      list.add(0,x);
   }	   

   public anyType pop()
   {
      if(list.isEmpty())
         return null;
      return list.remove(0);
   }		      
                                    
   public anyType peek()
   {
      if(list.isEmpty())
         return null;
      return list.get(0);
   }		      
                                    
   public boolean isEmpty()
   {
      
         return list.isEmpty();
   }	      
   
   public int size()
   {
      return list.size();
   }  
}