package algorithms.mazeGenerators;

/**
 * An abstract department responsible for making mazes
 */
public abstract class AMazeGenerator implements IMazeGenerator{

    //A function to generate the maze
    public abstract Maze  generate (int row, int column);

    /**
     * A function that measures how long it takes to create a maze
     * @param row nuber of rows
     * @param column number of columns
     * @return long running time in milliseconds
     */
    public long measureAlgorithmTimeMillis(int row, int column)
    {
        //Checks whether the numbers are positive
        if(row >= 0 && column >= 0){
            long startTime = System.currentTimeMillis();
            generate(row,column);
            long endTime = System.currentTimeMillis();
            return endTime-startTime;
        }
        return 0;

    }
}
