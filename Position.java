 /**
* Position - Amy Eddins
* This class creates a position object representing the 
* position in the maze. 
* 
* @author Amy Eddins (ale0010@auburn.edu)
* @author Andrew Molony (apm0006@auburn.edu)
* @version 2011-03-04
*/  
	public class Position
   {
		/**
		* Integers for y and x.
		*/
      private int y, x;
		/**
		* Position object variable.
		*/
      private Position p;
      /**
		* Constructor that takes in the y and x coordinates 
		* and the previous position.
		*
		* @param yIn y integer
		* @param xIn x integer
		* @param pIn position object
		*/
      public Position(int yIn, int xIn, Position pIn)
      {
         y = yIn;
         x = xIn;
         p = pIn;
      }
   	/**
		* Returns y.
		*
		* @return y Y coordinate integer.
		*/
      public int getY()
      {
         return y;
      }
   	/**
		* Returns x.
		*
		* @return x X coordinate integer.
		*/
      public int getX()
      {
         return x;
      }
   	/**
		* Returns Postion object.
		*
		* @return p Position object.
		*/
      public Position getPrevious()
      {
         return p;
      }
   	/**
		* Returns a boolean if the positions are equal or not.
		*
		* @param p position object.
		* @return true/false if positions are equal.
		*/
      public boolean equals(Position p)
      {
         if (this.getY() == p.getY() && this.getX() == p.getX())
         {
            return true;
         }
         else
         {
            return false;
         }
      }
      /**
		* Returns a String of the position object.
		*
		* @return "(" + getY() + "," + getX() + ")" String of object.
		*/
      public String toString()
      {
         return "(" + getY() + "," + getX() + ")";
      }
   }