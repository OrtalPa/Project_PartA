package algorithms.mazeGenerators;

/**
 * This class creates an empty maze with no walls
 */
public class EmptyMazeGenerator extends AMazeGenerator {

    /**
     * A function to generate the maze
     * @param row number of rows
     * @param column number of columns
     * @return an empty Maze object
     */
    @Override
    public Maze generate(int row, int column) {

        //Checks whether the numbers are positive
        if(row > 0 && column > 0){
            Position start  = new Position(0,0);
            Position end = new Position(row-1,column-1);
            return new Maze(new int[row][column],start,end);
        }
        return null;
    }
}
