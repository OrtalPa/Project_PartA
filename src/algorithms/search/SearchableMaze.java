package algorithms.search;


import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

/**
 * Department of Labyrinth Adaptation
 */
public class SearchableMaze implements  ISearchable{


    AState start;
    AState end;
    Maze maze;

    /**
     * A constructor who gets a maze and turns it into a linked list
     * @param maze
     */
    public SearchableMaze(Maze maze) {

        if(maze != null && maze.getNumberOfColumns() > 0 && maze.getNumberOfRows() > 0){
            this.maze = maze;
            int row = maze.getNumberOfRows();
            int col = maze.getNumberOfColumns();
            int colend  = maze.getGoalPosition().getColumnIndex();
            int rowend  = maze.getGoalPosition().getRowIndex();
            end  = new MazeState(rowend,colend);
            int colstart = maze.getStartPosition().getColumnIndex();
            int rowstart = maze.getStartPosition().getRowIndex();
            start  = new MazeState(rowstart,colstart);
        }

        else{
            int[][] arrayMaze = {{0,1,1,1} , {0,1,1,1,},{0,0,1,1},{1,0,0,0}};
            Maze mazeNew = new Maze(arrayMaze,new Position(0,0),new Position(3,3));
            this.maze = mazeNew;
            int row = mazeNew.getNumberOfRows();
            int col = mazeNew.getNumberOfColumns();
            int colend  = mazeNew.getGoalPosition().getColumnIndex();
            int rowend  = mazeNew.getGoalPosition().getRowIndex();
            end  = new MazeState(rowend,colend);
            int colstart = mazeNew.getStartPosition().getColumnIndex();
            int rowstart = mazeNew.getStartPosition().getRowIndex();
            start  = new MazeState(rowstart,colstart);

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
     * This function gets a point and a maze and returns a list with
     * all the points that can be accessed from the point
     * @param state
     * @return ArrayList<AState>
     */
    public ArrayList<AState> getAllPossibleStates(AState state)
    {
        ArrayList<AState> list = new ArrayList<>();
        int col =((MazeState)state).getCol();
        int row = ((MazeState)state).getRow();
        if(this.maze.getValue(row,col) == 1){
            return null;
        }

        //1-up
        if(maze.getValue(row -1 ,col) == 0){
            MazeState ms = new MazeState(row -1,col);
            list.add(ms);
        }
        //2-Upper right diagonal
        if(maze.getValue(row -1 ,col+1) == 0 )
        {
            if(maze.getValue(row -1 ,col) == 0 || maze.getValue(row ,col+1) == 0){
                MazeState s1 =new MazeState(row -1,col+1);
                s1.setCost(15);
               list.add(s1);
            }
        }
        //3-right
        if(maze.getValue(row ,col+1) == 0){
            MazeState ms = new MazeState(row,col+1);
            list.add(ms);
        }
        //4- Lower right diagonal
        if(maze.getValue(row +1 ,col+1) == 0 ){
            if(maze.getValue(row ,col+1) == 0 || maze.getValue(row+1 ,col) == 0){
                MazeState s1 =new MazeState(row +1,col+1);
                s1.setCost(15);
                list.add(s1);
            }
        }

        //5-down
        if(maze.getValue(row+1 ,col) == 0){
            MazeState ms = new MazeState(row+1,col);
            list.add(ms);
        }
        //6- Lower left diagonal
        if(maze.getValue(row +1 ,col-1) == 0){
            if(maze.getValue(row+1 ,col) == 0 || maze.getValue(row ,col-1) == 0){
                MazeState s1 =new MazeState(row +1,col-1);
                s1.setCost(15);
                list.add(s1);
            }
        }
        //7-left
        if(maze.getValue(row ,col-1) == 0){
            MazeState ms = new MazeState(row,col-1);
            list.add(ms);
        }
        //8-Upper left diagonal
        if(maze.getValue(row -1 ,col-1) == 0){
            if(maze.getValue(row ,col-1) == 0 || maze.getValue(row -1 ,col) == 0){
                MazeState s1 =new MazeState(row -1,col-1);
                s1.setCost(15);
                list.add(s1);
            }
        }
        return list;
    }

}
