   import java.util.Scanner;
   import java.io.File;
/**
* Maze - Amy Eddins
* This class represents a maze with a double string array.
*
* @author Amy Eddins (ale0010@auburn.edu)
* @author Andrew Molony (apm0006@auburn.edu)
* @version 2011-03-04
*/  
   public class Maze
   {
		/**
		* String double array to represent the maze.
		*/
      private String[][] maze;
		/**
		* Integers for the number of rows a columns.
		*/
      private int rows, cols;
   	/**
		* Constructor that creates a maze array from the filename.
		*
		* @param fileIn Name of file.
		* @throws Exception Catches exception
		*/
      public Maze(String fileIn) throws Exception
      {
         File inputMaze = new File(fileIn); // Create a file for the input file
         if (inputMaze == null) //in case the file is null
         {
            System.out.print("no file");
         }
         Scanner scan = new Scanner(inputMaze);
			
      	//Create array of file names
         String[] array = scan.nextLine().split(" "); 
         rows = Integer.parseInt(array[0]); //read in number of rows
         cols = Integer.parseInt(array[1]); //read in number of cols
         maze = new String[rows][cols]; //create maze 
         int i = 0;
         while (i < rows) //fill up maze
         {
            String[] mazeArray = scan.nextLine().split("");
            for (int col = 1; col <= cols; col++)
            {
               maze[i][col - 1] = (mazeArray[col]); 
            }
            i++;
         }
      }
		/**
		* Returns a clone of the string maze double array.
		*
		* @return (String[][])maze.clone() A clone of the maze array.
		*/
		public String[][] getMazeArray()
		{
			return (String[][]) maze.clone();
		}
      /**
		* Returns the number of rows in the maze array.
		*
		* @return rows The number of rows.
		*/
      public int getRows()
      {
         return rows;
      }
   	/**
		* Returns the number of columns in the maze array.
		*
		* @return cols The number of columns.
		*/
      public int getCols()
      {
         return cols;
      }
   	/**
		* Returns the maze array as a String.
		*
		* @return output The maze array as a String. 
		*/
      public String toString()
      {
         String output = "";
         for (int row = 0; row < getRows(); row++)
         {
            for (int col = 0; col < getCols(); col++)
            {
               output += maze[row][col];
            }
            output += "\n";
         }
         return output;
      }
   }