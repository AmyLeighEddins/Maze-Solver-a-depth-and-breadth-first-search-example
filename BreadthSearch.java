   import java.util.concurrent.LinkedBlockingQueue;
/**
* BreadthSearch - Amy Eddins
* The program uses a breadth-first search strategy to
* find a solution to a maze.
*
* @author Amy Eddins (ale0010@auburn.edu)
* @author Andrew Molony (apm0006@auburn.edu)
* @version 2011-03-04
*/  

   public class BreadthSearch 
   {
   	/**
   	* Boolean for if the finish point is found.
   	*/
      private boolean finishFound;
   	/**
   	* Boolean for if the maze has a solution.
   	*/
      private boolean hasSolution = true;
      /**
   	* Integers for i, numExplored, and steps.
   	*/
      private int i = 1, numExplored = 0, steps = 0;
   	/**
   	* Position variables position and finish.
   	*/
      private Position position, finish;
   	/**
   	* Array of positions solution.
   	*/
      private Position[] solution;
   	/**
   	* Double string array maze.
   	*/
      private String[][] maze;
   	/**
   	* Maze object.
   	*/
      private Maze mazeObject;
      
   	/**
   	* Empty constructor.
   	*/
      public BreadthSearch()
      {
      }
   	/**
   	* Searches the Maze for a solution.
   	*
   	* @param mazeIn the maze object.
   	*/
      public void search(Maze mazeIn)
      {
         maze = mazeIn.getMazeArray();
         mazeObject = mazeIn;
         //creates solution array
         solution = new Position[mazeObject.getRows() * mazeObject.getCols()];
         for (int row = 0; row < mazeIn.getRows(); row++)
         {
         	//find start and finish
            for (int col = 0; col < mazeIn.getCols(); col++)
            {
               if (maze[row][col].equals("S"))
               {
                  solution[0] = new Position(row, col, null);
               }
            	
               if (maze[row][col].equals("F"))
               {
                  finish = new Position(row, col, null);
               }
            } 
         }
         LinkedBlockingQueue<Position> p = new LinkedBlockingQueue<Position>();
         try
         {
            while (!finishFound) //loops until finish is found
            {
               position = solution[i - 1];
               if (searchRight(position)) //search right
               {
                  if (position.equals(finish))
                  {
                     finishFound = true;
                  }
                  Position temp = new Position(position.getY(), 
                  position.getX() + 1, solution[i - 1]);
                  numExplored++;
                  p.offer(temp); //put in queue
               }
               if (searchDown(position)) //search down
               {
                  if (position.equals(finish))
                  {
                     finishFound = true;
                  }
                  Position temp = new Position(position.getY() + 1, 
                  position.getX(), solution[i - 1]);
                  numExplored++;
                  p.offer(temp); //put in queue
               }
               if (searchLeft(position)) //search left
               {
                  if (position.equals(finish))
                  {
                     finishFound = true;
                  }
                  Position temp = new Position(position.getY(), 
                  position.getX() - 1, solution[i - 1]);
                  numExplored++;
                  p.offer(temp); //put in queue
               }
               if (searchUp(position)) //search up
               {
                  if (position.equals(finish))
                  {
                     finishFound = true;
                  }
                  Position temp = new Position(position.getY() - 1, 
                  position.getX(), solution[i - 1]);
                  numExplored++;
                  p.offer(temp); //put in queue
               }
               if (position.equals(finish))
               {
                  finishFound = true;
               }
               else 
               {
                  if (p.peek() != null)
                  {
                     solution[i] = p.poll(); //poll from queue
                  }
                  i++;
               }
            }
         }
            catch (NullPointerException e)
            {
               finishFound = true;
               hasSolution = false;
            }
      }
      /**
   	* Returns if the maze is solvable.
   	*
   	* @return hasSolution if the maze is solvable
   	*/
      public boolean isSolvable()
      {
         return hasSolution;
      }
      /**
   	* Returns the number explored.
   	*
   	* @return numExplored The number positions explored.
   	*/
      public int getNumExplored()
      {
         return numExplored;
      }
      /**
   	* Returns the percentage of the maze explored.
   	*
   	* @return output The percentage of the maze explored.
   	*/
      public double getPercentage()
      {
         double output = ((double) getNumExplored() 
         / ((double) mazeObject.getRows()
         * (double) mazeObject.getCols())) * 100;
         return output;
      }
      /**
   	* Returns the solution length.
   	*
   	* @return i solution length.
   	*/
      public int getSolutionLength()
      {
         int i = 0;
         while (solution[i] != null)
         {
            i++;
         }
         return i;
      }
      /**
   	* Returns if the maze is solvable.
   	*
   	* @return hasSolution if the maze is solvable
   	*/
      public int getSteps()
      {
         int i = 1;
         Position temp = new Position(solution[getSolutionLength() - 1].getY(),
            solution[getSolutionLength() - 1].getX(), 
            solution[(getSolutionLength()) - 1].getPrevious());
            
         while (temp.getPrevious() != null)
         {
            i++;
            temp = temp.getPrevious();
         }
         steps = i;
         return steps;
      }
      /**
   	* Sets the maze array with "o"'s for the solution.
   	*/
      public void setSolution()
      {
         Position[] tempPos = new Position[getSteps()];
         
         Position temp = new Position(solution[getSolutionLength() - 1].getY(),
            solution[getSolutionLength() - 1].getX(), 
            solution[(getSolutionLength()) - 1].getPrevious()); 
            
         tempPos[getSteps() - 1] = temp; 
         for (int i = getSteps() - 1; i > 0; i--)
         {
            temp = temp.getPrevious();
            if (temp != null && hasSolution)
            { 
               tempPos[i - 1] = temp;
               if (!maze[temp.getY()][temp.getX()].equals("S"))
               {
                  maze[temp.getY()][temp.getX()] = "o";
               }
            }  
         }
      }
      /**
   	* Sets the maze array with "o"'s for the solution.
   	* And prints it out.
   	*
   	* @return output The maze solution.
   	*/
      public String printSolution()
      {
         String output = "";
         Position[] tempPos = new Position[getSteps()];
         
         Position temp = new Position(solution[getSolutionLength() - 1].getY(),
            solution[getSolutionLength() - 1].getX(), 
            solution[(getSolutionLength()) - 1].getPrevious()); 
         tempPos[getSteps() - 1] = temp; 
         for (int i = getSteps() - 1; i > 0; i--)
         {
            temp = temp.getPrevious();
            if (temp != null && hasSolution)
            {  
               tempPos[i - 1] = temp;
               if (!maze[temp.getY()][temp.getX()].equals("S"))
               {
                  maze[temp.getY()][temp.getX()] = "o";
               }
            }
         }
         int j;
         for (j = 0; j <= getSteps() - 2; j++)
         {
            output += tempPos[j].toString() + "->";
         }
         output += tempPos[j].toString();
         return output;
      }
      /**
   	* Returns whether it is able to search down.
   	*
   	* @param pIn position 
   	* @return true/false if you can search down.
   	*/
      public boolean searchDown(Position pIn)
      {
         if (pIn.getY() + 1 < mazeObject.getRows())
         {	
            if (maze[pIn.getY() + 1][pIn.getX()].equals("-") 
            || maze[pIn.getY() + 1][pIn.getX()].equals("F"))
            {
               if (!maze[pIn.getY() + 1][pIn.getX()].equals("F")
               && !maze[pIn.getY() + 1][pIn.getX()].equals("S"))
               {
                  maze[pIn.getY() + 1][pIn.getX()] = ".";
               }
               return true;
            }
            else 
            { 
               return false;
            }
         }
         else
         {
            return false;
         }
      }
   	/**
   	* Returns whether it is able to search right.
   	*
   	* @param pIn position 
   	* @return true/false if you can search right.
   	*/
      public boolean searchRight(Position pIn)
      {
         if (pIn.getX() + 1 < mazeObject.getCols())
         {
            if (maze[pIn.getY()][pIn.getX() + 1].equals("-") 
            || maze[pIn.getY()][pIn.getX() + 1].equals("F"))
            {
               if (!maze[pIn.getY()][pIn.getX() + 1].equals("F")
               && !maze[pIn.getY()][pIn.getX() + 1].equals("S"))
               {
                  maze[pIn.getY()][pIn.getX() + 1] = ".";
               }
               return true;
            }
            else 
            { 
               return false;
            }
         }
         else
         {
            return false;
         }
      }
   	/**
   	* Returns whether it is able to search left.
   	*
   	* @param pIn position 
   	* @return true/false if you can search left.
   	*/
      public boolean searchLeft(Position pIn)
      {
         if (pIn.getX() - 1 >= 0)
         {
            if (maze[pIn.getY()][pIn.getX() - 1].equals("-") 
            || maze[pIn.getY()][pIn.getX() - 1].equals("F"))
            {
               if (!maze[pIn.getY()][pIn.getX() - 1].equals("F")
               && !maze[pIn.getY()][pIn.getX() - 1].equals("S"))
               {
                  maze[pIn.getY()][pIn.getX() - 1] = ".";
               }
               return true;
            }
            else 
            {
               return false;
            }
         }
         else
         {
            return false;
         }
      }
   	/**
   	* Returns whether it is able to search up.
   	*
   	* @param pIn position 
   	* @return true/false if you can search up.
   	*/
      public boolean searchUp(Position pIn)
      {
         if (pIn.getY() - 1 >= 0)
         {
            if (maze[pIn.getY() - 1][pIn.getX()].equals("-") 
            || maze[pIn.getY() - 1][pIn.getX()].equals("F"))
            {
               if (!maze[pIn.getY() - 1][pIn.getX()].equals("F")
               && !maze[pIn.getY() - 1][pIn.getX()].equals("S"))
               {
                  maze[pIn.getY() - 1][pIn.getX()] = ".";
               }
               return true;
            }
            else 
            { 
               return false;
            }
         }
         else
         {
            return false;
         }
      }
   }