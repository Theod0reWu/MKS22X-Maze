import java.util.*;
import java.io.*;
public class Maze{
    private char[][]maze;
    private int rows, cols;
    private int[] start = new int[2];
    private boolean animate;//false by default
    public Maze(String filename) throws FileNotFoundException{
      Scanner inf;
      try{
        File text = new File(filename);
        inf = new Scanner(text);
      }
      catch (FileNotFoundException e){throw new FileNotFoundException("invalid filename");}
      String data = "";
      data += inf.nextLine();
      int cols = data.length(); int rows=1;
      while(inf.hasNextLine()){
         data += inf.nextLine();
         rows++;
      }
      this.rows = rows; this.cols = cols;
      maze = new char[rows][cols];
      int c = 0; int starts = 0; int ends = 0;
      for (int r = 0; r < rows; r++){
        for (int l = 0; l < cols; l++){
          maze[r][l] = data.charAt(c);
          if (maze[r][l] == 'S'){starts++; start[0] = r; start[1] = l;}
          if (maze[r][l] == 'E'){ends++;}
          c++;
        }
      }
      if (starts != 1 || ends != 1){throw new IllegalStateException("invalid starting and ending points");}
    }
    public String toString(){
      String out = "";
      for (int r =0;r<rows;r++){
        for(int c =0 ; c < cols; c++){
          out+=maze[r][c];
        }
        out+="\n";
      }
      return out;
    }

    private void wait(int millis){
         try {
             Thread.sleep(millis);
         }
         catch (InterruptedException e) {
         }
     }


    public void setAnimate(boolean b){
        animate = b;
    }
    public void clearTerminal(){
        System.out.println("\033[2J\033[1;1H");
    }
    /*Wrapper Solve Function returns the helper function
      Note the helper function has the same name, but different parameters.
      Since the constructor exits when the file is not found or is missing an E or S, we can assume it exists.

    */
    public int solve(){
            return 1;
    }
    /*
      Recursive Solve function:
      A solved maze has a path marked with '@' from S to E.
      Returns the number of @ symbols from S to E when the maze is solved,
      Returns -1 when the maze has no solution.
      Postcondition:

        The S is replaced with '@' but the 'E' is not.

        All visited spots that were not part of the solution are changed to '.'

        All visited spots that are part of the solution are changed to '@'
    */
    private boolean solve(int row, int col){ //you can add more parameters since this is private
        if(animate){
            clearTerminal();
            System.out.println(this);
            wait(20);
        }
        return -1; //so it compiles
    }
    public static void main(String[] args) throws FileNotFoundException{
      Maze m = new Maze("Maze1.txt");
      System.out.println(m);
    }

}
