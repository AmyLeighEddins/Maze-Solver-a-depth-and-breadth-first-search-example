   import java.util.Stack;
   import java.util.ArrayList;
   
   /**
   * Depth Class - scans a maze that is represented
   * by a 2D string array using a stack-based depth-first
   * technique. Determines a path from the start point
   * to the finish point, although not necessarily the
   * fastest path due to the depth-first technique.
   *
   * @author Andrew Molony (apm0006@auburn.edu)
	* @author Amy Eddins (ale0010@auburn.edu)
   * @version 2011-03-03.
   */
   
   public class Depth {
   
      /** the actual maze object that will be explored. */
      private String[][] maze;
   	/** the number of cells in the maze that have been explored. */
      private int numExplored;
      /** The number of steps taken on the correct path to the
   	* finish location. */
      private int steps;
      /** The number of columns in the maze. */
      private int columns;
      /** The number of rows in the maze. */
      private int rows;
   	/** The starting location of the maze, represented by
   	* an Array where index 0 is the row, and index 1 is the
   	* column. */
      private int[] start;
      /** The stack object that keeps track of the path
   	* taken to reach the finish location. */
      private Stack<int[]> stack;
      /**
   	*	The path from the starting point to the end point.
   	* Does not contain path until after depthSearch method called */
      private ArrayList<int[]> path;
   
   	/**
   	* Constructor for the Depth class - instantiates
   	* a stack object that will hold the current "position"
   	* at the top, and a history of all steps taken to
   	* arrive at that position within the stack, and also
   	* instantiates counter variables. 
   	*/
      public Depth() {
         stack = new Stack<int[]>();
         path = new ArrayList<int[]>();
      }
   	
   	/**
   	* Given a maze represented by a 2D array of
   	* string values, searches the maze for the finish
   	* location using a depth-first strategy by calling
   	* the depthScan method. Returns a string where
   	* the path taken is represented by "o" String objects.
   	* Also keeps track of the steps taken.
   	*
   	* @param mazeIn The maze to be searched, represented
   	* by a 2D String array. See the depthScan method
   	* for more precise details on how the maze should
   	* be represented in the array.
   	* @return out A string that represents the maze,
   	* as well as a successful path through the maze.
   	*/
      public String depthSearch(String[][] mazeIn) {
         stack.clear();
         String out = new String("");
         stack = depthScan(mazeIn); //determines solution stack		
         steps = 0;
         
      	// plot solution path through maze
         //remove start location so "S" is not altered
         if (!stack.isEmpty()) {
            int[] element = stack.pop();
            path.add(element);
            int size = stack.size();
         // loop through stack, excluding "F" element,
         // to plot "o" line and add to collection so stack
         // is not lost
            for (int k = 1; k < size; k++) {
               int[] location = stack.pop();
               maze[location[0]][location[1]] = "o";
               path.add(location);
               steps++;
            }
         // add final element to path
            path.add(stack.pop());
            steps++;
         }
         // loop through maze elements for return string
         for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
               out += maze[i][j];
            }
            out += "\n";
         }
         return out;
      }
      
   	/**
   	* Returns the solution path through the maze.
   	* WARNING: pops from a stack created by the
   	* depthSearch method to return path. If depthSearch
   	* method has not been called, return value
   	* will be incorrect. If called multiple times, will
   	* return empty stack all proceeding times after
   	* the first (all values have been popped off).
   	*
   	* @return A solution path as a string of coordinates,
   	* following the form (0,0)-->(0,1)--> etc. If no
   	* solution path exists, returns an empty string.
   	*/
   	
      public String solutionPath() {
         String out = "";
         int[] element;
         if (!path.isEmpty()) {
            element = path.get(path.size() - 1);
            out += "(" + element[0] + "," + element[1]
               + ")";
         for (int i = path.size() - 2; i >= 0; i--) {
            element = path.get(i);
            out += "-->(" + element[0] + "," + element[1]
               + ")";
         }
         }
      	else {
      		return "**Soltion path does not exist**";
      	}
         return out;
      }
      
   	/**
   	* Returns the number of steps taken in the path
   	* to the finish location of the maze.
   	*
   	* @return steps The number of steps taken to the
   	* finish location. If depthSearch method has not
   	* been called, will return 0.
   	*/
      public int steps() {
         return steps;
      }
   	/**
   	* Returns the number of cells that have been explored.
   	* This number is inclusive of the Finish location, but
   	* does not include the starting location.
   	*
   	* @return numExplored the number of cells that have been
   	* explored.
   	*/
      public int numExplored() {
         return numExplored;
      }
   	
   	/**
   	* Utility method for the depthSearch class - actually
   	* searches the parameter while recording a solution stack.
   	* Scans the parameter maze using a depth-first strategy.
   	* The maze inputted as a parameter must follow the
   	* specified format: A 2D String array where each index
   	* has only one string value. All "open" cells, such as 
   	* cells that can be traversed, must be marked with a "-";
   	* All "closed" cells, or cells that cannot be traversed,
   	* must be marked with an "x"; The starting point must
   	* be marked with a "S" (case-sensitive), and the finish
   	* point must be marked with a "F" (case sensitive). If any
   	* other String objects are included, or if the start/finish
   	* objects are not included, the method will fail.
   	* Depth-first scan may not find the fastest route, as it
   	* attempts to go as far as possible in one direction before
   	* changing course. Default direction is right, then down, then
   	* left, then up. If no solution is possible, the method
   	* will return an empty stack.
   	*
   	* @param mazeIn a 2D string array where each index has one string
   	* value. Array must be in the proper format for the method
   	* to function correctly. See method description for proper format.
   	* @return stack A stack of array objects indicative of the
   	* path to be taken in reverse order. Each "step" is represented
   	* by an array object, where the 0 index value is the row of that
   	* step, and the 1 index value is the column of that step.
   	*/
      private Stack depthScan(String[][] mazeIn) {
         numExplored = 0;
         maze = mazeIn;
         columns = maze[0].length;
         rows = maze.length;
         //find the starting location
         start = startSearch(rows, columns);
         if (start[0] == -1) {
            System.out.println("Error: No starting Location");
            return stack;
         }
         //set the current location to the starting location
         int[] current = new int[2];
         current[0] = start[0];
         current[1] = start[1];
         //push starting location onto stack
         stack.push(start);
         boolean found = false;
         //while the finish has not been found
         while (!found) {
            boolean run = true;
            // if current location is the finish, end loop
            if (maze[current[0]][current[1]].equals("F")) {
               found = true;
               run = false;
            }
            // an empty stack indicates no solution. End loop and
         	// return the empty stack
            if (stack.empty()) {
               System.out.println("**WARNING: maze unsolvable!**");
               return stack;
            }
            // only runs if it is possible to move right (calls method that
            // checks possibility
            else if (isRight(current[0], current[1])) {
               current[1] += 1;
               // avoids removing F symbol from maze
               if (!maze[current[0]][current[1]].equals("F")) {
                  maze[current[0]][current[1]] = ".";
               }
               // adds location to stack. Cannot add current as current
            	// is an object reference, so creates new object
               int[] p = new int[2];
               p[0] = current[0];
               p[1] = current[1];
               stack.push(p);
               // makes all other else cases false for this loop iteration
               run = false;
               numExplored++;
            }
            // same logic as previous, but checks down
            else if (run && isDown(current[0], current[1])) {          
               current[0] += 1;
               if (!maze[current[0]][current[1]].equals("F")) {               
                  maze[current[0]][current[1]] = ".";
               }
               int[] p = new int[2];
               p[0] = current[0];
               p[1] = current[1];                             
               stack.push(p);
               run = false;
               numExplored++;
            }
            // same logic as previous, but checks left
            else if (run && isLeft(current[0], current[1])) {          
               current[1] -= 1;
               if (!maze[current[0]][current[1]].equals("F")) {               
                  maze[current[0]][current[1]] = ".";
               } 
               int[] p = new int[2];
               p[0] = current[0];
               p[1] = current[1];                             
               stack.push(p);
               run = false;
               numExplored++;
            }
            
            // same logic as previous, but checks up
            else if (run && isTop(current[0], current[1])) {           
               current[0] -= 1;
               if (!maze[current[0]][current[1]].equals("F")) {              
                  maze[current[0]][current[1]] = "."; 
               }
               int[] p = new int[2];
               p[0] = current[0];
               p[1] = current[1];                           
               stack.push(p);
               run = false;
               numExplored++;
            }
            
            // if all other else cases fail, pop the location
            // from the top of the stack (take a step back)
            else if (run) {
               stack.pop();
               if (!stack.isEmpty()) {
                  int[] temp = stack.peek();
                  current[0] = temp[0];
                  current[1] = temp[1];
               }
            }            	   	
         }
            
         return stack;
      }
      
   	/**
   	* Utility method for the depthScan method. Determines
   	* whether or not it is possible for a step to be taken
   	* in the right direction (moving one column forward).
   	*
   	* @param row the current row.
   	* @param col the current column.
   	* @return boolean value representative of whether
   	* or not it is possible to move in the right direction.
   	*/
      private boolean isRight(int row, int col) {
         if ((col + 1 < columns) 
         	&& ((maze[row][col + 1].equals("-"))
         	|| (maze[row][col + 1].equals("F")))) {
            return true;
         }
         return false;
      }
   
   	/**
   	* Utility method for the depthScan method. Determines
   	* whether or not it is possible for a step to be taken
   	* in the down direction (moving one row forward).
   	*
   	* @param row the current row.
   	* @param col the current column.
   	* @return boolean value representative of whether
   	* or not it is possible to move in the down direction.
   	*/      
      private boolean isDown(int row, int col) {
         if ((row + 1 < rows) 
         	&& ((maze[row + 1][col].equals("-"))
         	|| (maze[row + 1][col].equals("F")))) {
            return true;
         }
         return false;
      }
      
   	/**
   	* Utility method for the depthScan method. Determines
   	* whether or not it is possible for a step to be taken
   	* in the left direction (moving one column backwards).
   	*
   	* @param row the current row.
   	* @param col the current column.
   	* @return boolean value representative of whether
   	* or not it is possible to move in the left direction.
   	*/   	
      private boolean isLeft(int row, int col) {
         if ((col - 1 >= 0) 
         	&& ((maze[row][col - 1].equals("-"))
         	|| (maze[row][col - 1].equals("F")))) {
            return true;
         }
         return false;
      }
      
   	/**
   	* Utility method for the depthScan method. Determines
   	* whether or not it is possible for a step to be taken
   	* in the up direction (moving one row backwards).
   	*
   	* @param row the current row.
   	* @param col the current column.
   	* @return boolean value representative of whether
   	* or not it is possible to move in the up direction.
   	*/   	
      private boolean isTop(int row, int col) {
         if ((row - 1 >= 0) 
         	&& ((maze[row - 1][col].equals("-"))
         	|| (maze[row - 1][col].equals("F")))) {
            return true;
         }
         return false;
      }   	   	
      
   	/**
   	* Utility method for the depthScan method - searches 
   	* for the starting point of the maze, represented
   	* by the character string "S".
   	* 
   	* @param row the amount of rows in the maze.
   	* @param col the amount of columns in the maze.
   	* @return startSpot an array of length 2 that
   	* indicates to the user where the starting location
   	* is. Index 0 represents the row, index 1 represents 
   	* the column. If no starting location is found, returns
   	* [-1,-1].
   	*/
      private int[] startSearch(int row, int col) {
         boolean found = false;
         int[] startSpot = new int[2];
         int i = -1;
         int j;
         while (!found && i < row) { // loops through rows
            i++;
            j = 0;
            while (!found && j < col) { //loops through columns
            	// if the current location contains the start
               if (maze[i][j].equals("S")) {
                  startSpot[0] = i;
                  startSpot[1] = j;
                  found = true;
               }
               else {
                  j++;
               }
            }
         }
         
      	// if not found, return negative numbers indicating so
         if (!found) {
            startSpot[0] = -1;
            startSpot[1] = -1;
         }
         
         return startSpot;
      }	  	   	
   }