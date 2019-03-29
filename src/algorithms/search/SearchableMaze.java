package algorithms.search;

import algorithms.mazeGenerators.Maze;

import java.util.ArrayList;
import java.util.List;

/**
 * Department of Labyrinth Adaptation
 */
public class SearchableMaze implements  ISearchable{

    boolean[][] Visited;
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

        //sets all points as not visited
        Visited = new boolean[row][col];
        for (int i = 0; i < Visited.length; i++) {
            for (int j = 0; j < Visited[0].length; j++) {
                Visited[i][j] = false;
            }
        }
    }

    /**
     * The function returns the starting point
     * @return AState
     */
    public AState getStart() {
        return start;
    }

    /**
     * The function returns the end point
     * @return AState
     */
    public AState getEnd() {
        return end;
    }

    /**
     * sets a state as visited
     * @param state Maze State to set as visited
     */
    @Override
    public void setStateAsVisited(AState state) {
        if (state instanceof MazeState)
        {
            MazeState ms = (MazeState)state;
            ms.setVisited(true);
            Visited[ms.getRow()][ms.getCol()] = true;
        }
    }

    @Override
    public boolean getStateAsVisited(AState state) {
        if (state instanceof MazeState)
        {
            return Visited[((MazeState)state).getRow()][((MazeState)state).getCol()];
        }
        return false;
    }

    /**
     * This function gets a point and a maze and returns a list with
     * all the points that can be accessed from the point
     * @param state
     * @return ArrayList<AState>
     */
    public ArrayList<AState> getAllPossibleStates(AState state)
    {
        ArrayList<AState> list = new ArrayList<AState>();
        int col =((MazeState)state).getCol();
        int row = ((MazeState)state).getRow();
        if(this.maze.getValue(row,col) == 1){
            return null;
        }

        //1-up
        if(maze.getValue(row -1 ,col) == 0){
            MazeState ms = new MazeState(row -1,col);
            ms.setVisited(Visited[row -1][col]);
            list.add(ms);
        }
        //2-Upper right diagonal
        if(maze.getValue(row -1 ,col+1) == 0 )
        {
            if(maze.getValue(row -1 ,col) == 0 || maze.getValue(row ,col+1) == 0){
                MazeState s1 =new MazeState(row -1,col+1);
                s1.setVisited(Visited[row -1][col+1]);
                s1.setCost(1);
               list.add(s1);
        }
        }
        //3-right
        if(maze.getValue(row ,col+1) == 0){
            MazeState ms = new MazeState(row,col+1);
            ms.setVisited(Visited[row][col+1]);
            list.add(ms);
        }
        //4- Lower right diagonal
        if(maze.getValue(row +1 ,col+1) == 0 ){
            if(maze.getValue(row ,col+1) == 0 || maze.getValue(row+1 ,col) == 0){
                MazeState s1 =new MazeState(row +1,col+1);
                s1.setVisited(Visited[row +1][col+1]);
                s1.setCost(1);
                list.add(s1);
            }

        }
        //5-down
        if(maze.getValue(row+1 ,col) == 0){
            MazeState ms = new MazeState(row+1,col);
            ms.setVisited(Visited[row +1][col]);
            list.add(ms);
        }
        //6- Lower left diagonal
        if(maze.getValue(row +1 ,col-1) == 0){
            if(maze.getValue(row+1 ,col) == 0 || maze.getValue(row ,col-1) == 0){
                MazeState s1 =new MazeState(row +1,col-1);
                s1.setVisited(Visited[row +1][col-1]);
                s1.setCost(1);
                list.add(s1);
            }
        }
        //7-left
        if(maze.getValue(row ,col-1) == 0){
            MazeState ms = new MazeState(row,col-1);
            ms.setVisited(Visited[row][col-1]);
            list.add(ms);
        }
        //8-Upper left diagonal
        if(maze.getValue(row -1 ,col-1) == 0){
            if(maze.getValue(row ,col-1) == 0 || maze.getValue(row -1 ,col) == 0){
                MazeState s1 =new MazeState(row -1,col-1);
                s1.setVisited(Visited[row -1][col-1]);
                s1.setCost(1);
                list.add(s1);
            }
        }
        return list;
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
