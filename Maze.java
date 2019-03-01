import java.util.*;
import java.io.*;
public class Maze{
    private char[][]maze;
    private int rows, cols, steps;
    private int[] start = new int[2];
    private boolean animate;//false by default
    private static int[] x = new int[]{0,1,0,-1};
    private static int[] y = new int[]{1,0,-1,0};
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
    public int solve(){
      solve(start[0], start[1]); //System.out.println(maze[start[0]][start[1]]);
      return steps;
    }
    private boolean solve(int row, int col){
        if(animate){
            clearTerminal();
            System.out.println(this);
            wait(20);
        }
        if (maze[row][col] == 'E'){return true;}
        if (maze[row][col] != ' ' && maze[row][col] != 'S'){return false;}
        maze[row][col] = '@'; steps++;
        for(int i = 0; i < 4; i++){
          //System.out.println(i);
          if (solve(row+x[i],col+y[i])){
            return true;
          }
        }
        maze[row][col] = '.'; steps--;
        return false;
    }
    public static void main(String[] args) throws FileNotFoundException{
      Maze m = new Maze("Maze1.txt");
      //System.out.println(m);
      //m.setAnimate(true);
      System.out.println(m.solve());
      System.out.println(m);
    }

}
