   import java.io.File;
   import java.text.DecimalFormat;
 /**
* Maze Solver - Amy Eddins
* This driver program prints out solutions to the 
* mazes.
*
* @author Amy Eddins (ale0010@auburn.edu)
* @author Andrew Molony (apm0006@auburn.edu)
* @version 2011-03-04
*/  
   public class MazeSolver
   {
		/**
		* Prints out the solutions to the mazes with breadth and depth searches.
		* 
		* @param args arguments.
		* @throws Exception throws exception
		*/
      public static void main(String[] args) throws Exception
      {
         DecimalFormat d = new DecimalFormat("0.##");
			String[] arrayFileNames;
         File directory = new File(args[0]);
         arrayFileNames = directory.list();
            for (int j = 0; j < arrayFileNames.length; j++)
            {
					//Depth 
            	System.out.println("Sample output using a"
					+ " depth-first strategy:\n");
            	System.out.println("////////////////////////////");
            	System.out.println("Maze file: " + arrayFileNames[j] + "\n");
            	System.out.println("Original maze:\n");
            	Maze m = new Maze(args[0] + "/" + arrayFileNames[j]);
            	System.out.print(m.toString() + "\n");
            	System.out.println("Explored maze:\n");
            	Depth depth = new Depth();
            	String s = depth.depthSearch(m.getMazeArray());
            	System.out.println(s);
            	System.out.println("Number of cells explored: "
					+ depth.numExplored() + " (" 
            	+ ((((double)depth.numExplored() / ((double)m.getRows() 
					* (double)m.getCols())) * 100) + "%)"));
            	System.out.println("\nSolution:\n");
            	System.out.println(depth.solutionPath());
            	System.out.println("Number of steps in solution: " 
					+ depth.steps());
            	System.out.println("\n\n\n");
            
            
            	//Breadth
            	Maze m2 = new Maze(args[0] + "/" + arrayFileNames[j]);
               System.out.println("Sample output using a"
					+ " breadth-first strategy:");
               System.out.println("////////////////////////////");
               System.out.println("Maze file: " + arrayFileNames[j] + "\n");
               System.out.println("Original maze:\n");
               System.out.print(m2.toString() + "\n");
               System.out.println("Explored maze:\n");
               BreadthSearch b = new BreadthSearch();
               b.search(m2);
               b.setSolution();
               System.out.print(m2.toString() + "\n");
               System.out.println("Number of cells explored: " 
					+ b.getNumExplored() 
					+ " (" + d.format(b.getPercentage()) + "%)");
               if (b.isSolvable())
               {
                  System.out.println("\nSolution:\n");
                  System.out.print(b.printSolution());
                  System.out.println("\n\nNumber of steps in solution: " 
						+ (b.getSteps() - 1) + "\n\n\n\n");
               }
               else
               {
                  System.out.print("\nNo Solution\n\n\n\n");
               }
            } 
      }
   }