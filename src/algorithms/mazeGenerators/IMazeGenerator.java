package algorithms.mazeGenerators;



/**
 * An interface that is responsible for making mazes
 */
public interface IMazeGenerator {

    //creates a maze
    Maze generate (int row, int column);

    //measures how long it takes to create a maze
    long measureAlgorithmTimeMillis(int row, int column);

}
