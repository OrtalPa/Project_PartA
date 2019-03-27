package algorithms.search;

import algorithms.mazeGenerators.Maze;

import java.util.ArrayList;
import java.util.List;

/**
 * Department of Labyrinth Adaptation
 */
public class SearchableMaze implements  ISearchable{

    //ArrayList<List<MazeState>> list;
    AState start;
    AState end;
    Maze maze;

    /**
     * A constructor who gets a maze and turns it into a linked list
     * @param maze
     */
    public SearchableMaze(Maze maze) {

        this.maze = maze;
        int row = maze.getRowLength();
        int col = maze.getColLength();
       int colend  = maze.getGoalPosition().getColumnIndex();
        int rowend  = maze.getGoalPosition().getRowIndex();
        end  = new MazeState(rowend,colend);
        int colstart = maze.getStartPosition().getColumnIndex();
        int rowstart = maze.getStartPosition().getRowIndex();
        start  = new MazeState(rowstart,colstart);

       // list = new ArrayList<>();

        /*
        for(int i=0;i<row; i++) {
            for (int j = 0; j < col; j++) {
                MazeState state = new MazeState(i, j);

                list.add(getAllPossibleStates(state));
            }
        }
        */

    }

    /**
     * The function returns the starting point
     * @return AState
     */
    public AState getStart() {
        //deep copy ??
        return start;
    }

    /**
     * The function returns the end point
     * @return AState
     */
    public AState getEnd() {
        //deep copy ??
        return end;
    }

    /**
     * This function gets a point and a maze and returns a list with
     * all the points that can be accessed from the point
     * @param state
     * @return ArrayList<AState>
     */
    public ArrayList<MazeState> getAllPossibleStates(AState state)
    {

        ArrayList<MazeState> list = new ArrayList<MazeState>();
        int col =((MazeState)state).getCol();
        int row = ((MazeState)state).getRow();
        if(this.maze.getValue(row,col) == 1){
            return null;
        }
       // list.add(state);

        //1-up
        if(maze.getValue(row -1 ,col) == 0){
            list.add(new MazeState(row -1,col));
        }
        //2-Upper right diagonal
        if(maze.getValue(row -1 ,col+1) == 0 )
        {
            if(maze.getValue(row -1 ,col) == 0 || maze.getValue(row ,col+1) == 0){
                MazeState s1 =new MazeState(row -1,col+1);
                s1.setCost(1);
               list.add(s1);
        }
        }
        //3-right
        if(maze.getValue(row ,col+1) == 0){
            list.add(new MazeState(row,col+1));
        }
        //4- Lower right diagonal
        if(maze.getValue(row +1 ,col+1) == 0 ){
            if(maze.getValue(row ,col+1) == 0 || maze.getValue(row+1 ,col) == 0){
                MazeState s1 =new MazeState(row +1,col+1);
                s1.setCost(1);
                list.add(s1);
            }

        }
        //5-down
        if(maze.getValue(row+1 ,col) == 0){
            list.add(new MazeState(row+1,col));
        }
        //6- Lower left diagonal
        if(maze.getValue(row +1 ,col-1) == 0){
            if(maze.getValue(row+1 ,col) == 0 || maze.getValue(row ,col-1) == 0){
                MazeState s1 =new MazeState(row +1,col-1);
                s1.setCost(1);
                list.add(s1);
            }
        }
        //7-left
        if(maze.getValue(row ,col-1) == 0){
            list.add(new MazeState(row,col-1));
        }
        //8-Upper left diagonal
        if(maze.getValue(row -1 ,col-1) == 0){
            if(maze.getValue(row ,col-1) == 0 || maze.getValue(row -1 ,col) == 0){
                MazeState s1 =new MazeState(row -1,col-1);
                s1.setCost(1);
                list.add(s1);
            }
        }
        return list;
    }

    // add by shir
    public int[][]  ListOfAllPoints(){
        int[][] ListOfAllPoints = new int[maze.getRowLength()][maze.getColLength()];
        return ListOfAllPoints;
    }

    /**
     *
     * The function returns the route as a solution to the maze
     * @return ArrayList<AState>
     */
    public ArrayList<MazeState> getSolutionPath(){
        return null;
    }

}
